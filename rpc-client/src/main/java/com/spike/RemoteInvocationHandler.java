package com.spike;

import com.spike.entity.RpcRequest;
import com.spike.trans.RpcInetTransportor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParams(args);
        request.setTypes(method.getParameterTypes());

        // 建立传输对象传输调用信息
        RpcInetTransportor transporter = new RpcInetTransportor(host, port);
        Object sendResult = transporter.sendReq(request);// 发送完成

        //System.out.println("here is proxy...");
        //return "hello proxy :)";
        return sendResult;
    }
}
