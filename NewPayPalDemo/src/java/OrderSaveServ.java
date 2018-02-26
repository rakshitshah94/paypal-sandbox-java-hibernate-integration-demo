/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderDetails;
import model.ProductTable;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rakshit shah (rakshitshah1994@gmail.com)
 */
public class OrderSaveServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session ss = sf.openSession();
            Transaction tr = ss.beginTransaction();

            ProductTable pt = new ProductTable();
            pt.setPid(Integer.parseInt(request.getParameter("pid")));

            OrderDetails od = new OrderDetails();
            od.setPid(pt);
            od.setUserid(1);

            double amount = (Integer.parseInt(request.getParameter("qty"))) * (Integer.parseInt(request.getParameter("prate")));
            od.setAmount(amount);
            System.out.println("Rate------" + request.getParameter("prate"));
            od.setQty(Integer.parseInt(request.getParameter("qty")));
            od.setTstatus("Pending");

            ss.save(od);
            tr.commit();

            String pname= request.getParameter("pname");
            String prate = request.getParameter("prate");
            HttpSession hs = request.getSession();
            hs.setAttribute("OrderDetails", od);
            request.setAttribute("pname", pname);
            request.setAttribute("prate", prate);
            RequestDispatcher rd = request.getRequestDispatcher("PaymentPage.jsp");
            rd.forward(request, response);

        } catch (HibernateException e) {
            out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
