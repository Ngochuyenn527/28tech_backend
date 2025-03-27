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
                                                            id="btnSearchBuilding" >
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
<%--                                                                        <a class="btn btn-xs btn-success" title="Giao tòa nhà"--%>
<%--                                                                           onclick="assignmentBuilding(${tableList.id})">--%>
<%--                                                                            <i class="ace-icon glyphicon glyphicon-list"></i>--%>
<%--                                                                        </a>--%>

<%--                                                                        <a class="btn btn-xs btn-info" title="Sửa tòa nhà"--%>
<%--                                                                           href="/admin/building-edit-${tableList.id}">--%>
<%--                                                                            <i class="ace-icon fa fa-pencil bigger-120"></i>--%>
<%--                                                                        </a>--%>

<%--                                                                        <button class="btn btn-xs btn-danger" title="Xóa tòa nhà"--%>
<%--                                                                                onclick="deleteBuilding(${tableList.id})" id="btnDeleteBuilding">--%>
<%--                                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                                                                        </button>--%>
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


<!-- giao tòa nhà -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog"
     style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Giao tòa nhà cho nhân viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            Chọn
                        </th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody class="center">
                    <tr>
                        <td>
                            <input type="checkbox" id="checkbox_1" value="1">
                        </td>
                        <td>Nguyễn Văn A</td>
                    </tr>

                    <tr>
                        <td>
                            <input type="checkbox" id="checkbox_2" value="2">
                        </td>
                        <td>Nguyễn Văn B</td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="1">
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentBuilding" data-dismiss="modal">Giao
                    tòa nhà
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val();
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {}; // có cấu trúc của json
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staff'] = staffs;
        console.log("OK");
    })


    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();

        // Lấy dữ liệu từ form và chuyển thành object
        var formData = $('#listForm').serialize();

        $.ajax({
            type: "GET",
            url: "http://localhost:8081/api/building",
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
        tableBody.empty(); // Xóa dữ liệu cũ

        if (!data || data.length === 0) {
            tableBody.append(`<tr><td colspan="10" class="text-center">Không có dữ liệu</td></tr>`);
            return;
        }

        for (let i = 0; i < data.length; i++) {
            let building = data[i]; // Lấy từng phần tử trong mảng
            if (!building.id) {
                console.error("Lỗi: ID tòa nhà không tồn tại!", building);
                continue; // Bỏ qua nếu ID không hợp lệ
            }

            let row = `
            <tr id="row-${building.id}">
                <td>` + building.name + `  </td>
                <td>` + building.structure + `  </td>
                <td>` + building.address + `  </td>
                <td>` + building.numberOfBasement + `  </td>
                <td>` + building.direction + `   </td>
                <td>` + building.rentArea + `  </td>
                <td>` + building.rentPrice + ` triệu/m²  </td>
                <td>` + building.serviceFee + `  </td>
                <td>` + building.brokerageFee + `  </td>

                <td class="col-actions">
                    <a class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${building.id})">
                        <i class="ace-icon glyphicon glyphicon-list"></i>
                    </a>
                    <a class="btn btn-xs btn-info" title="Sửa tòa nhà" href="/admin/building-edit-${building.id}">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </a>
                    <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${building.id})">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                </td>
            </tr>
        `;
            tableBody.append(row);
        }
    }


    <%--function deleteBuilding(id) {--%>

    <%--console.log("🔹 ID nhận được khi bấm xóa:", id, typeof id); // Kiểm tra giá trị và kiểu dữ liệu của id--%>
    <%--    if (!id || isNaN(id)) {--%>
    <%--        alert("Lỗi: ID tòa nhà không hợp lệ!");--%>
    <%--        return;--%>
    <%--    }--%>


    <%--    if (!confirm("Bạn có chắc chắn muốn xóa tòa nhà này?")) {--%>
    <%--        return;--%>
    <%--    }--%>

    <%--    $.ajax({--%>
    <%--        type: "DELETE",--%>
    <%--        url: `http://localhost:8081/api/building/${id}`, // API Backend--%>
    <%--        contentType: "application/json",--%>
    <%--        success: function (response) {--%>
    <%--            alert("Xóa thành công!");--%>
    <%--            $("#row-" + id).remove(); // Xóa dòng khỏi bảng nếu thành công--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            console.error("Lỗi khi xóa tòa nhà:", error);--%>
    <%--            alert("Không thể xóa tòa nhà. Vui lòng thử lại!");--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>

//     function deleteBuilding(id) {
//     if (!confirm("Bạn có chắc muốn xóa không?")) return;
//
//     console.log("ID cần xóa:", id); // Kiểm tra ID có giá trị không
//
//     $.ajax({
//         url: "/api/building/delete/" + id,
//         type: "DELETE",
//         success: function (response) {
//             alert("Xóa thành công!");
//             location.reload();
//         },
//         error: function (xhr, status, error) {
//             console.log("Lỗi AJAX:", status, error);
//             console.log("Chi tiết lỗi:", xhr.responseText);
//             alert("Có lỗi xảy ra, không thể xóa!");
//         }
//     });
// }




    // Xóa tòa nhà
    // $(".btnDeleteBuilding").click(function () {
    //     let buildingId = $(this).data("id");
    //     if (confirm("Bạn có chắc chắn muốn xóa?")) {
    //         $.ajax({
    //             url: buildingAPI + "/" + buildingId,
    //             type: "DELETE",
    //             success: function () {
    //                 alert("Xóa thành công!");
    //                 location.reload();
    //             },
    //             error: function (error) {
    //                 alert("Lỗi khi xóa: " + error.responseText);
    //             }
    //         });
    //     }
    // });





</script>


</body>
</html>