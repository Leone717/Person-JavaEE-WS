<%@page import="hu.leone.CounterSessionBean"%>
<%@page import="hu.leone.CurrencySessionBean"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="hu.leone.CurrencySessionBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<body>
<%
	double HUFamount = Double.parseDouble(request.getParameter("amount"));
	out.print("Amount = " + HUFamount);
%>
<br>
<%
 
	String toCurrency = request.getParameter("currency");
	out.print("To currency = " + request.getParameter("currency")); 

	/* EJB hivas */
	CurrencySessionBean ejb = null;
	InitialContext ctx = new InitialContext();
	ejb = (CurrencySessionBean)ctx.lookup("java:global.PersonEAR.PersonEJB.CurrencySessionBean!hu.leone.CurrencySessionBean");
	
	double result = ejb.convertCurrency(HUFamount, toCurrency);
	
%>
<br>
<!--  -->

<br>
<%
	/* Counter bean */
	CounterSessionBean counterEjb = (CounterSessionBean)ctx.lookup("java:global.PersonEAR.PersonEJB.CounterSessionBean");
	counterEjb.function();

%>
Counter=<%=counterEjb.getCounter()%>
<br>
Last call time=<%=counterEjb.getLastCallTime()%>

<%
	/* EJB hivas */
	int i = 1;
	for (String s:counterEjb.getCounterList()) {
		out.println("<br>" + i + ". - " + s);
		i++;
	}
%>
</body>
</html>



