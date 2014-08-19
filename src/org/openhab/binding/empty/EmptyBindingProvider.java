package org.openhab.binding.empty;

import org.openhab.core.binding.BindingProvider;
import org.openhab.core.items.Item;

/**
 * This interface is implemented by classes that can provide mapping information
 * between openHAB items and EMPTY items.
 * 
 * @author Josh Stroschein
 * @since 0.0.1
 */
public interface EmptyBindingProvider extends BindingProvider {
	
	/**
	 * @return the corresponding openHAB item type to the given <code>itemName</code>
	 */
	public Class<? extends Item> getItemType(String itemName);
	
	/**
	 * @return the corresponding Empty item name to the given <code>itemName</code>
	 */
	public String getItemName(String itemName);
}
