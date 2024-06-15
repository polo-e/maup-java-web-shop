<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<%@ page isELIgnored = "false" %>

<div id="content">
    <div style="text-align: center;">
        <c:if test="${empty sessionScope.user && sessionScope.isShowAntiBotForm != true}">
            <table border="0">
                <tr>
                    <td>
                        <form action="./registration" method="post">
                            <center>
                                <table border="0">
                                    <tr>
                                        <td>ім'я:</td>
                                        <td><input value="${sessionScope.name}" type="text" name="name"></td>
                                    </tr>
                                    <tr>
                                        <td>логін:</td>
                                        <td><input value="${sessionScope.login}" type="text" name="login"></td>
                                    </tr>
                                    <tr>
                                        <td>пароль:</td>
                                        <td><input value="${sessionScope.password}" type="password" name="password">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>повторіть пароль:</td>
                                        <td><input value="${sessionScope.retypepassword}" type="password"
                                                   name="retypepassword"></td>
                                    </tr>
                                    <tr>
                                        <td>регіон:</td>
                                        <td>
                                            <select name="address">
                                                <option ${sessionScope.address==
                                                'UA' ? 'selected' : ''} value="UA">UA</option>
                                                <option ${sessionScope.address==
                                                'EN' ? 'selected' : ''} value="EN">EN</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>стать:</td>
                                        <td>Male<input ${empty sessionScope.sex || sessionScope.sex== 'Male' ? 'checked' :
                                            ''} value="Male" type="radio" name="sex">
                                            Female<input ${sessionScope.sex== 'Female' ? 'checked' : ''} value="Female"
                                            type="radio" name="sex">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>коментар:</td>
                                        <td><textarea name="comment" cols="20"
                                                      rows="10">${sessionScope.comment}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right"><input type="submit" value="send"></td>
                                    </tr>
                                </table>
                            </center>
                        </form>
                    </td>
                    <td>
                        <label><font color="orange">${sessionScope.errorMessage}</font></label>
                    </td>
                </tr>
            </table>
        </c:if>

        <c:if test="${not empty sessionScope.user}">
            <h1>Hello, ${user.name}</h1>
            <form action="./logout" method="post"><input type="submit" value="logout"/></form>
        </c:if>

        <c:if test="${sessionScope.isShowAntiBotForm == true}">
            <form action="./registration">
                <label><span style="color: brown; ">Approve that you are not a bot!</span></label>
                <br/>
                <div class="field">
                    <label><span style="color: orange; ">${sessionScope.generatedLetter}</span></label>
                    <div class="input"><input type="text" name="letters" placeholder="type the letters" value=""
                                              id="letters"/></div>
                </div>
                <div class="submit">
                    <button type="submit" formmethod="post">Send</button>
                </div>
            </form>
        </c:if>
    </div>
</div>
<%@ include file="includes/footer.jsp"%>