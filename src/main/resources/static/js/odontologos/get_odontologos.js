window.addEventListener('load', function () {
    const formulario = document.querySelector('#form_actualizar_odontologo');
    const url = 'http://localhost:8080/dentists/all';
    const settings = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
    }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            const odontologosData = data;
            let dashboardOdontologos = document.getElementById("container-dashboard");
            data.forEach(odontologo => {
                dashboardOdontologos.innerHTML += `
            <tr>
            <td scope="row" id="row-id">${odontologo.id}</td>
            <td scope="row" id="row-nombre">${odontologo.nombre}</td>
            <td scope="row" id="row-apellido">${odontologo.apellido}</td>
            <td scope="row" id="row-email">${odontologo.email}</td>
            <td scope="row" id="row-matricula">${odontologo.matricula}</td>
            <td scope="row"><button id=${odontologo.id} class="btn btn-primary">Actualizar</button></td>
            <td scope="row"><button id=${odontologo.id} class="btn btn-danger">Eliminar</button></td>
            </tr>`
            });
            const botonesEliminar = dashboardOdontologos.querySelectorAll("button");
            botonesEliminar.forEach((boton, i) => {
                if (i % 2 !== 0) {
                    boton.addEventListener("click", (e) => {
                        if (formulario.hasChildNodes()) {
                            this.location.reload()
                        }
                        const idboton = e.target.getAttribute("id");
                        const settings = {
                            method: 'DELETE'
                        }
                        fetch(`http://localhost:8080/dentists/${idboton}`, settings)
                            .then(() => {
                                this.location.reload()
                            }).catch((err) => {
                                console.log("no pudimos eliminar el odontologo " + err);
                            });
                    })
                } else {
                    boton.addEventListener("click", e => {
                        if (formulario.hasChildNodes()) {
                            console.log("ya tiene hijoss");
                        }
                        else {
                            const idOdontologo = parseInt(e.target.getAttribute("id"));
                            const odontologo = odontologosData.find(odontologo => odontologo.id === idOdontologo)
                            actualizarOdontologo(odontologo)
                        }
                    })
                }
            });
        })
    function actualizarOdontologo(odontologo) {
        formulario.innerHTML += `
        <h3>Actualizar odontologo</h3>
        <br>
        <div class="mb-3">
          <div class="row">
            <div class="col">
              <label for="new_nombre" class="form-label">Nombre</label>
              <input type="text" class="form-control" id="new_nombre" name="new_nombre" value=${odontologo.nombre} required>
            </div>
            <div class="col">
              <label for="new_apellido" class="form-label">Apellido</label>
              <input type="text" class="form-control" id="new_apellido" name="new_apellido" value=${odontologo.apellido} required>
            </div>
            <div class="col">
              <label for="new_email" class="form-label">Correo</label>
              <input type="email" class="form-control" id="new_email" name="new_email" value=${odontologo.email} required>
            </div>
          </div>
          <br>
          <div class="row">
            <div class="col">
              <label for="new_dni" class="form-label">DNI</label>
              <input type="text" class="form-control" id="new_matricula" name="new_matricula" value='${odontologo.matricula}' required>
            </div>
          </div>
          <br>
          <button class="btn btn-primary" type="submit" id="btn-actualizar">Actualizar</button>
          <button class="btn btn-secondary" type="button" id="btn-cancelar">Cancelar</button>
        `

        const btn_cancelar = document.getElementById('btn-cancelar')
        btn_cancelar.addEventListener('click', () => {
            formulario.innerHTML = ""
        })

        formulario.addEventListener('submit', (event) => {
            event.preventDefault()

            const payload = {
                id: odontologo.id,
                apellido: document.getElementById('new_apellido').value,
                nombre: document.getElementById('new_nombre').value,
                email: document.getElementById('new_email').value,
                matricula: document.getElementById('new_matricula').value,
            };

            const url = 'http://localhost:8080/dentists';
            const settings = {
                method: 'PUT',
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(payload)
            }

            fetch(url, settings)
                .then(response => response.json())
                .then(data => console.log(data))
                .catch(error => console.log(error))
            location.reload()
        })

    }
})

