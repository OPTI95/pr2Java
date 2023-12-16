package com.example.demo.controllers;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userDao.index();
        model.addAttribute("users", users);
        return "user/userList";
    }

    @GetMapping("/{id}")
    public String showUserDetails(@PathVariable Long id, Model model) {
        User user = userDao.show(id);
        model.addAttribute("user", user);
        return "user/userDetails";
    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/userForm";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userDao.show(id);
        model.addAttribute("user", user);
        return "user/editUserForm";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        userDao.update(id, updatedUser);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userDao.delete(id);
        return "redirect:/users";
    }
}
