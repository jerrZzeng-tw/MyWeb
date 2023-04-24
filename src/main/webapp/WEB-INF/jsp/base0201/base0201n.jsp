<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0201N"/>
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
                            <li class="breadcrumb-item2">報表範例</li>
                            <li class="breadcrumb-item2">產製報表</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">產製報表-BASE0201N</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <div class="container formButtonDiv">
                        <button type="button" class="btnwhite" id="printPDFBtn">
                            列印&nbsp;PDF&nbsp;<i class="fas fa-print"></i>
                        </button>
                          <button type="button" class="btnwhite" id="printXLSXBtn">
                            列印&nbsp;EXCEL&nbsp;<i class="fas fa-print"></i>
                        </button>
                    </div>
                    <form:form action="" modelAttribute="base0201" id="sessionForm">
                        <fieldset>
                            <legend>查詢項目</legend>
                            <table class="form-table mx-auto">
                                <tr>
                                    <th width="40%">公司名稱：</th>
                                    <td width="60%">
                                        <form:input path="companyName" type="text" class="textinput" size="14"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th width="40%">負責人姓名：</th>
                                    <td width="60%">
                                        <form:input path="owner" type="text" class="textinput" size="14"/>
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

        $('#printPDFBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0201_printPDF.action" />').submit();
        });
        $('#printXLSXBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0201_printXLSX.action" />').submit();
        });

    });
</script>
</body>
</html>
