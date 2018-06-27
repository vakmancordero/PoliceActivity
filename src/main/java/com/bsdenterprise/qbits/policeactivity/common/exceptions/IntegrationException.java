package com.bsdenterprise.qbits.policeactivity.common.exceptions;

public class IntegrationException extends Exception {

    private String path;

    public IntegrationException(String message){
        super(message);
    }

    public IntegrationException(String path, String message){
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

