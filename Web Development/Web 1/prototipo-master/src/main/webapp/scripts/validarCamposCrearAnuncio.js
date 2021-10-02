/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarUbicacion(ubicacion) {
    var correcto = true;
    if(ubicacion.length <= 1) correcto = false;
    var contLetra = 0;
    var contNum = 0;
    for(i = 0; i < ubicacion.length; i++){
        if(ubicacion.charAt(i) >= 'a' && ubicacion.charAt(i) <= 'z'){
            contLetra ++;
        }
        if(ubicacion.charAt(i) >= '0' && ubicacion.charAt(i) <= '9'){
            contNum ++;
        }
    }
    if(contNum > 0) correcto = false;
    return correcto;
}

function validarFechas(fechaInicio, fechaFin){
    if(fechaInicio > fechaFin){
        return false;
    }
    return true;
}


function validarCrearAnuncio() {
    var form = document.forms.infoAnuncio;
    var formData = new FormData(form);
    var ubicacion = document.getElementById('ubicacion').value;
    var fechaInicio = document.getElementById('fechaIni').value;
    var fechaFin = document.getElementById('fechaFin').value;
    var correcto = true;
    var correctoFecha = true;
    correcto = validarUbicacion(ubicacion);
    correctoFecha = validarFechas(fechaInicio, fechaFin);
    if (!correcto) {
            alert("Formato de ubicacion incorrecto. Introduzca unicamente letras");
        return false;
    }
    
    if (!correctoFecha) {
            alert("La fecha de inicio debe ser anterior a la fecha de fin");
        return false;
    }
    return true;
}
