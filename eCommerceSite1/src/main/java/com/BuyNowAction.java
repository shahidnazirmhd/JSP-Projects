package com;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyNowAction extends Action{
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session=request.getSession();
	String uname=session.getAttribute("uname").toString();
	int id = Integer.parseInt(request.getParameter("id"));
	ProductModel row = DbCon.getInstance().getSingleProduct(id);
	LocalDate dateObj = LocalDate.now();
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	String formattedDate = dateObj.format(myFormatObj);
	int quantity=1;
	DbCon.getInstance().buyNow(row,uname,quantity,formattedDate);
	String pname=session.getAttribute("pagename").toString();
	return pname+".jsp";
}
}
