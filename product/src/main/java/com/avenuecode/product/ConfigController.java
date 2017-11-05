/**
 * 
 */
package com.avenuecode.product;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * @author timoshenko
 *
 */
@Controller
public class ConfigController {

	/**
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			container.setPort(8070);
		});
	}

}
