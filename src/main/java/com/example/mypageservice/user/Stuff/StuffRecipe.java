package com.example.mypageservice.user.Stuff;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StuffRecipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String quantity;
	private String stuffName;

}