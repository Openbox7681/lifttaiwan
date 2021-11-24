<%@ page language="java" pageEncoding="UTF8"%>
<nav class="navbar navbar_customer">
	<div class="container">
		<div class="row">
			<div "col-lg-12">
				<a href="<c:out value="${pageContext.request.contextPath}" />/"
					class="login_logo"> <img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/hisac-logo.png"
					alt="<s:message code="globalSiteName" />"
					title="<s:message code="globalSiteName" />" />
				</a>
				<div class="line1"></div>
			</div>
			</div>
			
			
			<%-- <div>
				<ul class="nav navbar-right navbar_right hidden-xs">
					<li class="dropdown"><a id="btnLogin" href="#"
						onclick="toggleLogin()" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="fas fa-fw fa-sign-in-alt"></i>
							<s:message code="loginTitle" /></a><a id="btnIndex"
						href="<c:out value="${pageContext.request.contextPath}" />/" onclick="toggleLogin()"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false" style="display: none;"><i
							class="fas fa-fw fa-home"></i> <s:message
								code="globalGoToHomepage" /></a></li>
				</ul>
			</div> --%>
		<%-- <div class="navbar-header">
			<a id="btnLogin2" href="#" onclick="toggleLogin()"><button
					type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#menu">
					<i class="fas fa-lg fa-sign-in-alt"></i>
				</button> </a> <a id="btnIndex2" href="<c:out value="${pageContext.request.contextPath}" />/"
				onclick="toggleLogin()" style="display: none;"><button
					type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#menu">
					<i class="fas fa-lg fa-home"></i>
				</button> </a>
		</div> --%>
		<div class="collapse navbar-collapse" id="menu"></div>
	</div>
</nav>
