package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdminRest {
    private final UserService userService;

    @Autowired
    public AdminRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> AllUsers() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> panel(Principal principal) {
        return new ResponseEntity<>(userService.getUsername(principal.getName()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> newUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<User> edit(@RequestBody User user) {
        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

}
