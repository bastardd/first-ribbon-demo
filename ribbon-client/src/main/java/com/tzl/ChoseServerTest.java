package com.tzl;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用负载均衡器来选择服务器
 * 可以看到输出的结果和之前TestRestClient选择服务器的逻辑是一致的
 */
public class ChoseServerTest {

    public static void main(String[] args) {
        //创建负载均衡器
        ILoadBalancer iLoadBalancer = new BaseLoadBalancer();
        //添加服务器
        List<Server> serversList = new ArrayList<Server>();
        serversList.add(new Server("localhost",8080));
        serversList.add(new Server("localhost",8081));
        iLoadBalancer.addServers(serversList);
        //进行6次服务器的选择
        for (int i = 0; i < 6; i++) {
            Server server = iLoadBalancer.chooseServer(null);
            System.out.println(server);
        }
    }

}
