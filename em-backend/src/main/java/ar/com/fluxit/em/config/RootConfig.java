package ar.com.fluxit.em.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = { "ar.com.fluxit" })
public class RootConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("/application.properties"));
		
		ppc.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
		
		return ppc;
	}
	
}