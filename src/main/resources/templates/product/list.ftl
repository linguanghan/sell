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
                                        商品id
                                    </th>
                                    <th>
                                        名称
                                    </th>
                                    <th>
                                        图片
                                    </th>
                                    <th>
                                        单价
                                    </th>
                                    <th>
                                        库存
                                    </th>
                                    <th>
                                        介绍
                                    </th>
                                    <th>
                                        类目
                                    </th>
                                    <th>
                                        创建时间
                                    </th>
                                    <th>
                                        修改时间
                                    </th>
                                    <th colspan="2">
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list products.list as product>
                                    <tr>
                                        <td>
                                            ${product.productId}
                                        </td>
                                        <td>
                                            ${product.productName}
                                        </td>
                                        <td>
                                            <img width="100" height="100" src="${product.productIcon}">
                                        </td>
                                        <td>
                                            ${product.productPrice}
                                        </td>
                                        <td>
                                            ${product.productStock}
                                        </td>
                                        <td>
                                            ${product.productDescription}
                                        </td>
                                        <td>
                                            ${product.getCategoryType()}
                                        </td>
                                        <td>
                                            ${product.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                                        </td>
                                        <td>
                                            <a href="/sell/seller/product/index?productId=${product.productId}">修改</a>
                                        </td>

                                        <#if product.getProductStatusEnum().message=="在架">
                                            <td><a href="/sell/seller/product/off_sale?productId=${product.productId}">下架</a></td>
                                        <#else >
                                            <td><a href="/sell/seller/product/up_sale?productId=${product.productId}">上架</a></td>
                                        </#if>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if products.pageNum lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${products.pageNum-1}&size=${products.pageSize}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..products.pages as index>

                            <#if products.pageNum == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/product/list?page=${index}&size=${products.pageSize}">${index}</a>
                                </li>
                            </#if>


                        </#list>
                        <#if products.pageNum gte products.pages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${products.pageNum+1}&size=${products.pageSize}">下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>