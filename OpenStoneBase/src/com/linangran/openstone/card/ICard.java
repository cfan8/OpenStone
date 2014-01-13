package com.linangran.openstone.card;

/**
 * Created by Admin on 1/6/14.
 */
public abstract class ICard {
	public int cost;
	public int quality;
	public String cardImage;
	public String cardName;
	public String cardDescription;
	public int overLoad;
	public boolean deckUsable;

	public static final int QUALITY_FREE = 0;
	public static final int QUALITY_COMMON = 1;
	public static final int QUALITY_RARE = 2;
	public static final int QUALITY_EPIC = 3;
	public static final int QUALITY_LEGENDARY = 4;

	public ICard() {
	}

}
