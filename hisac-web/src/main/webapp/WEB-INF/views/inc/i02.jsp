<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/inc/i02.js"></script>
<body class="index-login" ng-controller="getAppController" id="body">
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
                    <h6>影片標題</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="QueryTitle" name="QueryTitle" ng-model="QueryTitle" 
                        	class="form-control" placeholder="影片標題" autocomplete="off">
                      </div>
                    </div>
                    <h6>發布日期</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input class="form-control" id="QueryDate" type="date" 
                        	placeholder="發布日期" ng-model="QueryDate"
                        	aria-label="發布日期" aria-describedby="basic-addon2">
                        <div class="input-group-append"></div>
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
                    <h6>是否公開</h6>
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
                      <h5>影音資訊管理</h5>
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
                              <th scope="col" class="td_style">影片標題</th>
                              <th scope="col" class="td_style">發布日期</th>
                              <th class="hcenter" scope="col" class="td_style">是否啟用</th>
                              <th class="hcenter" scope="col" class="td_style">是否公開</th>
                              <th scope="col" class="td_style">操作</th>
                            </tr>
                          </thead>
                          <tbody class="adjust_center" id="addbtn">
                            <tr ng-repeat="item in allitems">
                              <th scope="row">{{$index + 1}}</th>
                              <td class="td_style">{{item.Title}}</td>
                              <td class="td_style">{{item.Date}}</td>
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
                                  <p>編輯</p>
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
                    <h6>影片標題</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Title" name="Title" ng-model="Title"
							class="form-control" placeholder="影片標題" ng-maxlength="2147483647"
							autocomplete="off" ng-required="true">
                      </div>
                    </div>
                    <h6>影片連結網址</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Video_Url" name="Video_Url" ng-model="Video_Url"
							class="form-control" placeholder="影片連結網址" ng-maxlength="2147483647"
							autocomplete="off" ng-required="true">
                      </div>
                    </div>
                    <h6>影片縮圖網址</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Thumbnail_Url" name="Thumbnail_Url" ng-model="Thumbnail_Url"
							class="form-control" placeholder="影片縮圖網址" ng-maxlength="2147483647"
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
	
	
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>