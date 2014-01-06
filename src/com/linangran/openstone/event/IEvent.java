package com.linangran.openstone.event;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class IEvent {

	public int eventType;
	public int eventLevel; //Decide when this event is created, it should be inserted into the head of the event list or the tail of it.
	public IEvent()
	{
		this.eventType = getEventType();
		this.eventLevel = getEventLevel();
	}

	public abstract int getEventLevel();

	public abstract int getEventType();

	public static final int EVENT_LEVEL_TAIL = 0;
	public static final int EVENT_LEVEL_HEAD = 1;

	public static final int EVENT_TYPE_ATTACK;
	public static final int EVENT_TYPE_SUMMON;
	public static final int EVENT_TYPE_SPELL_ATTACK;
	public static final int EVENT_TYPE_SPELL;
}
