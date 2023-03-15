package com.htmlTips.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.ui.Model;
import com.htmlTips.models.User;
import com.htmlTips.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // list user
    @GetMapping("/user")
    public String userPage(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    // create user
    @GetMapping("/user/create-user")
    public String getRegisterPage(){
        return "user/create-user";
    }

    @PostMapping("/user/create-user")
    public String createUser(@RequestParam String username,
                             @RequestParam String password){
        User user = new User(username,password,"user");
        userRepository.save(user);
        return "redirect:/user";
    }

    // delete user
    @PostMapping("/user/delete-user/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id){
        if(!userRepository.existsById(id)){return "redirect:/user/list";}
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/user";
    }

    // edit user
    @GetMapping("/user/edit-user/{id}")
    public String editUser(@PathVariable(value = "id") Long id,Model model){
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user",user);
        return "/user/edit-user";
    }

    @PostMapping("/user/edit-user")
    public String editUser(User user){
        userRepository.save(user);
        return "redirect:/user";
    }

    // change password
    @GetMapping("/user/change-password")
    public String getChangePasswordPage(){
        return "/user/change-password";
    }

    @PostMapping("/user/change-password")
    public String changeUserPassword(@RequestParam Long id,
                                     @RequestParam String password){
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/";
    }

    // user Login
    @GetMapping("/user/login")
    public String getLoginPage(){
        return "/user/login";
    }

    @PostMapping("/user/login")
    public String userLogin(Model model,
                            HttpSession session,
                            @RequestParam String username,
                            @RequestParam String password) {
        Iterable<User> users = userRepository.findAll();

        for (User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                session.setAttribute("currentUserName",user.getUsername());
                session.setAttribute("currentUserId",user.getId());
                session.setAttribute("currentUserPassword",user.getPassword());
                session.setAttribute("currentUserRole",user.getRole());
                return "redirect:/";
            }
        }

        return "/user/login";
    }

    // user logout
    @GetMapping("/user/logout")
    public String userLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
