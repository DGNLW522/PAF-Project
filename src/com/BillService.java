package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Bill;
@Path("/Bills")


public class BillService {
	
	
	Bill billObj = new Bill();

	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return billObj.readBill();
	 }
	
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertBill(
		
 @FormParam("issueDate") String issueDate,
 @FormParam("units") String units,
 @FormParam("balance") String balance,
 @FormParam("amountToPay") String amountToPay,
 @FormParam("totalAmount") String totalAmount)
{
 String output = billObj.insertBill(issueDate,units,balance,amountToPay,totalAmount);
return output;
}
	

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateBill(String sData)
{
//Convert the input string to a JSON object
 JsonObject billObject = new JsonParser().parse(sData).getAsJsonObject();
//Read the values from the JSON object
 String bID = billObject.get("bID").getAsString();
 String issueDate = billObject.get("issueDate").getAsString();
 String units = billObject.get("units").getAsString();
 String balance = billObject.get("balance").getAsString();
 String amountToPay = billObject.get("amountToPay").getAsString();
 String totalAmount = billObject.get("totalAmount").getAsString(); 
 String output = billObj.updateBill( bID,issueDate,units,balance,amountToPay,totalAmount);
return output;
}



@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String sData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(sData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String bID = doc.select("bID").text();
 String output = billObj.deleteBill(bID);
return output;
}
}
