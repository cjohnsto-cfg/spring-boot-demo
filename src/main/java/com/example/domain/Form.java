package com.example.domain;

public class Form {

	// theOtherParam form field
	private String theOtherParam;

	// theParam form field
	private String theParam;

	public String getTheParam() {
		return theParam;
	}

	public void setTheParam(String theParam) {
		this.theParam = theParam;
	}

	public String getTheOtherParam() {
		return theOtherParam;
	}

	public void setTheOtherParam(String theOtherParam) {
		this.theOtherParam = theOtherParam;
	}

	@Override
	public String toString() {
		return "Form{" +
			   "theParam='" + theParam + '\'' +
			   ", theOtherParam='" + theOtherParam + '\'' +
			   '}';
	}
}
