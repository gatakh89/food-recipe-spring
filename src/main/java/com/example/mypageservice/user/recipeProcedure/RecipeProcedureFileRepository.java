package com.example.mypageservice.user.recipeProcedure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeProcedureFileRepository extends JpaRepository<RecipeProcedureFile, Long> {

//	public List<RecipeProcedureFile> findByRecipeId(long procedureId);
}
