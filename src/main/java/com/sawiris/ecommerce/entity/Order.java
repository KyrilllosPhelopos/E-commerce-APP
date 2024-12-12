package com.sawiris.ecommerce.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sawiris.ecommerce.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "\"order\"") // Double quotes to escape reserved keywords in PostgreSQL
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	BigDecimal totalPrice;

	@Enumerated(EnumType.STRING)
	OrderStatus status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	User user;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private java.util.List<OrderItem> items = new java.util.ArrayList<>();

}
