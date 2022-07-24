package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String adminsPage() {
        return "admin";
    }
//
//    @GetMapping("/newUser")
//    public String newUser(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("roles", roleService.allRoles());
//        return "newUser";
//    }
//
//    @PostMapping
//    public String createUser(@ModelAttribute("user") User user,
//                             @RequestParam("role") String [] roles) {
//        user.setRoles(roleService.addRoles(roles));
//        userService.addUser(user);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editUser(@PathVariable("id") Long id, Model model,
//                           @ModelAttribute("user") User user,
//                           @RequestParam("editRole") String [] role) {
//        user.setRoles(roleService.addRoles(role));
//        model.addAttribute("user", userService.findById(id));
//        model.addAttribute("roles", roleService.allRoles());
//        return "admin";
//    }
//
//    @PostMapping("/{id}")
//    public String editUser(@ModelAttribute("user") User user,
//                           @RequestParam("editRole") String [] role,
//                           @PathVariable("id") Long id,
//                           @ModelAttribute("password") String password) {
//        userService.edit(id, user, role, password);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("delete/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }

}
