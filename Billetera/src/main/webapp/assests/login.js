/**
 * 
 */

	const formulario = document.querySelector("#loginForm");
	
	formulario.addEventListener("submit", function(event) {
		
		let isValid = true;
		
		const user = document.querySelector("#usuario");
		const pass = document.querySelector("#password");
		
		// Validar usuario
		const userRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
		
		if(!userRegex.test(user.value)){
			user.classList.add('is-invalid');
			errorMsgUser.style.display = "block";
			errorMsgUser.innerText = "Debe ingresar un usuario válido";
			isValid = false;
		}else{
			user.classList.remove('is-invalid');
		}
		
		// Validar clave
		const passRegex = /^(?=.*[A-Z])(?=.*\d).{5,}$/;
		if(!passRegex.test(pass.value)) {
			pass.classList.add('is-invalid');
			errorMsgPass.style.display = "block";
			errorMsgPass.innerText = "Debe ingresar una clave válida";
	
			isValid = false;
		}else{
			pass.classList.remove('is-invalid');
		}
		
		if (!isValid) {
			event.preventDefault();
			event.stopPropagation();
		}
	});
	
	const user = document.querySelector("#usuario");
	const pass = document.querySelector("#password");
	
	user.addEventListener("input", function () {
		errorMsgUser.style.display = "none";
		user.classList.remove('is-invalid');
	});

	pass.addEventListener("input", function () {
		errorMsgPass.style.display = "none";
		pass.classList.remove('is-invalid');
	});
