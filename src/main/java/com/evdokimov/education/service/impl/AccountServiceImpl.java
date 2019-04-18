package com.evdokimov.education.service.impl;

import com.evdokimov.education.exceptions.UserAlreadyExists;
import com.evdokimov.education.model.Authority;
import com.evdokimov.education.model.User;
import com.evdokimov.education.repository.AuthotrityRepository;
import com.evdokimov.education.repository.UserRepository;
import com.evdokimov.education.security.AuthoritiesConstants;
import com.evdokimov.education.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthotrityRepository authotrityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        checkIfUserPresent(user);
        User newUser = createUser(user);
        userRepository.save(newUser);
    }

    private void checkIfUserPresent(User user) {
        Optional<User> checkLogin = userRepository.findOneByLogin(user.getLogin().toLowerCase());
        checkLogin.ifPresent(u -> {
            throw new UserAlreadyExists();
        });
        Optional<User> checkEmail = userRepository.findOneByEmail(user.getEmail().toLowerCase());
        checkEmail.ifPresent(u -> {
            throw new UserAlreadyExists();
        });
    }

    private User createUser(User user) {
        User newUser = new User();
        Authority authority = authotrityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        return newUser;
    }
}
