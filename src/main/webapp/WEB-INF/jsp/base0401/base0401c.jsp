<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0401C"/>
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
                        <div class="legend">帳號管理-BASE0401C</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <form:form action="" modelAttribute="base0401" id="sessionForm">

                        <div class="container formButtonDiv">
                            <c:if test="${base0401.status == 'add'}">
                                <button id="addBtn" type="button" class="btnwhite">
                                    新增&nbsp;<i class="fas fa-user-plus"></i>
                                </button>
                            </c:if>
                            <c:if test="${base0401.status == 'upd'}">
                                <button id="updBtn" type="button" class="btnwhite">
                                    修改&nbsp;<i class="fas fa-user-edit"></i>
                                </button>
                            </c:if>
                            <button id="backBtn" type="button" class="btnwhite">
                                返回&nbsp;<i class="fas fa-reply"></i>
                            </button>
                        </div>
                        <fieldset>
                            <legend>帳號管理</legend>
                            <!------------------------------ 新增 ------------------------------>

                            <c:if test="${base0401.status == 'add'}">
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%" class="star">使用者ID：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.id" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">使用者名稱：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.name" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%" class="star">使用者密碼：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.pw" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">使用者信箱：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.mail" type="text" class="textinput"
                                                        size="30"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">員工編號：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.empNo" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">帳號說明：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.memo" type="text" class="textinput"
                                                        size="30"/>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                            <!------------------------------ 修改 ------------------------------>
                            <c:if test="${base0401.status == 'upd'}">
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%">使用者ID：</th>
                                        <td width="60%">
                                            <c:out value="${base0401.addAndUpdCase.id}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">使用者名稱：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.name" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%">使用者密碼：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.pw" type="text" class="textinput" size="20"
                                                        placeholder="若不輸入 沿用舊密碼"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">使用者信箱：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.mail" type="text" class="textinput"
                                                        size="30"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">員工編號：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.empNo" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">帳號說明：</th>
                                        <td width="60%">
                                            <form:input path="addAndUpdCase.memo" type="text" class="textinput"
                                                        size="30"/>
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
            $('#sessionForm').attr('action', '<c:url value="/base0401_add.action" />').submit();
        });

        $('#updBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0401_upd.action" />').submit();
        });

        $('#backBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0401_enter.action" />').submit();
        });

    });
</script>
</body>
</html>
