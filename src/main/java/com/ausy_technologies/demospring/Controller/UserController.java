package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addRole")
    public Role saveRole(@RequestBody Role role) {
        Role roleAdded = this.userService.saveRole(role);
        return roleAdded;
    }

    @PostMapping("/addUser")
    public User saveUser(@RequestBody User user) {
        User userAdded = this.userService.saveUser(user);
        return userAdded;
    }

    @PostMapping("/addUserEntity")
    public ResponseEntity<User> addUsers (@RequestBody User user){
        User newUser = this.userService.saveUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Custom-Header","Add User");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(newUser);
    }

    @PostMapping("/addUser2/{idRole}")
    public User saveUser2(@RequestBody User user, @PathVariable int idRole)
    {
        return this.userService.saveUser2(user,idRole);

    }

    @PostMapping("/addUser3/{roleList}")
    public User saveUser3(@RequestBody User user , @PathVariable List<Role> roleList)
    {
        return this.userService.saveUser3(user,roleList);
    }

    @GetMapping("/findRoleBy/{id}")
    public Role findRoleById(@PathVariable int id)
    {
  return this.userService.findRoleById(id);
    }

    @GetMapping("/findAllRoles")
    public List<Role> findAllRoles()
    {
        return  userService.findAllRoles();
    }

    @GetMapping("/findAllUsers")
    public List<User> findAllUsers()
    {
        return this.userService.findAllUsers();
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);
    }

    @PutMapping("/updateUserById/{id}/{roleList}")
    public User updateUserById(@RequestBody User user, @PathVariable int id, @PathVariable List<Role> roleList){
        return userService.updateUserById(id, user, roleList);
    }

    @PutMapping("/updateUserName/{id}")
    public void updateUserName(@RequestBody Map<String, String> map, @PathVariable int id){
        userService.updateUserName(map.get("firstName"), map.get("lastName"), id);
    }

    @PutMapping("/updateUserPassword/{id}")
    public void updateUserPassword(@RequestBody Map<String, String> map, @PathVariable int id){
        userService.updateUserPassword(map.get("password"), id);
    }


}
