package com.lambferret.playhttpground.config;

import com.lambferret.playhttpground.security.CustomHttpSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    /**
     * http security 에 대한 filter chain
     * 경로 등의 설정을 하는 곳
     *
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeHttpRequests((authz) -> authz.anyRequest().permitAll())
//                .formLogin().disable()
                .logout().disable()
                .build();
    }

    /**
     * web시큐리티를 적용할 경로의 예외처리
     * WebSecurity는 Spring Security Chain에 적용되지 않으므로 제일 최상위의 필터를 가진다
     * 여담 : WebSecurity는 여러개의 HttpFilterChain을 가질 수 있다 (보통 노필요)
     *
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/resources/**");
    }

    /**
     * 세션 데이터를 헤더의 X-AUTH-TOKEN 형식으로 반환
     *
     */
    @Bean
    public HeaderHttpSessionIdResolver httpSessionIdResolver() {
        return new CustomHttpSessionStrategy("X-Auth-Token");
    }

    /**
     * CORS 설정
     *
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addExposedHeader("X-Auth-Token");
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
