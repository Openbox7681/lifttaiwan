<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/pub/starRatings.css">
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/pub/p01.js"></script>
<body class="no-skin" ng-controller="getAppController">
	<%@ include file="../include/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_blue">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-news.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>			 
		</div>
		<div id="divQuery" style="display: none">
						<div class="form_group">
							<label for="QueryKeyword"
								class="form_label form_label_search form_label_gray">關鍵字</label>
							<div class="form_input form_input_search">
								<input type="text" id="QueryKeyword" name="QueryKeyword"
									ng-model="QueryKeyword" class="form-control"
									placeholder="關鍵字" />							
							<button class="btn btn_custom btn_blue left pull-left" type="button"
							ng-click="queryData()">
							<i class="fas fa-fw fa-search"></i>
							<s:message code="btnSearch" />
						</button>
						</div>
						</div>
				
			<div class="row">
				<div class="col-xs-12 col-md-6 no_padding">
					<div class="help-block"></div>
					<%@ include file="./../include/table_row_select.jsp"%>
				</div>
				<div class="col-xs-12 col-md-6 no_padding"></div>
			</div>
			<div class="row">
				<div class="table-responsive">
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th class="col-xs-2"><s:message code="newsPostDateTime" /></th>
								<th class="col-xs-3"><s:message code="newsGroup" /></th>
								<th><s:message code="newsTitle" /></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in allitems">
								<td class="text-center">{{item.Date}}</td>
								<td>{{item.Group}}</td>
								<td><a href="#" ng-click="queryDetailData(item.Id)">{{item.Title}}</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<%@ include file="./../include/table_row_empty.jsp"%>
				<div class="text-center">
					<paging class="pagination" page="currentPage" page-size="maxRows"
						total="total" show-prev-next="true" show-first-last="true"
						text-next-class="fas fa-step-forward"
						text-prev-class="fas fa-step-backward"
						text-first-class="fas fa-fast-backward"
						text-last-class="fas fa-fast-forward"
						paging-action="queryData(page)"></paging>
				</div>
				<a class="btn btn-default btn_custom pull-left"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/">
					<i class="fas fa-fw fa-home"></i>
					<s:message code="globalGoToHomepage" />
				</a>
			</div>
		</div>
		<div id="divDetail" style="display: none">
			<div class="row detail_block">				
					<div class="pull-right">						
							{{avgStars}}
							<span>
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
								<div class="star"></div>
							</span>
						</div>													
				<h3 class="detailTitle">{{detailTitle}}</h3>		
				<h5 class="detailSource">
					<span><s:message code="newsSoruce" />&nbsp;:&nbsp;{{detailSourceName}}</span>
					<span class="pull-right"><s:message code="newsPostDateTime" />&nbsp;:&nbsp;{{detailPostDataTime}}</span>
				</h5>
				<div class="detailContent" ng-show="isDetail">
					<div ng-if="!isBreakLine" ng-bind-html="detailContent"></div>
					<div ng-if="isBreakLine" ng-bind-html="detailContent | trustHtml"></div>
				</div>
				<h5 class="detailSource" ng-if="detailSourceLink != null && detailSourceLink != ''">
					<span><s:message code="s31SourceLink" />&nbsp;:&nbsp;</span><a ng-href="{{detailSourceLink}}" target="_news">{{detailSourceLink}}</a>
				</h5>
			</div>
			<div class="row detail_block" ng-show="itemAttachments.length > 0">
				<h4>
					<i class="fas fa-fw fa-download"></i>
					<s:message code="newsAttachDownload" />
				</h4>
				<ul class="detailAttachFile">
					<li ng-repeat="item in itemAttachments"><s:message
							code="globalFileName" />&nbsp;:&nbsp;<a
						ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/api/p01/attach/download/{{id}}/{{item.Id}}"
						target="_blank">{{item.FileName}}</a>&nbsp;(<s:message
							code="globalFileSize" />&nbsp;:&nbsp;{{item.FileSize}}&nbsp;)</li>
				</ul>
			</div>			
			<div class="row detail_block">			
				<a class="btn btn-default btn_custom"
					ng-href="<c:out value="${pageContext.request.contextPath}" />/pub/">
					<i class="fas fa-fw fa-home"></i>
					<s:message code="globalGoToHomepage" />
				</a>
				<button ng-hide="!isEnableRating" ng-disabled="!isEnableRating" type="button" class="btn btn-info btn_custom" data-toggle="modal" data-target="#ratingWindow">
					評分
				</button>
				<button ng-hide="isEnableRating" ng-disabled="true" type="button" class="btn btn-info btn_custom" data-toggle="modal" data-target="#ratingWindow">
					<div>{{stars}}<span style="color: orange;">★</span></div>
				</button>
				<button type="button" class="btn btn-info btn_custom" data-toggle="modal" data-target="#commentWindow">
					留言
				</button>				
				<button class="btn btn-default btn_custom pull-right"
					ng-click="queryData(currentPage)">
					<i class="fas fa-fw fa-undo"></i>
					<s:message code="btnReturnList" />
				</button>
			</div>
			<div style="max-height:500px; overflow: scroll" ng-if=comments.length>
				<h3 class="detailTitle">留言</h3>
				<div class="col-xs-12 shadow_board" ng-repeat="item in comments">
					<div class="form_group">					
						<label
							class="">
							<p ng-hide="item.isHideName">{{item.createName}} ({{item.orgName}})</p>
							<p ng-hide="!item.isHideName">匿名者</p>
							{{item.createTime.split('.')[0]}}</label>						
					</div>
					<c:if test="${isAdmin || isHisac}">
						<button class="btn btn-danger pull-right" ng-click="CancelReply(item.id)" >
							<i class="far fa-fw fa-trash-alt"></i> 刪除
						</button>	
					</c:if>	
					<div class="form_group">													
						<div ng-repeat = "item in (item.comment | BrHtml) track by $index" style="background-color:#FFE7BF">																									
							{{item}} 
						</div>															
					</div>																	
				</div>
			</div>
		</div>
	</div>
	<!-- Begin: star rating dialog -->
	<div class="modal fade" id="ratingWindow" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">評分</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="rating">
								<input ng-model="ratingStar" type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 Stars">卓越</label>
								<input ng-model="ratingStar" type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 Stars">優良</label>
								<input ng-model="ratingStar" type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 Stars">尚可</label>
								<input ng-model="ratingStar" type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 Stars">不良</label>
								<input ng-model="ratingStar" type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 Star">有待改進</label>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" ng-click="insertStarRating(item.Id)" class="btn btn-primary pull-left">送出</button>
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">關閉</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End: star rating dialog -->
	
	
		<!-- Begin: star comment dialog -->
	<div class="modal fade" id="commentWindow" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">留言</h4>
				</div>
				<button class="btn btn_custom btn_blue" ng-click=changeRuleIsOpen()>
			<span class="glyphicon glyphicon-plus" ng-if="!RuleIsOpen"></span>
			<span class="glyphicon glyphicon-remove" ng-if="RuleIsOpen"></span>
			規範</button>	
			<div ng-if="RuleIsOpen">
			<ul>
            <li>請勿重覆張貼一樣的文章，或大意內容相同、類似的文章。</li>
            <li>張貼文章不得有違法或侵害他人權益之言論，請勿涉及謾罵、髒話穢言、侵害他人權利之言論，違者應自負法律責任。</li>
            <li>請勿提供軟體註冊碼等違反智慧財產權之資訊。</li>
            <li>禁止發表涉及他人隱私、含有個人對公眾人物之私評，且未經證實、未註明消息來源的網路八卦、不實謠言等。</li>            
            <strong>違反上述規定，網站管理者有權刪除留言，留言前請配合相關規則。</strong>
          </ul>
			</div>
				<div class="modal-body">				
						<div class="row">
							<label>是否匿名留言:</label>							
								<toggle ng-model="isHideName" ng-init="isHideName=false"  								
									on='<i class="far fa-fw fa-check-circle"></i><s:message code="globalisTrue" />'
									off='<i class="fas fa-fw fa-minus-circle"></i><s:message code="globalisFalse" />'
									onstyle="btn-success" offstyle="btn-danger"></toggle>							
							<br>	
							<textarea type="text" id="commentData" name="commentData" style="width:99%"
									ng-model="commentData" placeholder="留言" rows="10"  cols="55"
									autocomplete="off" ng-requird="true"></textarea>																						
						</div>					
				</div>
				<div class="modal-footer">
					<button type="submit" ng-click="insertComment(item.Id)" class="btn btn-primary pull-left">送出</button>
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">關閉</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End: star comment dialog -->
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
