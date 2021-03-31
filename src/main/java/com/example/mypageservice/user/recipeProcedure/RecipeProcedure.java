package com.example.mypageservice.user.recipeProcedure;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecipeProcedure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String recipeProcedureImage;
	private String recipeProcedure;
//
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "procedureId")
	private List<RecipeProcedureFile> RecipeProcedurefile;

}