package com.sawiris.ecommerce.service.cart;

import java.util.List;

import com.sawiris.ecommerce.entity.CartItem;

public interface CartService {

	public List<CartItem> listAllCartItms();
	public CartItem updateCartItems(CartItem cartItem);
	public CartItem addNewCartItem(CartItem cartItem);		
	public void deleteCartItems(List<CartItem> cartItems);	
	public void emptyCart();
}
