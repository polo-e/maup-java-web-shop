<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored="false"%>
<div id="content">
    <center>
        <c:if test="${empty sessionScope.cart}">
			<label><font color="orange">Кошик порожній!</font></label>
		</c:if>
        <c:forEach var="list" items="${sessionScope.cart}">
            <table>
                <tr>
                    <td width="250px"><font color="#E5E7E9">${list.key.name}</font></td>
                    <td width="250px">${list.key.category.name}</td>
                </tr>
                <tr>
                    <td><img src="./resources/images/${list.key.id}.webp"
                             height="100px"/></td>
                    <td width="500px">${list.key.description}</td>
                </tr>
                <tr>
                    <td><font color="#E5E7E9">${list.value * list.key.price} грн</font></td>
                    <td>
                        <input id="cnt${list.key.id}" type="text" name="count" value="${list.value}"></input>
                        <input id="del${list.key.id}" type="submit" name="productId" value="Видалити"
                               onclick="del('${list.key.id}')"/>
                        <div style="margin-top: 10px;">
                            <input id="order${list.key.id}" type="submit" name="order" value="Оформити замовлення" style="background-color: lightgreen;"/>
                        </div>
                    </td>
                </tr>
            </table>
        </c:forEach>
    </center>
</div>
<%@ include file="includes/footer.jsp"%>