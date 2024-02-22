%%%-------------------------------------------------------------------
%% @doc chat_erlang public API
%% @end
%%%-------------------------------------------------------------------

-module(chat_erlang_app).

-behaviour(application).

-export([start/2, stop/1]).

start(_Type, _Args) ->
    Routes = [ {
        '_',
        [
            {"/", web_socket_handler, []}
        ]
    } ],
    Dispatch = cowboy_router:compile(Routes),

    ProtoOpts = #{env => #{dispatch => Dispatch}},

    {ok, _} = cowboy:start_clear(my_http_listener,
        [{port, 9000}], ProtoOpts),
    mnesia_handler:init(),
    chat_erlang_sup:start_link().

stop(_State) ->
    ok.
