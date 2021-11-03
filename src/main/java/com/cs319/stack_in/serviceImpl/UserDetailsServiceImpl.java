package com.cs319.stack_in.serviceImpl;

import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserDetailsServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUniqueId(uniqueId);
        final User user;

        if (!optionalUser.isPresent()) throw new UsernameNotFoundException("User '" + uniqueId + "' not found");
        else user = optionalUser.get();

        if (!user.isActive()) throw new UsernameNotFoundException(uniqueId + " is deactivated user");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getId().toString())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public UserDetails loadUserByRoleTemp(String subject) {
        return org.springframework.security.core.userdetails.User
                .withUsername(subject)
                .password("")
                .authorities("ROLE_TEMP")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
