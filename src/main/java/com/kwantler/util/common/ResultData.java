package com.kwantler.util.common;

public class ResultData<T> {
    private String result;
    private T data;

    public ResultData(String result, T data) {
        this.result = result;
        this.data = data;
    }

    public ResultData(T data) {
        this.data = data;
        this.result = "success";
    }
}
