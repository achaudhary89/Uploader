<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="label.app.title" /></title>
</head>

<body>
	<form:form action="rolewiseAccessSubmit.htm"
		commandName="RoleWiseAccessBean" method="POST">
		<jsp:include page="security.jsp" /><br />
		<form:errors path="selectedMenuIDCheckBox" />
		<div align="center" class="error">
			<c:if test="${RoleWiseAccessBean.valFailed==true }">
				<div align="center" class="error">
					<span style=" font-size: 17px;">&nbsp;<spring:message
							code="cibil.error.mandatory.field" /></span>
				</div>
			</c:if>
			<c:if test="${RoleWiseAccessBean.message=='rolewise.add.updated'}">
				<span style="font-size: 17px;">&nbsp;<spring:message
						code="rolewise.add.updated" /></span>
			</c:if>
		</div>
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
							<td><table width="20%" border="0" cellspacing="0"
									cellpadding="4">
									<tr>
										<td colspan="2" class="tableheading">Rolewise Access
											Rights</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td colspan="2" class="contentbg">
								<table width="98%" border="1" cellspacing="0" cellpadding="4"
									style="border-collapse: collapse; background-color: #ffffff;"
									bordercolor="#e39997">

									<tr>
										<td><b>System Role</b> <span class="red">*</span></td>
										<td><form:select id="docNo" path="systemRole"
												onchange="changeSystemRole(this.form);">
												<form:option value="NONE" label="--- Select ---" />
												<form:options items="${systemRoleList}" itemValue="roleID"
													itemLabel="roleName" />
											</form:select></td>
									</tr>
									<tr>
										<td colspan="4">
											<table width="100%" border="1" cellspacing="0"
												cellpadding="4" class="datatable">
												<tr class="">
													<td align="center" class="head">Sr No</td>
													<td align="center" class="head">Screen Name</td>
													<td align="center" class="head">Access Rights</td>
												</tr>
												<tr>
													<c:forEach items="${menuList}" var="menu">
														<tr class="">
															<td align="center"><c:out value="${menu.serialNo}"></c:out>
															</td>
															<td align="center"><c:out value="${menu.screenName}"></c:out>
															</td>
															<td align="center"><c:if
																	test="${menu.isPreSelected==true}">
																	<input type="checkbox" name="selectedMenuIDCheckBox"
																		value="${menu.menuID}" checked="checked" style="width:45px;border:0px solid #ffffff;"/>
																</c:if> <c:if test="${menu.isPreSelected==false}">
																	<input type="checkbox" name="selectedMenuIDCheckBox"
																		value="${menu.menuID}" style="width:45px;border:0px solid #ffffff;"/>
																</c:if></td>
														</tr>
													</c:forEach>

												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center"><input name="button"
											id="button" type="submit" value="Save" class="button" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
											name="button" id="button2" type="submit" value="Clear"
											class="button" onclick="loadHomePage(this.form);" /></td>
									</tr>
								</table> <br /> <br />
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="19">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
