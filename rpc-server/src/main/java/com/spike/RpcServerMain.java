package com.spike;

import com.spike.provider.RpcProxy;
import com.spike.provider.SayHelloServiceImpl;

/**
 * 将自身服务发布到本机对应的端口上
 */
public class RpcServerMain {
    public static void main(String[] args) {

        SayHelloServiceImpl sayHelloService = new SayHelloServiceImpl(); // 实例化
        System.out.println("RPC服务端已启动...");
        RpcProxy rpcProxy = new RpcProxy();
        rpcProxy.export(sayHelloService, 5555);
    }
}
