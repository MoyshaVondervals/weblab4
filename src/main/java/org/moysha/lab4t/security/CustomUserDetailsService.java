package org.moysha.lab4t.security;

import org.moysha.lab4t.models.AppUser;
import org.moysha.lab4t.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("First invoke!");
        AppUser appUser = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        return new User(appUser.getUsername(), appUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}