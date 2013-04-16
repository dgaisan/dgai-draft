<!DOCTYPE html PUBLIC 
    "-//W3C//DTD XHTML 1.1 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index, follow">
    <meta name="googlebot" content="index, follow">
    <meta name="google-site-verification" content="google220375e075299fc7">
    <meta name="description" content="HTML5 MAKER is an Awesome Tool to create HTML5 Banner, Slideshow or Presentation easily! It will work everywhere: Mac, PC, iPad, iPhone, Android and other platforms. Try it for FREE!">
    <meta name="keywords" content="effects, free, html5, interactive, ios, ipad, iphone, linux, mac, make, no plug-ins, no plugins, online, os x, pc, phone, platform, platforms, presentation, presentations, slide, slide show, slide shows, slides, slideshow, slideshows, smartphone, symbian, tablet, tablets, tool, transition, transitions, windows, windows phone">
    <meta name="author" content="HTML5MAKER.com">
    <meta name="copyright" content="HTML5MAKER.com">
    <meta name="og:title" content="HTML5 MAKER | Templates">
    <meta name="og:description" content="HTML5 MAKER is an Awesome Tool to create HTML5 Banner, Slideshow or Presentation easily! It will work everywhere: Mac, PC, iPad, iPhone, Android and other platforms. Try it for FREE!">
    <meta name="og:site_name" content="HTML5 MAKER">
    <meta name="og:type" content="online tool">
    <meta name="og:url" content="http://www.html5maker.com/templates">
    <meta name="og:image" content="">
    <meta name="DC.title" content="HTML5 MAKER | Templates">
    <meta name="DC.subject" content="HTML5 MAKER is an Awesome Tool to create HTML5 Banner, Slideshow or Presentation easily! It will work everywhere: Mac, PC, iPad, iPhone, Android and other platforms. Try it for FREE!">
    <meta name="DC.creator" content="HTML5MAKER.com">
    <link rel="icon" type="image/png" href="favicon.png">
    <link rel="shortcut icon" type="image/png" href="favicon.png">
    <link rel="image_src" href="image.png">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,700,900,700italic|Ubuntu:300,700&subset=latin,cyrillic-ext">
    <link rel="stylesheet" href="<s:url value='/css/main.css'/>">
    
    <title>
        HTML5 MAKER | Templates | Try it for FREE! | www.html5maker.com
    </title>
</head>
<body>
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
                                	<li><a href="#" title="">FAQs</a></li>
                                	<li><a href="#" title="">Contact</a></li>
                                	<li>
                                        <a href="<s:url value='/logout.html'/>" title="Logout" class="sign">
                                            <span>Sign Out</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <article>
            <s:actionerror />

            <div class="package article_cloud">
                <div class="article">
                    <div class="article_btn">
                        <p>
                            <a href="<s:url value='/design/create.html'/>" class="package article_btn_start"
                            title="Create HTML5 Banner or Professional Slideshow and Presentation">Create HTML5 Banner or Professional Slideshow and Presentation</a>
                        </p>
                    </div>
                    <div class="article_title">
                        Welcome Back!
                        <br />
                        <span>
                            Manage your own Awesome Banners
                        </span>
                    </div>
                    <div class="article_banner">
                    
                        <div class="tabs">
                            <ul class="tabs_list">
                                <li><a href="#tabs-1" title="Dashboard"><span>Dashboard</span></a></li>
                                <li><a href="#tabs-2" title="Subscription"><span>Subscription</span></a></li>
                                <li class="no_margin"><a href="#tabs-3" title="Profile Settings"><span>Profile Settings</span></a></li>
                            </ul>
                            <span class="separator"></span>
                            <div id="tabs-1" class="tabs_block tabs_block_full tabs_block_widht">
                            	<div class="dashboard">
                                    <div class="option_title">
                                        <s:property value="totalBanners" default="Unknown" /> Total Banner(s)
                                        <span>View your Banner</span>
                                    </div><!--end option_title-->
                                    <div class="clearfix"></div>

                                    <s:iterator value="banners">
                                        <div class="banner">
                                        	<div class="banner_img">
                                            	<!--insert image here 278x158-->
                                            </div><!--end banner-->
                                            <div class="banner_name"><s:property value="name" default="No Name" /></div>
                                            <p>
                                                <strong>
                                                    <s:property value="bannerWidth" />x<s:property value="bannerHeight" />
                                                </strong> 
                                                size and created on 
                                                <strong><s:date name="dateCreated" format="dd/MM/yyyy" /></strong>
                                            </p>
                                            <!--
                                            <s:url var="ajaxTest" value="/dashboard/download.html">
                                                <s:param name="bannerId">
                                                    <s:property value="id" />
                                                </s:param>
                                            </s:url>
                                            -->
                                            <div class="banner_control">
                                            	<a href="#popups"  class="various" title="Embed">Embed</a>
                                            	<a href="<s:url value='/dashboard/download.html'/>?bannerId=<s:property value='id' />" title="Download">Download</a>
                                            	<a href="#" title="Dublicate">Dublicate</a>
                                            	<a href="#" title="Rename">Rename</a>
                                            	<a href="#" title="Edit">Edit</a>
                                            	<a href="#" title="Delete">Delete</a>
                                            </div><!--end banner_control-->
                                        </div><!--end banner-->
                                    </s:iterator>
                                    <div class="all"><a href="#" title="Change subscription plan to create more banners">Change subscription plan to create more banners</a></div>
                                </div><!--end dashboard-->
                                <div class="trafik">
                                	<div class="border_trafik"></div>
                                    <div class="option_title">
                                        Traffic Usage
                                        <span>Statistic is updating every hour</span>
                                    </div><!--end option_title-->
                                    <div class="trafik_box">
                                    	<div class="mb"><s:property value="totalTraffic" default="Unknown" /></div>
                                        <div class="procent great">83%</div>
                                        <div class="grafik">
                                        	<div class="grafik_grad" style="width:83%;"></div>
                                        </div><!--end grafik-->
                                        <p>1000 Mb total available for the current plan</p>
                                    </div><!--end trafik_box-->
                                    <div class="option_separator"></div>
                                    <div class="option_title">
                                        Current Plan
                                        <span>Upgrade your subscription plan</span>
                                    </div><!--end option_title-->
                                    <div class="current_banner">BASIC</div>
                                    <a class="buy_now upgrade" title="" href="#">upgrade</a>
                                </div><!--end trafik-->
                            </div>
                            <div id="tabs-2" class="tabs_block tabs_block_full tabs_block_widht">
                                <div class="option_title">
                                    Upgrade or Downgrade Subscription Plan
                                    <span>Choose one of two amazing subscription plans to upgrade or downgrade</span>
                                </div><!--end option_title-->
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
                                        <span  class="buy_now current">current</span>
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
                                        <a href="#" title="" class="buy_now upgrade">upgrade</a>
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
                                        <a href="#" title="" class="buy_now upgrade">upgrade</a>
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
                                        <a href="#" title="" class="buy_now upgrade">upgrade</a>
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
                                <div class="signing_text signing_text_second signing_text_second_table">
                                    <p>If you don't like our awesome service you can <a href="#" title="cancel your subscription">cancel your subscription</a> anytime.</p>
                                </div><!--end input_block-->
                            </div>
                            <div id="tabs-3" class="tabs_block tabs_block_full tabs_block_full_last">
                                <div class="option_title">
                                    Change Profile Settings
                                    <span>Change your current eMail to new one or change your password</span>
                                </div>
                                    <div class="clearfix"></div>
                                <form action="">
                                    <div class="input_block">
                                        <input name="" type="text" class="input valid not_valid current" placeholder="frank.freeman@email.com">
                                        <div class="error">
                                            <span class="error_left"></span>
                                            <span class="error_top"></span>	
                                            <div class="error_center">
                                                <label>Please choose a stronger password!</label>
                                            </div><!--end error_center-->
                                            <span class="error_bottom"></span>	
                                        </div><!--end error-->
                                    </div><!--end input_block-->
                                    <div class="clearfix"></div>
                                    <div class="input_block">
                                        <input name="" type="text" class="input input_password" placeholder="Current Password">
                                    </div><!--end input_block-->
                                    <div class="clearfix"></div>
                                    <div class="input_block">
                                        <input name="" type="text" class="input input_password input_password_re_enter input_new_password" placeholder="New Password">
                                    </div><!--end input_block-->
                                    <div class="clearfix"></div>
                                    <div class="input_block">
                                        <input name="" type="text" class="input input_password input_password_re_enter" placeholder="Re-type New Password">
                                    </div><!--end input_block-->
                                    <div class="clearfix"></div>
                                    <input name="" type="button" class="button update_settings" value="update">
                                </form>
                            </div>
                        </div><!--end tabs-->
                    </div>
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
    <div id="popups" style="width:640px;">
        <div class="popups_center">
        	<div class="option_title">
            	Embed Code
            	<span>Choose preferred embed code of the three proposed</span>
            </div><!--end option_title-->
            <div class="embed_links">
            	<a href="#" title="HTML Code for Websites" id="html_textarea_embed1" class="active"></a>
            	<a href="#" title="BB Code for Forums" id="bb_textarea_embed2"></a>
            	<a href="#" title="WordPress Code" id="wp_textarea_embed3"></a>
            </div><!--end embed_links-->
            <form action="" method="get">
                <div class="textarea_embed" id="textarea_embed1">
                	<textarea name="" cols="" rows=""><code></code></textarea>
                </div><!--end textarea_embed-->
                <div class="textarea_embed" id="textarea_embed2">
                	<textarea name="" cols="" rows=""><bcode></bcode></textarea>
                </div><!--end textarea_embed-->
                <div class="textarea_embed" id="textarea_embed3">
                	<textarea name="" cols="" rows="" ><wpcode></wpcode></textarea>
                </div><!--end textarea_embed-->
            </form>
            <a href="#" class="copy" title="COPY EMBED CODE">COPY EMBED CODE</a>
        </div><!--end popups_center-->
    </div><!--end popups-->
    <div id="lean_overlay"></div>
    <div id="fb-root"></div>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" /></script>
    <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
    <script src="js/fancybox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
    <link rel="stylesheet" href="js/fancybox/jquery.fancybox.css?v=2.1.3" type="text/css" media="screen" />
	<script src="js/fancybox/jquery.fancybox.pack.js?v=2.1.3"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
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
    <script>
    $(".various").fancybox({
		maxWidth	: 800,
		maxHeight	: 1000,
		fitToView	: false,
		width		: 'auto',
		height		: 'auto',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'elastic',
		closeEffect	: 'elastic'
	});
    </script>
</body>
</html>