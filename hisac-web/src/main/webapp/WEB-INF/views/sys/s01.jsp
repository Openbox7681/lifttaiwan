<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/sys/s01.js"></script>
<style>
	.td_style{
		text-align: center!important;
		width: 25%!important;
	}
	.title_style{
		text-align: left!important;
	}
	.title_margin{
		text-align: left!important;
		margin: 1rem 0;
	}
</style>
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
			<div class="col-lg-12">
                <div id="filiter">
                  <div class="filiter_line subsystem_data_color">
                    <h6>子系統名稱</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <div class="form_input form_input_search">
							<input type="text" id="QueryName" name="QueryName"
								ng-model="QueryName" class="form-control"
								placeholder="<s:message code="subsystemName" />"
								autocomplete="off">
						</div>
                      </div>
                    </div>
                    <h6>是否啟用</h6>
                    <div class="choose_item">
                      <select id="QueryIsEnable" name="QueryIsEnable" aria-label="Default select example"
									ng-model="QueryIsEnable" class="form-select">
                        <option value="" selected><s:message code="all" /></option>
						<option value="true"><s:message code="isEnableTrue" /></option>
						<option value="false"><s:message code="isEanbleFalse" /></option>
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
		</div>
		
		<div class="col-lg-12 adjust_pos">
                <div class="row">
                  <div class="col-sm-12">
                    <div class="index_title title_style">
                      <h5>子系統資料維護</h5>
                    </div>
                  </div>
                  <div class="row">
			<div class="col-xs-12 col-md-11"  style = "padding : 3px">
				<div class="help-block"></div>
				<%@ include file="./../include/table_row_select.jsp"%>
			</div>
		</div>
                  <div class="col-lg-12">
                    <div class="form_chart">
                      <div class="form_outbor">
                        <table class="table caption-top">
                          <thead class="tablecolor">
                            <tr>
                              <th scope="col" class="td_style">編號</th>
                              <th scope="col" class="td_style">子系統名稱</th>
                              <th scope="col" class="td_style">狀態</th>
                              <th scope="col" class="td_style">操作</th>
                            </tr>
                          </thead>
                          <tbody id="addbtn">
                            <tr ng-repeat="item in allitems">
                              <th scope="row" class="td_style">{{$index + 1}}</th>
                              <td class="td_style">{{item.Name}}</td>
                              <td class="text-center td_style"><span ng-show="{{item.IsEnable}}"><i
									class="far fa-fw fa-check-circle text-success"
									title='<s:message code="isEnableTrue" />'></i> <s:message
										code="isEnableTrue" /></span> <span ng-show="{{!item.IsEnable}}"><i
									class="fas fa-fw fa-minus-circle text-danger"
									title='<s:message code="isEanbleFalse" />'></i> <s:message
										code="isEanbleFalse" /></span></td>
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
	
	<div id="divEdit" class="col-lg-9" ng-show="btnIns || btnUpd">
            <div class="row">
              <div class="col-lg-12">
                <div id="filiter">
                  <div class="filiter_line subsystem_data_color">
                    <div class="index_title addfunction title_margin">
                      <h5>修改                    </h5>
                    </div>
                    <h6>子系統名稱</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Name" name="Name" ng-model="Name"
									class="form-control"
									placeholder="<s:message code="subsystemName" />"
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
                    <div class="search_btn hcenter saveicon">
                      <div class="button_fill_orange btn_m" ng-click="updateData()">
                        <p>儲存</p>
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