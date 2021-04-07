package com.example.mypageservice.userLecture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mypageservice.user.security.Auth;
import com.example.mypageservice.user.security.Profile;

@RestController
public class UserLectureController {

	private UserLectureRepository LectureRepo;

	@Autowired
	public UserLectureController(UserLectureRepository LectureRepo) {

		this.LectureRepo = LectureRepo;
	}

	@Auth
	@RequestMapping(value = "/user-lecture", method = RequestMethod.GET)
	public List<UserLecture> getUserLecture(HttpServletRequest req) {
		Profile profile = (Profile) req.getAttribute("profile");
		return LectureRepo.findByUserId(profile.getUserId());

	}
}
