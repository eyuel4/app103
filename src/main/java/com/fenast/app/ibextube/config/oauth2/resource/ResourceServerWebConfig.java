package com.fenast.app.ibextube.config.oauth2.resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan({"com.fenast.app.ibextube"})
public class ResourceServerWebConfig implements WebMvcConfigurer {
}
