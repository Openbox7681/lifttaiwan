<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="<c:out value="${pageContext.request.contextPath}" />/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/cyb/c05d.js"></script>
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
				</form>
			</div>
			
		<div class="row">
			<div class="col-xs-12 col-md-6 no_padding">
				<div class="help-block"></div>				
			</div>

		</div>
		<div class="row">
			<div class="table-responsive">
				<table
					class="table table-striped table-bordered table-hover table_customer table_gray">
					<thead>
						<tr>																		
							<th>#</th>							
							<th>檔名</th>							
							<th>MD5</th>
							<th>惡意檔案類型</th>
							<th>防毒軟體掃描結果</th>
						</tr>
					</thead>
					<tbody>
                       <tr ng-repeat="r in result">                                   
                          <th>{{ $index + 1 }}</th>                            
                          <th>{{ r.fileName }}</th>
						  <!-- MD5 -->
                           <td>{{ r.res.Hash.md5 }}</td>
                           <!-- Malware Type -->
                           <td ng-if="r.res.Type == null">掃描中... <i class="fa fa-spinner fa-spin"></i></td>
                           <td ng-if="r.res.Type != null">
                           	<span ng-if="r.res.Type == 'Safety'">-</span>
                           	<span ng-if="r.res.Type != 'Safety'">
                           	<span ng-if="r.res.Type.hybrid == null">{{ r.res.Type }}</span>
                           		<span ng-if="r.res.Type.hybrid != null">
                           			{{ r.res.Type['Main Type'] }}
                           			<br>
                             		( {{ r.res.Type.hybrid }} )
                            		</span>
                           	</span>
                           </td>
                           <!-- 5 Antivirus Softwares -->
                                    <td>
                                        <ul>
                                            <li ng-repeat="v in ['Fprot','Fsecure','clamav','sophos','comodo']">
                                                <span>{{v}}</span>
                                                <span>
                                                    <span ng-if="r.res.Vendor[v] == null">掃描中... <i class="fa fa-spinner fa-spin"></i></span>
                                                    <span ng-if="r.res.Vendor[v] != null">
                                                        <span ng-if="r.res.Vendor[v] == 'clear'" style="color:rgb(44, 164, 151)"><i
                                                                class="fas fa-check-circle"></i> {{ r.res.Vendor[v]
                                                            }}</span>
                                                        <span ng-if="r.res.Vendor[v] != 'clear'" style="color:rgb(219, 63, 38)"><i
                                                                class="fas fa-exclamation-triangle"></i> {{
                                                            r.res.Vendor[v] }}</span>
                                                    </span>
                                                </span>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>			
					</tbody>
				</table>
			</div>
			<%@ include file="./../include/table_row_empty.jsp"%>			
		</div>
	</div>
		</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>
