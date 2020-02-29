
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class Insert_Ramirezgarcia0227 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Insert_Ramirezgarcia0227() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String FirstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String phone = request.getParameter("phone");
      String email = request.getParameter("email");
      String Address = request.getParameter("address");

      Connection connection = null;
      String insertSql = " INSERT INTO MyTableRamirezgarcia0225 (id, FIRST_NAME,LAST_NAME,PHONE, EMAIL, ADDRESS) values (default, ?, ?, ?,?,?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, FirstName);
         preparedStmt.setString(2, lastName);
         preparedStmt.setString(3, phone);
         preparedStmt.setString(4, email);
         preparedStmt.setString(5, Address);

         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "CONTACT LIST";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + FirstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //
            "  <li><b>Phone</b>: " + phone + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>Address</b>: " + Address + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject-tech-02027-Ramirezgarcia/simpleFormSearch.html>Search Data</a> <br />");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
