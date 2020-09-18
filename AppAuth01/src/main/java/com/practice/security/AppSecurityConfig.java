package com.practice.security;

import com.practice.service.CustomAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static com.practice.security.SecurityStaticConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomAccountDetailsService customAccountDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

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
//			.antMatchers("/login").permitAll()
//			.antMatchers("/","index","/css/*","/js/*").permitAll()
//			.antMatchers("/api/**").hasRole(USER.name()) //only STUDENT role can access this api and after that
//			.antMatchers("/api/v1/teams").hasRole(ADMIN.name())

	}


	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customAccountDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
