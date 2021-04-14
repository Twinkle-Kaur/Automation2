<%@ page import="com.automation2.*,com.microsoft.azure.management.compute.*,org.joda.time.DateTime,java.io.PrintWriter" %>
<% 
	String ip="not found";
	String s1="try";
	DateTime ll= null;
	VirtualMachineSizeTypes si = null ;
	String name = request.getParameter("vm");
	//String rname=(String)request.getAttribute("VM");
	//System.out.print(rname);
	VirtualMachine o=Search.check(name);
	//VirtualMachine p=Search.check(rname);
if(o==null){
	response.sendRedirect("index.jsp");
	
}else if(o!=null){
	 ip=Search.getIP(o);
	 ll=Search.getLastLogin(o);
	 si=Search.getSize(o);
	 s1=Search.getStatus(o);
	 System.out.print(s1);
}
//else //if(p!=null){
//	ip=Search.getIP(p);
	// ll=Search.getLastLogin(p);
	//si=Search.getSize(p);
	// s1=Search.getStatus(p);
//}

%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="author" content="colorlib.com">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet" />
    <link href="css/main.css" rel="stylesheet" />
  </head>
  <body>
    <div class="s002">
      <form action="btn" method="post">
        <fieldset>
          <legend>Virtual Machine</legend>
        </fieldset>
        <div class="inner-form">
          <div class="input-field first-wrap">
            <input id="search" type="text" name="vmname" value=<%=name%> readonly>
          </div>
          <div class="input-field fifth-wrap">
            <div class="icon-wrap">
            </div>
            
            <button  style="background:green" class="btn-search" type="submit"  name="action" value="start" disabled>Start</button>
           
          </div>
          <div class="input-field fifth-wrap">
            <div class="icon-wrap">
            </div>
            <button style="background:red" class="btn-search" type="submit" name="action" value="stop">Stop</button>
          </div>
          <div class="input-field fifth-wrap">
            <button class="btn-search" type="submit" name="action" value="restart">Restart</button>
          </div>
        </div>
         <div class="inner-form">
          <div class="input-field first-wrap">
            <label style="color:white">VM IP</label>
            <input id="search" type="text" value="<%=ip%>" readonly>
          </div>
          <div class="input-field first-wrap">
            <label style="color:white">VM Status</label>
            <input id="search" type="text" value="<%=s1%>" readonly>
          </div>
          <div class="input-field first-wrap">
            <label style="color:white">VM Last Login </label>
            <input id="search" type="text" value="<%=ll%>" readonly>
          </div>
          <div class="input-field first-wrap">
            <label style="color:white">VM Size </label>
            <input id="search" type="text" value="<%=si%>" readonly>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
