package com.michau.kurseasyapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    //    private final RestAuthenticationSuccessHandler successHandler;
//    private final RestAuthenticationFailureHandler failureHandler;
    private final String secret;
    private final OAuthClientProvidersRepository OAuthClientProvidersRepository;


    public SecurityConfig(DataSource dataSource, ObjectMapper objectMapper,
                          @Value("${jwt.secret}") String secret, OAuthClientProvidersRepository OAuthClientProvidersRepository) {
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
//        this.successHandler = successHandler;
//        this.failureHandler = failureHandler;
        this.secret = secret;
        this.OAuthClientProvidersRepository = OAuthClientProvidersRepository;
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .withDefaultSchema()
//                .dataSource(dataSource)
//                .withUser("test")
//                .password("{bcrypt}" + new BCryptPasswordEncoder().encode("test"))
//                .roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/maja")
                .failureUrl("/failed")
                .clientRegistrationRepository(OAuthClientProvidersRepository.clientRegistrationRepository())
                .authorizedClientService(OAuthClientProvidersRepository.authorizedClientService())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .addFilter(new JwtFilter(authenticationManager()))
//                .addFilter(new JwtAuthorizationFilter(authenticationManagerBean()))
                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .headers().frameOptions().disable();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
//        return tomcatServletWebServerFactory -> tomcatServletWebServerFactory.addContextCustomizers((TomcatContextCustomizer) context -> {
//            context.setCookieProcessor(new LegacyCookieProcessor());
//        });
//    }
}
