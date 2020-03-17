package com.tzl;


import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import com.tzl.rule.MyRule;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试自定义的负载规则
 */
public class TestMyRule {

    public static void main(String[] args) {
        BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
        baseLoadBalancer.setRule(new MyRule(baseLoadBalancer));
        //
        //添加服务器
        List<Server> serversList = new ArrayList<Server>();
        serversList.add(new Server("localhost",8080));
        serversList.add(new Server("localhost",8081));
        baseLoadBalancer.addServers(serversList);
        //进行6次服务器的选择
        for (int i = 0; i < 6; i++) {
            Server server = baseLoadBalancer.chooseServer(null);
            System.out.println(server);
        }
    }
}
