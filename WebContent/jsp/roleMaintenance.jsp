<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Maintainance Datastore - CIBIL</title>
</head>
<body>
	<form:form action="editRole.htm" commandName="RoleBean" method="POST">
		<jsp:include page="security.jsp" />
		<form:hidden path="roleID" id="roleID" />
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="center"><span
					style="font-size: 17px;">&nbsp; <c:if
							test="${RoleBean.message=='role.added'}">
							<spring:message code="role.added" />
						</c:if> <c:if test="${RoleBean.message=='role.updated'}">
							<spring:message code="role.updated" />
						</c:if>
				</span></td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="0" class=""
			width="100%">
			<tr>
				<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" class="contentbg">&nbsp;</td>
							<td align="center" class="contentbg">&nbsp;</td>
						</tr>
						<tr>
							<td><%@ include file="roleMaintenanceSubMenu.jsp"%>

							</td>
						</tr>
						<tr>
							<td colspan="2" align="" class="contentbg">
								<table width="98%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="1" cellspacing="0"
												style="background-color: #ffffff;" cellpadding="4"
												class="datatable">
												<%-- <tr class="">
													<!-- <td align="center" class="head">Role ID</td> -->
													<td align="center" class="head">Role Name</td>
													<td align="center" class="head">Status</td>
													<td align="center" class="head">Description</td>
													<td align="center" class="head">Edit</td>
											</tr>
											<c:forEach items="${roleBeanList}" var="roles">
												<tr class="">
													<td align="center">
														<c:out value="${roles.roleID}"></c:out>
													</td>
													<td align="center">
														<c:out value="${roles.roleName}"></c:out>
													</td>
													<td align="center">
														<c:out value="${roles.status}"></c:out>
													</td>
													<td align="center">
														<c:out value="${roles.description}"></c:out>
													</td>
													<td align="center">
														 <input type="button" id="button" value="Edit" class="button" onclick="editRole(this.form,'<c:out value="${roles.roleID}"/>')" />
													</td>
												</tr>
											</c:forEach> --%>
												<% 	String pageSize = (String) request.getAttribute("pageSize");
 											  	int selectedPageSize = Integer.valueOf(pageSize).intValue(); 
 												String tokenValue = (String) request.getAttribute("ctoken");
 												String url="viewRole.htm?ctoken="+tokenValue;
 											%>

												<div align="center"
													style="text-align: center; color: #a13c39; font-size: 15px;">
													<display:table name="roleBeanList"
														pagesize="<%= selectedPageSize%>" id="roles"
														class="appyborderColor" requestURI="<%=url%>"
														excludedParams="*">
														<display:column property="roleName" title="Role Name"
															headerClass="head" />
														<display:column property="status" title="Status"
															headerClass="head" />
														<display:column property="description" title="Description"
															headerClass="head" />
														<display:column title="Edit" headerClass="head">
															<input type="button" id="button" value="Edit"
																class="buttonedit"
																onclick="editRole(this.form,'<c:out value="${roles.roleID}"/>')" />
														</display:column>
													</display:table>
												</div>
												<!-- </table> -->
												</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td align="center" class="contentbg">&nbsp;</td>
										<td align="center" class="contentbg">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td height="19">&nbsp;</td>
										<td height="19">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table> </form:form>
</body>
</html>
