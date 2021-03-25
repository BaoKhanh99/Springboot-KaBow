package com.bk.kabow.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bk.kabow.entity.Location;
import com.bk.kabow.entity.User;
import com.bk.kabow.entity.userRegistration;
import com.bk.kabow.other.FileUploadUtil;
import com.bk.kabow.entity.Searching;
import com.bk.kabow.service.location.LocationService;
import com.bk.kabow.service.user.UserService;

@org.springframework.stereotype.Controller
@RequestMapping("/kabow")
public class Controller {

	UserService userService;
	LocationService locationService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	User user = new User();
	
	public Controller(UserService service, LocationService locationService) {
		this.userService = service;
		this.locationService = locationService;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		
		Searching searching = new Searching();
		model.addAttribute("searching", searching);
		
		return "index";
	}
	
	@GetMapping("/afterLogin")
	public String afterLogin(Principal principal, Model model, HttpServletRequest request) {
		
		String email = principal.getName();
		
		user = userService.findByEmail(email);
		HttpSession session = request.getSession();
		session.setAttribute("userID", user.getID());
		session.setAttribute("userName", user.getName());
		
		Searching searching = new Searching();
		model.addAttribute("searching", searching);
		
		return "redirect:/kabow/index";
	}
	
	@GetMapping("/afterLogout")
	public String afterLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/kabow/loginPage?logout";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		
		return "login-page";
	}
	
	@GetMapping("/profile")
	public String profileUser(Model model) {
		
		model.addAttribute("infoUser", user);
		 
		
		return "profile-page";
	}
	
	@PostMapping("/editInfo")
	public String editInfo(@ModelAttribute("infoUser")User user, Model model, HttpServletRequest request) {
		int id = this.user.getID();
		System.out.println(id);
		
		this.user = user;
		this.user.setID(id);
		System.out.println(this.user.getAddress());
		
		userService.updateInfo(this.user.getName(), this.user.getYearOfBirth(), this.user.getAddress(), this.user.getPhoneNumber(), id);
		
		HttpSession session = request.getSession();
		session.setAttribute("userID", this.user.getID());
		session.setAttribute("userName", this.user.getName());
		
		model.addAttribute("userInfo", this.user);
		
		return "redirect:/kabow/profile";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam(value = "oldPassword")String oldPassword, @RequestParam(value = "newPassword")String newPassword, @RequestParam(value = "reNewPassword")String reNewPassword) {
		
		if (passwordEncoder.matches(oldPassword, this.user.getPassword())) {
			
			int id = this.user.getID();
			
			String password = passwordEncoder.encode(newPassword);
			
			System.out.println("new pass: "+newPassword+ " id: "+id+" encode: "+password);
			
			userService.changePassword(password, id);
			
			return "redirect:/kabow/profile";
		}
		
		return "redirect:/kabow/profile";	
	}
	
	@GetMapping("/showRegistration")
	public String showRegistration(Model model) {
		
		userRegistration userRegistration = new userRegistration();
		model.addAttribute("user", userRegistration);
		
		return "registration-page";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute("user")userRegistration userRegistration) {
		
		if (userRegistration.getPassword().equals(userRegistration.getRePassword())) {
			userService.registration(userRegistration);;
			return "redirect:/kabow/loginPage";
		}
		else {
			return "redirect:/kabow/showRegistration?failure";		
			}
	}
	
	
	
	@GetMapping("/searchLocation")
	public String searchLocation(@ModelAttribute("searching") Searching search, Model model) {
		
		int People = Integer.valueOf(search.getPeople()) ;
		int People1 = 0, People2 = 0;
		
		if ( People>=6) {
			People1 = 6; People2 = 12;
		} 
		else if (People >= 3 && People < 6) {
			People1 = 3; People2 = 6;
		}
		else if(People < 3 && People >= 1){
			People1 = 1; People2 = 3;
		}
		
		List<Location> listLocation = locationService.findByLocationAndCategory(search.getLocation(),search.getCategory(), People1, People2);
		
		if (listLocation.isEmpty()) {
			System.out.println("oc cho");
		} else {
			model.addAttribute("locationList", listLocation);
		}		
		return "search-page";
	}

	
	@GetMapping("/information-place/{locationID}")
	public String informationPlace(@PathVariable("locationID") int LocationID, Model model) {

		Location location = new Location();
		
		location = locationService.findById(LocationID);
		String[] Image = location.getImage().split(", ");
		
		model.addAttribute("thelocation", location);
		model.addAttribute("ImageLocation", Image);
		
		return "information-place";
	}
	
	@GetMapping("/admin/home")
	public String listUser(Model theModel) {
		List<User> userList = userService.findAll();
		theModel.addAttribute("theUserList",userList);
		
		List<Location> locationList = locationService.findAll();
		theModel.addAttribute("theLocationList", locationList);
		
		Location location = new Location();
		theModel.addAttribute("location", location);
				
		return "admin/home-page-admin";
	}
	
	@PostMapping("/admin/addLocation")
	public String addLocation(@ModelAttribute("location") Location location, @RequestParam("images") MultipartFile multipartFile ) throws IOException {
		

		String fileName = multipartFile.getOriginalFilename();
		System.out.println(fileName);
		location.setImage(fileName);
		String uploadDir = "images/";
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		
		
		return "redirect:/kabow/admin/home";
	}
	
}
