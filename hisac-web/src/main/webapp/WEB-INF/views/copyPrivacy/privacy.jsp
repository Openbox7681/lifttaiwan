<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<%@ include file="../include/head_index.jsp"%>

<body class="no-skin" ng-controller="getAppController" id="stop">
	<%@ include file="../include/head_fontend_navbar.jsp"%>
	
    <section id="banner_content">
      <div class="banner">
        <h2>隱私權宣告</h2>
      </div>
    </section>
    <section id="privacy_content">
      <div class="container">
        <div class="col-md-12 hcenter">
          <div class="icon_taiwan">
          	<img src="<c:out value="${pageContext.request.contextPath}" />/resources/img/icon/icon_logo.svg">
          </div>
          <div class="explain">
            <h5>歡迎蒞臨海外人才橋接平台 (以下簡稱本平台)，為了讓您能夠安心的使用本網站的各項服務與資訊，特此向您說明本網站的隱私權保護政策，以保障您的權益，請您詳閱下列內容：</h5>
          </div>
        </div>
        <div class="col-md-12">
          <h6>第一條、隱私權保護政策的適用範圍</h6>
          <div class="line_div"></div><span>本隱私權政策聲明適用於本平台所提供的服務。</span><span>在您瀏覽或使用本平台各項服務與資訊過程中，涉及個人資料蒐集、處理、利用與保護之原則與相關作法。   </span>
        </div>
        <div class="col-md-12">
          <h6>第二條、資料之蒐集與使用方式</h6>
          <div class="line_div"></div>
          <p>本平台個人資料的蒐集、處理、利用，將遵守中華民國「個人資料保護法」及相關法律之規範。</p>
          <p>本平台蒐集個人資料目的為：提供辦理「海外人才橋接方案」相關事宜，建立使用者與產學研機構線上/線下媒合平台，進而達成延攬高階使用者與滿足國內各機構對中高階人才的需求。</p>
          <p>基於上述蒐集目的，當您於本平台登錄履歷後，產學研機構會員可設定條件查詢求職之履歷資料或由系統依照求職/求才條件推播予產學研機構會員，做為媒合與面試徵才使用。且雙方須同時對彼此有應徵或面試的意願時，系統才開啟雙方聯絡資訊，並可能透過您所填寫的聯絡方式與您聯繫。</p>
          <p>當您使用本平台，我們將使用cookie進行管理及記錄活動紀錄。包括記錄您 IP 位址、瀏覽器種類及時間等軌跡資料，作為本平台增進網站服務之參考依據。若您不願接受cookie的寫入，您可在您使用的瀏覽器功能項中設定隱私權等級為高，即可拒絕cookie的寫入，但可能會導致網站某些功能無法正常執行。   </p>
          <p>為提供精準的服務，我們會針對求職者之履歷資料，進行統計與分析，例如針對使用者畢業國家分佈、學歷、專長、年資、職務、薪資等為研究。分析結果之呈現方式僅有整體之數據及說明文字，除提供內部研究外，本平台得視需要對外公布該等數據及說明文字，但不涉及特定個人資料之公開。                                   </p>
        </div>
        <div class="col-md-12">
          <h6>第三條、資料之管理與維護</h6>
          <div class="line_div"></div>
          <p>自初次登錄資料後，爾後如有編修之需求，本平台提供您自行維護資料的功能。</p>
          <p>除您有權管理及維護您的資料外，當您所登錄履歷/職缺資料不正確、不完整，本平台將會聯繫您，提醒您修改或補填；或者在您的同意下，協助您修改或補填履歷/職缺資料。</p>
        </div>
        <div class="col-md-12">
          <h6>第四條、 資料之保護</h6>
          <div class="line_div"></div>
          <p>為防止外人非法入侵本平台資料庫系統，本平台主機已裝設防火牆系統，並有專人負責資料庫之管理及維護工作。另外，只有經過授權的人員才能接觸您的個人資料，相關處理人員皆簽有保密合約，如有違反保密義務者，將會受到相關的法律處分。如相關單位或機構因業務需要有必要委託本平台提供服務時，本平台亦會嚴格要求其遵守保密義務，並且採取必要檢查程序以確定其將確實遵守。</p>
          <p>為維護您的隱私權益及求職資料之安全，我們會竭盡最大努力，嚴格篩選徵才機構，敬請安心登錄您的履歷資料。</p>
          <p>對於非善意的使用行為、未遂行為或意圖(包含但不限於：不當地獲取資料、危害網站的安全系統、有損本平台或他人權利的侵權行為)，本平台有權要求檢調機關、司法機關或行政機關協助調查，並向該行為者求償。         </p>
          <p>請妥善保管您的帳號密碼及求職資料，不要將任何資料，尤其是帳號密碼提供給任何人。在您登入系統完成各項作業後，務必記得登出帳號，並定時更換您的密碼，以防他人盜用。</p>
        </div>
        <div class="col-md-12">
          <h6>第五條、隱私權保護政策之修正</h6>
          <div class="line_div"></div><span>本平台保留增刪修改本隱私權保護政策內容之權利，所有修改條款應自公告於網站上後生效，不個別通知。</span>
          <div class="divider">     </div>
        </div>
      </div>
    </section>
	<div class="footer_space"></div>
	<%@ include file="../include/footer.jsp"%>
</body>
</html>