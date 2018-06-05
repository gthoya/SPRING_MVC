package com.gthoya.configuration;

import com.gthoya.aspect.TimeLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfiguration {
    @Bean
    public TimeLoggingAspect timeLoggingAspect() {
        return new TimeLoggingAspect();
    }
}
