package redis;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by letingoo on 2016/10/31.
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

//    @Bean
//    public KeyGenerator

}
