package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "root");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertBill(String issueDate, String units, String balance, String amountToPay,String totalAmount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting!."; }
	 // create a prepared statement
	 String query = " insert into bills(`bID`,`issueDate`,`units`,`balance`,`amountToPay`,`totalAmount`)"
	 + " values (?, ?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, issueDate);
	 preparedStmt.setString(3, units);
	 preparedStmt.setString(4, balance);
	 preparedStmt.setString(5, amountToPay);
	 preparedStmt.setString(6, totalAmount); 
	 // execute the statement

	 preparedStmt.execute();
	 con.close();
	 String newBill = readBill();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newBill + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

public String readBill()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Issue Date</th><th>Units</th>" +
 "<th>Balance</th>" +
 "<th>Amount To Pay</th>" +
 "<th>Total Amount</th>" + 
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from bills";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String bID = Integer.toString(rs.getInt("bID"));
 String issueDate = rs.getString("issueDate");
 String units = rs.getString("units");
 String balance = rs.getString("balance");
 String amountToPay = rs.getString("amountToPay");
 String totalAmount = rs.getString("totalAmount"); 
 // Add into the html table

 output += "<td>" + issueDate + "</td>";
 output += "<td>" + units + "</td>";
 output += "<td>" + balance + "</td>";
 output += "<td>" + amountToPay + "</td>";
 output += "<td>" + totalAmount + "</td>"; 
//buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + bID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + bID + "'></td></tr>";
}
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the nitems.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updateBill(String bID,String issueDate, String units, String balance, String amountToPay,String totalAmount)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE bills SET issueDate=?,units=?,balance=?,amountToPay=?,totalAmount=?WHERE bID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, issueDate);
preparedStmt.setString(2, units);
preparedStmt.setString(3, balance);
preparedStmt.setString(4, amountToPay);
preparedStmt.setString(5, totalAmount); 
preparedStmt.setInt(6, Integer.parseInt(bID));
// execute the statement
preparedStmt.execute();
con.close();
String newBill = readBill();
output = "{\"status\":\"success\", \"data\": \"" +
newBill + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while Updating the Bill.\"}";
System.err.println(e.getMessage());
}
return output;
}



public String deleteBill(String bID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from bills where bID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(bID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newBill = readBill();
 output = "{\"status\":\"success\", \"data\": \"" +
 newBill + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Bill Details.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}
