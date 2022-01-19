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
                        <div class="tab-pane fade show active fade_style" id="pills-cooperate" role="tabpanel"
                            aria-labelledby="pills-cooperate-tab">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="chart mt-1" id="picture1">
                                            <!--<img src="../img/img_bar.jpg">-->

                                            <div id="country" style="width: 1200px; height: 500px"></div>
                                        </div>
                                        <div class="chart mt-1" id="picture2" style="display:none">
                                            <!--<img src="../img/img_bar.jpg">-->

                                            <div id="country2" style="width: 1200px; height: 500px"></div>
                                        </div>
                                        <div class="chart mt-1" id="picture3" style="display:none">
                                            <!--<img src="../img/img_bar.jpg">-->

                                            <div id="country3" style="width: 1200px; height: 500px"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 intadjust">
                                        <div class="row">
                                            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                                <li class="nav-item" role="presentation">
                                                    <button class="nav-link active" id="pills-int1-tab"
                                                        data-bs-toggle="pill" data-bs-target="#pills-int1" type="button"
                                                        role="tab" aria-controls="pills-int1"
                                                        aria-selected="true" onclick="picture1()">
                                                        各國學術論文國際合作比例</button>
                                                </li>
                                                <li class="nav-item" role="presentation">
                                                    <button class="nav-link" id="pills-int2-tab" data-bs-toggle="pill"
                                                        data-bs-target="#pills-int2" type="button" role="tab"
                                                        aria-controls="pills-int2"
                                                        aria-selected="false" onclick="picture2()">
                                                        各國論文國際合作與否之相對影響力比較</button>
                                                </li>
                                                <li class="nav-item" role="presentation">
                                                    <button class="nav-link" id="pills-int3-tab" data-bs-toggle="pill"
                                                        data-bs-target="#pills-int3" type="button" role="tab"
                                                        aria-controls="pills-int3"
                                                        aria-selected="false" onclick="picture3()">
                                                        我國各學術領域論文之國際合作比例</button>
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
                                                                                <th scope="col" class="td_style">國家</th>
                                                                                <th scope="col" class="td_style">2013-2017</th>
                                                                                <th scope="col" class="td_style">2014-2018</th>
                                                                                <th scope="col" class="td_style">2015-2019</th>
                                                                                <th scope="col" class="td_style">總計</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                            <tr>
                                                                                <th scope="row">中國</th>
                                                                                <td class="td_style">25.67</td>
                                                                                <td class="td_style">26.2</td>
                                                                                <td class="td_style">26.61</td>
                                                                                <td class="td_style">78.48</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">丹麥</th>
                                                                                <td class="td_style">63.34</td>
                                                                                <td class="td_style">65.1</td>
                                                                                <td class="td_style">66.69</td>
                                                                                <td class="td_style">195.13</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">日本</th>
                                                                                <td class="td_style">31.25</td>
                                                                                <td class="td_style">32.43</td>
                                                                                <td class="td_style">33.74</td>
                                                                                <td class="td_style">97.42</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">以色列</th>
                                                                                <td class="td_style">48.96</td>
                                                                                <td class="td_style">50.03</td>
                                                                                <td class="td_style">51.11</td>
                                                                                <td class="td_style">150.1</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">印度</th>
                                                                                <td class="td_style">24.35</td>
                                                                                <td class="td_style">25.25</td>
                                                                                <td class="td_style">26.23</td>
                                                                                <td class="td_style">75.83</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">南韓</th>
                                                                                <td class="td_style">30.12</td>
                                                                                <td class="td_style">30.74</td>
                                                                                <td class="td_style">31.49</td>
                                                                                <td class="td_style">92.35</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">美國</th>
                                                                                <td class="td_style">36.7</td>
                                                                                <td class="td_style">38.21</td>
                                                                                <td class="td_style">39.54</td>
                                                                                <td class="td_style">114.45</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">英國</th>
                                                                                <td class="td_style">54.79</td>
                                                                                <td class="td_style">57.12</td>
                                                                                <td class="td_style">58.92</td>
                                                                                <td class="td_style">170.83</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">荷蘭</th>
                                                                                <td class="td_style">60.89</td>
                                                                                <td class="td_style">62.56</td>
                                                                                <td class="td_style">64.21</td>
                                                                                <td class="td_style">187.66</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">新加坡</th>
                                                                                <td class="td_style">64.21</td>
                                                                                <td class="td_style">66.49</td>
                                                                                <td class="td_style">68.68</td>
                                                                                <td class="td_style">199.38</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">瑞士</th>
                                                                                <td class="td_style">70.17</td>
                                                                                <td class="td_style">71.52</td>
                                                                                <td class="td_style">72.77</td>
                                                                                <td class="td_style">214.46</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">瑞典</th>
                                                                                <td class="td_style">63.34</td>
                                                                                <td class="td_style">65.1</td>
                                                                                <td class="td_style">66.69</td>
                                                                                <td class="td_style">195.13</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">臺灣</th>
                                                                                <td class="td_style">30.59</td>
                                                                                <td class="td_style">33.18</td>
                                                                                <td class="td_style">36.09</td>
                                                                                <td class="td_style">99.86</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">德國</th>
                                                                                <td class="td_style">54.35</td>
                                                                                <td class="td_style">55.67</td>
                                                                                <td class="td_style">56.92</td>
                                                                                <td class="td_style">166.94</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">澳洲</th>
                                                                                <td class="td_style">52.79</td>
                                                                                <td class="td_style">54.99</td>
                                                                                <td class="td_style">57.17</td>
                                                                                <td class="td_style">164.95</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row">總計</th>
                                                                                <td class="td_style">711.52</td>
                                                                                <td class="td_style">734.59</td>
                                                                                <td class="td_style">756.86</td>
                                                                                <td class="td_style">2202.97</td>
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
                                                                                        <th scope="col" class="td_style">國家</th>
                                                                                        <th scope="col" class="td_style">Numbers (NO)</th>
                                                                                        <th scope="col" class="td_style">Numbers (YES)</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <th scope="row">丹麥</th>
                                                                                        <td class="td_style">1.05</td>
                                                                                        <td class="td_style">1.87</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">以色列</th>
                                                                                        <td class="td_style">0.78</td>
                                                                                        <td class="td_style">1.79</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">美國</th>
                                                                                        <td class="td_style">1.13</td>
                                                                                        <td class="td_style">1.56</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">荷蘭</th>
                                                                                        <td class="td_style">1.08</td>
                                                                                        <td class="td_style">1.86</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞士</th>
                                                                                        <td class="td_style">1.13</td>
                                                                                        <td class="td_style">1.86</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">瑞典</th>
                                                                                        <td class="td_style">0.96</td>
                                                                                        <td class="td_style">1.71</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">澳洲</th>
                                                                                        <td class="td_style">1.01</td>
                                                                                        <td class="td_style">1.71</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">總計</th>
                                                                                        <td class="td_style">7.14</td>
                                                                                        <td class="td_style">12.36</td>
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
                                            <div class="tab-pane fade fade_style" id="pills-int3" role="tabpanel"
                                                aria-labelledby="pills-int3-tab">
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
                                                                                        <th scope="col" class="td_style">領域</th>
                                                                                        <th scope="col" class="td_style">2013-2017</th>
                                                                                        <th scope="col" class="td_style">2014-2018</th>
                                                                                        <th scope="col" class="td_style">2015-2019</th>
                                                                                        <th scope="col" class="td_style">總計</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <th scope="row">工程學</th>
                                                                                        <td class="td_style">20.31</td>
                                                                                        <td class="td_style">23.47</td>
                                                                                        <td class="td_style">27.54</td>
                                                                                        <td class="td_style">71.32</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">分子生物學與遺傳學</th>
                                                                                        <td class="td_style">37.66</td>
                                                                                        <td class="td_style">39.01</td>
                                                                                        <td class="td_style">39.98</td>
                                                                                        <td class="td_style">116.65</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">化學</th>
                                                                                        <td class="td_style">30.44</td>
                                                                                        <td class="td_style">33.16</td>
                                                                                        <td class="td_style">36.07</td>
                                                                                        <td class="td_style">99.67</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">太空科學</th>
                                                                                        <td class="td_style">91.58</td>
                                                                                        <td class="td_style">92.87</td>
                                                                                        <td class="td_style">93.41</td>
                                                                                        <td class="td_style">277.86</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">生物與生化學</th>
                                                                                        <td class="td_style">32.89</td>
                                                                                        <td class="td_style">35.02</td>
                                                                                        <td class="td_style">37.12</td>
                                                                                        <td class="td_style">105.03</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">地球科學</th>
                                                                                        <td class="td_style">62.22</td>
                                                                                        <td class="td_style">63.85</td>
                                                                                        <td class="td_style">65.29</td>
                                                                                        <td class="td_style">191.36</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">免疫學</th>
                                                                                        <td class="td_style">24.2</td>
                                                                                        <td class="td_style">26.22</td>
                                                                                        <td class="td_style">27.65</td>
                                                                                        <td class="td_style">78.07</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">材料科學</th>
                                                                                        <td class="td_style">24.94</td>
                                                                                        <td class="td_style">28.39</td>
                                                                                        <td class="td_style">32.55</td>
                                                                                        <td class="td_style">85.88</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">物理</th>
                                                                                        <td class="td_style">45.02</td>
                                                                                        <td class="td_style">49.75</td>
                                                                                        <td class="td_style">55.15</td>
                                                                                        <td class="td_style">149.92</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">社會科學</th>
                                                                                        <td class="td_style">26.56</td>
                                                                                        <td class="td_style">28.75</td>
                                                                                        <td class="td_style">30.82</td>
                                                                                        <td class="td_style">86.13</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">神經科學與行為學</th>
                                                                                        <td class="td_style">32.72</td>
                                                                                        <td class="td_style">33.02</td>
                                                                                        <td class="td_style">35.59</td>
                                                                                        <td class="td_style">101.33</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">植物與動物科學</th>
                                                                                        <td class="td_style">48.11</td>
                                                                                        <td class="td_style">50.25</td>
                                                                                        <td class="td_style">52.29</td>
                                                                                        <td class="td_style">150.65</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">微生物學</th>
                                                                                        <td class="td_style">34.19</td>
                                                                                        <td class="td_style">38.07</td>
                                                                                        <td class="td_style">40.92</td>
                                                                                        <td class="td_style">113.18</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">經濟與商管</th>
                                                                                        <td class="td_style">32.98</td>
                                                                                        <td class="td_style">35.99</td>
                                                                                        <td class="td_style">40.36</td>
                                                                                        <td class="td_style">109.33</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">農業科學</th>
                                                                                        <td class="td_style">26.24</td>
                                                                                        <td class="td_style">28.75</td>
                                                                                        <td class="td_style">31.48</td>
                                                                                        <td class="td_style">86.47</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">電腦科學</th>
                                                                                        <td class="td_style">25</td>
                                                                                        <td class="td_style">27.75</td>
                                                                                        <td class="td_style">31.5</td>
                                                                                        <td class="td_style">84.25</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">精神病與心理學</th>
                                                                                        <td class="td_style">34.53</td>
                                                                                        <td class="td_style">36.19</td>
                                                                                        <td class="td_style">38.87</td>
                                                                                        <td class="td_style">109.59</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">綜合領域</th>
                                                                                        <td class="td_style">30.48</td>
                                                                                        <td class="td_style">32.47</td>
                                                                                        <td class="td_style">35.94</td>
                                                                                        <td class="td_style">98.89</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">數學</th>
                                                                                        <td class="td_style">49.28</td>
                                                                                        <td class="td_style">54.86</td>
                                                                                        <td class="td_style">60.87</td>
                                                                                        <td class="td_style">165.01</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">環境生態學</th>
                                                                                        <td class="td_style">38.26</td>
                                                                                        <td class="td_style">40.93</td>
                                                                                        <td class="td_style">42.88</td>
                                                                                        <td class="td_style">122.07</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">臨床醫學</th>
                                                                                        <td class="td_style">23.06</td>
                                                                                        <td class="td_style">24.26</td>
                                                                                        <td class="td_style">25.99</td>
                                                                                        <td class="td_style">73.31</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">藥理學與毒理學</th>
                                                                                        <td class="td_style">26.1</td>
                                                                                        <td class="td_style">28.67</td>
                                                                                        <td class="td_style">29.96</td>
                                                                                        <td class="td_style">84.73</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">總計</th>
                                                                                        <td class="td_style">796.77</td>
                                                                                        <td class="td_style">851.7</td>
                                                                                        <td class="td_style">912.23</td>
                                                                                        <td class="td_style">2560.7</td>
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
      
        country();
        country2();
        country3();

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
                formatter: '',
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
                    	data: ['2013-2017', '2014-2018', '2015-2019']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: { show: false },
                            dataView: { show: false, readOnly: false },
                            magicType: { show: false, type: ['line', 'bar', 'stack'] },
                            restore: { show: false },
                            saveAsImage: { show: true }
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: { show: false },
                            data: ['中國', '丹麥', '日本', '以色列', '印度', '南韓', '美國', 
                                '英國', '荷蘭', '新加坡', '瑞士', '瑞典', '臺灣', '德國', '澳洲']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '2013-2017',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [25.67, 63.34, 31.25, 48.96, 24.35, 30.12, 36.7, 54.79, 
                                60.89, 64.21, 70.17, 63.34, 30.59, 54.35, 52.79]
                        },
                        {
                            name: '2014-2018',
                            type: 'bar',
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [26.2, 65.1, 32.43, 50.03, 25.25, 30.74, 38.21, 57.12, 
                                   62.56, 66.49, 71.52, 65.1, 33.18, 55.67, 54.99]
                        },
                        {
                            name: '2015-2019',
                            type: 'bar',
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [26.61, 66.69, 33.74, 51.11, 26.23, 31.49, 39.54, 58.92,
                                   64.21, 68.68, 72.77, 66.69, 36.09, 56.92, 57.17]
                        }
                    ]
                };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
        
        function country2() {
            var dom = document.getElementById("country2");
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
                formatter: '',
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
                    	data: ['Number (NO)', 'Number (YES)']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: { show: false },
                            dataView: { show: false, readOnly: false },
                            magicType: { show: false, type: ['line', 'bar', 'stack'] },
                            restore: { show: false },
                            saveAsImage: { show: true }
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: { show: false },
                            data: ['丹麥', '以色列', '美國', '荷蘭', '瑞士', '瑞典', '澳洲']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: 'Number (NO)',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [1.05, 0.78, 1.13, 1.08, 1.13, 0.96, 1.01]
                        },
                        {
                            name: 'Number (YES)',
                            type: 'bar',
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [1.87, 1.79, 1.56, 1.86, 1.86, 1.71, 1.71]
                        }
                    ]
                };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
        
        function country3() {
            var dom = document.getElementById("country3");
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
                formatter: '',
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
                    	data: ['2013-2017', '2014-2018', '2015-2019']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: { show: false },
                            dataView: { show: false, readOnly: false },
                            magicType: { show: false, type: ['line', 'bar', 'stack'] },
                            restore: { show: false },
                            saveAsImage: { show: true }
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: { show: false },
                            data: ['工程學', '分子生物學與遺傳學', '化學', '太空科學', '生物與生化學',
                            	'地球科學', '免疫學', '材料科學', '物理', '社會科學', '神經科學與行為學',
                            	'植物與動物科學', '微生物學', '經濟與商管', '農業科學', '電腦科學',
                            	'精神病與心理學', '綜合領域', '數學', '環境生態學', '臨床醫學', '藥理學與毒理學']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '2013-2017',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [20.31, 37.66, 30.44, 91.58, 32.89, 62.22, 24.2, 24.94,
                            	45.02, 26.56, 32.72, 48.11, 34.19, 32.98, 26.24, 25, 34.53,
                            	30.48, 49.28, 38.26, 23.06, 26.1]
                        },
                        {
                            name: '2014-2018',
                            type: 'bar',
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [23.47, 39.01, 33.16, 92.87, 35.02, 63.85, 26.22, 28.39,
                            	49.75, 28.75, 33.02, 50.25, 38.07, 35.99, 28.75, 27.75, 36.19,
                            	32.47, 54.86, 40.93, 24.26, 28.67]
                        },
                        {
                            name: '2015-2019',
                            type: 'bar',
                            label: labelOption,
                            emphasis: {
                                focus: 'series'
                            },
                            data: [27.54, 39.98, 36.07, 93.41, 37.12, 65.29, 27.65, 32.55,
                            	55.15, 30.82, 35.59, 52.29, 40.92, 40.36, 31.48, 31.5, 38.87,
                            	35.94, 60.87, 42.88, 25.99, 29.96]
                        }
                    ]
                };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
        
        function picture1() {
        	$("#picture1").show();
        	$("#picture2").hide();
        	$("#picture3").hide();
        }
		function picture2() {
			$("#picture2").show();
        	$("#picture1").hide();
        	$("#picture3").hide();
		}
		function picture3() {
			$("#picture3").show();
        	$("#picture1").hide();
        	$("#picture2").hide();
		}
    </script>
</body>
</html>