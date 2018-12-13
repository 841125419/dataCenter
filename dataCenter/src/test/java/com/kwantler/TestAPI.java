package com.kwantler;


public class TestAPI {

    public static void main(String[] args) {

        //new LotteryApi().getLottery();
        PureNetUtil.get("http://localhost:8080/test?ee=3");
    }
}
