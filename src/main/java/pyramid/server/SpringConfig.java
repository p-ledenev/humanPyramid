package pyramid.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ledenev.p on 21.04.2015.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pyramid")
public class SpringConfig extends WebMvcConfigurerAdapter {
}