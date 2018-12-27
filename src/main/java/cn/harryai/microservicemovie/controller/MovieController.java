package cn.harryai.microservicemovie.controller;

import cn.harryai.microservicemovie.entity.User;
import cn.harryai.microservicemovie.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private UserFeignClient userFeignClient;
    //    @Autowired
//    private UserFeignClient2 userFeignClient2;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate template;
//    @GetMapping("/user/costomize/{id}")
//    public User findById2(@PathVariable Long id) {
//        User findOne = this.userFeignClient2.findByIdWith(id);
//        LOGGER.info("user is {}", findOne);
//        return findOne;
//    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = this.userFeignClient.findById(id);
        LOGGER.info("user is {}", findOne);
        return findOne;
    }

    @GetMapping("/log-user-instance")
    public ServiceInstance logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-user");
        LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
        return serviceInstance;
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("microservice-user");
    }

    @GetMapping("user/get-multi-param")
    public String get(String firstParam, Long secondParam) {
        Map<String, Object> param = new HashMap<>();
        param.put("firstParam", firstParam);
        param.put("secondParam", secondParam);
        return userFeignClient.get(param);
    }

    @PostMapping("user/post-multi-param")
    public String post(String firstParam, Long secondParam, Date thirdParam) {
        Map<String, Object> param = new HashMap<>();
        param.put("firstParam", firstParam);
        param.put("secondParam", secondParam);
        param.put("thirdParam", thirdParam);
        return userFeignClient.post(param);
    }

    @PostMapping("user/post-multi-param2")
    public String post(@RequestBody User user) {

        return userFeignClient.post2(user);
    }

    @GetMapping("/user/template/{id}")
    @HystrixCommand(fallbackMethod = "findByIdWithTemplateFallback",ignoreExceptions = ArithmeticException.class)
    public User findByIdWithTemplate(@PathVariable Long id) {
        User findOne = this.template.getForObject("http://microservice-user/" + id, User.class);
        LOGGER.info("user is {}", findOne);
        return findOne;
    }


    public User findByIdWithTemplateFallback( Long id,Throwable throwable) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        LOGGER.error("findByIdWithTemplate fallback ! {}",throwable);
        return user;
    }
}
