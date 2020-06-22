package com.spike.trans;

import com.spike.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RpcInetTransportor {

    private String host;
    private int port;

    public RpcInetTransportor(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newScoket() {
        try {
            Socket socket = new Socket(host, port);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 客户端发送数据到socket中
     */
    public Object sendReq(RpcRequest request) {

        Socket socket = newScoket();
        // 处理通信数据

        try (
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            // 将数据写入socket流中
            out.writeObject(request);
            out.flush(); // 将缓冲区数据刷入socket中传输

            // 从socket中获取服务端的响应数据并返回
            return in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

