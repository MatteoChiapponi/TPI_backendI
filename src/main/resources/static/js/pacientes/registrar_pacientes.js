window.addEventListener('load', function () {

    const formulario = document.querySelector('#registrar_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#ingreso').value,
            email: document.querySelector('#email').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            }
        };
        const url = 'http://localhost:8080/patients';
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
                let successAlert =
                    '<div class="alert alert-success alert-dismissible">' +
                    '<strong> Paciente agregado exitosamente </strong>' +
                    '</div>';

                document.querySelector('#response').style.display = "block";
                document.querySelector('#response').innerHTML = successAlert;
                formulario.reset()

            })
            .catch(error => {
                console.log(error);
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                formulario.reset()
            })
    });
});