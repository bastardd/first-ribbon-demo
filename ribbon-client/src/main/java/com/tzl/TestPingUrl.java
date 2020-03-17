package com.tzl;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * ribbon的Ping机制
 * 每隔一段时间，会去ping服务器，判断服务器是否存活
 * 该工作由IPing接口的实现类负责
 */
public class TestPingUrl {

    public static void main(String[] args) throws InterruptedException {
        BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
        List<Server> serverList = new ArrayList<Server>();
        serverList.add(new Server("localhost",8080));
        serverList.add(new Server("localhost",8888));
        baseLoadBalancer.addServers(serverList);
        //设置IPing实现类
        baseLoadBalancer.setPing(new PingUrl());
        //设置PIng时间间隔是2s
        baseLoadBalancer.setPingInterval(2);
        Thread.sleep(6000);
        for (Server s : serverList){
            System.out.println(s.getHostPort() + "状态：" + s.isAlive());
        }
    }
}
