package com.userprofile.Controller;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.userprofile.Entity.Admin;

import com.userprofile.Entity.NewUser;
import com.userprofile.Entity.UserUpload;
import com.userprofile.Repository.AdminRepo;
import com.userprofile.Repository.Uploading_photos;
import com.userprofile.Repository.UserRepo;
import com.userprofile.Service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepo userRepo;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	Uploading_photos uploading_photos;


//===================For Users===========================
	@PostMapping("/user")
	public void adduser(@RequestBody NewUser user) {
		userService.saveUser(user);

	}

	@GetMapping("/user")
	public List<NewUser> findUsers() {
		return userService.findall();
	}

	@GetMapping("/user/{email}")
	public List<NewUser> findUsersByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}

	@PostMapping("/userlogin")
	public NewUser findUser(@RequestParam String email) {
		NewUser u = userRepo.getByEmail(email);
		if (u != null) {
			return u;
		} else {
			return null;
		}
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.remove(id);
		return "User Deleted";
	}
//================For Admin=====================

	@PostMapping("/admin")
	public void addAdmin(@RequestBody Admin admin) {
		userService.saveAdmin(admin);
	}

	@GetMapping("/admin")
	public List<Admin> findAdmin() {
		return userService.findAllAdmin();
	}

	@PostMapping("/adminlogin")
	public Admin findAdmin(@RequestParam String email, String password) {
		Admin a = adminRepo.getByEmail(email);
		if (a != null && a.getPassword().equals(password)) {
			return a;
		} else {
			return null;
		}
	}
//    ====================For Upload photos=====================
//    
//@PostMapping("/userupload/{email}")
//public void addpost(@RequestBody UserUpload photos, @PathVariable("email") String email){
//    NewUser newUser = userRepo.getByEmail(email);
//    if (newUser.getEmail().equals(email))  	
//    photos.setNewUser(newUser);
//    System.out.println(photos);
//    userService.savePhotos(photos);
//}
//---------------------------------------------------------------

	@PostMapping("/userupload/{email}")
    public UploadFileResponse addpost(@PathVariable("email") String email, 
    		@RequestParam("file") MultipartFile file, @RequestParam(required = false)String description ) {
//        String fileName = uploading_photos.storeFile(file);
		try {
//		InputStream initialStream = file.getInputStream();
//		byte[] buffer = new byte[initialStream.available()];
		String filename = System.currentTimeMillis()+"_"+file.getOriginalFilename(); 
		file.getContentType();
		
		
		NewUser newUser = userRepo.getByEmail(email);
	    if (newUser!=null){  	
	    	UserUpload user = new UserUpload();
	    	user.setCreatedAt(LocalDateTime.now());
	    	user.setImageUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(filename).toUriString());
	    	user.setImageData(file.getBytes());
	    	user.setNewUser(newUser);
	    	user.setDescription(description);
	    	user.setFileType(file.getContentType());
	    	user.setImageName(filename);
	    	uploading_photos.save(user);
	    }
	    	

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

        return new UploadFileResponse("", "",
                file.getContentType(), file.getSize());
    }

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		return fileName;

	}

	@GetMapping("/userupload")
	public List<UserUpload> getAllDetals() {
		return uploading_photos.findAll();
	}
	
	 @GetMapping("/userupload/{email}")
	  public ResponseEntity<List<UserUpload>> getListFiles(@PathVariable ("email") String email) {
////	    List<UserUpload> files = storageService.getAllFiles().map(UserUpload -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .toUriString();
//	      return new ResponseFile(
//	          dbFile.getName(),
//	          fileDownloadUri
//	          dbFile.getType(),
//	          dbFile.getData().length);
	    
	      NewUser newUser = userRepo.getByEmail(email);
	      List<UserUpload> imageDetailList =  uploading_photos.findAllByNewUser(newUser);
	  		
    	
    	
    	
    	
//	    }).collect(Collectors.toList());
	    return ResponseEntity.status(HttpStatus.OK).body(imageDetailList);
	  }
	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    UserUpload fileDB = uploading_photos.findByImageName(id);
	    return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileDB.getFileType()))
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getImageName() + "\"")
	        .body(fileDB.getImageData());
	  }
	}

