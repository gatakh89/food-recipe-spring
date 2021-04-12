package com.example.mypageservice.userPurchaseOrder;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPurchaseOrderService {

	private UserPurchaseOrderRepository orderRepo;

	@Autowired
	public UserPurchaseOrderService(UserPurchaseOrderRepository orderRepo, UserOrderProductRepository productRepo) {
		this.orderRepo = orderRepo;
	}

	@RabbitListener(queues = "Market.purchaseOrder")
	public void receiveOrder(UserPurchaseOrder purchaseOrder) {

		UserPurchaseOrder purchaseOrders = UserPurchaseOrder.builder().id(purchaseOrder.getId())
				.userId(purchaseOrder.getUserId()).orderDate(purchaseOrder.getOrderDate())
				.orderProduct(purchaseOrder.getOrderProduct()).build();
		// System.out.println("------------------" + purchaseOrders);
		orderRepo.save(purchaseOrders);
	}
}
