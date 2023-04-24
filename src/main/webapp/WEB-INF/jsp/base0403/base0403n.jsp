<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0403N"/>
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
                            <li class="breadcrumb-item2">角色管理</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">角色管理-BASE0403N</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <div class="container formButtonDiv">
                        <button id="addBtn" type="button" class="btnwhite">
                            新增&nbsp;<i class="fas fa-user-plus"></i>
                        </button>
                    </div>
                    <form:form action="" modelAttribute="base0403" id="sessionForm">
                        <fieldset>
                            <legend>角色管理</legend>
                            <table class="caption inquiry" id="dataTable">
                                <thead class="tbhead">
                                <tr>
                                    <td class="text-center" width="20%">角色ID</td>
                                    <td class="text-center" width="20%">角色名稱</td>
                                    <td class="text-center" width="20%">角色說明</td>
                                    <td class="text-center" width="40%">操作</td>
                                </tr>
                                </thead>
                                <tbody class="tbbody">
                                <c:forEach var="data" items="${base0403.base0403Cases}" varStatus="loop">
                                    <tr>
                                        <td class="text-center"><c:out value="${data.roleId}"/></td>
                                        <td class="text-center"><c:out value="${data.roleName}"/></td>
                                        <td class="text-center"><c:out value="${data.roleDesc}"/></td>
                                        <td class="text-center">
                                            <button type="button" class="btnwhite updBtn" value="${loop.index}">
                                                修改&nbsp;<i class="fas fa-user-edit"></i>
                                            </button>
                                            <button type="button" class="btnwhite delBtn" value="${loop.index}">
                                                刪除&nbsp;<i class="fas fa-trash-alt"></i>
                                            </button>
                                            <button type="button" class="btnwhite authBtn" value="${loop.index}">
                                                權限管理&nbsp;<i class="fas fa-cog"></i>
                                            </button>
                                        </td>
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>

                            <hr>
                            <div class="note-area">
                                <div>
                                    <font color="#FF0000">※注意事項：</font>
                                </div>
                                <div class="note-text">
                                    1.&nbsp;<font color="#FF0000">＊</font>為必填欄位。<br>
                                </div>
                            </div>
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
        
        $('#addBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0403_enterAdd.action" />').submit();
        });

        $(".updBtn").click(function () {
            $("#listIndex").val($(this).val());
            $('#sessionForm').attr('action', '<c:url value="/base0403_enterUpd.action" />').submit();
        });

        $(".delBtn").click(function () {
            $("#listIndex").val($(this).val());
            showConfirm('請確認是否刪除？', del);
        });

        function del() {
            $('#sessionForm').attr('action', '<c:url value="/base0403_delete.action" />').submit();
        }

        $(".authBtn").click(function () {
            $("#listIndex").val($(this).val());
            $('#sessionForm').attr('action', '<c:url value="/base0403_enterAuth.action" />').submit();
        });
    });
</script>
</body>
</html>
