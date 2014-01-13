package com.linangran.openstone.card;

import java.util.List;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class IChoiceCard extends ICard {

	public List<Class<? extends ICard>> choiceList;
	public IChoiceCard(List<Class<? extends ICard>> choiceList)
	{
		super();
		this.choiceList = choiceList;
	}

}
