<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="INDEX"/><!-- 每頁JSP的頁面代碼 -->
    <%@ include file="/WEB-INF/jsp/includes/meta.jsp" %>
    <%@ include file="/WEB-INF/jsp/includes/css.jsp" %>
    <%@ include file="/WEB-INF/jsp/includes/js.jsp" %>
</head>

<body>
<div class="web frameDiv">
    <%@ include file="/WEB-INF/jsp/includes/header.jsp" %>
    <div class="wrapper">
        <div class="content">
            <%@ include file="/WEB-INF/jsp/includes/menu.jsp" %>

            <div class="page">

                <!-- 從這裡開始新增程式碼 -->

                <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
            </div>


        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        // default open menu
        $(".sidenav-button").trigger("click");
    });
</script>
</body>
</html>

