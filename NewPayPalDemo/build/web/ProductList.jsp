<%-- 
    Document   : ProductList
    Created on : 11 Mar, 2016, 1:25:26 PM
    Author     : Rakshit shah (rakshitshah1994@gmail.com)
--%>

<%@page import="model.ProductTable"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (request.getAttribute("pList") != null) {
                ArrayList<ProductTable> pList = (ArrayList<ProductTable>) request.getAttribute("pList");
        %>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Buy</th>
            </tr>

            <% for (ProductTable pt : pList) { %>
            <tr>
                <td><%=pt.getPid()%></td>
                <td><%=pt.getPname()%></td>
                <td>$<%=pt.getPrate()%></td>
                <td><a href="ProceedToPayServ?id=<%=pt.getPid()%>">Click</a></td>
            </tr>
            <%  }%>
        </table>
       <% }
        %>
    </body>
</html>
