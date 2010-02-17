<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/default.css">
</head>

<body>
  <table>
    <tr>
      <th>Make</th>
      <th>Model</th>
      <th class="model-year">Model Year</th>
      <th>Buy Car</th>
    </tr>
    
    <c:forEach items='${carList}' var='car'>
      <tr>
      <td>${car.make}</td>
      <td>${car.model}</td>
      <td class="model-year">${car.modelYear}</td>
      <td><a href="${pageContext.request.contextPath}/controller/viewBuyCarForm?id=${car.id}&admin=false">Buy</a></td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>