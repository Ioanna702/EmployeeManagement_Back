package com.ausy_technologies.demospring.Service;

import com.ausy_technologies.demospring.Exception.ErrorResponse;
import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Repository.RoleRepository;
import com.ausy_technologies.demospring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User saveUser2(User user, int idRole) {
        Role role = this.roleRepository.findById(idRole).get();
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoleList(roleList);
        return this.userRepository.save(user);
    }

    public User saveUser3(User user, List<Role> roleList) {
        int ok = 1;
        for (Role r : roleList) {
            try {
                findRoleById(r.getId());
            } catch (NullPointerException e) {
                ok = 0;
                System.err.println("Unul din roluri nu a fost gasit.");
                e.printStackTrace();
            }
        }
        if (ok == 1) {
            user.setRoleList(roleList);
            return this.userRepository.save(user);
        } else
            return null;
    }

    public Role findRoleById(int id) {
        return this.roleRepository.findById(id).get();
    }

    public User findUserById(int id) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            return this.userRepository.findById(id);
        } else {
            throw new ErrorResponse("User-ul nu a fost gasit.");
        }
    }

    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUserById(int id) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            this.userRepository.deleteById(id);
        } else {
            throw new ErrorResponse("Nu exista user cu id-ul introdus pentru a fi sters.");
        }

    }

    public User updateUserById(int id, User user, List<Role> roleList) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            user.setId(id);
            int ok = 1;
            for (Role r : roleList) {
                try {
                    findRoleById(r.getId());
                } catch (NullPointerException e) {
                    ok = 0;
                    System.err.println("Unul din roluri nu a fost gasit.");
                    e.printStackTrace();
                }
            }
            if (ok == 1) {
                user.setRoleList(roleList);
                return this.userRepository.save(user);
            } else {
                return userFound;
            }
        } else {
            throw new ErrorResponse("User-ul nu a fost gasit.");
        }
    }

    public void updateUserName(String firstname, String lastname, int id) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            userRepository.updateUserName(firstname, lastname, id);
        } else {
            throw new ErrorResponse("User-ul nu a fost gasit.");
        }
    }

    public void updateUserPassword(String password, int id) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            userRepository.updateUserPassword(password, id);
        } else {
            throw new ErrorResponse("User-ul nu a fost gasit.");
        }

    }

    public void updateUserBirthday(LocalDate date, int id) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            userRepository.updateUserBirthday(date, id);
        } else {
            throw new ErrorResponse("User-ul nu a fost gasit.");
        }
    }

    public User updateRoleList(List<Role> roleList, int id) {
        User userFound = this.userRepository.findById(id);
        if (userFound != null) {
            userFound.setId(id);
            int ok = 1;
            for (Role r : roleList) {
                try {
                    findRoleById(r.getId());
                } catch (NullPointerException e) {
                    ok = 0;
                    System.err.println("Unul din roluri nu a fost gasit.");
                    e.printStackTrace();
                }
            }
            if (ok == 1) {
                userFound.setRoleList(roleList);
                return this.userRepository.save(userFound);
            } else {
                return userFound;
            }

        } else {
            throw new ErrorResponse("User-ul nu a fost gasit.");
        }
    }

}
