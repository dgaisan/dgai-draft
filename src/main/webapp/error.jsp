<!DOCTYPE HTML>

<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="icon" type="image/png" href="favicon.png">
    <link rel="shortcut icon" type="image/png" href="favicon.png">
    <link rel="image_src" href="image.png">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato:300,300italic,400,400italic,700,900,700italic|Ubuntu:300,700&subset=latin,cyrillic-ext">
    <link rel="stylesheet" href="<s:url value='/css/main.css'/>" />
    <title>
        HTML5 MAKER | Preview and Download | www.html5maker.com (updated)
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
                                    <div class="package logo_main" onclick="location.href='index.html';"
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
                                	<li><a href="<s:url value='/design/create.html'/>" title="">Create HTML5 Banner</a></li>
                                	<li><a href="#" title="">FAQs</a></li>
                                	<li><a href="#" title="">Contact</a></li>
                                	<li>
                                        <s:if test="%{#session['theUser'] == null}">
                                            <a href="signin.html" title="Sign In" class="sign">
                                                <span>Sign In</span>
                                            </a>
                                        </s:if>
                                        <s:elseif test="%{#session['loggedin'] == 1}">
                                            <a href="<s:url value='/homepage.html'/>" title="Dashboard" class="sign">
                                                <span>Dashboard</span>
                                            </a>
                                            <a href="<s:url value='/logout.html'/>" title="Logout" class="sign">
                                                <span>Sign Out</span>
                                            </a>
                                        </s:elseif>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <article>
            <div class="package article_cloud article_cloud_404">
                <div class="article_banner">
                	<div class="plane error_404">
                    	<span>404</span>
                        <p>OOPS! Something went wrong :-( </p>
                    </div>
                    <div class="signing_text signing_text_second signing_text_second_error">
                    	<s:if test="hasActionErrors()">
                            <s:iterator value="actionErrors">
                                <p><s:property escape="false" /></p>
                            </s:iterator>
                        </s:if>
                    </div><!--end input_block-->
                    <div class="signing_text signing_text_second signing_text_second_404">
                    	<p>Go back to the <a href="<s:url value='/index.html'/>" title="">homepage</a> or <a href="#" title="">contact us</a> about the problem.</p>
                    </div><!--end input_block-->
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