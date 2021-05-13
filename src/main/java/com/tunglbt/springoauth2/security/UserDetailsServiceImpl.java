package com.tunglbt.springoauth2.security;

import com.tunglbt.springoauth2.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findUser(username);
        if (user == null) {
            // If user not found. Throw exception.
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.getAuthorities() != null) {
            String[] authors = user.getAuthorities().split(";");
            for (String authority : authors) {
                // Spring needs roles to be in this format: "ROLE_" + userRole (e.g. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (e.g. hasRole("ADMIN")).
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + authority));
            }
        }
        return new MyUserDetails(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private UserEntity findUser(String username) {
        // hard coding the users
        final List<UserEntity> users = Arrays.asList(
                new UserEntity(1L, "tunglbt", bCryptPasswordEncoder.encode("123456"), "USER"),
                new UserEntity(2L, "admin", bCryptPasswordEncoder.encode("123456"), "ADMIN")
        );
        for (UserEntity user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
