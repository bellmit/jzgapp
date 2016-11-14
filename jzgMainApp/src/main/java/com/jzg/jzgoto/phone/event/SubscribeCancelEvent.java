package com.jzg.jzgoto.phone.event;


import com.jzg.jzgoto.phone.models.CarConditionData;

public class SubscribeCancelEvent extends BaseEvent {
    public CarConditionData subscribeInfo;

    public static SubscribeCancelEvent build(CarConditionData subscribeInfo) {
        SubscribeCancelEvent event = new SubscribeCancelEvent();
        event.subscribeInfo = subscribeInfo;
        return event;
    }
}
