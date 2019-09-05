package life.majiang.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionIntercept sessionIntercept;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(sessionIntercept).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*")
                .addResourceLocations( "classpath:/META-INF/resources/",
                        "classpath:/resources/",
                        "classpath:/static/","classpath:/public/");
    }
}
