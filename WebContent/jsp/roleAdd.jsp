<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/cibil.css" />
<script type="text/javascript" language="JavaScript" src="js/role.js"></script>
<title><spring:message code="label.app.title" /></title>
</head>
<body>
	<form:form action="roleSubmit.htm" commandName="RoleBean" method="POST">
		<jsp:include page="security.jsp" />
		<form:hidden path="flowName" id="flowName" />
		<div id="errorRoleAdd">
			<c:if test="${RoleBean.valFailed==true }">
				<div align="center" class="error">
					<span style="font-size: 17px;">&nbsp;<spring:message
							code="cibil.error.mandatory.field" /></span>
				</div>
			</c:if>
			<c:if test="${RoleBean.specialCharValFailed==true }">
				<div align="center" class="error">
					<span style=" font-size: 17px;">&nbsp;<spring:message
							code="cibil.error.special.field" /></span>
				</div>
			</c:if>
			<div class="error" style="font-size: 17px;"
				align="center" id="errorroleName"></div>
		</div>

		<table cellspacing="0" cellpadding="0" class="" width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>
				<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" class="contentbg">&nbsp;</td>
							<td align="center" class="contentbg">&nbsp;</td>
						</tr>
						<tr>
							<td><table width="40%" border="0" cellspacing="0"
									cellpadding="4">
									<tr>
										<td class="tableheading" onclick="tabSubmit('roleMaster.htm')"  onmouseover="this.style.cursor='pointer';">Add Role</td>
										<td class="tableheadingd" onclick="tabSubmit('viewRole.htm')"  onmouseover="this.style.cursor='pointer';"><a href="#"
											onclick="tabSubmit('viewRole.htm')">Role Maintenance</a></td>
									</tr>
								</table></td>
						</tr>

						<tr>
							<td colspan="2" class="contentbg">
								<table width="98%" border="1" cellspacing="0" cellpadding="4"
									style="border-collapse: collapse; background-color: #ffffff;"
									bordercolor="#e39997">
									<tr>
										<td colspan="4">
											<table width="100%" border="1" cellspacing="0"
												cellpadding="4" class="datatable">
												<tr class="">
													<td align="left"><b><spring:message code="label.role.nameRole" /></b><span
														class="red">*</span></td>
													<td align="left"><form:input path="roleName"
															onpaste="return false;" id="roleName" maxlength="50"
															onblur="checkifNameExists(this.form,this.id,this.value)" />
													</td>
													<td align="left"><b><spring:message code="label.role.status" /></b><span
														class="red"> *</span></td>
													<td align="left"><form:select id="docNo" path="status">
															<form:options items="${roleStatusList}"
																itemValue="codeID" itemLabel="codeDescription" />
														</form:select></td>
												</tr>
												<tr>
													<td align="left"><b><spring:message code="label.role.desc" /></b><span
														class="red"> *</span></td>
													<td align="left"><form:input path="description"
															maxlength="200" onpaste="return false;" /></td>
															<td> </td><td> </td>

												</tr>

											</table>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center"><input type="button"
											name="flowName" id="button" value="Save" class="button"
											onclick="submitForm(this.form);" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
											type="button" name="flowName" id="button2" value="Clear"
											class="button" onclick="clearForm(this.form);" /></td>
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
