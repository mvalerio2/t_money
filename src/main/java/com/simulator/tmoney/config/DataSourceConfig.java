package com.simulator.tmoney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value= {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = {"com.simulator.tmoney.model","com.simulator.tmoney"})
@EntityScan(basePackages = {"com.simulator.tmoney.model","com.simulator.tmoney"})
@ComponentScan(basePackages = {"com.simulator.tmoney.model","com.simulator.tmoney"})
public class DataSourceConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource datasource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }
}