import { cargarPacientesYOdontologos } from './loadOptions.js';
window.addEventListener("load", () => {
  let id_odontologo_seleccionado;

  let id_paciente_seleccionado;

  const opcionesOdontologos = document.getElementById("formControlSelect_odontologo");
  const opcionesPacientes = document.getElementById("formControlSelect_paciente");

  cargarPacientesYOdontologos(opcionesOdontologos,opcionesPacientes,"undefined","hola")

  opcionesOdontologos.addEventListener("change", event => {
    id_odontologo_seleccionado = event.target.value;
  });

  opcionesPacientes.addEventListener("change", event => {
    id_paciente_seleccionado = event.target.value
  });

  const formulario = document.getElementById("registrar_turno");

  formulario.addEventListener("submit", event => {
    event.preventDefault();

    const fecha_data = document.getElementById("fecha").value;
    const hora_data = document.getElementById("hora").value;

    const payload = {
      fechaHoraTurno: `${fecha_data} ${hora_data}`,
      paciente:{id: id_paciente_seleccionado},
      odontologo:{id: id_odontologo_seleccionado},
    };

    const settings = {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(payload),
    };

    fetch("/appointments", settings)
      .then((response) => response.json())
      .then(data => {
        let successAlert =
          '<div class="alert alert-success alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong></strong> Turno registrado </div>";

        document.getElementById("response").innerHTML = successAlert;
        document.getElementById("response").style.display = "block";
        formulario.reset()
      })
      .catch((error) => {
        console.log(error.message);
        const errorAlert =
          '<div class="alert alert-danger alert-dismissible">' +
          '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
          "<strong> Error intente nuevamente</strong> </div>";

        document.getElementById("response").innerHTML = errorAlert;
        document.getElementById("response").style.display = "block";
        formulario.reset()
      });
  });
});
