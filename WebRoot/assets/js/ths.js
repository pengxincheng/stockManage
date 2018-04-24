/**
 * Created by wangml on 2016/4/27.
 * 思路扩展的jQuery方法/jQueryUI组件
 */
jQuery(function($){
    /**
     * 重写jQuery Dialog的Title方法，使默认有ACE的样式
     */
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function(title) {
            var $title = "<div class='widget-header widget-header-small'>" +
                "<h4 class='smaller'>" +
                "<i class='ace-icon fa " + (this.options.iconCss||"fa-exclamation-circle") + "'></i> " +
                this.options.title +
                "</h4></div>";
            title.html($title);
        }
    }));

    $.extend({
        /**
         *
         * @param dialogDivId 对话框DIV的ID
         * @param dialogTitle 对话框的Title
         * @param dialogButtons 对话框的按钮，数组，可以有事件
         * @param fa_iconCss 对话框Title的ICON，为fontawesome的clsss，默认为！号图标，可不传递此参数
         * @returns {jQuery}
         */
        thsDialogWithDiv:function(dialogDivId,dialogTitle,dialogButtons,fa_iconCss){
            return $( "#" + dialogDivId ).removeClass('hide').dialog({
                minHeight:200,
                minWidth:400,
                modal: true,
                position:{my:"center center",at:"center center-20%" },
                iconCss:fa_iconCss,
                title: dialogTitle,
                buttons: dialogButtons
            });
        },

        /**
         *
         * @param dialogTitle
         * @param dialogText
         * @param dialogButtons
         * @param fa_iconCss
         * @returns {*|jQuery}
         */
        thsDialog:function(dialogTitle,dialogText,dialogButtons,fa_iconCss){
            return $("<div><div class='alert alert-warning'>" + dialogText + "</div></div>").dialog({
                minHeight:200,
                minWidth:400,
                modal: true,
                position:{my:"center center",at:"center center-20%" },
                iconCss:fa_iconCss,
                title: dialogTitle,
                buttons: dialogButtons
            });
        }
    });
});
