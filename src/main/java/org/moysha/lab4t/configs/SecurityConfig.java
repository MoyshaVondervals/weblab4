package org.moysha.lab4t.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",             // Главная страница
                                "/index.html",   // React-файлы
                                "/static/**",    // Статические ресурсы React
                                "/favicon.ico",  // Иконка
                                "/api/auth/**"
                        ).permitAll() // Разрешаем без авторизации
                        .anyRequest().authenticated() // Остальные запросы требуют аутентификации
                )

                // Включаем стандартный обработчик формы логина
                .formLogin(form -> form
                        .loginPage("/login") // URL страницы логина
                        .loginProcessingUrl("/login") // URL для обработки логина
                        .defaultSuccessUrl("/dashboard", true) // Перенаправление после успешного входа
                        .permitAll()
                )

                // Настройка логаута
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода из системы
                        .logoutSuccessUrl("/login") // Куда перенаправить после выхода
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
