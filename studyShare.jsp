<%@ include file="header.jsp" %>

<section class="question_section">
    <h3><span class="label label-default" >Study</span></h3>
    <%-- Img tag to display image--%>
    <img src="images/${sharedStudy.imageURL}" class="img-responsive" height="250" width="250" alt="Tree"/>
	<br>
<%--Code to rating the Question --%>
    <p class="text-left"><strong>Description: </strong><c:out value="${sharedStudy.description}"/></p>
    <p class="text-left"> <strong>Number of participants: </strong> <c:out value="${sharedStudy.numOfParticipants}"/></p>   
    <p><strong>Answers for the question: </strong></p> 
    <ol>
     <c:forEach items="${sharedStudy.listOfAnswers}" var="list">
     <li><c:out value="${list}"/></li><br>
     </c:forEach>
     </ol>
</section>
<%@include file="footer.jsp" %>