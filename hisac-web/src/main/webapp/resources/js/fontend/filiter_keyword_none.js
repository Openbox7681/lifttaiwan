

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
        'a1',
        'a2',
        'a3',
        'a4',
        ],
    'b': [
        'b1',
        'b2',
        'b3',
        'b4',
        'b5',
        ],
    'c': [
        'c1',
        'c2',
        'c3',
        'c4',
        'c5',
        ],
    'd': [
        '發動機技術',
        '戰機關鍵技術',
        '船舶核心技術',
        '推進系統技術',
        '衛星通訊',
        '太空檢測技術',
        '太空發射技術',
        ],
    'e': [
        'e1',
        'e2',
        'e3',
        'e4',
        'e5',
        ],
    'f': [
        'f1',
        'f2',
        'f3',
        'f4',
        'f5',
        ],
    'g': [
        'g1',
        'g2',
        'g3',
        'g4',
        'g5',
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
                "value": "",
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




