<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!doctype html>
<html>

<head>

</head>
<body scroll="no">

<div id="mainContent">

    <div id="main" class="mainBody">

        <fieldset>
            <legend>&nbsp;系統發生錯誤&nbsp;</legend>

            <div align="right" id="showtime">
                網頁下載時間：民國&nbsp;<func:nowDateTime/>
            </div>

            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2" class="px13">
                <tr>
                    <td>
                        <span style="color:#FF0000;">
                            系統發生預期之外的錯誤：
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span style="color:#FF0000;">
                            請試著再操作一次；若問題仍持續發生，請聯絡系統管理人員。
                        </span>
                    </td>
                </tr>
            </table>
        </fieldset>

    </div>
</div>

</body>
</html>
