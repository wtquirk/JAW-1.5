<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/default.css">
</head>

<body>
  <p><a href="${pageContext.request.contextPath}/index.jsp">[Return to Main Page]</a></p>
  
  <form method="post" action="${pageContext.request.contextPath}/controller/runCreditCheck">
  <table>
    <!-- input fields -->
    <tr>
      <td>Name</td>
      <td><input type="text" name="name"/></td>
    </tr>  
    <tr>  
      <td>SSN</td>
      <td><input type="text" name="ssn"/></td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input type="text" name="email"/></td>
    </tr>
    
    <!-- Save/Reset buttons -->
    <tr>
      <td colspan="2">
        <input type="submit" name="save" value="Submit Credit Info" /> 
        &nbsp;&nbsp;
        <input type="reset" name="reset" value="Reset" />
      </td>
    </tr>                
  </table>
  </form>
</body>
</html>