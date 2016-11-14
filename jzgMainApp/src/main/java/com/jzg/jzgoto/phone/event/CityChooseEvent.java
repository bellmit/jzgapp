package com.jzg.jzgoto.phone.event;


import com.jzg.jzgoto.phone.models.business.user.CityChooseData;

public class CityChooseEvent extends BaseEvent {
    public CityChooseData cityInfo;
    public int sourceFrom = 0;

    public static CityChooseEvent build(CityChooseData cityInfo, int sourceFrom) {
        CityChooseEvent event = new CityChooseEvent();
        event.cityInfo = cityInfo;
        event.sourceFrom = sourceFrom;
        return event;
    }
}
