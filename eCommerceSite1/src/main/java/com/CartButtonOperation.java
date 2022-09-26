package com;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartButtonOperation {
	public void process(HttpServletRequest request,HttpServletResponse response) {
		LocalDate dateObj = LocalDate.now();
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			String formattedDate = dateObj.format(myFormatObj);
		HttpSession session=request.getSession();
		Object objuname=session.getAttribute("uname");
		String uname=null;
		if(objuname!=null) {
			uname =(String)objuname;
			}
		String formid=request.getParameter("formid");
		String unformatid=request.getParameter("id");
		int id=0;
		if(unformatid!=null) {
		id = Integer.parseInt(unformatid);
		}
		if(formid.endsWith("inc")) {
			List<CartModel> cart =(List<CartModel>)session.getAttribute("showcart");
			if(cart != null) {
			for (CartModel c : cart) {
				if (c.getId() == id) {
					int quantity = c.getQuantity();
					quantity++;
					DbCon.getInstance().updateCart(id, uname, quantity);
				}
			}}
		}else if(formid.endsWith("dec")) {
			List<CartModel> cart =(List<CartModel>)session.getAttribute("showcart");
			if(cart != null) {
			for (CartModel c : cart) {
				if (c.getId() == id && c.getQuantity() > 1) {
					int quantity = c.getQuantity();
					quantity--;
					DbCon.getInstance().updateCart(id, uname, quantity);
				}
		}}
	}else if(formid.equals("removefromcart")) {
		List<CartModel> cart =(List<CartModel>)session.getAttribute("showcart");
		if(cart != null) {
		for (CartModel c : cart) {
			if (c.getId() == id) {
				DbCon.getInstance().removeFromCart(id, uname);
			}
		}}
		
	}else if(formid.equals("carttoorder")) {
		List<CartModel> cart =(List<CartModel>)session.getAttribute("showcart");
		if(cart != null) {
		for (CartModel c : cart) {
			if (c.getId() == id) {
				DbCon.getInstance().buynRmvCart(c, uname,id, c.getQuantity(), formattedDate);
			}
		}}
		
	}else if(formid.equals("checkout")) {
		 
			ArrayList<CartModel> cart_list = (ArrayList<CartModel>)session.getAttribute("showcart");
			if(cart_list != null) {
				for(CartModel c:cart_list) {
					OrderModel order=new OrderModel();
					order.setId(c.getId());
					order.setName(c.getName());
					order.setCategory(c.getCategory());
					order.setQuantity(c.getQuantity());
					order.setPrice(c.getPrice());
					order.setDate(formattedDate);
					DbCon.getInstance().buynRmvCart(order, uname,c.getId(), c.getQuantity(), formattedDate);
				}
			}
	}else if(formid.equals("cancelorder")) {
		List<OrderModel> orderlist =(List<OrderModel>)session.getAttribute("showorder");
		if(orderlist != null) {
		for (OrderModel c : orderlist) {
			if (c.getOrderID() == id) {
				DbCon.getInstance().cancelOrder(id, uname);
			}
	}}
}
}
}
