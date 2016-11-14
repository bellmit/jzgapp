package com.jzg.jzgoto.phone.event;


public class LogoutEvent extends BaseEvent {
	public boolean isSuccess;
	public static LogoutEvent build(boolean isSuccess) {
        LogoutEvent event = new LogoutEvent();
        event.isSuccess = isSuccess;
        return event;
    }
}
