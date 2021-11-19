<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c05.js"></script>
<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/navbar.jsp"%>
	<div id="divQuery" class="container">
		<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_green">
				<img
					src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon-security.svg" />
				<b><c:out value="${appName}" /></b>
			</h4>
				<div>
					<div class="well well-sm">
						<h5 >
                            <b>請上傳.zip壓縮檔供系統掃描</b>
                        </h5>
                        <h6>
                            VADER惡意程式特徵識別模組整合市面上多款知名商用級防毒軟體，提供使用者現行所有防毒廠商最新的靜態分析結果。此外，本系統所有的偵測行為皆在封閉環境中運行，分析檔案同時亦確保使用者上傳樣本的私密性，只要將檔案壓縮成zip就可以上傳掃描
                        </h6>
					</div>					
				</div>
			
		<div class="row">
			<div style="display: inline-block" ngf-select="" id="file"
				ng-model="file" name="file" ngf-pattern=".zip" accept=".zip"
				ngf-accept="'.zip'" >			
				<button id="SelectAttachment" class="btn btn-primary">
					<i class="fas fa-fw fa-file fa-lg"></i> 選擇檔案
				</button>
				<span>{{file.name}}</span>
			</div>			
			<div ng-show="file.name == null"><i class="fas fa-exclamation-circle"></i>檔案之副檔名須為：.zip</div>
			<button class="btn btn-success" ng-click="importZip()"
				ng-show="file.name!=null">
				上傳檔案
			</button>
		</div>

			<div class="col-xs-12 shadow_board">
				<form name="queryForm">													
				</form>
			</div>
			<div class="row">
			<h4 class="form_heading form_heading_fix form_heading_gray">
				<big><i class="fas fa-fw fa-list fa-lg"></i></big><b><s:message
						code="globalSearchResult" /></b>
			</h4>
		</div>
		<div class="row">
			<div class="col-xs-12 col-md-6 no_padding">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>

		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>																		
							<th><a href="" ng-click="setSortName('filename')"> 檔名 <i ng-show="sorttype != 'filename'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'filename' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'filename' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							
							<th><a href="" ng-click="setSortName('createTime')"> 建立時間 <i ng-show="sorttype != 'createTime'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'createTime' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'createTime' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
							</a></th>							
							<th>動作</th>
						</tr>
					</thead>
					<tbody>
								<tr ng-repeat="item in allitems">									
									<td>
									<a ng-href="${pageContext.request.contextPath}/cyb/c05d?{{item.Id}}">{{item.Filename}}</a>
									</td>																		
									<td>{{item.CreateTime}}</td>
									<td><a href="#" class="btn btn-sm btn-info"
								ng-click="deleteData(item.Id);"><i
									class="far fa-fw fa-trash-alt"></i> <s:message code="btnDelete" /></a>
							</td>
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
		</div>
	</div>
		</div>

		
	</div>	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
