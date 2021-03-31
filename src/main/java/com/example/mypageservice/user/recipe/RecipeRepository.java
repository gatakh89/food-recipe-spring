package com.example.mypageservice.user.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
//	public List<Recipe> findById(long id);

	public List<Recipe> findByUserId(long userId);

	public List<Recipe> findByRecipeId(long recipeName);
}