package com.alkemy.ong.config;


import com.alkemy.ong.filter.JwtRequestFilter;
import com.alkemy.ong.service.impl.UserDetailsCustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsCustomServiceImpl userDetailsCustomService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final String[] swaggerWhitelist = {
            "/configuration/ui",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api/docs",
            "/v2/api-docs"
    };

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsCustomService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers(swaggerWhitelist);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/auth/register","/auth/login").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/Slides").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/Slides/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/Slides/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/Slides/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/Slides").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/activities").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/activities/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/organization/public").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/news").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/news/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/news/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/news/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/categories/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/categories/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/categories").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/categories/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/storage/uploadFile").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/storage/deleteFile").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/comments").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/members").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/members/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/testimonials").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/testimonials/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/testimonials/{id}").hasRole("ADMIN")
		        .antMatchers(HttpMethod.GET,"/contacts").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}


