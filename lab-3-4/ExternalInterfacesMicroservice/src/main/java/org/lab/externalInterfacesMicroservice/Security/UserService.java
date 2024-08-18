package org.lab.externalInterfacesMicroservice.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.lab.dao.models.User;
import org.lab.dao.repositories.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
@AllArgsConstructor
@ComponentScan({"org.lab.dao.repositories"})
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        return user.map(UserDetailsConfig::new).orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }

    public void encodeUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
