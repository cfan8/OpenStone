package com.linangran.openstone.card;

import groovy.lang.GroovyObject;

import java.util.List;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class IChoiceCard extends ICard {

	public List<ICard> choiceList;
	public IChoiceCard(List<ICard> choiceList)
	{
		super();
		this.choiceList = choiceList;
	}

}
