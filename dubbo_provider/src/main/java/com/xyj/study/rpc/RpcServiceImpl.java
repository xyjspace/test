package com.xyj.study.rpc;

/**
 * Created by banma on 2017/8/3.
 */
public class RpcServiceImpl implements RpcService {

    @Override
    public String hello() {
        return "hello  原生的 RPC调用";
    }

}
