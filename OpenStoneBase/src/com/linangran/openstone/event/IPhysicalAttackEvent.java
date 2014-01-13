package com.linangran.openstone.event;

import com.linangran.openstone.card.ICard;
import com.linangran.openstone.card.IMinionCard;

/**
 * Created by Admin on 1/13/14.
 */
public abstract class IPhysicalAttackEvent extends IEvent {

	public IPhysicalAttackEvent()
	{
		super();
	}

	@Override
	public int getEventLevel() {
		return IEvent.EVENT_LEVEL_HEAD;
	}

	@Override
	public int getEventType() {
		return IEvent.EVENT_TYPE_ATTACK;
	}

	public ICard origin;
	public ICard target;
}
