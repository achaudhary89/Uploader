<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.cibil.util.CommonConstants"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.cibil.dao.RoleRightsDAO"%>
<%@ page import="com.cibil.bean.RoleRightsBean"%>
<%
	String name = (String) session.getAttribute(CommonConstants.sessionUserName);
	RoleRightsDAO roleRightsDAO = RoleRightsDAO.getInstance();
	RoleRightsBean roleRightsBean = roleRightsDAO.getUserRoles(name);
	if (!roleRightsBean.isRoleStatus() && roleRightsBean.isSearch() && roleRightsBean.isDataRepository() || roleRightsBean.isAdministrator()) {
		pageContext.setAttribute("viewTab", "true", PageContext.SESSION_SCOPE);
	}
	else
	{
		pageContext.setAttribute("viewTab", "false", PageContext.SESSION_SCOPE);
	}
	if (!roleRightsBean.isRoleStatus() && roleRightsBean.isSearch()) {
		pageContext.setAttribute("searchTab", "true", PageContext.SESSION_SCOPE);
	}else{
		pageContext.setAttribute("searchTab", "false", PageContext.SESSION_SCOPE);

	}
%>
<%
	
if (!roleRightsBean.isRoleStatus() && roleRightsBean.isSearch() && (roleRightsBean.isDataRepository() || roleRightsBean.isAdministrator() )) {
		pageContext.setAttribute("viewTab", "true", PageContext.SESSION_SCOPE);
	}else{
		pageContext.setAttribute("viewTab", "false", PageContext.SESSION_SCOPE);
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Online Maintenance Datastore - CIBIL</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/cibil.css" />
<link rel="stylesheet" type="text/css" href="js/chromestyle.css" />
<script type="text/javascript" src="js/chrome.js"></script>
<script type="text/javascript" language="JavaScript"
	src="js/jquery-2.1.1.js"></script>
<script type="text/javascript" language="JavaScript" src="js/role.js"></script>
<script type="text/javascript" language="JavaScript" src="js/timeout.js"></script>
</head>
<body>
	<form:form id="homePage">
		<input type="hidden" name="ctoken"
			value="<%=request.getAttribute("ctoken")%>" id="ctoken" />
	</form:form>
	<div class="menulink">
		<div class="chromestyle" id="chromemenu">
			<ul>
				<li>
					<% if (roleRightsBean.isRoleStatus()) { %>
					<center style="color:#000000;font-family: calibri">
						<spring:message code="error.roleStatus.Inactive" />
					</center> <%	} else if (!roleRightsBean.isPagewiseAccess()) { %>
					<center style="color:#000000;font-family: calibri">
						<spring:message code="error.roleStatus.InactivePageWiseAccess" />
					</center> <%	
					} else {
							if (roleRightsBean.isSearch()) { %> 
								<a href="#"	rel="dropmenu3" ><%=	roleRightsBean.getSearchLabel()	%></a>
							<%	}	%>
					</li>
					<li><%	
							if (roleRightsBean.isDataRepository()) { %> 
								<a href="#" rel="dropmenu2"><%=	roleRightsBean.getDataRepositoryLabel()	%></a>
							<%	}	%>
					</li>
					<li><%	
							if (roleRightsBean.isAdministrator()) { %> 
								<a href="#" rel="dropmenu1"><%=	roleRightsBean.getAdminLabel()	%></a> 
							<%	}	%>
					 <%	}	%>
				</li>

			</ul>
		</div>
		
		<%if (roleRightsBean.isSearch()) { %>
		<div id="dropmenu3" class="dropmenudiv">
			<a href="#" onclick="tabSubmit('searchMaster.htm')"><%=	roleRightsBean.getSingleSearch()	%>
			</a> <a href="#" onclick="tabSubmit('advancedSearch.htm')"><%=	roleRightsBean.getAdvancedSearch()	%>
			</a>
		</div>
		<%	} %>
		
		
		<%if (roleRightsBean.isAdministrator()) { %>
		<div id="dropmenu1" class="dropmenudiv">
			<a href="#" onclick="tabSubmit('userMaster.htm')"><%=	roleRightsBean.getUserMaintenanceLabel()	%>
			</a> <a href="#" onclick="tabSubmit('roleMaster.htm')"><%=	roleRightsBean.getRoleMasterLabel()	%>
			</a> <a href="#" onclick="tabSubmit('roleWiseAccess.htm')"><%=	roleRightsBean.getRolewiseAccessRightLabel()	%>
			</a>
		</div>
		<%	} %>
		<%	if (roleRightsBean.isDataRepository()) { %>
		<div id="dropmenu2" class="dropmenudiv">
			<a href="#" onclick="tabSubmit('upload.htm')"><%=	roleRightsBean.getUploadLabel()	%></a>
		</div>
		<%	} %>



		<script type="text/javascript">

    	cssdropdown.startchrome("chromemenu")
		function tabSubmit(hrefName) {
			var form = document.getElementById("homePage");
			$(form).attr("action", hrefName);
			$(form).submit();
			}
		</script>
	</div>
</body>
</html>
