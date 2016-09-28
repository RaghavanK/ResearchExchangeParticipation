<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
    
    <c:choose>
        <c:when test="${sessionScope.theUser.getType() == 'participant'}">
         <ul>
        <li>Coins (<span class="count"><c:out value="${sessionScope.theUser.getNumCoins()}"/></span>) </li>
        <li>Participants (<span class="count"><c:out value="0"/></span>) </li>
        <li>Participation (<span class="count"><c:out value="${sessionScope.theUser.getNumParticipations()}"/></span>) </li>
        <li><br></li>
        <li><a href="UserController?action=main">Home</a>  </li>
        <li><a href="StudyController?action=participate" id="participateLink">Participate</a></li>
        <li><a href="StudyController?action=studies" id="myStudiesLink">My Studies</a></li>
        <li><a href="recommend.jsp?user=Hello,Kim" id="recommendLink">Recommend</a></li>
        <li><a href="contact.jsp?user=Hello,Kim" id="contactLink">Contact</a></li>
         </ul>   
        </c:when>
        <c:otherwise>
            <ul>
                <li><a href="UserController?action=main">Home</a></li>
                <li><a href="reportques.jsp">Reported Questions</a></li>
            </ul> 
        </c:otherwise>
    </c:choose>   
</nav>
<%-- Section to display studies and participate in that study--%>
<div>
   
     <h3 class="text-left"><span class="label label-default">Studies</span>
     <span ><a class="label label-default" href="StudyController?action=report">Report History</a></span></h3>
     </div>
     
    <%-- Display the studies in the table --%>
    <%-- Clicking on Participate button displays Question.jsp page where 
            you can rate the question--%>
     <div class="table-responsive">
    <table class="table" >
       
        <%--Column Names --%>
        <tr>
            <th>Study Name</th>
            <th>Image</th>      
            <th>Question</th>
            <th>Action</th>
            <th>Report</th>
        </tr>
        
        <c:forEach items="${studyList}" var="study">     
            
            <tr>
                <td><c:out value="${study.studyName}"/></td>
                <td><img src=images/${study.imageURL} alt="img" width="20" height="20"></td>
                <td><c:out value="${study.question}"/></td>
                <td><form action="StudyController?action=participate&studyCode=<c:out value='${study.studyCode}'/>" method="post"><input type="submit" class="participate_button"
                                                                                value="Participate" /></form></td>
                                                                                <td><form action="StudyController?action=report&studyCode=<c:out value='${study.studyCode}'/>" method="post">
                                                                                        <input type="submit" class="participate_button" value="Report" /></form></td>    
            </tr>      
        </c:forEach> 
    </table>
    </div>


<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>