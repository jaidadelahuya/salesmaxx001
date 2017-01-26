$(document).ready(function() {
	

	$('#slick').slick({
		infinite : true,
		slidesToShow : 4,
		slidesToScroll : 1,
		//prevArrow : "<span class='glyphicon glyphicon-chevron-left'></span>",
		//nextArrow : "<span class='glyphicon glyphicon-chevron-right'></span>",
		autoplay : true,
		autoplaySpeed : 2000,
		responsive: [
		             {
		               breakpoint: 1024,
		               settings: {
		                 slidesToShow: 3,
		                 slidesToScroll: 3,
		                 infinite: true,
		                 dots: true
		               }
		             },
		             {
		               breakpoint: 600,
		               settings: {
		                 slidesToShow: 2,
		                 slidesToScroll: 2
		               }
		             },
		             {
		               breakpoint: 480,
		               settings: {
		                 slidesToShow: 1,
		                 slidesToScroll: 1
		               }
		             }
		             // You can unslick at a given breakpoint now by adding:
		             // settings: "unslick"
		             // instead of a settings object
		           ]
	});
});