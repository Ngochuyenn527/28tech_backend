<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
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

                                <form:hidden path="id" id="id"></form:hidden>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input id="name" class="form-control" path="name"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Cấu trúc</label>
                                    <div class="col-xs-9">
                                        <form:input id="structure" class="form-control" path="structure"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Quận</label>
                                    <div class="col-xs-2">
                                        <form:select id="district" class="form-control" path="district">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${districts}"></form:options>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <form:input id="ward" class="form-control" path="ward"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <form:input id="street" class="form-control" path="street"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <form:input id="numberOfBasement" class="form-control" path="numberOfBasement"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                        <form:input id="direction" class="form-control" path="direction"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <form:input id="rentArea" class="form-control" path="rentArea"
                                                    placeholder="Ví dụ: 500, 600"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <form:input id="rentPrice" class="form-control" path="rentPrice"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <form:input id="serviceFee" class="form-control" path="serviceFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền 1 số điện</label>
                                    <div class="col-xs-9">
                                        <form:input id="electricityFee" class="form-control" path="electricityFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền 1 khối nước</label>
                                    <div class="col-xs-9">
                                        <form:input id="waterFee" class="form-control" path="waterFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <form:input id="deposit" class="form-control" path="deposit"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <form:input id="brokerageFee" class="form-control" path="brokerageFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:checkboxes id="typeCode" items="${typeCodes}"
                                                         path="typeCode"></form:checkboxes>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty modelBuildingEdit.id}">
                                            <button type="submit" class="btn btn-success" id="btnEditBuilding">
                                                Cập nhật
                                                tòa nhà
                                            </button>
                                            <button type="reset" class="btn btn-danger" id="btnCancel">Hủy thao tác
                                            </button>
                                        </c:if>

                                        <c:if test="${empty modelBuildingEdit.id}">
                                            <button type="submit" class="btn btn-success" id="btnAddBuilding">
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

    $(document).ready(function () {

        // Hàm chung để xử lý thêm và cập nhật tòa nhà
        function handleBuildingAction(action, url, method) {
            // Lấy dữ liệu từ form
            let buildingData = {
                id: $("#id").val(),
                name: $("#name").val(),
                structure: $("#structure").val(),
                district: $("#district").val(),
                ward: $("#ward").val(),
                street: $("#street").val(),
                numberOfBasement: $("#numberOfBasement").val(),
                direction: $("#direction").val(),
                rentArea: $("#rentArea").val(),
                rentPrice: $("#rentPrice").val(),
                serviceFee: $("#serviceFee").val(),
                waterFee: $("#waterFee").val(),
                electricityFee: $("#electricityFee").val(),
                deposit: $("#deposit").val(),
                brokerageFee: $("#brokerageFee").val(),
                typeCode: []
            };
            // Lấy danh sách typeCode từ các checkbox
            $("input[name='typeCode']:checked").each(function () {
                buildingData.typeCode.push($(this).val());
            });

            // Gửi yêu cầu AJAX
            $.ajax({
                url: url,
                type: method,
                contentType: 'application/json',
                data: JSON.stringify(buildingData),
                success: function (response) {
                    if (action === "add") {
                        console.log("Thêm thành công!", response);
                        alert("Thêm tòa nhà thành công")
                    } else {
                        console.log("Cập nhật thành công!", response);
                        alert("Cập nhật tòa nhà thành công")
                    }
                    location.reload();
                    updateRowInTable(buildingData);
                },
                error: function (err) {
                    if (action === "add") {
                        console.log(" Lỗi khi thêm tòa nhà: ", err);
                        alert("Lỗi khi thêm: " + err.responseText)
                    } else {
                        console.log(" Lỗi khi cập nhật tòa nhà: ", err);
                        alert("Lỗi khi cập nhật: " + err.responseText)
                    }
                }
            });
        }

        // Thêm tòa nhà
        $("#btnAddBuilding").click(function () {
            handleBuildingAction("add", "${buildingAPI}", "POST");
        });

        // Cập nhật tòa nhà
        $("#btnEditBuilding").click(function (e) {
            e.preventDefault();
            handleBuildingAction("update", "${buildingAPI}" + "/" + $("#id").val(), "PUT");
        });
    });

    function updateRowInTable(building) {
        let row = $("#row-" + building.id);
        if (row.length === 0) {
            console.error("Không tìm thấy hàng với ID: " + building.id);
            return;
        }

        row.find("td").eq(0).text(building.name || '');
        row.find("td").eq(1).text(building.structure || '');
        row.find("td").eq(2).text(building.district + ', ' + building.ward + ', ' + building.street || '');
        row.find("td").eq(3).text(building.numberOfBasement || '');
        row.find("td").eq(4).text(building.direction || '');
        row.find("td").eq(5).text(building.rentArea || '');
        row.find("td").eq(6).text((building.rentPrice || '') + ' triệu/m²');
        row.find("td").eq(7).text(building.serviceFee || '');
        row.find("td").eq(8).text(building.brokerageFee || '');
    }


</script>


</body>
</html>