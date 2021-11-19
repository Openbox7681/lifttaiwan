<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/index.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.min.js"></script>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/Chart.PieceLabel.min.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/navbar.jsp"%>

	<div class="container-fluid">

		<div class="container">
			<div class="row row_bottom">
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>公開資訊</b>
					</h4>
					<canvas id="myChart1" height="240px"></canvas>
					<div id="myLegend1" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>公開資訊</b>
					</h4>
					<canvas id="myChart2" height="240px">></canvas>
					<div id="myLegend2" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>警訊</b>
					</h4>
					<canvas id="myChart3" height="240px">></canvas>
					<div id="myLegend3" class="chart_legend"></div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
					<h4 class="text-center">
						<b>警訊</b>
					</h4>
					<canvas id="myChart4" height="240px">></canvas>
					<div id="myLegend4" class="chart_legend"></div>
				</div>				
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p11"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
			</div>
		</div>
		<div class="container">
		<div>
						<%-- <div class="row">
							<label for="QueryKeyword"
								class="form_label form_label_search form_label_gray">關鍵字</label>
							<div class="form_input form_input_search">
							<div class="col-xs-10">
								<input type="text" id="QueryKeyword" name="QueryKeyword"
									ng-model="QueryKeyword" class="form-control"
									placeholder="關鍵字" />	
							</div>	
							<div class="col-xs-2">					
							<button class="btn btn_custom btn_blue left pull-right" type="button"
							ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						</div>	
						</div> --%>
						</div>					
			<div class="row">
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
					<h4 class="form_heading form_heading_blue">
						<img
							src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-news.svg" />
						<b><s:message code="globalTitleNews" /></b>
					</h4>
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p01"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
					<div style="position: relative">
						<table
							class="table table-striped table-hover table_public table_gray">
							<tbody>
								<tr ng-repeat="item in news">
									<td>{{item.Date}}</td>
									<td class="ellipsis" style="max-width: 100px;"><a
										ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p01?{{item.Id}}">{{item.Title}}</a></td>
								</tr>
							</tbody>
						</table>
						<div id="loadingNews" class="public_loading">
							<h4>
								<i class="fas fa-circle-notch fa-spin"></i>
								<s:message code="dataLoading" />
							</h4>
						</div>
					</div>
				</div>
				
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
						<h4 class="form_heading form_heading_green">
							<img style = "max-width : 25px;"
								src="<c:out value="${pageContext.request.contextPath}" />/resources/img/malware.svg" />
							<b>勒索軟體專區</b>
							<img style = "max-width : 25px;"
								src="<c:out value="${pageContext.request.contextPath}" />/resources/img/promotional.svg" />
						</h4>
						<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p13"
							class="btn btn-sm btn_more form_heading_more pull-right"><i
							class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
						<div style="position: relative">
							<table
								class="table table-striped table-hover table_public table_gray">
								<tbody>
									<tr ng-repeat="item in malwares">
										<td>{{item.Date}}</td>
										<td class="ellipsis" style="max-width: 100px;"><a
											ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p13?{{item.Id}}">{{item.Title}}</a></td>
									</tr>
								</tbody>
							</table>
							
							<div id="loadingMalwares" class="public_loading">
								<h4>
									<i class="fas fa-circle-notch fa-spin"></i>
									<s:message code="dataLoading" />
								</h4>
							</div> 
							
						</div>
					</div>
				
				</div>	
				
				
				
				
							<div class="row">
				
				
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
					<h4 class="form_heading form_heading_orange">
						<img
							src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-events.svg" />
						<b><s:message code="globalTitleActivity" /></b>
					</h4>
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p02"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
					<div style="position: relative">
						<table
							class="table table-striped table-hover table_public table_gray">
							<tbody>
								<tr ng-repeat="item in activity">
									<td>{{item.Date}}</td>
									<td class="ellipsis" style="max-width: 100px;"><a
										ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p02?{{item.Id}}">{{item.Title}}</a></td>
								</tr>
							</tbody>
						</table>
						<div id="loadingActivity" class="public_loading">
							<h4>
								<i class="fas fa-circle-notch fa-spin"></i>
								<s:message code="dataLoading" />
							</h4>
						</div>
					</div>
				</div>
				
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
					<h4 class="form_heading form_heading_pink">
						<img
							src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
						<b><s:message code="globalTitleSecBuzzer" /></b>
					</h4>
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p06"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
					<div style="position: relative">
						<table
							class="table table-striped table-hover table_public table_gray">
							<tbody>
								<tr ng-repeat="item in secbuzzer">
									<td>{{item.Date}}</td>
									<td class="ellipsis" style="max-width: 100px;"><a
										ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p06?{{item.Id}}">{{item.Cve}} {{item.Title}}</a></td>
								</tr>
							</tbody>
						</table>
						<div id="loadingSecBuzzer" class="public_loading">
							<h4>
								<i class="fas fa-circle-notch fa-spin"></i>
								<s:message code="dataLoading" />
							</h4>
						</div>
					</div>
				</div>
				
				
				
				
				
				
			
				
				
				</div>
				
				<div class="row">
				
			
				<div class="col-xs-12 col-sm-12 no_padding no_padding_col_fix"> 
					<h4 class="form_heading form_heading_green">
						<img
							src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
						<b><s:message code="globalTitleAna" /></b>
					</h4>
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p04"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
					<div style="position: relative">
						<table
							class="table table-striped table-hover table_public table_gray">
							<tbody>
								<tr ng-repeat="item in ana">
									<td>{{item.Date}}</td>
									<td class="ellipsis" style="max-width: 100px;"><a
										ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p04?{{item.Id}}">{{item.Title}}</a></td>
								</tr>
							</tbody>
						</table>
						<div id="loadingAna" class="public_loading">
							<h4>
								<i class="fas fa-circle-notch fa-spin"></i>
								<s:message code="dataLoading" />
							</h4>
						</div>
					</div>								
				</div>		
				
				
				<!-- 
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
				</div>
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
					<h4 class="form_heading form_heading_pink">
						<img
							src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
						<b>情資分享</b>
					</h4>
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p12"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
					<div style="position: relative">
						<table
							class="table table-striped table-hover table_public table_gray">
							<tbody>
								<tr ng-repeat="item in inf">
									<td>{{item.Date}}</td>
									<td class="ellipsis" style="max-width: 100px;"><a
										ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p12?{{item.Id}}">{{item.Cve}} {{item.Title}}</a></td>
								</tr>
							</tbody>
						</table>
						<div id="loadingInf" class="public_loading">
							<h4>
								<i class="fas fa-circle-notch fa-spin"></i>
								<s:message code="dataLoading" />
							</h4>
						</div>
					</div>
				</div>
				-->
				<!-- 
				<div class="col-xs-12 col-sm-6 no_padding no_padding_col_fix">
					<h4 class="form_heading form_heading_pink">
						<img
							src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-alert.svg" />
						<b><s:message code="globalTitleWeakness" /></b>
					</h4>
					<a href="<c:out value="${pageContext.request.contextPath}" />/pub/p05"
						class="btn btn-sm btn_more form_heading_more pull-right"><i
						class="fas fa-fw fa-play"></i> <s:message code="btnMore" /></a>
					<div style="position: relative">
						<table
							class="table table-striped table-hover table_public table_gray">
							<tbody>
								<tr ng-repeat="item in weakness">
									<td>{{item.Date}}</td>
									<td><a
										ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/p05?{{item.Id}}">{{item.Title}}</a></td>
								</tr>
							</tbody>
						</table>
						<div id="loadingWeakness" class="public_loading">
							<h4>
								<i class="fas fa-circle-notch fa-spin"></i>
								<s:message code="dataLoading" />
							</h4>
						</div>
					</div>
				</div>
				 -->
			</div>
		</div>
	</div>
	<div class="help-block"></div>
	<div class="container-fluid banner-zone">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-3" ng-repeat="item in links">
					<a href="{{item.SourceLink}}" target="_outer"><img
						src="<c:out value="${pageContext.request.contextPath}" />/{{item.ImageLink}}"
						class="img-responsive" title="{{item.ImageTitle}}" /></a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>