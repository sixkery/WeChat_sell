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
          <form role="form" method="post" action="/sell/seller/category/save">
            <div class="form-group">
              <label>名字</label>
              <input type="text" name="categoryName" class="form-control" value="${(category.categoryName)!''}"/>
            </div>
            <div class="form-group">
              <label>类别</label>
              <input type="text" name="categoryType" class="form-control" value="${(category.categoryType)!''}"/>
            </div>
            </div>
            <input type="hidden" name="categoryId" value="${(category.categoryId)!''}">
            <button style="margin-left:1em " type="submit" class="btn btn-default btn-info ">提交</button>
          </form>
        </div>

      </div>
    </div>
  </div>
</div>


</body>
</html>