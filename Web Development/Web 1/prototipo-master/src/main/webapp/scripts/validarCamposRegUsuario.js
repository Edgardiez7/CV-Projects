/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validarDni(dni) {
    var correcto = true;
    if (dni.length !== 9) {
        correcto = false;
    }
    for (i = 0; i < 8; i++) {
        char = dni.charAt(i);
        if (char < '0' || char > '9') {
            correcto = false;
        }
    }
    char = dni.charAt(8);
    if (!(char >= 'A' && char <= 'Z')) {
        correcto = false;
    }
    return correcto;
}

function validarContrasena(pass1, pass2) {
    var correcto = true;
    if (pass1 !== pass2) {
        alert("Las contrasenas no coinciden.");
        correcto = false;
    }
    if(pass1.length < 8) correcto = false;
    var contLetra = 0;
    var contNum = 0;
    for(i = 0; i < pass1.length; i++){
        if(pass1.charAt(i) >= 'a' && pass1.charAt(i) <= 'z'){
            contLetra ++;
        }
        if(pass1.charAt(i) >= '0' && pass1.charAt(i) <= '9'){
            contNum ++;
        }
    }
    if(contLetra < 1 || contNum < 1) correcto = false;
    return correcto;
}


function validarEmail(em1, em2) {
    correcto = true;
    if (em1 !== em2) {        
        correcto = false;
    }
    return correcto;
}

function validarRegUsuario() {
    var form = document.forms.nuevoRegistro;
    var formData = new FormData(form);
    var dni = formData.get('DNI');
    var pass1 = formData.get('Contrasena');
    var pass2 = formData.get('RepeContrasena');
    var em1 = formData.get('Mail');
    var em2 = formData.get('RepeMail');
    var correcto = true;

    correcto = validarDni(dni);
    if (!correcto) {
        alert("Formato DNI incorrecto. Introduzca 8 digitos + letra mayuscula.");
        return false;
    }

    correcto = validarContrasena(pass1, pass2);
    if (!correcto) {
        alert("La contrasena debe contener al menos 8 caracteres de los cuales al menos uno debe ser letra y al menos uno un numero.");
        return false;
    }

    correcto = validarEmail(em1, em2);
    if (!correcto){
        alert("Los emails introducidos no coinciden");
        return false;
    }
  
    return true;
}