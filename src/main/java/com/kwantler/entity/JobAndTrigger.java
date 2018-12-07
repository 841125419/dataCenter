package com.kwantler.entity;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JobAndTrigger {
	private String JOB_NAME;
	private String JOB_GROUP;
	private String JOB_CLASS_NAME;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private BigInteger REPEAT_INTERVAL;
	private BigInteger TIMES_TRIGGERED;
	private String CRON_EXPRESSION;
	private String TIME_ZONE_ID;
	private String TRIGGER_STATE;
	/**
	 * 下次执行时间
	 */
	private Long NEXT_FIRE_TIME;
	/**
	 * 上次执行时间
	 */
	private Long PREV_FIRE_TIME;
	/**
	 * 开始启动时间
	 */
	private Long START_TIME;
	/**
	 * 结束启动时间
	 */
	private Long END_TIME;

	public String getNEXT_FIRE_TIME() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(NEXT_FIRE_TIME));
	}

	public void setNEXT_FIRE_TIME(Long NEXT_FIRE_TIME) {
		this.NEXT_FIRE_TIME = NEXT_FIRE_TIME;
	}

	public String getPREV_FIRE_TIME() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(PREV_FIRE_TIME));
	}

	public void setPREV_FIRE_TIME(Long PREV_FIRE_TIME) {
		this.PREV_FIRE_TIME = PREV_FIRE_TIME;
	}

	public String getSTART_TIME() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(START_TIME));
	}

	public void setSTART_TIME(Long START_TIME) {
		this.START_TIME = START_TIME;
	}

	public String getEND_TIME() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(END_TIME));
	}

	public void setEND_TIME(Long END_TIME) {
		this.END_TIME = END_TIME;
	}

	public String getJOB_NAME() {
		return JOB_NAME;
	}
	public void setJOB_NAME(String jOB_NAME) {
		JOB_NAME = jOB_NAME;
	}
	public String getJOB_GROUP() {
		return JOB_GROUP;
	}
	public void setJOB_GROUP(String jOB_GROUP) {
		JOB_GROUP = jOB_GROUP;
	}
	public String getJOB_CLASS_NAME() {
		return JOB_CLASS_NAME;
	}
	public void setJOB_CLASS_NAME(String jOB_CLASS_NAME) {
		JOB_CLASS_NAME = jOB_CLASS_NAME;
	}
	public String getTRIGGER_NAME() {
		return TRIGGER_NAME;
	}
	public void setTRIGGER_NAME(String tRIGGER_NAME) {
		TRIGGER_NAME = tRIGGER_NAME;
	}
	public String getTRIGGER_GROUP() {
		return TRIGGER_GROUP;
	}
	public void setTRIGGER_GROUP(String tRIGGER_GROUP) {
		TRIGGER_GROUP = tRIGGER_GROUP;
	}
	public BigInteger getREPEAT_INTERVAL() {
		return REPEAT_INTERVAL;
	}
	public void setREPEAT_INTERVAL(BigInteger rEPEAT_INTERVAL) {
		REPEAT_INTERVAL = rEPEAT_INTERVAL;
	}
	public BigInteger getTIMES_TRIGGERED() {
		return TIMES_TRIGGERED;
	}
	public void setTIMES_TRIGGERED(BigInteger tIMES_TRIGGERED) {
		TIMES_TRIGGERED = tIMES_TRIGGERED;
	}
	public String getCRON_EXPRESSION() {
		return CRON_EXPRESSION;
	}
	public void setCRON_EXPRESSION(String cRON_EXPRESSION) {
		CRON_EXPRESSION = cRON_EXPRESSION;
	}
	public String getTIME_ZONE_ID() {
		return TIME_ZONE_ID;
	}
	public void setTIME_ZONE_ID(String tIME_ZONE_ID) {
		TIME_ZONE_ID = tIME_ZONE_ID;
	}

	public String getTRIGGER_STATE() {
		return TRIGGER_STATE;
	}

	public void setTRIGGER_STATE(String TRIGGER_STATE) {
		this.TRIGGER_STATE = TRIGGER_STATE;
	}
}
