import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.calendar.HolidayCalendar;

public class Sample2 {

	
	public static void main(String args[]) throws SchedulerException {

		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler sched = schedFact.getScheduler();
		sched.start();
	
		HolidayCalendar cal = new HolidayCalendar();
		cal.addExcludedDate( new Date() );
		sched.addCalendar("myHolidays", cal, false, true);
		
		Trigger t2  = TriggerBuilder.newTrigger()
			    .withIdentity("trigger3", "group1")
			    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
			    .modifiedByCalendar("myHolidays") // but not on holidays
			    .build();
		
		JobDetail job = JobBuilder.newJob()
				.ofType(Sample1.class)
				.withIdentity("myJob", "group1")
				.build();
		
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, t2);
	}
	
}
