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

CHANGES made here to use feign client instead of web client:

1- add dependency of openfeign in the pom of DepartmentService

2- add below annotation to the DepartServiceApplication:
    @EnableFeignClients

3- change annotations in the interface of employee service
    from:
        @HttpExchange
        @GetExchange
    to:
        @FeignClient(name="EMPLOYEE-SERVICE")
        @GetMapping

4- there is no need to use WebClntConf (Web Client Configuration) to create a bean of the interface (EmployeeClnt)

http://localhost:8081/employee/add
    {
        "id":"1"
        , "name":"e1"
        , "departmentId":"1"
        , "age":"11"
        , "position":"junior"
    }


http://localhost:8082/department/add
    {
        "id": 1,
        "name": "IT",
        "employees": []
    }

http://localhost:8081/employee/get/all
http://localhost:8081/department/get/allWithEmployees

http://localhost:8060/department/get/allWithEmployees
http://localhost:8060/department/get/allWithEmployeesUsingFeignClient

