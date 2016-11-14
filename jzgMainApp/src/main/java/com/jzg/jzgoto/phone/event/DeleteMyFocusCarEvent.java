package com.jzg.jzgoto.phone.event;


import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;

public class DeleteMyFocusCarEvent extends BaseEvent {
	public FocusCarData carInfo;

    public static DeleteMyFocusCarEvent build(FocusCarData carInfo) {
        DeleteMyFocusCarEvent event = new DeleteMyFocusCarEvent();
        event.carInfo = carInfo;
        return event;
    }
}
