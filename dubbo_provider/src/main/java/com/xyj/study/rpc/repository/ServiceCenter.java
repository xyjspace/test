package com.xyj.study.rpc.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by banma on 2017/8/3.
 */
@Slf4j
public class ServiceCenter implements Server {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static final HashMap<String, Class> serviceRegistory = new HashMap<String, Class>();

    private static boolean isRunning = false;

    private static int port;

    public ServiceCenter(int port) {
        this.port = port;
    }

    @Override
    public void start() throws Exception {
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(port));

        try {
            while (true) {
                executorService.execute(new ServiceTask(socket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    @Override
    public void register(Class serviceInterface, Class impl) {
        serviceRegistory.put(serviceInterface.getName(), impl);
    }

    @Override
    public void stop() {
        isRunning = false;
        executorService.shutdown();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }
}
