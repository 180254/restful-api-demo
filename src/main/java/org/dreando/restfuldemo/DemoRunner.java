package org.dreando.restfuldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Mateusz Koza on 13.10.15.
 */
@SpringBootApplication
public class DemoRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoRunner.class);
    }

    @Configuration
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        /**
         * Tells that every request should be protected against not authenticated users using Basic Auth method.
         *
         * @see org.dreando.restfuldemo.DemoRunner.AccessConfiguration
         */
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();

            httpSecurity.csrf().disable();
        }
    }

    @Configuration
    public static class AccessConfiguration extends GlobalAuthenticationConfigurerAdapter {

        /**
         * In proper application we should use a specified bean that provides authentication control, UserDetailsService.
         */
        @Override
        public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder
                    .inMemoryAuthentication()
                    .withUser("test-user")
                    .password("test-pass")
                    .roles("USER");
        }
    }
}
