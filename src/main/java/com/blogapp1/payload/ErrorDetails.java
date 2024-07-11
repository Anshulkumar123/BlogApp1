package com.blogapp1.payload;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String message;
    private String Detail;

    public ErrorDetails(Date date, String message, String detail) {
        this.date = date;
        this.message = message;
        Detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return Detail;
    }
}
