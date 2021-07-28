<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>
<body>
<div id="wrapper" class="toggled">
    <!--边栏-->
    <#include "../common/nav.ftl">
    <!--主体内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>类目名称</label><input type="text" name="categoryName" value="${(category.categoryName)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>类目编号</label><input type="text" name="categoryType" value="${(category.categoryType)!''}" class="form-control"/>
                        </div>
                        <input hidden name="categoryId" value="${(category.categoryId)!' '}">
                        <div class="checkbox">
                        </div> <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>