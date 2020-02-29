package com.winston.vo;

import java.io.Serializable;

public class Response implements Serializable {

    private boolean success;
    private Object obj;

    public Response(boolean success, Object obj) {
        this.success = success;
        this.obj = obj;
    }

    public  static  Response  setSuccess(Object obj){

        return new Response(true,obj);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
