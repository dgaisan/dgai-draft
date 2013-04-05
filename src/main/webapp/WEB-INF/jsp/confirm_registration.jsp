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
    <link rel="stylesheet" href="<s:url value='/css/main.css'/>">
    <title>
        HTML5 MAKER | Preview and Download | www.html5maker.com
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
                                	<li><a href="#" title="">Create HTML5 Banner</a></li>
                                	<li><a href="#" title="">FAQs</a></li>
                                	<li><a href="#" title="">Contact</a></li>
                                	<li><a href="/signin.html" title="Sign In" class="sign"><span>Sign In</span></a></li>
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
                	<div class="step step_final"></div>
                    <div class="article_title article_title_center">
                        Step 3: Verification
                        <br />
                        <span>
                            Please, verify your email address
                        </span>
                    </div>
                </div>
                <div class="article_banner">
                	<s:form action="re/confirmation" namespace="/">
                    	<div class="plane"></div>
                        <div class="signing_text signing_text_second">
                        	The eMail with verification link has been sent to <a href="#" title=""><s:property value="login" /></a>
                            <p>Note: If you have not recieved an eMail with verification code, check your spam folder or click on the “Send eMail again” button</p>
                        </div><!--end input_block-->
                        <input type="submit" class="button_again" value="SEND EMAIL AGAIN" />
                    </s:form>
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