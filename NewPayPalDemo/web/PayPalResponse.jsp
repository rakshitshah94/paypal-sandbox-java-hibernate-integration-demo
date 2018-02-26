<%-- 
    Document   : PayPage
    Created on : 11 Mar, 2016, 4:50:38 PM
    Author     : Nishant Singh
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         String TransactionId = request.getParameter("txn_id");
         String grossAmount = request.getParameter("payment_gross");
         String paymentStatus = request.getParameter("payment_status");
         String orderId = request.getParameter("custom");
        %>
        <div>Transaction ID:<%=TransactionId%></div>
        <div> GrossAmount:<%=grossAmount%></div>
        <div>Payment Status:<%=paymentStatus%></div>
        <div>OrderID:<%=orderId%></div>
        <a href="ShowProductServ">Back To Product Page</a>
    </body>
</html>
