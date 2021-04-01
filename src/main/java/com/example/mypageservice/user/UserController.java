package com.example.mypageservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mypageservice.userLecture.UserLecture;
import com.example.mypageservice.userLecture.UserLectureRepository;
import com.example.mypageservice.userpurchaseOreder.PurchaseOrder;
import com.example.mypageservice.userpurchaseOreder.PurchaseOrderRepository;

@RestController
public class UserController {

	private PurchaseOrderRepository PurchaseRepo;
	private UserRepository UserRepo;
	private UserLectureRepository LectureRepo;

	@Autowired // UserlectureRepository UserlectureRepo, UserPerchaseRepository
				// UserPerchaseRepo, UserRepository UserRepo,
	public UserController(PurchaseOrderRepository PurchaseRepo, UserRepository UserRepo,
			UserLectureRepository LectureRepo) {
		this.PurchaseRepo = PurchaseRepo;
		this.UserRepo = UserRepo;
		this.LectureRepo = LectureRepo;
	}

	@RequestMapping(value = "/purchase-order", method = RequestMethod.GET)
	public List<PurchaseOrder> getPurUserchaseOrderList() {
		return PurchaseRepo.findAll();

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> getUser() {
		return UserRepo.findAll();

	}

	@RequestMapping(value = "/user-lecture", method = RequestMethod.GET)
	public List<UserLecture> getUserLecture() {
		return LectureRepo.findAll();

	}
}
//	public UserController(UserRepository userRepo, RecipeRepository recipeRepo) {
//		// TODO - implement UserController.UserController
//		throw new UnsupportedOperationException();
//	}
//
//	public List<Userlecture> getlectureList() {
//		// TODO - implement UserController.getlectureList
//		throw new UnsupportedOperationException();
//	}
//
//	public List<UserPerchase> getPerchasetList() {
//		// TODO - implement UserController.getPerchasetList
//		throw new UnsupportedOperationException();
//	}
//
//}