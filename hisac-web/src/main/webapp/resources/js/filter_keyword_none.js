

//scroll to top

$(document).ready(function (){
    $("#top_button").click(function (){
        $('html, body').animate({
            scrollTop: $("#top_pos").offset().top
        }, 800);
    });
});
//call datepicker
$(function() {
    $( "#datepicker1" ).datepicker();
} );
$(function() {
    $( "#datepicker2" ).datepicker();
} );

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





//click scholars_btn show-i_top_scholars-pages
$(function() {                       
  $("#scholar_click").click(function() { 
    $(".scholars_chart").addClass("show_chart");
  });
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
    const subjectDict= {
    'a': [{
        "Name" :"a1", 
        "Flag" : true
    },
    {
        "Name" :"a2", 
        "Flag" : true
    }
        ],
    'b': [{
        "Name" :"b1", 
        "Flag" : true
    },
    {
        "Name" :"b2", 
        "Flag" : true
    }
        ],
    'c': [{
        "Name" :"c1", 
        "Flag" : true
    },
    {
        "Name" :"c2", 
        "Flag" : true
    }
        ],
    'd':
    [{
        "Name" :"發動機技術", 
        "Flag" : true
    },
    {
        "Name" :"戰機關鍵技術", 
        "Flag" : true
    },
    {
        "Name" :"船舶核心技術", 
        "Flag" : true
    },{
        "Name" :"衛星通訊", 
        "Flag" : true
    },{
        "Name" :"太空發射技術", 
        "Flag" : true
    },
        ]
    ,
    'e': [{
        "Name" :"31", 
        "Flag" : true
    },
    {
        "Name" :"32", 
        "Flag" : true
    }
        ],
    'f': [{
        "Name" :"f1", 
        "Flag" : true
    },
    {
        "Name" :"f2", 
        "Flag" : true
    }
        ],
    'g': [{
        "Name" :"g1", 
        "Flag" : true
    },
    {
        "Name" :"g2", 
        "Flag" : true
    }
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
                "value": textStr.Flag,
                "ng-value" : "IsShow",
                "id": "flexCheckDefault"+i,
                "onclick" : "alert( \"" + String(textStr.Flag) +"\");"
            })).append( $("<label></label>", {
                "class": "form-check-label",
                "for": "flexCheckDefault"+i,
                text: textStr.Name,
            }));
        subSubject.append(element);
        console.log(textStr)
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




