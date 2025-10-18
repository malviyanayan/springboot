package com.nayan.jwt1.services;

import com.nayan.jwt1.models.User;
import com.nayan.jwt1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // save new user (signup)
    public User saveNewUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); // encode password
        return userRepository.save(newUser);
    }

    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get single user by id
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // update user (only password/email/roles for example)
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setRoles(updatedUser.getRoles());
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    // delete user
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
