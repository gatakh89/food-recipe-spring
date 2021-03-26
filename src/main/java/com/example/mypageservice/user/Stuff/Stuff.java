package com.example.mypageservice.user.Stuff;

import javax.persistence.Entity;

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
public class Stuff {
	@Id
	private String name;

}