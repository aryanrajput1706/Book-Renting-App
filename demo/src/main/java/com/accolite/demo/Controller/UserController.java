package com.accolite.demo.Controller;

import com.accolite.demo.Model.User;
import com.accolite.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}/wallet")
    public void updateWalletBalance(@PathVariable Long id, @RequestParam Double amount)
    {
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userService.updateWalletBalance(user,amount);
    }

}
