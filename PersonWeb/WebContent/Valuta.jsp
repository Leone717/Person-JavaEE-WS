<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Valuta v�lt�</title>
</head>
<body>
	<form action="Result.jsp">
		<table>
			<tr>
				<td><input maxlength="10" name="amount" value="10" /></td>
				<td>�sszeg (HUF)</td>
			</tr>
			<tr>
				<td><select name="currency">
						<option>EUR</option>
						<option>USD</option>
				</select></td>
				<td>C�l valuta</td>
			</tr>
		</table>
		<br>
		<br> <input name="convert" type="submit" value="CONVERT" />
	</form>

</body>
</html>