package com.sawiris.ecommerce.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NotNull
	String name;
	String description;
	int inventory;
	
	
	@JsonIgnore
	LocalDateTime deletedAt = null;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "brand_id")
	Brand brand;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "category_id")
	Category category;

	public Product(long id, String name, String description, int inventory, Brand brand,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.inventory = inventory;
		this.deletedAt = null;
		this.brand = brand;
		this.category = category;
	}
	
	 

}
