//package com.example.sourceSafeMaven.service;
//
//import com.example.sourceSafeMaven.entities.User;
//import com.example.sourceSafeMaven.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class UserDetail implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserNameOrEmail(username, username);
//        if(user==null){
//            throw new UsernameNotFoundException("User not exists by Username");
//        }
//
//
//        return org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
//                .password(user.getPassword())
////                .roles(String.valueOf(user.getRole()))
//                .build();
//
////        Set<GrantedAuthority> authorities = user.getRole().stream()
////                .map((role) -> new SimpleGrantedAuthority(role.getName()))
////                .collect(Collectors.toSet());
////        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
//    }
//}
