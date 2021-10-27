$('#userModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var pre = button.data('pre');
	var modal = $(this);
	modal.find('.modal-title').text(pre + ' User');

	if (pre === "Edit") {
		// Retrieve info from the data- attributes
		var email = button.data('email');
		var active = button.data('active');
		var password = button.data('password');
		var firstname = button.data('firstname');
		var lastname = button.data('lastname');
		var role = button.data('role');

		$('#email').val(email);
		$('#active').val(active);
		$('#password').val(password);
		$('#firstName').val(firstname);
		$('#lastName').val(lastname);
		$('#role').val(role);
			
		// Add the active switch to UI if not exists yet
		if (document.getElementById('activeswitch') === null) {
			$('#userForm').
				append ("\
					<div id='activeswitch' class='form-check form-switch d-flex justify-content-end'> \n\
						<input role='switch' class='form-check-input' type='checkbox' id='activetoggle' />\n\
						<label class='form-check-label align-items-center' for='active'>Active</label>\n\
					</div>");
		}

		// Set the toggle to proper value
		$('#activetoggle').prop('checked', active);

		
		$('#activetoggle').on('change', function() {
			$('#formactive').val(this.checked);		
		});

		document.forms['userForm'].action.value = "edit";
	}  else {

		$('#email').val('');
		$('#password').val('');
		$('#firstName').val('');
		$('#lastName').val('');
		$('#role').val('Select the role');
		$('#activeswitch').remove();
		document.forms['userForm'].action.value = "add";
	}
});
