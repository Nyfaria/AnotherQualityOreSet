package com.nyfaria.anotherqualityoreset.api.tree.builder;

public class AbortSearchException extends RuntimeException{
	public AbortSearchException(String reason){
		super(reason);
	}
}
