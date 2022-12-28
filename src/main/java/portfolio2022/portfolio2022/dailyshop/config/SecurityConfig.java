package portfolio2022.portfolio2022.dailyshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity//security 활성화
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder(){
        //패스워드 암호화
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/dailyShop").authenticated() //인증이 필요한 patterns
                .anyRequest().permitAll() //그게 아니면 인증 x
                .and()
                .formLogin()
                .loginPage("/dailyShop/login")//인증이 필요하면 여기로 이동
                .loginProcessingUrl("/dailyShop/login")//스프링 시큐리티가 로그인 자동 진행
                .defaultSuccessUrl("/dailyShop")//로그인이 정상적이면 여기로 이동
                .and().build();
    }
}
