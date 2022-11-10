package com.epam.esm.enums;

public enum UserType {

    USER("USER"),
    CLIENT("CLIENT"),
    ADMIN("ADMIN");

    public String code;

    UserType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
