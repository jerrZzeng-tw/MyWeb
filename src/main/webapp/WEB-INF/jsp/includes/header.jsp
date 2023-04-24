<%@ page language="java" pageEncoding="UTF-8"%>

<div class="nav">
      <div class="sys_info">
        <div>員工編號：<c:out value="${frameworkUserData.empNo}" /></div>
        <div> 部門代號：<c:out value="${frameworkUserData.deptId}" /></div>
        <div> 登入日期：<c:out value="${frameworkUserData.loginDateString}" /></div>
        <div>登入時間：<c:out value="${frameworkUserData.loginTimeString}" /></div>
       </div> 
</div>


