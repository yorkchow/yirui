<%@ page import="com.yirui.common.utils.LogUtils" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <title>${title}</title>
    <link rel="icon" href="${ctx}/static/img/favicon.ico">
    <link rel="shortcut icon" href="${ctx}/static/img/favicon.ico">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/error/error.css">
</head>
<body>
<div id="main">
    <%
        LogUtils.logPageError(request);

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        pageContext.setAttribute("statusCode", statusCode);

        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String queryString = request.getQueryString();
        String url = uri + (queryString == null || queryString.length() == 0 ? "" : "?" + queryString);
        pageContext.setAttribute("url", url);

        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        request.setAttribute("exception", exception);

    %>

    <c:set var="msg" value="<p>出错了!</p>"/>

    <c:if test="${statusCode eq 404}">
        <header id="header">
            <h1><span class="icon">!</span>404<span class="sub">页面未找到</span></h1>
        </header>
        <c:set value="msg" var="<h2><br>您所请求的页面无法找到</h2><p>服务器无法正常提供信息。<br>
        目标页面可能已经被更改、删除或移到其他位置，或您所输入页面地址错误。<br>请联系管理员解决此问题！<br>
        <refresh><a href='${url}' class='btn btn-danger'>刷新,看看是否能访问了</a></refresh></p>"/>
    </c:if>

    <c:if test="${statusCode ne 404}">
        <header id="header">
            <h1><span class="icon">!</span>${statusCode}<span class="sub">服务器程序出问题了</span></h1>
        </header>
        <c:set value="msg" var="<h2><br>服务器程序出问题了</h2><p>目标页面出了一点内部小问题，<br>
        请联系管理员解决此问题！<br>
        刷新重新访问或先去别的页面转转,过会再来吧~！<br>
        <refresh><a href='${url}' class='btn btn-danger'>刷新,看看是否能访问了</a></refresh></p>"/>
    </c:if>

    <div id="content">
        ${msg}

        <c:if test="${not empty exception}">
            <%
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                exception.printStackTrace(printWriter);
                pageContext.setAttribute("stackTrace", stringWriter.toString());
            %>
            <div>
                <a class="btn btn-link btn-detail">
                    点击显示详细错误信息
                </a>
                <div class="prettyprint detail" style="display: none;">
                    <p>${stackTrace}</p>
                </div>
            </div>
        </c:if>

        <div class="utilities">
            <a class="button right" href="/">返回首页</a>
            <a class="button right" href="#" onClick="history.go(-1);return true;">返回...</a>
            <div class="clear"></div>
        </div>
    </div>
    <div id="footer">

    </div>
</div>

<script src="${ctx}/static/js/jquery.js"></script>
<script type="text/javascript">
    $(".btn-detail").click(function() {
        var a = $(this);
        a.find("i").toggleClass("icon-collapse-alt").toggleClass("icon-expand-alt");
        $(".detail").toggle();
    });
</script>

</body>
</html>
