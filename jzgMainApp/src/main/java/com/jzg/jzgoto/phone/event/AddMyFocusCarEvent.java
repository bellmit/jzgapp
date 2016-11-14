package com.jzg.jzgoto.phone.event;


import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;

public class AddMyFocusCarEvent extends BaseEvent {
	public FocusCarData carInfo;

    public static AddMyFocusCarEvent build(FocusCarData carInfo) {
        AddMyFocusCarEvent event = new AddMyFocusCarEvent();
        event.carInfo = carInfo;
        return event;
    }
}
