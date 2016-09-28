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
            
            <li><a href="UserController?action=main">Home</a></li>
             <li><a href="StudyController?action=reportques">Reported Questions</a></li>
            
        </c:otherwise>
    </c:choose>    

</nav>
<%-- Section tag is used to write description  --%>
<section class="main">
    <h3>How it Works</h3>
    <p>This site was built to help researchers conduct their user studies</p>
    <p>1 participation = 1 coin</p>
    <p><b>To participate,</b> go to "Participate" section and choose a study to complete</p>
    <p><b>To get participants,</b> submit your study here to start getting Participations. Inorder to do so, you must have enough coins in Your account</p>

</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>