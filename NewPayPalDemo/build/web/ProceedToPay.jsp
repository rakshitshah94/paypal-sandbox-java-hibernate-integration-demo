<%-- 
    Document   : PayPage
    Created on : 11 Mar, 2016, 1:42:38 PM
    Author     : Rakshit shah (rakshitshah1994@gmail.com)
--%>

<%@page import="model.ProductTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proceed To Pay</title>
    </head>
    <body>
        
        <% 
           ProductTable pt = (ProductTable)request.getAttribute("product");
        %>
        
        <form action="OrderSaveServ">
            <input type="hidden" name="pid" value="<%=pt.getPid()%>" />
            <input type="hidden" name="pname" value="<%= pt.getPname() %>">
            Product Name : <input type="text" name="name" value="<%= pt.getPname()%>" readonly="readonly" disabled="disabled" />
            <input type="hidden" name="prate" value="<%= pt.getPrate()%>">
            Cost         : <input type="text" name="rate" value="$<%= pt.getPrate()%>" readonly="readonly" disabled="disabled" />
            Quantity     : <input type="text" name="qty" value="" />
            <input type="submit" value="Proceed To Pay"  />
        </form>
        
    </body>
</html>
