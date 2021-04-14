package com.automation2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.rest.LogLevel;

public class Buttons extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String VM = request.getParameter("vmname");
		System.out.println(VM);
		try 
        {
        	String action = request.getParameter("action");
        	System.out.println(action);
        	final File credFile = new File("C:\\\\Users\\\\iamlabadmin.IAMCYBERLAB\\\\Desktop\\\\C\\\\eclipse-workspace\\\\com.azure.maven.eclipse\\\\azureauth.properties");
		    Azure azure = Azure.configure()
		        .withLogLevel(LogLevel.BASIC)
		        .authenticate(credFile)
		        .withDefaultSubscription();
           VirtualMachine vm = azure.virtualMachines().getByResourceGroup("rg-iamlab", VM);
           
           if(action.equalsIgnoreCase("start"))
           {
        	   System.out.println("Enter Start");
        	   vm.start();
               System.out.println("Finish Start");
               response.setContentType("text/html");
           				PrintWriter out=response.getWriter();
           				out.println("<script type=\"text/javascript\">");
           				out.println("alert('VM Started');");
           				out.println("</script>");
           				//request.setAttribute(VM, vm);
           				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
           				rd.include(request, response);
           
           }
           else if(action.equalsIgnoreCase("stop"))
           {
        	   System.out.println("Enter Stop");
        	   System.out.println(VM);
        	   vm.powerOff();
        	   //vm.deallocate();
        	   System.out.println("Finish Stop");
        	   response.setContentType("text/html");
  				PrintWriter out=response.getWriter();
  				out.println("<script type=\"text/javascript\">");
  				out.println("alert('VM Stop');");
  				out.println("</script>");
   				//request.setAttribute("VM", vm);
  				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
  				rd.include(request, response);
           }
           else if(action.equalsIgnoreCase("restart"))
           {
        	   System.out.println("Enter restart");
        	   vm.restart();
        	   System.out.println("Finish restart");
        	   response.setContentType("text/html");
  				PrintWriter out=response.getWriter();
  				out.println("<script type=\"text/javascript\">");
  				out.println("alert('VM Restarted');");
  				out.println("</script>");
  				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
  				rd.include(request, response);
           }
               
          
       } 
       catch (Exception e) 
       {
           System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }
   

}
