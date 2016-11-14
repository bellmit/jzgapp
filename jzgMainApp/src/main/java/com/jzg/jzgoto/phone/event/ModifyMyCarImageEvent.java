package com.jzg.jzgoto.phone.event;


import android.content.Context;

import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;

public class ModifyMyCarImageEvent extends BaseEvent {
	public CarManagerMyCarData carInfo;
    public Context activity;

    public static ModifyMyCarImageEvent build(CarManagerMyCarData carInfo, Context activity) {
        ModifyMyCarImageEvent event = new ModifyMyCarImageEvent();
        event.carInfo = carInfo;
        event.activity = activity;
        return event;
    }
}
