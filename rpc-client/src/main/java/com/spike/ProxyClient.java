package com.spike;

import java.lang.reflect.Proxy;

public class ProxyClient {

    /**
     * 利用动态代理
     */
    public <T> T clientProxy(final Class<T> interfaceClazz, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(),
            new Class[] {interfaceClazz},
            new RemoteInvocationHandler(host, port));
    }
}
