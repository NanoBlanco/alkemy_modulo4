/**
 * 
 */
const formulario = document.querySelector("#registrarForm");
	
	formulario.addEventListener("submit", function(event) {
		
		let isValid = true;
		
		const nombre = document.querySelector("#nombre");
		const user = document.querySelector("#usuario");
		const pass = document.querySelector("#password");
		
		// Validar nombre
		const nombreRegex = /^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+){0,9}$/;
		if(!nombreRegex.test(nombre.value)){
			nombre.classList.add('is-invalid');
			errorMsgNom.style.display = "block";
			errorMsgNom.innerText = "Debe ingresar un nombre válido";
			isValid = false;
		}else{
			nombre.classList.remove('is-invalid');
		}
		
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
	
	const nombre = document.querySelector("#nombre");
	const user = document.querySelector("#usuario");
	const pass = document.querySelector("#password");
	
	nombre.addEventListener("input", function () {
		errorMsgNom.style.display = "none";
		nombre.classList.remove('is-invalid');
	});
		
	user.addEventListener("input", function () {
		errorMsgUser.style.display = "none";
		user.classList.remove('is-invalid');
	});

	pass.addEventListener("input", function () {
		errorMsgPass.style.display = "none";
		pass.classList.remove('is-invalid');
	});
