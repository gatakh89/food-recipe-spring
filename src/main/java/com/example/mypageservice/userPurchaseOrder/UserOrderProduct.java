package com.example.mypageservice.userPurchaseOrder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserOrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	private Long purchaseOrderId;
	private String category;
	private long productId;
	private String productName;
	private String productTitleImage;
	private long productPrice;
	private long productQuantity;
}
