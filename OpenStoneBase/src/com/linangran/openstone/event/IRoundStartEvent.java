package com.linangran.openstone.event;

/**
 * Created by Admin on 1/17/14.
 */
public class IRoundStartEvent extends IEvent {
	@Override
	public int getEventLevel() {
		return IEvent.EVENT_LEVEL_HEAD;
	}

	@Override
	public int getEventType() {
		return IEvent.EVENT_TYPE_ROUND_START;
	}
}
