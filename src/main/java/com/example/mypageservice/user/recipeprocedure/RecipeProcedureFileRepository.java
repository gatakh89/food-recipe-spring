package com.example.mypageservice.user.recipeprocedure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeProcedureFileRepository extends JpaRepository<RecipeProcedureFile, Long> {
	List<RecipeProcedureFile> findByProcedureId(long procedureId);
	// public List<RecipeProcedureFile> findByProcedureId(long procedureId);
}
