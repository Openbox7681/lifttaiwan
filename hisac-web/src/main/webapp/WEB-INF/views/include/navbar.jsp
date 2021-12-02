<%@ page language="java" pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar navbar_customer">
	<div class="container">
		<div class="index_title">
			<div>
				<a href="<c:out value="${pageContext.request.contextPath}" />/"
					class="logo logo_fix pull-left"> <img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/hisac-logo.png"
					alt="<s:message code="globalSiteName" />"
					title="<s:message code="globalSiteName" />" class="img-responsive" />
				</a>
			</div>
			<div>
				<ul class="nav navbar-right navbar_right hidden-xs">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="fas fa-fw fa-user"></i> <c:out
								value="${memberName}" /><i class="fas fa-fw fa-caret-down"></i></a>
						<ul class="dropdown-menu">
							<li
								ng-if="<c:out value="${isAdmin}" />==true || <c:out value="${isHisac}" />==true || <c:out value="${isApplyAdmin}" />==true || <c:out value="${isMemberAdmin}" />==true"><a
								href="<c:out value="${pageContext.request.contextPath}" />/sys/edit_org_profile"><s:message
										code="globalEditOrgPorfile" /></a></li> 
							<li><a
								href="<c:out value="${pageContext.request.contextPath}" />/sys/edit_profile"><s:message
										code="globalEditPorfile" /></a></li>
							<li><a
								href="<c:out value="${pageContext.request.contextPath}" />/sys/change_code"><s:message
										code="globalChangeCode" /></a></li>
							<li><a href="<c:out value="${pageContext.request.contextPath}" />/sys/login_history">登入紀錄</a></li>
							<c:if test="${isMemberContact || isMemberAdmin}">
								<li><a href="<c:out value="${pageContext.request.contextPath}" />/sys/click_history">點擊紀錄</a></li>
							</c:if>
							
							<c:if test="${isMemberAdmin || isAdmin}">
								<li><a href="<c:out value="${pageContext.request.contextPath}" />/sys/ires">資產盤點API KEY申請/查詢</a></li>
							</c:if>
							
							
							<li><a href="<c:out value="${pageContext.request.contextPath}" />/sys/survey"><s:message
										code="globalSurvey" /></a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#" onclick="logout();"><i
									class="fas fa-fw fa-sign-out-alt"></i> <s:message
										code="globalLogout" /></a></li>
						</ul></li>
				</ul>
			</div>
			<c:if test="${fn:length(memberRoles) gt 1}">	
			<div>
				<ul class="nav navbar-right navbar_right hidden-xs">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="fa fa-users"></i>切換角色(
						<c:forEach items="${memberRoles}" var="memberRole">
							<c:if test="${memberRole.getIsEnable()}">
    								<c:if test="${memberRole.getRoleId() == 1}">
    									SuperAdmin
    								</c:if> 
    									
    								<c:if test="${memberRole.getRoleId() == 2}">
    									H-ISAC管理者
    								</c:if> 	
    								
    								<c:if test="${memberRole.getRoleId() == 3}">
    									H-ISAC內容維護者
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 4}">
    									H-ISAC內容審核者
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 5}">
    									H-ISAC警訊建立者
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 6}">
    									H-ISAC警訊審核者
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 7}">
    									權責單位警訊審核者
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 8}">
    									權責單位聯絡人
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 9}">
    									權責單位管理者
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 10}">
    									會員機構聯絡人
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 11}">
    									會員機構管理者
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 12}">
    									HISAC通報審核者
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 13}">
    									HISAC情資維護者
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 14}">
    									HISAC-情資審核者
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 15}">
    									權責單位通報審核者
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 16}">
    									主管機關
    								</c:if>
    								    		
    							 	<c:if test="${memberRole.getRoleId() == 17}">
    									H-CERT審核者
    								</c:if>
    								    		
    							 	<c:if test="${memberRole.getRoleId() == 18}">
    									事件處理單位聯絡人
    								</c:if>

    							 	<c:if test="${memberRole.getRoleId() == 20}">
    									資安維護計畫稽核委員
    								</c:if>
									
									<c:if test="${memberRole.getRoleId() == 21}">
    									PMO
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 22}">
    									系統開發人員
    								</c:if>
    							</c:if>
						</c:forEach>
						)<i class="fas fa-fw fa-caret-down"></i></a>
						<ul class="dropdown-menu">														
							<c:forEach items="${memberRoles}" var="memberRole">
							<c:if test="${!memberRole.getIsEnable()}">
    								<c:if test="${memberRole.getRoleId() == 1}">
    									<li><a href="#" onclick="changeRole(1)"><span>SuperAdmin</span></a></li>    									
    								</c:if> 
    									
    								<c:if test="${memberRole.getRoleId() == 2}">
    									<li><a href="#" onclick="changeRole(2)"><span>H-ISAC管理者</span></a></li>
    								</c:if> 	
    								
    								<c:if test="${memberRole.getRoleId() == 3}">
    									<li><a href="#" onclick="changeRole(3)"><span>H-ISAC內容維護者</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 4}">
    									<li><a href="#" onclick="changeRole(4)"><span>H-ISAC內容審核者</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 5}">
    									<li><a href="#" onclick="changeRole(5)"><span>H-ISAC警訊建立者</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 6}">
    									<li><a href="#" onclick="changeRole(6)"><span>H-ISAC警訊審核者</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 7}">
    									<li><a href="#" onclick="changeRole(7)"><span>權責單位警訊審核者</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 8}">
    									<li><a href="#" onclick="changeRole(8)"><span>權責單位聯絡人</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 9}">
    									<li><a href="#" onclick="changeRole(9)"><span>權責單位管理者</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 10}">
    									<li><a href="#" onclick="changeRole(10)"><span>會員機構聯絡人</span></a></li>
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 11}">
    									<li><a href="#" onclick="changeRole(11)"><span>會員機構管理者</span></a></li>
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 12}">
    									<li><a href="#" onclick="changeRole(12)"><span>HISAC通報審核者</span></a></li>
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 13}">
    									<li><a href="#" onclick="changeRole(13)"><span>HISAC情資維護者</span></a></li>
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 14}">
    									<li><a href="#" onclick="changeRole(14)"><span>HISAC-情資審核者</span></a></li>
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 15}">
    									<li><a href="#" onclick="changeRole(15)"><span>權責單位通報審核者</span></a></li>
    								</c:if>
    							 			
    							 	<c:if test="${memberRole.getRoleId() == 16}">
    									<li><a href="#" onclick="changeRole(16)"><span>主管機關</span></a></li>    									
    								</c:if>
    								    	
    							 	<c:if test="${memberRole.getRoleId() == 17}">
    									<li><a href="#" onclick="changeRole(17)"><span>H-CERT審核者</span></a></li>    									
    								</c:if>
    								    	
    							 	<c:if test="${memberRole.getRoleId() == 18}">
    									<li><a href="#" onclick="changeRole(18)"><span>事件處理單位聯絡人</span></a></li>    									
    								</c:if>

    							 	<c:if test="${memberRole.getRoleId() == 20}">
    									<li><a href="#" onclick="changeRole(20)"><span>資安維護計畫稽核委員</span></a></li>    									
    								</c:if>
									
									<c:if test="${memberRole.getRoleId() == 21}">
    									<li><a href="#" onclick="changeRole(21)"><span>PMO</span></a></li>    									
    								</c:if>
    								
    								<c:if test="${memberRole.getRoleId() == 22}">
    									<li><a href="#" onclick="changeRole(22)"><span>系統開發人員</span></a></li>    									
    								</c:if>
    							</c:if>
						</c:forEach>
						</ul></li>
				</ul>
			</div>
			</c:if>
			<div
				ng-if="<c:out value="${developmentMode}" /> && <c:out value="${memberAccount != 'useradmin'}" />"
				class="menu_bar_toggle" style="display: none;">
				<ul class="nav navbar-right navbar_right hidden-xs">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><i class="fas fa-fw fa-cog"></i>Dev Mode<i
							class="fas fa-fw fa-caret-down"></i></a>
						<ul class="dropdown-menu">
							<li><a href="#">subsystemName: <c:out
										value="${subsystemName}" />
							</a></li>
							<li><a href="#">appName: <c:out value="${appName}" />
							</a></li>
							<li><a href="#">controllerName: <c:out
										value="${controllerName}" />
							</a></li>
							<li><a href="#">actionName: <c:out value="${actionName}" />
							</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">actionCreate: <c:out
										value="${actionCreate}" />
							</a></li>
							<li><a href="#">actionUpdate: <c:out
										value="${actionUpdate}" />
							</a></li>
							<li><a href="#">actionDelete: <c:out
										value="${actionDelete}" />
							</a></li>
							<li><a href="#">actionRead: <c:out value="${actionRead}" />
							</a></li>
							<li><a href="#">actionSign: <c:out value="${actionSign}" />
							</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">orgType: <c:out value="${orgType}" />
							</a></li>
							<li><a href="#">orgId: <c:out value="${orgId}" />
							</a></li>
							<li><a href="#">languageCountry: <c:out
										value="${languageCountry}" />
							</a></li>
							<li><a href="#">developmentMode: <c:out
										value="${developmentMode}" />
							</a></li>
							<li><a href="#">debugMode: <c:out value="${debugMode}" />
							</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#menu">
				<span class="sr-only">Toggle</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="menu">
			<ul class="nav navbar-nav" id="menuLeft">
			
			</ul>
			<ul class="nav navbar-nav ">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><i class="fas fa-fw fa-user"></i> <c:out
							value="${memberName}" /><i class="fas fa-fw fa-caret-down"></i></a>
					<ul class="dropdown-menu">
						<li
							ng-if="<c:out value="${orgType}" />==2 || <c:out value="${orgType}" />==3"><a
							href="<c:out value="${pageContext.request.contextPath}" />/sys/edit_org_profile"><s:message
									code="globalEditOrgPorfile" /></a></li>
						<li><a
							href="<c:out value="${pageContext.request.contextPath}" />/sys/edit_profile"><s:message
									code="globalEditPorfile" /></a></li>
						<li><a
							href="<c:out value="${pageContext.request.contextPath}" />/sys/change_code"><s:message
									code="globalChangeCode" /></a></li>
						<li><a href="<c:out value="${pageContext.request.contextPath}" />/sys/survey"><s:message
									code="globalSurvey" /></a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#" onclick="logout();"><i
								class="fas fa-fw fa-sign-out-alt"></i> <s:message
									code="globalLogout" /></a></li>
					</ul></li>
			</ul>
			<ul
				class="menu_bar_toggle nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><i class="fas fa-fw fa-cog"></i>Dev Mode<i
						class="fas fa-fw fa-caret-down"></i></a>
					<ul class="dropdown-menu">
						<li role="separator" class="divider"></li>
						<li><a href="#">subsystemName: <c:out
									value="${subsystemName}" />
						</a></li>
						<li><a href="#">appName: <c:out value="${appName}" />
						</a></li>
						<li><a href="#">controllerName: <c:out
									value="${controllerName}" />
						</a></li>
						<li><a href="#">actionName: <c:out value="${actionName}" />
						</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">actionCreate: <c:out
									value="${actionCreate}" />
						</a></li>
						<li><a href="#">actionUpdate: <c:out
									value="${actionUpdate}" />
						</a></li>
						<li><a href="#">actionDelete: <c:out
									value="${actionDelete}" />
						</a></li>
						<li><a href="#">actionRead: <c:out value="${actionRead}" />
						</a></li>
						<li><a href="#">actionSign: <c:out value="${actionSign}" />
						</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">orgType: <c:out value="${orgType}" />
						</a></li>
						<li><a href="#">orgId: <c:out value="${orgId}" />
						</a></li>
						<li><a href="#">languageCountry: <c:out
									value="${languageCountry}" />
						</a></li>
						<li><a href="#">developmentMode: <c:out
									value="${developmentMode}" />
						</a></li>
						<li><a href="#">debugMode: <c:out value="${debugMode}" />
						</a></li>
					</ul></li>
			</ul>
		</div>
		<div id="breadcrumb" class="hidden-xs"
			ng-if="'pub'!='${controllerName.toLowerCase()}' || 'index'!='${actionName.toLowerCase()}'">
			<ol class="breadcrumb no_padding">
				<li><a href="<c:out value="${pageContext.request.contextPath}" />/"><i
						class="fas fa-fw fa-home"></i> <s:message code="globalHomepage" /></a></li>
				<li><c:out value="${subsystemName}" /></li>
				<li><c:out value="${appName}" /></li>
				<li ng-if="btnIns"><s:message code="btnCreate" /></li>
				<li ng-if="btnUpd"><s:message code="btnUpdate" /></li>
				<li ng-if='detailTitle'>{{detailTitle}}</li>
			</ol>
		</div>
	</div>
</nav>

<script>
function changeRole(roleId){
	$
	.ajax(
			{
				type : 'POST',
				url : '<c:out value="${pageContext.request.contextPath}" />/public/api/changeRole',
				data : JSON.stringify({RoleId : roleId}),
				contentType: "application/json; charset=utf-8",
			    dataType: "json",				    
				headers : header
			}).done(function(response) {					
				location.replace(response.webSiteUrl)
	});
}
	$(document)
			.ready(
					function() {
						$(".menu_bar_toggle").show();

						function b64DecodeUnicode(str) {
							return decodeURIComponent(Array.prototype.map.call(
									atob(str),
									function(c) {
										return '%'
												+ ('00' + c.charCodeAt(0)
														.toString(16))
														.slice(-2)
									}).join(''))
						}

						function escapeXml(unsafe) {
							return unsafe
									.replace(
											/[<>&'"]/g,
											function(c) {
												switch (c) {
													case '<':
														return '&lt;';
						            				case '>' :
														return '&gt;';
													case '&' :
														return '&amp;';
													case '\'' :
														return '&apos;';
													case '"' :
														return '&quot;';
												}
											});
						}

						function makeUL(lst, topLevelUl, rootLvl) {
							var html = [];
							if (topLevelUl) {
								html.push('<ul class="nav navbar-nav">');
								topLevelUl = false;
							} else {
								html
										.push('<ul class="dropdown-menu" role="menu">');
							}

							$(lst).each(function() {
								html.push(makeLI(this, topLevelUl, rootLvl))
							});
							html.push('</ul>');
							rootLvl = true;
							return html.join("\n");
						}

						function makeLI(elem, topLevelUl, rootLvl) {
							var html = [];
							if (elem.children && !rootLvl) {
								html.push('<li">');
							} else {
								html.push('<li class="dropdown">');
								rootLvl = false;
							}
							if (elem.value) {
								if (elem.separator == true) {
									html
											.push('<li role="separator" class="divider">');
								} else {
									if (elem.target) {
										html
												.push('<a href="<c:out value="${pageContext.request.contextPath}" />'
														+ escapeXml(elem.value)
														+ '" target="_'
														+ escapeXml(elem.target)
														+ '">'
														+ escapeXml(elem.link)
														+ ' <i class="fas fa-fw fa-external-link-alt"></i></a>');
									} else {
										html
												.push('<a href="<c:out value="${pageContext.request.contextPath}" />'
														+ escapeXml(elem.value)
														+ '">'
														+ escapeXml(elem.link)
														+ ' <span id="'
														+ escapeXml(elem.code)
														+ '" class="badge"></span></a>');
									}
								}
							} else {
								html
										.push('<a id="'
												+ escapeXml(elem.code)
												+ '" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">'
												//+ '<!--<i class="fas fa-fw ' + elem.iconStyle + '"></i>-->'
												+ escapeXml(elem.link)
												+ ' <span id="'
												+ escapeXml(elem.code)
												+ '" class="badge"></span>'
												+ ' <i class="fas fa-fw fa-caret-down"></i></a>');
							}
							if (elem.children) {
								html.push(makeUL(elem.children, topLevelUl,
										rootLvl));
							}
							html.push('</li>');
							return html.join("\n");
						}

						$(function() {
							var topLevelUl = true;
							var menuJson = "<c:out value="${menuJson}" />";
							$("#menuLeft")
									.replaceWith(
											makeUL(
													JSON
															.parse(b64DecodeUnicode(menuJson)).menu,
													topLevelUl, true));

							$("#subsystem_${controllerName.toLowerCase()}")
									.addClass("subsystem");

							var request = {
								"a" : Math.random().toString(36).substring(2,
										15)
							}
							// 系統管理
							if ($("#subsystem_sys").length > 0) {
								var subsystem_sys_count = 0;

								if ($("#form_sign_apply").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/member_sign",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$(
																	"#form_sign_apply")
																	.html(count);
															subsystem_sys_count = subsystem_sys_count
																	+ count;
														}
													});

								}

								if ($("#form_news_management").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/news_management",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$(
																	"#form_news_management")
																	.html(count);
															subsystem_sys_count = subsystem_sys_count
																	+ count;
														}
													});
								}

								if ($("#form_activity_management").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/activity_management",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$(
																	"#form_activity_management")
																	.html(count);
															subsystem_sys_count = subsystem_sys_count
																	+ count;
														}
													});
								}

								if ($("#form_ana_management").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/ana_management",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$(
																	"#form_ana_management")
																	.html(count);
															subsystem_sys_count = subsystem_sys_count
																	+ count;
														}
													});
								}

								if ($("#form_information_management").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/information_management",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$(
																	"#form_information_management")
																	.html(count);
															subsystem_sys_count = subsystem_sys_count
																	+ count;
														}
													});
								}

								//if ($("#form_weakness_management").length > 0) {
								//	$
								//			.ajax(
								//					{
								//						url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/weakness_management",
								//						method : "POST",
								//						data : request,
								//						headers : header,
								//						datatype : "json",
								//						async : false
								//					})
								//			.done(
								//					function(response) {
								//						var count = response.count;
								//						if (count > 0) {
								//							$(
								//									"#form_weakness_management")
								//									.html(count);
								//							subsystem_sys_count = subsystem_sys_count
								//									+ count;
								//						}
								//					});
								//}

								if (subsystem_sys_count > 0) {
									$("#subsystem_sys > .badge").html(
											subsystem_sys_count);
								}
							}

							// 警訊發佈
							if ($("#subsystem_not").length > 0) {
								var subsystem_not_count = 0;
								if ($("#form_not").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/not",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$("#form_not")
																	.html(count);
															subsystem_not_count = subsystem_not_count
																	+ count;
														}
													});
								}
								if (subsystem_not_count > 0) {
									$("#subsystem_not > .badge").html(
											subsystem_not_count);
								}
							}


							// 通報服務							
							if ($("#subsystem_alt").length > 0) {
								var subsystem_alt_count = 0;
								if ($("#form_alert_management").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/alt",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$(
																	"#form_alert_management")
																	.html(count);
															subsystem_alt_count = subsystem_alt_count
																	+ count;
														}
													});
								}
								if (subsystem_alt_count > 0) {
									$("#subsystem_alt > .badge").html(
											subsystem_alt_count);
								}
							}

							
							// 資安資訊							
							if ($("#subsystem_cyb").length > 0) {
								var subsystem_cyb_count = 0;
								if ($("#form_nisac").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/cyb",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$("#form_nisac")
																	.html(count);
															subsystem_cyb_count = subsystem_cyb_count
																	+ count;
														}
													});
								}
								if (subsystem_cyb_count > 0) {
									$("#subsystem_cyb > .badge").html(
											subsystem_cyb_count);
								}
							}
							

							// 事故處理
							// 新增功能選項 108/6/6 added by bowwow
							if ($("#subsystem_inc").length > 0) {
								var subsystem_inc_count = 0;
								if ($("#form_inc").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/inc",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$("#form_inc")
																	.html(count);
															subsystem_inc_count = subsystem_inc_count + count;
														}
													});
								}
								if (subsystem_inc_count > 0) {
									$("#subsystem_inc > .badge").html(subsystem_inc_count);
								}
							}
							
							
							// 資安維護計畫
							if ($("#subsystem_mtp").length > 0) {
								var subsystem_mtp_count = 0;
								if ($("#form_maintainPlan").length > 0) {
									$
											.ajax(
													{
														url : "<c:out value="${pageContext.request.contextPath}" />/pub/api/count/mtp",
														method : "POST",
														data : request,
														headers : header,
														datatype : "json",
														async : false
													})
											.done(
													function(response) {
														var count = response.count;
														if (count > 0) {
															$("#form_maintainPlan")
																	.html(count);
															subsystem_mtp_count = subsystem_mtp_count
																	+ count;
														}
													});
								}
								if (subsystem_mtp_count > 0) {
									$("#subsystem_mtp > .badge").html(
											subsystem_mtp_count);
								}
							}


						});
					})

	
</script>