package com.linangran.openstone.context;

import com.linangran.openstone.card.ICard;
import com.linangran.openstone.card.IHeroCard;
import com.linangran.openstone.card.IMinionCard;

import java.util.List;

/**
 * Created by Admin on 1/21/14.
 */
public class IGameConfig {
	public List<ICard> cardList;
	public List<IHeroCard> heroList;
	public String groovyClasspath;
	public String resourcePath;
}
