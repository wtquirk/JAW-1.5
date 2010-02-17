<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>  
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/default.css">

</head>

<body>
  <form method="POST" action="j_security_check">
    <table>
      <!-- input fields -->
      <tr>
        <td align="left">User Name:</td>
	<td align="right"><input tabindex="1" type="text" name="j_username"></td>
      </tr>  
      <tr>  
	<td align="left">Password:</td>
	<td align="right"><input tabindex="2" type="password" name="j_password"></td>
      </tr>
    
      <!-- Save/Reset buttons -->
      <tr>
        <td colspan="2">
          <input type="submit" name="save" value="Save" /> 
          &nbsp;&nbsp;
          <input type="reset" name="reset" value="Reset" />
        </td>
      </tr> 
    </table>
  </form>
</body>

</html>