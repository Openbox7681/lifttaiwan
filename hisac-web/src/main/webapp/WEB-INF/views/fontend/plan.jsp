<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index.jsp"%>
<link rel="stylesheet"
	href="<c:out value="${pageContext.request.contextPath}" />/resources/css/new.css">
<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
    <section id="banner_content">
      <div class="banner2">
        <h2>計畫介紹</h2>
        <h5>About</h5>
      </div>
    </section>
    <section id="planinfo_content">
      <div class="container">
        <div class="col-lg-12 hcenter">
          <div class="title_cube"></div>
          <h5 class="adjustpd ">方案簡介</h5>
          <p>在目前全球人才競逐趨勢下，為回應臺灣產學研各界對前瞻科研領域人才需求及海外人才歸國期待，並配合政府推動「前瞻基礎建設」及「產業創新領域」，科技部自106年開始推動「海外人才歸國橋接方案(LIFT, Leaders in Future Trends)」，以號召我國赴海外留學的人才，返國貢獻所學，達到激勵產業創新及刺激技術躍升之成效為方案目標。</p>
          <p>在第1期方案基礎上，「109年度海外人才橋接方案(LIFT2.0)」（下稱本方案），將建置平臺，積極促成海外學人與國內產學研機構進行線上/線下媒合，並提供海外學人來回機票補助與全程免費食宿，安排海外學人參加「海外學人國內交流會」，與國內產學研機構進行面對面交流，以促成海外學人返/來臺就業發展。</p>
          <div class="plan_imgouter">
          	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/plan_img.jpg" alt="">
          </div>
        </div>
        <div class="col-lg-12">
          <div class="index_title title_style">
            <h5>申請人資格</h5>
            <p>1. 年齡在45歲(含)以下(1975年1月1日後出生)。</p>
            <p>2. 學經歷資格(四擇一)：</p>
            <p>(1) 具教育部「外國大學參考名冊」所列院校之國外大學博士學位且2年(含)以上最後一份有薪工作不在臺灣者：以十大產業創新相關領域者優先。（綠能科技、生醫產業、智慧機械、國防航太、新農業、循環經濟、數位國家創新經濟、文化科技、晶片設計與半導體產業等） </p>
            <p>(2)具教育部「外國大學參考名冊」所列院校之國外大學碩士學位且須具備人工智慧3年(含)以上相關國外工作經驗者。                                        </p>
          </div>
          <div class="index_title title_style">
            <h5>申請方式</h5>
            <p>1. 申請人請至本方案人才交流網站線上申請並完成使用帳號註冊，網址：https://lifttaiwan.stpi.narl.org.tw</p>
            <p>2. 本方案第1梯次收件截止日期為109年7月31日(五)中午12:00（臺北時間GMT+08:00），請於截止日前詳實完整填寫線上申請資料(包含學經歷、自傳等)，並上傳下列文件：</p>
            <p class="adjust_p">(1)國內外學歷證明。</p>
            <p class="adjust_p"> (2)就業媒合申請書，內容須包含相關有利於審查之項目，如：</p>
            <p class="adjust_p">最近2年內參與研究或工作之經驗及成果。</p>
            <p class="adjust_p">所具備專長對臺灣科技發展之必要性及可能貢獻。</p>
            <p class="adjust_p">求職規劃(含求職方向及希望任職產業/機構之範疇及領域)。 </p>
            <p>(3)於申請截止日前，最近2年內在國外就學/就業證明，或者近3年出入境證明。</p>
            <p>3. 申請文件不全、不符合規定者或逾期申請者，不予受理。</p>
          </div>
          <div class="index_title title_style">
            <h5>甄選方式</h5>
            <p>109年度本方案甄選審查流程共分為初審與複審，將邀請相關領域產學界專家實質審查，並視需要透過電話或視訊訪談。</p>
          </div>
          <div class="index_title title_style">
            <h5>名單公告</h5>
            <p>第1梯次入選名單於109年8月下旬在本方案網站公告。第2梯次預定於109年10月上旬在本方案網站公告入選名單。                   </p>
          </div>
          <div class="index_title title_style">
            <h5>海外學人國內交流會</h5>
            <p>109年度本方案預計辦理兩梯次「海外學人國內交流會」，第1梯次預定於109年10月19日(一)至109年10月28日(三)舉辦，第2梯次預定於109年12月14日(一)至109年12月23日(三)舉辦。主辦單位將保留實際辦理時程調動之權利並於計畫網站公告。</p>
          </div>
          <div class="index_title title_style">
            <h5>補助方式及配套措施</h5>
            <p>1.  獲選返/來臺參加「海外學人國內交流會」者，本方案將提供返/來臺交流之機票補助金，以參加者本人單次來回經濟艙機票補助為限，僅提供自居住地或工作地來回臺灣最直接航程之機票費用；且須提供電子機票、登機證及購票證明共計3項領據資料辦理後續撥付。但如自第三地來臺灣者，則以第三地來回臺灣機票核銷，且其補助金額不得高於自居住地或工作地來回臺灣最直接航程之機票費用，相關補助辦法另於計畫網站公告。</p>
            <p>2.  獲選返/來臺參加「海外學人國內交流會」者，本方案將安排專業交流活動、國家建設及國內產學研機構參觀，以及舉辦人才交流媒合會等(共計為期10天)，活動期間將提供免費住宿、飲食及交通服務安排，不另支付其他費用。</p>
          </div>
          <div class="index_title title_style">
            <h5>權利義務</h5>
            <p>1. 申請人接獲入選參加【第1梯次】「海外學人國內交流會」者，若因臨時有事須改參加【第2梯次】，最晚須於公告入選日後15天內通知LIFT計畫辦公室，逾期需重新提出返/來臺申請。</p>
            <p>2. 補助經費僅限參加該年度活動。海外學人於參加該梯次之「海外學人國內交流會」報到當日前與本計畫辦公室簽定計畫約定書，因故無法出席交流會或無法完成簽約者，視同放棄本方案所有權利。</p>
            <p>3. 海外學人若於在臺交流期間獲產學研界延聘，應通報「LIFT專案計畫辦公室」並提出相關獲聘證明後，經專案辦公室同意即可提出停止參加後續交流會活動。</p>
            <p>4. 海外學人在臺交流期間，若有違反我國法令、返/來臺前已在臺灣有專任職務或其他不配合之情事，將保留解約權利。</p>
            <p>5. 海外學人於交流期滿或提前終止時，應依合約書規定繳交「國內交流成果報告書」，並於獲得補助之後2年內應配合本方案之電話或書面調查，以追蹤本方案成效。</p>
          </div>
          <div class="index_title title_style">
            <h5>其他注意事項</h5>
            <p>1. 本公告如有未盡事宜，得隨時修訂公布之，或依合約書及其他有關規定辦理。</p>
            <p>2. LIFT計畫辦公室聯絡方式</p>
            <p>專線：+886-2-2737-7426馮小姐；+886-2-2737-7746林小姐</p>
            <p>Email：lift@narlabs.org.tw</p>
          </div>
        </div>
      </div>
    </section>
    <div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>

	
</body>
</html>