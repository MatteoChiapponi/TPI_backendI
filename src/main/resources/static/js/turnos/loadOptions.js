export function cargarPacientesYOdontologos(selectOdontologos,selectPacientes,idPaciente, idOdonotogo) {
  fetch("/dentists/all")
    .then(response => response.json())
    .then(data => {
        data.forEach(odontologo => {
            const { id, nombre, apellido } = odontologo;
        if (idOdonotogo !== id) {
            selectOdontologos.innerHTML += `<option value=${id}>${apellido}, ${nombre}</option>`;
        }
        });
    });

  fetch("/patients/all")
    .then(response => response.json())
    .then(data => {
        data.forEach(paciente => {
        const { id, nombre, apellido } = paciente;
        if (idPaciente !== id) {
            selectPacientes.innerHTML += `<option value=${id}>${apellido}, ${nombre}</option>`;
        }
      });
    });
}