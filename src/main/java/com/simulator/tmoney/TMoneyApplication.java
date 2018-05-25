package com.simulator.tmoney;

//import com.simulator.tmoney.quartz.ConsultarCriptomoeda;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TMoneyApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(TMoneyApplication.class, args);

//		JobDetail job = JobBuilder.newJob(ConsultarCriptomoeda.class)
//				.withIdentity("dummyJobName", "group1").build();
//
//		// Trigger the job to run on the next round minute
//		Trigger trigger = TriggerBuilder
//				.newTrigger()
//				.withIdentity("dummyTriggerName", "group1")
//				.withSchedule(
//						SimpleScheduleBuilder.simpleSchedule()
//								.withIntervalInSeconds(5).repeatForever())
//				.build();
//
//		// schedule it
//		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//		scheduler.start();
//		scheduler.scheduleJob(job, trigger);
	}
}
