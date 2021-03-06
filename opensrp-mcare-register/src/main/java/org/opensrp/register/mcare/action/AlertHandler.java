package org.opensrp.register.mcare.action;

import static org.motechproject.scheduletracking.api.domain.WindowName.due;
import static org.motechproject.scheduletracking.api.domain.WindowName.earliest;
import static org.motechproject.scheduletracking.api.domain.WindowName.late;
import static org.motechproject.scheduletracking.api.domain.WindowName.max;
import static org.opensrp.register.mcare.OpenSRPScheduleConstants.ChildScheduleConstants.*;
import static org.opensrp.register.mcare.OpenSRPScheduleConstants.ECSchedulesConstants.*;
import static org.opensrp.register.mcare.OpenSRPScheduleConstants.MotherScheduleConstants.*;
import static org.opensrp.register.mcare.OpenSRPScheduleConstants.HHSchedulesConstants.HH_SCHEDULE_CENSUS;
import static org.opensrp.register.mcare.OpenSRPScheduleConstants.MemberScheduleConstants.*;
import static org.opensrp.scheduler.Matcher.any;
import static org.opensrp.scheduler.Matcher.anyOf;
import static org.opensrp.scheduler.Matcher.eq;

import org.opensrp.scheduler.HookedEvent;
import org.opensrp.scheduler.Matcher;
import org.opensrp.scheduler.TaskSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AlertHandler {
	@Autowired
	public AlertHandler(TaskSchedulerService scheduler,
			@Qualifier("AlertCreationAction") HookedEvent alertCreation) {
	
		scheduler.addHookedEvent(
				hhSchedules(),
				any(),
				anyOf(earliest.toString(), due.toString(), late.toString(),
						max.toString()), alertCreation).addExtraData(
				"beneficiaryType", "household");

		scheduler.addHookedEvent(
				membersSchedules(),
				any(),
				anyOf(earliest.toString(), due.toString(), late.toString(),
						max.toString()), alertCreation).addExtraData(
				"beneficiaryType", "members");
		
		scheduler.addHookedEvent(
				motherSchedules(),
				any(),
				anyOf(earliest.toString(), due.toString(), late.toString(),
						max.toString()), alertCreation).addExtraData(
				"beneficiaryType", "mother");
		
		scheduler.addHookedEvent(
				childSchedules(),
				any(),
				anyOf(earliest.toString(), due.toString(), late.toString(),
						max.toString()), alertCreation).addExtraData(
				"beneficiaryType", "child");

		scheduler.toReportaAction(alertCreation);		

	}

	private Matcher childSchedules() {
		return anyOf(CHILD_SCHEDULE_BCG,SCHEDULE_ENCC,

		CHILD_SCHEDULE_DPT_BOOSTER1, CHILD_SCHEDULE_DPT_BOOSTER2,

		CHILD_SCHEDULE_MEASLES, CHILD_SCHEDULE_MEASLES_BOOSTER,

		CHILD_SCHEDULE_OPV_0_AND_1, CHILD_SCHEDULE_OPV_2, CHILD_SCHEDULE_OPV_3,
				CHILD_SCHEDULE_OPV_BOOSTER,

				CHILD_SCHEDULE_PENTAVALENT_1, CHILD_SCHEDULE_PENTAVALENT_2,
				CHILD_SCHEDULE_PENTAVALENT_3);
	}

	private Matcher motherSchedules() {
		return anyOf(SCHEDULE_ANC, SCHEDULE_BNF, SCHEDULE_PNC, SCHEDULE_TT_1, SCHEDULE_TT_2,
				SCHEDULE_IFA_1, SCHEDULE_IFA_2, SCHEDULE_IFA_3, SCHEDULE_LAB,
				SCHEDULE_EDD, SCHEDULE_HB_TEST_1, SCHEDULE_HB_TEST_2,
				SCHEDULE_HB_FOLLOWUP_TEST, SCHEDULE_DELIVERY_PLAN);
	}

	private Matcher ecSchedules() {
		return anyOf(EC_SCHEDULE_DMPA_INJECTABLE_REFILL,
				EC_SCHEDULE_OCP_REFILL, EC_SCHEDULE_CONDOM_REFILL,
				EC_SCHEDULE_FEMALE_STERILIZATION_FOLLOWUP,
				EC_SCHEDULE_MALE_STERILIZATION_FOLLOWUP,
				EC_SCHEDULE_IUD_FOLLOWUP, EC_SCHEDULE_FP_FOLLOWUP,
				EC_SCHEDULE_FP_REFERRAL_FOLLOWUP_MILESTONE);
	}

	private Matcher hhSchedules() {
		return anyOf(HH_SCHEDULE_CENSUS);

	}
	private Matcher membersSchedules() {
		return anyOf(SCHEDULE_Woman_BNF, IMD_SCHEDULE_Woman_BNF, SCHEDULE_Woman_Measles, 
				SCHEDULE_Woman_1, SCHEDULE_Woman_2, SCHEDULE_Woman_3, 
				SCHEDULE_Woman_4, SCHEDULE_Woman_5, child_vaccination_bcg, 
				IMD_child_bcg, child_vaccination_ipv, child_vaccination_measles1, 
				child_vaccination_measles2, child_vaccination_opv0, IMD_child_opv0, 
				child_vaccination_opv1, child_vaccination_opv2, child_vaccination_opv3, 
				child_vaccination_pcv1, child_vaccination_pcv2, child_vaccination_pcv3, 
				child_vaccination_penta1, child_vaccination_penta2, child_vaccination_penta3);
	}

}
