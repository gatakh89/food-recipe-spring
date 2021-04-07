package com.example.mypageservice.user.recipe;

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

	public void sendOrder(long recipeId) {
		System.out.println("--------------COMMERCE LOG-----------");
		rabbit.convertAndSend("recipe.order.id", recipeId);

	}

}
