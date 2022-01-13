

//scroll to top
$(document).ready(function(){
  // Add smooth scrolling to all links
  $("a").on('click', function(event) {

    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 800, function(){

        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
});



$(document).scroll(function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('#top_button').fadeIn(2000);
    } else {
      $('#top_button').fadeOut();
    }
});

//click six area span focus
$(function() {                       
  $(".su_info").click(function() { 
    $(".su_sec").removeClass("it_color");
    $(".su_healthy").removeClass("it_color");
    $(".su_safe").removeClass("it_color");
    $(".su_power").removeClass("it_color");
    $(".su_livelihood").removeClass("it_color");
    $(".su_other").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});

$(function() {                       
  $(".su_sec").click(function() { 
    $(".su_info").removeClass("it_color");
    $(".su_healthy").removeClass("it_color");
    $(".su_safe").removeClass("it_color");
    $(".su_power").removeClass("it_color");
    $(".su_livelihood").removeClass("it_color");
    $(".su_other").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});
$(function() {                       
  $(".su_healthy").click(function() { 
    $(".su_sec").removeClass("it_color");
    $(".su_info").removeClass("it_color");
    $(".su_safe").removeClass("it_color");
    $(".su_power").removeClass("it_color");
    $(".su_livelihood").removeClass("it_color");
    $(".su_other").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});
$(function() {                       
  $(".su_livelihood").click(function() { 
    $(".su_sec").removeClass("it_color");
    $(".su_healthy").removeClass("it_color");
    $(".su_safe").removeClass("it_color");
    $(".su_power").removeClass("it_color");
    $(".su_info").removeClass("it_color");
    $(".su_other").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});
$(function() {                       
  $(".su_safe").click(function() { 
    $(".su_sec").removeClass("it_color");
    $(".su_healthy").removeClass("it_color");
    $(".su_info").removeClass("it_color");
    $(".su_power").removeClass("it_color");
    $(".su_livelihood").removeClass("it_color");
    $(".su_other").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});
$(function() {                       
  $(".su_power").click(function() { 
    $(".su_sec").removeClass("it_color");
    $(".su_healthy").removeClass("it_color");
    $(".su_safe").removeClass("it_color");
    $(".su_info").removeClass("it_color");
    $(".su_livelihood").removeClass("it_color");
    $(".su_other").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});


$(function() {                       
  $(".su_other").click(function() { 
    $(".su_sec").removeClass("it_color");
    $(".su_healthy").removeClass("it_color");
    $(".su_safe").removeClass("it_color");
    $(".su_info").removeClass("it_color");
    $(".su_livelihood").removeClass("it_color");
    $(".su_info").removeClass("it_color");
    $($(this)).addClass("it_color");
  });
});













//click dataname show
const action_map = [
    $(".data_subsidy"),
    $(".results"),
    $(".network"),
    $(".profess"),
    ]

$(function(){
    const subsidyDOM = $('.subsidy div');
    for(var i = 0; i < subsidyDOM.length; i++){
        subsidyDOM[i].onclick = (e) => {
            for(var j=0; j<action_map.length; j++){
                if(e.currentTarget.dataset.index === j.toString()) action_map[j].addClass("show_chart");
                else action_map[j].removeClass('show_chart');
            };
        };
    };
});


//click rotate
$(".filiterLightboxBtn").click(
    function(e){
        const lightBoxIcon = $(e.currentTarget).find(".fa-chevron-up");
        console.log(lightBoxIcon);
        lightBoxIcon.toggleClass("ani_rotate");
});









//search filiter

let currMainSubject;
let currSubSubject;
const openKeyword = (index) => {
    const keywordDict = {
    '0': [
        'k01',
        'k02',
        'k03',
    ],
    '1': [
        'k11',
        'k12',
        'k13',
        'k14',
    ],
    }
    const keywords = $('.keyword-block');
    if(index !== currSubSubject){
        keywords.fadeIn();
        currSubSubject = index;
        return;
    }
    keywords.fadeOut();
    currSubSubject = '';

}
const openSubSubject = (index) => {
    console.log('test');
    const subjectDict= {
    'a': [
        'AI晶片',
        '人工智慧',
        '半導體材料',
        '半導體設備',
        '行動通訊',
        '系統軟體',
        '物聯網(含感測器)',
        '晶圓代工',
        '晶圓封測',
        '智慧運輸',
        '量子科技',
        '資安支援',
        '資安防護',
        '資料中心(含伺服器)',
        '運動科技',
        '零接觸生活科技',
        '網路通訊',
        '數位娛樂',
        '數據科學',
        '衛星通訊',
        '積體電路設計'
        ],
    'b': [
        
        ],
    'c': [
        '再生醫療',
        '健康管理',
        '智慧醫材',
        '智慧醫療',
        '精準醫療(含遠距醫療)',
        ],
    'd': [
        '太空發射技術',
        '太空檢測技術',
        '推進系統技術',
        '船舶核心技術',
        '發動機技術',
        '衛星通訊',
        '戰機關鍵技術',
        ],
    'e': [
        '太陽光電',
        '水下基礎',
        '風機系統',
        '海事工程',
        '電力設備',
        '離岸風電'
        ],
    'f': [
        '民生物資與食品加工產業鏈',
        '再生能源',
        '安全庫存',
        '救災資源資料庫'
        ],
    'g': [
        '其他'
        ],
    }
    const subSubject = $('.sub-subject');
    const keywords = $('.keyword-block');
    $('.sub-subject div.form-check').remove();
    for(let [i, textStr] of subjectDict[index].entries()){
        var element = $("<div></div>", {
            "class": "form-check",
            "data-index": i,
            }).append( $("<input>", {
                "class": "form-check-input",
                "type": "checkbox",
                "value": textStr,
                "name" : "checkbox2",
                "id": "flexCheckDefault"+i,
            })).append( $("<label></label>", {
                "class": "form-check-label",
                "for": "flexCheckDefault"+i,
                text: textStr,
            }));
        subSubject.append(element);
    };
    if(currSubSubject !== ''){
        keywords.fadeOut();
        currSubSubject = '';
    }
    if(index !== currMainSubject){
        subSubject.fadeIn();
        currMainSubject = index;
        return;
    }
    subSubject.fadeOut();
    currMainSubject = '';

}


window.onload = () => {
    eventInitialize();
    currMainSubject= '';
    currSubSubject = '';
}


eventInitialize = () => {
    console.log('test');
    const detailDom = document.getElementById('main-detail').children;
    for(var i = 0; i < detailDom.length; i++){
        const target = detailDom[i];
        target.onclick = () => {
            openSubSubject(target.dataset.index);
        };
    }
}




