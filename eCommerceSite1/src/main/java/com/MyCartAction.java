package com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyCartAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String uname=session.getAttribute("uname").toString();
		List<CartModel> cart = DbCon.getInstance().showCart(uname);
		session.setAttribute("showcart",cart);
		double total=DbCon.getInstance().getTotalCartPrice(cart, uname);
		session.setAttribute("totalprice",total);
		return "goto.cart";
	}
}