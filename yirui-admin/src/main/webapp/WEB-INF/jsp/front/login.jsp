<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf" %>
<yirui:contentHeader title="用户登录" />

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">用户登录</h3>
                </div>
                <div class="panel-body">
                    <yirui:showMessage></yirui:showMessage>
                    <form role="form" id="loginForm" method="post">
                        <yirui:BackURL hiddenInput="true"/>
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" type="text" id="username" name="username" value="${param.username}"
                                       placeholder="请输入用户名、邮箱或手机号" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入密码" id="password" name="password" type="password">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input id="rememberme" name="rememberMe" type="checkbox" value="true">记住我
                                </label>
                            </div>
                            <input type="submit" value="登录" class="btn btn-success btn-block">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<yirui:contentFooter/>