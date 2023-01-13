package mk.ukim.finki.wp.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;


    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/assets/**", "/register").permitAll()
//                .antMatchers("/admin/*").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .failureForwardUrl("/login?error=BadCredentials")
//                .defaultSuccessUrl("/courses", true)
//                .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/login");

        http.csrf().disable()
                .authorizeRequests().antMatchers("/login","/courses","/grades/allGrades","/teachers/allTeachers","/css/**","/img/**","/js/**","/lib/**")
                .permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/courses/access_denied");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("123"))
                .authorities("ROLE_ADMIN")
                .and()
                .withUser("user")
                .password(passwordEncoder.encode("123"))
                .authorities("ROLE_USER");
    }
}
