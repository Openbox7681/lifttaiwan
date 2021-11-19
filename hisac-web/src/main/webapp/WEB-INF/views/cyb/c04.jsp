<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c04.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_green">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>

			<div class="col-xs-12 shadow_board">
				<form name="queryForm">

					<div>
						<div class="form_group">
							<label for="QueryIP"
								class="form_label form_label_search form_label_gray">IP或Domain</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryIP" name="QueryIP"
									ng-model="QueryIP" class="form-control col-xs-6"
									placeholder="IP或Domain" />
							</div>
						</div>
					</div>

					<div>
						<div class="form_group"></div>
					</div>
					<div class="help-block"></div>
					<div class="submit_bar">
						<button class="btn btn_custom btn_blue" type="button"
							ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						<button class="btn btn_custom btn_gray" type="button"
							ng-click="clearData()">
							<i class="fas fa-fw fa-redo-alt"></i>
							<s:message code="btnReset" />
						</button>
					</div>
				</form>
			</div>
		</div>

		<div class="row" ng-show="inList || outList">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
		</div>

		<div class="row">			
				<div class="col-xs-12 shadow_board"  style="background-color:red;" ng-show="inList">
					<p Align="Center"><font size="5"> 位於黑名單列表中 </font></p>
			</div> 
			<div class="col-xs-12 shadow_board"  style="background-color:white;" ng-show="outList">
					<p Align="Center"><font size="5"> 不在黑名單列表中 </font></p>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
		</div>
	</div>
	</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
