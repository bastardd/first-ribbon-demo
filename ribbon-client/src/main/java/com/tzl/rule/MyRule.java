package com.tzl.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载规则
 */
public class MyRule implements IRule{

    ILoadBalancer iLoadBalancer;

    public MyRule(){}

    public MyRule(ILoadBalancer iLoadBalancer){
        this.iLoadBalancer = iLoadBalancer;
    }

    public Server choose(Object o) {
        List<Server> list = iLoadBalancer.getAllServers();
        //定义规则是：只返回第一个server对象
        return list.get(0);
    }

    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    public ILoadBalancer getLoadBalancer() {
        return this.iLoadBalancer;
    }
}
