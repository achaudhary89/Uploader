<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
 
 <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/> 
 <link type="text/css" rel="stylesheet" href="css/cibil.css" />
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.js"></script>
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

	function selectAllCheckBoxes(oForm) {
		
		var frm_elements 	= oForm.elements;
		var select_all		= document.getElementById("select_all").checked;
		//alert(frm_elements.length);
		for (var i = 1; i < frm_elements.length; i++) {
			field_type 		= frm_elements[i].type.toLowerCase();
		
		if(field_type == "checkbox"){
			var id		= frm_elements[i].id;
			var status		=	document.getElementById(id).disabled;
			if(select_all){
				//alert(status);
				if(status){
					//alert("disabled and unchecked");
					frm_elements[i].checked = false;
				}else{
					//alert("enabled and checked");
					frm_elements[i].checked = true
				}
				
			}else{
				//alert("select_all unchecked setting all unchecked");
				frm_elements[i].checked = false;
			}
			
		}
		}
		}
	
	function loadExcelPage(form){
			$(form).attr("action", "excelCancel.htm");
			$(form).submit();
		
	}
	
	 function downloadRejectedRecords(form){
		alert("downloading rejected records");
		$(form).attr("action", "downloadRejectedRecords.htm");
		$(form).submit();
	
	}
	
	
	
/* 	function approvePage(form){
		$(form).attr("action", "excelApprove.htm");
		$(form).submit();
	
	} */
	
	
	function approvePage(form){
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
	if(check){	
		$(form).attr("action", "excelApprove.htm");
		$(form).submit();
		}else{
			document.getElementById('approveEmpty').innerHTML = 'No record selected to approve';
			
		}
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

</head>

<body>


	<form:form action="excelSubmit.htm" commandName="DataStore" method="POST" enctype="multipart/form-data">
	
		<jsp:include page="security.jsp" />
		
		<table cellspacing="0" cellpadding="0" class="" width="100%">
	
			<tr>
			<td width="1%"></td>
				<td align="center">&nbsp;
				  <c:if
						test="${DataStore.validationName=='error.excel.format'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.format' /></span>
					</c:if> 
					<div class="error" style="font-size: 17px;"
				          align="center" id="approveEmpty"> </div>
					 <c:if
						test="${DataStore.validationName=='error.excel.fileSelect'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.fileSelect' /></span>
					</c:if> <c:if
						test="${DataStore.validationName=='success.excel.recordInsert'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='success.excel.recordInsert' /></span>
					</c:if> <c:if
						test="${DataStore.validationName=='cancel.excel.operation'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='cancel.excel.operation' /></span>
					</c:if>
						<c:if
						test="${DataStore.validationName=='error.excel.fileEmpty'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.fileEmpty' /></span>
					</c:if>
					<c:if
						test="${DataStore.validationName=='error.excel.fileSize'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.fileSize' /></span>
					</c:if>
					 <c:if
						test="${DataStore.validationName=='error.excel.invalid'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.invalid' /></span>
					</c:if>
					<c:if
						test="${DataStore.validationName=='error.excel.data'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.data' /></span>
					</c:if>
					<c:if
						test="${DataStore.validationName=='error.excel.invalid.header'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.invalid.header' /></span>
					</c:if>
					<c:if
						test="${DataStore.validationName=='error.excel.invalid.columns'}">
						<span style="font-size: 17px;">&nbsp;<spring:message
								code='error.excel.invalid.columns' /></span>
					</c:if>
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
										<td colspan="2" class="tableheading">Upload Data</td>
									</tr>
									<tr>
										<td colspan = "2">&nbsp;</td>
										
										
									</tr>
									<tr>
										<td align = "center"><b>Select File</b><span class="red"></span></td>
											<td colspan="2"><label for="textfield"></label> <label
											for="file"></label> 
											<input name="typeData" type="hidden" id="typeData" value="SEARCH"></input>
											
											<input name="file" type="file"
											id="file" size="50" /></td>
											
											
									</tr>

											</table>
										</td>
									</tr>
									<tr>
									
										<td valign="top" colspan="2" align = "center"><input type="submit" name="button" id="popup_window"
											value="Upload File" class="button" /></td>
										
									</tr>
								</table>
								
								 <c:choose>
	 					    <c:when test="${DataStore.approvedCount > 0}">
	 					    </br>
	 					         <table width="98%" cellspacing="0">
									<tr><td style="width:40%"></td><td align="left"><span style="color: #000000; font-size: 15px;">Duplicate records found&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${DataStore.duplicateCount}" ></c:out></span></td></tr>		
									<tr><td style="width:40%;"></td><td align="left"><span style="color: #000000; font-size: 15px;">Rejected records found&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${DataStore.rejectedCount}" ></c:out></span></td></tr>		
									<tr><td style="width:40%;"></td><td align="left"><span style="color: #000000; font-size: 15px;">Approved records found&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${DataStore.approvedCount}"></c:out> </span></td></tr>
					                <tr><td style="width:40%;"></td><td align="left"><span style="color: #000000; font-size: 15px;">Invalid records found&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${DataStore.invalidCount}" ></c:out></span></td></tr>
					                <tr><td style="width:40%;"></td><td align="left"><span style="color: #000000; font-size: 15px;">Total records found&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${DataStore.duplicateCount +DataStore.rejectedCount+DataStore.approvedCount+DataStore.invalidCount}" ></c:out></span></td></tr>
									
								</table>
						     
							</c:when>
					     </c:choose> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
					
	<form:form  id="StagingForm" commandName="DataStore" method="POST" action ="downloadRejectedRecords.htm">
						<jsp:include page="security.jsp"/>
						<form:hidden path="format"/>
						<form:hidden id= "download" path="downloadRejected"/>
						<table  border="0" cellspacing="0" cellpadding="0" class="" width="100%" align="center">
						<tr>
						<td width="1%"></td>
						<td>
						
					<table border="0" cellspacing="0" cellpadding="4" class="" align="center" width="100%">
						<tr>
					
						<td align="center">
			
						<c:if test="${fn:length(beansBag) gt 0}"><br/>
								<div id="eleememene">
													<input type="hidden"/>
													</div>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									 <div class="wrapper small" style="width:1300px; overflow:auto; height:480px;" align="center">
										<table width="100%" border="1" cellspacing="0" style="background-color: #ffffff;" cellpadding="4" class="datatable demo0">
											<thead>
												<tr char="">
													
													<!-- <th align="center" class="head" >Select/Select All
													<input type="checkbox" name="select_all" id="select_all" onclick="selectAllCheckBoxes(this.form)"/>
													</th>
													<th  align="center" class="head" >SR.NO</th>
													<th align="center" class="head" style= "white-space: nowrap;padding:25px;">Date</th>
													<th align="center" class="head" >Member Name</th>
													<th align="center" class="head" >Borrower Name</th>
													<th align="center" class="head" >Account Number</th>
													<th align="center" class="head" >Account To Be Suppressed</th>
													<th align="center" class="head" >Status</th>
													<th align="center" class="head" style= "white-space: nowrap;padding:25px;">Comments</th>
													<th align="center" class="head" >Ownership Indicator</th>
													<th align="center" class="head" >Account Type</th>
													<th align="center" class="head" >Current Balance</th>
													<th align="center" class="head" >Amount OverDue</th>
													<th align="center" class="head" >Date Closed</th>
													<th align="center" class="head" >Date Of Last Payment</th>
													<th align="center" class="head" >Date Reported</th>
													<th align="center" class="head" >Sanctioned Amount</th>
													<th align="center" class="head" >Ndpd for latest month</th>
													<th align="center" class="head" >Account Status</th>
													<th align="center" class="head" >Others</th>
													<th align="center" class="head" >Response</th>
													<th align="center" class="head" >Date Processed</th>
													<th align="center" class="head" >Method</th>
													<th align="center" class="head" >Member Code</th>
													<th align="center" class="head" >Request By</th>
													<th align="center" class="head" >Date Requirement</th>
													<th align="center" class="head" >Time</th>
													<th align="center" class="head" >Communication status</th>
													<th align="center" class="head" style= "white-space: nowrap;padding:25px;">Request Details</th> -->
													
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
									<input type="button" name="button" id="Approve"
											value="Approve" class="button" onclick = "approvePage(this.form);" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" name="button" id="Cancel"
											value="Cancel" class="button" onclick = "loadExcelPage(this.form);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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
	<jsp:include page="/WEB-INF/views/sections/footer.jsp" />
	<script  type="text/javascript">
	
	function matchHeight() {
		var form = document.getElementById('eleememene');
		if (form != null) {
			$('.footer').css('position', 'relative');
		}
	}
	matchHeight();

	
	var check			= document.getElementById('download').value;
	//alert(check);
	if(check == 'true'){
	 var form			= document.getElementById('StagingForm'); 	
	var frm_elements 	= form.elements;
	//alert("inside script:"+frm_elements.length);
	if(frm_elements.length > 3){ 
		
		document.getElementById('StagingForm').submit();	
	}
	}
	
	  $(document).ready(function() {
			fixheader();
	  });
	  
	  $( window ).scroll(function() {
		  fixheader();
		});
	  fixheader();

</script>
</body>
</html>
