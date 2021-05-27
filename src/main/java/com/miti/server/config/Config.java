package com.miti.server.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.miti.data.model")
@ComponentScan("com.miti.server.config.jwt")
@ComponentScan("com.miti.data.mapper")
@EnableJpaRepositories("com.miti.data.repository")
public class Config {

}
