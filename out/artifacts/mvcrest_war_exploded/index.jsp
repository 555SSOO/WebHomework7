<%--
  Created by IntelliJ IDEA.
  User: Vindu
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coupon app</title>

    <link rel="stylesheet" href="style/main.css">
</head>
<body>

<%--Tabela kupona koju cemo popuniti asinhrono sa javascript-om--%>
<h3>Coupons</h3>

<%--<button id="load-coupons-btn">Ucitaj korisnike</button>--%>

<table id="coupon-tbl">
    <colgroup>
        <col/>
        <col/>
        <col class="green"/>
        <col class="red"/>
    </colgroup>
    <thead>
    <tr>
        <th>Product Name</th>
        <th>Store Name</th>
        <th>Price with discount</th>
        <th>Price without discount</th>
        <th>Discount %</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>

<%--Novi redovi--%>
<br><br>
<h3>Add a coupon</h3>
<form id="add-coupon-form">

    <%--add stores from server--%>
    <input type="text" name="productName" placeholder="Product Name">
    <br>
    <input type="number" name="oldPrice" placeholder="Old Price" step="0.01" min="0">
    <br>
    <input type="number" name="newPrice" placeholder="New Price" step="0.01" min="0">
    <br>
    <select name="stores" id="stores-select">
    </select>
    <input type="Submit" value="Submit">
</form>
<script src="js/script.js"></script>
</body>
</html>
