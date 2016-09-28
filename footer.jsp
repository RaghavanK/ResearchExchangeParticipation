<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>
<%-- Section to display description --%>
<% Cookie cookie1=new Cookie("host",request.getServerName());
Cookie cookie2=new Cookie("port",Integer.toString(request.getServerPort()));
cookie1.setPath("/");
cookie2.setPath("/");
response.addCookie(cookie1);
response.addCookie(cookie2);
%>
<section class="copyright">
   &copy; Researchers Exchange Participations
   <% Cookie[] cookies=request.getCookies();Cookie cookie=null;
if(cookies!=null){        
for(int i=1;i<cookies.length;i++){
    cookie=cookies[i];
    out.print(" | " + cookie.getName() + ":"+ cookie.getValue() + " | ");
}}else{
        out.print(" | ");
        }
%>
</section>
</body>
</html>