%%%-------------------------------------------------------------------
%%% @author sangu
%%% @copyright (C) 2023, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 03. mag 2023 11:21
%%%-------------------------------------------------------------------
-module(web_socket_handler).
-import(string, [equal/2]).
-export([init/2, websocket_init/1, websocket_handle/2, websocket_info/2, terminate/3]).


init(Req, State) ->
  Opts = #{idle_timeout => infinity},
  {cowboy_websocket, Req, State, Opts}.  %Perform websocket setup

websocket_init(State) ->
  {ok, State}.

websocket_handle({text, Json}, State) ->
  Map = mochijson2:decode(Json),
  erlang:display(Json),
  {struct, JsonData} = Map,
  Request = erlang:binary_to_list(proplists:get_value(<<"request">>, JsonData)),
  if
    Request =:= "connect" ->
      UserId = erlang:binary_to_list(proplists:get_value(<<"id">>, JsonData)),
      Fullname = erlang:binary_to_list(proplists:get_value(<<"fullname">>, JsonData)),
      Role = erlang:binary_to_list(proplists:get_value(<<"role">>, JsonData)),
      erlang:display(Role),
      if
          Role =:= "student" ->
            io:format("connect student ~p~n", [self()]),
            mnesia_handler:add_student_name(UserId, Fullname),
            mnesia_handler:add_mapping_student(UserId, self()),
            Msg = erlang:iolist_to_binary(io_lib:format("{sendList} ~p", [mnesia_handler:retrieve_chat_list(UserId, "student", "tutor")]));
          Role =:= "tutor" ->
            io:format("connect tutor ~p~n", [self()]),
            mnesia_handler:add_tutor_name(UserId, Fullname),
            mnesia_handler:add_mapping_tutor(UserId, self()),
            Msg = erlang:iolist_to_binary(io_lib:format("{sendList} ~p", [mnesia_handler:retrieve_chat_list(UserId, "tutor", "student")]));
          true ->
            Msg = "Error",
            io:format("Error")
      end;
    Request =:= "connectNewChat" ->
      io:format("create new chat~n"),
      UserId = erlang:binary_to_list(proplists:get_value(<<"id">>, JsonData)),
      Fullname = erlang:binary_to_list(proplists:get_value(<<"fullname">>, JsonData)),
      IdDest = erlang:binary_to_list(proplists:get_value(<<"idDest">>, JsonData)),
      FullnameDest = erlang:binary_to_list(proplists:get_value(<<"fullnameDest">>, JsonData)),
      mnesia_handler:add_student_name(UserId, Fullname),
      mnesia_handler:add_mapping_student(UserId, self()),
      mnesia_handler:add_tutor_name(IdDest, FullnameDest),
      Msg = erlang:iolist_to_binary(io_lib:format("{sendList} ~p", [mnesia_handler:retrieve_chat_list(UserId, "student", "tutor")]));
    Request =:= "requestMessages" ->
      UserId = erlang:binary_to_list(proplists:get_value(<<"id">>, JsonData)),
      DestId = erlang:binary_to_list(proplists:get_value(<<"idDest">>, JsonData)),
      Role = erlang:binary_to_list(proplists:get_value(<<"role">>, JsonData)),
      DestRole = erlang:binary_to_list(proplists:get_value(<<"destRole">>, JsonData)),
      Msg = erlang:iolist_to_binary(io_lib:format("{fullChat} ~p", [mnesia_handler:retrieve_messages(UserId, Role, DestId, DestRole)]));
    Request =:= "sendMessage" ->
      UserId = erlang:binary_to_list(proplists:get_value(<<"id">>, JsonData)),
      MyName = erlang:binary_to_list(proplists:get_value(<<"myName">>, JsonData)),
      DestId = erlang:binary_to_list(proplists:get_value(<<"idDest">>, JsonData)),
      Role = erlang:binary_to_list(proplists:get_value(<<"role">>, JsonData)),
      Timestamp = erlang:binary_to_list(proplists:get_value(<<"timestamp">>, JsonData)),
      Message = erlang:binary_to_list(proplists:get_value(<<"message">>, JsonData)),
      mnesia_handler:add_message(UserId, Role, DestId, Message, Timestamp),
      if
        Role =:= "student" ->
          Pid = mnesia_handler:retrieve_mapping_tutor(DestId);
        Role =:= "tutor" ->
          Pid = mnesia_handler:retrieve_mapping_student(DestId);
        true->
          Pid = -1
      end,
      Msg = "sent",
      [ X ! {text, erlang:iolist_to_binary(io_lib:format("{newMessage} ~p", [{Message, UserId, Timestamp, MyName}]))} || X <- Pid];
    true ->
      Msg = "error"
  end,
  {
    reply,
    {text, Msg},
    State
  };

websocket_handle(_Other, State) ->  %Ignore
  erlang:display(_Other),
  {ok, State}.

websocket_info({text, Msg}, State) ->
  {reply, {text, Msg}, State};

websocket_info(_Other, State) ->
  {ok, State}.

terminate(_Reason, _Req, _State) ->
  mnesia_handler:remove_mapping_student(self()),
  mnesia_handler:remove_mapping_tutor(self()),
  erlang:display("client exited").