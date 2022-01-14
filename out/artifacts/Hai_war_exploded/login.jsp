<%--
  Created by IntelliJ IDEA.
  User: huzhida
  Date: 2022/1/12
  Time: 下午8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    html{
        width: 100%;
        height: 100%;
        overflow: hidden;

    }
    body{
        width: 100%;
        height: 100%;
        font-family: 'Open Sans',sans-serif;
        margin: 0;
        background-color: #4A374A;
    }
    #login{
        position: absolute;
        top: 50%;
        left:50%;
        margin: -150px 0 0 -150px;
        width: 300px;
        height: 300px;
    }
    #login h1{
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
    }

    #register h1{
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
    }

    h1{
        font-size: 2em;
        margin: 0.67em 0;
    }
    input{
        width: 278px;
        height: 18px;
        margin-bottom: 10px;
        outline: none;
        padding: 10px;
        font-size: 13px;
        color: #fff;
        text-shadow:1px 1px 1px;
        border-top: 1px solid #312E3D;
        border-left: 1px solid #312E3D;
        border-right: 1px solid #312E3D;
        border-bottom: 1px solid #56536A;
        border-radius: 4px;
        background-color: #2D2D3F;
    }
    .but{
        width: 278px;
        min-height: 20px;
        display: block;
        background-color: #4a77d4;
        border: 1px solid #3762bc;
        color: #fff;
        padding: 9px 14px;
        font-size: 15px;
        line-height: normal;
        border-radius: 5px;
        margin: 0;
    }

</style>

<script>
    <%
        String flag;
        try{
            flag = request.getParameter("flag");
        }
        catch (NullPointerException e)
        {
            flag = "";
        }
        String warning="";

        if(flag!=null){
          warning = "username or password wrong!";
        }
      %>
    const flag = <%=flag%>;

    if(flag!=null){
        alert("<%=warning%>");
    }

</script>
<body>
<div id="login">
    <h1>Login client</h1>
    <form name="login" method="POST" action="./Login">
        <label>
            <input type="text" required="required" placeholder="用户名" name="username" value="">
        </label>
        <label>
            <input type="password" required="required" placeholder="密码" name="password" value="">
        </label>
        <button class="but" type="submit">登录</button>
    </form>

</div>

</body>
</html>
