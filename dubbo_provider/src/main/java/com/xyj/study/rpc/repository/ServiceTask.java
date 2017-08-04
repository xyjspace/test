package com.xyj.study.rpc.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by banma on 2017/8/3.
 */
@Slf4j
public class ServiceTask implements Runnable {

    Socket client = null;

    public ServiceTask(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        // 将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果

        try {
            inputStream = new ObjectInputStream(client.getInputStream());
            String serviceName = inputStream.readUTF();
            log.info("serviceName: {}", serviceName);
            String methodName = inputStream.readUTF();
            log.info("methodName: {}", methodName);
            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
            log.info("parameterTypes:{}", parameterTypes);
            Object[] arguments = (Object[]) inputStream.readObject();
            log.info("parameterTypes:{}", arguments);
            Class serviceClass = ServiceCenter.serviceRegistory.get(serviceName);
            if (serviceClass == null) {
                log.warn("class not found: {}", serviceName);
                throw new RuntimeException(serviceName + "not found");
            }
            Method method = serviceClass.getMethod(methodName, parameterTypes);
            Object result = method.invoke(serviceClass.newInstance(), arguments);

            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
