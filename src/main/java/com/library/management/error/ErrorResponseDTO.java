package com.library.management.error;



import java.io.Serializable;

/***
 *	ErrorResponseDTO Class 
 *  
 *  ######################################################################################################################
 *  Author        Date               Implementation Note in Brief
 *  
 *
 *  
 *  
 *  ######################################################################################################################
 *
 **/
public class ErrorResponseDTO implements Serializable {
	
	//=================================================
    // Static variables
    //=================================================
	
	private static final long serialVersionUID = 6106668430566998391L;
	
	//=================================================
    // Private variables
    //=================================================

	private String errorCode;

	private String errorResponse;

	private int httpStatusCode;
	
	//=================================================
    // Accessors and Mutators
    //=================================================
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	
	//=================================================
    // Public Methods
    //=================================================
	
	@Override
	public String toString() {
		return "ErrorDTO [errorCode=" + errorCode + ", errorResponse=" + errorResponse + ", httpStatusCode="
				+ httpStatusCode + "]";
	}

}
