package ca.sheridancollege.ca.sandnavl.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity 
@AllArgsConstructor
public class SecurityConfig{
	private UserDetailsService userDetailsService;
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Bean
	public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder=
				http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService)
		.passwordEncoder(encoder);
		return authenticationManagerBuilder.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//Delete on production, bottom 2 lines
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeHttpRequests((authz)-> authz
				.requestMatchers(antMatcher("/{username}/**")).hasRole("USER")
					.requestMatchers(antMatcher("/css/**")).permitAll()
					.requestMatchers(antMatcher("/scripts/**")).permitAll()
					.requestMatchers(antMatcher("/h2-console/**")).permitAll()
					.requestMatchers(antMatcher("/register")).permitAll()
					.anyRequest().authenticated()
			).formLogin((formLogin)-> formLogin
					.loginPage("/login")
					.successHandler(customAuthenticationSuccessHandler)
					.failureUrl("/login?failed")
					.permitAll()
			).logout((logout)-> logout
					.deleteCookies("remove")
					.invalidateHttpSession(false)
					.logoutUrl("/logout")
					.logoutSuccessUrl("/?logout")
					.permitAll()
					
			);
		return http.build();
	}
}
