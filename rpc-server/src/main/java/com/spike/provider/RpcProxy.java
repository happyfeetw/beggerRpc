package com.spike.provider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过阻塞IO的方式
 */
public class RpcProxy {

    /**
     * 暴露服务
     * @param service
     * @param port
     */
    public void export(Object service, int port) {

        // 暂且用一个连接池对阻塞IO作初步优化
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (
                ServerSocket ss = new ServerSocket(port)
        ) {
            // 监听本地port

            /**
             * 阻塞IO模式下
             * 一次while循环只能处理一个连接
             */
            while (true) {
                Socket socket = ss.accept();    //阻塞io，等待连接进入
                //socket.getOutputStream(); // 接收服务端(本机)的响应数据
                //socket.getInputStream(); // 接收客户端传入的数据
                // 每接收到一个连接就交给线程池分配线程处理
                // 将流的处理放到ProcessorHandler的run()中进行
                executorService.submit(new ProcessorHandler(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
