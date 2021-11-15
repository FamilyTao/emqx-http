package com.unionman.mqtt.exception;

public class UnionmanException extends RuntimeException{

	private static final long serialVersionUID = -1831051539157107204L;

	private String errorCode;

	public UnionmanException() {
		super();
	}

	public UnionmanException(String msg) {
		super(msg);
	}

	public UnionmanException(Throwable e) {
		super(e);
	}

	public UnionmanException(String msg, Throwable e) {
		super(msg, e);
	}

	public UnionmanException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
