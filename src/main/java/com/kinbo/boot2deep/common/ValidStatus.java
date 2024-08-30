package com.kinbo.boot2deep.common;

public enum ValidStatus {


    VAILD(1, "有效"), INVAILD(0, "失效");
    int code;
    String msg;

    ValidStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }
}
