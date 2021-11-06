<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
<%--    <link type="text/css" rel="stylesheet" href="../css/style.css">--%>
<%--    <link type="text/css" rel="stylesheet" href="../css/public.css">--%>
<%--        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" charset="utf-8"/>--%>
<%--    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" charset="utf-8"/>--%>
</head>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    body{
        font-size: 12px;
        background: #4287c2;
    }
    .clear:after{
        content: '';
        display: block;
        clear: both;
    }
    a{
        text-decoration: none;
    }
    li{
        list-style: none;
    }

    /*公共样式，头部*/
    .publicHeader{
        height: 48px;
        line-height: 48px;
        font-size: 14px;
        background: linear-gradient(to bottom,#60acf0,#64a5df,#62a0dd,#5994d6,#4f8ace,#4880ca);
        background:-webkit-linear-gradient(to bottom,#60acf0,#64a5df,#62a0dd,#5994d6,#4f8ace,#4880ca);
        background:-moz-linear-gradient(to bottom,#60acf0,#64a5df,#62a0dd,#5994d6,#4f8ace,#4880ca);
        background:-ms-linear-gradient(to bottom,#60acf0,#64a5df,#62a0dd,#5994d6,#4f8ace,#4880ca);
    }
    .publicHeader h1{
        float: left;
        color: #fff;
        font-size: 22px;
        padding-left:80px;
        text-shadow: 2px 1px #000;
        background: url("../../images/buy.png") 30px center no-repeat;
    }
    .publicHeaderR{
        float: right;
        color: #fff;
        margin-right: 50px;

    }
    .publicHeaderR p{
        display: inline-block;
        font-weight: bold;
    }
    .publicHeaderR a{
        width: 50px;
        height: 28px;
        display: inline-block;
        border: 1px solid #679e43;
        background: linear-gradient(to bottom,#baf076,#a2d866,#9cd055,#8dc838,#8bc93a);
        line-height: 28px;
        text-align: center;
        border-radius: 4px;
        font-weight: bold;
        color: #fff;

    }
    .publicHeaderR a:hover,.publicHeaderR a:active{
        border: 1px solid #192b02;
        border-radius: 4px;
        font-weight: bold;
        background: linear-gradient(to bottom,#70b21c,#5c9613,#47740e,#3b6209,#2b4906);
    }
    /*时间*/
    .publicTime{
        height: 28px;
        line-height: 28px;
        font-size: 12px;
        background: linear-gradient(to bottom,#f5f9f8,#eef2fb,#e2ecf5,#e2ecf5,#e0edf6);
        padding-left: 20px;
        color: #808589;
    }
    #time{
        float: left;
        background: url("../../images/time.png") 0 center no-repeat;
        padding-left: 26px;
    }
    .publicTime a{
        float: right;
        margin-right: 50px;
        color: #626367;
    }

    /*公共部分主体内容*/
    .publicMian{
        border-top: 1px solid #c2ccd5;
        display: flex;  /*变为弹性盒模型*/
        display: -webkit-flex;
        background: #fff;
    }
    /*左边*/
    .left{
        width: 168px;
        background: url("../../images/leftBg.png") 0 0 repeat-y;
        margin-right: 10px;
        /*height: 520px;*/
        min-height: 520px;

    }
    .leftH2{
        width: 140px;
        height: 30px;
        border-radius: 4px;
        line-height: 30px;
        text-align: center;
        color: #fff;
        background: #60b3e7;
        margin: 10px  auto;
        box-shadow:4px 4px rgba(212,212,212,0.7);
    }
    .leftH2 span{
        width: 10px;
        height: 10px;
        display: inline-block;
        background: radial-gradient(#70c2f4,#3a8dc1, #035384, #4696c7,#83d1f5);
        border-radius: 50%;
    }
    .span1{
        margin-right: 10px;
    }
    .span2{
        margin-left: 12px;
    }

    .list{
        margin: 0 20px;
        font-weight: bold;
    }
    .list a{
        color: red;
    }
    .list li{
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid rgba(212,212,212,0.5) ;
    }
    .list li:nth-child(1){
        background: url("../../images/zd.png") 0  center no-repeat;
    }
    .list li:nth-child(2){
        background: url("../../images/gys.png") 0  center no-repeat;
    }
    .list li:nth-child(3){
        background: url("../../images/yh.png") 0  center no-repeat;
    }
    .list li:nth-child(4){
        background: url("../../images/mm.png") 0  center no-repeat;
    }
    .list li:nth-child(5){
        background: url("../../images/tc.png") 0  center no-repeat;
    }
    .list li:hover{
        background-color: #acd5f5;
        border-radius: 4px;
    }
    .list li:active, #active{
        background-color: #92c609;
        border-radius: 4px;
    }
    .list a{
        color: #0042a8;
        display: inline-block;
        padding-left: 40px;
        width: 90%;
    }


    /*右边*/
    .right{
        width: 85%;
    }
    /*右边所在位置栏*/
    .location{
        height: 30px;
        line-height: 30px;
        border: 1px solid #e6eaf6;
        border-radius: 8px;
        background: linear-gradient(to bottom, #fefefe,#ffffff,#f6fafd);
        color: #4a4a4a;
        padding-left: 30px;
    }
    .location strong{
        background: url("../../images/home.png") 0 center no-repeat;
        display: inline-block;
        padding-left: 30px;
    }
    .location span{
        color: #2179a9;
        font-weight:  bold;
    }
    /*搜索信息栏*/
    .search{
        height:50px;
        line-height:50px;
        background: #f6fafd;
        padding-left: 24px;
        color: #000;
    }
    .search input[type='text']{
        width: 200px;
        height: 30px;
        outline: none;
        padding-left: 10px;
        border: 1px solid #8ab2d5;
        border-radius: 4px;
    }
    .search input[type='button']{
        margin-left: 20px;
        width: 100px;
        padding: 0 20px;
        height: 30px;
        border: 1px solid #7ba92c;
        border-radius: 4px;
        color: #fff;
        font-weight: bold;
        background:#87c212 url("../../images/search.png") 10px center no-repeat;
    }
    .search input[type='button']:focus{
        outline: none;
        background-color: #5d8410;
    }
    .search a{
        width: 80px;
        padding-left:40px;
        float: right;
        margin: 10px 60px;
        height: 30px;
        line-height: 30px;
        border: 1px solid #0c89de;
        border-radius: 4px;
        color: #fff;
        font-weight: bold;
        background:#47acf1 url("../../images/tianjia.png") 10px center no-repeat;
        display: inline-block;
    }
    .search a:hover,.search a:active{
        background-color: #0778c5;
    }
    .search span{
        margin-left: 10px;
    }
    .search select{
        margin: 10px;
        width: 100px;
        height: 30px;
        border-radius: 4px;
        border: 1px solid #0c89de;
        outline: none;
    }

    /*底部*/
    .footer{
        width: 100%;
        line-height: 40px;
        text-align: center;
        color: #fff;
    }

    #searchbutton{
        margin-left: 20px;
        width: 100px;
        padding: 0 20px;
        height: 30px;
        border: 1px solid #7ba92c;
        border-radius: 4px;
        color: #fff;
        font-weight: bold;
        background:#87c212 url("../../images/search.png") 10px center no-repeat;
    }
    #searchbutton:focus{
        outline: none;
        background-color: #5d8410;
    }
</style>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市订单管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b"> ${userSession.userName }</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
         <nav>
             <ul class="list">
                 <li ><a href="${pageContext.request.contextPath }/jsp/bill.do?method=query">订单管理</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/provider.do?method=query">供应商管理</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/user.do?method=query">用户管理</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/logout.do">退出系统</a></li>
             </ul>
         </nav>
     </div>
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>