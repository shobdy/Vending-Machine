<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Vending Machine</h1>
            <hr style="border: .5px solid gray;" />
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayMenuPage">Menu</a></li>
                </ul>    
            </div>
                
            <!-- Main Page contents start here -->
            
            <div id="error-messages">
                <c:out value="${errorMessage}"/>                
            </div>
                
            <div class="row">
                <div class="col-md-8 container" id="menu-buttons">
                    
                    <c:forEach var="currentItem" items="${menuList}">
                        
                        <div class="col-xs-4 menu-item" style="margin-bottom: 20px;">
                            <a href ="${pageContext.request.contextPath}/selectItem?itemId=${currentItem.itemId}" class="btn btn-default" style="border: 1px solid black; width: 100%;">
                                <h4 style="text-align: left; margin-top: 0px;"><c:out value="${currentItem.itemId}"/></h4>
				<h4><c:out value="${currentItem.itemName}"/></h4>
                                <h4>$<c:out value="${currentItem.itemCost}"/></h4>
				<h4>Quantity Left: <c:out value="${currentItem.itemQty}"/></h4>
                            </a>
                        </div>
                    </c:forEach>
                    
                </div>

                <div class="col-md-4" id="menu-controls">
                    <form class="form-horizontal text-center" role="form">
                        <h3 style="margin: 0px 0px 5px 0px; padding: 0px;" >Total $ In</h3>
                        <div class="form-group" style="margin-bottom: 10px;">
                            <input type="text" id="totalAmt" style="width: 300px; padding: 5px;" disabled placeholder="<c:out value="${formDisplayContent.totalAmtContent}"/>" />
                        </div>
                        <div class="form-group" style="margin-bottom: 10px; margin-top: 2px;">
                            <a href="${pageContext.request.contextPath}/addDollar" id="add-dollar" class="btn btn-default" style="width: 150px" >Add Dollar</a>
                            <a href="${pageContext.request.contextPath}/addQuarter" id="add-quarter"class="btn btn-default" style="width: 150px" >Add Quarter</a>
                        </div>
                        <div class="form-group" style="margin-bottom: 10px;">
                            <a href="${pageContext.request.contextPath}/addDime" id="add-dime" class="btn btn-default" style="width: 150px">Add Dime</a>
                            <a href="${pageContext.request.contextPath}/addNickel" id="add-nickel" class="btn btn-default" style="width: 150px">Add Nickel</a>
                        </div>
                    </form>
                    <hr style="border: .5px solid gray; margin-top: 10px; margin-bottom: 5px;" />

                    <form class="form-horizontal text-center" role="form">
                        <h3 style="margin:0px;">Messages</h3>
                        <div class="form-group" style="margin: 5px 0px 0px 0px; padding: 0px;">
                            <input type="text" id="messages" style="width: 300px; text-align: center; margin: 0px; padding: 2px;" disabled placeholder="<c:out value="${formDisplayContent.messagesContent}"/>"/>
                        </div>
                        <div class="form-group" style="margin: 0px; padding: 0px;">
                            <label for="item-name" class="control-label" style="width: 40px; margin: 5px;">Item: </label>
                            <input type="text" id="item-name" style="width: 250px; text-align: center; margin: 5px; padding: 2px;" disabled  placeholder="<c:out value="${formDisplayContent.itemIdContent}"/>"/>
                        </div>
                        <div class="form-group" style="margin: 0px; padding: 0px;">
                            <a href="${pageContext.request.contextPath}/purchaseItem?itemID=${formDisplayContent.itemIdContent}" role="button" id="make-purchase" class="btn btn-default" style="width: 305px; margin: 5px 0px 0px 0px;">Make Purchase</a>
                        </div>
                    </form>
                    <hr style="border: .5px solid gray; margin-top: 10px; margin-bottom: 10px; padding: 0px;" />

                    <form class="form text-center" role="form">
                        <h3 style="margin: 5px 0px;">Change</h3>
                        <div class="form-group" style="margin: 0px;">
                            <input type="text" id="change-text" style="width: 300px; text-align: center; margin: 0px; padding: 2px;" disabled placeholder="<c:out value="${formDisplayContent.changeReturnContent}"/>"/>
                        </div>
                        <div class="form-group" style="margin: 0px;">
                            <a href="${pageContext.request.contextPath}/changeReturn" role ="button" id="change-return" class="btn btn-default" style="margin: 10px;">Change Return</a>
                        </div>
                    </form>
                </div>
            </div>

        </div> <!-- End of container -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
