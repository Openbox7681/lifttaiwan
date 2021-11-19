<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/alt/a02.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_yellow">
				<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-speaker.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
			<div class="col-xs-12 shadow_board">
				<form name="queryForm">
					<div>
						<div class="form_group">
							<label for="QuerySApplyTime"
								class="form_label form_label_search form_label_gray"><s:message
									code="messageQueryDateTime" /></label>
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QuerySdate" name="QuerySdate"
										ng-model="QuerySdate" class="form-control"
										placeholder="<s:message code="messageQuerySdate" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
							</div>
							<span><s:message code="globalDataFromTo" /></span><br
								class="visible-xs" />
							<div class="form_input form_input_half">
								<div class="input-group">
									<input type="text" id="QueryEdate" name="QueryEdate"
										ng-model="QueryEdate" class="form-control"
										placeholder="<s:message code="messageQueryEdate" />"
										autocomplete="off"><span class="input-group-addon">
										<i class="fas fa-calendar-alt"></i>
									</span>
								</div>
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

		<div class="row" ng-show="ExporExcel">
			<h4 class="form_heading form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
			<button class="btn btn_custom btn_heading_tool btn_blue pull-right"
				ng-click="exportData();">
				<i class="fas fa-fw fa-file-excel"></i><s:message
						code="globalExcel" />
			</button>
		</div>
		<!-- 影響等級數量統計 -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel1">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">
					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span><s:message code="a02EffectLevel" /></span><span class="pull-right"><s:message code="a02DateTime" /> {{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02EffectLevel4" /></span></th>
								<th width="15%"><span><s:message code="a02EffectLevel3" /></span></th>
								<th width="15%"><span><s:message code="a02EffectLevel2" /></span></th>
								<th width="15%"><span><s:message code="a02EffectLevel1" /></span></th>
								<th width="15%"><span><s:message code="a02EffectLevel0" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:message code="a02CeffectLevel" /></td>
								<td class="text-right">{{ceffectLevelCount(1,4)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(1,3)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(1,2)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(1,1)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(1,0)|number:0}}</td>
								<td class="text-right">{{ceffectLevelTotalCount(1)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02IeffectLevel" /></td>
								<td class="text-right">{{ceffectLevelCount(2,4)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(2,3)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(2,2)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(2,1)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(2,0)|number:0}}</td>
								<td class="text-right">{{ceffectLevelTotalCount(2)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02AeffectLevel" /></td>
								<td class="text-right">{{ceffectLevelCount(3,4)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(3,3)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(3,2)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(3,1)|number:0}}</td>
								<td class="text-right">{{ceffectLevelCount(3,0)|number:0}}</td>
								<td class="text-right">{{ceffectLevelTotalCount(3)|number:0}}</td>
							</tr>
							<tr>
								<th colspan="7"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>
		</div>
		
		<div>
			<div class="form_group"></div>
		</div>
		<div class="help-block"></div>
		<!-- 事件分類數量統計 -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel2">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span><s:message code="a02EventType" /></span><span class="pull-right"><s:message code="a02DateTime" /> {{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02EventType1" /></span></th>
								<th width="15%"><span><s:message code="a02EventType2" /></span></th>
								<th width="15%"><span><s:message code="a02EventType3" /></span></th>
								<th width="15%"><span><s:message code="a02EventType4" /></span></th>
								<th width="15%"><span><s:message code="a02Other" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span><s:message code="a02Amount" /></td>
								<td class="text-right">{{eventTypeCount(1)|number:0}}</td>
								<td class="text-right">{{eventTypeCount(2)|number:0}}</td>
								<td class="text-right">{{eventTypeCount(3)|number:0}}</td>
								<td class="text-right">{{eventTypeCount(4)|number:0}}</td>
								<td class="text-right">{{eventTypeCount(5)|number:0}}</td>
								<td class="text-right">{{eventTypeTotalCount()|number:0}}</td>
							</tr>

							<tr>
								<th colspan="7"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>

		</div>

		<div>
			<div class="form_group"></div>
		</div>
		<div class="help-block"></div>
		<!-- 事件分類發生原因數量統計(網頁攻擊) -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel3">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="8"><span><s:message code="a02IsEventType1" /></span><span class="pull-right"> <s:message code="a02DateTime" />{{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02IsEventType1Opt1" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType1Opt2" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType1Opt3" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType1Opt4" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType1Opt5" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType1Opt6" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:message code="a02Amount" /></td>
								<td class="text-right">{{eventType1Count(1)|number:0}}</td>
								<td class="text-right">{{eventType1Count(2)|number:0}}</td>
								<td class="text-right">{{eventType1Count(3)|number:0}}</td>
								<td class="text-right">{{eventType1Count(4)|number:0}}</td>
								<td class="text-right">{{eventType1Count(5)|number:0}}</td>
								<td class="text-right">{{eventType1Count(6)|number:0}}</td>
								<td class="text-right">{{eventTypeTotalCount()|number:0}}</td>
							</tr>

							<tr>
								<th colspan="8"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>

		</div>

		<div>
			<div class="form_group"></div>
		</div>
		<div class="help-block"></div>
		<!-- 事件分類發生原因數量統計(非法入侵) -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel4">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span><s:message code="a02IsEventType2" /></span><span class="pull-right"><s:message code="a02DateTime" /> {{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02IsEventType2Opt1" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType2Opt2" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType2Opt3" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType2Opt4" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType2Opt5" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:message code="a02Amount" /></td>
								<td class="text-right">{{eventType2Count(1)|number:0}}</td>
								<td class="text-right">{{eventType2Count(1)|number:0}}</td>
								<td class="text-right">{{eventType2Count(1)|number:0}}</td>
								<td class="text-right">{{eventType2Count(1)|number:0}}</td>
								<td class="text-right">{{eventType2Count(1)|number:0}}</td>
								<td class="text-right">{{eventTypeTotalCount()|number:0}}</td>
							</tr>

							<tr>
								<th colspan="7"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>

		</div>


		<div>
			<div class="form_group"></div>
		</div>
		<div class="help-block"></div>
		<!-- 事件分類發生原因數量統計(阻斷服務) -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel5">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span><s:message code="a02IsEventType3" /></span><span class="pull-right"><s:message code="a02DateTime" /> {{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02IsEventType3Opt1" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType3Opt2" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:message code="a02Amount" /></td>
								<td class="text-right">{{eventType3Count(1)|number:0}}</td>
								<td class="text-right">{{eventType3Count(2)|number:0}}</td>
								<td class="text-right">{{eventTypeTotalCount()|number:0}}</td>
							</tr>

							<tr>
								<th colspan="7"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>

		</div>


		<div>
			<div class="form_group"></div>
		</div>
		<div class="help-block"></div>
		<!-- 事件分類發生原因數量統計(設備異常) -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel6">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span><s:message code="a02IsEventType4" /></span><span class="pull-right"><s:message code="a02DateTime" /> {{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02IsEventType4Opt1" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType4Opt2" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType4Opt3" /></span></th>
								<th width="15%"><span><s:message code="a02IsEventType4Opt4" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:message code="a02Amount" /></td>
								<td class="text-right">{{eventType4Count(1)|number:0}}</td>
								<td class="text-right">{{eventType4Count(2)|number:0}}</td>
								<td class="text-right">{{eventType4Count(3)|number:0}}</td>
								<td class="text-right">{{eventType4Count(4)|number:0}}</td>
								<td class="text-right">{{eventTypeTotalCount()|number:0}}</td>
							</tr>

							<tr>
								<th colspan="7"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>

		</div>


		<div>
			<div class="form_group"></div>
		</div>
		<div class="help-block"></div>
		<!-- 事件分類發生原因數量統計 -->
		<div class="row" ng-show="ExporExcel">
			<div id="exporExcel7">
				<meta http-equiv=Content-Type content="text/html; charset=utf-8">
				<div class="table-responsive">


					<div class="space-12"></div>
					<table
						class="table table-striped table-bordered table-hover table_customer table_gray">
						<thead>
							<tr>
								<th colspan="7"><span><s:message code="a02Finish5Reason" /></span><span class="pull-right"><s:message code="a02DateTime" /> {{QuerySdate}}~{{QueryEdate}}</span>
								</th>

							</tr>

							<tr>
								<th width="15%"><span></span></th>
								<th width="15%"><span><s:message code="a02EventType1" /></span></th>
								<th width="15%"><span><s:message code="a02EventType2" /></span></th>
								<th width="15%"><span><s:message code="a02EventType3" /></span></th>
								<th width="15%"><span><s:message code="a02EventType4" /></span></th>
								<th width="15%"><span><s:message code="a02Other" /></span></th>
								<th width="15%"><span><s:message code="a02Total" /></span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt1" /></td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(2,1)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(5,1)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(2,1)+finishReasonCount(5,1)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt2" /></td>
								<td class="text-right">{{finishReasonCount(1,1)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(2,1)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(5,2)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(1,1)+finishReasonCount(2,1)+finishReasonCount(5,2)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt3" /></td>
								<td class="text-right">{{finishReasonCount(1,2)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(1,3)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(5,3)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(1,2)+finishReasonCount(1,3)+finishReasonCount(5,3)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt4" /></td>
								<td class="text-right">{{finishReasonCount(1,3)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(2,4)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(5,4)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(1,3)+finishReasonCount(2,4)+finishReasonCount(5,4)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt5" /></td>
								<td class="text-right">{{finishReasonCount(1,4)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(2,5)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(5,5)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(1,4)+finishReasonCount(2,5)+finishReasonCount(5,5)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt6" /></td>
								<td class="text-right">{{finishReasonCount(1,5)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(5,6)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(1,5)+finishReasonCount(5,6)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt7" /></td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(4,1)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(5,7)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(4,1)+finishReasonCount(5,7)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt8" /></td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(4,2)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(5,8)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(4,2)+finishReasonCount(5,8)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt9" /></td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(4,3)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(5,9)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(4,3)+finishReasonCount(5,9)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Finish5ReasonOpt10" /></td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCount(4,4)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(5,10)|number:0}}</td>
								<td class="text-right">{{finishReasonCount(4,4)+finishReasonCount(5,10)|number:0}}</td>
							</tr>
							<tr>
								<td><s:message code="a02Other" /></td>
								<td class="text-right">{{finishReasonCountTotal(1)|number:0}}</td>
								<td class="text-right">{{finishReasonCountTotal(2)|number:0}}</td>
								<td class="text-center">-</td>
								<td class="text-right">{{finishReasonCountTotal(4)|number:0}}</td>
								<td class="text-right">{{finishReasonCountTotal(5)|number:0}}</td>
								<td class="text-right">{{finishReasonCountTotal(0)|number:0}}</td>
							</tr>

							<tr>
								<th colspan="7"><s:message code="a02Annotation" /></th>
							</tr>
						</tbody>
					</table>


				</div>

			</div>

		</div>


	</div>



	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>