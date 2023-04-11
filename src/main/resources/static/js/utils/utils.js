function validarCorreoGmail(correo) {
    // Expresión regular para validar un correo de Gmail
    const regex = /^[a-zA-Z0-9._%+-]+@gmail.com$/;
  
    // Validar el correo utilizando la expresión regular
    if (regex.test(correo)) {
      return true;
    } else {
      return false;
    }
  }
  function normalizarTexto(texto) {
    // Convertir el texto a minúsculas y eliminar todos los espacios
    const textoNormalizado = texto.toLowerCase().replace(/\s+/g, "");
  
    return textoNormalizado;
  }
  function validarFormatoFecha(fecha) {
    // Expresión regular para validar el formato de la fecha
    const regex = /^\d{4}-\d{2}-\d{2}$/;
  
    // Validar la fecha utilizando la expresión regular
    if (regex.test(fecha)) {
      // Parsear la fecha y verificar si es válida
      const parsedDate = new Date(fecha);
      if (isNaN(parsedDate.getTime())) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
  function validarHora(hora) {
    // Expresión regular para validar el formato de la hora
    const regex = /^([01]\d|2[0-3]):([0-5]\d)$/;
  
    // Validar la hora utilizando la expresión regular
    if (regex.test(hora)) {
      // Parsear la hora y verificar si es válida
      const [horas, minutos] = hora.split(":");
      if (horas <= 23 && minutos <= 59) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
  export { validarCorreoGmail, normalizarTexto, validarFormatoFecha,validarHora };
  