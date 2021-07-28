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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>名称</label><input type="text" name="productName" value="${(productInfo.productName)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label><input type="text" name="productPrice" value="${(productInfo.productPrice)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label><input type="text" name="productStock" value="${(productInfo.productStock)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label><input type="text" name="productDescription" value="${(productInfo.productDescription)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}">
                            <input type="text" name="productIcon" value="${(productInfo.productIcon)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)??&& productInfo.categoryType==category.categoryType>
                                            selected
                                        </#if>>
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}"/>
                        <input hidden type="text" name="productStatus" value="${(productInfo.productStatus)!''}"/>
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