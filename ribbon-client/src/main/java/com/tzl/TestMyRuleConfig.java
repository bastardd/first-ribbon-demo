package com.tzl;

import com.netflix.client.ClientException;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;
import com.tzl.rule.MyRule;

import java.net.URI;

/**
 * 通过配置设置负载的规则
 * 运行可以看到结果，6次请求都是调用的8080
 */
public class TestMyRuleConfig {

    public static void main(String[] args) throws Exception {

        ConfigurationManager.getConfigInstance().setProperty(
                "my-client.ribbon.listOfServers","localhost:8080,localhost:8081");
        //配置规则处理类
        ConfigurationManager.getConfigInstance().setProperty(
                "my-client.ribbon.NFLoadBalancerRuleClassName", MyRule.class.getName());
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
    }

}
