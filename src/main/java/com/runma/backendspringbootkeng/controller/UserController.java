package com.runma.backendspringbootkeng.controller;

import com.runma.backendspringbootkeng.entity.User;
import com.runma.backendspringbootkeng.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public User create(@RequestBody User theUser) {
        theUser.setId(0);
        return userService.save(theUser);
    }

    @PutMapping("/{userId}")
    public User update(@PathVariable Integer userId, @RequestBody User theUser) {
        return userService.update(userId, theUser);
    }

//    @GetMapping("/email/{userEmail}")
//    public User getByEmail(@PathVariable String userEmail) {
//        return userService.findByEmail(userEmail);
//    }

//    @PostMapping("/findByEmail")
//    public User getByEmail(@RequestBody Map<String, String> requestBody) {
//        String userEmail = requestBody.get("userEmail");
//        if (userEmail == null || userEmail.isEmpty()) {
//            throw new RuntimeException("Email address cannot be null or empty.");
//        }
//        return userService.findByEmail(userEmail);
//    }

    @GetMapping("/findByEmail")
    public User getByEmail(@RequestParam String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            throw new RuntimeException("Email address cannot be null or empty.");
        }
        return userService.findByEmail(userEmail);
    }


}


