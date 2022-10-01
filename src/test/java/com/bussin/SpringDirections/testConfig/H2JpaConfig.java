package com.bussin.SpringDirections.testConfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.bussin.SpringDirections" +
        ".repositories")
@TestPropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan("com.bussin.SpringDirections")
public class H2JpaConfig {

}
