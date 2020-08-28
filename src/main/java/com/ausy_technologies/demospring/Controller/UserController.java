package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addRole")
    public ResponseEntity<Role> saveRole (@RequestBody Role role) {
        Role newRole = this.userService.saveRole(role);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Add new role");
        return new ResponseEntity<>(newRole, headers, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser (@RequestBody User user){
        User newUser = this.userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Add new user");
        return new ResponseEntity<>(newUser, headers, HttpStatus.OK);
    }

    @PostMapping("/addUser2/{idRole}")
    public ResponseEntity<User> saveUser2(@RequestBody User user, @PathVariable int idRole)
    {
        User newUser = this.userService.saveUser2(user, idRole);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Add new user");
        return new ResponseEntity<>(newUser, headers, HttpStatus.OK);
    }

    @PostMapping("/addUser3/{roleList}")
    public ResponseEntity<User> saveUser3(@RequestBody User user, @PathVariable List<Role> roleList)
    {
        User newUser = this.userService.saveUser3(user, roleList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Add new user");
        return new ResponseEntity<>(newUser, headers, HttpStatus.OK);
    }

    @GetMapping("/findRoleBy/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable int id)
    {
        Role roleFound = this.userService.findRoleById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Find role by id");
        return new ResponseEntity<>(roleFound, headers, HttpStatus.OK);
    }

    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Role>> findAllRoles()
    {
        List<Role> rolesFound = this.userService.findAllRoles();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Find all roles");
        return new ResponseEntity<>(rolesFound, headers, HttpStatus.OK);
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id)
    {
        User userFound = this.userService.findUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Find user by id");
        return new ResponseEntity<>(userFound, headers, HttpStatus.OK);
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> findAllUsers()
    {
        List<User> usersFound = this.userService.findAllUsers();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Find all users");
        return new ResponseEntity<>(usersFound, headers, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Delete user by id");
        return new ResponseEntity<>("The user has been deleted.", headers, HttpStatus.OK);

    }

    @PutMapping("/updateUserById/{id}/{roleList}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable int id, @PathVariable List<Role> roleList){
        User userUpdate = this.userService.updateUserById(id, user, roleList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Update user by id");
        return new ResponseEntity<>(userUpdate, headers, HttpStatus.OK);
    }

    @PutMapping("/updateUserName/{id}")
    public ResponseEntity<User> updateUserName(@RequestBody Map<String, String> map, @PathVariable int id){
        this.userService.updateUserName(map.get("firstName"), map.get("lastName"), id);
        User userUpdate = this.userService.findUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Update user name by user id");
        return new ResponseEntity<>(userUpdate, headers, HttpStatus.OK);
    }

    @PutMapping("/updateUserPassword/{id}")
    public ResponseEntity<User> updateUserPassword(@RequestBody Map<String, String> map, @PathVariable int id){
        this.userService.updateUserPassword(map.get("password"), id);
        User userUpdate = this.userService.findUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Update password by user id");
        return new ResponseEntity<>(userUpdate, headers, HttpStatus.OK);
    }

    @PutMapping("/updateUserBirthday/{id}")
    public ResponseEntity<User> updateUserBirthday(@RequestBody Map<String, String> map, @PathVariable int id){
        String dateString = map.get("birthday");
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, form);
        this.userService.updateUserBirthday(localDate, id);
        User userUpdate = this.userService.findUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header","Update birthday by user id");
        return new ResponseEntity<>(userUpdate, headers, HttpStatus.OK);


    }


}
