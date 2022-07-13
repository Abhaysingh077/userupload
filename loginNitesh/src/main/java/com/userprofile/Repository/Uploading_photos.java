package com.userprofile.Repository;

import com.userprofile.Entity.NewUser;
import com.userprofile.Entity.UserUpload;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Uploading_photos extends JpaRepository<UserUpload, Integer>{


	List<UserUpload> findAllByNewUser(NewUser newUser);

	UserUpload findByImageName(String id);

	
}