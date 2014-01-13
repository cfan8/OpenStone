import com.linangran.openstone.card.IMinionCard
import com.linangran.openstone.event.GeneralPhysicalAttackEvent
import com.linangran.openstone.event.IEvent
import com.linangran.openstone.listener.GeneralPhysicalAttackEventListener

/**
 * Created by Admin on 1/13/14.
 */
class SimpleCard extends IMinionCard implements GeneralPhysicalAttackEventListener
{

	@Override
	List<IEvent> onFired(GeneralPhysicalAttackEvent event, IMinionCard origin, IMinionCard target) {
		List<IEvent> events = new ArrayList<IEvent>();
		if
	}
}
