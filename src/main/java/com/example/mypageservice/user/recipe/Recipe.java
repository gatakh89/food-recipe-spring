package com.example.mypageservice.user.recipe;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

import com.example.mypageservice.user.recipeprocedure.RecipeProcedure;
import com.example.mypageservice.user.stuff.StuffRecipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recipeId;

	private String recipeName;
	private long userId;

	private String image;
	private String tip;
	private String Explanation;
	private String category;
	private String dataUrl;

	@OneToMany
	@JoinColumn(name = "recipeId")
	private List<RecipeFile> Recipefile;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stuffRecipeId")
	private List<StuffRecipe> StuffRecipe;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "procedureId")
	private List<RecipeProcedure> recipeProcedure;

}