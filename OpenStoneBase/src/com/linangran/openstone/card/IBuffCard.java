package com.linangran.openstone.card;

/**
 * Created by Admin on 1/17/14.
 */
public abstract class IBuffCard {

	public String buffDescription;

	public abstract void onCast();

	public abstract void onRemove();

	public IBuffCard()
	{

	}
}
