
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fieldset>
<legend><spring:message code="dashboard.statscompany" /></legend>
<p><spring:message code="dashboard.statscompany.avg" />: ${avgPC} </p>
<p><spring:message code="dashboard.statscompany.min" />: ${minPC} </p>
<p><spring:message code="dashboard.statscompany.max" />: ${maxPC} </p>
<p><spring:message code="dashboard.statscompany.std" />: ${stdPC} </p>
<br/>
</fieldset>
<br/>

<fieldset>
<legend><spring:message code="dashboard.statsrookie" /></legend>
<p><spring:message code="dashboard.statsrookie.avg" />: ${avgAH} </p>
<p><spring:message code="dashboard.statsrookie.min" />: ${minAH} </p>
<p><spring:message code="dashboard.statsrookie.max" />: ${maxAH} </p>
<p><spring:message code="dashboard.statsrookie.std" />: ${stdAH} </p>
<br/>
</fieldset>
<br/>

<p><spring:message code="dashboard.topcompanies" /></p>

<display:table name="topC" id="row">
</display:table>

<br/>
<p><spring:message code="dashboard.toprookies" /></p>
<display:table name="topH" id="row">
</display:table>

<br/>

<fieldset>
<legend><spring:message code="dashboard.statssalaries" /></legend>
<p><spring:message code="dashboard.statssalaries.avg" />: ${avgS}</p>
<p><spring:message code="dashboard.statssalaries.min" />: ${minS}</p>
<p><spring:message code="dashboard.statssalaries.max" />: ${maxS}</p>
<p><spring:message code="dashboard.statssalaries.std" />: ${stdS}</p>
<br/>
</fieldset>
<br/>
<fieldset>
<legend><spring:message code="dashboard.positions" /></legend>
<p><spring:message code="dashboard.positions.best" />:<c:if test="${empty bP}">
    -
</c:if><c:if test="${not empty bP}">
    ${bP.title}</c:if></p>
<p><spring:message code="dashboard.positions.worst" />: <c:if test="${empty wP}">
    -
</c:if><c:if test="${not empty wP}">
    ${wP.title}
</c:if></p>
<br/>
</fieldset>

<br/>
<fieldset>
<legend><spring:message code="dashboard.statscv" /></legend>
<p><spring:message code="dashboard.statscv.avg" />: ${avgS}</p>
<p><spring:message code="dashboard.statscv.min" />: ${minCH}</p>
<p><spring:message code="dashboard.statscv.max" />: ${maxCH}</p>
<p><spring:message code="dashboard.statscv.std" />: ${stdCH}</p>
<br/>
</fieldset>

<br/>
<fieldset>
<legend><spring:message code="dashboard.statsfinder" /></legend>
<p><spring:message code="dashboard.statsfinder.avg" />: ${avgRF}</p>
<p><spring:message code="dashboard.statsfinder.min" />: ${minRF}</p>
<p><spring:message code="dashboard.statsfinder.max" />: ${maxRF}</p>
<p><spring:message code="dashboard.statsfinder.std" />: ${stdRF}</p>
<br/>
</fieldset>

<br/>
<fieldset>
<legend><spring:message code="dashboard.statsfinder" /></legend>
<p><spring:message code="dashboard.statsfinder.avg" />: ${avgRF}</p>
<p><spring:message code="dashboard.statsfinder.min" />: ${minRF}</p>
<p><spring:message code="dashboard.statsfinder.max" />: ${maxRF}</p>
<p><spring:message code="dashboard.statsfinder.std" />: ${stdRF}</p>
<p><spring:message code="dashboard.statsfinder.ratio" />: ${ratioEF}</p>
<br/>
</fieldset>
<br/>

<fieldset>
<legend><spring:message code="dashboard.statspositionscore" /></legend>
<p><spring:message code="dashboard.avg" />: ${avgSP}</p>
<p><spring:message code="dashboard.min" />: ${minSP}</p>
<p><spring:message code="dashboard.max" />: ${maxSP}</p>
<p><spring:message code="dashboard.std" />: ${stdSP}</p>
<br/>
</fieldset>
<br/>

<fieldset>
<legend><spring:message code="dashboard.scorecompanies" /></legend>
<p><spring:message code="dashboard.avg" />: ${avgSC}</p>
<p><spring:message code="dashboard.min" />: ${minSC}</p>
<p><spring:message code="dashboard.max" />: ${maxSC}</p>
<p><spring:message code="dashboard.std" />: ${stdSC}</p>
<br/>
</fieldset>
<br/>

<p><spring:message code="dashboard.topauditscorecompanies" /></p>

<display:table name="topSC" id="row">
</display:table>
<br/>

<fieldset>
<legend><spring:message code="dashboard.avgtopsalaries" /></legend>
<p><spring:message code="dashboard.avg" />: ${avgTC}</p>
<br/>
</fieldset>
<br/>

<fieldset>
<legend><spring:message code="dashboard.itemprovider" /></legend>
<p><spring:message code="dashboard.avg" />: ${avgSa}</p>
<p><spring:message code="dashboard.min" />: ${minSa}</p>
<p><spring:message code="dashboard.max" />: ${maxSa}</p>
<p><spring:message code="dashboard.std" />: ${stdSa}</p>
<br/>
</fieldset>
<br/>

<p><spring:message code="dashboard.topproviders" /></p>

<display:table name="topP" id="row">
</display:table>
<br/>

<fieldset>
<legend><spring:message code="dashboard.sponsorprovider" /></legend>
<p><spring:message code="dashboard.avg" />: ${avgSPr}</p>
<p><spring:message code="dashboard.min" />: ${minSPr}</p>
<p><spring:message code="dashboard.max" />: ${maxSPr}</p>
<p><spring:message code="dashboard.std" />: ${stdSPr}</p>
<br/>
</fieldset>
<br/>

<fieldset>
<legend><spring:message code="dashboard.sponsorposition" /></legend>
<p><spring:message code="dashboard.avg" />: ${avgSPo}</p>
<p><spring:message code="dashboard.min" />: ${minSPo}</p>
<p><spring:message code="dashboard.max" />: ${maxSPo}</p>
<p><spring:message code="dashboard.std" />: ${stdSPo}</p>
<br/>
</fieldset>
<br/>

<p><spring:message code="dashboard.superiorproviders" /></p>

<display:table name="supP" id="row">
</display:table>
<br/>



<acme:button name="back" code="back" onclick="javascript: relativeRedir('welcome/index.do');" />

