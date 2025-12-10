//package com.wuyunbin.quadra.admin.adapter.in.web.config;
//
//import com.wuyunbin.quadra.admin.adapter.in.web.intercepter.AuthorizationInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MvcConfig implements WebMvcConfigurer {
//
//    private final AuthorizationInterceptor authorizationInterceptor;
//
//    public MvcConfig(AuthorizationInterceptor authorizationInterceptor) {
//        this.authorizationInterceptor = authorizationInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/**/login",
//                        "/**/logout",
//                        "/doc.html",
//                        "/favicon.ico",
//                        "/webjars/**",
//                        "/error",
//                        "/v3/**"
//                );
//    }
//}
