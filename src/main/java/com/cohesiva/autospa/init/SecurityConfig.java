package com.cohesiva.autospa.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cohesiva.autospa.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("a").password("123456")
				.roles("USER");
		auth.inMemoryAuthentication().withUser("b").password("123456")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("c").password("123456")
				.roles("SUPERADMIN");
//		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/views/usersTable*")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/views/manager*")
				.access("hasRole('ROLE_SUPERADMIN')").and().logout().permitAll()
				.logoutSuccessUrl("/logout").and().formLogin().loginPage("/login");

	}

}
