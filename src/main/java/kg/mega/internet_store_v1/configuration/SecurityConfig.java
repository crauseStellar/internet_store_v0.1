package kg.mega.internet_store_v1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$u0KWZS8ZEyV97oA37GDf8eosETTvXeyCNajerIuCXbMpN1IWKJz8m")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$10$oOsWW8Rhxp3aTQ6gWsWxXepORVenA2XR/GIyCjLzlXClxDHLfbPbC")
                .roles("USER", "ADMIN")
                .build();
        UserDetails manager = User.withUsername("manager").password(passwordEncoder().encode("MyPassword")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin,manager);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("api/v1/good/**", "api/v1/category/**").hasRole("ADMIN")
                        .requestMatchers("api/v1/user/**").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

}
