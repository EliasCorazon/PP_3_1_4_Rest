package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.ropository.UserRepository;

import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    @Transactional
    @Override
    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }
    @Transactional
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public User addUser(User addUser) {
        addUser.setPassword(new BCryptPasswordEncoder().encode(addUser.getPassword()));
        return userRepository.save(addUser);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getAuthorities()
        );
    }

    @Override
    @Transactional
    public void edit(User editUser) {
        String password = editUser.getPassword();
        editUser.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(editUser);
    }

    @Override
    @Transactional
    public User getUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
