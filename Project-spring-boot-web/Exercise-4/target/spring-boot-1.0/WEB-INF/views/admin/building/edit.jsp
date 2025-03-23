<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <!-- <title>Thêm tòa nhà</title> -->
</head>
<body>
<div class="main-content" id="main-container" style="font-family: 'Times New Roman', Times, serif;">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <%--Nếu đang sửa tòa nhà (id đã có giá trị) (tức là đã tồn tại tòa nhà)--%>
                    <c:if test="${not empty modelBuildingEdit.id}">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="#">Trang chủ</a>
                        </li>
                        <li class="active">Sửa tòa nhà</li>
                    </c:if>

                    <%--Nếu đang thêm tòa nhà (id chưa có giá trị) (tức là đang tạo mới tòa nhà).--%>
                    <c:if test="${empty modelBuildingEdit.id}">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="#">Trang chủ</a>
                        </li>
                        <li class="active">Thêm tòa nhà</li>
                    </c:if>


                </ul><!-- /.breadcrumb -->


            </div>

            <div class="page-content">
                <div class="page-header">
                    <c:if test="${not empty modelBuildingEdit.id}">
                        <h1 style="font-family: 'Times New Roman', Times, serif;">Sửa tòa nhà</h1>
                    </c:if>

                    <c:if test="${empty modelBuildingEdit.id}">
                        <h1 style="font-family: 'Times New Roman', Times, serif;">Thêm tòa nhà</h1>
                    </c:if>

                </div><!-- /.page-header -->

                <%--bảng --%>
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <%-- modelAttribute="modelBuildingEdit": => Các trường nhập liệu trong form sẽ tự động liên kết với các thuộc tính của modelBuildingEdit .--%>
                    <form:form id="listForm" modelAttribute="modelBuildingEdit" method="GET">
                        <div class="col-xs-12 ">
                            <form class="form-horizontal" role="form">

                                <form:hidden path="id" id="buildingId"></form:hidden>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="name"/>
                                            <%--                                    <input type="text" name="name" id="name" class="form-control">--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Cấu trúc</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="structure"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Quận</label>
                                    <div class="col-xs-2">
                                        <form:select class="form-control" path="district">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${districts}"></form:options>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="ward"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="street"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="numberOfBasement"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="direction"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="rentArea"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="rentPrice"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="serviceFee"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-xs-3">Tiền 1 số điện</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="electricityFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền 1 khối nước</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="waterFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="deposit"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="brokerageFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:checkboxes items="${typeCodes}"
                                                         path="typeCode"></form:checkboxes>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty modelBuildingEdit.id}">
                                            <button type="submit" class="btn btn-success" id="btnAddOrUpdateBuilding">
                                                Cập nhật
                                                tòa nhà
                                            </button>
                                            <button type="reset" class="btn btn-danger" id="btnCancel">Hủy thao tác
                                            </button>
                                        </c:if>

                                        <c:if test="${empty modelBuildingEdit.id}">
                                            <button type="submit" class="btn btn-success" id="btnAddOrUpdateBuilding">
                                                Thêm tòa
                                                nhà
                                            </button>
                                            <button type="reset" class="btn btn-danger" id="btnCancel">Hủy thao tác
                                            </button>
                                        </c:if>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<script>
    $('#btnAddOrUpdateBuilding').click(function () {
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != 'typeCode') {
                data["" + v.name + ""] = v.value;
            } else {
                typeCode.push(v.value);
            }
        });
        data['typeCode'] = typeCode;

        if (typeCode.length > 0) {
            addOrUpdateBuilding(data)
        } else {
            window.location.href = "<c:url value = "/admin/building-edit?typeCode=required" />";
        }
    });


    //call api
    function addOrUpdateBuilding(data) {
        $.ajax({
            type: "POST", //http method
            url: "${buildingAPI}",  //url call api
            data: JSON.stringify(data), //chuyen data => dang json
            dataType: "json",
            contentType: "application/json",
            success: function (respond) {
                console.log("Success")
            },
            error: function (respond) {
                console.log(respond)
            }
        });
    }


    $('#btnCancel').click(function () {
        window.location.href = "/admin/building-list";
    });

</script>


</body>
</html>