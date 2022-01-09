$(document).scroll(function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('#top_button').fadeIn(1000);
    } else {
      $('#top_button').fadeOut();
    }
  });

$(document).ready(function() {
  const inbound = document.querySelector(".inbound");
  const outbound = document.querySelector(".outbound");
  const mechanism = document.querySelector(".mechanism");
  const result = document.querySelector(".result");

  $(".map0").click(function(){
    inbound.classList = ["inbound"];
    outbound.classList = ["outbound"];
    mechanism.classList = ["mechanism"];
    result.classList = ["result"];
  });      
  $(".map1").click(function() {
    inbound.classList = ["inbound", "change_pink1"].join(' ');
    outbound.classList = ["outbound", "change_pink2"].join(' ');
    mechanism.classList = ["mechanism", "change_pink3"].join(' ');
    result.classList = ["result", "change_pink4"].join(' ');
  });

  $(".map2").click(function() {        
    inbound.classList = ["inbound", "change_red1"].join(' ');
    outbound.classList = ["outbound", "change_red2"].join(' ');
    mechanism.classList = ["mechanism", "change_red3"].join(' ');
    result.classList = ["result", "change_red4"].join(' ');
  });

  $(".map3").click(function() {        
    inbound.classList = ["inbound", "change_yellow1"].join(' ');
    outbound.classList = ["outbound", "change_yellow2"].join(' ');
    mechanism.classList = ["mechanism", "change_yellow3"].join(' ');
    result.classList = ["result", "change_yellow4"].join(' ');
  });


  $(".map6").click(function() {        
    inbound.classList = ["inbound", "change_blue1"].join(' ');
    outbound.classList = ["outbound", "change_blue2"].join(' ');
    mechanism.classList = ["mechanism", "change_blue3"].join(' ');
    result.classList = ["result", "change_blue4"].join(' ');
  });

  $(".map4").click(function() {        
    inbound.classList = ["inbound", "change_green1"].join(' ');
    outbound.classList = ["outbound", "change_green2"].join(' ');
    mechanism.classList = ["mechanism", "change_green3"].join(' ');
    result.classList = ["result", "change_green4"].join(' ');
  });

  $(".map5").click(function() {        
    inbound.classList = ["inbound", "change_lightblue1"].join(' ');
    outbound.classList = ["outbound", "change_lightblue2"].join(' ');
    mechanism.classList = ["mechanism", "change_lightblue3"].join(' ');
    result.classList = ["result", "change_lightblue4"].join(' ');
  });
});

















$(function() {                       
	  $(".map0").click(function() { 
	  	$(".u").removeClass("addgreen");
	  	$(".u").removeClass("addyellow");
	  	$(".u").removeClass("addblue");
	  	$(".u").removeClass("addlightblue");
	  	$(".u").removeClass("addred");
	  	$(".u").removeClass("addpink");
	  	
	  	$(".s").removeClass("addgreen");
	  	$(".s").removeClass("addyellow");
	  	$(".s").removeClass("addblue");
	  	$(".s").removeClass("addlightblue");
	  	$(".s").removeClass("addred");
	  	$(".s").removeClass("addpink");
	  	
	  	$(".o").removeClass("addgreen");
	  	$(".o").removeClass("addyellow");
	  	$(".o").removeClass("addblue");
	  	$(".o").removeClass("addlightblue");
	  	$(".o").removeClass("addred");
	  	$(".o").removeClass("addpink");
	  	
	  	$(".v").removeClass("addgreen");
	  	$(".v").removeClass("addyellow");
	  	$(".v").removeClass("addblue");
	  	$(".v").removeClass("addlightblue");
	  	$(".v").removeClass("addred");
	  	$(".v").removeClass("addpink");
	  	
	  	$(".t").removeClass("addgreen");
	  	$(".t").removeClass("addyellow");
	  	$(".t").removeClass("addblue");
	  	$(".t").removeClass("addlightblue");
	  	$(".t").removeClass("addred");
	  	$(".t").removeClass("addpink");
	  });
	});

$(function() {                       
  $(".map1").click(function() { 
  	$(".u").removeClass("addgreen");
  	$(".u").removeClass("addyellow");
  	$(".u").removeClass("addblue");
  	$(".u").removeClass("addlightblue");
  	$(".u").removeClass("addred");
  	
  	$(".s").removeClass("addgreen");
  	$(".s").removeClass("addyellow");
  	$(".s").removeClass("addblue");
  	$(".s").removeClass("addlightblue");
  	$(".s").removeClass("addred");
  	
  	$(".o").removeClass("addgreen");
  	$(".o").removeClass("addyellow");
  	$(".o").removeClass("addblue");
  	$(".o").removeClass("addlightblue");
  	$(".o").removeClass("addred");
  	
  	$(".v").removeClass("addgreen");
  	$(".v").removeClass("addyellow");
  	$(".v").removeClass("addblue");
  	$(".v").removeClass("addlightblue");
  	$(".v").removeClass("addred");
  	
  	$(".t").removeClass("addgreen");
  	$(".t").removeClass("addyellow");
  	$(".t").removeClass("addblue");
  	$(".t").removeClass("addlightblue");
  	$(".t").removeClass("addred");
  	
    $(".u").addClass("addpink");
    $(".t").addClass("addpink");
    $(".s").addClass("addpink");
    $(".o").addClass("addpink");
    $(".v").addClass("addpink");
  });
});

$(function() {                       
	  $(".map2").click(function() { 
	  	$(".u").removeClass("addgreen");
	  	$(".u").removeClass("addyellow");
	  	$(".u").removeClass("addblue");
	  	$(".u").removeClass("addlightblue");
	  	$(".u").removeClass("addpink");
	  	
	  	$(".s").removeClass("addgreen");
	  	$(".s").removeClass("addyellow");
	  	$(".s").removeClass("addblue");
	  	$(".s").removeClass("addlightblue");
	  	$(".s").removeClass("addpink");
	  	
	  	$(".o").removeClass("addgreen");
	  	$(".o").removeClass("addyellow");
	  	$(".o").removeClass("addblue");
	  	$(".o").removeClass("addlightblue");
	  	$(".o").removeClass("addpink");
	  	
	  	$(".v").removeClass("addgreen");
	  	$(".v").removeClass("addyellow");
	  	$(".v").removeClass("addblue");
	  	$(".v").removeClass("addlightblue");
	  	$(".v").removeClass("addpink");
	  	
	  	$(".t").removeClass("addgreen");
	  	$(".t").removeClass("addyellow");
	  	$(".t").removeClass("addblue");
	  	$(".t").removeClass("addlightblue");
	  	$(".t").removeClass("addpink");
	  	
	  	$(".u").addClass("addred");
	    $(".t").addClass("addred");
	    $(".s").addClass("addred");
	    $(".o").addClass("addred");
	    $(".v").addClass("addred");
	  });
	});

$(function() {                       
	  $(".map3").click(function() { 
	  	$(".u").removeClass("addgreen");
	  	$(".u").removeClass("addblue");
	  	$(".u").removeClass("addlightblue");
	  	$(".u").removeClass("addred");
	  	$(".u").removeClass("addpink");
	  	
	  	$(".s").removeClass("addgreen");
	  	$(".s").removeClass("addblue");
	  	$(".s").removeClass("addlightblue");
	  	$(".s").removeClass("addred");
	  	$(".s").removeClass("addpink");
	  	
	  	$(".o").removeClass("addgreen");
	  	$(".o").removeClass("addblue");
	  	$(".o").removeClass("addlightblue");
	  	$(".o").removeClass("addred");
	  	$(".o").removeClass("addpink");
	  	
	  	$(".v").removeClass("addgreen");
	  	$(".v").removeClass("addblue");
	  	$(".v").removeClass("addlightblue");
	  	$(".v").removeClass("addred");
	  	$(".v").removeClass("addpink");
	  	
	  	$(".t").removeClass("addgreen");
	  	$(".t").removeClass("addblue");
	  	$(".t").removeClass("addlightblue");
	  	$(".t").removeClass("addred");
	  	$(".t").removeClass("addpink");
	  	
	  	$(".u").addClass("addyellow");
	    $(".t").addClass("addyellow");
	    $(".s").addClass("addyellow");
	    $(".o").addClass("addyellow");
	    $(".v").addClass("addyellow");
	  });
	});

$(function() {                       
	  $(".map4").click(function() { 
	  	$(".u").removeClass("addyellow");
	  	$(".u").removeClass("addblue");
	  	$(".u").removeClass("addlightblue");
	  	$(".u").removeClass("addred");
	  	$(".u").removeClass("addpink");
	  	
	  	$(".s").removeClass("addyellow");
	  	$(".s").removeClass("addblue");
	  	$(".s").removeClass("addlightblue");
	  	$(".s").removeClass("addred");
	  	$(".s").removeClass("addpink");
	  	
	  	$(".o").removeClass("addyellow");
	  	$(".o").removeClass("addblue");
	  	$(".o").removeClass("addlightblue");
	  	$(".o").removeClass("addred");
	  	$(".o").removeClass("addpink");
	  	
	  	$(".v").removeClass("addyellow");
	  	$(".v").removeClass("addblue");
	  	$(".v").removeClass("addlightblue");
	  	$(".v").removeClass("addred");
	  	$(".v").removeClass("addpink");
	  	
	  	$(".t").removeClass("addyellow");
	  	$(".t").removeClass("addblue");
	  	$(".t").removeClass("addlightblue");
	  	$(".t").removeClass("addred");
	  	$(".t").removeClass("addpink");
	  	
	  	$(".u").addClass("addgreen");
	    $(".t").addClass("addgreen");
	    $(".s").addClass("addgreen");
	    $(".o").addClass("addgreen");
	    $(".v").addClass("addgreen");
	  });
	});

$(function() {                       
	  $(".map5").click(function() { 
	  	$(".u").removeClass("addgreen");
	  	$(".u").removeClass("addyellow");
	  	$(".u").removeClass("addblue");
	  	$(".u").removeClass("addred");
	  	$(".u").removeClass("addpink");
	  	
	  	$(".s").removeClass("addgreen");
	  	$(".s").removeClass("addyellow");
	  	$(".s").removeClass("addblue");
	  	$(".s").removeClass("addred");
	  	$(".s").removeClass("addpink");
	  	
	  	$(".o").removeClass("addgreen");
	  	$(".o").removeClass("addyellow");
	  	$(".o").removeClass("addblue");
	  	$(".o").removeClass("addred");
	  	$(".o").removeClass("addpink");
	  	
	  	$(".v").removeClass("addgreen");
	  	$(".v").removeClass("addyellow");
	  	$(".v").removeClass("addblue");
	  	$(".v").removeClass("addred");
	  	$(".v").removeClass("addpink");
	  	
	  	$(".t").removeClass("addgreen");
	  	$(".t").removeClass("addyellow");
	  	$(".t").removeClass("addblue");
	  	$(".t").removeClass("addred");
	  	$(".t").removeClass("addpink");
	  	
	  	$(".u").addClass("addlightblue");
	    $(".t").addClass("addlightblue");
	    $(".s").addClass("addlightblue");
	    $(".o").addClass("addlightblue");
	    $(".v").addClass("addlightblue");
	  });
	});

$(function() {                       
	  $(".map6").click(function() { 
	  	$(".u").removeClass("addgreen");
	  	$(".u").removeClass("addyellow");
	  	$(".u").removeClass("addlightblue");
	  	$(".u").removeClass("addred");
	  	$(".u").removeClass("addpink");
	  	
	  	$(".s").removeClass("addgreen");
	  	$(".s").removeClass("addyellow");
	  	$(".s").removeClass("addlightblue");
	  	$(".s").removeClass("addred");
	  	$(".s").removeClass("addpink");
	  	
	  	$(".o").removeClass("addgreen");
	  	$(".o").removeClass("addyellow");
	  	$(".o").removeClass("addlightblue");
	  	$(".o").removeClass("addred");
	  	$(".o").removeClass("addpink");
	  	
	  	$(".v").removeClass("addgreen");
	  	$(".v").removeClass("addyellow");
	  	$(".v").removeClass("addlightblue");
	  	$(".v").removeClass("addred");
	  	$(".v").removeClass("addpink");
	  	
	  	$(".t").removeClass("addgreen");
	  	$(".t").removeClass("addyellow");
	  	$(".t").removeClass("addlightblue");
	  	$(".t").removeClass("addred");
	  	$(".t").removeClass("addpink");
	  	
	  	$(".u").addClass("addblue");
	    $(".t").addClass("addblue");
	    $(".s").addClass("addblue");
	    $(".o").addClass("addblue");
	    $(".v").addClass("addblue");
	  });
	});

// .addpink u
//   background-color: #ff6e9e
// .addogreen g
//   background-color: #63cecb
// .addyellow s
//   background-color: #ffbf61
// .addblue o
//   background-color: #366694
// .addlightblue v
//   background-color: #7caedd
// .addred t
//   background-color: #ff5f5f



$(document).ready(function () {

$(".d1").mouseenter(
  function(){
    $(".country1").fadeIn();
  }

);
$(".d1").mouseleave(
  function(){
  	$(".country1").fadeOut(1000);
  }
);
$(".s1").mouseenter(
  function(){
    $(".country4").fadeIn();
  }

);
$(".s1").mouseleave(
  function(){
  	$(".country4").fadeOut(1000);
  }
);

$(".s2").mouseenter(
  function(){
    $(".country5").fadeIn();
  }

);
$(".s2").mouseleave(
  function(){
  	$(".country5").fadeOut(1000);
  }
);


$(".t1").mouseenter(
  function(){
    $(".country6").fadeIn();
  }

);
$(".t1").mouseleave(
  function(){
  	$(".country6").fadeOut(1000);
  }
);

$(".t2").mouseenter(
  function(){
    $(".country7").fadeIn();
  }

);
$(".t2").mouseleave(
  function(){
  	$(".country7").fadeOut(1000);
  }
);

$(".t3").mouseenter(
  function(){
    $(".country8").fadeIn();
  }

);
$(".t3").mouseleave(
  function(){
  	$(".country8").fadeOut(1000);
  }
);

$(".u1").mouseenter(
  function(){
    $(".country9").fadeIn();
  }

);
$(".u1").mouseleave(
  function(){
  	$(".country9").fadeOut(1000);
  }
);

$(".u3").mouseenter(
  function(){
    $(".country11").fadeIn();
  }

);
$(".u3").mouseleave(
  function(){
  	$(".country11").fadeOut(1000);
  }
);

$(".v1").mouseenter(
  function(){
    $(".country13").fadeIn();
  }

);
$(".v1").mouseleave(
  function(){
  	$(".country13").fadeOut(1000);
  }
);


$(".o1").mouseenter(
  function(){
    $(".country14").fadeIn();
  }

);
$(".o1").mouseleave(
  function(){
  	$(".country14").fadeOut(1000);
  }
);
$(".o2").mouseenter(
  function(){
    $(".country15").fadeIn();
  }

);
$(".o2").mouseleave(
  function(){
  	$(".country15").fadeOut(1000);
  }
);
});



$(document).ready(function() {
  

	  
    var numItems = $('li.fancyTab').length;
		
	
			  if (numItems == 12){
					$("li.fancyTab").width('8.3%');
				}
			  if (numItems == 11){
					$("li.fancyTab").width('9%');
				}
			  if (numItems == 10){
					$("li.fancyTab").width('10%');
				}
			  if (numItems == 9){
					$("li.fancyTab").width('11.1%');
				}
			  if (numItems == 8){
					$("li.fancyTab").width('12.5%');
				}
			  if (numItems == 7){
					$("li.fancyTab").width('14.27%');
				}
			  if (numItems == 6){
					$("li.fancyTab").width('16.666666666666667%');
				}
			  if (numItems == 5){
					$("li.fancyTab").width('20%');
				}
			  if (numItems == 4){
					$("li.fancyTab").width('25%');
				}
			  if (numItems == 3){
					$("li.fancyTab").width('33.3%');
				}
			  if (numItems == 2){
					$("li.fancyTab").width('50%');
				}
		  
	 

	
		});

$(window).load(function() {

  $('.fancyTabs').each(function() {

    var highestBox = 0;
    $('.fancyTab a', this).each(function() {

      if ($(this).height() > highestBox)
        highestBox = $(this).height();
    });

    $('.fancyTab a', this).height(highestBox);

  });
});