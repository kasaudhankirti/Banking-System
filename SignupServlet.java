import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SignupServlet extends HttpServlet
{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
	    PrintWriter pw = res.getWriter();
	    String name = req.getParameter("name");
	    String pass = req.getParameter("password");
	    String email = req.getParameter("email");
	    String contact = req.getParameter("contact");
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
            	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");

		//Class.forName("com.mysql.cj.jdbc.Driver");
		
	        // String url = "jdbc:mysql://localhost:3306/ducatproject";
	         //String userName = "root";
	        // String password = "root";
	
	        //Connection con = DriverManager.getConnection(url, userName, password);

	        PreparedStatement ps = con.prepareStatement("insert into employee values(?,?,?,?)");
	        ps.setString(1, name);
	        ps.setString(2, pass);
	        ps.setString(3, email);
	        ps.setString(4, contact);
	        ps.executeUpdate();
	        RequestDispatcher dispatcher = req.getRequestDispatcher("Login.html");
	        dispatcher.include(req, res);
	        pw.println("<br>Hello " + name + " you are registered successfully");
	        con.close();
	        ps.close();
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

}