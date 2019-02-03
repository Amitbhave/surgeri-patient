//get table data
function getPatientsData() {
	console.log('Getting patients data');

	$
			.ajax({
				type : "GET",
				url : "/patient/getAll",
				success : function(data) {
					$("#patientData").empty();
					var tableData;
					var index = 0;
					$
							.each(
									data,
									function(i, record) {
										tableData += "<tr><td>"
												+ (i + 1)
												+ "</td><td class=\"mdl-data-table__cell--non-numeric\">"
												+ record.patientName
												+ "</td><td>"
												+ record.age
												+ "</td><td class=\"mdl-data-table__cell--non-numeric wraptd\">"
												+ record.sex + "</td><td>"
												+ record.mobileNumber
												+ "</td></tr>"
									});
					$("#patientData").append(tableData);
					$(document).ready(function() {
						$('#patientTable').DataTable({
							"ordering" : false,
							"pageLength" : 5
						});
					});
				},
				error : function(data) {
					console.log('An error occurred.');
					console.log(data);
				},
			});

	console.log('Getting patient data complete');
}

function addPatient() {
	var isValidForm = validateAddProduct();
	if (isValidForm) {

		var patientRecord = {}
		patientRecord["patientName"] = $("#patientName").val();
		patientRecord["age"] = $("#age").val();
		patientRecord["sex"] = $('input[name=sex]:checked').val();
		patientRecord["mobileNumber"] = $("#mobileNumber").val();

		$.ajax({
			type : "POST",
			contentType : "application/json; charset=utf-8",
			url : "http://localhost:8080/patient/create",
			data : JSON.stringify(patientRecord),
			// dataType : 'json',
			success : function(data, xhr) {
				console.log("Patient added successfully");
				refreshTable();
			},
			error : function(data) {
				console.log('Patient addition error: '
						+ data.responseJSON.errorMessage);
				window.location = "/";
			},
		});
	}
}

function validateAddProduct() {
	var patientName = $("#patientName").val();
	var age = $("#age").val();
	var mobileNumber = $("#mobileNumber").val();

	if (!patientName || patientName == "") {
		$("#patientnameDiv").addClass('is-dirty');
		$("#patientnameDiv").addClass('is-invalid');
		return false;
	} else {
		$("#patientnameDiv").removeClass('is-dirty');
		$("#patientnameDiv").removeClass('is-invalid');
	}

	if (!age || age == "") {
		$("#ageDiv").addClass('is-dirty');
		$("#ageDiv").addClass('is-invalid');
		return false;
	} else {
		$("#ageDiv").removeClass('is-dirty');
		$("#ageDiv").removeClass('is-invalid');
	}

	if (!mobileNumber || mobileNumber == "") {
		$("#mobilenumberDiv").addClass('is-dirty');
		$("#mobilenumberDiv").addClass('is-invalid');
		return false;
	} else {
		$("#mobilenumberDiv").removeClass('is-dirty');
		$("#mobilenumberDiv").removeClass('is-invalid');
	}

	return true;
}

function refreshTable() {
	$('#patientTable').DataTable().destroy();
	getPatientsData();
}