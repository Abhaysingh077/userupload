package com.userprofile.Repository;

import com.userprofile.Entity.UserUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Uploading_photos extends JpaRepository<UserUpload, Integer>{
}