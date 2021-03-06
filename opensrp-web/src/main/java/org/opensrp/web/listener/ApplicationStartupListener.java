package org.opensrp.web.listener;

import java.util.concurrent.TimeUnit;

import org.opensrp.connector.openmrs.constants.OpenmrsConstants;
import org.opensrp.register.mcare.OpenSRPScheduleConstants;
import org.opensrp.scheduler.RepeatingSchedule;
import org.opensrp.scheduler.TaskSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {
	
	public static final String APPLICATION_ID = "org.springframework.web.context.WebApplicationContext:/opensrp";
	
	private TaskSchedulerService scheduler;
	
	private RepeatingSchedule formSchedule;
	
	private RepeatingSchedule anmReportScheduler;
	
	private RepeatingSchedule mctsReportScheduler;
	
	private RepeatingSchedule openmrsScheduleSyncerScheduler;
	
	private RepeatingSchedule atomfeedSyncClient;
	
	private RepeatingSchedule atomfeedSyncEvent;
	
	@Autowired
	public ApplicationStartupListener(TaskSchedulerService scheduler,
	    @Value("#{opensrp['form.poll.time.interval']}") int formPollInterval,
	    @Value("#{opensrp['mcts.poll.time.interval.in.minutes']}") int mctsPollIntervalInHours,
	    @Value("#{opensrp['openmrs.scheduletracker.syncer.interval-min']}") int openmrsSchSyncerMin) {
		this.scheduler = scheduler;
		formSchedule = new RepeatingSchedule(OpenSRPScheduleConstants.FORM_SCHEDULE_SUBJECT, 0, TimeUnit.MINUTES,
		        formPollInterval, TimeUnit.MINUTES);
		// anmReportScheduler = new RepeatingSchedule(OpenSRPScheduleConstants.ANM_REPORT_SCHEDULE_SUBJECT, 10, TimeUnit.MINUTES, 6, TimeUnit.HOURS);
		// mctsReportScheduler = new RepeatingSchedule(OpenSRPScheduleConstants.MCTS_REPORT_SCHEDULE_SUBJECT, 10, TimeUnit.MINUTES, mctsPollIntervalInHours, TimeUnit.HOURS);
		//openmrsScheduleSyncerScheduler = new RepeatingSchedule(OpenmrsConstants.SCHEDULER_TRACKER_SYNCER_SUBJECT, 0, TimeUnit.MINUTES, 1, TimeUnit.MINUTES);
		atomfeedSyncClient = new RepeatingSchedule(OpenmrsConstants.SCHEDULER_OPENMRS_ATOMFEED_SYNCER_SUBJECT_CLIENT, 0,
		        TimeUnit.MINUTES, formPollInterval, TimeUnit.MINUTES);
		atomfeedSyncEvent = new RepeatingSchedule(OpenmrsConstants.SCHEDULER_OPENMRS_ATOMFEED_SYNCER_SUBJECT_EVENT, 0,
		        TimeUnit.MINUTES, formPollInterval, TimeUnit.MINUTES);
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (APPLICATION_ID.equals(contextRefreshedEvent.getApplicationContext().getId())) {
			scheduler.startJob(formSchedule);
			//scheduler.startJob(anmReportScheduler);
			// scheduler.startJob(mctsReportScheduler);
			//scheduler.startJob(openmrsScheduleSyncerScheduler);
			scheduler.startJob(atomfeedSyncClient);
			scheduler.startJob(atomfeedSyncEvent);
			System.out.println("STARTED ALL SCHEDULES");
		}
	}
}
