package org.example.medicos.services;

import org.example.medicos.models.User;
import org.example.medicos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    public User create(User user) {
        return userRepository.save(user);
    }
    public User update(User user) {
        return userRepository.save(user);
    }
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
