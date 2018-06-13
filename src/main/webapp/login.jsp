<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>德客CRM后台管理系统</title>
    <link href="/static/jQueryLogin/css/default.css" rel="stylesheet" type="text/css"/>
    <!--必要样式-->
    <link href="/static/jQueryLogin/css/styles.css" rel="stylesheet" type="text/css"/>
    <link href="/static/jQueryLogin/css/demo.css" rel="stylesheet" type="text/css"/>
    <link href="/static/jQueryLogin/css/loaders.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/zeroModal.css" /><!-- 弹出框Css -->
    <link rel="stylesheet" href="/static/css/jq22.css">
    <script type="text/javascript" src="/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script src="/static/js/zeroModal.min.js"></script><!-- 弹出框 -->
    <script src="//cstaticdun.126.net/load.min.js"></script> <!-- 初始化JS -->
    <script type="text/javascript">
        var test = false;
        $(function () {
            $('#loginBtn').click(function () {

                    $('#loginForm').form('submit', {
                        url: "/login.do",
                        success: function (data) {
                            console.log(data);
                            console.log(data);
                            data = JSON.parse(data);
                            if (!data.success) {
                                window.setTimeout(function () {
                                    _alert2(data.msg);
                                }, 1000);
                                return;
                            }
                            //登录成功
                            $('.login div').fadeOut(100);
                            $('.success').fadeIn(1000);
                            $('.success').html(data.Text);
                            window.setTimeout(function () {
                                window.location.href = "/index.do";
                            }, 1000)

                        }
                    })
            });
        });
        function _alert1(msg) {
            zeroModal.alert(msg);
        }
        function _alert2(msg) {
            zeroModal.alert({
                content: msg,
                contentDetail: '将跳回主页面重新登录',
                okFn: function() {
                    window.location.href = "/login.do";
                }
            });
        }
    </script>--%>
</head>
<body>
<div class='login'>
    <div class='login_title' style="text-align:center;">
        <span style="font-size:30px">德客后台管理系统</span>
        <li><a href="javascript:_alert1()"></a></li>
        <li><a href="javascript:_alert2()"></a></li>
    </div>
    <div class='login_fields'>
        <form id="loginForm" method="post">
            <div class='login_fields__user'>
                <div class='icon'>
                    <img alt="" src='img/user_icon_copy.png'>
                </div>
                <input id="username" name="username" placeholder='用户名' maxlength="16" type='text' autocomplete="off"/>
                <div class='validation'>
                    <img alt="" src='img/tick.png'>
                </div>
            </div>
            <div class='login_fields__password'>
                <div class='icon'>
                    <img alt="" src='img/lock_icon_copy.png'>
                </div>
                <input id="password" name="password" placeholder='密码' maxlength="16" type='password' autocomplete="off">
                <div class='validation'>
                    <img alt="" src='img/tick.png'>
                </div>
            </div>
            <div id="captcha"></div> <!-- 验证码容器元素 -->
            <script src="//cstaticdun.126.net/load.min.js"></script> <!-- 初始化JS -->
          <%--  <script>
                initNECaptcha({
                    captchaId: 'e46ed959706d4c96948815a2829b826a',
                    element: '#captcha',
                    mode: 'float',
                    width: 320,
                    onReady: function (instance) {
                        // 验证码一切准备就绪，此时可正常使用验证码的相关功能
                    },
                    onVerify: function (err, data) {
                        test = true;
                        if (err != null) {
                            test = false;
                            _alert1("滑动验证错误，请重新滑动!");
                        }

                        /**
                         * 第一个参数是err（Error的实例），验证失败才有err对象
                         * 第二个参数是data对象，验证成功后的相关信息，data数据结构为key-value，如下：
                         * {
                         *   validate: 'xxxxx' // 二次验证信息
                         * }
                         */
                        // 点击登录按钮后可调用服务端接口，以下为伪代码，仅作示例用

                        /*$.get('/login.do', {
                            captchaId: 'e46ed959706d4c96948815a2829b826a',
                            username: $('#username').val(),
                            password: $('#password').val(),
                            validate: data.validate
                        }, function (data) {
                            // 登录成功后，相关处理逻辑
                            console.log(data);
                        })*/
                    }
                }, function onload(instance) {
                    // 初始化成功
                }, function onerror(err) {
                    // 验证码初始化失败处理逻辑，例如：提示用户点击按钮重新初始化
                })
            </script>--%>
            <div class='login_fields__submit' style="text-align:center;">
                <input id="loginBtn" type='button' value='登录'>
            </div>
        </form>
    </div>
    <div class='success'>
    </div>
    <div class='disclaimer'>
        <p>欢迎登陆德客CRM后台系统</p>
    </div>
</div>
<div class='authent'>
    <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>认证中...</p>
</div>
<div class="OverWindows"></div>
<link href="/static/jQueryLogin/layui/css/layui.css" rel="stylesheet" type="text/css"/>
<script src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="/static/jQueryLogin/js/jquery-ui.min.js"></script>
<script type="text/javascript" src='/static/jQueryLogin/js/stopExecutionOnTimeout.js?t=1'></script>
<script src="/static/jQueryLogin/layui/layui.js" type="text/javascript"></script>
<script src="/static/jQueryLogin/js/Particleground.js" type="text/javascript"></script>
<script src="/static/jQueryLogin/js/Treatment.js" type="text/javascript"></script>
<script src="/static/jQueryLogin/js/jquery.mockjax.js" type="text/javascript"></script>
<script type="text/javascript" src="/static/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
    var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
    var ajaxmockjax = 1;//是否启用虚拟Ajax的请求响 0 不启用  1 启用

    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            $('input[type="button"]').click();
        }
    });
    //粒子背景特效
    $('body').particleground({
        dotColor: '#E8DFE8',
        lineColor: '#133b88'
    });
</script>
</body>
</html>
