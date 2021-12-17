<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s11.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>

<body class="no-skin" ng-controller="getAppController" id="body">
	<%@ include file="../include/f_navbar.jsp"%>
	
	<section id="main_content">
	<div class="container">
	<div class="row">
	
	<div class="col-md-12">
          <div class="sidebar_button" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><span>選單項目</span></div>
     </div>
				
	<%@ include file="../include/slidebar.jsp"%>
					
		<div id="divQuery" class="col-lg-9 container">
		<div class="row">
			<div class="col-lg-12">
                <div id="filiter">
                  <div class="filiter_line form_data_color">
                    <h6>子系統名稱</h6>
                    <div class="choose_item">
                      <select id="QuerySubsystemId" name="QuerySubsystemId"
						ng-model="QuerySubsystemId" class="form-select col-xs-6"
						ng-options="Subsystem.Id as Subsystem.Id+':'+Subsystem.Name  for  Subsystem in subsystems">
						<option value="" selected><s:message code="all" /></option>
					  </select>
                    </div>
                    <h6>表單名稱</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="QueryName" name="QueryName"
							ng-model="QueryName" class="form-control"
							placeholder="<s:message code="s11Name" />"
							autocomplete="off">
                      </div>
                    </div>
                    <h6>是否啟用</h6>
                    <div class="choose_item">
                      <select id="QueryIsEnable" name="QueryIsEnable"
						ng-model="QueryIsEnable" class="form-select col-xs-6">
						<option value="" selected><s:message code="all" /></option>
						<option value="true"><s:message code="isEnableTrue" /></option>
						<option value="false"><s:message code="isEanbleFalse" /></option>
					  </select>
                    </div>
                    <h6>是否顯示</h6>
                    <div class="choose_item">
                      <select id="QueryIsShow" name="QueryIsShow"
						ng-model="QueryIsShow" class="form-select col-xs-6">
						<option value="" selected><s:message code="all" /></option>
						<option value="true"><s:message code="isShowTrue" /></option>
						<option value="false"><s:message code="isShowFalse" /></option>
					  </select>
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
              
              <div class="col-lg-12 adjust_pos">
                <div class="row">
                  <div class="col-sm-12">
                    <div class="index_title title_style">
                      <h5>表單資料維護</h5>
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
                              	<a href="" ng-click="setSortName('subsystemName')" style="color:white;"> <s:message code="s11subsystemName" /> 
									<i ng-show="sorttype != 'subsystemName'" class="fas fa-fw fa-sort text-muted"></i>
									<i ng-show="sorttype == 'subsystemName' && !sortreverse" class="fas fa-fw fa-caret-down"></i> 
									<i ng-show="sorttype == 'subsystemName' && sortreverse" class="fas fa-fw fa-caret-up"></i>
								</a>
							  </th>
                              <th scope="col" class="td_style">
                              	<a href="" ng-click="setSortName('name')" style="color:white;"> 
	                              	<span><s:message code="s11Name" /></span> 
									<i ng-show="sorttype != 'name'" class="fas fa-fw fa-sort text-muted"></i>
									<i ng-show="sorttype == 'name' && !sortreverse" class="fas fa-fw fa-caret-down"></i> 
									<i ng-show="sorttype == 'name' && sortreverse" class="fas fa-fw fa-caret-up"></i>
								</a>
							  </th>
                              <th scope="col" class="td_style">是否啟用</th>
                              <th scope="col" class="td_style">是否顯示</th>
                              <th scope="col" class="td_style">操作</th>
                            </tr>
                          </thead>
                          <tbody id="addbtn">
                            <tr ng-repeat="item in allitems">
                              <th scope="row" class="td_style">{{$index + 1}}</th>
                              <td class="td_style">{{item.SubsystemName}}</td>
                              <td class="td_style">{{item.Name}}</td>
                              <td class="text-center td_style"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span>
							  </td>
							  <td class="text-center td_style"><span ng-show="{{item.IsShow}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isShowTrue" />'></i> <s:message
										code="isShowTrue" /></span> <span ng-show="{{!item.IsShow}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isShowFalse" />'></i> <s:message
										code="isShowFalse" /></span>
							  </td>
                              <td class="td_style"> 
                                <div class="button_fill_orange btn_s" ng-click="editData(item.Id);">
                                  <p>修改</p>
                                </div>
                                <div class="button_fill_gray btn_s" ng-click="deleteData(item.Id);">
                                  <p>刪除 </p>
                                </div>
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
              </div>
	</div>
	
	<div id="divEdit" class="container col-lg-9" ng-show="btnIns || btnUpd">
		<div class="row">
              <div class="col-lg-12">
                <div id="filiter">
                  <div class="filiter_line subsystem_data_color">
                    <div class="index_title addfunction title_margin">
                      <h5 ng-show="btnIns">新增                    </h5>
                      <h5 ng-show="btnUpd">修改                    </h5>
                    </div>
                    <h6>子系統名稱</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <select id=SubsystemId name="SubsystemId"
							    ng-model="SubsystemId" class="form-select col-xs-6"
								ng-options="Subsystem.Id as Subsystem.Id+':'+Subsystem.Name  for  Subsystem in subsystems"
							    ng-required="true" >
								<option value="" selected><s:message code="s11Select" /></option>
						</select>
                      </div>
                    </div>
                    <h6>表單名稱</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Name" name="Name" ng-model="Name"
							class="form-control" placeholder="<s:message code="s11Name" />" ng-maxlength="2147483647"
							autocomplete="off" ng-required="true">
                      </div>
                    </div>
                    <h6 class="float">是否啟用</h6>
                    <div class="choose_item">
						<toggle ng-model="IsEnable" ng-init="IsEnable=true"
							on='<i class="far fa-fw fa-check-circle"></i>是'
							off='<i class="fas fa-fw fa-minus-circle"></i>否'
							onstyle="btn-success" offstyle="btn-danger"></toggle>
					</div>
					<h6 class="float">是否顯示</h6>
                    <div class="choose_item">
						<toggle ng-model="IsShow" ng-init="IsShow=true"
							on='<i class="far fa-fw fa-check-circle"></i>是'
							off='<i class="fas fa-fw fa-minus-circle"></i>否'
							onstyle="btn-success" offstyle="btn-danger"></toggle>
					</div>
                    <div class="search_btn hcenter saveicon">
                      <div class="button_fill_orange btn_m" ng-show="${actionUpdate} && btnUpd" ng-click="updateData()">
                        <p>儲存</p>
                      </div>
                      <div class="button_fill_orange btn_m" ng-show="${actionCreate} && btnIns" ng-click="createData()">
                        <p>新增</p>
                      </div>
                      <div class="button_fill_gray btn_m" ng-click="closeEdit()"><i class="fas fa-undo-alt"></i>
                        <p>返回</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
	
		
	</div>			
	
	
	</div>
	</div>
	</section>
	
	 <!-- tablet&mobile sidebar lightbox-->
	<%@ include file="../include/mobilesidebar.jsp"%>
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>