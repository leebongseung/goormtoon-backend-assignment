package goorm.tricount.config;

import goorm.tricount.annotation.LoginMemberArguementResolver;
import goorm.tricount.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheck())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/signup", "/login", "/css/**", "/*.ico", "/error", "/h2-console/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArguementResolver());
    }
}
