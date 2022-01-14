package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="Login")
public class Login extends HttpServlet {
    public static ArrayList<HttpSession> sessions=new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArrayList<User> users = DBUtils.Select_Users();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            for(User user:users){

                if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                    HttpSession session = request.getSession();
                    session.setAttribute("permission",String.valueOf(user.getPermission()));
                    session.setMaxInactiveInterval(3600);
                    sessions.add(session);

                    break;
                }
            }

            if(request.getSession().getAttribute("permission")!=null&&request.getSession().getAttribute("permission").equals("1")){
                response.sendRedirect("dashboard.jsp");
            }
            else{
                response.sendRedirect("login.jsp?flag=false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
