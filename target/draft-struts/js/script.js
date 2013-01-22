

$(document).ready(function(e) {
	$('.article_banner_box').css("width",(parseInt($('.download_img_center img').width()) + 20 )+'px');
	$('.input').keyup(function(e){
		if($(this).val() == ""){
		$(this).removeClass('current');
		    return false;
		  }

		$(this).addClass('current');
		});
		$('.textarea').keyup(function(e){
		if($(this).val() == ""){
		$(this).removeClass('current');
		    return false;
		  }
		$(this).addClass('current');
		});
		if ($.browser.mozilla) {
            $('.tabs_list').css( "margin-bottom","-5px" );
        }
		jQuery('input[placeholder], textarea[placeholder]').placeholder();
		$( ".tabs" ).tabs();
		
		$('.embed_links a').click(function(e){
			$('.embed_links a').removeClass('active');
			$(this).addClass('active');
		});
		$('#html_textarea_embed1').click(function(e){
			$('#textarea_embed2').hide();
			$('#textarea_embed3').hide();
			$('#textarea_embed1').show();
		});
		$('#bb_textarea_embed2').click(function(e){
			$('#textarea_embed1').hide();
			$('#textarea_embed3').hide();
			$('#textarea_embed2').show();
		});
		$('#wp_textarea_embed3').click(function(e){
			$('#textarea_embed1').hide();
			$('#textarea_embed2').hide();
			$('#textarea_embed3').show();
		});
		
	$('.default').dropkick();
});
		
		
