<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- <link href="/OLMDataStore/resources/css/main.css" rel="stylesheet" type="text/css" /> -->
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="main">
		<tr>
			<td align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="logintable">
					<tr>
						<td colspan="3"><tiles:insertAttribute name="header-content" />

						</td>
					</tr>
					<tr>
						<td colspan="3"><tiles:insertAttribute name="menu-content" />
						</td>
					</tr>

					<tr>
						<td align="left" valign="top" width="90%" class="cheight"><tiles:insertAttribute
								name="body-content" /></td>
					</tr>
					<tr>
						<td colspan="3"><tiles:insertAttribute name="footer-content" />

						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>