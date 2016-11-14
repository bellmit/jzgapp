package com.jzg.jzgoto.phone.event;


import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;

public class ModifyMyCarMileageEvent extends BaseEvent {
	public CarManagerMyCarData carInfo;
	public boolean isEvaluate;

    public static ModifyMyCarMileageEvent build(CarManagerMyCarData carInfo, boolean isEvaluate) {
        ModifyMyCarMileageEvent event = new ModifyMyCarMileageEvent();
        event.carInfo = carInfo;
		event.isEvaluate = isEvaluate;
        return event;
    }
}
