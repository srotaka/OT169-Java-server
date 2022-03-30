package com.alkemy.ong.service;

import com.alkemy.ong.entity.User;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class UserDetailsSegService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User usuario = userRepository.findByEmail(s);
        if(usuario ==null){
            throw new UsernameNotFoundException("User not found");
        }
        /*Role assignment by user*/
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+usuario.getRoleId().getName());
        authorities.add(p1);

        org.springframework.security.core.userdetails.User userSeg =
                new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), authorities );


        return userSeg;
    }
}


