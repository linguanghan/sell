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
                                        订单id
                                    </th>
                                    <th>
                                        姓名
                                    </th>
                                    <th>
                                        手机号
                                    </th>
                                    <th>
                                        地址
                                    </th>
                                    <th>
                                        金额
                                    </th>
                                    <th>
                                        订单状态
                                    </th>
                                    <th>
                                        支付状态
                                    </th>
                                    <th>
                                        创建时间
                                    </th>
                                    <th colspan="2">
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list orderDTOPage.list as orderDTO>
                                    <tr>
                                        <td>
                                            ${orderDTO.orderId}
                                        </td>
                                        <td>
                                            ${orderDTO.buyerName}
                                        </td>
                                        <td>
                                            ${orderDTO.buyerPhone}
                                        </td>
                                        <td>
                                            ${orderDTO.buyerAddress}
                                        </td>
                                        <td>
                                            ${orderDTO.buyerAmount}
                                        </td>
                                        <td>
                                            ${orderDTO.getOrderStatusEnum().getMsg()}
                                        </td>
                                        <td>
                                            ${orderDTO.getPayStatusEnum().getMsg()}
                                        </td>
                                        <td>
                                            ${orderDTO.createTime?string('yyyy-MM-dd HH:mm:ss')}
                                        </td>
                                        <td>
                                            <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                        </td>
                                        <#if orderDTO.getOrderStatusEnum().getMsg()=="新订单">
                                        <td>
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                        </td>
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
                        <#if orderDTOPage.pageNum lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${orderDTOPage.pageNum-1}&size=${orderDTOPage.pageSize}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..orderDTOPage.pages as index>

                            <#if orderDTOPage.pageNum == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${orderDTOPage.pageSize}">${index}</a>
                                </li>
                            </#if>


                        </#list>
                        <#if orderDTOPage.pageNum gte orderDTOPage.pages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${orderDTOPage.pageNum+1}&size=${orderDTOPage.pageSize}">下一页</a>
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