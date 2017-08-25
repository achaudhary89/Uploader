<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.cibil.util.CommonConstants"%>
<head>
<meta http-equiv="X-Frame-Options" content="deny" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<body>
	<form:form>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="topbd">
			<tr>
				<td align="left" width="10%"><img src="images/header_logo.jpg" width="96" height="86" /></td>
				<td width="70%" align="left" class="">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr><td class="onlinetxt">Online maintenance Datastore</td><td>&nbsp;</td></tr>
				<tr>
				<td class="welcomeOp"><%
									String userName = (null != session
												.getAttribute(CommonConstants.sessionUserName))
												? session.getAttribute(CommonConstants.sessionUserName)
														.toString() : null;
								%><%
						if (userName != null) {
							out.print("Welcome " + userName);
						}
					%></td>
				<td class="welcomeOp"><%
						String operationDate = (null != session
									.getAttribute(CommonConstants.operationDate))
									? session.getAttribute(CommonConstants.operationDate)
											.toString() : null;
							if (userName != null && operationDate != null) {
								out.print("Operation Date : " + operationDate);
							}
					%></td></tr>
				</table>
				
				</td>
				<td><table width="90%"
						border="0" cellspacing="0" cellpadding="6">
						
						<tr>
							<%
								if (userName != null) {
							%>
							<td align="right"><a href="#"
								onclick="tabSubmit('logout.htm')"> <img
									src="images/logout1.png" alt="Submit"
									style="width: 90px; height: 28px; border: none" />
							</a></td>
							<%
								}
							%>
						</tr>
					</table></td>
				
			</tr>
			
		</table>
	</form:form>
</body>
<script type="text/javascript" language="javascript">
	function DisableBackButton() {
		window.history.forward()
	}
	DisableBackButton();
	window.onload = DisableBackButton;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			DisableBackButton()
	}
	window.onunload = function() {
		void (0)
	}
	window.onbeforeunload = function() {
		void (0)
	}

	window.onload = function() {
		document.onkeydown = function(e) {
			if (e.which == 17 || e.keyCode == 17)
				return false;
			else if (e.which == 18 || e.keyCode == 18)
				return false;
			else if (e.which == 123 || e.keyCode == 123)
				return false;
			return (e.which || e.keyCode) != 116;
		};
	}

	var isNS = (navigator.appName == "Netscape") ? 1 : 0;

	if (navigator.appName == "Netscape")
		document.captureEvents(Event.MOUSEDOWN || Event.MOUSEUP);

	function mischandler() {
		return false;
	}

	function clickJack() {
		if (top.location != self.location) {
			top.location = self.location;
		}
	}

	clickJack();
	
	function mousehandler(e) {
		var myevent = (isNS) ? e : event;
		var eventbutton = (isNS) ? myevent.which : myevent.button;
		if ((eventbutton == 2) || (eventbutton == 3))
			return false;
	}
	document.oncontextmenu = mischandler;
	document.onmousedown = mousehandler;
	document.onmouseup = mousehandler;
</script>
</html>
