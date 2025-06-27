package com.rxjs.practice.reactiveApp.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  // https://www.baeldung.com/spring-qualifier-annotation
  // @Qualifier("userDetailsServiceImpl")
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#customize-global-authentication-manager
  // https://www.javaguides.net/2024/05/securityfilterchain-in-spring-boot-3.html
  // https://stackoverflow.com/questions/74609057/how-to-fix-spring-authorizerequests-is-deprecated
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/css/**", "/js/**", "/registration").permitAll()
            .requestMatchers(HttpMethod.GET, "/todos/**").permitAll()
            .requestMatchers("/").permitAll()
            .anyRequest().authenticated())

        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/", true).permitAll())
        .logout(logout -> logout
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/").permitAll());

    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

    ProviderManager providerManager = new ProviderManager(authenticationProvider);
    providerManager.setEraseCredentialsAfterAuthentication(false);

    return providerManager;
  }

  /*
   * @Autowired
   * public void configureGlobal(AuthenticationManagerBuilder auth) throws
   * Exception {
   * auth
   * .userDetailsService(userDetailsService)
   * .passwordEncoder(bCryptPasswordEncoder());
   * }
   */
}
