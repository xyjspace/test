package com.xyj.study.rpc.repository;

import com.xyj.study.rpc.RpcService;
import com.xyj.study.rpc.RpcServiceImpl;

/**
 * Created by banma on 2017/8/3.
 */
public class RPCtest {
    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(9994);
                    serviceServer.register(RpcService.class, RpcServiceImpl.class);
                    serviceServer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
