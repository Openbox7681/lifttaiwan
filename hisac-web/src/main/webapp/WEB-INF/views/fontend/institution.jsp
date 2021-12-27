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
                    <div class="tab-content" id="pills-tabContent">
                    	<div class="tab-pane fade show active fade_style" id="pills-mechanism" role="tabpanel"
                            aria-labelledby="pills-mechanism-tab">
                            <div class="col-lg-12">
                            	<div class="row">
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
    </script>
</body>
</html>