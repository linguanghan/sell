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
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>
                                        类目id
                                    </th>
                                    <th>
                                        类目名称
                                    </th>
                                    <th>
                                        类目编号
                                    </th>
                                    <th>
                                        创建时间
                                    </th>
                                    <th>
                                        修改时间
                                    </th>
                                    <th>
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list categoryList as category>
                                    <tr>
                                        <td>
                                            ${category.categoryId}
                                        </td>
                                        <td>
                                            ${category.categoryName}
                                        </td>

                                        <td>
                                            ${category.categoryType}
                                        </td>

                                        <td>
                                            ${category.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                                        </td>
                                        <td>
                                            ${category.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                                        </td>
                                        <td>
                                            <a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>