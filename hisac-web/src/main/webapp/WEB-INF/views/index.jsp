<%@ page pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="./include/head_index.jsp"%>
<script>
	var loginLogin = '<s:message code="loginLogin" />';
	var loginFail = '<s:message code="loginFail" />';
	var btnClose = '<s:message code="btnClose" />';
</script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/index.js"></script>
<body ng-app="myApp" class="logincolor" id="stop" >


	<div ng-controller="getAppController" class="container">

	
	<section id="login_logo">
      <div class="container">
        <div class="row">
          <div class="col-lg-12"><a class="login_logo" href="../index.html">
          <img src=   "<c:out value="${pageContext.request.contextPath}" />/resources/img/logo_lightup.svg"
           alt=""></a>
            <div class="line1"></div>
          </div>
        </div>
      </div>
    </section>
    
    
    <section id="login_content">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 hcenter">
            <div class="login_outer hcenter">
              <div class="login_inner">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="login_title">
                      <h5>會員登入/Login</h5>
                    </div>
                    <form name="myForm">
                    
                    <div class="account hleft">
                      <p>*Account</p>
                      <input id="userName" name="userName" ng-model="userName"
                      class="form-control" type="text" placeholder="輸入您的帳號" aria-label="輸入您的帳號" aria-describedby="basic-addon2"
                      autocomplete="off" autofocus ng-required="true" ng-disabled="showTwoFactor"
                      >
                      <div class="line2"></div>
                      <h5 class="text-danger"
						ng-show="myForm.userName.$invalid && myForm.userName.$dirty">
						<s:message code="pleaseEnter" />
						<s:message code="loginName" />
						</h5>
                    </div>
                    <div class="code hleft">
                      <p>*Password</p>
                      <input type = password id="userCode" name="userCode" ng-model="userCode"
                      class="form-control" type="text" placeholder="輸入您的密碼" aria-label="輸入您的密碼" aria-describedby="basic-addon2"
                       autocomplete="off" ng-required="true" ng-disabled="showTwoFactor"
                      >
                      <div class="line2"></div>
                      <h5 class="text-danger"
						ng-show="myForm.userCode.$invalid && myForm.userCode.$dirty">
						<s:message code="pleaseEnter" />
						<s:message code="loginCode" />
						</h5>
                    </div>
                    <div class="loginbtn"><a
                    type="button"
					ng-click="!myForm.userName.$valid || !myForm.userCode.$valid || login()"
					ng-disabled="!myForm.userName.$valid || !myForm.userCode.$valid"
                    
                    ><span>會員登入/Login</span></a></div>
                    
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    
    
			
			
			
 	</div>
	
	
<!-- 	<div class="footer_space"></div>
 -->	
	<%@ include file="./include/footer.jsp"%>
	<!--<![endif]-->
</body>
</html>