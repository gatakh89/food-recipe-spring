package com.example.mypageservice.user.recipeprocedure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecipeProcedureFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String fileName;
	private String contentType;
	private Long procedureId;
//	public String getDataUrl() {
//		return "http://localhost:8080" + "/recipe-files/" + this.id;
//	}

	private String dataUrl;

}
