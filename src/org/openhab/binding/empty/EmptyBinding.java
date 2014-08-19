package org.openhab.binding.empty;

import java.util.Dictionary;

import org.openhab.binding.empty.EmptyBindingProvider;
import org.openhab.core.binding.AbstractBinding;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.items.NumberItem;
import org.openhab.core.library.items.StringItem;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.IncreaseDecreaseType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.Command;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An empty binding 'template'.
 * 
 * @author Josh Stroschein
 * @since 0.0.1
 */
public class EmptyBinding extends AbstractBinding<EmptyBindingProvider> implements ManagedService {

	private static final Logger logger = 
			LoggerFactory.getLogger(EmptyBinding.class);
	
    /**
     * @{inheritDoc}
     */
	@Override
	public void internalReceiveCommand(String itemName, Command command) {

		if(itemName != null) {
			EmptyBindingProvider provider = findFirstMatchingBindingProviderByType(itemName, command);

			if (provider == null) {
				logger.warn("Doesn't find matching binding provider [itemName={}, command={}]", itemName, command);
				return;			
			}
			
			//handle the command here
		}		
	}
	
	/**
	 * Find the first matching {@link Empty} according to
	 * <code>itemName</code>.
	 * 
	 * @param itemName
	 * 
	 * @return the matching binding provider or <code>null</code> if no binding
	 *         provider could be found
	 */
	private EmptyBindingProvider findFirstMatchingBindingProviderByType(String itemName, Command command) {
		
		EmptyBindingProvider firstMatchingProvider = null;
		Class<? extends Item> itemClass = mapCommandToItemType(command);

		for (EmptyBindingProvider provider : this.providers) {
			
			String emptyItemName = provider.getItemName(itemName);
			Class<? extends Item> itemType = provider.getItemType(itemName);

			if (itemClass.equals(itemType)) {
				firstMatchingProvider = provider;
				break;
			} else if (emptyItemName != null && itemType != null && itemType.isAssignableFrom(itemClass)) {
				firstMatchingProvider = provider;
				break;
			}
		}

		return firstMatchingProvider;
	}
	
	private Class<? extends Item> mapCommandToItemType(Command command) {
		if (command instanceof IncreaseDecreaseType) {
			return DimmerItem.class;
		} else if (command instanceof PercentType) {
			return DimmerItem.class;
		} else if (command instanceof DecimalType) {
			return NumberItem.class;
		} else if (command instanceof OnOffType) {
			return SwitchItem.class;
		} else if (command instanceof StringType) {
			return StringItem.class;
		} else {
			logger.warn("No explicit mapping found for command type '{}' - return StringItem.class instead", command.getClass().getSimpleName());
			return StringItem.class;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException {
		// TODO Auto-generated method stub
		
	}
}
