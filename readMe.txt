run openzipkin(from docker hub) on docker

@EnableEurekaServer
@EnableDiscoveryClient

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

route configurations are better to be defined in .YAML file to work out:
spring:
  cloud:
    gateway:
      routes:
        - id: employee-service
          uri: lb://employee-service
          predicates:
            - Path=/employee/**
        - id: department-service
          uri: lb://department-service
          predicates:
            - Path=/department/**

