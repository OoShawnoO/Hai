package swu.edu.hzd;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="Add")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String good = request.getParameter("good");
        if(good.equals("")){
            good = request.getParameter("goods");
        }
        String intro = request.getParameter("intro");
        float amount = Float.valueOf(request.getParameter("amount"));
        String unit = request.getParameter("unit");
        String date = request.getParameter("date");
        DataItem dataItem = new DataItem();
        dataItem.setUnit(unit);
        dataItem.setIntro(intro);
        dataItem.setGood(good);
        dataItem.setDate(date);
        dataItem.setAmount(amount);
        try {
            DBUtils.Insert("record",dataItem,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("profile.jsp");
    }
}
