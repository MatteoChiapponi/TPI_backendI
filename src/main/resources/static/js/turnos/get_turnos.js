import { cargarPacientesYOdontologos } from './loadOptions.js';
window.addEventListener('load', function () {
    const formulario = this.document.querySelector("#form_actualizar_turno");
    const dashboard = document.getElementById("container-dashboard");

    let id_odontologo_seleccionado;
    let id_paciente_seleccionado;
    fetch('/appointments')
        .then(response => response.json())
        .then(data => {
            data.forEach(turno => {
                const { id, fechaHoraTurno, odontologo, paciente } = turno

                dashboard.innerHTML += `
                <tr id="turno/${id}">
                    <th scope="row" id="row-fecha">${fechaHoraTurno}</th>
                    <td id="row-paciente">${paciente.nombre} ${paciente.apellido}</td>
                    <td id="row-odontologo">${odontologo.nombre} ${odontologo.apellido}</td>
                    <td><button type="button" class="btn btn-primary" name="actualizar" id="${id}">Actualizar</button></td>
                    <td><button type="button" class="btn btn-danger" name="eliminar" id="${id}">Eliminar</button></td>
                </tr>`
            });

            const btns = dashboard.querySelectorAll("button");
            btns.forEach((boton, i) => {
                if (i % 2 !== 0) {
                    boton.addEventListener("click", (e) => {
                        const idboton = e.target.getAttribute("id");
                        const settings = {
                            method: 'DELETE'
                        }
                        fetch(`/appointments/${idboton}`, settings)
                            .then(() => {
                                if (formulario.hasChildNodes())
                                    this.location.reload();
                                else
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
                            const idTurno = parseInt(e.target.getAttribute("id"));
                            const turno = data.find(turno => turno.id === idTurno);
                            actualizarTurno(turno)
                        }
                    })
                }
            })
        });
    function actualizarTurno(turno) {
        id_odontologo_seleccionado = turno.odontologo.id;
        id_paciente_seleccionado = turno.paciente.id;
        formulario.innerHTML += `
        <h3>Actualizar Turno</h3>
        <br>
        <div class="mb-3">
          <div class="row">
             <div class="col">
               <label for="new_fecha" class="form-label">Fecha y Hora del Turno</label>
               <input type="text" class="form-control" id="new_fecha" value='${turno.fechaHoraTurno}' required>
             </div>
           </div>
           <br>
           <div class="row">
            <div class="col">
                <label for="new_odonotologo" class="form-label">Odontologo</label>
                <select class="form-control" id="formControlSelect_odontologo">
                    <option class="option_default" value=${turno.odontologo.id}>'${turno.odontologo.nombre} ${turno.odontologo.apellido}'</option>
                </select>
            </div>
           </div>
            <br>
            <div class="row">
            <div class="col">
                <label for="new_paciente" class="form-label">Paciente</label>    
                <select class="form-control" id="formControlSelect_paciente">
                    <option class="option_default" value=${turno.paciente.id}>'${turno.paciente.nombre} ${turno.paciente.apellido}'</option>
                </select>
            </div>
           </div>
          <br>
          <br>
          <button class="btn btn-primary" type="submit" id="btn-actualizar">Actualizar</button>
          <button class="btn btn-secondary" type="button" id="btn-cancelar">Cancelar</button>
        `

        const opcionesOdontologos = document.getElementById("formControlSelect_odontologo");
        const opcionesPacientes = document.getElementById("formControlSelect_paciente");

        cargarPacientesYOdontologos(opcionesOdontologos, opcionesPacientes, turno.paciente.id, turno.odontologo.id)

        const btn_cancelar = document.getElementById('btn-cancelar')
        btn_cancelar.addEventListener('click', () => {
            formulario.innerHTML = ""
        })

        opcionesOdontologos.addEventListener("change", event => {
            id_odontologo_seleccionado = event.target.value;
        });

        opcionesPacientes.addEventListener("change", event => {
            id_paciente_seleccionado = event.target.value
        });
        formulario.addEventListener('submit', event => {
            event.preventDefault()

            const payload = {
                id: turno.id,
                fechaHoraTurno: document.getElementById('new_fecha').value,
                odontologo: {
                    id: id_odontologo_seleccionado
                },
                paciente: {
                    id: id_paciente_seleccionado
                }
            };
            const url = '/appointments/update';
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
