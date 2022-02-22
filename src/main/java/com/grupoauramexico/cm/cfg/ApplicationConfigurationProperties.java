/**
 * 
 */
package com.grupoauramexico.cm.cfg;

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * @author vaguire
 *
 */
@ConfigurationProperties("application") 
public class ApplicationConfigurationProperties implements ApplicationConfiguration {

	/**
	 * 
	 */
	public ApplicationConfigurationProperties() {
	}

}
