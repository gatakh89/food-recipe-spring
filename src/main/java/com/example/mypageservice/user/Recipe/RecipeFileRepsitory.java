package com.example.mypageservice.user.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeFileRepsitory extends JpaRepository<RecipeFile, Long> {
	List<RecipeFile> findByRecipeId(long recipeId);
}
