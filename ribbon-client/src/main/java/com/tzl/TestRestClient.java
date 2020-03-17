package com.tzl;


import com.netflix.client.ClientFactory;
import com.netflix.client.IClient;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

/**
 * 使用ribbon客户端发送请求，
 * 从输出结果可以看到。请求在8080和8081之间轮流切换，从而实现了负载均衡
 */
public class TestRestClient {


    public static void main(String[] args) {
        try {
            // 写入服务列表
            ConfigurationManager.getConfigInstance().setProperty("my-client.ribbon.listOfServers", "localhost:8080,localhost:8081");
            // 输出服务列表
            System.out.println("服务列表：" + ConfigurationManager.getConfigInstance().getProperty("my-client.ribbon.listOfServers"));
            // 获取客户端（如果获取不到，可通过getNamedClient方法自动创建）
            RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
            // 创建request对象
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("/person/1")).build();// 写入将要访问的接口
            // 多次访问测试
            for (int i = 0; i < 10; i++) {
                // 创建response对象
                HttpResponse response = client.executeWithLoadBalancer(request);
                // 接收请求结果
                String json = response.getEntity(String.class);
                // 打印结果
                System.out.println(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
