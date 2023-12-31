//package com.dev.ForecastApiTestJarV2.backup;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.dev.ForecastApiTestJarV2.config.CorsConfig;
//import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
//import com.dev.ForecastApiTestJarV2.filter.JwtExceptionFilter;
//import com.dev.ForecastApiTestJarV2.filter.JwtFilter;
//import com.dev.ForecastApiTestJarV2.service.member.CustomUserDetailsService;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfigEX {
//
//    private final JwtTokenProvider jwtTokenProvider;
//    private final CustomUserDetailsService customUserDetailsService;
//    private final CorsConfig corsConfig;
//    private final AuthenticationEntryPoint entryPoint;
//    private final String[] allowedUrls = {
//    		"/api/member/signup", 
//    		"/api/member/signin",
//    		"/test/**"
//    		};
//    private final String[] notAllowedUrls = {
//    		"/api/member/test", 
//    		};
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(allowedUrls).permitAll()
//                .antMatchers(notAllowedUrls).hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(entryPoint)
//                .and()
//                .addFilter(corsConfig.corsFilter())
//                .addFilterBefore(
//                        new JwtFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class
//                ).addFilterBefore(new JwtExceptionFilter(), JwtFilter.class);
//                
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setHideUserNotFoundExceptions(false);
//        return authenticationProvider;
//    }
//}