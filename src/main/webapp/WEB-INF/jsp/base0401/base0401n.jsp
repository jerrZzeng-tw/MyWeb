<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0401N"/>
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
                <div class="main">
                    <div class="header">
                        <ol class="breadcrumb2">
                            <li class="breadcrumb-item2">系統管理</li>
                            <li class="breadcrumb-item2">帳號管理</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">帳號管理-BASE0401N</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <div class="container formButtonDiv">
                        <button id="queryBtn" class="btnwhite">
                            查詢&nbsp;<i class="fas fa-search"></i>
                        </button>
                        <button id="addBtn" type="button" class="btnwhite">
                            新增&nbsp;<i class="fas fa-user-plus"></i>
                        </button>

                    </div>
                    <form:form action="" modelAttribute="base0401" id="sessionForm">
                        <fieldset>
                            <legend>查詢條件</legend>
                            <table class="form-table">
                                <tr>
                                    <th>使用者ID：</th>
                                    <td>
                                        <form:input path="id" type="text" class="textinput" size="14"/>
                                    </td>
                                    <th>使用者名稱：</th>
                                    <td>
                                        <form:input path="name" type="text" class="textinput" size="14"/>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                        <fieldset>
                            <legend>帳號管理</legend>
                            <table class="caption inquiry" id="dataTable">
                                <thead class="tbhead">
                                <tr>
                                    <td class="text-center" width="10%">使用者ID</td>
                                    <td class="text-center" width="20%">使用者名稱</td>
                                    <td class="text-center" width="20%">使用者信箱</td>
                                    <td class="text-center" width="10%">員工編號</td>
                                    <td class="text-center" width="20%">帳號說明</td>
                                    <td class="text-center" width="20%">操作
                                </tr>
                                </thead>
                                <tbody class="tbbody">
                                <c:forEach var="data" items="${base0401.base0401qCases}" varStatus="loop">
                                    <tr>
                                        <td class="text-center"><c:out value="${data.id}"/></td>
                                        <td class="text-center"><c:out value="${data.name}"/></td>
                                        <td class="text-center"><c:out value="${data.mail}"/></td>
                                        <td class="text-center"><c:out value="${data.empNo}"/></td>
                                        <td class="text-center"><c:out value="${data.memo}"/></td>
                                        <td class="text-center">
                                            <button type="button" class="btnwhite updBtn" value="${loop.index}">
                                                修改&nbsp;<i class="fas fa-user-edit"></i>
                                            </button>
                                            <button type="button" class="btnwhite delBtn" value="${loop.index}">
                                                刪除&nbsp;<i class="fas fa-trash-alt"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </fieldset>
                        <form:hidden id="listIndex" path="listIndex"/>
                    </form:form>
                </div>
                <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
            </div>
        </div>

    </div>
</div>
<script>
    $(document).ready(function () {

        // default open menu
        $(".sidenav-button").trigger("click");

        var config = getDataTablesConfig();
        config.pageLength = 10; // 預設每頁 10 筆
        var oTable = $('#dataTable').DataTable(config);
        
        $('#queryBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0401_query.action" />').submit();
        });

        $('#addBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0401_enterAdd.action" />').submit();
        });

        $(".updBtn").click(function () {
            $("#listIndex").val($(this).val());
            $('#sessionForm').attr('action', '<c:url value="/base0401_enterUpd.action" />').submit();
        });

        $(".delBtn").click(function () {
            $("#listIndex").val($(this).val());
            showConfirm('請確認是否刪除？', del);
        });

        function del() {
            $('#sessionForm').attr('action', '<c:url value="/base0401_delete.action" />').submit();
        }
    });
</script>
</body>
</html>
