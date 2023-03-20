// TODO: SET [WEB SECURITY]

// package com.boilerplate.xotomicro_back_auth_service.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.boilerplate.xotomicro_back_auth_service.repository.UserRepository;
// import com.boilerplate.xotomicro_back_auth_service.serviceimpl.UserDetailsServiceImpl;
// import com.boilerplate.xotomicro_back_auth_service.utils.AuthEntryPointJwt;
// import com.boilerplate.xotomicro_back_auth_service.utils.AuthTokenFilter;

// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(
// 		prePostEnabled = true)
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

// 	@Autowired
// 	UserDetailsServiceImpl userDetailsService;

// 	@Autowired
// 	private AuthEntryPointJwt unauthorizedHandler;

// 	@Autowired
// 	private AuthTokenFilter authenticationJwtTokenFilter;

// 	@Override
// 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

// 		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

// 	}

// 	@Bean
// 	@Override
// 	public AuthenticationManager authenticationManagerBean() throws Exception {
// 		return super.authenticationManagerBean();
// 	}

// 	@Bean
// 	public PasswordEncoder passwordEncoder() {
// 		return new BCryptPasswordEncoder();
// 	}

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 			http.csrf().disable()
// 			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
// 			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
// 			.and()
// 			.authorizeRequests()
// 			.antMatchers("/auth/**").permitAll()
// 			.antMatchers("/actuator/health").permitAll()
// 			.anyRequest()
// 			.authenticated();

// 			http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
// 	}

// }
