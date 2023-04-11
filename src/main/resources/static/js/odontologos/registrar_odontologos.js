window.addEventListener('load', () => {

    const formulario = document.getElementById('registrar_paciente');
    const responseServer = document.getElementById('response');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault()

        const formData = {
            matricula: document.getElementById('matricula').value,
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value,
            email: document.getElementById('email').value
        };

        const url = 'http://localhost:8080/dentists';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<strong> Odontologo agregado exitosamente </strong> </div>'
                responseServer.innerHTML = successAlert;
                responseServer.style.display = "block";
                setTimeout(() => {
                    responseServer.style.display = "none";
                }, 000)
                formulario.reset();
            })
            .catch(error => {
                console.log("en el catch maestro y el error es " + error);
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<strong> Error intente nuevamente</strong> </div>'
                responseServer.innerHTML = errorAlert;
                responseServer.style.display = "block";
                setTimeout(() => {
                    responseServer.style.display = "none";
                }, 5000)
                formulario.reset();
            })
    })
})
