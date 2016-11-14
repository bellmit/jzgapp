package com.jzg.jzgoto.phone.event;


public class TimeChooseEvent extends BaseEvent {
    public int year;
	public int month;
    public int sourceFrom = 0;

    public static TimeChooseEvent build(int year, int month, int sourceFrom) {
        TimeChooseEvent event = new TimeChooseEvent();
        event.year = year;
		event.month = month;
        event.sourceFrom = sourceFrom;
        return event;
    }
}
