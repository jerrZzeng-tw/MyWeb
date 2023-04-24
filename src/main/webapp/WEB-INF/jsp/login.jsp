<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/includes/meta.jsp" %>
    <%@ include file="/WEB-INF/jsp/includes/css.jsp" %>
    <%@ include file="/WEB-INF/jsp/includes/js.jsp" %>
</head>

<body>
<div class="web frameDiv">

    <div class="nav">
        <div class="sys_info">
        </div>
    </div>
    <div class="wrapper">
        <div class="content">
            <div class="page">
                <form:form id='form' action='' method="post">
                    <fieldset>
                        <table class="form-table">
                            <tr>
                                <th>帳號：</th>
                                <td>
                                    <input name="ID" value="sys" type="text" class="textinput" size="20"/>
                                </td>
                            </tr>
                            <tr>
                                <th>密碼：</th>
                                <td>
                                    <input name="PW" value="sys" type="password" class="textinput" size="20"/>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button id="btnLogin" type="button" class="btnwhite">
                                        登入
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
                <script>
                    $(document).ready(function () {
                        $('#btnLogin').click(
                            function () {
                                $('#form').attr('action', '<c:url value="/Login.action" />').submit();
                            });
                    });
                </script>


            </div>


        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        // default open menu
        $(".sidenav-button").trigger("click");
    });
    $(function () {
        showAlert('<func:systemAlertMessage />');
    });
</script>
</body>
</html>

