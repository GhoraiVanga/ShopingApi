package me.suman.shopapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import me.suman.shopapi.entity.User;

import java.util.Collection;

/**
 * Created By Zhu Lin on 3/13/2018.
 */

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);

}
