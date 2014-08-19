package org.openhab.binding.empty;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extension of the default OSGi bundle activator
 * 
 * @author Josh Stroschein
 * @since 0.0.1
 */
public class Activator implements BundleActivator {

	private static Logger logger = 
			LoggerFactory.getLogger(Activator.class);

	/**
	 * Called whenever the OSGi framework starts our bundle
	 */
	public void start(BundleContext bundleContext) throws Exception {
		logger.debug("Empty Binding Started...");
	}

	/**
	 * Called whenever the OSGi framework stops our bundle
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		logger.debug("Empty Binding Started...");
	}

}
