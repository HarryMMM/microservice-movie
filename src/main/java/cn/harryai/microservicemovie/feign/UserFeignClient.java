package cn.harryai.microservicemovie.feign;

import cn.harryai.microservicemovie.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "microservice-user",fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {
    @GetMapping("/{id}")
    User findById(@PathVariable("id") Long id);

    @GetMapping("/get-multi-param")
    String get(@RequestParam Map<String,Object> params);

    @PostMapping("/post-multi-param")
    String post(@RequestParam Map<String,Object> params);


    @PostMapping("/post-multi-param2")
    String post2(@RequestBody User user);

}
