<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.cibil.util.CommonConstants"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/> 
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery-ui.min.js"></script>
  <script type="text/javascript">
	var jQuery_min = $.noConflict(true);
 </script>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript">
	var jQuery_js = $.noConflict(true);
 </script>
  <script type="text/javascript" src="js/jquery.floatThead.js"></script>
  <script type="text/javascript" src="js/site.js"></script>

<style type="text/css">
.wrapper {
	overflow: auto;
	position: relative;
}

.wrapper.small {
	height: 400px;
	width: 500px;
}

table thead {
	background-color: #eee;
}

table.large {
	width: 900px;
}
</style>

<script type="text/javascript">
  var browser_ver=navigator.appVersion;

  function selectAllCheckBoxes(oForm) {
	 
		var frm_elements 	= oForm.elements;
		var select_all		= document.getElementById("select_all").checked;
		for (var i = 1; i < frm_elements.length; i++) {
			field_type 		= frm_elements[i].type.toLowerCase();
		if(field_type == "checkbox"){
			//alert(frm_elements[i]);
			var id		= frm_elements[i].id;
			var status		=	document.getElementById(id).disabled;
			if(select_all){
				//alert(status);
				if(status){
					//alert("disabled and unchecked");
					frm_elements[i].checked = false;
				}else{
					//alert("enabled and checked"+frm_elements[i].checked);
					frm_elements[i].checked = true;
				}
				
			}else{
				//alert("select_all unchecked setting all unchecked");
				frm_elements[i].checked = false;
			}
			
		}
		}
		}
  
  
	function tabSubmit(hrefName) {
		var form = document.getElementById("homePage");
		$(form).attr("action", hrefName);
		$(form).submit();
	}
	
	function exportSearch(form) {
		var frm_elements 	= form.elements;
		for (var i = 1; i < frm_elements.length; i++) {
			field_type 		= frm_elements[i].type.toLowerCase();
			if(field_type == "checkbox"){
				frm_elements[i].checked = true;
			}
		}
		$(form).attr("action", "exportSearch.htm");
		$(form).submit();
	}
	
	function deleteRecords(form){
		
		
		var frm_elements 	= form.elements;
		var check			=	false;
		for (var i = 1; i < frm_elements.length; i++) {
			field_type 		= frm_elements[i].type.toLowerCase();
			
		if(field_type == "checkbox" && frm_elements[i].id != "select_all" ){
			check		=	frm_elements[i].checked;
		}
			if(check	==	true){
				break;
			}
		}
		
		if(!check){
			document.getElementById('approveEmpty').innerHTML = 'No record selected to delete';
		} else {
			document.getElementById('approveEmpty').innerHTML = '';
			var decision	=	confirm("Are you sure you want to delete?");
			if(decision == true) {
				$(form).attr("action", "deleteSearch.htm");
				$(form).submit();
			}
		}
		
		
		
		
		
	/* 	
		
		
		
		var decision	=	confirm("Are you sure you want to delete");
		if(decision == true){
			$(form).attr("action", "deleteSearch.htm");
			$(form).submit();
		}else{
			$(form).attr("action", "searchMaster.htm");
			$(form).submit();//repoSearch.htm
		} */
	
}
  function fixheader(){
	var $demoTable= $("table.demo0");
    $demoTable.floatThead({
			scrollingTop : pageTop,
			scrollContainer : function(
					$table) {
				return $table
						.closest('.wrapper');
			},
			useAbsolutePositioning : false
		});
	}
  </script>
  <script type="text/javascript">
  $(document).ready(function() {
	  jQuery_min("#fromDate").datepicker({
		  	dateFormat:"yy-mm-dd",  
		  	changeMonth: true,
	        changeYear: true
	    });
  });
  $(document).ready(function() {
	  jQuery_min("#toDate").datepicker({
		  	dateFormat:"yy-mm-dd",  
		  	changeMonth: true,
	        changeYear: true
	    });
  });
    </script>
<style>

label {
  margin-left: -30px;
}
</style>
</head>
<body onload="fixheader();">
	<form:form id="homePage">
		<jsp:include page="security.jsp" />
	</form:form>
	<% if ("true".equals(pageContext.getAttribute("searchTab",PageContext.SESSION_SCOPE).toString())) { %>

     <form:form action="repoSearch.htm" commandName="DataStore" method="POST">
		<jsp:include page="security.jsp" />

		<table cellspacing="0" cellpadding="0" class="" width="100%">
			

			<tr>
			<td width="1%"></td>
				<td align="center"><br/>
    
   <div id="errorRoleAdd"></div>
   <div class="error" style=" font-size: 17px;"
				          align="center" id="approveEmpty"> 
				
  					  <c:if	test="${DataStore.validationName=='delete.excel.operation'}">
						<span style=" font-size: 17px;">&nbsp;
						<spring:message	code='delete.excel.operation' /></span>
					</c:if>
					<c:if test="${DataStore.validationName=='recordNotTodel.excel.operation'}">
						<span style="font-size: 17px;">&nbsp;
						<spring:message	code='recordNotTodel.excel.operation' /></span>
					</c:if>
					<c:if test="${DataStore.validationName=='search.excel.export'}">
						<span style="font-size: 17px;">&nbsp;
						<spring:message	code='search.excel.export' /></span>
					</c:if>
					<c:if test="${DataStore.validationName=='search.condition.result'}">
						<span style="font-size: 17px;">&nbsp;
						<spring:message	code='search.condition.result' /></span>
					</c:if>
					<form:errors path="message" cssStyle=" font-size: 17px;"/>
	</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" class="contentbg">&nbsp;</td>
							<td align="center" class="contentbg">&nbsp;</td>
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
                                            <td colspan="4" class="tableheading"><b>SEARCH</b></td>
                                                </tr>
        
            <tr>
              <td align="left"><b>Select Condition</b></td>
              <td colspan="3" align="left">
              
	           <div>
					<c:if test="${DataStore.condition=='1'}">
	           			AND <form:radiobutton path="condition" id="radio1" value="1" checked="checked" style="width:45px;border:0px solid #ffffff;"/>
	           			OR  <form:radiobutton path="condition" id="radio2" value="2" class="radio" style="width:45px;border:0px solid #ffffff;"/>
					</c:if>
					<c:if test="${DataStore.condition!='1'}">
	           			AND <form:radiobutton path="condition" id="radio1" value="1" class="radio" style="width:45px;border:0px solid #ffffff;;"/>
    	       			OR  <form:radiobutton path="condition" id="radio2" value="2" checked="checked" class="radio" style="width:45px;border:0px solid #ffffff;"/>
					</c:if>
				</div>
				
	    	 </td>
            
              </tr>
            <tr>
              <td align="left"><b>First Name</b></td>
              <td align="left">
                <form:input path="name" id="textfield" /></td>
              <td align="left"><b>Sir Name</b></td>
              <td align="left"> <form:input path="sirName" id="textfield7" /></td>
              </tr>
            <tr>
            <tr>
              <td align="left"><b>Fathers Name</b></td>
              <td align="left">
                <form:input path="fathersName" id="textfield" /></td>
              <td align="left"><b>mobile</b></td>
              <td align="left"> <form:input path="mobile1" id="textfield7" /></td>
              </tr>
            <tr>
              <td align="left"><b>PAN Number</b></td>
              <td align="left"> <form:input path="pan" id="textfield2" /></td>
              <td align="left"><b>Aadhar Number</b></td>
              <td align="left"> <form:input path="aadharNumber" id="textfield6" /></td>
              </tr>
            <tr>
            <tr>
              <td align="left"><b>Passport Number</b></td>
              <td align="left"> <form:input path="passportNumber" id="textfield2" /></td>
              <td align="left"><b>Voter ID</b></td>
              <td align="left"> <form:input path="voterID" id="textfield6" /></td>
              </tr>
            <tr>
              <td align="left"><b>Creation Date</b></td>
              <td align="left">From :
                 <form:input path="fromDate"  id="fromDate" style="width:164px;"/> </td>
              <td align="left"><b>File Name</b></td>
              <td align="left"><form:input path="fileName" id="textfield3" /></td>
              </tr>
            <tr>
              <td></td>
              <td align="left">To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <form:input path="toDate"  id="toDate" style="width:164px;"/></td>
              <td>             </td>
              <td> 
             </td>
              </tr>
            
         											</table>
										</td>
									</tr>
									<tr>
             
              <td colspan="4" align="center"><input type="submit" name="flowName" id="button"
											value="Retrieve" class="button" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" name="flowName" id="button"
											value="Reset" class="button" onclick="clearForm(this.form)"/> </td>
              
              </tr>
								</table> <br />
							</td>
						</tr>

					</table>
				</td>
			</tr>
		</table>
	</form:form>
	
<form:form   commandName="DataStore" method="POST">
<jsp:include page="security.jsp" />
						<table  border="0" cellspacing="0" cellpadding="0" class="" width="100%" align="center">
						<tr>
						<td width="1%"></td>
						<td>
						
					<table border="0" cellspacing="0" cellpadding="0" class="" align="center" width="100%">
						<tr>
						<td align="center">
						<c:if test="${fn:length(beansBag) gt 0}">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<div class="wrapper small" style="width:1305px; overflow:auto; height:480px;" align="center">
										<table width="100%" border="1" cellspacing="0" style="background-color: #ffffff;" cellpadding="4" class="datatable demo0">
											<thead>
												<tr class="">
														<!-- <td align="center" class="head">Role ID</td> -->
														<th align="center" class="head">Select/Select All
														<input type="checkbox" name="select_all" id="select_all" onclick="selectAllCheckBoxes(this.form)"/>
														</th>
														<th align="center" class="head">Name</th>
														<th align="center" class="head" style= "white-space: nowrap;padding:25px;">Sir Name</th>
														<th align="center" class="head">Fathers Name</th>
														<th align="center" class="head">Date Of Birth</th>
														<th align="center" class="head">Mobile1</th>
														<th align="center" class="head">Mobile2</th>
														<th align="center" class="head">Email1</th>
														<th align="center" class="head">Email2</th>
														<th align="center" class="head">Address Line 1</th>
														<th align="center" class="head">Address Line 2</th>
														<th align="center" class="head">Company Name 1</th>
														<th align="center" class="head">Company Name 2</th>
														<th align="center" class="head">City</th>
														<th align="center" class="head">State</th>
														<th align="center" class="head">Pin Code</th>
														<th align="center" class="head">PAN</th>
														<th align="center" class="head">Aadhaar Number</th>
														<th align="center" class="head">Voter Id</th>
														<th align="center" class="head">Passport Number</th>
														
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${beansBag}" var="excel" >
													<div id="eleememene">
													<input type="hidden"/>
													</div>
													<tr class="">
														<%-- <td align="center">
															<c:out value="${roles.roleID}"></c:out>
														</td> --%>
														
														<td align="center" class="tdata">
															<c:if test="${excel.flag != 0}">
	   														 <input id ="checkbox1" type="checkbox" name="selectedRecordIDCheckBox" value="${excel.id}"  />
	   														 
														</c:if>
														<c:if test="${excel.flag == 0}">
	   														 <input id ="checkbox2" type="checkbox" name="selectedRecordIDCheckBox" value="${excel.id}" disabled />
														</c:if>
														
														</td>
														<td align="center">
															<c:out value="${excel.name}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.sirName}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.fathersName}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.dob}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.mobile1}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.mobile2}"></c:out> 
														</td>
														<td align="center">
															<c:out value="${excel.email1}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.email2}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.addressLine1}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.addressLine2}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.companyName1}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.companyName2}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.city}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.addressLine3}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.pinCode}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.pan}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.aadharNumber}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.voterID}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.passportNumber}"></c:out>
														</td>
														<%-- <td align="center">
															<c:out value="${excel.srNo}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.olmDate}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.memberMe}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.consumerMe}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.accountNumber}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.accountToSupress}"></c:out> 
														</td>
														<td align="center">
															<c:out value="${excel.status}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.comments}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.ownershipIndicator}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.accountType}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.currentBalance}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.amountOverdue}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.dateClosed}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.dateOfLastPayment}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.dateReported}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.sactionedAmount}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.accountStatus}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.ndpdForLatestMonth}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.response}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.others}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.method}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.dateProcessed}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.requestBy}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.memberCode}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.time}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.dateRequirement}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.requestDetails}"></c:out>
														</td>
														<td align="center">
															<c:out value="${excel.communicationStatus}"></c:out>
														</td> --%>
														
													<%-- 	<td align="center" style = "display:none">
															<c:out value="${excel.id}"></c:out>
														</td> --%>
														
													</tr>
												</c:forEach>
											</tbody>
										</table>
										</div>
									</td>
								</tr>
								<tr>
								<td align="center"><br/><br/>
									<input type="submit" name="button" id="Approve"
											value="Export" class="button" onclick="exportSearch(this.form)"/>
											&nbsp;&nbsp;&nbsp;
									<% if ("true".equals(pageContext.getAttribute("viewTab",PageContext.SESSION_SCOPE).toString())) { %> 
											<input	type="button" name="button" id="Cancel" value="Delete"	class="button" onclick="deleteRecords(this.form);" />
											<% } %> 
								</td>
								</tr>
							</table>
							</c:if>
							
						 <br /> <br /></td>
						</tr>
						
		</table>
		
		</td>
		</tr>
		</table>
	</form:form>

<% } %>
<jsp:include page="/WEB-INF/views/sections/footer.jsp" />
</body>

<script type="text/javascript">  

	function matchHeight() {
		var form = document.getElementById('eleememene');
		if (form != null) {
			$('.footer').css('position', 'relative');
		}
	}
	matchHeight();

  $(document).ready(function() {
	  jQuery_min("#fromDate").datepicker().focus(function(event){
            var dim = $(this).offset();
            $("#ui-datepicker-div").offset({
                top     :   dim.top - 180,
                left    :   dim.left + 150
            });
        });
	  jQuery_min("#toDate").datepicker().focus(function(event){
            var dim = $(this).offset();
            $("#ui-datepicker-div").offset({
                top     :   dim.top - 180,
                left    :   dim.left + 150
            });
        });
		
  });
  
  </script>
</html>