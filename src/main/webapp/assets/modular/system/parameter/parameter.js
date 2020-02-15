layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--消息管理
     */
    var Parameter = {
        tableId: "noticeTable"    //表格id
    };

    /**
     * 初始化表格的列
     */
    Parameter.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'parameterId', hide: true, sort: true, title: 'id'},
            {field: 'code', sort: true, title: '参数编码'},
            {field: 'value', sort: true, title: "参数内容"},
            {field: 'createrName', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Parameter.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Parameter.tableId, {where: queryData});
    };

    /**
     * 弹出添加通知
     */
    Parameter.openAddNotice = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加系统参数',
            content: Feng.ctxPath + '/parameter/parameter_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Parameter.tableId);
            }
        });
    };

    /**
     * 点击编辑通知
     *
     * @param data 点击按钮时候的行数据
     */
    Parameter.onEditNotice = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '通知详情',
            content: Feng.ctxPath + '/parameter/parameter_update/' + data.parameterId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Parameter.tableId);
            }
        });
    };

    /**
     * 点击删除通知
     *
     * @param data 点击按钮时候的行数据
     */
    Parameter.onDeleteNotice = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/parameter/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Parameter.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("parameterId", data.parameterId);
            ajax.start();
        };
        Feng.confirm("是否删除通知 " + data.code + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Parameter.tableId,
        url: Feng.ctxPath + '/parameter/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Parameter.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Parameter.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Parameter.openAddNotice();
    });

    // 工具条点击事件
    table.on('tool(' + Parameter.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Parameter.onEditNotice(data);
        } else if (layEvent === 'delete') {
            Parameter.onDeleteNotice(data);
        }
    });
});
