%%%-------------------------------------------------------------------
%%% @author sangu
%%% @copyright (C) 2023, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 03. mag 2023 11:20
%%%-------------------------------------------------------------------
-module(mnesia_handler).

%% API
-export([init/0, add_message/5, retrieve_messages/4, add_mapping_student/2, retrieve_mapping_student/1, add_mapping_tutor/2,
  retrieve_mapping_tutor/1, retrieve_chat_list/3, add_student_name/2, retrieve_student_name/1, add_tutor_name/2, retrieve_tutor_name/1,
  remove_mapping_student/1, remove_mapping_tutor/1]).
-include("schema.hrl").

init() ->
  mnesia:create_schema([node()]),
  mnesia:start(),
  %%check if tables is present
  case mnesia:wait_for_tables([messages, tutorNames, studentNames, pidStudentMap, pidTutorMap],4000) == ok of
    true-> %%if true, finish
      io:format("mnesia initialized ~n"),
      ok;
    false -> %%else, create table
      io:format("mnesia initialized 2~n"),
      mnesia:create_table(messages,
        [{attributes, record_info(fields, messages)},
          {disc_copies, [node()]},
          {type, bag}
        ]),
      mnesia:create_table(pidStudentMap,
        [{attributes, record_info(fields, pidStudentMap)},
          {disc_copies, [node()]}
        ]),
      mnesia:create_table(pidTutorMap,
        [{attributes, record_info(fields, pidTutorMap)},
          {disc_copies, [node()]}
        ]),
      mnesia:create_table(studentNames,
        [{attributes, record_info(fields, studentNames)},
          {disc_copies, [node()]}
        ]),
      mnesia:create_table(tutorNames,
        [{attributes, record_info(fields, tutorNames)},
          {disc_copies, [node()]}
        ]),
      io:format("mnesia initialized 3~n")
  end.

add_message(IdSender, SenderRole, IdDest, Message, Timestamp)->
  %%write new record into table
  Insert = fun()->
    mnesia:write(#messages{
      idSender = IdSender,
      senderRole = SenderRole,
      idDest = IdDest,
      message = Message,
      banana = Timestamp
    })
           end,
  mnesia:activity(transaction,Insert).

retrieve_messages(IdSender, SenderRole, IdDest, DestRole) ->
  RetrieveSent = fun() ->
    Match = #messages{idSender = IdSender, idDest = IdDest, senderRole = SenderRole, message= '$1', banana = '$2'},
    Guard = [],
    Response = ['$_'],
    mnesia:select(messages, [{Match, Guard, Response}])
      end,
  {atomic, Sent} = mnesia:transaction(RetrieveSent),
  %IdSender, IdDest,
  RetrieveReceived = fun() ->
    Match = #messages{idSender = IdDest, idDest = IdSender, senderRole = DestRole, message= '$1', banana = '$2'},
    Guard = [],
    Response = ['$_'],
    mnesia:select(messages, [{Match, Guard, Response}])
      end,
  {atomic, Received} = mnesia:transaction(RetrieveReceived),
  %IdDest, IdSender,
  Sent ++ Received.

add_mapping_student(Id, Pid)->
  %%write new record into table
  Insert = fun()->
    mnesia:write(#pidStudentMap{
      id = Id,
      pid = Pid
    })
           end,
  mnesia:activity(transaction,Insert).

retrieve_mapping_student(Id) ->
  F = fun() ->
    R = #pidStudentMap{id=Id, pid='$1', _='_'},
    mnesia:select(pidStudentMap, [{R, [], ['$1']}])
      end,
  {atomic, Output} = mnesia:transaction(F),
  Output.

retrieve_mapping_student_from_pid(Pid) ->
  F = fun() ->
    R = #pidStudentMap{id='$1', pid=Pid, _='_'},
    mnesia:select(pidStudentMap, [{R, [], ['$1']}])
      end,
  {atomic, Output} = mnesia:transaction(F),
  Output.

remove_student_map(Id) ->
  F = fun() ->
    mnesia:delete({pidStudentMap, Id})
      end,
  mnesia:transaction(F).

remove_mapping_student(Pid) ->
  List = retrieve_mapping_student_from_pid(Pid),
  [remove_student_map(X) || X <- List].

add_mapping_tutor(Id, Pid)->
  %%write new record into table
  Insert = fun()->
    mnesia:write(#pidTutorMap{
      id = Id,
      pid = Pid
    })
           end,
  mnesia:activity(transaction,Insert).

retrieve_mapping_tutor(Id) ->
  F = fun() ->
    R = #pidTutorMap{id=Id, pid='$1', _='_'},
    mnesia:select(pidTutorMap, [{R, [], ['$1']}])
      end,
  {atomic, Output} = mnesia:transaction(F),
  Output.

retrieve_mapping_tutor_from_pid(Pid) ->
  F = fun() ->
    R = #pidTutorMap{id='$1', pid=Pid, _='_'},
    mnesia:select(pidTutorMap, [{R, [], ['$1']}])
      end,
  {atomic, Output} = mnesia:transaction(F),
  Output.

remove_tutor_map(Id) ->
  F = fun() ->
    mnesia:delete({pidTutorMap, Id})
      end,
  mnesia:transaction(F).

remove_mapping_tutor(Pid) ->
  List = retrieve_mapping_tutor_from_pid(Pid),
  [remove_tutor_map(X) || X <- List].

add_student_name(Id, Fullname)->
  %%write new record into table
  Insert = fun()->
    mnesia:write(#studentNames{
      idStudent = Id,
      fullNameStudent = Fullname
    })
           end,
  mnesia:activity(transaction,Insert).

retrieve_student_name(Id) ->
  F = fun() ->
    Match = #studentNames{idStudent =Id, fullNameStudent='$1', _='_'},
    Result = ['$1'],
    mnesia:select(studentNames, [{Match, [], Result}])
      end,
  {atomic, Output} = mnesia:transaction(F),
  Output.

add_tutor_name(Id, Fullname)->
  %%write new record into table
  Insert = fun()->
    mnesia:write(#tutorNames{
      idTutor = Id,
      fullNameTutor = Fullname
    })
           end,
  mnesia:activity(transaction,Insert).

retrieve_tutor_name(Id) ->
  F = fun() ->
    Match = #tutorNames{idTutor=Id, fullNameTutor = '$1', _='_'},
    Result = ['$1'],
    mnesia:select(tutorNames, [{Match, [], Result}])
      end,
  {atomic, Output} = mnesia:transaction(F),
  Output.

helper_function(Id, Role) ->
  if
    Role =:= "student" ->
      Fullname = retrieve_tutor_name(Id);
    Role =:= "tutor" ->
      Fullname = retrieve_student_name(Id);
    true ->
      Fullname = ""
  end,
  Fullname.

retrieve_chat_list(Id, Role, RoleDest) ->
  F1 = fun() ->
    Match = #messages{idSender=Id, senderRole=Role, idDest='$1', _='_'},
    Guard = [],
    Result = ['$1'],
    mnesia:select(messages, [{Match, Guard, Result}])
      end,
  {atomic, Result1} = mnesia:transaction(F1),
  F2 = fun() ->
    Match = #messages{idSender='$1', idDest=Id, senderRole=RoleDest, _='_'},
    Guard = [],
    Result = ['$1'],
    mnesia:select(messages, [{Match, Guard, Result}])
      end,
  {atomic, Result2} = mnesia:transaction(F2),
  FullIdList = Result1 ++ Result2,
  Set = sets:from_list(FullIdList),
  IdList = sets:to_list(Set),
  %%[ [{"prova", list_to_helper_function(X, Role)}, {"prova2", X}] || X <- IdList ].
  [ {helper_function(X, Role), [X]} || X <- IdList ].


