package com.jzg.jzgoto.phone.event;


public class NetworkStatusEvent extends BaseEvent {
	private boolean isConnected;
	
	public static NetworkStatusEvent build(boolean isConnected) {
        NetworkStatusEvent event = new NetworkStatusEvent();
        event.isConnected = isConnected;
        return event;
    }
	public boolean getConnected(){
		return isConnected;
	}
}
