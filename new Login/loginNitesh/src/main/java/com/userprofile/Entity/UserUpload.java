package com.userprofile.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name="UserUpload")
public class UserUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    public UserUpload() {
        super();
    }
        String image_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    

    public UserUpload(int id, String image_name, String image_url, LocalDateTime createdAt, String description,
			NewUser newUser) {
		super();
		this.id = id;
		this.image_name = image_name;
		this.createdAt = createdAt;
		this.description = description;
		this.newUser = newUser;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
@Lob
	byte[] image_url;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    String description;



    public UserUpload(int id, String image_name, byte[] image_url, LocalDateTime createdAt, String description,
			NewUser newUser) {
		super();
		this.id = id;
		this.image_name = image_name;
		this.image_url = image_url;
		this.createdAt = createdAt;
		this.description = description;
		this.newUser = newUser;
	}

	public byte[] getImage_url() {
		return image_url;
	}

	public void setImage_url(byte[] image_url) {
		this.image_url = image_url;
	}

	@Override
	public String toString() {
		return "UserUpload [id=" + id + ", image_name=" + image_name + ", image_url=" + Arrays.toString(image_url)
				+ ", createdAt=" + createdAt + ", description=" + description + ", newUser=" + newUser + "]";
	}

    
	
	@ManyToOne
	NewUser newUser;

	

	public NewUser getNewUser() {
		return newUser;
	}

	public void setNewUser(NewUser newUser) {
		this.newUser = newUser;
	}

	
}
