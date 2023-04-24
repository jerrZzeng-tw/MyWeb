<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0301C"/>
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
                        <div class="legend">排程範例-BASE0301C</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <form:form action="" modelAttribute="base0301" id="sessionForm">

                        <div class="container formButtonDiv">
                            <c:if test="${base0301.condition == 'add'}">
                                <button id="addBtn" type="button" class="btnwhite">
                                    新增&nbsp;<i class="fas fa-user-plus"></i>
                                </button>
                            </c:if>
                            <button id="backBtn" type="button" class="btnwhite">
                                返回&nbsp;<i class="fas fa-reply"></i>
                            </button>
                        </div>
                        <fieldset>
                            <legend>排程範例</legend>
                            <!------------------------------ 新增 ------------------------------>

                            <c:if test="${base0301.condition == 'add'}">
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%" class="star">排程類別：</th>
                                        <td width="60%">
                                         	 <form:select path="addAndQueryCase.jobClassName">
											       <form:option value="Sample1Job">Sample1Job</form:option>
											       <form:option value="Sample2Job">Sample2Job</form:option>
									   		</form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%" class="star">排程名稱：</th>
                                        <td width="60%">
                                            <form:input path="addAndQueryCase.jobName" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%" >排程描述：</th>
                                        <td width="60%">
                                            <form:input path="addAndQueryCase.description" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%"  class="star">排定時間：</th>
                                        <td width="60%">
                                            <form:input path="addAndQueryCase.scheduleTime" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">排程參數：</th>
                                        <td width="60%">
                                            <form:input path="addAndQueryCase.param" type="text" class="textinput"
                                                        size="14"/>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                            <!------------------------------ 查詢 ------------------------------>
                            <c:if test="${base0301.condition == 'query'}">
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%">jobId：</th>
                                        <td width="60%">
                                            <c:out value="${base0301.addAndQueryCase.jobId}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">排程名稱：</th>
                                        <td width="60%">
                                            <c:out value="${base0301.addAndQueryCase.jobName}"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%">排程描述：</th>
                                        <td width="60%">
                                             <c:out value="${base0301.addAndQueryCase.description}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">排定時間：</th>
                                        <td width="60%">
                                             <c:out value="${base0301.addAndQueryCase.scheduleTime}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">排程參數：</th>
                                        <td width="60%">
                                        	<c:out value="${base0301.addAndQueryCase.param}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">job啟動時間：</th>
                                        <td width="60%">
                                       		 <c:out value="${base0301.addAndQueryCase.exeStart}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">job結束時間：</th>
                                        <td width="60%">
                                             <c:out value="${base0301.addAndQueryCase.exeEnd}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">執行狀態：</th>
                                        <td width="60%">
                                        	<c:out value="${base0301.addAndQueryCase.status}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">執行結果：</th>
                                        <td width="60%">
                                            <c:out value="${base0301.addAndQueryCase.result}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">排程人員：</th>
                                        <td width="60%">
                                             <c:out value="${base0301.addAndQueryCase.addId}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">新增排程時間：</th>
                                        <td width="60%">
                                            <c:out value="${base0301.addAndQueryCase.addTime}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">取消人員：</th>
                                        <td width="60%">
                                            <c:out value="${base0301.addAndQueryCase.cancelId}"/>
                                        </td>
                                    </tr>
                                     <tr>
                                        <th width="40%">取消排程時間：</th>
                                        <td width="60%">
                                            <c:out value="${base0301.addAndQueryCase.cancelTime}"/>
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
                        <form:hidden id="condition" path="condition"/>

                    </form:form>
                </div>
                <%@ include file="/WEB-INF/jsp/includes/footer.jsp" %>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {

        let condition = $('#condition').val();
        // default open menu
        $(".sidenav-button").trigger("click");

        $('#addBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0301_add.action" />').submit();
        });

        $('#backBtn').click(function () {
//         	 if (condition == "query") {
//                  $('#sessionForm').attr('action', '<c:url value="/base0301_query.action" />').submit();
//              } else {
                 $('#sessionForm').attr('action', '<c:url value="/base0301_enter.action" />').submit();
//              }
        });

    });
</script>
</body>
</html>
