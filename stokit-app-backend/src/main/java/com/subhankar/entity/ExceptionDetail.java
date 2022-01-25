package com.subhankar.entity;

import java.util.Date;

public class ExceptionDetail {
    private Date timeStamp;
    private String message;
    private String details;
    private int errorCode;

    public ExceptionDetail(Date timeStamp, String message, String details, int errorCode) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
