package com.example.mypageservice.user.recipe;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mypageservice.user.stuff.StuffRecipe;

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

	public void sendsOrder(long recipeId, String recipeName, String category, List<StuffRecipe> recipe) {
		System.out.println("--------------COMMERCE LOG-----------");
		// TODO Auto-generated method stub
		rabbit.convertAndSend("recipe.order", recipeName, category);
	}

}
