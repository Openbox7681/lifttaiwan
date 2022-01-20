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
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/resources/css/all.min.css">
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
                                                                        <th scope="col">機構/Oranization</th>
                                                                        <th scope="col">國家別/Country</th>
                                                                        <th scope="col">總被引證數/TAC</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <th scope="row">1</th>
                                                                        <td>Harvard University</td>
                                                                        <td>US</td>
                                                                        <td>509544</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">2</th>
                                                                        <td>University of California, Los Angeles</td>
                                                                        <td>US</td>
                                                                        <td>288695</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">3</th>
                                                                        <td>University of Toronto</td>
                                                                        <td>Canada</td>
                                                                        <td>287620</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">4</th>
                                                                        <td>University of Michigan</td>
                                                                        <td>US</td>
                                                                        <td>269772</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">5</th>
                                                                        <td>University of Washington</td>
                                                                        <td>US</td>
                                                                        <td>247978</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">6</th>
                                                                        <td>The University of Tokyo</td>
                                                                        <td>Japan</td>
                                                                        <td>247161</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">7</th>
                                                                        <td>Stanford University</td>
                                                                        <td>US</td>
                                                                        <td>236799</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">8</th>
                                                                        <td>Johns Hopkins University</td>
                                                                        <td>US</td>
                                                                        <td>225704</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">9</th>
                                                                        <td>University of Pennsylvania</td>
                                                                        <td>US</td>
                                                                        <td>222160</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">10</th>
                                                                        <td>University of Minnesota</td>
                                                                        <td>US</td>
                                                                        <td>216489</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">11</th>
                                                                        <td>University of Oxford</td>
                                                                        <td>UK</td>
                                                                        <td>211076</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">12</th>
                                                                        <td>University of California, San Francisco</td>
                                                                        <td>US</td>
                                                                        <td>204677</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">13</th>
                                                                        <td>Chinese Academy of Sciences</td>
                                                                        <td>China</td>
                                                                        <td>204316</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">14</th>
                                                                        <td>University of Cambridge</td>
                                                                        <td>UK</td>
                                                                        <td>199231</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">15</th>
                                                                        <td>University of Pittsburgh-Pittsburgh Campus</td>
                                                                        <td>US</td>
                                                                        <td>190815</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">16</th>
                                                                        <td>Duke University</td>
                                                                        <td>US</td>
                                                                        <td>190139</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">17</th>
                                                                        <td>University of California, Berkeley</td>
                                                                        <td>US</td>
                                                                        <td>187489</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">18</th>
                                                                        <td>Yale University</td>
                                                                        <td>US</td>
                                                                        <td>186970</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">19</th>
                                                                        <td>Columbia University</td>
                                                                        <td>US</td>
                                                                        <td>180307</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">20</th>
                                                                        <td>University of California, San Diego</td>
                                                                        <td>US</td>
                                                                        <td>178862</td>
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
                                                                        <th scope="col">平均被引證數/AAC</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <th scope="row">1</th>
                                                                        <td>Peterson, Daniel</td>
                                                                        <td>University South Florida</td>
                                                                        <td>1560</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">2</th>
                                                                        <td>Abecasis, Goncalo R.</td>
                                                                        <td>University Michigan</td>
                                                                        <td>1471</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">3</th>
                                                                        <td>Ferlay, Jacques</td>
                                                                        <td>International Agency for Research on Cancer</td>
                                                                        <td>1439</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">4</th>
                                                                        <td>Gabriel, Stacey B.</td>
                                                                        <td></td>
                                                                        <td>1357</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">5</th>
                                                                        <td>Tamura, Koichiro</td>
                                                                        <td>Tokyo Metropolitan University</td>
                                                                        <td>1317</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">6</th>
                                                                        <td>Flicek, Paul</td>
                                                                        <td>Wellcome Trust Sanger Institute</td>
                                                                        <td>1241</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">7</th>
                                                                        <td>Haussler, David</td>
                                                                        <td>University Calif Santa Cruz</td>
                                                                        <td>1215</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">8</th>
                                                                        <td>Gerstein, Mark</td>
                                                                        <td>Yale University</td>
                                                                        <td>1154</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">9</th>
                                                                        <td>Mardis, Elaine R.</td>
                                                                        <td>Washington University</td>
                                                                        <td>1112</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">10</th>
                                                                        <td>Lawrence, Michael S.</td>
                                                                        <td>Harvard Medical School</td>
                                                                        <td>1098</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">11</th>
                                                                        <td>Chin, Lynda</td>
                                                                        <td>The University of Texas MD Anderson Cancer Center</td>
                                                                        <td>1040</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">12</th>
                                                                        <td>Schultz, Nikolaus</td>
                                                                        <td>Memorial Sloan Kettering Cancer Center</td>
                                                                        <td>1013</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">13</th>
                                                                        <td>Siegel, Rebecca L.</td>
                                                                        <td>American Cancer Society</td>
                                                                        <td>979</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">14</th>
                                                                        <td>Getz, Gad</td>
                                                                        <td>Massachusetts General Hospital</td>
                                                                        <td>941</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">15</th>
                                                                        <td>Gibbs, Richard A.</td>
                                                                        <td>Baylor College of Medicine</td>
                                                                        <td>909</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">16</th>
                                                                        <td>Weinstein, John N.</td>
                                                                        <td>The University of Texas MD Anderson Cancer Center</td>
                                                                        <td>902</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">17</th>
                                                                        <td>Mathers, Colin</td>
                                                                        <td>World Health Organization</td>
                                                                        <td>894</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">18</th>
                                                                        <td>Kellis, Manolis</td>
                                                                        <td>Massachusetts Institute of Technology</td>
                                                                        <td>891</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">19</th>
                                                                        <td>Snyder, Michael</td>
                                                                        <td>Stanford University</td>
                                                                        <td>879</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th scope="row">20</th>
                                                                        <td>Park, Peter J.</td>
                                                                        <td>Harvard Medical School</td>
                                                                        <td>855</td>
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
	<%@ include file="../include/footer_fondtend.jsp"%>
	
	<script type="text/javascript">
        top20();
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
                                name: 'Harvard University',
                                value: 509544
                            },
                            {
                                name: 'University of California, Los Angeles',
                                value: 288695
                            },
                            {
                                name: 'University of Toronto',
                                value: 287620
                            },
                            {
                                name: 'University of Michigan',
                                value: 269772
                            },
                            {
                                name: 'University of Washington',
                                value: 247978
                            },
                            {
                                name: 'The University of Tokyo',
                                value: 247161
                            },
                            {
                                name: 'Stanford University',
                                value: 236799
                            },
                            {
                                name: 'Johns Hopkins University',
                                value: 225704
                            },
                            {
                                name: 'University of Pennsylvania',
                                value: 222160
                            },
                            {
                                name: 'University of Minnesota',
                                value: 216489
                            },
                            {
                                name: 'University of Oxford',
                                value: 211076
                            },
                            {
                                name: 'University of California, San Francisco',
                                value: 204677
                            },
                            {
                                name: 'Chinese Academy of Sciences',
                                value: 204316
                            },
                            {
                                name: 'University of Cambridge',
                                value: 199231
                            },
                            {
                                name: 'University of Pittsburgh-Pittsburgh Campus',
                                value: 190815
                            },
                            {
                                name: 'Duke University',
                                value: 190139
                            },
                            {
                                name: 'University of California, Berkeley',
                                value: 187489
                            },
                            {
                                name: 'Yale University',
                                value: 186970
                            },
                            {
                                name: 'Columbia University',
                                value: 180307
                            },
                            {
                                name: 'University of California, San Diego',
                                value: 178862
                            }
                        ]
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