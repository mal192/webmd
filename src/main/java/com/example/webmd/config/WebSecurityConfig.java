package com.example.webmd.config;

import com.example.webmd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // добавляю для авторизации с базы
// в v4 авторизация реализутся через userDetectService поэтому DataSource не нужен
//    @Autowired
//    private DataSource dataSource; //
//    //- - - - - -- - - - - --
    // V4--
    @Autowired
    private UserService userService;

    //  <--
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //главная страничка, разрешающая полный доступ
                .antMatchers("/", "/home", "/reg").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login") //форма логининга
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

//    @Bean УДАЛЯЕМ "менеджер" для загрузки юзверей с базы
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u")
//                        .password("p")
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    // Берем юзеров с базы. для ускарения давим Alt+Insrt -> Override Metods
    // выбираем configure(AuthenticationManagerBuilder auth)

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //V4 меняем на
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.jdbcAuthentication()
//                .dataSource(dataSource) //На верху добовляем AutoWired
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());//кодирование паролей
//        Убираем SQL обращение V4 ->>>
//                .usersByUsernameQuery
//                //SQL запрос поиск юзера по его имени. НЕЛЬЗЯ менять порядок палей
//            ("SELECT username, password, active FROM usr WHERE username=?")
//                //Запрос помогает spring получить список пользователей с их ролями
//               .authoritiesByUsernameQuery
//("SELECT u.username, ur.roles FROM usr u INNER JOIN user_role ur ON u.id = ur.user_id WHERE u.username=?");
//
    }
}
