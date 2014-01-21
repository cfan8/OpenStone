package com.linangran.openstone.card;

import groovy.lang.GroovyObject;

import java.util.List;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class IChoiceCard extends ICard {

	public List<GroovyObject> choiceList;
	public IChoiceCard(List<GroovyObject> choiceList)
	{
		super();
		this.choiceList = choiceList;
	}

}
