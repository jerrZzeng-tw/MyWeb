<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0101Q"/>
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
                            <li class="breadcrumb-item2">CRUD範例</li>
                            <li class="breadcrumb-item2">基本範例</li>
                        </ol>
                    </div>
                    <div class="fieldset">
                        <div class="legend">基本範例-BASE0101Q</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <form:form action="" modelAttribute="base0101" id="sessionForm">
                        <div class="container formButtonDiv">
                            <button id="backBtn" type="button" class="btnwhite">
                                返回&nbsp;<i class="fas fa-reply"></i>
                            </button>
                        </div>
                        <fieldset>
                            <legend>基本範例</legend>
                        
                                <table class="form-table mx-auto">
                                    <tr>
                                        <th width="40%">Select的值：</th>
                                        <td width="60%">
                                         <form:select path="base0101qCase.selectVal" id="selectOpt">
					                        <form:option  label="請選擇..." value="" />
					                        <form:options items="${base0101.testOptions}" itemLabel="codeName" itemValue="code" />
					                    </form:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">Checkbox的值：</th>
                                        <td width="60%">
	                                        <form:checkbox value="1" path="base0101qCase.checkboxVal" class="textinput" />&nbsp;商品 1&nbsp;
	                                        <form:checkbox value="2" path="base0101qCase.checkboxVal" class="textinput" />&nbsp;商品 2&nbsp;
	                                        <form:checkbox value="3" path="base0101qCase.checkboxVal" class="textinput" />&nbsp;商品 3&nbsp;
                                        </td>
                                    </tr>

                                    <tr>
                                        <th width="40%">Radio的值：</th>
                                        <td width="60%">
	                                        <form:radiobutton path="base0101qCase.radioVal" checked="true" value="1" label="男" />&nbsp;
			                                <form:radiobutton path="base0101qCase.radioVal" value="2" label="女" />&nbsp;
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">Input的值：</th>
                                        <td width="60%">
                                          <form:input path="inputVal" type="text" class="textinput" size="14" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th width="40%">File下載：</th>
                                        <td width="60%">
                                         <c:choose>
									         <c:when test = "${base0101.haveFile}">
									            <button id="downloadBtn" type="button" class="btnwhite">
							                    下載
							                   	</button>
									         </c:when>
									         <c:otherwise>
									            <button id="downloadBtn" type="button" class="btnwhite" disabled="disabled">
							                    下載
							                   	</button>
									         </c:otherwise>
									      </c:choose>
                                       
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

        $(".sidenav-button").trigger("click");

        $('input[type="checkbox"]').prop('disabled', true);
        $("#selectOpt option:not(:selected)").prop("disabled", true);
        $('input[type="radio"]').prop('disabled', true);

        $('#backBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0101_enter.action" />').submit();
        });

        $('#downloadBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0101_download.action" />').submit();
        });
        
    });
</script>
</body>
</html>
