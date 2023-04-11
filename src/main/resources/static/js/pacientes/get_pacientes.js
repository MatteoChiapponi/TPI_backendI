window.addEventListener('load', function () {
  const formulario = document.querySelector('#form_actualizar_paciente');

  //con fetch invocamos a la API de peliculas con el método GET
  //nos devolverá un JSON con una colección de peliculas
  const url = 'http://localhost:8080/patients/all';
  const settings = {
    method: 'GET'
  }

  fetch(url, settings)
    .then(response => response.json())
    .then(data => {
      const dataPaciente = data;
      let dashboardPacientes = document.querySelector("#dashboard_pacientes");
      data.forEach(paciente => {
        dashboardPacientes.innerHTML += `
            <tr>
            <td scope="row" id="id">${paciente.id}</td>
            <td scope="row" id="nombre">${paciente.nombre}</td>
            <td scope="row" id="apellido">${paciente.apellido}</td>
            <td scope="row" id="dni">${paciente.dni}</td>
            <td scope="row" id="fechaIngreso">${paciente.fechaIngreso}</td>
            <td scope="row" id="email">${paciente.email}</td>
            <td scope="row"><button id=${paciente.id} class="btn btn-primary">Actualizar</button></td>
            <td scope="row"><button id=${paciente.id} class="btn btn-danger">Eliminar</button></td>
            </tr>`
      });
      const botonesEliminar = dashboardPacientes.querySelectorAll("button");
      botonesEliminar.forEach((boton, i) => {
        if (i % 2 !== 0) {
          boton.addEventListener("click", (e) => {
            const idboton = e.target.getAttribute("id");
            const settings = {
              method: 'DELETE'
            }
            fetch(`http://localhost:8080/patients/${idboton}`, settings)
              .then(() => {
                if (formulario.hasChildNodes()) {
                  this.location.reload()
              }
                this.location.reload()
              }).catch((err) => {
                console.log("no pudimos eliminar el paciente " + err);
              });
          })
        } else {
          boton.addEventListener("click", (e) => {
            if (formulario.hasChildNodes()) {
              console.log("ya tiene hijoss");
            }
            else {
              const idPaciente = parseInt(e.target.getAttribute("id"));
              const paciente = dataPaciente.find(paciente => paciente.id === idPaciente)
              actualizarPaciente(paciente)
            }
          })
        }
      });
    })
  function actualizarPaciente(paciente) {
    formulario.innerHTML += `
        <h3>Actualizar paciente</h3>
        <br>
        <div class="mb-3">
          <div class="row">
            <div class="col">
              <label for="new_nombre" class="form-label">Nombre</label>
              <input type="text" class="form-control" id="new_nombre" name="new_nombre" value=${paciente.nombre} required>
            </div>
            <div class="col">
              <label for="new_apellido" class="form-label">Apellido</label>
              <input type="text" class="form-control" id="new_apellido" name="new_apellido" value=${paciente.apellido} required>
            </div>
            <div class="col">
              <label for="new_email" class="form-label">Correo</label>
              <input type="email" class="form-control" id="new_email" name="new_email" value=${paciente.email} required>
            </div>
          </div>
          
          <br>
    
          <div class="row">
            <div class="col">
              <label for="new_dni" class="form-label">DNI</label>
              <input type="text" class="form-control" id="new_dni" name="new_dni" value='${paciente.dni}' required>
            </div>
            <div class="col">
              <label for="new_ingreso" class="form-label">Fecha de ingreso</label>
              <input type="date" class="form-control" id="new_ingreso" name="new_ingreso" value=${paciente.fechaIngreso} required>
            </div>
          </div> 
          
          <br>
          <br>
    
          <h4>Domicilio</h4>
          <br>
          <div class="row">
            <div class="col">
              <label for="new_calle" class="form-label">Calle</label>
              <input type="text" class="form-control" id="new_calle" name="new_calle" value='${paciente.domicilio.calle}' required>
            </div>
            <div class="col">
              <label for="new_numero" class="form-label">Número</label>
              <input type="text" class="form-control" id="new_numero" name="new_numero" value=${paciente.domicilio.numero} required>
            </div>
          </div>
    
          <br>
    
          <div class="row">
            <div class="col">
              <label for="new_localidad" class="form-label">Localidad</label>
              <input type="text" class="form-control" id="new_localidad" name="new_ocalidad" value='${paciente.domicilio.localidad}' required>
            </div>
            <div class="col">
              <label for="new_provincia" class="form-label">Provincia</label>
              <input type="text" class="form-control" id="new_provincia" name="new_provincia" value=${paciente.domicilio.provincia} required>
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
        id: paciente.id,
        apellido: document.getElementById('new_apellido').value,
        nombre: document.getElementById('new_nombre').value,
        dni: document.getElementById('new_dni').value,
        fechaIngreso: document.getElementById('new_ingreso').value,
        email: document.getElementById('new_email').value,
        domicilio: {
          id: paciente.domicilio.id,
          calle: document.getElementById('new_calle').value,
          numero: document.getElementById('new_numero').value,
          localidad: document.getElementById('new_localidad').value,
          provincia: document.getElementById('new_provincia').value
        }
      };

      const url = 'http://localhost:8080/patients';
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