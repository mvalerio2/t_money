package com.simulator.tmoney.config;

import com.simulator.tmoney.jobs.ConsultarCriptomoeda;
import com.simulator.tmoney.jobs.PingHeroku;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

@Configuration
public class QuartzConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext context;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(context);

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);


        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(consultarCriptomoedaJobTrigger(), pingHerokuJobTrigger());

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    private JobDetail consultarCriptomoedaJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();

        factoryBean.setJobClass(ConsultarCriptomoeda.class);
        factoryBean.setDurability(true);
        factoryBean.setApplicationContext(context);
        factoryBean.setName("consultar-criptomoeda-job");

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    private Trigger consultarCriptomoedaJobTrigger() {

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(consultarCriptomoedaJobDetail());
        factoryBean.setName("consultar-criptomoeda-trigger");
        factoryBean.setStartTime(new Date());
        factoryBean.setCronExpression("0 0 * ? * * *");
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CronTrigger object = factoryBean.getObject();

        return object;
    }

    private JobDetail pingHerokuJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();

        factoryBean.setJobClass(PingHeroku.class);
        factoryBean.setDurability(true);
        factoryBean.setApplicationContext(context);
        factoryBean.setName("ping-heroku-job");

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    private Trigger pingHerokuJobTrigger() {

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(pingHerokuJobDetail());
        factoryBean.setName("ping-heroku-trigger");
        factoryBean.setStartTime(new Date());
        factoryBean.setCronExpression("0 0/10 * ? * * *");
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CronTrigger object = factoryBean.getObject();

        return object;
    }
}
