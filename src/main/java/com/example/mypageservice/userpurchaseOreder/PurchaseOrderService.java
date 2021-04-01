package com.example.mypageservice.userpurchaseOreder;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {

	private PurchaseOrderRepository orderRepo;

	@Autowired
	public PurchaseOrderService(PurchaseOrderRepository orderRepo, OrderProductRepository productRepo) {
		this.orderRepo = orderRepo;
	}

	@RabbitListener(queues = "Market.purchaseOrder")
	public void receiveOrder(PurchaseOrder purchaseOrder) {

		PurchaseOrder purchaseOrders = PurchaseOrder.builder().id(purchaseOrder.getId())
				.orderDate(purchaseOrder.getOrderDate()).orderProduct(purchaseOrder.getOrderProduct()).build();
		System.out.println("------------------" + purchaseOrders);
		orderRepo.save(purchaseOrders);
	}
}
