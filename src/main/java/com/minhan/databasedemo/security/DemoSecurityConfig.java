package com.minhan.databasedemo.security;

import com.minhan.databasedemo.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    private static final String EMPLOYEE_ROLE = "EMPLOYEE";
    private static final String MANAGER_ROLE = "MANAGER";
    private static final String ADMIN_ROLE = "ADMIN";

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles(EMPLOYEE_ROLE)
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles(EMPLOYEE_ROLE, MANAGER_ROLE)
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles(EMPLOYEE_ROLE, MANAGER_ROLE, ADMIN_ROLE)
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager
//                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
//
//        jdbcUserDetailsManager
//                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
//
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> {
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/students").hasRole(EMPLOYEE_ROLE)
                    .requestMatchers(HttpMethod.GET, "/api/students/**").hasRole(EMPLOYEE_ROLE)
                    .requestMatchers(HttpMethod.POST, "/api/students").hasRole(MANAGER_ROLE)
                    .requestMatchers(HttpMethod.PUT, "/api/students").hasRole(MANAGER_ROLE)
                    .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole(ADMIN_ROLE);

            configurer
                    .requestMatchers(HttpMethod.GET, "/api/members").hasRole(EMPLOYEE_ROLE)
                    .requestMatchers(HttpMethod.GET, "/api/members/**").hasRole(EMPLOYEE_ROLE)
                    .requestMatchers(HttpMethod.POST, "/api/members").hasRole(MANAGER_ROLE)
                    .requestMatchers(HttpMethod.PUT, "/api/members").hasRole(MANAGER_ROLE)
                    .requestMatchers(HttpMethod.DELETE, "/api/members/**").hasRole(ADMIN_ROLE);
        });

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
