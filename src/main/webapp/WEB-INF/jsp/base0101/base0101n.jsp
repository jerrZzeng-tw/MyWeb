<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <acl:setProgId progId="BASE0101N"/>
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
                        <div class="legend">基本範例-BASE0101N</div>
                        <div class="timeword">
                            網頁下載時間：民國&nbsp;
                            <func:nowDateTime/>
                        </div>
                    </div>
                    <div class="container formButtonDiv">
                        <button id="queryBtn" class="btnwhite">
                            確認與上傳&nbsp;<i class="fas fa-cog"></i>
                        </button>
                    </div>
                    <form:form action="" modelAttribute="base0101" id="sessionForm"  enctype="multipart/form-data">
                        <fieldset>
                            <legend>查詢條件</legend>
                            <table class="form-table">
                                <tr>
                                    <th>Select範例：</th>
                                    <td>
	                                    <form:select path="selectVal">
					                        <form:option  label="請選擇..." value="" />
					                        <form:options items="${base0101.testOptions}" itemLabel="codeName" itemValue="code"/>
					                    </form:select>
                                    </td>
                               </tr>
                                <tr>
                                    <th>Checkbox範例：</th>
                                    <td>
                                        <form:checkbox value="1" path="checkboxVal" class="textinput" />&nbsp;商品 1&nbsp;
                                        <form:checkbox value="2" path="checkboxVal" class="textinput" />&nbsp;商品 2&nbsp;
                                        <form:checkbox value="3" path="checkboxVal" class="textinput" />&nbsp;商品 3&nbsp;
                                    </td>
                                </tr>
                                <tr>
                                    <th>Radio範例：</th>
                                    <td>
                                      <form:radiobutton path="radioVal" checked="true" value="1" label="男"/>&nbsp;
		                              <form:radiobutton path="radioVal" value="2" label="女"/>&nbsp;
                                    </td>
                                </tr>
                                 <tr>
                                 <th>Input範例：</th>
                                   <td>
                                       <form:input path="inputVal" type="text" class="textinput" size="14"/>
                                   </td>
                                 </tr>
                                  <tr>
                                  <th>File上傳範例：</th>
                                   <td>
            						   <form:input path="fileContent" type="file" name="fileContent" />
                                   </td>
                                  </tr>
                            </table>
                            
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

        $('#queryBtn').click(function () {
            $('#sessionForm').attr('action', '<c:url value="/base0101_query.action" />').submit();
        });

    });
</script>
</body>
</html>
