<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head.jsp"%>
<script
	src="${pageContext.request.contextPath}/resources/plugins/angular/angular-sanitize.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inc/i01.js"></script>
<script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
<script src="https://unpkg.com/i18next/dist/umd/i18next.js"></script>

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
                    <h6>文章標題</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="QueryTitle" name="QueryTitle" ng-model="QueryTitle" 
                        	class="form-control" placeholder="文章標題" autocomplete="off">
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
                      <h5>國際合作個案管理</h5>
                    </div>
                  </div>
                  <div class="row">
					<div class="col-xs-12 col-md-11"  style = "padding : 3px">
						<div class="help-block"></div>
						<%@ include file="./../include/table_row_select.jsp"%>
					</div>
					<div class="col-sm-12 exportbtn">
	                    <div class="button_line_orange btn_s" ng-click="openEdit()">
	                      <p>新增文章</p>
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
                              <th scope="col" class="td_style">文章標題</th>
                              <th scope="col" class="td_style">發布日期</th>
                              <th class="hcenter td_style" scope="col">是否啟用</th>
                              <th class="hcenter td_style" scope="col">是否公開</th>
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



	<div id="divEdit" class="col-lg-9" ng-show="btnIns || btnUpd">
		<div class="row">
              <div class="col-lg-12">
                <div id="filiter">
                  <div class="filiter_line subsystem_data_color">
                    <div class="index_title addfunction title_margin">
                      <h5 ng-show="btnIns">新增                    </h5>
                      <h5 ng-show="btnUpd">修改                    </h5>
                    </div>
                    <h6>文章標題</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Title" name="Title" ng-model="Title"
							class="form-control" placeholder="文章標題" ng-maxlength="2147483647"
							autocomplete="off" ng-required="true">
                      </div>
                    </div>
                    <h6>作者</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Author" name="Author" ng-model="Author"
							class="form-control" placeholder="作者" ng-maxlength="2147483647"
							autocomplete="off" ng-required="true">
                      </div>
                    </div>
                    <h6>關鍵字</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                        <input type="text" id="Tag" name="Tag" ng-model="Tag"
							class="form-control" placeholder="關鍵字請以『，』分開" ng-maxlength="2147483647"
							autocomplete="off" ng-required="true">
                      </div>
                    </div>
                    <h6 class="float">排序</h6>
                    <div class="choose_item"> 
                    	<input type="number" id="Sort" name="Sort" ng-model="Sort">
                    </div>
                    <h6>文章內容</h6>
                    <div class="choose_item contentfile">
                      <div class="input-group mb-2"></div>
                    </div>
                    <h6></h6>
                    <div class="choose_item contentfile">
                      <div id="div1"></div>
                    </div>
                    <h6 class="float">是否啟用</h6>
                    <div class="choose_item">
						<toggle ng-model="IsEnable" ng-init="IsEnable=true"
							on='<i class="far fa-fw fa-check-circle"></i>是'
							off='<i class="fas fa-fw fa-minus-circle"></i>否'
							onstyle="btn-success" offstyle="btn-danger"></toggle>
					</div>
					<h6 class="float">是否公開</h6>
                    <div class="choose_item">
						<toggle ng-model="IsShow" ng-init="IsShow=true"
							on='<i class="far fa-fw fa-check-circle"></i>是'
							off='<i class="fas fa-fw fa-minus-circle"></i>否'
							onstyle="btn-success" offstyle="btn-danger"></toggle>
					</div>
                    <h6>縮圖上傳</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                      	<input type="file" id="img_upload_file_article" data-target="file-uploader" multiple="multiple"/>
                      	<input type="hidden" name="img_upload_base_article" id="img_upload_base_article" />
                      </div>
                    </div>
                    <h6>縮圖預覽</h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                      </div>
                    </div>
                    <h6></h6>
                    <div class="choose_item">
                      <div class="input-group mb-2">
                      	<img width="200px" height="100px" id="img_upload_show_article" >
                      </div>
                    </div>
                    <br>
                    <div class="search_btn hcenter saveicon">
                      <div class="button_fill_gray btn_m" ng-show="${actionCreate} && btnIns" ng-click="createData(0)">
                      	<i class="fas fa-save"></i>
                        <p>暫存</p>
                      </div>
                      <div class="button_fill_gray btn_m" ng-show="${actionUpdate} && btnUpd" ng-click="updateData(0)">
                      	<i class="fas fa-save"></i>
                        <p>暫存</p>
                      </div>
                      <div class="button_fill_gray btn_m" ng-show="${actionCreate} && btnIns" ng-click="createAndClose(0)">
                      	<i class="fas fa-save"></i>
                        <p>暫存並離開                                            </p>
                      </div>
                      <div class="button_fill_gray btn_m" ng-show="${actionCreate} && btnUpd" ng-click="updateAndClose(0)">
                      	<i class="fas fa-save"></i>
                        <p>暫存並離開                                            </p>
                      </div>
                      <div class="button_fill_orange btn_m iconnone" ng-show="${actionCreate} && btnIns" ng-click="createData(1)">
                        <p>送出</p>
                      </div>
                      <div class="button_fill_orange btn_m iconnone" ng-show="${actionUpdate} && btnUpd" ng-click="updateData(1)">
                        <p>送出</p>
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