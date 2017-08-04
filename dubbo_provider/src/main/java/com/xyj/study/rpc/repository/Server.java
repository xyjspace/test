package com.xyj.study.rpc.repository;

/**
 * Created by banma on 2017/8/3.
 */
public interface Server {

    void start() throws Exception;

    void register(Class serviceInterface, Class impl);

    void stop();

    boolean isRunning();

    int getPort();

}
