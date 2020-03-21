package com.spike.provider;

import com.spike.api.ISayHelloService;

public class SayHelloServiceImpl implements ISayHelloService {
    public String sayHello(String msg) {
        System.out.println("request param: "+msg);
        //return "Fine thank you, and you?";
        return "I love China! China's CRAZY!";
    }
}
