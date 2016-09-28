<%-- 
    Document   : forgotPassword
    Created on : Apr 16, 2016, 9:46:42 PM
    Author     : Raghavan
--%>

    <%@include file="header.jsp" %>
    <br/>
     <c:if test="${requestScope.msg ne null}">
            <div class="infoMsg"><c:out value="${requestScope.msg}"/></div>
     </c:if>
    <form class="form-horizontal" action="UserController" method="post">
    
        <input type="hidden" name="action" value="forgot">
        <div class="form-group">
        <label class="col-sm-4 control-label" >Email Address *</label>
            <div class="col-sm-4">
                <input type="email"  class="form-control" name="email" required/>
            </div>
        </div>
        <div class="col-sm-offset-4 col-sm-10">
            <input type="submit" value="Get New Password" class="btn btn-primary" >
        </div>
    </form>
    
<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>
