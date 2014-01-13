package com.linangran.openstone.context;

import com.linangran.openstone.card.IMinionCard;

/**
 * Created by Admin on 1/13/14.
 */
public abstract class IBoardMinion extends IMinionCard{
	public IMinionCard minionCard;
	public IPlayer owner;

	public IBoardMinion(IMinionCard card, IPlayer owner)
	{
		this.minionCard = card;
		this.owner = owner;
	}
}
