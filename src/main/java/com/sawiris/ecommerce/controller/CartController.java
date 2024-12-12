package com.sawiris.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sawiris.ecommerce.entity.CartItem;
import com.sawiris.ecommerce.exceptions.ResourceNotFoundException;
import com.sawiris.ecommerce.service.cart.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
	
	private final CartService cartService;
	
	@GetMapping("/items")
	public List<CartItem> getAllCartItems()
	{
		return cartService.listAllCartItms();
	}
	
	@PostMapping("/items")
	public CartItem addNewItem(@RequestBody CartItem item)
	{
		return cartService.addNewCartItem(item);
	}
	
	@PutMapping("/items")
	public CartItem updateCartItem(@RequestBody CartItem item)
	{
		return cartService.updateCartItems(item);
	}
	
	
	@DeleteMapping
	public  ResponseEntity<String> emptyCart()
	{
        try {
            cartService.emptyCart();
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
	}
	
	

}
