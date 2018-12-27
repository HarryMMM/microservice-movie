package cn.harryai.microservicemovie.feign;

import cn.harryai.microservicemovie.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class UserFeignClientImplFallback implements UserFeignClient {
    @Override
    public User findById(Long id) {
        User user =new User();
        user.setUsername("findById");
        return user;
    }

    @Override
    public String get(Map<String, Object> params) {
        return null;
    }

    @Override
    public String post(Map<String, Object> params) {
        return null;
    }

    @Override
    public String post2(User user) {
        return null;
    }
}
