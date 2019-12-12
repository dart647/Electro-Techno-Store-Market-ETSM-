package com.etsm.ETSM.Configs;

import com.etsm.ETSM.Services.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthProvider authProvider;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/",
                        "/main",
                        "/catalog/**",
                        "/registration",
                        "/login").permitAll()//разрешенные сайты для входа без авторизации
                .and()
                .authorizeRequests()
                .antMatchers("/auth/userCabinet",
                        "/auth/basket").hasAnyAuthority("USER","MANAGER","ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/auth/admin",
                        "/admin/addProduct").hasAnyAuthority("MANAGER","ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/all").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/auth/userCabinet").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
    }
}
