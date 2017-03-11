<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>禁用语词筛选</title>
    <meta name="decorator" content="default"/>
    <style>
        #main{
            margin: 5px;
            width: 95%;
        }
        #left{
            width: 500px;
            height: 160px;
            background-color: #fff;
        }

        #beforeFilter{
            width: 95%;
            height: 100%;
        }
        #right{
            width: 500px;
            height: 160px;
            padding: 5px;
            background-color: #f0f0f0;
        }
        .red_border{
            border: solid red 1px;
            border-shadow: 0px 0px 1px  red outset;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#btn').click(function () {

                var inputValue = $('#beforeFilter').val();
                console.log('===========',inputValue);
                if(!inputValue || !inputValue.trim()){ //没有输入内容
                    $('#beforeFilter').addClass('red_border');
                    return false;
                }
                $('#beforeFilter').removeClass('red_border');
                $.ajax({
                    url:'${ctx}/mms/forbiddenWords/filter',
                    type:'get',
                    data:{
                        inputValue:inputValue
                    },
                    success:function (data) {
                        $('#right').html(data);
                    },
                    error:function (err) {
                        alert('出错啦');
                    }
                })
            })
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">禁用语词筛选</a></li>
</ul>

<ul class="ul-form">
</ul>
<div id="main">
    <div id="left">
        <textarea  type="text" id="beforeFilter" rows="6" cols="20"> </textarea>
    </div>
    <input type="button" id="btn" class="btn btn-primary" value="筛选" />
    <div id="right">

    </div>
</div>
</body>
</html>