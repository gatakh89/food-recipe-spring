package com.example.mypageservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mypageservice.userPurchaseOreder.OrderProductRepository;
import com.example.mypageservice.userPurchaseOreder.PurchaseOrder;
import com.example.mypageservice.userPurchaseOreder.PurchaseOrderRepository;

@RestController
public class UserController {

	private PurchaseOrderRepository PurchaseRepo;
	private UserRepository UserRepo;
//	private RecipeRepository RecipeRepo;

	@Autowired // UserlectureRepository UserlectureRepo, UserPerchaseRepository
				// UserPerchaseRepo, UserRepository UserRepo,
	public UserController(PurchaseOrderRepository PurchaseRepo, UserRepository UserRepo) {
		this.PurchaseRepo = PurchaseRepo;

		this.UserRepo = UserRepo;
//		this.RecipeRepo = RecipeRepo;
	}

	@RequestMapping(value = "/PurchaseOrder", method = RequestMethod.GET)
	public List<PurchaseOrder> getRecipeList() {
		return PurchaseRepo.findAll();

	}

	@RequestMapping(value = "/User", method = RequestMethod.GET)
	public List<User> getUser() {
		return UserRepo.findAll();

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