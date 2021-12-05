

//scroll to top

$(document).ready(function (){
    $("#top_button").click(function (){
        $('html, body').animate({
            scrollTop: $("#top_pos").offset().top
        }, 800);
    });
});



//call datepicker
//$(function() {
//    $("#datepicker").datepicker();
//});




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



$(function() {                       
  $(".sub-subject span").click(function() { 
    $($(this)).addClass("it_color");
  });
});
















const changeFiliterFirstColor = (i) => {
    const dom = $(".it_filiter_first");
    const lIcons = dom.find('p');
    const lIcon  = lIcons.filter(function(index) {
        return index === i;
    });
    lIcons.removeClass('it_color');
    lIcon.addClass('it_color');
};

const changeFiliterSecondColor = (i) => {
    const dom = $(".main-detail");
    const lIcons = dom.find('span');
    const lIcon  = lIcons.filter(function(index) {
        return index === i;
    });
    lIcons.removeClass('it_color');
    lIcon.addClass('it_color');
};


$(function() {
    let firstIndex;
    let keywordsIsOpen;
    const keywordsDict = {
        0: [
            'k1',
            'k2',
            'k3',
        ],
        1: [
            'k4',
            'k5',
            'k6',
            'k7',
        ],
        2: [
            'k7',
            'k7',
            'k7',
            'k7',
            'k7',            
        ],
        3: [
            'k4',
            'k5',
            'k6',
            'k4',
        ],
        4: [
            'k4',
            'k2',
            'k6',
            'k7',
        ],                        
    }
    const subjectsDict = {
        0: [
            '發動機技術',
            '戰機關鍵技術',
            '船舶核心技術',
            '推進系統技術',
            '衛星通訊',
            '太空檢測技術',
            '太空發射技術',
        ],
        1: [
            'a1',
            'a2',
            'a3',
        ],
        2: [
            'b1',
            'b2',
            'b3',
            'b4',
        ],
        3: [
            'apple',
            'banana',
            'candy',
            'dog',
            'window',
        ],
        4: [
            'aaaaa',
            'bbbbb',
            'ccccc',
            'dddddd',
            'eeee'
        ],
        5: [
            'aeea',
            'bbcvbb',
            'cccerwc',
            'dddrewd',
            'eeweree'
        ],        
    }
    const createKeywords = (index) => {

        const filiterKeyword = $(".it_filiter_showkeyword1");
        const keywords = keywordsDict[index];
        filiterKeyword.fadeOut(500, () => {
            filiterKeyword.find('.form-check').remove();
            for(var i = 0 ; i < keywords.length; i++){
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
                        text: keywords[i],
                    }));
                filiterKeyword.append(element);
            };
        });
        filiterKeyword.fadeIn(500);
    }
    const createSubjects = (index) => {
        if(index === firstIndex) return; 
        const filiterSub = $(".it_filiter_sub1");
        const filiterSubElements = subjectsDict[index];
        filiterSub.fadeOut(500, () => {
            filiterSub.find("p").remove();
            for(var i = 0; i < filiterSubElements.length; i++){
                var element = $("<p></p>", {
                    "data-index": i,
                    on: {
                    "click": (e) => {
                        createKeywords(e.currentTarget.dataset.index);
                        keywordsIsOpen = 1;
                        },
                    },
                    text: filiterSubElements[i],
                });
                filiterSub.append(element);
            }
        });
        filiterSub.fadeIn(500);
    };
     
    $(".it_info").click(function(){
        if(keywordsIsOpen){
            $(".it_filiter_showkeyword1").fadeOut(500);
            keywordsIsOpen = 0;
        }
        createSubjects(0);
        changeFiliterFirstColor(0);
        changeFiliterSecondColor(0);        
        firstIndex = 0;
    });
    $(".it_sec").click(function(){
        if(keywordsIsOpen){
            $(".it_filiter_showkeyword1").fadeOut(500);
            keywordsIsOpen = 0;
        }
        createSubjects(1);
        changeFiliterFirstColor(1);
        firstIndex = 1;
    });
                         
    $(".it_healthy").click(function(){
        if(keywordsIsOpen){
            $(".it_filiter_showkeyword1").fadeOut(500);
            keywordsIsOpen = 0;
        }
        createSubjects(2);
        changeFiliterFirstColor(2);
        firstIndex = 2;

    });                    
    $(".it_safe").click(function(){
        if(keywordsIsOpen){
            $(".it_filiter_showkeyword1").fadeOut(500);
            keywordsIsOpen = 0;
        }
        createSubjects(3);
        changeFiliterFirstColor(3);
        firstIndex = 3;
    });                     
    $(".it_power").click(function(){
        if(keywordsIsOpen){
            $(".it_filiter_showkeyword1").fadeOut(500);
            keywordsIsOpen = 0;
        }
        createSubjects(4);
        changeFiliterFirstColor(4);
        firstIndex = 4;

    });                     
    $(".it_livelihood").click(function(){
        if(keywordsIsOpen){
            $(".it_filiter_showkeyword1").fadeOut(500);
            keywordsIsOpen = 0;
        }
        createSubjects(5);
        changeFiliterFirstColor(5);
        firstIndex = 5;
    });


});
                      






$(".filiterLightboxBtn").click(
    function(e){
        const lightBoxIcon = $(e.currentTarget).find(".fa-chevron-up");
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
        'd1',
        'd2',
        'd3',
        'd4',
        'd5',
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
    $('.sub-subject span').remove();
    for(let [i, textStr] of subjectDict[index].entries()){
        var element = $("<span></span", {
            "data-index": i,
            on: {
            "click": () => {
            openKeyword(i);
            }
        }
    });
    element.text(textStr);
    subSubject.append(element);
    }
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
    const detailDom = document.getElementById('main-detail').children;
    for(var i = 0; i < detailDom.length; i++){
        const target = detailDom[i];
        target.onclick = () => {
            openSubSubject(target.dataset.index);
        };
    }
}




