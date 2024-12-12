package com.sawiris.ecommerce.service.cart;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sawiris.ecommerce.entity.CartItem;
import com.sawiris.ecommerce.repository.CartItemRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

	private final CartItemRepo itemRepo;
	
	public List<CartItem> listAllCartItms()
	{
		return itemRepo.findAll();
	}
	public CartItem updateCartItems(CartItem cartItem)
	{
		if (cartItem.getQty() > 0)
			return itemRepo.save(cartItem);
		else 
		{
			itemRepo.delete(cartItem);
			return null;
		}
	}
	
	public CartItem addNewCartItem(CartItem cartItem)
	{
		return itemRepo.save(cartItem);
	}
	
	public void deleteCartItems(List<CartItem> cartItems)
	{
		itemRepo.deleteAll(cartItems);
	}
	public void emptyCart()
	{
		itemRepo.deleteAll();
	}
	
}
