package id.ac.ui.cs.advprog.afk3.controller;

import id.ac.ui.cs.advprog.afk3.model.Enum.UserType;
import id.ac.ui.cs.advprog.afk3.model.UserEntity;
import id.ac.ui.cs.advprog.afk3.service.UserService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    String createHTML = "userCreate";
    String listHTML = "userList";

    String editHTML = "edituser";

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView userListPage(Model model){
        List<UserEntity> allUsers = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        Optional<UserEntity> user = userService.findByUsername(jwtUser.getUsername());
        System.out.println(user.isPresent() ? user.get().getUsername():null);
        ModelAndView modelAndView = new ModelAndView(listHTML);
        modelAndView.addObject("userlogedin", user.get());
        modelAndView.addObject("users", allUsers);
        return modelAndView;
    }

    @GetMapping(value="/edit/{userId}")
    public ModelAndView editProductPage(Model model, @PathVariable("userId") String username){
        Optional<UserEntity> user = userService.findByUsername(username);
        ModelAndView modelAndView = new ModelAndView(editHTML);
        model.addAttribute("user", user);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editProductPost(@ModelAttribute("user") UserEntity user, Model model){
        System.out.println(user.getUsername());
        if (userService.update(user.getUsername(), user)){
            return new ResponseEntity<>("Edit on "+user.getUsername()+" was SUCCESSful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Edit on "+user.getUsername()+" was FAILED", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-username")
    public ResponseEntity<String> getUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        Optional<UserEntity> user = userService.findByUsername(jwtUser.getUsername());
        return new ResponseEntity<String>(jwtUser.getUsername(), HttpStatus.OK);
    }

    @GetMapping("/get-role")
    public ResponseEntity<String> getRole(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        Optional<UserEntity> user = userService.findByUsername(jwtUser.getUsername());
        return user.map(userEntity ->
                new ResponseEntity<>(userEntity.getType(), HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
