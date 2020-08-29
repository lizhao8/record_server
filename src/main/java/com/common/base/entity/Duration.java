package com.common.base.entity;

public class Duration {

	static final int HOURS_PER_DAY = 24;

	static final int MINUTES_PER_HOUR = 60;

	static final int SECONDS_PER_MINUTE = 60;

	static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	java.time.Duration duration;
	private final long seconds;
	private final int nanos;

	public Duration(long millis) {
		duration = java.time.Duration.ofMillis(millis);
		this.seconds = duration.getSeconds();
		this.nanos = duration.getNano();
	}

	@Override
	public String toString() {
		if (duration == java.time.Duration.ZERO) {
			return "00:00:00";
		}

		long hour = seconds / SECONDS_PER_HOUR;
		int minute = (int) ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
		int second = (int) (seconds % SECONDS_PER_MINUTE);
		String hourString;
		String minuteString;
		String secondString;

		if (hour < 10) {
			hourString = "0" + hour;
		} else {
			hourString = Long.toString(hour);
		}
		if (minute < 10) {
			minuteString = "0" + minute;
		} else {
			minuteString = Integer.toString(minute);
		}
		if (nanos > 0) {
			second++;
		}
		if (second < 10) {
			secondString = "0" + second;
		} else {
			secondString = Integer.toString(second);
		}
		return (hourString + ":" + minuteString + ":" + secondString);

	}

	public static Duration ofMillis(long millis) {
		return new Duration(millis);
	}

	public static Duration ofMillis(String time) {
		if(time==null) {
			return null;
		}
		String[] times = time.split(":");
		long millis = 0;
		long hour = Long.parseLong(times[0]);
		int minute = Integer.parseInt(times[1]);
		int second = Integer.parseInt(times[2]);

		millis += hour * SECONDS_PER_HOUR;
		millis += minute * SECONDS_PER_MINUTE;
		millis += second;
		millis *= 1000L;
		return new Duration(millis);
	}
}
