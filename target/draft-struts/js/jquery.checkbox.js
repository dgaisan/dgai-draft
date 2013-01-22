
function changeCheck(el)
{
     var el = el,
          input = el.getElementsByTagName("input")[0];
		
     if(input.checked)
     {
	     el.style.backgroundPosition="-701px -934px"; 
		 input.checked=false;
     }
     else
     {
          el.style.backgroundPosition="-701px -955px"; 
		  input.checked=true;
     }
     return true;
}
function startChangeCheck(el)
{
	var el = el,
          input = el.getElementsByTagName("input")[0];
     if(input.checked)
     {
          el.style.backgroundPosition="-701px -955px";     
      }
     return true;
}

function startCheck()
{
	startChangeCheck(document.getElementById("niceCheckbox1"));
}

window.onload=startCheck;

