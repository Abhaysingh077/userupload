package com.userprofile.Repository;

import com.userprofile.Entity.Admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {

Admin getByEmail(String email);
		
}
