package com;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String fullname=request.getParameter("fullname");
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		String city=request.getParameter("city");
		long mobileNum=Long.parseLong(request.getParameter("mobileNum"));
		int regsta=DbCon.getInstance().register(fullname, uname, upass, city, mobileNum);
		if(regsta==1) {
			session.setAttribute("message", "registersuccess");
			return "login.message";
		}else if(regsta==2){
			session.setAttribute("message", 2);
			return "register.message";
		}else if(regsta==3){
			session.setAttribute("message", 3);
			return "register.message";		
		}
		else {
			session.setAttribute("message", 4);
			return "register.message";		
		}
	}
}
