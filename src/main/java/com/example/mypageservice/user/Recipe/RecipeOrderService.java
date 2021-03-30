package com.example.mypageservice.user.Recipe;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeOrderService {
	private RabbitTemplate rabbit;

	@Autowired
	public RecipeOrderService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	public void sendOrder(Recipe order) {
		System.out.println("--------------COMMERCE LOG-----------");
		rabbit.convertAndSend("recipe.order", order);

	}

	public void sendsOrder(long id) {
		System.out.println("--------------COMMERCE LOG-----------");
		rabbit.convertAndSend("recipe.order.id", id);

	}
}
