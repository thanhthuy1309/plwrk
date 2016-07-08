var id = 0;
var currentPage = 0;
$(document).ready(function() {
	$("#idTest").click(function() {
		alert("test js");
	});
	
	$(".btn-success").click(function() {
		var url = "/employee/deleteApply/" + id + "/" + currentPage;
		$.get(url,function(data, status) {
		
		}
	});
});
function confirmDelete(idValue, currentPageValue) {
	id = idValue;
	currentPage = currentPageValue;
	var settings = {
			callbackSuccess : function() {
				
			},
			callbackCancel : function() {
				
			},
			closeButton : false
	};
	createBootboxDialog('Confirm Delete', 'Are you want delete this?', settings);
}

function createBootboxDialog(title, msg, settings) {

    /** sample setting
    var settings = {
            lblSuccess : "Aceptar",
            lblCancel : "Cancelar",
            classBtnSuccess : "btn-success",
            classBtnCancel : "btn-cancel",
            callbackSuccess : function() {
                alert("Sin callback ok");
            },
            callbackCancel : function() {
                alert("Sin callback cancel");
            },
            closeButton : false,
            hideClass : "btn-success"
        };
    */

    var dialog = null;
    try {
        dialog = bootbox.dialog({
                title : title,
                message : msg,
                show : true,
                buttons : {
                    success : {
                        label :  "OK",
                        className : "btn-success",
                        callback : function() {
                                }
                    },
                    cancel : {
                        label : "キャンセル",
                        className : "btn-primary",
                        callback : function() {
                                }

                    }
                }
            });

        if (checkNotEmpty(settings.hideClass)) {
            $("." + settings.hideClass).hide();
        }
    } catch (e) {
    }

    return dialog;
};

