<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0301N"/>
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
                            <li class="breadcrumb-item2">其他範例</li>
                            <li class="breadcrumb-item2">排程範例</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">排程範例-BASE0301N</div>
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
                    <form:form action="" modelAttribute="base0301" id="sessionForm">
                        <fieldset>
                            <legend>查詢條件</legend>
                            <table class="form-table">
                                <tr>
                                    <th>排程名稱：</th>
                                    <td>
                                        <form:input path="jobName" type="text" class="textinput" size="14"/>
                                    </td>
                                    <th>新增人員：</th>
                                    <td>
                                        <form:input path="addId" type="text" class="textinput" size="14"/>
                                    </td>
                                    <th>執行狀態：</th>
                                    <td>
                                     <form:select path="status">
				                        <form:option  label="請選擇..." value="" />
				                        <form:options items="${base0301.statusOptions}" itemLabel="codeName" itemValue="code"/>
				                    </form:select>
                                    </td>
                                </tr>
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
                            
                             <fieldset>
                            <legend>查詢結果頁面</legend>
                            <table class="caption inquiry" id="dataTable">
                                <thead class="tbhead">
                                <tr>
                                    <td class="text-center" width="20%">jobId</td>
                                    <td class="text-center" width="15%">job名稱</td>
                                    <td class="text-center" width="10%">排定時間</td>
                                    <td class="text-center" width="10%">job啟動時間</td>
                                    <td class="text-center" width="10%">job結束時間</td>
                                    <td class="text-center" width="10%">執行狀態</td>
                                    <td class="text-center" width="10%">執行結果</td>
                                    <td class="text-center" width="15%">操作
                                    </td>
                                </tr>
                                </thead>
                                <tbody class="tbbody">
                                <c:forEach var="data" items="${base0301.base0301qCases}" varStatus="loop">
                                    <tr>
                                        <td class="text-center"><c:out value="${data.jobId}"/></td>
                                        <td class="text-center"><c:out value="${data.jobName}"/></td>
                                        <td class="text-center"><c:out value="${data.scheduleTime}"/></td>
                                        <td class="text-center"><c:out value="${data.exeStart}"/></td>
                                        <td class="text-center"><c:out value="${data.exeEnd}"/></td>
                                        <td class="text-center"><c:out value="${data.statusName}"/></td>
                                        <td class="text-center"><c:out value="${data.result}"/></td>
                                        <td class="text-center">
                                            <button type="button" class="btnwhite detailBtn" value="${loop.index}">
                                                明細資料&nbsp;
                                            </button>
                                             <c:if test="${data.status == '1'}">
	                                            <button type="button" class="btnwhite delBtn" value="${loop.index}">
	                                                取消&nbsp;<i class="fas fa-trash-alt"></i>
	                                            </button>
                                            </c:if>
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
            $('#sessionForm').attr('action', '<c:url value="/base0301_query.action" />').submit();
        });

        $('#addBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0301_enterAdd.action" />').submit();
        });
        
        $(".detailBtn").click(function () {
            $("#listIndex").val($(this).val());
            $('#sessionForm').attr('action', '<c:url value="/base0301_enterQuery.action" />').submit();
        });
        
        $(".delBtn").click(function () {
            $("#listIndex").val($(this).val());
            showConfirm('請確認是否取消Job？', del);
        });

        function del() {
            $('#sessionForm').attr('action', '<c:url value="/base0301_delete.action" />').submit();
        }
    });
</script>
</body>
</html>
