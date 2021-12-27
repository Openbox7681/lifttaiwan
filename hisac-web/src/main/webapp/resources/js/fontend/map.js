$(document).scroll(function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('#top_button').fadeIn(1000);
    } else {
      $('#top_button').fadeOut();
    }
  });

$(document).ready(function() {
	
  $("#inbound").text("31710");
  $("#outbound").text("402");
  $("#mechanism").text("31629");
  $("#result").text("5000");

  $(".d1").click(function() {
	  $(".change_text").text("巴拿馬");
	  $("#inbound").text("0");
	  $("#outbound").text("0");
	  $("#mechanism").text("0");
	  $("#result").text("0");
  });

  $(".s1").click(function() { 
	  $(".change_text").text("台灣");
	  $("#inbound").text("24456");
	  $("#outbound").text("0");
	  $("#mechanism").text("24183");
	  $("#result").text("5000");
  });
  
  $(".s2").click(function() { 
    $(".change_text").text("德國");
    $("#inbound").text("110");
	  $("#outbound").text("23");
	  $("#mechanism").text("132");
	  $("#result").text("29");
  });
  
  $(".t1").click(function() { 
    $(".change_text").text("馬來西亞");
    $("#inbound").text("202");
	  $("#outbound").text("0");
	  $("#mechanism").text("202");
	  $("#result").text("11");
  });
  
  $(".t2").click(function() { 
    $(".change_text").text("中國");
    $("#inbound").text("0");
	  $("#outbound").text("0");
	  $("#mechanism").text("0");
	  $("#result").text("232");
  });
  
  $(".t3").click(function() { 
    $(".change_text").text("法國");
    $("#inbound").text("153");
	  $("#outbound").text("6");
	  $("#mechanism").text("151");
	  $("#result").text("18");
  });
                     
  $(".u1").click(function() { 
    $(".change_text").text("印度");
    $("#inbound").text("3016");
	  $("#outbound").text("0");
	  $("#mechanism").text("2913");
	  $("#result").text("15");
  });
                     
  $(".u3").click(function() { 
    $(".change_text").text("俄羅斯");
    $("#inbound").text("321");
	  $("#outbound").text("0");
	  $("#mechanism").text("317");
	  $("#result").text("10");
  });
  
  $(".v1").click(function() { 
    $(".change_text").text("越南");
    $("#inbound").text("235");
	  $("#outbound").text("0");
	  $("#mechanism").text("233");
	  $("#result").text("0");
  });

  $(".o1").click(function() { 
    $(".change_text").text("美國");
    $("#inbound").text("618");
	  $("#outbound").text("286");
	  $("#mechanism").text("877");
	  $("#result").text("383");
  });
                    
  $(".o2").click(function() { 
    $(".change_text").text("日本");
    $("#inbound").text("373");
	  $("#outbound").text("23");
	  $("#mechanism").text("388");
	  $("#result").text("81");
  });
  
  $(".worldicon").click(function() { 
    $(".change_text").text("過去10年中的國際優秀人才和研究成果");
    $("#inbound").text("31710");
    $("#outbound").text("402");
    $("#mechanism").text("31629");
    $("#result").text("5000");
  });
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