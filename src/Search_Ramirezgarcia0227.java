import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormSearch")
public class Search_Ramirezgarcia0227 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Search_Ramirezgarcia0227() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      search(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Contact Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;

         if (keyword.isEmpty()) {
            String selectSQL = "SELECT * FROM MyTableRamirezgarcia0225";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
            String selectSQL = "SELECT * FROM MyTableRamirezgarcia0225";
            //String theUserName = keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
           // preparedStatement.setString(1, theUserName);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("First_Name").trim();
            String lastName = rs.getString("Last_Name").trim();
            String phone = rs.getString("phone").trim();
            String email = rs.getString("email").trim();
            String Address = rs.getString("address").trim();

            if (keyword.isEmpty() || firstName.contains(keyword)
            		|| lastName.contains(keyword)
            		|| phone.contains(keyword)
            		|| email.contains(keyword)
            		|| Address.contains(keyword)) {
               out.println("ID: " + id + ", ");
               out.println("First Name: " + firstName + ", ");
               out.println("Last Name: " + lastName + ", ");
               out.println("Phone: " + phone + ", ");
               out.println("Email: " + email + ", ");
               out.println("Address: " + Address + "<br>");
            }
         }
         out.println("<a href=/webproject-tech-02027-Ramirezgarcia/simpleFormSearch.html>Search Data</a> <br />");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
