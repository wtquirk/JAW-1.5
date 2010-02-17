<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/default.css">
</head>

<body>  
  <form method="post" action="${pageContext.request.contextPath}/controller/buyCar">
  <input type="hidden" name="id" value="${car.id}" />
  <input type="hidden" name="admin" value="${admin}" />
  <table>
    <!-- input fields -->
    <tr>
      <td>Car</td>
      <td><input type="text" name="car" readonly="true" value="${car. modelYear} ${car.make} ${car.model}"/></td>
    </tr>  
    <tr>  
      <td>Price</td>
      <td><input type="text" name="price"/></td>
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