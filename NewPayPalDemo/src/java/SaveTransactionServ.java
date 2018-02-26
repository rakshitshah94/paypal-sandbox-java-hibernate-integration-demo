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
import model.TransactionDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Rakshit Shah (rakshitshah1994@gmail.com)
 */
public class SaveTransactionServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String TransactionId = request.getParameter("txn_id");
            String grossAmount = request.getParameter("payment_gross");
            String paymentStatus = request.getParameter("payment_status");
            String orderId = request.getParameter("custom");

            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session ss = sf.openSession();
            Transaction tr = ss.beginTransaction();

            HttpSession hs = request.getSession();
            OrderDetails od = (OrderDetails) hs.getAttribute("OrderDetails");
            String path = "";
            if (paymentStatus.equals("Completed")) {
                System.out.println("Inside Iff Block");
                OrderDetails od2 = new OrderDetails();
                od2.setOrderId(od.getOrderId());
                od2.setAmount(od.getAmount());
                od2.setPid(od.getPid());
                od2.setQty(od.getQty());
                od2.setUserid(od.getUserid());
                od2.setTstatus("Success");

                ss.saveOrUpdate(od2);

                TransactionDetails td = new TransactionDetails();
                td.setTransactionID(request.getParameter("txn_id"));
                td.setStatus(paymentStatus);
                td.setOrderID(od2);
                ss.save(td);
                
                tr.commit();
                path="PayPalResponse.jsp";
            } else {
                TransactionDetails td = new TransactionDetails();
                td.setTransactionID(request.getParameter("txn_id"));
                td.setTransactionID(paymentStatus);
                td.setOrderID(od);
                ss.save(td);

                tr.commit();
                path="PaymentFailed.jsp";
            }

            RequestDispatcher rd = request.getRequestDispatcher(path);
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
