package com.automation2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.InstanceViewStatus;
import com.microsoft.azure.management.compute.PowerState;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.VirtualMachineSizeTypes;
import com.microsoft.rest.LogLevel;

public class Search extends HttpServlet {
	
	public static VirtualMachine check(String VM) {
		VirtualMachine vm1=null;
		try {
		    //final File credFile = new File(System.getenv("AZURE_AUTH_LOCATION"));
			final File credFile = new File("C:\\Users\\iamlabadmin.IAMCYBERLAB\\Desktop\\C\\eclipse-workspace\\com.azure.maven.eclipse\\azureauth.properties");
		    Azure azure = Azure.configure()
		        .withLogLevel(LogLevel.BASIC)
		        .authenticate(credFile)
		        .withDefaultSubscription();
		    	 vm1 = azure.virtualMachines().getByResourceGroup("rg-iamlab", VM);
		    	 if(vm1!=null) {
		    		 return vm1;
		    	 }
		    	 
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		    e.printStackTrace();
		}
		return vm1;
	}
	
	public static String getName(VirtualMachine v) {
		return v.osProfile().computerName();
	}
	public static String getIP(VirtualMachine v) {
		return v.getPrimaryNetworkInterface().primaryPrivateIP();
	}
	public static DateTime getLastLogin(VirtualMachine v) {
		DateTime time= null;
		
			for(InstanceViewStatus status : v.instanceView().statuses()) {
		        System.out.println("    code: " + status.code());
		        System.out.println("    displayStatus: " + status.displayStatus());
		        System.out.println("    message: " + status.message());
		        System.out.println("    time: " + status.time());
		        if(status.time()!=null) {
		        	time=status.time();
		        }
		        
		    }
		
		
		return time;
	}
	public static String getStatus(VirtualMachine v) {
		String display="";
		for(InstanceViewStatus status : v.instanceView().statuses()) {
	        System.out.println("  code: " + status.code());
	        System.out.println("  displayStatus: " + status.displayStatus());
	        System.out.println("  displayStatus: " + status.time());
	        display=status.displayStatus();
	    }
		return display;
	}
	public static VirtualMachineSizeTypes getSize(VirtualMachine v) {
		return v.size();
	}
	//@Override
	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    //{
	//	String VM = request.getParameter("vm");
	//	Search s=new Search();
	//	VirtualMachine o = s.check(VM);
	//	try {
	//		if(o!=null) {
	//		response.setContentType("text/html");
	//		response.sendRedirect("VMInfo.html");
	//		}
	//		else {
	//			response.setContentType("text/html");
	//			PrintWriter out=response.getWriter();
	//			out.println("<script type=\"text/javascript\">");
	//			out.println("alert('Invalid VM name');");
	//			out.println("</script>");
	//			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	//			rd.include(request, response);
	//		}
	//		}
	//	catch(Exception e)
	//	{
	//		System.out.println(e.getMessage());
	//  	       e.printStackTrace();
	//	}
   // }

}
