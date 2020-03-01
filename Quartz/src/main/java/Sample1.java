import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class Sample1 implements Job {

	public static void main(String args[]) throws SchedulerException {

	
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler sched = schedFact.getScheduler();
		sched.start();

		JobDetail job = JobBuilder.newJob()
				.ofType(Sample1.class)
				.withIdentity("myJob", "group1")
			    .usingJobData("jobSays", "Hello World!")
			    .usingJobData("myFloatValue", 3.141f)
				.build();
		Trigger t2  = TriggerBuilder.newTrigger()
			    .withIdentity("trigger3", "group1")
			    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
			    .build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10)).build();

		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, t2);
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		/*JobKey key = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	    String jobSays = dataMap.getString("jobSays");
	    float myFloatValue = dataMap.getFloat("myFloatValue");
	    System.out.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
		*/
		System.out.println("Job is running:" + new Date());
			
	}
}
