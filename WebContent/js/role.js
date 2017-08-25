/* Role Page */
function submitForm(form) {
	var content ='';
	if (document.getElementById("errorroleName") != null) {
		content = document.getElementById("errorroleName").innerHTML;
	}
	if (content == '' || content.length == 0) {
		document.getElementById("flowName").value = "Save";
		form.submit();
	}
}

function nameChange(form, id, value) {
	var string1 = value.toLocaleLowerCase();
	var oldValue = document.getElementById("oldName").value;
	var string2 = oldValue.toLocaleLowerCase();
	if (string1 != string2) {
		checkifNameExists(form, id, value)
	} else {
		elementID = "error" + id;
		document.getElementById(elementID).innerHTML = "";
	}
}

var xmlRegisterHttp = null;
var elementID = '';

function checkifNameExists(form, id, value) {
	elementID = "error" + id;
	if (document.getElementById(id).value == "") {
		document.getElementById(elementID).innerHTML = "";
		return;
	}
	document.getElementById(elementID).innerHTML = "";
	if (typeof XMLHttpRequest != "undefined") {
		xmlRegisterHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlRegisterHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xmlRegisterHttp == null) {
		alert("Browser does not support XMLHTTP Request")
		return;
	}
	var url;
	if (id == 'roleName') {
		url = "roleNamechange.htm";
	} else {
		url = "userNamechange.htm";
	}
	$.ajax({
		url : url,
		data : "name=" + value,
		success : function(response) {
			document.getElementById(elementID).innerHTML = response;
			$(elementID).html(response);
		}
	});
}

function clearForm(oForm) {
	var frm_elements = oForm.elements;
	for (var i = 0; i < frm_elements.length; i++) {
		field_type = frm_elements[i].type.toLowerCase();
		switch (field_type) {
		case "text":
		case "textarea":
			frm_elements[i].value = "";
			break;
		case "checkbox":
			frm_elements[i].checked = false;
			break;
		default:
			break;
		}
	}
	if (document.getElementById("errorroleName") != null) {
		document.getElementById("errorroleName").innerHTML = '';
	}
	if (document.getElementById("erroruserName") != null) {
		document.getElementById("erroruserName").innerHTML = '';
	}
	if (document.getElementById("errorRoleAdd") != null) {
		document.getElementById("errorRoleAdd").innerHTML = '';
	}
	if (document.getElementById("radio2") != null) {
		document.getElementById("radio2").checked = true;
	}
}

/* Edit Role */
function editRole(form, value) {
	document.getElementById("roleID").value = value;
	form.submit();
}

function viewRole(form) {
	$(form).attr("action", "viewRole.htm");
	$(form).submit();
}

/* User Page */

function editUser(form, value) {
	document.getElementById("userID").value = value;
	form.submit();
}

function formSubmit(form) {
	var content ='';
	if (document.getElementById("erroruserName") != null) {
		content = document.getElementById("erroruserName").innerHTML;
	}
	if (content == '' || content.length == 0) {
		document.getElementById("flowName").value = "Save";
		form.submit();
	}
}

function updateUser(form) {
	var content ='';
	if (document.getElementById("erroruserName") != null) {
		content = document.getElementById("erroruserName").innerHTML;
	}
	content = document.getElementById("erroruserName").innerHTML;
	if (content == '' || content.length == 0) {
		form.submit();
	}
}

function viewUser(form) {
	$(form).attr("action", "viewUser.htm");
	$(form).submit();
}

/* Role Wise Screen Access */
function changeSystemRole(form) {
	$(form).attr("action", "chngRoleWiseSysRole.htm");
	$(form).submit();
}

function loadHomePage(form) {
	clearForm(form);
	$(form).attr("action", "roleWiseAccess.htm");
	$(form).submit();
}