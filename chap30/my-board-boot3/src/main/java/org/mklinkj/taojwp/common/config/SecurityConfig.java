package org.mklinkj.taojwp.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.security.handler.CustomLoginFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  AuthenticationFailureHandler loginFailureHandler() {
    return new CustomLoginFailureHandler();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        authorize ->
            authorize
                .requestMatchers(
                    "/login.do",
                    "/webjars/**", //
                    "/webjars_locator/**",
                    "/favicon.ico",
                    "/resources/**",
                    "/",
                    "/main.do",
                    "/index.html",
                    "/member/memberForm.do",
                    "/member/addMember.do",
                    "/member/listMembers.do",
                    "/file/**",
                    "/mail/*",
                    "/error")
                .permitAll()
                .anyRequest()
                .authenticated());

    http.formLogin(
        formLogin ->
            formLogin
                .loginPage("/login.do")
                .loginProcessingUrl(
                    "/login") // ✨ Java 기반 설정에서는 따로 설정하지 않으면 loginPage에 설정한 경로와 동일하게 설정된다.
                .defaultSuccessUrl("/main.do")
                .failureHandler(loginFailureHandler())
                .permitAll());

    http.logout(
        logout ->
            logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/main.do"));

    return http.build();
  }
}
