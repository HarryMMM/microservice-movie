package cn.harryai.microservicemovie.feign;

import cn.harryai.microservicemovie.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
  private static final   Logger LOGGER =LoggerFactory.getLogger(UserFeignClientFallbackFactory.class);
    @Override
    public UserFeignClient create(final Throwable cause) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                User user =new User();
                user.setUsername("findById");
                LOGGER.info("fallback; reason was:",cause);
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
        };
    }
}
