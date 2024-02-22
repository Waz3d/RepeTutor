.PHONY: all deps run

PROJECT = erws


all:: deps
	rebar3 compile

release: deps
	APP_ROOT=var rebar3 release

deps:
	rebar3 get-deps

clean::
	rm -fr ebin log  erl_crash.dump

run:
	#erl $(LIB_ARGS) -boot priv/erws
	APP_ROOT=var rebar3 shell --name erlang@10.2.1.48