<!doctype html>
<html lang="en">
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
  <div id="page-content-wrapper">
    <div class="container-fluid">
      <div class="row clearfix">
        <div class="col-md-12 column">
          <table class="table table-hover table-bordered">
            <thead>
            <tr>
              <th>商品ID</th>
              <th>名称</th>
              <th>图片</th>
              <th>单价</th>
              <th>库存</th>
              <th>描述</th>
              <th>类目</th>
              <th>创建时间</th>
              <th>修改时间</th>
              <th colspan="2">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list productInfoPage.content as productInfo>
              <tr class="success">
                <td>${productInfo.productId}</td>
                <td>${productInfo.productName}</td>
                <td>${productInfo.productIcon}</td>
                <td>${productInfo.productPrice}</td>
                <td>${productInfo.productStock}</td>
                <td>${productInfo.productDescription}</td>
                <td>${productInfo.categoryType}</td>
                <td>${productInfo.createTime}</td>
                <td>${productInfo.updateTime}</td>
                <td>
                  <a href="/sell/seller/product/index?productId=${productInfo.productId}" type="button"
                     class="btn btn-default btn-primary">修改</a>
                </td>
                <td>
                    <#if productInfo.getProductStatusEnum().message == "在架">
                      <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}" type="button"
                         class="btn btn-default btn-danger">下架商品</a>
                    <#else>
                      <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}" type="button"
                         class="btn btn-default btn-danger">上架商品</a>
                    </#if>


                </td>
              </tr>
            </#list>
            </tbody>
          </table>
        </div>
          <#--分页-->
        <div class="col-md-12 column">
          <ul class="pagination pull-right">
              <#if currentPage lte 1>
                <li class="disabled"><a href="#">上一页</a></li>
              <#else>
                <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=8">上一页</a></li>
              </#if>
              <#list 1..productInfoPage.getTotalPages() as index>
                  <#if currentPage==index>
                    <li class="disabled"><a href="#">${index}</a></li>
                  <#else>
                    <li><a href="/sell/seller/order/list?page=${index}&size=8">${index}</a></li>
                  </#if>
              </#list>
              <#if currentPage gte productInfoPage.getTotalPages()>
                <li class="disabled"><a href="#">下一页</a></li>
              <#else>
                <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=8">下一页</a></li>
              </#if>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>