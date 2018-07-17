package com.example.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tada
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Spring Security ignores URLs of static resources
        web.ignoring().requestMatchers(
                PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure login
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("account")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .permitAll();
        // configure logout
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true);
        // configure URL authorization
        http.authorizeRequests()
                .mvcMatchers("/signup").permitAll()
                .mvcMatchers(HttpMethod.GET, "/issues/").hasAuthority("readIssue")
                .mvcMatchers(HttpMethod.GET, "/issue/new").hasAuthority("writeIssue")
                .mvcMatchers(HttpMethod.POST, "/issues/").hasAuthority("writeIssue")
                .mvcMatchers("/users").hasAuthority("manageUser")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // CAUTION : Do not use NoOpPasswordEncoder in production
        return NoOpPasswordEncoder.getInstance();
    }
}
