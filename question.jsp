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
<%-- Code to Display Question--%>
<section class="question_section">
    <h3><span class="label label-default" >Question</span></h3>
    <%-- Img tag to display image--%>
    <img src="images/${study.imageURL}" class="img-responsive" height="250" width="250" alt="Tree"/>

<%--Code to rating the Question --%>
    <p class="text-left"><c:out value="${study.description}"/></p>

        <form action="StudyController" method="post">
            <input type="hidden" name="action" value="answer"/>
            <input type="hidden" name="studyCode" value="<c:out value='${study.studyCode}'/>"/>
            <c:forEach items="${study.listOfAnswers}" var="list">
                <div class="radio">
                 <input type="radio" name="number" value="<c:out value='${list}'/>" required><c:out value="${list}"/>
                </div>
            </c:forEach>    
<%-- Code to submit the Rating  --%>
    
         <div class="form-group">
        <div class="col-sm-offset-3 col-sm-4">
        <button type="submit"  class="btn btn-primary">Submit</button>
         </div>
            </div>
            <br/><br/><br/>   
        </form>
        
    
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>