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
	public boolean freezed;
	public boolean hasShield;
	public boolean hasCharge;

	public IMinionCard()
	{
		super();
		this.health = getHealth();
		this.attack = getAttack();
		this.immune = getImmune();
		this.stealth = getStealth();
		this.taunt = getTaunt();
		this.windfury = getWindfury();
		this.freezed = false;
		this.hasShield = getHasShield();
		this.hasCharge = getHasCharge();
	}

	public abstract boolean getHasCharge();

	public abstract boolean getHasShield();

	public abstract boolean getWindfury();

	public abstract boolean getTaunt();

	public abstract boolean getStealth();

	public abstract boolean getImmune();

	public abstract int getAttack();

	public abstract int getHealth();

}
