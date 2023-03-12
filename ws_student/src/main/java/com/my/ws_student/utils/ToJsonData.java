package com.my.ws_student.utils;

public class ToJsonData<T,Y> {
    T data=null;
    Y message =null;

    public ToJsonData(T data, Y message) {
        this.data = data;
        this.message = message;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Y getMessage() {
        return message;
    }

    public void setMessage(Y message) {
        this.message = message;
    }
}
