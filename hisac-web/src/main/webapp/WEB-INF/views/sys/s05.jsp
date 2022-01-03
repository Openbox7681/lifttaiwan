<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s05.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
 -->
 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css" />
<script>
	var globalSureDisableMember = '<s:message code="globalSureDisableMember" />';
</script>
<body class="no-skin" ng-controller="getAppController" id="body">

	<%@ include file="../include/f_navbar.jsp"%>
	
	
	<section id="main_content">
	<div class="container">
	<div class="row">
	
	<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
     </div>
				
	<%@ include file="../include/slidebar.jsp"%>
	
	<div id="divQuery" class="col-lg-9">
		<div class="row">
			<div class="col-sm-12">
              <div class="index_title title_style">
                <h5>人員基本資料管理</h5>
                </br>
              </div>
            </div>
			<div class="col-lg-12">
				<div id="filiter">
					<div class="filiter_line subsystem_data_color">
					<h6><s:message code="memberAccount" /></h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <div class="form_input form_input_search">
							<input type="text" iid="QueryAccount" name="QueryAccount"
									ng-model="QueryAccount" class="form-control"
									placeholder="<s:message code="memberAccount" />"
									autocomplete="off">
						</div>
                      </div>
                    </div>
                    
                    <h6><s:message code="memberName" /></h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <div class="form_input form_input_search">
							<input type="text" id="QueryName" name="QueryName"
									ng-model="QueryName" class="form-control"
									placeholder="<s:message code="memberName" />"
									autocomplete="off">
						</div>
                      </div>
                    </div>
                    
                    <h6><s:message code="memberEmail" /></h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <div class="form_input form_input_search">
							<input type="text" id="QueryEmail" name="QueryEmail"
									ng-model="QueryEmail" class="form-control"
									placeholder="<s:message code="memberEmail" />"
									autocomplete="off">
						</div>
                      </div>
                    </div>
                    
                    <h6>是否啟用</h6>
                    <div class="choose_item">
                    	<div class="input-group mb-2">
                    	<div class="form_input form_input_search">
                      <select id="QueryIsEnable" name="QueryIsEnable" aria-label="Default select example"
									ng-model="QueryIsEnable" class="form-select">
                        <option value="" selected><s:message code="all" /></option>
						<option value="true"><s:message code="isEnableTrue" /></option>
						<option value="false"><s:message code="isEanbleFalse" /></option>
                      </select>
                      </div>
                      </div>
                    </div>
                    
                    <div class="search_btn hcenter">
                      <div class="button_fill_orange btn_m" ng-click="queryData()">
                        <p>查詢</p>
                      </div>
                      <div class="button_fill_gray btn_m" ng-click="clearData()"><i class="fas fa-undo-alt"></i>
                        <p>清空</p>
                      </div>
                    </div>
					</div>
			</div>
			</div>
		</div>
		
		<div class="col-lg-12 adjust_pos">
                <div class="row">
		
		<div class="col-sm-12">
          <div class="index_title title_style">
            <h5>查詢結果</h5>
          </div>
        </div>
        <div class="row">
			<div class="col-xs-12 col-md-11"  style = "padding : 3px">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>
			<div class="col-sm-12 exportbtn">
              <div class="button_line_orange btn_s" ng-click="openEdit()">
                <p>新增</p>
              </div>
            </div>
		</div>
		
		<div class="col-lg-12">
                    <div class="form_chart">
                      <div class="form_outbor">
				<table class="table caption-top">
					<thead class="tablecolor">
						<tr>
							<th scope="col" class="td_style">編號</th>
							<th scope="col" class="td_style">
								<a href="" ng-click="setSortName('account')" style="color:white;">
									<s:message code="memberAccount" /> <i
									ng-show="sorttype != 'account'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'account' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'account' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
								</a>
							</th>
							<th scope="col" class="td_style">
								<a href="" ng-click="setSortName('memberName')" style="color:white;"> 
									<s:message code="memberName" /> 
									<i ng-show="sorttype != 'memberName'" class="fas fa-fw fa-sort text-muted"></i>
									<i ng-show="sorttype == 'memberName' && !sortreverse" class="fas fa-fw fa-caret-down"></i> 
									<i ng-show="sorttype == 'memberName' && sortreverse" class="fas fa-fw fa-caret-up"></i>
								</a>
							</th>
							<th scope="col" class="td_style">
								<a href="" ng-click="setSortName('email')" style="color:white;"> <s:message
										code="memberEmail" /> <i ng-show="sorttype != 'email'"
									class="fas fa-fw fa-sort text-muted"></i><i
									ng-show="sorttype == 'email' && !sortreverse"
									class="fas fa-fw fa-caret-down"></i> <i
									ng-show="sorttype == 'email' && sortreverse"
									class="fas fa-fw fa-caret-up"></i>
								</a>
							</th>
							<th scope="col" class="td_style">是否啟用</th>
							<th scope="col" class="td_style">操作</th>
						</tr>
					</thead>
					<tbody id="addbtn">
						<tr ng-repeat="item in allitems">
							<th scope="row" class="td_style">{{$index + 1}}</th>
							<td class="td_style">{{item.Account}}</td>
							<td class="td_style">{{item.Name}}</td>
							<td class="td_style">{{item.Email}}</td>
							<td class="text-center td_style"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span>
							  </td>
							  <td class="td_style"> 
                                <div class="button_fill_orange btn_s" ng-click="editData(item.Id);">
                                  <p>修改</p>
                                </div>
                              </td>
						</tr>
					</tbody>
				</table>
			</div>
			</div>
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
	
	<div id="divEdit" class="col-lg-9 container" ng-show="btnIns || btnUpd">
		<div class="row">
			<div class="col-lg-12">
			<div id="filiter">
			<div class="filiter_line subsystem_data_color">
			<div class="index_title addfunction title_margin">
              <h5 ng-show="btnIns">新增                    </h5>
              <h5 ng-show="btnUpd">修改                    </h5>
            </div>
            <form name="myForm">
            <h6><s:message code="memberAccount" /></h6>
             <div class="choose_item">
               <div class="input-group mb-2">
                 <input type="text" id="memberAccount" name="memberAccount"
					ng-model="memberAccount"
					ng-disabled = "!btnIns"
					ng-model-options="{ updateOn: 'default' , allowInvalid:'true'}"
					ng-maxlength="64" ng-pattern="/^[a-zA-Z][a-zA-Z0-9]{3,63}$/"
					ng-keyup="checkMemberAccount()" class="form-control"
					onpaste="return false" oncopy="return false"
					oncut="return false" oncontextmenu="return false"
					ng-copy="$event.preventDefault()"
					ng-cut="$event.preventDefault()"
					ng-paste="$event.preventDefault()"
					placeholder="請輸入帳號"
					autocomplete="off" ng-required="true">
				<h5 class="text-danger"
					ng-show="myForm.memberAccount.$error.required && myForm.memberAccount.$dirty">
					<s:message code="pleaseEnter" />
					<s:message code="memberAdminAccount" />
				</h5>
				<h5 class="text-danger"
					ng-show="!myForm.memberAccount.$error.required && myForm.memberAccount.$invalid && myForm.memberAccount.$dirty">
					<s:message code="memberAccountFormat" />
				</h5>
				<h5 class="text-danger" ng-show="memberAccountVerifyFail">
					<s:message code="memberAccountExist" />
				</h5>
               </div>
             </div>
             
             <h6><s:message code="memberName" /></h6>
              <div class="choose_item">
                <div class="input-group mb-2">
                  <input type="text" id="memberName" name="memberName"
						ng-model="memberName" ng-maxlength="128" class="form-control"
						placeholder="請輸入顯示名稱"
						autocomplete="off" ng-required="true">
					<h5 class="text-danger"
						ng-show="myForm.memberName.$error.required && myForm.memberName.$dirty">
						<s:message code="pleaseEnter" />
						<s:message code="memberAdminName" />
					</h5>
					<h5 class="text-danger"
						ng-show="!myForm.memberName.$error.required && myForm.memberName.$invalid && myForm.memberName.$dirty">
						<s:message code="memberNameFormat" />
					</h5>
                </div>
              </div>
              
              <h6><s:message code="memberEmail" /></h6>
              <div class="choose_item">
                <div class="input-group mb-2">
                  <input type="email" id="memberEmail" name="memberEmail"
						ng-model="memberEmail" ng-maxlength="128" class="form-control"
						placeholder="請輸入電子郵件信箱"
						autocomplete="off" ng-required="true">
					<h5 class="text-danger"
						ng-show="myForm.memberEmail.$error.required && myForm.memberEmail.$dirty">
						<s:message code="pleaseEnter" />
						<s:message code="memberAdminEmail" />
					</h5>
					<h5 class="text-danger"
						ng-show="!myForm.memberEmail.$error.required && myForm.memberEmail.$invalid && myForm.memberEmail.$dirty">
						<s:message code="memberEmailFormat" />
					</h5>
                </div>
              </div>
              
              <h6>密碼</h6>
              <div class="choose_item">
                <div class="input-group mb-2">
                  <input type="password" id="memberCode" name="memberCode"
						ng-model="memberCode"
						ng-pattern="regex"
						class="form-control"
						placeholder="請輸入密碼"
						autocomplete="off" ng-required="true">
					<h5 class="text-danger"
						ng-show="myForm.memberCode.$error.required && myForm.memberCode.$dirty">
						請輸入密碼
					</h5>
                </div>
              </div>
              
              <h6>確認密碼</h6>
              <div class="choose_item">
                <div class="input-group mb-2">
                  <input type="password" id="memberCodeAgain" name="memberCodeAgain"
						ng-model="memberCodeAgain" class="form-control"
						placeholder="再次輸入密碼"
						autocomplete="off" ng-required="true">
					<h5 class="text-danger"
						ng-show="myForm.memberCodeAgain.$error.required && myForm.memberCodeAgain.$dirty">
						請再次輸入密碼
					</h5>
					<h5 class="text-danger"
						ng-show="!myForm.memberCodeAgain.$error.required && myForm.memberCodeAgain.$dirty"
						ng-if="!(memberCode === memberCodeAgain)">
						<s:message code="loginNewCodeNotTheSame" />
					</h5>
                </div>
              </div>
              
              <h6 class="float">是否啟用</h6>
              <div class="choose_item">
				<toggle ng-model="IsEnable" ng-init="IsEnable=true"
					on='<i class="far fa-fw fa-check-circle"></i>是'
					off='<i class="fas fa-fw fa-minus-circle"></i>否'
					onstyle="btn-success" offstyle="btn-danger"></toggle>
			  </div>
			  
					
				<div class="search_btn hcenter saveicon">
                   <div class="button_fill_orange btn_m" 
	                   	ng-show="${actionUpdate} && btnUpd" 
	                   	ng-click="!myForm.$valid || memberAccountVerifyFail || updateData()"
	                   	ng-disabled="!myForm.$valid || memberAccountVerifyFail">
	                     <p>儲存</p>
                   </div>
                   <div class="button_fill_orange btn_m" 
	                   	ng-show="${actionCreate} && btnIns" 
	                   	ng-click="!myForm.$valid || memberAccountVerifyFail || createData()"
	                   	ng-disabled="!myForm.$valid || memberAccountVerifyFail">
                     	<p>新增</p>
                   </div>
                   <div class="button_fill_gray btn_m" ng-click="closeEdit()"><i class="fas fa-undo-alt"></i>
                     <p>返回</p>
                   </div>
                 </div>
                 </form>
			</div>
		</div>
		</div>
		</div>
	</div>
	
	
	
	
	
	</div>
	
	</div>
	
	</section>
	
	
	
	
	
	
	
	
	
	
	
	<div class="modal-body" id="exporExcel" ng-show="false">
	<table>
		<thead>
			<tr>
				<th><s:message code="memberAccount" /></th>
				<th><s:message code="memberName" /></th>
				<th><s:message code="memberPosition" /></th>
				<th><s:message code="memberEmail" /></th> 
				<th>	<s:message code="memberSpareEmail" /></th>
				<th><s:message code="memberMobilePhone" /></th>
				<th><s:message code="memberCityPhone" /></th>			
				<th><s:message code="memberFaxPhone" /></th>						
				<th><s:message code="memberOrgName" /></th>
				<th>會員等級</th>
				<th><s:message code="memberDepartment" /></th>
				<th><s:message code="memberStatus" /></th>
				<th><s:message code="roleName" /></th>							
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="item in allExcelItems">
				<td>{{item.Account}}</td>
				<td>{{item.Name}}</td>
				<td>{{item.Title}}</td>							
				<td>{{item.Email}}</td>
				<td>{{item.SpareEmail}}</td>							
				<td>{{item.MobilePhone}}</td>
				<td>{{item.CityPhone}}</td>
				<td>{{item.FaxPhone}}</td>							
				<td>{{item.OrgName}}</td>
				<td>
					<span ng-if="item.CILevel!=''">{{item.CILevel}}</span>
					<span ng-if="item.CILevel==''">無</span>
				</td>
				<td>{{item.Department}}</td>
				<td>
					<span ng-if="item.Status==0"><s:message code="memberStatus0" /></span> 
					<span ng-if="item.Status==1"><s:message code="memberStatus1" /></span> 
					<span ng-if="item.Status==2"><s:message code="memberStatus2" /></span> 
					<span ng-if="item.Status==3"><s:message code="memberStatus3" /></span>
				</td>
				<td>{{item.Roles| join:', '}}</td>				
			</tr>
		</tbody>
	</table>
	</div>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>