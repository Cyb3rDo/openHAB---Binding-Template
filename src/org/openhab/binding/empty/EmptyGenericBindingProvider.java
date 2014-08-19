package org.openhab.binding.empty;

import org.openhab.binding.empty.EmptyBindingProvider;
import org.openhab.binding.empty.EmptyGenericBindingProvider;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.items.NumberItem;
import org.openhab.core.library.items.StringItem;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Josh Stroschein 
 * @since 0.0.1
 */
public class EmptyGenericBindingProvider extends
	AbstractGenericBindingProvider implements EmptyBindingProvider {

	static final Logger logger = 
			LoggerFactory.getLogger(EmptyGenericBindingProvider.class);

	@Override
	public Class<? extends Item> getItemType(String itemName) {
		EmptyBindingConfig config = (EmptyBindingConfig) bindingConfigs.get(itemName);
		return config != null ? config.itemType : null;
	}

	@Override
	public String getItemName(String itemName) {
		EmptyBindingConfig config = (EmptyBindingConfig) bindingConfigs.get(itemName);		
		return config != null ? config.itemName : null;
	}

	/**
	 * This is the name that the binding uses in the *.items file. For example, this binding would be referenced in an items file
	 * as: Switch Test "Test Switch" { empty='ARG1' }
	 */
	@Override
	public String getBindingType() {
		return "empty";
	}

	@Override
	public void validateItemType(Item item, String bindingConfig)
			throws BindingConfigParseException {
		if ((item instanceof NumberItem) || (item instanceof StringItem) || (item instanceof SwitchItem) || (item instanceof DimmerItem)) {
			return;
		}
		throw new BindingConfigParseException("item '" + item.getName()
				+ "' is of type '" + item.getClass().getSimpleName()
				+ "', which is not supported by the Empty binding - please check your *.items configuration");		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
		
		String[] configParts = bindingConfig.trim().split(":");

		//Do simple validation here
		if (configParts.length < 1 && configParts.length > 1) {
			throw new BindingConfigParseException(
					"Empty binding configuration must consist of one parts [config="
							+ configParts + "]");
		}
		
		EmptyBindingConfig config = new EmptyBindingConfig();
				
		//populate the config object
		config.itemName = item.getName();		
		config.itemType = item.getClass();
		
		//now add the binding
		addBindingConfig(item, config);
	}
	
	/*
	 * Data structure to hold config parameters for *
	 * 
	 * @author Josh Stroschein
	 * @since 1.0.0
	 */
	static private class EmptyBindingConfig implements BindingConfig {

		public String itemName; // name of the *.items object
		public Class<? extends Item> itemType; //type of item that we're creating a config for
		
	}
}
