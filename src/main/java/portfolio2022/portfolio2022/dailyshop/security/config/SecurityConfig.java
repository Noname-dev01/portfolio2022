package portfolio2022.portfolio2022.dailyshop.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import portfolio2022.portfolio2022.dailyshop.security.common.FormAuthenticationDetailsSource;
import portfolio2022.portfolio2022.dailyshop.security.handler.CustomAccessDeniedHandler;
import portfolio2022.portfolio2022.dailyshop.security.service.PrincipalOauthUserService;

@EnableWebSecurity//security 활성화
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final FormAuthenticationDetailsSource authenticationDetailsSource;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private PrincipalOauthUserService principalOauthUserService;

    @Bean
    public PasswordEncoder passwordEncoder(){return PasswordEncoderFactories.createDelegatingPasswordEncoder();}
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> {
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
            web.ignoring().antMatchers("/favicon.ico","/resources/**","/error");
        };
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/dailyShop/mypage/**").authenticated()
                .antMatchers("/dailyShop/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
        .and()
                .formLogin()
                .loginPage("/dailyShop/login")//인증이 필요하면 여기로 이동
                .loginProcessingUrl("/dailyShop/login_proc")//스프링 시큐리티가 로그인 자동 진행
                .defaultSuccessUrl("/dailyShop")//로그인이 정상적이면 여기로 이동
                .authenticationDetailsSource(authenticationDetailsSource)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll();
//        .and()
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler())
//                .and()
//                .oauth2Login()
//                .loginPage("/dailyShop/login")
//                .defaultSuccessUrl("/dailyShop")
//                .userInfoEndpoint()
//                .userService(principalOauthUserService);

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }
}
