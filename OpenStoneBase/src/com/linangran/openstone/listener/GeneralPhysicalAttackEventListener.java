package com.linangran.openstone.listener;

import com.linangran.openstone.card.IMinionCard;
import com.linangran.openstone.context.IContext;
import com.linangran.openstone.event.GeneralPhysicalAttackEvent;
import com.linangran.openstone.event.IEvent;
import com.linangran.openstone.event.IPhysicalAttackEvent;
import com.linangran.openstone.event.LaunchPhysicalAttackEvent;

import java.util.List;

/**
 * Created by Admin on 1/13/14.
 */
public interface GeneralPhysicalAttackEventListener
{
	public abstract List<IEvent> onFired(GeneralPhysicalAttackEvent event, IMinionCard origin, IMinionCard target, IContext context);
}
