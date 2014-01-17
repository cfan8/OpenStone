package com.linangran.openstone.context;

import com.linangran.openstone.card.IBuffCard;
import com.linangran.openstone.card.IMinionCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 1/13/14.
 */
public abstract class IBoardMinion{
	public IMinionCard minionCard;
	public IPlayer owner;
	public List<IBuffCard> buffList;

	public int health;
	public int maxHealth;
	public int attack;
	public boolean immune;
	public boolean stealth;
	public boolean taunt;
	public boolean windfury;
	public boolean freezed;
	public boolean hasShield;
	public boolean hasCharge;
	public int cost;

	public int remainedAttackCount;

	public IBoardMinion(IMinionCard card, IPlayer owner)
	{
		this.minionCard = card;
		this.owner = owner;
		this.buffList = new ArrayList<IBuffCard>();
	}
}
