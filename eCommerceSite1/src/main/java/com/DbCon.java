package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbCon {
	private static DbCon instance;
		public DbCon() {
			try {
					Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static synchronized DbCon getInstance(){
	        if (null!=instance){
	            return instance;
	        }else {
	            instance=new DbCon();
	            return instance;
	        }
	    }
		
		public boolean checkUser(String uname,String upass) {
			
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement("select flag from eshopusers where uname=? and upass=?");
				ps.setString(1, uname);
				ps.setString(2, upass);
				ResultSet rs=ps.executeQuery();
				if (rs.next()) {
						return true;
			
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		public boolean checkFlag(String uname,String upass) {
			
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement("select flag from eshopusers where uname=? and upass=?");
				ps.setString(1, uname);
				ps.setString(2, upass);
				ResultSet rs=ps.executeQuery();
				int f=0;
				if(rs.next()) {
					 f=rs.getInt(1);
				}
				if (f==0) {
				 con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
					PreparedStatement psu=con.prepareStatement("update eshopusers set flag=1 where uname=? and upass=?");
					psu.setString(1, uname);
					psu.setString(2, upass);
					psu.executeUpdate();
					return true;
				}else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		public boolean reSetFlag(String uname,String upass) {
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement psr=con.prepareStatement("update eshopusers set flag=0 where uname=? and upass=?");
				psr.setString(1, uname);
				psr.setString(2, upass);
				psr.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		public int register(String fullname,String uname,String upass,String city,long mobileNum) {
			try {
				Connection con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement psc1=con.prepareStatement("select uname from eshopusers where uname=?");
				psc1.setString(1, uname);
				ResultSet res1=psc1.executeQuery();
				if (!res1.next()) {
					con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
					PreparedStatement psc2=con.prepareStatement("select mobileNum from eshopusers where mobileNum=?");
					psc2.setLong(1, mobileNum);
					ResultSet res2=psc2.executeQuery();
					if (!res2.next()) {
						con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
						PreparedStatement psre=con.prepareStatement("insert into eshopusers values (?,?,?,?,?,?)");
						psre.setString(1, fullname);
						psre.setString(2, uname);
						psre.setString(3, upass);
						psre.setString(4, city);
						psre.setLong(5, mobileNum);
						psre.setInt(6, 0);
						int i=psre.executeUpdate();
						return i;
					}else {
						return 3;
					}
				}else {
					return 2;
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		public List<ProductModel> getAllProducts() {
			List<ProductModel> collect = new ArrayList<>();
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement("select * from products");
				ResultSet rs=ps.executeQuery();
				 while (rs.next()) {
					 ProductModel row = new ProductModel();
		                row.setId(rs.getInt("id"));
		                row.setName(rs.getString("name"));
		                row.setCategory(rs.getString("category"));
		                row.setPrice(rs.getDouble("price"));
		                row.setImage(rs.getString("image"));

		                collect.add(row);
		            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return collect;
		}
		public List<ProductModel> getByCategory(String categ){
			List<ProductModel> collect = new ArrayList<>();
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement("select * from products where category=?");
				ps.setString(1, categ);
				ResultSet rs=ps.executeQuery();
				 while (rs.next()) {
					 ProductModel row = new ProductModel();
		                row.setId(rs.getInt("id"));
		                row.setName(rs.getString("name"));
		                row.setCategory(rs.getString("category"));
		                row.setPrice(rs.getDouble("price"));
		                row.setImage(rs.getString("image"));

		                collect.add(row);
		            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return collect;
		}
		public ProductModel getSingleProduct(int id) {
			 ProductModel row = null;
		        try {
		        	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
		        	PreparedStatement ps=con.prepareStatement("select * from products where id=?");
		        	ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();

		            while (rs.next()) {
		            	row = new ProductModel();
		                row.setId(rs.getInt("id"));
		                row.setName(rs.getString("name"));
		                row.setCategory(rs.getString("category"));
		                row.setPrice(rs.getDouble("price"));
		                row.setImage(rs.getString("image"));
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println(e.getMessage());
		        }

		        return row;
		    }
		
		public boolean addToCart(ProductModel row, String uname) {
			
			try {
				Connection con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement psc=con.prepareStatement("select name from productsincart where id=? and uname=?");
				psc.setInt(1, row.getId());
				psc.setString(2, uname);
				ResultSet res=psc.executeQuery();
				if(!res.next()) {
				con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement("insert into productsincart values (?,?,?,?,?,?)");
				ps.setInt(1, row.getId());
				ps.setString(2, row.getName());
				ps.setString(3, row.getCategory());
				ps.setDouble(4, row.getPrice());
				ps.setString(5, uname);
				ps.setInt(6,1);
				ps.executeUpdate();
				return true;
				}else {return false;}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public List<CartModel> showCart(String uname){
			List<CartModel> collect = new ArrayList<>();
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement("select * from productsincart where uname=?");
				ps.setString(1, uname);
				ResultSet rs=ps.executeQuery();
				 while (rs.next()) {
					 CartModel row = new CartModel();
		                row.setId(rs.getInt("id"));
		                row.setName(rs.getString("name"));
		                row.setCategory(rs.getString("category"));
		                row.setPrice(rs.getDouble("price"));
		                row.setQuantity(rs.getInt("quantity"));

		                collect.add(row);
		            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			return collect;
		}
		public void updateCart(int id,String uname,int newquantity) {
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement psu=con.prepareStatement("update productsincart set quantity=? where id=? and uname=?");
				psu.setInt(1, newquantity);
				psu.setInt(2, id);
				psu.setString(3, uname);
				psu.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void removeFromCart(int id, String uname) {
			try {
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement psd=con.prepareStatement("DELETE FROM productsincart WHERE id=? and uname=?");
				psd.setInt(1, id);
				psd.setString(2, uname);
				psd.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public double getTotalCartPrice(List<CartModel> cartList, String uname) {
	        double sum = 0;
	        try {
	            if (cartList.size() > 0) {
	                for (CartModel item : cartList) {
	                	Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
	    				PreparedStatement ps=con.prepareStatement("select price from productsincart where id=? and uname=?");
	                    ps.setInt(1, item.getId());
	                    ps.setString(2, uname);
	                    ResultSet rs = ps.executeQuery();
	                    while (rs.next()) {
	                        sum+=rs.getDouble("price")*item.getQuantity();
	                    }

	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
	        return sum;
	    }
public boolean buyNow(ProductModel row, String uname,int quantity,String date) {
			
			try {
				 Connection con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
				PreparedStatement ps=con.prepareStatement(" INSERT INTO orders (product_id,name,category,quantity,price,uname,date) VALUES (?,?,?,?,?,?,?)");
				ps.setInt(1, row.getId());
				ps.setString(2, row.getName());
				ps.setString(3, row.getCategory());
				ps.setInt(4,quantity);
				ps.setDouble(5, row.getPrice()*quantity);
				ps.setString(6, uname);
				ps.setString(7,date);
				ps.executeUpdate();
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
public List<OrderModel> showorder(String uname){
	List<OrderModel> collect = new ArrayList<>();
	try {
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
		PreparedStatement ps=con.prepareStatement("select * from orders where uname=?");
		ps.setString(1, uname);
		ResultSet rs=ps.executeQuery();
		 while (rs.next()) {
			 OrderModel row = new OrderModel();
                row.setDate(rs.getString("date"));
			    row.setId(rs.getInt("product_id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setQuantity(rs.getInt("quantity"));
                row.setOrderID(rs.getInt("order_id"));
                collect.add(row);
            }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return collect;
}
public void cancelOrder(int id, String uname) {
	try {
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
		PreparedStatement psd=con.prepareStatement("DELETE FROM orders WHERE order_id=? and uname=?");
		psd.setInt(1, id);
		psd.setString(2, uname);
		psd.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public boolean buynRmvCart(ProductModel row, String uname,int id,int quantity,String date) {
	
	try {
		 Connection con=	DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","passmysql");
		PreparedStatement ps=con.prepareStatement(" INSERT INTO orders (product_id,name,category,quantity,price,uname,date) VALUES (?,?,?,?,?,?,?)");
		ps.setInt(1, row.getId());
		ps.setString(2, row.getName());
		ps.setString(3, row.getCategory());
		ps.setInt(4,quantity);
		ps.setDouble(5, row.getPrice()*quantity);
		ps.setString(6, uname);
		ps.setString(7,date);
		int i=ps.executeUpdate();
		if(i==1) {PreparedStatement psd=con.prepareStatement("DELETE FROM productsincart WHERE uname=? and id=?");
		psd.setString(1, uname);
		psd.setInt(2, id);
		psd.executeUpdate();
		return true;
		}else{return false;}
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	
}
}
