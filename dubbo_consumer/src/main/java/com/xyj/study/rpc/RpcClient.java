package com.xyj.study.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by banma on 2017/8/3.
 */
public class RpcClient<T> {
    //动态代理实现接口的远程调用。
    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface, final InetSocketAddress addr) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectInputStream inputStream = null;
                ObjectOutputStream outputStream = null;
                try {
                    // 创建Socket客户端，根据指定地址连接远程服务提供者
                    socket = new Socket();
                    socket.connect(addr);
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeUTF(serviceInterface.getName());
                    outputStream.writeUTF(method.getName());
                    outputStream.writeObject(method.getParameterTypes());
                    outputStream.writeObject(args);

                    inputStream = new ObjectInputStream(socket.getInputStream());
                    return inputStream.readObject();
                } finally {
                    if (socket != null) socket.close();
                    if (outputStream != null) outputStream.close();
                    if (inputStream != null) inputStream.close();
                }
            }
        });
    }
}
