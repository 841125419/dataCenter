package com.kwantler.interchange.wsdl.client.hello;


import com.kwantler.interchange.wsdl.service.invoke.hello.JwsServiceHello;
import com.kwantler.interchange.wsdl.service.invoke.hello.JwsServiceHelloService;
import com.kwantler.interchange.wsdl.service.invoke.hello.User;

import java.util.Arrays;
import java.util.List;

public class JwsClientHello {
    public static void main(String[] args) {
        //调用webservice

        JwsServiceHello hello=new JwsServiceHelloService().getJwsServiceHelloPort();
        String name=hello.getValue("panchengming");
        List<User> list = hello.getUsers("chenmingjian");
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(name);

    }
}
