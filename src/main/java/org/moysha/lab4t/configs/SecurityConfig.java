package org.moysha.lab4t.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder().username("admin").password(passwordEncoder.encode("admin")).build();
        return new InMemoryUserDetailsManager(user);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth->auth.requestMatchers("/welcome","/login").permitAll()
//                .requestMatchers("/**").authenticated())
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//                .formLogin(formLogin->formLogin.loginPage("/login"))
//                .build();
//
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/", "/welcome", "/login").permitAll() // Укажите доступные URL
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login") // URL кастомной страницы логина
                    .loginProcessingUrl("/login-page") // URL для обработки формы (из action формы)
                    .defaultSuccessUrl("/welcome") // Перенаправление после успешного входа
                    .failureUrl("/login?error=true") // Перенаправление при ошибке входа
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
            );

    return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
