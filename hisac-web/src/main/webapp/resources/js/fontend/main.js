$(document).ready(function(){
	$('#nav-icon3').click(function(){
		$(this).toggleClass('open');
		$('#stop').toggleClass('stop_scrolling');
		$('.nav_barcontent').fadeToggle('show_bar');
	});
});