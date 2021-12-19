$(document).scroll(function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('#top_button').fadeIn(1000);
    } else {
      $('#top_button').fadeOut();
    }
  });


$(function() {                       
  $(".d").click(function() { 
    $(".change_text").text("國家名");
  });
});

$(function() {                       
  $(".s").click(function() { 
    $(".change_text").text("國家名");
  });
});
$(function() {                       
  $(".t").click(function() { 
    $(".change_text").text("國家名");
  });
});
$(function() {                       
  $(".u").click(function() { 
    $(".change_text").text("國家名");
  });
});
$(function() {                       
  $(".g").click(function() { 
    $(".change_text").text("國家名");
  });
});
$(function() {                       
  $(".v").click(function() { 
    $(".change_text").text("國家名");
  });
});

$(function() {                       
  $(".o").click(function() { 
    $(".change_text").text("國家名");
  });
});


$(function() {                       
  $(".worldicon").click(function() { 
    $(".change_text").text("過去10年中的國際優秀人才和研究成果");
  });
});









$(function() {
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
  $(".map1").click(function() { 
  	$(".g").removeClass("addgreen");
  	$(".s").removeClass("addyellow");
  	$(".o").removeClass("addblue");
  	$(".v").removeClass("addlightblue");
  	$(".t").removeClass("addred");  	  	  	
    $(".u").addClass("addpink");
  });
});


$(function() {                       
  $(".map2").click(function() { 
  	$(".g").removeClass("addgreen");
  	$(".s").removeClass("addyellow");
  	$(".o").removeClass("addblue");
  	$(".v").removeClass("addlightblue");
    $(".u").removeClass("addpink");
  	$(".t").addClass("addred");  	  	  	

  });
});
$(function() {                       
  $(".map5").click(function() { 
  	$(".g").removeClass("addgreen");
  	$(".s").removeClass("addyellow");
  	$(".o").removeClass("addblue");
    $(".u").removeClass("addpink");  	
  	$(".t").removeClass("addred");
  	$(".v").addClass("addlightblue");  		  	  	

  });
});
$(function() {                       
  $(".map6").click(function() { 
  	$(".g").removeClass("addgreen");
  	$(".s").removeClass("addyellow");
    $(".u").removeClass("addpink");  	
  	$(".v").removeClass("addlightblue");
  	$(".t").removeClass("addred"); 
  	$(".o").addClass("addblue");  	 	  	  	

  });
});
$(function() {                       
  $(".map3").click(function() { 
  	$(".g").removeClass("addgreen");
    $(".u").removeClass("addpink");  	
  	$(".o").removeClass("addblue");
  	$(".v").removeClass("addlightblue");
  	$(".t").removeClass("addred");
  	$(".s").addClass("addyellow");  		  	  	
  });
});


$(function() {                       
  $(".map4").click(function() { 
    $(".u").removeClass("addpink");  	
  	$(".o").removeClass("addblue");
  	$(".v").removeClass("addlightblue");
  	$(".t").removeClass("addred");
  	$(".s").removeClass("addyellow");
  	$(".g").addClass("addgreen");  		  	  	
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

$(".d2").mouseenter(
  function(){
    $(".country2").fadeIn();
  }

);
$(".d2").mouseleave(
  function(){
  	$(".country2").fadeOut(1000);
  }
);
$(".d3").mouseenter(
  function(){
    $(".country3").fadeIn();
  }

);
$(".d3").mouseleave(
  function(){
  	$(".country3").fadeOut(1000);
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
$(".u2").mouseenter(
  function(){
    $(".country10").fadeIn();
  }

);
$(".u2").mouseleave(
  function(){
  	$(".country10").fadeOut(1000);
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

$(".g1").mouseenter(
  function(){
    $(".country12").fadeIn();
  }

);
$(".g1").mouseleave(
  function(){
  	$(".country12").fadeOut(1000);
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
$(".o3").mouseenter(
  function(){
    $(".country16").fadeIn();
  }

);
$(".o3").mouseleave(
  function(){
  	$(".country16").fadeOut(1000);
  }
);


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