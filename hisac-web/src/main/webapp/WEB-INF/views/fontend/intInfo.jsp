<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index_info.jsp"%>
<!-- =====  vendor  =====-->
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/reset.min.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/5.0.2bootstrap.css">
<!-- =====  google font  =====-->
<!-- <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&amp;display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Hind+Siliguri&amp;family=Noto+Sans+TC&amp;display=swap" rel="stylesheet"> -->
<!-- =====  awesome icon  =====-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/new.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/basicstyle.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/mainstyle.css">
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/fontend/responsive.css">

<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/filiter_keyword_none.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/main.js"> </script>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/resources/js/fontend/echarts.min.js"></script>
<body class="no-skin" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
    <section id="main_content">
        <div class="container">
        	<div class="col-sm-12 intadjust">
        		<div class="row">
        			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="pills-mechanism-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-mechanism" type="button" role="tab"
                                aria-controls="pills-mechanism" aria-selected="true">Top20機構</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-scholar-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-scholar" type="button" role="tab" aria-controls="pills-scholar"
                                aria-selected="false">Top20學者</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-cooperate-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-cooperate" type="button" role="tab"
                                aria-controls="pills-cooperate" aria-selected="false">台灣及其他國家國際合作表現</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="pills-sixarea-tab" data-bs-toggle="pill"
                                data-bs-target="#pills-sixarea" type="button" role="tab" aria-controls="pills-sixarea"
                                aria-selected="false">六大領域國際網絡</button>
                        </li>
                    </ul>
                    <div class="tab-content" id="pills-tabContent">
                    	<div class="tab-pane fade show active fade_style" id="pills-mechanism" role="tabpanel"
                            aria-labelledby="pills-mechanism-tab">
                            <div class="col-lg-12">
                            	<div class="row">
                            		<div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                    <div class="form-check">
                                                                        <div class="all_area_checkbox">
                                                                            <input class="form-check-input"
                                                                                id="flexCheckDefault" type="checkbox"
                                                                                value="">
                                                                            <label class="form-check-label"
                                                                                for="flexCheckDefault">所有領域 </label>
                                                                        </div>
                                                                    </div><span class="su_info"
                                                                        data-index="a">資訊及數位相關產業</span><span
                                                                        class="su_sec" data-index="b">資安卓越產業</span><span
                                                                        class="su_healthy"
                                                                        data-index="c">臺灣精準健康戰略產業</span><span
                                                                        class="su_safe"
                                                                        data-index="d">國防及戰略產業</span><span
                                                                        class="su_power"
                                                                        data-index="e">綠電及再生能源產業</span><span
                                                                        class="su_livelihood"
                                                                        data-index="f">民生及戰備產業</span><span
                                                                        class="su_other" data-index="g">其他產業</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="line2"></div>
                                                    <div class="sub-subject"></div>
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p>送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="chart mt-1">
                                            <!--<img src="../img/img_cubelink.jpg">-->

                                            <div id="top20" style="height: 500px"></div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <div class="col-lg-12 adjust_pos">
                                                    <div class="form_chart">
                                                        <div class="form_outbor">
                                                            <table class="table caption-top">
                                                                <thead class="tablecolor">
                                                                    <tr>
                                                                        <th scope="col">排名/Rank</th>
                                                                        <th scope="col">姓名/Name</th>
                                                                        <th scope="col">機構/Oranization</th>
                                                                        <th scope="col">國家別/Country</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <th scope="row">1</th>
                                                                        <td>Mark</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>335598</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">2</th>
                                                                        <td>Jacob</td>
                                                                        <td>INAF</td>
                                                                        <td>28391 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">3</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>28189 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">4</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>27674 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">5</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25760 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">6</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25633 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">7</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25332 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">8</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25217 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">9</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25032 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">10</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>22344 </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                            	</div>
                            </div>
                        </div>
                        <div class="tab-pane fade fade_style" id="pills-scholar" role="tabpanel"
                            aria-labelledby="pills-scholar-tab">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                    <div class="form-check">
                                                                        <div class="all_area_checkbox">
                                                                            <input class="form-check-input"
                                                                                id="flexCheckDefault" type="checkbox"
                                                                                value="">
                                                                            <label class="form-check-label"
                                                                                for="flexCheckDefault">所有領域 </label>
                                                                        </div>
                                                                    </div><span class="su_info"
                                                                        data-index="a">資訊及數位相關產業</span><span
                                                                        class="su_sec" data-index="b">資安卓越產業</span><span
                                                                        class="su_healthy"
                                                                        data-index="c">臺灣精準健康戰略產業</span><span
                                                                        class="su_safe"
                                                                        data-index="d">國防及戰略產業</span><span
                                                                        class="su_power"
                                                                        data-index="e">綠電及再生能源產業</span><span
                                                                        class="su_livelihood"
                                                                        data-index="f">民生及戰備產業</span><span
                                                                        class="su_other" data-index="g">其他產業</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="line2"></div>
                                                    <div class="sub-subject"></div>
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p>送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="chart mt-1">
                                            <!--<img src="../img/img_bubble-gradient2.jpg">-->

                                            <div id="maxima" style="width: 800px; height: 500px"></div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="row">
                                                <div class="col-lg-12 adjust_pos">
                                                    <div class="form_chart">
                                                        <div class="form_outbor">
                                                            <table class="table caption-top">
                                                                <thead class="tablecolor">
                                                                    <tr>
                                                                        <th scope="col">排名/Rank</th>
                                                                        <th scope="col">作者/Author</th>
                                                                        <th scope="col">發表機構/Publishing Oranization</th>
                                                                        <th scope="col">被引用次數/Number of citations</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <th scope="row">1</th>
                                                                        <td>Mark</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>335598</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">2</th>
                                                                        <td>Jacob</td>
                                                                        <td>INAF</td>
                                                                        <td>28391 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">3</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>28189 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">4</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>27674 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">5</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25760 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">6</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25633 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">7</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25332 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">8</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25217 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">9</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>25032 </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">10</th>
                                                                        <td>Larry</td>
                                                                        <td>Univ British Columbia</td>
                                                                        <td>22344 </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade fade_style" id="pills-cooperate" role="tabpanel"
                            aria-labelledby="pills-cooperate-tab">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                    <div class="form-check">
                                                                        <div class="all_area_checkbox">
                                                                            <input class="form-check-input"
                                                                                id="flexCheckDefault" type="checkbox"
                                                                                value="">
                                                                            <label class="form-check-label"
                                                                                for="flexCheckDefault">所有領域 </label>
                                                                        </div>
                                                                    </div><span class="su_info"
                                                                        data-index="a">資訊及數位相關產業</span><span
                                                                        class="su_sec" data-index="b">資安卓越產業</span><span
                                                                        class="su_healthy"
                                                                        data-index="c">臺灣精準健康戰略產業</span><span
                                                                        class="su_safe"
                                                                        data-index="d">國防及戰略產業</span><span
                                                                        class="su_power"
                                                                        data-index="e">綠電及再生能源產業</span><span
                                                                        class="su_livelihood"
                                                                        data-index="f">民生及戰備產業</span><span
                                                                        class="su_other" data-index="g">其他產業</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="line2"></div>
                                                    <div class="sub-subject"></div>
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p>送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="chart mt-1">
                                            <!--<img src="../img/img_bar.jpg">-->

                                            <div id="country" style="width: 600px; height: 500px"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 intadjust">
                                        <div class="row">
                                            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                                <li class="nav-item" role="presentation">
                                                    <button class="nav-link active" id="pills-int1-tab"
                                                        data-bs-toggle="pill" data-bs-target="#pills-int1" type="button"
                                                        role="tab" aria-controls="pills-int1"
                                                        aria-selected="true">各國國際合作比例</button>
                                                </li>
                                                <li class="nav-item" role="presentation">
                                                    <button class="nav-link" id="pills-int2-tab" data-bs-toggle="pill"
                                                        data-bs-target="#pills-int2" type="button" role="tab"
                                                        aria-controls="pills-int2"
                                                        aria-selected="false">各領域論文國際合作比例</button>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-content" id="pills-tabContent">
                                            <div class="tab-pane fade fade_style show active" id="pills-int1" role="tabpanel"
                                                aria-labelledby="pills-int1-tab">
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-12 adjust_pos">
                                                            <div class="form_chart">
                                                                <div class="form_outbor">
                                                                    <table class="table caption-top">
                                                                        <thead class="tablecolor">
                                                                            <tr>
                                                                                <th scope="col">領域</th>
                                                                                <th scope="col">2011-2015(%)</th>
                                                                                <th scope="col">2015-2019(%)</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">太空科學</th>
                                                                                <td>89.05</td>
                                                                                <td>93.41 </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade fade_style" id="pills-int2" role="tabpanel"
                                                aria-labelledby="pills-int2-tab">
                                                <div class="col-lg-12">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="row">
                                                                <div class="col-lg-12 adjust_pos">
                                                                    <div class="form_chart">
                                                                        <div class="form_outbor">
                                                                            <table class="table caption-top">
                                                                                <thead class="tablecolor">
                                                                                    <tr>
                                                                                        <th scope="col">國家</th>
                                                                                        <th scope="col">2009-2014(%)
                                                                                        </th>
                                                                                        <th scope="col">2015-2019(%)
                                                                                        </th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td>65.43</td>
                                                                                        <td>72.77 </td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade fade_style" id="pills-sixarea" role="tabpanel"
                            aria-labelledby="pills-sixarea-tab">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12">
                                        <div class="info_button info" type="button" data-bs-toggle="modal"
                                            data-bs-target="#myModal"><span>篩選查詢 </span></div>
                                    </div>
                                    <div class="col-lg-12 none">
                                        <div id="filiter">
                                            <div class="filiter_line_dropdown">
                                                <h6>按領域查詢 </h6>
                                                <div class="main-block">
                                                    <div class="main-subject">
                                                        <div class="row g-0">
                                                            <div class="col-lg-12">
                                                                <div class="main-detail" id="main-detail">
                                                                    <div class="form-check">
                                                                        <div class="all_area_checkbox">
                                                                            <input class="form-check-input"
                                                                                id="flexCheckDefault" type="checkbox"
                                                                                value="">
                                                                            <label class="form-check-label"
                                                                                for="flexCheckDefault">所有領域 </label>
                                                                        </div>
                                                                    </div><span class="su_info"
                                                                        data-index="a">資訊及數位相關產業</span><span
                                                                        class="su_sec" data-index="b">資安卓越產業</span><span
                                                                        class="su_healthy"
                                                                        data-index="c">臺灣精準健康戰略產業</span><span
                                                                        class="su_safe"
                                                                        data-index="d">國防及戰略產業</span><span
                                                                        class="su_power"
                                                                        data-index="e">綠電及再生能源產業</span><span
                                                                        class="su_livelihood"
                                                                        data-index="f">民生及戰備產業</span><span
                                                                        class="su_other" data-index="g">其他產業</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="line2"></div>
                                                    <div class="sub-subject"></div>
                                                </div>
                                                <div class="search_btn hright">
                                                    <div class="button_fill_orange btn_m">
                                                        <p>送出查詢 </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12">
                                        <div class="chart">
                                            <!--<img src="../img/img_bubble-chart.jpg">-->
                                        
                                            <div id="connect" style="width: 500px; height: 500px"></div>
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
    <div class="modal fade fade_style" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="col-md-12">
                        <div id="filiter">
                            <div class="filiter_lightbox_dropdown">
                                <h6>按領域查詢</h6>
                                <div class="form-check ad_form">
                                    <input class="form-check-input" id="filiterall" type="checkbox" value="">
                                    <label class="form-check-label" for="filiterall"></label>所有領域
                                </div>
                                <div class="line2"></div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea1" aria-expanded="false" aria-controls="filiterarea1"><i
                                        class="fas fa-chevron-up"></i><span>資訊及數位相關產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea1">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea2" aria-expanded="false" aria-controls="filiterarea2"><i
                                        class="fas fa-chevron-up"></i><span>資安卓越產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea2">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea3" aria-expanded="false" aria-controls="filiterarea3"><i
                                        class="fas fa-chevron-up"></i><span>臺灣精準健康戰略產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea3">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea4" aria-expanded="false" aria-controls="filiterarea4"><i
                                        class="fas fa-chevron-up"></i><span>國防及戰略產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea4">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea5" aria-expanded="false" aria-controls="filiterarea5"><i
                                        class="fas fa-chevron-up"></i><span>綠電及再生能源產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea5">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea6" aria-expanded="false" aria-controls="filiterarea6"><i
                                        class="fas fa-chevron-up"></i><span>民生及戰備產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea6">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <button class="filiterLightboxBtn" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#filiterarea7" aria-expanded="false"
                                    aria-controls="#filiterarea7"><i
                                        class="fas fa-chevron-up"></i><span>其他產業</span></button>
                                <div class="collapse filiterlightbox_form_check" id="filiterarea7">
                                    <div class="card card-body">
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter1" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter1"></label>發動機技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter2" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter2"></label>戰機關鍵技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter3" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter3"></label>船舶核心技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter4" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter4"></label>推進系統技術
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter5" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter5"></label>衛星通訊
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" id="filiter6" type="checkbox" value="">
                                            <label class="form-check-label" for="filiter6"></label>太空檢測技術
                                        </div>
                                    </div>
                                </div>
                                <!--saerch btn------------------------>
                                <div class="search_btn hcenter">
                                    <div class="button_fill_orange btn_m" id="ad_btn_search">
                                        <p>送出查詢 </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
	
	<script type="text/javascript">
        top20();
        maxima();
        country();
        connect();


        function top20() {
            var dom = document.getElementById("top20");
            var myChart = echarts.init(dom);
            var app = {};

            var option;

            option = {
                series: [
                    {
                        type: 'treemap',
                        data: [
                            {
                                name: 'nodeA',
                                value: 10,
                                children: [
                                    {
                                        name: 'nodeAa',
                                        value: 4
                                    },
                                    {
                                        name: 'nodeAb',
                                        value: 6
                                    }
                                ]
                            },
                            {
                                name: 'nodeB',
                                value: 20,
                                children: [
                                    {
                                        name: 'nodeBa',
                                        value: 20,
                                        children: [
                                            {
                                                name: 'nodeBa1',
                                                value: 20
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }


        //千里馬計畫優勢學術領域
        function maxima() {
            var dom = document.getElementById("maxima");
            var myChart = echarts.init(dom);
            var app = {};

            var option;

            const data = [
                [
                    [28604, 77, 17096869, 'Australia', 1990],
                    [31163, 77.4, 27662440, 'Canada', 1990],
                    [1516, 68, 1154605773, 'China', 1990],
                    [13670, 74.7, 10582082, 'Cuba', 1990],
                    [28599, 75, 4986705, 'Finland', 1990],
                    [29476, 77.1, 56943299, 'France', 1990],
                    [31476, 75.4, 78958237, 'Germany', 1990],
                    [28666, 78.1, 254830, 'Iceland', 1990],
                    [1777, 57.7, 870601776, 'India', 1990],
                    [29550, 79.1, 122249285, 'Japan', 1990],
                    [2076, 67.9, 20194354, 'North Korea', 1990],
                    [12087, 72, 42972254, 'South Korea', 1990],
                    [24021, 75.4, 3397534, 'New Zealand', 1990],
                    [43296, 76.8, 4240375, 'Norway', 1990],
                    [10088, 70.8, 38195258, 'Poland', 1990],
                    [19349, 69.6, 147568552, 'Russia', 1990],
                    [10670, 67.3, 53994605, 'Turkey', 1990],
                    [26424, 75.7, 57110117, 'United Kingdom', 1990],
                    [37062, 75.4, 252847810, 'United States', 1990]
                ],
                [
                    [44056, 81.8, 23968973, 'Australia', 2015],
                    [43294, 81.7, 35939927, 'Canada', 2015],
                    [13334, 76.9, 1376048943, 'China', 2015],
                    [21291, 78.5, 11389562, 'Cuba', 2015],
                    [38923, 80.8, 5503457, 'Finland', 2015],
                    [37599, 81.9, 64395345, 'France', 2015],
                    [44053, 81.1, 80688545, 'Germany', 2015],
                    [42182, 82.8, 329425, 'Iceland', 2015],
                    [5903, 66.8, 1311050527, 'India', 2015],
                    [36162, 83.5, 126573481, 'Japan', 2015],
                    [1390, 71.4, 25155317, 'North Korea', 2015],
                    [34644, 80.7, 50293439, 'South Korea', 2015],
                    [34186, 80.6, 4528526, 'New Zealand', 2015],
                    [64304, 81.6, 5210967, 'Norway', 2015],
                    [24787, 77.3, 38611794, 'Poland', 2015],
                    [23038, 73.13, 143456918, 'Russia', 2015],
                    [19360, 76.5, 78665830, 'Turkey', 2015],
                    [38225, 81.4, 64715810, 'United Kingdom', 2015],
                    [53354, 79.1, 321773631, 'United States', 2015]
                ]
            ];
            option = {
                backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [
                    {
                        offset: 0,
                        color: '#f7f8fa'
                    },
                    {
                        offset: 1,
                        color: '#cdd0d5'
                    }
                ]),
                title: {
                    text: '',
                    left: '5%',
                    top: '3%'
                },
                legend: {
                    right: '10%',
                    top: '3%',
                    data: ['1990', '2015']
                },
                grid: {
                    left: '8%',
                    top: '10%'
                },
                xAxis: {
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    }
                },
                yAxis: {
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    },
                    scale: true
                },
                series: [
                    {
                        name: '1990',
                        data: data[0],
                        type: 'scatter',
                        symbolSize: function (data) {
                            return Math.sqrt(data[2]) / 5e2;
                        },
                        emphasis: {
                            focus: 'series',
                            label: {
                                show: true,
                                formatter: function (param) {
                                    return param.data[3];
                                },
                                position: 'top'
                            }
                        },
                        itemStyle: {
                            shadowBlur: 10,
                            shadowColor: 'rgba(120, 36, 50, 0.5)',
                            shadowOffsetY: 5,
                            color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
                                {
                                    offset: 0,
                                    color: 'rgb(251, 118, 123)'
                                },
                                {
                                    offset: 1,
                                    color: 'rgb(204, 46, 72)'
                                }
                            ])
                        }
                    },
                    {
                        name: '2015',
                        data: data[1],
                        type: 'scatter',
                        symbolSize: function (data) {
                            return Math.sqrt(data[2]) / 5e2;
                        },
                        emphasis: {
                            focus: 'series',
                            label: {
                                show: true,
                                formatter: function (param) {
                                    return param.data[3];
                                },
                                position: 'top'
                            }
                        },
                        itemStyle: {
                            shadowBlur: 10,
                            shadowColor: 'rgba(25, 100, 150, 0.5)',
                            shadowOffsetY: 5,
                            color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [
                                {
                                    offset: 0,
                                    color: 'rgb(129, 227, 238)'
                                },
                                {
                                    offset: 1,
                                    color: 'rgb(25, 183, 207)'
                                }
                            ])
                        }
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }


        //台灣及其他國家表現
        function country() {
            var dom = document.getElementById("country");
            var myChart = echarts.init(dom);
            var app = {};

            var option;

            const posList = [
                'left',
                'right',
                'top',
                'bottom',
                'inside',
                'insideTop',
                'insideLeft',
                'insideRight',
                'insideBottom',
                'insideTopLeft',
                'insideTopRight',
                'insideBottomLeft',
                'insideBottomRight'
            ];
            app.configParameters = {
                rotate: {
                    min: -90,
                    max: 90
                },
                align: {
                    options: {
                        left: 'left',
                        center: 'center',
                        right: 'right'
                    }
                },
                verticalAlign: {
                    options: {
                        top: 'top',
                        middle: 'middle',
                        bottom: 'bottom'
                    }
                },
                position: {
                    options: posList.reduce(function (map, pos) {
                        map[pos] = pos;
                        return map;
                    }, {})
                },
                distance: {
                    min: 0,
                    max: 100
                }
            };
            app.config = {
                rotate: 90,
                align: 'left',
                verticalAlign: 'middle',
                position: 'insideBottom',
                distance: 15,
                onChange: function () {
                    const labelOption = {
                        rotate: app.config.rotate,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        position: app.config.position,
                        distance: app.config.distance
                    };
                    myChart.setOption({
                        series: [
                            {
                                label: labelOption
                            },
                            {
                                label: labelOption
                            },
                            {
                                label: labelOption
                            },
                            {
                                label: labelOption
                            }
                        ]
                    });
                }
            };
            const labelOption = {
                show: true,
                position: app.config.position,
                distance: app.config.distance,
                align: app.config.align,
                verticalAlign: app.config.verticalAlign,
                rotate: app.config.rotate,
                formatter: '{c}  {name|{a}}',
                fontSize: 16,
                rich: {
                    name: {}
                }
            };
            option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['Forest', 'Steppe', 'Desert', 'Wetland']
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true },
                        dataView: { show: true, readOnly: false },
                        magicType: { show: true, type: ['line', 'bar', 'stack'] },
                        restore: { show: true },
                        saveAsImage: { show: true }
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: { show: false },
                        data: ['2012', '2013', '2014', '2015', '2016']
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'Forest',
                        type: 'bar',
                        barGap: 0,
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [320, 332, 301, 334, 390]
                    },
                    {
                        name: 'Steppe',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [220, 182, 191, 234, 290]
                    },
                    {
                        name: 'Desert',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [150, 232, 201, 154, 190]
                    },
                    {
                        name: 'Wetland',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: [98, 77, 101, 99, 40]
                    }
                ]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }


        //6大領域國際網路
        function connect() {
            var dom = document.getElementById("connect");
            var myChart = echarts.init(dom);
            var app = {};

            var option;

            var ROOT_PATH = 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';

            myChart.showLoading();
            $.getJSON(ROOT_PATH + '/data/asset/data/les-miserables.json', function (graph) {
                myChart.hideLoading();
                graph.nodes.forEach(function (node) {
                    node.label = {
                        show: node.symbolSize > 30
                    };
                });
                option = {
                    title: {
                        text: 'Les Miserables',
                        subtext: 'Default layout',
                        top: 'bottom',
                        left: 'right'
                    },
                    tooltip: {},
                    legend: [
                        {
                            // selectedMode: 'single',
                            data: graph.categories.map(function (a) {
                                return a.name;
                            })
                        }
                    ],
                    animationDuration: 1500,
                    animationEasingUpdate: 'quinticInOut',
                    series: [
                        {
                            name: 'Les Miserables',
                            type: 'graph',
                            layout: 'none',
                            data: graph.nodes,
                            links: graph.links,
                            categories: graph.categories,
                            roam: true,
                            label: {
                                position: 'right',
                                formatter: '{b}'
                            },
                            lineStyle: {
                                color: 'source',
                                curveness: 0.3
                            },
                            emphasis: {
                                focus: 'adjacency',
                                lineStyle: {
                                    width: 10
                                }
                            }
                        }
                    ]
                };
                myChart.setOption(option);
            });

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
    </script>
</body>
</html>