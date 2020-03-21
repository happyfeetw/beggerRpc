package com.spike.provider;

import com.spike.entity.RpcRequest;

import javax.annotation.processing.Processor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {

    Socket socket;
    Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    // 处理流
    public void run() {
        // 在try中声明的流资源可以自动关闭
        // socket通信双工协议
        try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); // 接收客户端发来的需要调用的方法的相关信息
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); // 接收自身调用目标方法得到的结果
        ) {
            RpcRequest rpcRequest = (RpcRequest) in.readObject(); // 拿到客户端发来的数据
            System.out.println("收到客户端的请求："+ rpcRequest.toString());
            Object result = invoke(rpcRequest); // 根据数据调用对应方法并获得调用结果
            out.writeObject(result); // 将调用结果写入输出流中
            out.flush(); // 将输出流刷入socket传输到客户端
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest request) {

        try {
            // 反射获取远程调用的目标类对象
            Class<?> targetClass = Class.forName(request.getClassName());
            // 获取远程调用的目标方法
            Method method = targetClass.getMethod(request.getMethodName(), request.getTypes());
            // 通过反射调用目标方法并返回调用结果
            return method.invoke(service, request.getParams());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }
}
