package com.example.mypageservice.user.recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecipeFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String fileName;
	private String contentType;
	private long recipeId;

//	public String getDataUrl() {
//		return "http://192.168.0.29:8080" + "/recipe-files/" + this.id;
//	}
//
	private String dataUrl;

}
