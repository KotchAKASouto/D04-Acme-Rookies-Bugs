<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${enlace}" modelAttribute="item">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
	<acme:textbox code="item.name" path="name" obligatory="true"/>

	<acme:textarea code="item.description" path="description" obligatory="true"/>
	
	<acme:textbox code="item.link" path="link" obligatory="true" />
	
	<acme:textbox code="item.pictures" path="pictures"/>
	
	
	
	<br>
	
	<acme:submit name="save" code="item.save" />
	
	<acme:cancel code="item.cancel" url="welcome/index.do" />


</form:form>  