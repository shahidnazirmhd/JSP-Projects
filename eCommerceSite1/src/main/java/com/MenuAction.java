package com;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuAction extends Action{
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	HttpSession session=request.getSession();
	String id=request.getParameter("formid");
	String categid=request.getParameter("category");
if(id.equals("showall")) {
	List<ProductModel> product = DbCon.getInstance().getAllProducts();
	session.setAttribute("showproduct",product);
	return id+".jsp";
}
else if(id.equals("categ")) {
		if(categid.equals("shirts")) {
			List<ProductModel> product = DbCon.getInstance().getByCategory(categid);
	    	session.setAttribute("showproduct",product);
	    	return categid+".jsp";
		}else if(categid.equals("pants")) {
			List<ProductModel> product = DbCon.getInstance().getByCategory(categid);
	    	session.setAttribute("showproduct",product);
	    	return categid+".jsp";
		}
		else if(categid.equals("jeans")) {
			List<ProductModel> product = DbCon.getInstance().getByCategory(categid);
	    	session.setAttribute("showproduct",product);
	    	return categid+".jsp";
		}
		else if(categid.equals("tshirts")) {
			List<ProductModel> product = DbCon.getInstance().getByCategory(categid);
	    	session.setAttribute("showproduct",product);
	    	return categid+".jsp";
		}
		else if(categid.equals("footwears")) {
			List<ProductModel> product = DbCon.getInstance().getByCategory(categid);
	    	session.setAttribute("showproduct",product);
	    	return categid+".jsp";
		}
		else if(categid.equals("accessories")) {
			List<ProductModel> product = DbCon.getInstance().getByCategory(categid);
	    	session.setAttribute("showproduct",product);
	    	return categid+".jsp";
		}else{return null;}
		
		}else {
			return id+".jsp";
		}
	
	
}
}
