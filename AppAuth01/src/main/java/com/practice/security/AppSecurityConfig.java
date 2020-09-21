package com.practice.security;

import com.practice.jwt.JwtFilter;
import com.practice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.practice.security.SecurityStaticConstants.*;
import static com.practice.security.Role.*;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
			.authorizeRequests()
			.antMatchers(
                    "/",
                    "/favicon.ico",
                    "/**/*.png",
                    "/**/*.gif",
                    "/**/*.svg",
                    "/**/*.jpg",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            ).permitAll()
			.antMatchers(SIGN_UP_URLS).permitAll()
			.antMatchers(PROFILE_API).permitAll()
			.antMatchers(TEAM_API).permitAll()
			.antMatchers(MEMBER_API).permitAll()
			.antMatchers(HISTORY_API).permitAll()
            .anyRequest().authenticated();
		
		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(accountService);
		return provider;
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
