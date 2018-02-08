package com.fenast.app.ibextube.config.oauth2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by taddesee on 2/8/2018.
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.fenast.app.ibextube" })
public class ResourceServerWebConfig implements WebMvcConfigurer {
}
