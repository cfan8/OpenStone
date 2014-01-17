package com.linangran.openstone.card;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class IMinionCard extends ICard {

	public int health;
	public int attack;
	public boolean immune;
	public boolean stealth;
	public boolean taunt;
	public boolean windfury;
	public boolean hasShield;
	public boolean hasCharge;

	public IMinionCard()
	{
		super();
	}

}
