
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta charset="utf-8"/>
	<title>Html5Maker Editor</title>
	<meta name="description" content="" />
	
	<script src="http://html5maker.com/app/js/swfobject.js"></script>
	<script>
		var 
		flashvars = {
			banner_id: '<s:property value="bannerId" />',
			token: '<s:property value="token" />',
			root_url: 'http://localhost:8080/'
			//root_url: 'http://localhost:8080/draft-struts/'
					};
		var params = {
			menu: "false",
			scale: "noScale",
			allowFullscreen: "false",
			allowScriptAccess: "always",
			bgcolor: "",
			wmode: "window" // can cause issues with FP settings & webcam
		};
		var attributes = {
			id:"html5",
			name:"html5"
		};
		swfobject.embedSWF(
			"http://html5maker.com/app/html5_java.swf", 
			"flashContent", "100%", "100%", "10.3.0", 
			"expressInstall.swf", 
			flashvars, params, attributes);
	</script>
	<script type="text/javascript" src="http://feather.aviary.com/js/feather.js"></script>
				<!-- Instantiate Feather -->
				<script type="text/javascript">
					var currPhoto = '';
					var imgIdName = '';
						var featherEditor = new Aviary.Feather({
							apiKey: '3340d0838',
							apiVersion: 2,
							tools: 'all',
							appendTo: '',
							onSave: function(imageID, newURL) {
								var img = document.getElementById(imageID);
								img.src = newURL;
								swfobject.getObjectById('html5').style.width = '100%';
								swfobject.getObjectById('html5').style.height = '100%';
								swfobject.getObjectById('html5').setPhoto(newURL);
								currPhoto = '';
								featherEditor.close();
								makeImg()
							},
							onLoad: function() {
								document.getElementById('avpw_controls').style.position = 'fixed';
								document.getElementById('avpw_control_cancel_pane').style.position = 'fixed';
							},
							onClose: function() {
								if (currPhoto != '')
								{
									swfobject.getObjectById('html5').style.width = '100%';
									swfobject.getObjectById('html5').style.height = '100%';
									swfobject.getObjectById('html5').setPhoto(currPhoto);
									
									makeImg();
								}
							}
						});
						
						function makeImg()
						{
							var ni = document.getElementById('av');
							var newitem = document.createElement('img');
							imgIdName = 'myimg' + makeid();
								
							newitem.src = 'http://html5maker.com/app/aviary.gif';
							newitem.style.display = "none";
							newitem.id = imgIdName; 
							ni.appendChild(newitem);
						}
						
						function makeid()
						{
							var text = "";
							var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
							
							for( var i=0; i < 5; i++ )
								text += possible.charAt(Math.floor(Math.random() * possible.length));
							
							return text;
						}
							
						function launchEditor(id, src) {
							currPhoto = src;
							
							if (imgIdName == '')
							{
								makeImg();
							}
							
							swfobject.getObjectById('html5').style.width = '1px';
							swfobject.getObjectById('html5').style.height = '1px';
							featherEditor.launch({
								image: imgIdName,
								url: src
							});
							return false;
						}
				</script>
	<style>
		html, body { height:100%; overflow:hidden; }
		body { margin:0; }
	</style>
</head>
<body>
	<div id="flashContent">
		<p style="padding-left:20px; padding-top:20px; color:#333333; font-size:22px;"> To enable custom fonts support in html5 maker please install <a href="http://www.adobe.com/go/getflashplayer" style="color:blue;">Flash Player</a></p>
	</div>
        
	<div id="av"><img id="imageFacebook1" style="display:none;" src="http://html5maker.com/app/aviary.png"/></div>
</body>
</html>