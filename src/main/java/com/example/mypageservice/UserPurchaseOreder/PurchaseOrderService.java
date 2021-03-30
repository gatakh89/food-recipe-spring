package com.example.mypageservice.UserPurchaseOreder;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderService {

	private PurchaseOrderRepository orderRepo;
	private OrderProductRepository productRepo;

	@Autowired
	public PurchaseOrderService(PurchaseOrderRepository orderRepo, OrderProductRepository productRepo) {
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
	}
//	// 트랜잭션 처리
//	// 커밋이 안 된 데이터도 읽을 수 있게 하겠다.
//	// @Transactional(isolation=Isolation.READ_UNCOMMITTED)
//	@Transactional
//	public CreateResponse createSalesOrder(CreateRequest request) {
//		// 요청 객체를 새로운 엔티티 객체 변환
//		SalesOrder newOrder = SalesOrder.builder()
//								.address(request.getAddress())
//								.amount(request.getAmount())
//								.name(request.getName())
//								.created_by(request.getName())
//								.status("00")
//							.build();
//		//부모 레코드 저장
//		// repo.save(entity) -> entity 객체에 id값이 생성이 됨
//		orderRepo.save(newOrder);
//		// 데이터 베이스에서 실제로 저장된 객체를 다시 조회하여 응답을 돌려줄 거임
//		SalesOrder savedOrder = orderRepo.getOne(newOrder.getId());
//		
//		// 자식 엔티티 객체 목록 초기화 
//		savedOrder.setSalesOrderDetails(new ArrayList<SalesOrderDetail>());
//		
//		// 요청 자식 객체 목록을 엔티티 객체로 변환 및 저장
//		for(CreateRequest.Detail detail : request.getDetails()) {
//			SalesOrderDetail newDetail = 
//					SalesOrderDetail.builder()
//						.salesOrderId(savedOrder.getId())
//						.product(Product.builder()
//									.id(detail.getProductId())									
//									.build())
//						.quantity(detail.getQuantity())
//						.unitPrice(detail.getUnitPrice())
//						.build();
//			// 저장하고 조회한 부모 entity 객체의 자식에 추가
//			savedOrder.getSalesOrderDetails()
//					.add(detailRepo.save(newDetail));
////			detailRepo.save(newDetail);
//		}
//		
//		return new CreateResponse(savedOrder);
//	}

	@RabbitListener(queues = "commerce.purchaseorder")
	public void receiveOrder(PurchaseOrder order) {
		System.out.println("---- SALES LOG -----");
		System.out.println(order);

		PurchaseOrder purchaseOrder = PurchaseOrder.builder().id(order.getId()).orderDate(order.getOrderDate())
				.orderProduct(order.getOrderProduct())

				.build();

		System.out.println(purchaseOrder);
		orderRepo.save(purchaseOrder);
	}
}
