package com.jzg.jzgoto.phone.event;

import com.jzg.pricechange.phone.JzgCarChooseStyle;

public class CarChooseEvent extends BaseEvent {
    public JzgCarChooseStyle carInfo;
    public int sourceFrom = 0;

    public static CarChooseEvent build(JzgCarChooseStyle carInfo, int sourceFrom) {
        CarChooseEvent event = new CarChooseEvent();
        event.carInfo = carInfo;
        event.sourceFrom = sourceFrom;
        return event;
    }
}
