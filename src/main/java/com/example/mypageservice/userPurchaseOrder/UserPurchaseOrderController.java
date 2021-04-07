package com.example.mypageservice.userPurchaseOrder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mypageservice.user.security.Auth;
import com.example.mypageservice.user.security.Profile;
import com.example.mypageservice.userLecture.UserLecture;
import com.example.mypageservice.userLecture.UserLectureRepository;

@RestController
public class UserPurchaseOrderController {

	private PurchaseOrderRepository PurchaseRepo;

	@Autowired
	public UserPurchaseOrderController(PurchaseOrderRepository PurchaseRepo, UserLectureRepository LectureRepo) {
		this.PurchaseRepo = PurchaseRepo;

	}

//	@RequestMapping(value = "/purchase-order", method = RequestMethod.GET)
//	public List<PurchaseOrder> getPurUserchaseOrderList() {
//		return PurchaseRepo.findAll();
//
//	}
	@Auth
	@RequestMapping(value = "/purchase-order", method = RequestMethod.GET)
	public List<PurchaseOrder> getUserLecture(HttpServletRequest req) {
		Profile profile = (Profile) req.getAttribute("profile");
		return PurchaseRepo.findByUserId(profile.getUserId());

	}

}
