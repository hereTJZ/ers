package com.example.ers.config;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class ResourcePathConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        // 获取jar包所在目录
        String jarPath = jarF.getParentFile().toString();

        // 访问路径(例如 localhost:8080/upload/*)
        registry.addResourceHandler("/upload/**")
                // 映射的真实服务器路径
                .addResourceLocations("file:" + jarPath + "\\upload\\");
    }
}
