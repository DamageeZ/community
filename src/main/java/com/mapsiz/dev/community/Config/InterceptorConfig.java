package com.mapsiz.dev.community.Config;

import com.mapsiz.dev.community.Interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: DamageeZ
 * @Create: 06-06-2021 19:16
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //所要拦截的请求路径
    String[] addPathPatterns = {
            "/publish"
    };

    //不需要拦截的请求路径
    String[] excludePathPatterns = {
            "/","/exit","/happy"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
