<%-- 
    Document   : PaymentPage
    Created on : 11 Mar, 2016, 3:23:23 PM
    Author     : Rakshit Shah (rakshitshah1994@gmail.com)
--%>

<%@page import="model.OrderDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
    </head>
    <body>
        <h1>Payment Page</h1>
        <%
            if (session.getAttribute("OrderDetails") != null) {
                String pname= request.getParameter("pname");
                String prate= request.getParameter("prate");
                OrderDetails od = (OrderDetails) session.getAttribute("OrderDetails");


        %>
        <form name="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_xclick" />
            
            <input type="hidden" name="item_name" value="<%=pname%>">
            Product : <input type="text" name="pitem_name" value="<%=pname%>" readonly="readonly" disabled="disabled" />
            
            
            <input type="hidden" value="<%=od.getQty()%>" name="quantity">
            Quantity:<input type="text" value="<%=od.getQty()%>" name="pquantity" readonly="readonly" disabled="disabled"/><br>
            
            
            <input type="hidden" value="<%=prate%>" name="amount">
            Price:<input type="text" value="$<%=od.getAmount()%>" name="pamount" readonly="readonly" disabled="disabled" /><br>
            
            <input type="hidden" name="business" value="rakshitshah1994@gmail.com" />
            <input type="hidden" name="rm" value="2" />
            <input type="hidden" name="custom" value="<%=od.getOrderId()%>" />
            <input name = "currency_code" value = "USD" type = "hidden">
            <input type="image" src="https://www.sandbox.paypal.com/en_US/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
            <input type="hidden" name="return" value="http://localhost:8084/NewPayPalDemo/SaveTransactionServ" />
            <input type="hidden" name="cancel_return" value="http://localhost:8084/NewPayPalDemo/paypalResponseCancel.jsp" />
        </form>






        <% } else { %>

        <h3>Select Product First</h3>

        <%}%>


    </body>
</html>
