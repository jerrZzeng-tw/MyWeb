<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="acl" uri="http://www.bli.gov.tw/common-acl" %>
<%@ taglib prefix="func" uri="http://www.bli.gov.tw/common-func" %>

<spring:eval var="frameworkUserKey"
             expression="T(tw.gov.idb.base.framework.helper.UserSessionHelper).FRAMEWORK_USER_INFO"/>
<c:set var="frameworkUserData" value="${sessionScope[frameworkUserKey]}"/>

<spring:eval var="userKey" expression="T(tw.gov.idb.base.framework.helper.UserSessionHelper).USER_INFO"/>
<c:set var="userData" value="${sessionScope[userKey]}"/>
