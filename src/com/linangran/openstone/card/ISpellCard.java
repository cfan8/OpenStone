package com.linangran.openstone.card;

import com.linangran.openstone.event.IEvent;

import java.util.List;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class ISpellCard extends ICard {

	public ISpellCard()
	{
		super();
	}

	public abstract List<IEvent> onUse();
}
