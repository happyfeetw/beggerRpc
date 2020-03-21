package com.spike.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 远程调用请求对象
 * 包含了调用服务端对象所需的各种信息
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -5782353788305635676L;

    private String className; // 远程调用的目标类名
    private String methodName; // 远程调用的目标方法名
    private Object[] params; // 远程调用的目标方法需要的参数
    private Class[] types; // 目标方法所需参数的类型

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
