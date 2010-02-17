<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>  
  <link rel="stylesheet" type="text/css" href="default.css">
</head>

<body>
  <p><a href="controller?action=addCar">[Add Car]</a></p>
  
  <table>
      <tr>
          <th>Action</th>      
          <th>Make</th>      
          <th>Model</th>      
          <th class="model-year">Model Year</th>    
      </tr>        
      
      <c:forEach items='${carList}' var='car'>      
      <tr>
          <td><a href="controller?action=editCar&id=${car.id}">Edit</a></td>      
          <td>${car.make}</td>      
          <td>${car.model}</td>      
          <td class="model-year">${car.modelYear}</td>      
      </tr>    
      </c:forEach>  
      
  </table>
</body>

</html>