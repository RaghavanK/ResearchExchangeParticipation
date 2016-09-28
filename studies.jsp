<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
 <%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp?user=Hello,Kim" >Add a new study</a></h3>
 <%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >

<div class="table-responsive">
    <table class="table" >
        <tr>
            <th>Study Name</th>
            <th>Requested Participants</th>     
            <th>Participations</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
               <c:forEach items="${studyList}" var="study">
                   
                    <tr>
                        <td><c:out value="${study.studyName}"/></td>
                        <td><c:out value="${study.requestedParticipants}"/></td>
                        <td><c:out value="${study.numOfParticipants}"/></td>
                        <td><form action="StudyController?action=<c:out value="${study.status}"/>&studyCode=<c:out value="${study.studyCode}"/>" method="post">
                                <button type="submit" class="btn btn-primary" value="<c:out value='${study.status}'/>"><c:out value="${study.status}"/></button></form></td>
                                <td><form action="StudyController?action=edit&studyCode=<c:out value="${study.studyCode}"/>" method="post">
                    <button type="submit" class="btn btn-primary">Edit</button></form></td>
					<td class="plainCell"><button name="fshare" onclick="postShare()">fShare</button></td>
                    </tr>
                    
                 </c:forEach> 
    </table>
</div>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>