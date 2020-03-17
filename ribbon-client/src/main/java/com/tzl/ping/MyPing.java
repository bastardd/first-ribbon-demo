package com.tzl.ping;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * 自定义ping
 * 可以通过NFLoadBalancerPingClassName的配置应用
 */
public class MyPing implements IPing{

    public boolean isAlive(Server server) {
        System.out.println("自定义ping------" + server.getHostPort());
        return true;
    }
}
