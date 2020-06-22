package com.spike;

import com.spike.api.ISayHelloService;

public class RpcClientMain {

    public static void main(String[] args) {
        String msg = "hello cardiB how are you!";
        ProxyClient proxyClient = new ProxyClient();
        ISayHelloService sayHelloService = null;
        sayHelloService = proxyClient.clientProxy(ISayHelloService.class, "localhost", 5555);
        System.out.println(msg);
        System.out.println(sayHelloService.sayHello(msg));
    }
}
