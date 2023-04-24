<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0102Q"/>
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
                            <li class="breadcrumb-item2">完整範例</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">完整範例-BASE0102Q</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <div class="container formButtonDiv">

                        <button id="backBtn" class="btnwhite">
                            返回&nbsp;<i class="fas fa-reply"></i>
                        </button>

                    </div>
                    <form:form action="" modelAttribute="base0102" id="sessionForm">
                        <fieldset>
                            <legend>完整範例</legend>
                            <table class="caption inquiry" id="dataTable">
                                <thead class="tbhead">
                                <tr>
                                    <td class="text-center" width="20%">公司名稱</td>
                                    <td class="text-center" width="15%">負責人姓名</td>
                                    <td class="text-center" width="15%">負責人IDN</td>
                                    <td class="text-center" width="20%">公司EMAIL</td>
                                    <td class="text-center" width="15%">公司成立日期</td>
                                    <td class="text-center" width="15%">操作
                                    </td>
                                </tr>
                                </thead>
                                <tbody class="tbbody">
                                <c:forEach var="data" items="${base0102.base0102qCases}" varStatus="loop">
                                    <tr>
                                        <td class="text-center"><c:out value="${data.companyName}"/></td>
                                        <td class="text-center"><c:out value="${data.owner}"/></td>
                                        <td class="text-center"><c:out value="${data.ownerIdn}"/></td>
                                        <td class="text-center"><c:out value="${data.email}"/></td>
                                        <td class="text-center"><c:out value="${data.chineseIssueDate}"/></td>
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
        
        $('#backBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0102_enter.action" />').submit();
        });

        $(".updBtn").click(function () {
            $("#listIndex").val($(this).val());
            $('#sessionForm').attr('action', '<c:url value="/base0102_enterUpd.action" />').submit();
        });

        $(".delBtn").click(function () {
            $("#listIndex").val($(this).val());
            showConfirm('請確認是否刪除？', del);
        });

        function del() {
            $('#sessionForm').attr('action', '<c:url value="/base0102_delete.action" />').submit();
        }
    });
</script>
</body>
</html>
