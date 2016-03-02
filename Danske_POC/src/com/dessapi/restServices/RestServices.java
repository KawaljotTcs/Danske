package com.dessapi.restServices;




import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dessapi.bean.BranchLocatorBean;
import com.dessapi.bean.EMICalcBean;
import com.dessapi.util.BranchLocator;
import com.dessapi.util.EMCalc;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


@Path("/rest")
public class RestServices {
	
	
	@POST
	@Path("/emiCalc")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response emiCalculator(EMICalcBean ecb) {
		

		
		EMCalc emc = new EMCalc();
		ecb = emc.calculateEmi(ecb.getPrincipal(), ecb.getRate_of_interest(), ecb.getNumber_of_months());
		
		
		
		Gson gson = new GsonBuilder().create();
       	
		
		return Response.status(200).entity(gson.toJson(ecb)).build();
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/branchLocator")
	public Response branchLocator(@QueryParam("zipcode") String zipcode ,@QueryParam("type") String type)  {
		
		
		
		
		
		BranchLocator bl = new BranchLocator();	
		java.util.List<BranchLocatorBean> list = new ArrayList<>();
		

		System.out.println(zipcode);
		System.out.println(type);
		
			
			
			try {
				list = bl.findBranch(zipcode,type);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	
			
		
		Gson gson = new GsonBuilder().create();
       	
		
		return Response.status(200).entity(gson.toJson(list)).build();
		
	}
	
	
	
}
