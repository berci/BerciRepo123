package com.berta.test.br;

public interface BRCallable<R> {
	R call(IBR br) throws Exception;
}
