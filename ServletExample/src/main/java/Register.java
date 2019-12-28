import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet { 
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		
		String name =request.getParameter("uname");
		String pass =request.getParameter("pass");
		
		// DB Connection
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","root");
			
			PreparedStatement ps =con.prepareStatement("insert into user values(?,?)");
			ps.setString(1, name);
			ps.setString(2, pass);
			
			int i=ps.executeUpdate();
			
			if(i>0)
			{
				pw.print("You are SuccessFully Registered");
			}
			
		}
		
		catch (ClassNotFoundException e) {
			System.out.println("Coneection Failed");
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		pw.close();
		
	}
	
}