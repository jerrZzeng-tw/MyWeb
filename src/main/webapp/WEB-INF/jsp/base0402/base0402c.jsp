<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0402C"/>
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
                            <li class="breadcrumb-item2">功能項目管理</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">功能項目管理-BASE0402C</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <form:form action="" modelAttribute="base0402" id="sessionForm">

                        <div class="container formButtonDiv">
                            <c:if test="${base0402.status == 'add'}">
                                <button id="addBtn" type="button" class="btnwhite">
                                    新增&nbsp;<i class="fas fa-user-plus"></i>
                                </button>
                            </c:if>
                            <c:if test="${base0402.status == 'upd'}">
                                <button id="updBtn" type="button" class="btnwhite">
                                    修改&nbsp;<i class="fas fa-user-edit"></i>
                                </button>
                            </c:if>
                            <button id="backBtn" type="button" class="btnwhite">
                                返回&nbsp;<i class="fas fa-reply"></i>
                            </button>
                        </div>
                        <fieldset>
                            <legend>功能項目管理</legend>
                            <!------------------------------ 新增 ------------------------------>

                            <c:if test="${base0402.status == 'add'}">
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%" class="star">父功能項選單：</th>
                                        <td width="60%">
                                            <form:select path="addAndUpdCase.itemIdParent">
                                                <form:options items="${base0402.options}"/>
                                            </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">功能項ID：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.itemId" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">功能項名稱：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.itemName" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%" class="star">功能項種類：</th>
                                        <td width="60%">
                                            <form:radiobutton path="addAndUpdCase.type" value="1"/>&nbsp;1:功能列
                                            <form:radiobutton path="addAndUpdCase.type" value="0"/>&nbsp;0:標題列
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">功能項URL：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.url" type="text" class="textinput"
                                                        size="30"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">功能項排序：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.sortOrder" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                            <!------------------------------ 修改 ------------------------------>
                            <c:if test="${base0402.status == 'upd'}">
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%">父功能項選單：</th>
                                        <td width="60%">
                                            <c:out value="${base0402.itemIdParent}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">功能項ID：</th>
                                        <td width="60%">
                                            <c:out value="${base0402.addAndUpdCase.itemId}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">功能項名稱：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.itemName" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%" class="star">功能項種類：</th>
                                        <td width="60%">
                                            <form:radiobutton path="addAndUpdCase.type" value="1"/>&nbsp;1:功能列
                                            <form:radiobutton path="addAndUpdCase.type" value="0"/>&nbsp;0:標題列
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">功能項URL：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.url" type="text" class="textinput"
                                                        size="30"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">功能項排序：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.sortOrder" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
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
                        <form:hidden id="status" path="status"/>

                    </form:form>
                </div>
                <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {

        let status = $('#status').val();
        // default open menu
        $(".sidenav-button").trigger("click");

        $('#addBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0402_add.action" />').submit();
        });

        $('#updBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0402_upd.action" />').submit();
        });

        $('#backBtn').click(function () {
            if (status == "upd") {
                $('#sessionForm').attr('action', '<c:url value="/base0402_query.action" />').submit();
            } else {
                $('#sessionForm').attr('action', '<c:url value="/base0402_enter.action" />').submit();
            }
        });

    });
</script>
</body>
</html>
