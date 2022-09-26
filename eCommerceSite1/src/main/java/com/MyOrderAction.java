package com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyOrderAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String uname=session.getAttribute("uname").toString();
		List<OrderModel> order = DbCon.getInstance().showorder(uname);
		session.setAttribute("showorder",order);
		return "goto.orders";
	}
}
