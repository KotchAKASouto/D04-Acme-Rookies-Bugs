<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>



<display:table name="sponsorships" id="row" requestURI="${requestURI }" pagesize="5">

	
	<acme:column property="banner" titleKey="sponsorship.banner" value= "${row.banner}: "/>
	
	<acme:column property="target" titleKey="sponsorship.target" value= "${row.target}: "/>
	
	<acme:column property="cost" titleKey="sponsorship.cost" value= "${row.cost}: "/>
	
	<acme:column property="position.title" titleKey="sponsorship.position.title" value= "${row.position.title}: "/>
	
	<acme:column property="creditCard.number" titleKey="sponsorship.creditCard.number" value= "${row.creditCard.number}: "/>
		
	<acme:url href="sponsorship/provider/edit.do?sponsorshipId=${row.id }" code="sponsorship.edit" />
	
	<acme:url href="sponsorship/provider/display.do?sponsorshipId=${row.id }" code="sponsorship.display" />

	</display:table>
		
	<acme:button name="back" code="sponsorship.back" onclick="javascript: relativeRedir('welcome/index.do');" />




