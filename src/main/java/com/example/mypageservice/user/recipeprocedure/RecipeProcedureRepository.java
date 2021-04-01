package com.example.mypageservice.user.recipeprocedure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeProcedureRepository extends JpaRepository<RecipeProcedure, Long> {

//	public List<RecipeProcedure> findByprocedureId(long procedureId);

}
