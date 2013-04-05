<!DOCTYPE html PUBLIC 
    "-//W3C//DTD XHTML 1.1 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="icon" type="image/png" href="favicon.png">
    <link rel="shortcut icon" type="image/png" href="favicon.png">
    <link rel="image_src" href="image.png">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato:300,300italic,400,400italic,700,900,700italic|Ubuntu:300,700&subset=latin,cyrillic-ext">
    <link rel="stylesheet" href="css/main.css">
    <title>
        HTML5 MAKER | Preview and Download | www.html5maker.com
    </title>
</head>
<body>
    <s:actionerror/>

    <div class="wrapper">
        <header>
            <div class="repeatx header">
                <div class="package header_light header_150">
                    <div class="package header_rays header_150">
                        <div class="banner_container">
                            <div class="banner_left banner_left_10">
                                <div class="logo">
                                    <div class="package logo_main" onclick="location.href='http://html5maker.com/';"
                                    style="cursor:pointer;" title="Back to Home">
                                        HTML5 Maker
                                    </div>
                                    <div class="logo_slogan">
                                        The Banner Maker App
                                    </div>
                                </div>
                            </div>
                            <div class="menu">
                            	<ul>
                                	<li><a href="#" title="">Create HTML5 Banner</a></li>
                                	<li><a href="#" title="">FAQs</a></li>
                                	<li><a href="#" title="">Contact</a></li>
                                	<li>
                                        <s:if test="%{#session['theUser'] == null}">
                                            <a href="signin.html" title="Sign In" class="sign">
                                                <span>Sign In</span>
                                            </a>
                                        </s:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <article>
            <div class="package article_cloud">
                <div class="article">
                	<div class="step"></div>
                    <div class="article_title article_title_center">
                        Step 1: Choose Your Preferred Plan
                        <br />
                        <span>
                            Choose one of four amazing subscription plans
                        </span>
                    </div>
                </div>
                <div class="article_banner article_banner_buy">
                    <div class="option_block option_block_buy">
                    	<div class="option_block_top"></div>
                    	<div class="option_block_center">
                        	<h2>BASIC</h2>
                            <div class="subscription">
                            	<span class="subscription_from">was <del>$1.99</del></span>
                                <div class="subscription_price">
                                	<div class="subscription_price_left">
                                    	<span>$</span>
                                        0
                                    </div><!--end subscription_price_left-->
                                	<div class="subscription_price_right">
                                    	<span>00</span>
                                        <p>/month</p>
                                    </div><!--end subscription_price_right-->
                                </div><!--end subscription_price-->
                            </div><!--end subscription-->
                            <h3>FOREVER</h3>
                            <s:url id="membershipUrl1" namespace="/" action="register">
                                <s:param name="membership" value="availableMemberships[ 0].name"/>
                            </s:url>
                            <s:a cssClass="start_now" errorText="Sorry your request had an error." href="%{membershipUrl1}">
                                START NOW!
                            </s:a>
                            <div class="characteristic_option_download">
                            	<span>1 Banner</span>
                                <p>Banners Hosting</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>0,1 Gb/month</span>
                                <p>Bandwidth</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Embed Code</span>
                                <p>Export</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>SSL</span>
                                <p>Security</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>No</span>
                                <p>Watermark</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Anytime</span>
                                <p>Edit Saved Banner</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Banner Download</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Awesome Templates</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Cliparts Collection</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<div class="characteristic_non"></div>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<div class="characteristic_non"></div>
                            </div><!--end characteristic_option_download-->
                        </div><!--end option_block_center-->
                    	<div class="option_block_bottom"></div>
                    </div><!--end option_block-->
                    <div class="option_block option_block_buy">
                    	<div class="option_block_top"></div>
                    	<div class="option_block_center">
                        	<h2>PLUS</h2>
                            <div class="subscription">
                            	<span class="subscription_from">was <del>$5.69</del></span> 
                                <div class="subscription_price">
                                	<div class="subscription_price_left">
                                    	<span>$</span>
                                        4
                                    </div><!--end subscription_price_left-->
                                	<div class="subscription_price_right">
                                    	<span>74</span>
                                        <p>/month</p>
                                    </div><!--end subscription_price_right-->
                                    <div class="subscription_or">
                                    	<span>or $47 paid yearly</span>
                                        <p><span>to</span> SAVE 30%</p>
                                    </div>
                                </div><!--end subscription_price-->
                            </div><!--end subscription-->
                            
                            <a class="buy_now" href="#">
                                BUY NOW
                            <a>
                            <div class="characteristic_option_download">
                            	<span>10 Banner</span>
                                <p>Banners Hosting</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>15 Gb/month</span>
                                <p>Bandwidth</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Embed Code</span>
                                <p>Export</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>SSL</span>
                                <p>Security</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>No</span>
                                <p>Watermark</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Anytime</span>
                                <p>Edit Saved Banner</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Banner Download</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Awesome Templates</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Cliparts Collection</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Fast</span>
                                <p>Support</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>99.9%</span>
                                <p>Durability</p>
                            </div><!--end characteristic_option_download-->
                        </div><!--end option_block_center-->
                    	<div class="option_block_bottom"></div>
                    </div><!--end option_block-->
                    <div class="option_block option_block_buy option_block_recomended">
                    	<div class="option_block_top"></div>
                    	<div class="option_block_center">
                        	<h2>PREMIUM</h2>
                            <div class="subscription">
                            	<span class="subscription_from">was <del>$12.24</del></span> 
                                <div class="subscription_price">
                                	<div class="subscription_price_left">
                                    	<span>$</span>
                                        9
                                    </div><!--end subscription_price_left-->
                                	<div class="subscription_price_right">
                                    	<span>49</span>
                                        <p>/month</p>
                                    </div><!--end subscription_price_right-->
                                    <div class="subscription_or">
                                    	<span>or $94 paid yearly</span>
                                        <p><span>to</span> SAVE 45%</p>
                                    </div>
                                </div><!--end subscription_price-->
                            </div><!--end subscription-->
                            <a href="#" title="" class="buy_now">BUY NOW</a>
                            <div class="characteristic_option_download">
                            	<span>100 Banner</span>
                                <p>Banners Hosting</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>30 Gb/month</span>
                                <p>Bandwidth</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Embed Code</span>
                                <p>Export</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>SSL</span>
                                <p>Security</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>No</span>
                                <p>Watermark</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Anytime</span>
                                <p>Edit Saved Banner</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Banner Download</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Awesome Templates</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Cliparts Collection</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Fast</span>
                                <p>Support</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>99.9%</span>
                                <p>Durability</p>
                            </div><!--end characteristic_option_download-->
                        </div><!--end option_block_center-->
                    	<div class="option_block_bottom"></div>
                    </div><!--end option_block-->
                    <div class="option_block option_block_buy">
                    	<div class="option_block_top"></div>
                    	<div class="option_block_center">
                        	<h2>ULTIMATE</h2>
                            <div class="subscription subscription_large">
                            	<span class="subscription_from">was <del>$36.64</del></span> 
                                <div class="subscription_price">
                                	<div class="subscription_price_left">
                                    	<span>$</span>
                                        18
                                    </div><!--end subscription_price_left-->
                                	<div class="subscription_price_right">
                                    	<span>81</span>
                                        <p>/month</p>
                                    </div><!--end subscription_price_right-->
                                    <div class="subscription_or">
                                    	<span>or $118 paid yearly</span>
                                        <p><span>to</span> SAVE 57%</p>
                                    </div>
                                </div><!--end subscription_price-->
                            </div><!--end subscription-->
                            <a href="#" title="" class="buy_now">BUY NOW</a>
                            <div class="characteristic_option_download">
                            	<span>Unlimited</span>
                                <p>Banners Hosting</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>100 Gb/month</span>
                                <p>Bandwidth</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Embed Code</span>
                                <p>Export</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>SSL</span>
                                <p>Security</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>No</span>
                                <p>Watermark</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Anytime</span>
                                <p>Edit Saved Banner</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Banner Download</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Awesome Templates</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>FREE</span>
                                <p>Cliparts Collection</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>Fast</span>
                                <p>Support</p>
                            </div><!--end characteristic_option_download-->
                            <div class="option_separator"></div>
                            <div class="characteristic_option_download">
                            	<span>99.9%</span>
                                <p>Durability</p>
                            </div><!--end characteristic_option_download-->
                        </div><!--end option_block_center-->
                    	<div class="option_block_bottom"></div>
                    </div><!--end option_block-->
                </div>
            </div>
        </article>
        <footer>
            <div class="repeatx footer_bg">
                <div class="footer">
                    <div class="footer_copyright">
                        &copy; 2012
                        <a href="http://html5maker.com/" title="HTML5 Maker">html5maker.com</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <span>
                            |
                        </span>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:MyOtziv.mo_show_box();" title="Contact">Leave Feedback</a>
                    </div>
                    <div class="footer_facebook">
                        <div class="fb-subscribe" data-href="http://www.facebook.com/html5maker"
                        data-layout="button_count" data-show-faces="false" data-width="130">
                        </div>
                    </div>
                    <div class="footer_twitter">
                        <a href="https://twitter.com/html5maker" class="twitter-follow-button"
                        data-show-count="false" data-show-screen-name="false">Follow @html5maker</a>
                    </div>
                    <div class="footer_gplus">
                        <a href="https://plus.google.com/110212116009154355543" target="_blank"
                        title="Follow HTML5 Maker on Google+" class="package"><span class="package">Follow</span></a>
                    </div>
                    <div class="footer_like">
                        <div class="fb-like" data-href="http://www.facebook.com/html5maker" data-send="false"
                        data-layout="button_count" data-width="110" data-show-faces="false">
                        </div>
                    </div>
                    <div class="footer_tweet">
                        <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://html5maker.com/"
                        data-text="HTML5 Maker » Create HTML5 Banner, Slideshow or Presentation easily! Try it for FREE!"
                        data-via="html5maker" data-related="html5maker" data-hashtags="html5,banner">Tweet</a>
                    </div>
                    <div class="footer_plusone">
                        <div class="g-plusone" data-size="medium">
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>
    <div id="fb-root"></div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" /></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
    <script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
    <script type="text/javascript" src="js/social.js"></script>
    <script type="text/javascript" src="js/feedback.js"></script>
    <script type="text/javascript" language="JavaScript" src="http://idea.informer.com/tab6.js?domain=html5maker"></script>
    <noscript>
        <a href="http://html5maker.idea.informer.com">HTML5 Maker Feedback</a>
        <a href="http://idea.informer.com">
			<img src="http://widget.idea.informer.com/tmpl/images/widget_logo.jpg">
		</a>
    </noscript>
    <script type="text/javascript" src="js/analytics.js"></script>
</body>
</html>