package com.userprofile.Repository;

import com.userprofile.Entity.NewUser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<NewUser, Integer> {
    List<NewUser> findByEmail(String email);

	NewUser getByEmail(String email);

}
