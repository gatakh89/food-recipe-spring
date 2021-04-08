package com.example.mypageservice.userPurchaseOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mypageservice.userLecture.UserLecture;

@Repository
public interface UserPurchaseOrderRepository extends JpaRepository<UserPurchaseOrder, Long> {
	List<UserPurchaseOrder> findByUserId(String userId);
}
