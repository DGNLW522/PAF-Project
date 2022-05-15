<%@ page import="model.Bill" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Bill Management </h1>
<form id="formItem" name="formItem">
 Issue Date : 
 <input id="issueDate" name="issueDate" type="text" 
 class="form-control form-control-sm">
 <br> Units: 
 <input id="units" name="units" type="text" 
 class="form-control form-control-sm">
 <br> Balance: 
 <input id="balance" name="balance" type="text" 
 class="form-control form-control-sm">
 <br> Amount To Pay: 
 <input id="amountToPay" name="amountToPay" type="text" 
 class="form-control form-control-sm">
 <br> Total Amount: 
 <input id="totalAmount" name="totalAmount" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Bill billObj = new Bill(); 
 	 out.print(billObj.readBill());
 %>
</div>
</div> </div> </div> 
</body>
</html>
