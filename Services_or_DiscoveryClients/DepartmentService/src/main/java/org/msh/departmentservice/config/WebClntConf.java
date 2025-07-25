package org.msh.departmentservice.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.msh.departmentservice.client.EmployeeClnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClntConf {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    @Autowired
    public WebClntConf(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean
//    @LoadBalanced
    public WebClient.Builder webClient() {
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }


    @Bean
//    @LoadBalanced
    public WebClient employeeWebClient() {
//        return WebClient.builder()
        return webClient()
                .baseUrl("http://employee-service") // service name in Eureka
                .filter(filterFunction) // enables load-balanced discovery
                .build();
    }

    @Bean
    public EmployeeClnt employeeClnt() {
        //// ServiceProxy ~ Client
        // HttpServiceProxyFactory httpServiceProxyFactory
        //        = HttpServiceProxyFactory
        //        .builder(WebClientAdapter.forClient(employeeWebClient()))
        //        .build();

        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                  //.builder(WebClientAdapter.forClient(employeeWebClient()))
                .builderFor(WebClientAdapter.create(employeeWebClient()))
                .build();

        return httpServiceProxyFactory.createClient(EmployeeClnt.class);
    }
}