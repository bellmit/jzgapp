package com.jzg.jzgoto.phone.event;


public class LoginEvent extends BaseEvent {
	public boolean isSuccess;
	public static LoginEvent build(boolean isSuccess) {
        LoginEvent event = new LoginEvent();
        event.isSuccess = isSuccess;
        return event;
    }
}
