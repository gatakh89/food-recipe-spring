package com.example.mypageservice.user.RecipeProcedure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeProcedureRepository extends JpaRepository<RecipeProcedure, Long> {
//	public List<Recipe> findById(long id);
//
//	public List<RecipeProcedure> findById(long Id);

}
