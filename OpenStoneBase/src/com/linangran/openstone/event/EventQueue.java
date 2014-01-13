package com.linangran.openstone.event;

import java.util.List;

/**
 * Created by Admin on 1/13/14.
 */
public class EventQueue {

	private List<IEvent> eventList;

	public void addEvent(IEvent event)
	{
		if (event.getEventLevel() == Integer.MAX_VALUE)
		{
			eventList.add(event);
		}
		else if (event.getEventLevel() == Integer.MIN_VALUE)
		{
			eventList.add(0, event);
		}
		else
		{
			binarySearchAddEvent(event, 0, eventList.size());
		}
	}

	public void addEvents(List<IEvent> events)
	{
		for (int i = 0; i < events.size(); i++)
		{
			addEvent(events.get(i));
		}
	}

	public void binarySearchAddEvent(IEvent event, int startIndex, int endIndex)
	{
		int t = (startIndex + endIndex) / 2;
		if (t == startIndex)
		{
			if (event.compareTo(eventList.get(t)) < 0)
			{
				eventList.add(t, event);
			}
			else
			{
				eventList.add(t + 1, event);
			}
		}
		else
		{
			if (event.compareTo(eventList.get(t)) <= 0)
			{
				binarySearchAddEvent(event, startIndex, t);
			}
			else
			{
				binarySearchAddEvent(event, t, endIndex);
			}
		}
	}

}
