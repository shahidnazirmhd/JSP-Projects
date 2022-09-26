package com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCartAction extends Action {
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session=request.getSession();
	int id = Integer.parseInt(request.getParameter("id"));
	ProductModel row = DbCon.getInstance().getSingleProduct(id);
	String uname=session.getAttribute("uname").toString();
	DbCon.getInstance().addToCart(row, uname);
	String pname=session.getAttribute("pagename").toString();
	return pname+".jsp";
}
}
