<%-- 
    Document   : reset
    Created on : Apr 16, 2016, 10:01:18 PM
    Author     : Raghavan
--%>

<%@include file="header.jsp" %>
    <br/>
     <c:if test="${requestScope.msg ne null}">
            <div class="infoMsg"><c:out value="${requestScope.msg}"/></div>
     </c:if><br>
    <form class="form-horizontal" action="UserController" method="post">
    
        <input type="hidden" name="action" value="reset">
        <input type="hidden" name="token" value="<c:out value='${param.token}'/>">
        <input type="hidden" name="email" value="<c:out value='${param.email}'/>">
       
        <div class="form-group">
            <label class="col-sm-4 control-label">New Password *</label>
            <div class="col-sm-4">
            <input type="password" class="form-control" name="password" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Confirm New Password *</label>
            <div class="col-sm-4">
            <input type="password" class="form-control" name="confirm_password" required />
            </div>
	</div>
        <div class="col-sm-offset-4 col-sm-10">
            <input type="submit" value="Submit" class="btn btn-primary" >
        </div>
    </form>
    
<%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>
