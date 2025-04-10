<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>

<html>
<head>
    <title>
        Danh sách tòa nhà
    </title>
</head>
<body>
<div class="main-content" style="font-family: 'Times New Roman', Times, serif;">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Quản lý tòa nhà</li>
                <li class="active">Danh sách tòa nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12 ">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h5 class="widget-title">Tìm kiếm</h5>

                            <div class="widget-toolbar">

                                <a href="#" data-action="fullscreen" class="orange2">
                                    <i class="ace-icon fa fa-expand"></i>
                                </a>

                                <a href="#" data-action="reload">
                                    <i class="ace-icon fa fa-refresh"></i>
                                </a>

                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>

                                <a href="#" data-action="close">
                                    <i class="ace-icon fa fa-times"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form action="${buildingListURL}" id="listForm" modelAttribute="modelSearch"
                                           method="GET" accept-charset="UTF-8">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <label class="name">Tên tòa nhà</label>
                                                        <%--                                                <input name="name" type="text"--%>
                                                        <%--                                                           class="form-control" value="${modelSearch.name}">--%>
                                                        <%--thay vì sử dụng input thông thường => sd form:input, form:select .... => khi nhấn tìm kiếm, dữ liệu được nhập trong textfield sẽ không bị biến mất --%>
                                                    <form:input class="form-control" path="name"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="name">Cấu trúc</label>
                                                    <form:input class="form-control" path="structure"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label class="name">Quận</label>
                                                    <form:select class="form-control" path="district">
                                                        <form:option value="">---Chọn Quận---</form:option>
                                                        <form:options items="${districts}"></form:options>
                                                    </form:select>

                                                    <!-- ✅ Hiển thị lỗi khi không chọn quận -->
                                                    <form:errors path="district" cssClass="error"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="name">Phường</label>
                                                    <form:input class="form-control" path="ward"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="name">Đường</label>
                                                    <form:input class="form-control" path="street"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label class="name">Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement"/>
                                                </div>
                                                <div class="col-xs-7">
                                                    <label class="name">Hướng </label>
                                                    <form:input class="form-control" path="direction"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label class="name">Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Giá thuê từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="name">Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">

                                                <div class="col-xs-6">
                                                    <label class="name" style="margin-right: 20px">Loại tòa nhà</label>

                                                    <form:checkboxes items="${typeCodes}"
                                                                     path="typeCode"></form:checkboxes>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <button type="submit" class="btn btn-info"
                                                            id="btnSearchBuilding">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                             height="16" fill="currentColor" class="bi bi-search"
                                                             viewBox="0 0 16 16">
                                                            <path
                                                                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                        </svg>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>

                        <div class="pull-right">
                            <a href="/admin/building-edit">
                                <button class="btn btn-info" title="Thêm tòa nhà">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                        <path
                                                d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                        <path
                                                d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path
                                                d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </a>

                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-striped table-bordered table-hover"
                           style="margin-top: 3em;">
                        <tbody>
                        <form:form modelAttribute="buildingList">
                            <display:table name="${buildingList}" cellspacing="0" cellpadding="0"
                                           requestURI="${buildingListURL}" partialList="true" sort="external"
                                           size="${buildingList.totalItems}" defaultsort="2" defaultorder="ascending"
                                           id="tableList"
                                           export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                                <display:column headerClass="text-left" property="structure" title="Cấu trúc"/>
                                <display:column headerClass="text-left" property="address"
                                                title="Địa chỉ"/>
                                <display:column headerClass="text-left" property="numberOfBasement"
                                                title="Số tầng hầm"/>
                                <display:column headerClass="text-left" property="direction" title="Hướng"/>
                                <display:column headerClass="text-left" property="rentArea" title="D.tích thuê"/>
                                <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                                <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                                <display:column headerClass="text-left" property="brokerageFee"
                                                title="Phí môi giới"/>
                                <display:column headerClass="col-actions" title="Thao tác">
                                </display:column>
                            </display:table>
                        </form:form>
                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->


<script>


    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();
        // Lấy dữ liệu từ form và chuyển thành object
        var formData = $('#listForm').serialize();

        $.ajax({
            type: "GET",
            url: "${buildingAPI}",
            data: formData, // Truyền trực tiếp dữ liệu form
            success: function (response) {
                console.log("Danh sách tòa nhà:", response);
                loadResponseSearchDataFromTable(response);
            },
            error: function (error) {
                console.error("Lỗi khi gọi API:", error);
            }
        });
    });


    function loadResponseSearchDataFromTable(data) {

        let tableBody = $("#tableList tbody");
        $("#tableList").find("tbody").empty();

        if (!data || data.length === 0) {
            tableBody.append(`<tr><td colspan="10" class="text-center">Không có dữ liệu</td></tr>`);
            return;
        }

        console.log("Dữ liệu đầu vào:", data); // Kiểm tra dữ liệu

        for (let i = 0; i < data.length; i++) {
            let building = data[i];
            if (!building.id) {
                console.error("Lỗi: ID tòa nhà không tồn tại!", building);
                continue; // Bỏ qua nếu ID không hợp lệ
            }

            let row = '<tr id="row-' + building.id + '">' +
                '<td>' + (building.name || '') + '</td>' +
                '<td>' + (building.structure || '') + '</td>' +
                '<td>' + (building.address || '') + '</td>' +
                '<td>' + (building.numberOfBasement || '') + '</td>' +
                '<td>' + (building.direction || '') + '</td>' +
                '<td>' + (building.rentArea || '') + '</td>' +
                '<td>' + (building.rentPrice || '') + ' triệu/m²</td>' +
                '<td>' + (building.serviceFee || '') + '</td>' +
                '<td>' + (building.brokerageFee || '') + '</td>' +
                '<td class="col-actions">' +
                '<a class="btn btn-xs btn-info edit-building" title="Sửa tòa nhà" href="/admin/building-edit-' + building.id + '">' +
                '<i class="ace-icon fa fa-pencil bigger-120"></i>' +
                '</a>' +
                '<button class="btn btn-xs btn-danger delete-building" title="Xóa tòa nhà" data-id="' + building.id + '">' +
                '<i class="ace-icon fa fa-trash-o bigger-120"></i>' +
                '</button>' +
                '</td>' +
                '</tr>';

            tableBody.append(row);
        }
    }

    $.ajax({
        url: '/api/building',
        method: 'GET',
        success: function (data) {
            // Thêm bảng nếu chưa có
            if ($("#tableList").length === 0) {
                $("body").append('<table id="tableList"><thead>...</thead><tbody></tbody></table>');
            }
            loadResponseSearchDataFromTable(data);
        }
    });

    // Sự kiện click cho nút "Sửa"
    $(document).ready(function () {
        // Gắn sự kiện click cho nút "Sửa"
        $("#tableList").on("click", ".edit-building", function () {
            let buildingId = $(this).data("id");
            if (!buildingId || buildingId === "undefined" || isNaN(buildingId)) {
                console.error("ID tòa nhà không hợp lệ:", buildingId);
                return;
            }

            // Gọi API để lấy thông tin chi tiết của tòa nhà
            $.ajax({
                url: '/api/building/' + buildingId,
                type: 'GET',
                success: function (data) {
                    // Điền dữ liệu vào form
                    $("#id").val(data.id);
                    $("#name").val(data.name);
                    $("#structure").val(data.structure);
                    $("#district").val(data.district);
                    $("#ward").val(data.ward);
                    $("#street").val(data.street);
                    $("#numberOfBasement").val(data.numberOfBasement);
                    $("#direction").val(data.direction);
                    $("#rentArea").val(data.rentArea || '');
                    $("#rentPrice").val(data.rentPrice);
                    $("#serviceFee").val(data.serviceFee);
                    $("#waterFee").val(data.waterFee);
                    $("#electricityFee").val(data.electricityFee);
                    $("#depositFee").val(data.deposit);
                    $("#brokerageFee").val(data.brokerageFee);

                    // Điền typeCode
                    if (data.typeCode && Array.isArray(data.typeCode)) {
                        data.typeCode.forEach(type => {
                            $(`input[name='typeCode'][value='${typeCodes}']`).prop('checked', true);
                        });
                    }

                },
                error: function (err) {
                    console.error("Lỗi khi lấy thông tin tòa nhà: ", err);
                }
            });
        });
    });


    //XÓA
    $(document).ready(function () {
        $("#tableList").on("click", ".delete-building", function () {
            let buildingId = $(this).data("id"); // Cách 1

            if (!buildingId) {
                console.error("Không lấy được buildingId từ data-id", $(this));
                return;
            }

            console.log("ID của tòa nhà cần xóa: " + buildingId);
            deleteBuilding(buildingId);
        });
    });


    function deleteBuilding(id) {
        console.log("Đang xóa tòa nhà có ID: " + id);
        if (confirm("Bạn có chắc chắn muốn xóa tòa nhà này không?")) {
            $.ajax({
                url: "${buildingAPI}" + "/" + id,
                type: 'DELETE',
                success: function (response) {
                    // Xóa dòng tương ứng trong bảng
                    $("#row-" + id).remove();
                    alert("Xóa tòa nhà thành công!");

                    console.log("Xóa thành công!", response);
                },
                error: function (err) {
                    console.error("Lỗi khi xóa: ", err);
                }
            });
        }
    }


</script>


</body>
</html>