package com.example.mypageservice.userPurchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderProductRepository extends JpaRepository<UserOrderProduct, Long> {

}
