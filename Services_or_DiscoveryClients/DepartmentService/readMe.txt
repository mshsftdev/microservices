to use another (e.g., Employee) API through serviceRegistry:

1- create client > EmployeeClnt(interface, no beans) : @HttpExchange

2- create confi > WebClientConfig : @Bean
    to create a Bean of web client : WebClient, LoadBalancedExchangeFilterFunction
    and then, a Bean of desired Client(EmployeeClnt) : HttpServiceProxyFactory

@HttpExchange
@Bean
WebClient, LoadBalancedExchangeFilterFunction
HttpServiceProxyFactory