<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0102C"/>
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
                        <div class="legend">完整範例-BASE0102C</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <form:form action="" modelAttribute="base0102" id="sessionForm">

                        <div class="container formButtonDiv">
                            <c:if test="${base0102.status == 'add'}">
                                <button id="addBtn" type="button" class="btnwhite">
                                    新增&nbsp;<i class="fas fa-user-plus"></i>
                                </button>
                            </c:if>
                            <c:if test="${base0102.status == 'upd'}">
                                <button id="updBtn" type="button" class="btnwhite">
                                    修改&nbsp;<i class="fas fa-user-edit"></i>
                                </button>
                            </c:if>
                            <button id="backBtn" type="button" class="btnwhite">
                                返回&nbsp;<i class="fas fa-reply"></i>
                            </button>
                        </div>
                        <fieldset>
                            <legend>完整範例</legend>

                            <table class="form-table mx-auto">
                                <tr>
                                    <th width="40%" class="star">公司名稱：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.companyName" type="text" class="textinput"
                                                    size="14"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%" class="star">負責人姓名：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.owner" type="text" class="textinput" size="14"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%" class="star">負責人IDN：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.ownerIdn" type="text" class="textinput"
                                                    size="14"/>
                                    </td>
                                </tr>

                                <tr>
                                    <th width="40%" class="star">公司EMAIL：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.email" type="text" class="textinput" size="30"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%">公司網址：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.website" type="text" class="textinput"
                                                    size="14"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%">統一編號：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.uniformNumbers" type="text" class="textinput"
                                                    size="14"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%">員工人數：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.persons" type="text" class="textinput"
                                                    size="14"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%">資本額：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.capital" type="text" class="textinput"
                                                    size="14"/>
                                    </td>
                                </tr>

                                <tr>
                                    <th width="40%">縣市：</th>
                                    <td width="60%">
                                        <form:select id="city" path="addAndUpdCase.city">
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%">鄉鎮市：</th>
                                    <td width="60%">
                                        <form:select id="area" path="addAndUpdCase.area"> </form:select>
                                    </td>
                                </tr>

                                <tr>
                                    <th width="40%">地址：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.addr" type="text" class="textinput" size="50"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%" class="star">公司成立日期：</th>
                                    <td width="60%">
                                        <form:input path="addAndUpdCase.chineseIssueDate" type="text" class="textinput"
                                                    size="14"/>
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
            $('#sessionForm').attr('action', '<c:url value="/base0102_add.action" />').submit();
        });

        $('#updBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0102_upd.action" />').submit();
        });

        $('#backBtn').click(function () {
            if (status == "upd") {
                $('#sessionForm').attr('action', '<c:url value="/base0102_query.action" />').submit();
            } else {
                $('#sessionForm').attr('action', '<c:url value="/base0102_enter.action" />').submit();
            }
        });


        let cityVal = '<c:out value="${base0102.addAndUpdCase.city}"/>';
        let areaVal = '<c:out value="${base0102.addAndUpdCase.area}"/>';

        cityOptionInit().then(msg => {
            console.log(msg);
            initCity($("#city"), $("#area"), cityVal, areaVal);
        }).catch(msg => {
            console.log(msg);
        })

        $("#city").change(function () {
            cityEvent($(this), $("#area"));
        })

    });
</script>
</body>
</html>
