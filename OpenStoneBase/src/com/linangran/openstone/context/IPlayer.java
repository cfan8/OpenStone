package com.linangran.openstone.context;

import com.linangran.openstone.card.ICard;
import com.linangran.openstone.card.IMinionCard;
import com.linangran.openstone.card.ISecretCard;
import com.linangran.openstone.card.ISpellCard;

import java.util.List;

/**
 * Created by Admin on 1/13/14.
 */
public abstract class IPlayer {
	IMinionCard hero;
	ISpellCard heroAbility;
	boolean heroAbilityUsed;
	int heroHealth;
	int heroAttack;
	int heroArmor;
	boolean heroAttackUsed;
	List<ICard> handList;
	List<ICard> deckList;
	List<IBoardMinion> minionList;
	List<ISecretCard> secretList;
}
