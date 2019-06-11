<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <script type="text/javascript">
        function setCookie(cname,cvalue,exdays)
        {
            var d = new Date();
            d.setTime(d.getTime()+(exdays*24*60*60*1000));
            var expires = "expires="+d.toGMTString();
            document.cookie = cname + "=" + cvalue + "; " + expires;
        }
        function getCookie(cname)
        {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++)
            {
                var c = ca[i].trim();
                if (c.indexOf(name)==0) return c.substring(name.length,c.length);
            }
            return "";
        }
        setCookie("jsCookie","111111","1000*10");
        alert(getCookie("jsCookie"))
        alert(getCookie("tokenId"))
//        setCookie("tokenId","66666","1000*10");
    </script>

</head>
<body>

cookieTest<%="this is a test"%>
</body>
</html>