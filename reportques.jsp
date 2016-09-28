<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to go back to Main page  --%>
<br>
<h3><span id="studies">Reported Questions</span></h3><br/>
<a href="admin.jsp?user=${sessionScope.theUser.getName()}" id="back_to_page">&laquo;Back to the Main Page</a><br/>
<br/><br/><br/>


<!-- TODO: Add more code to get the table here.
  -->
  <div class="table-responsive">
  <table class="table" >
        <%--Column Names --%>
        <tr>
            <th>Question</th>
            <th>Action</th>		
        </tr>
        <c:forEach items="${studyList}" var="study">
            <tr>
                <td><c:out value="${study.question}"/></td>
                <td>
                    <form action="request.jsp" method="post">
                        <input type="submit" class="btn btn-primary" formaction="StudyController?action=approve&studyCode=<c:out value='${study.studyCode}'/>" value="Approve">
                        <input type="submit" class="btn btn-primary" formaction="StudyController?action=disapprove&studyCode=<c:out value='${study.studyCode}'/>" value="Dispprove">
                    </form>
                </td>
            </tr> 
        </c:forEach>
        <!-- TODO Add one more for removal and not re -->
        
        <tr> 
            <%-- Second study details --%>
            <td></td>
            <td></td>

        </tr>
        </table>
        </div>
  
  
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>