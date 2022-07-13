package com.userprofile.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "UserUpload")
public class UserUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "image_name")
	String imageName;

	@Column(name = "file_type")
	String fileType;

	@Lob
	@Column(name = "image_data")
	byte[] imageData;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	String description;

	@ManyToOne
	NewUser newUser;
	
	@Column(name = "image_url")
	String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
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

	public NewUser getNewUser() {
		return newUser;
	}

	public void setNewUser(NewUser newUser) {
		this.newUser = newUser;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UserUpload() {
		super();
	}

	public UserUpload(int id, String imageName, String fileType, byte[] imageData, LocalDateTime createdAt,
			String description, NewUser newUser, String imageUrl) {
		super();
		this.id = id;
		this.imageName = imageName;
		this.fileType = fileType;
		this.imageData = imageData;
		this.createdAt = createdAt;
		this.description = description;
		this.newUser = newUser;
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "UserUpload [id=" + id + ", imageName=" + imageName + ", fileType=" + fileType + ", imageData="
				+ Arrays.toString(imageData) + ", createdAt=" + createdAt + ", description=" + description
				+ ", newUser=" + newUser + ", imageUrl=" + imageUrl + "]";
	}


}
