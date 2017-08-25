<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="label.app.title" /></title>
</head>
<body>
	<form:form action="userSubmit.htm" commandName="UserBean" method="POST">
		<jsp:include page="security.jsp" />
		<div id="errorRoleAdd">
			<c:if test="${UserBean.valFailed==true }">
				<div align="center" class="error">
					<span style=" font-size: 17px;">&nbsp;<spring:message
							code="cibil.error.mandatory.field" /></span>
				</div>
			</c:if>
			<c:if test="${UserBean.specialCharValFailed==true }">
				<div align="center" class="error">
					<span style="font-size: 17px;">&nbsp;<spring:message
							code="cibil.error.special.field" /></span>
				</div>
			</c:if>
			<div class="error" style="font-size: 17px;"
				align="center" id="erroruserName"></div>
			<div align="center">
				<form:errors path="emailId"
					cssStyle="font-size: 17px;" />
				<br />
				<form:errors path="mobileNumber"
					cssStyle="font-size: 17px;" />
			</div>
		</div>
		<form:hidden path="flowName" id="flowName" />


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
							<td><table width="40%" border="0" cellspacing="0"
									cellpadding="4">
									<tr>
										<td class="tableheading" onclick="tabSubmit('userMaster.htm')" onmouseover="this.style.cursor='pointer';">Add User</td>
										<td class="tableheadingd" onclick="tabSubmit('viewUser.htm')" onmouseover="this.style.cursor='pointer';"><a href="#"
											onclick="tabSubmit('viewUser.htm')">User Maintenance</a></td>
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
												<tr>
													<td width="25%" align="left"><b><spring:message
																code="label.user.empName" /></b><span class="red">*</span></td>
													<td width="25%" align="left"><form:input path="empName"
															maxlength="200" onpaste="return false;" /></td>
													<td width="25%" align="left"><b><spring:message
																code="label.user.name" /></b><span class="red"> *</span></td>
													<td width="25%" align="left"><form:input path="userName"
															id="userName" maxlength="50" onpaste="return false;"
															onblur="checkifNameExists(this.form,this.id,this.value)" />
													</td>
												</tr>

												<tr>
													<td align="left"><b><spring:message code="label.user.sysRole" /></b><span
														class="red"> *</span></td>
													<td align="left"><form:select id="docNo" path="systemRole">
															<form:options items="${systemRoleList}"
																itemValue="roleID" itemLabel="roleName" />
														</form:select></td>
													<td align="left"><b><spring:message code="label.user.empType" /></b><span
														class="red"> *</span></td>
													<td align="left"><form:input path="empType" maxlength="50"
															onpaste="return false;" /></td>
												</tr>

												<tr>
													<td align="left"><b><spring:message code="label.user.status" /></b><span
														class="red"> *</span></td>
													<td align="left"><form:select id="status" path="status">
															<form:options items="${userStatusList}"
																itemValue="codeID" itemLabel="codeDescription" />
														</form:select></td>
													<td align="left"><b><spring:message code="label.user.mobNo" /></b></td>
													<td align="left"><form:input path="mobileNumber" maxlength="15"
															onpaste="return false;" /></td>
												</tr>
												<tr>
													<td align="left"><b><spring:message code="label.user.emailID" /></b></td>
													<td align="left"><form:input path="emailId" maxlength="150"
															onpaste="return false;" /></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>


											</table>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center"><input type="button"
											name="flowName" id="button" value="Save" class="button"
											onclick="formSubmit(this.form);" />
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
