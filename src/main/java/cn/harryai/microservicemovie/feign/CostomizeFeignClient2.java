//package cn.harryai.microservicemovie.feign;
//
//import cn.harryai.microservicemovie.config.FeignConfiguration;
//import cn.harryai.microservicemovie.entity.User;
//import feign.Param;
//import feign.RequestLine;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
///**
// * 测试feign原生注解
// */
//@FeignClient(name = "microservice-user",configuration = FeignConfiguration.class)
//public interface UserFeignClient2 {
////    @GetMapping("/{id}")
//    @RequestLine("GET /{id}")
//    User findByIdWith(@Param("id") Long id);
//
//}
