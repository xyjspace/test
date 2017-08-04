package com.xyj.study.rpc;

import java.net.InetSocketAddress;

/**
 * Created by banma on 2017/8/3.
 */
public class RpcTest {

    public static void main(String[] args) {
        RpcService service = RpcClient.getRemoteProxyObj(RpcService.class, new InetSocketAddress("localhost", 9994));
        System.out.println(service.hello());
    }
}
