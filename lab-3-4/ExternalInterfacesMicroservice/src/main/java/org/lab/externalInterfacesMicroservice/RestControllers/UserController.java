package org.lab.externalInterfacesMicroservice.RestControllers;

import org.lab.dao.models.User;
import org.lab.externalInterfacesMicroservice.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/new-user")
    public void addUser(@RequestBody User user){
        userService.encodeUser(user);
    }
}
