package pyramid.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pyramid.controllers.ISolverRunner;
import pyramid.controllers.SolverRunner;
import pyramid.solvers.SolversFactory;

/**
 * Created by ledenev.p on 21.04.2015.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pyramid")
public class SpringConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SolversFactory solversFactory() {
        return new SolversFactory(50);
    }

    @Bean
    public ISolverRunner solverRunner() {
        return new SolverRunner();
    }
}
