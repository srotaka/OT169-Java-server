package com.alkemy.ong;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.headers().frameOptions().sameOrigin().and()
	        .authorizeRequests()
	            .antMatchers("/css/*", "/js/*", "/img/*", "/**")
	            .permitAll()
	        .and().formLogin()
	            .loginPage("/user/login") // Que formulario esta mi login
	                .loginProcessingUrl("/logincheck")
	                .usernameParameter("username") // Como viajan los datos del logueo
	                .passwordParameter("password")// Como viajan los datos del logueo
	                .defaultSuccessUrl("/") // A que URL viaja
	                .permitAll()
	        .and().logout() // Aca configuro la salida
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .permitAll().and().csrf().disable();
		}
	}
	
