package com.example.webmd.service;

import com.example.webmd.rep.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    // V4-> добовляем используем анотацию
    @Autowired //в нашем варианте userRep интерфейс обращения к базе данных
    private UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //findByUsername // стандартное обращение из мануала Spring
        return userRep.findByUsername(username);
    }
}
