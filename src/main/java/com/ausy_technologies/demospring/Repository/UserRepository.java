package com.ausy_technologies.demospring.Repository;


import com.ausy_technologies.demospring.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByUsername(String unsername);

    @Modifying
    @Transactional
    @Query("Update User set firstName= :firstName, lastName= :lastName where id= :id")
    void updateUserName(@Param("firstName") String firstname, @Param("lastName") String lastname, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("Update User set password= :password where id= :id")
    void updateUserPassword(@Param("password") String password, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("Update User set birthday= :date where id= :id")
    void updateUserBirthday(@Param("date")LocalDate date, @Param("id") int id);

}
