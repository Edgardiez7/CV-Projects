/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarCIF(cif) {
    var correcto = true;
    if (cif.length !== 9) {
        correcto = false;
    }
    var char = cif.charAt(0);
    if (!(char >= 'A' && char <= 'Z')) {
        correcto = false;
    }
    for (i = 1; i < 9; i++) {
        char = cif.charAt(i);
        if (char < '0' || char > '9') {
            correcto = false;
        }
    }
    return correcto;
}

function validarCodCuenta(cod) {//2letras + 22 numeros
    var correcto = true;
    if (cod.length !== 24) {
        correcto = false;
    }
    char1 = cod.charAt(0).toLowerCase();
    char2 = cod.charAt(1).toLowerCase();
    if (char1 < 'a' || char1 > 'z') {
        correcto = false;
    }
    if (char2 < 'a' || char2 > 'z') {
        correcto = false;
    }
    for (i = 2; i < 24; i++) {
        char = cod.charAt(i);
        if (char < '0' || char > '9') {
            correcto = false;
        }
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

function validarRegEmpresa() {
    var form = document.forms.nuevoRegistro;
    var formData = new FormData(form);
    var cif = formData.get('CIF');
    var codCuenta = formData.get('Cotizacion');
    var pass1 = formData.get('Contrasena');
    var pass2 = formData.get('RepeContrasena');
    var em1 = formData.get('Mail');
    var em2 = formData.get('RepeMail');
    var correcto = true;

    correcto = validarCIF(cif);
    if (!correcto) {
        if (!correcto)
            alert("Formato CIF incorrecto. Introduzca letra mayuscula + 8 digitos.");
        return false;
    }
   
    correcto = validarCodCuenta(codCuenta);
    if (!correcto) {
        if (!correcto)
            alert("Formato Codigo Cuenta IBAN incorrecto. 24 digitos: 2 letras + 22 numeros");
        return false;
    }

    correcto = validarContrasena(pass1, pass2);
    if (!correcto) {
        alert("La contrasena debe contener al menos 8 caracteres de los cuales el menos uno debe ser letra y al menos uno un numero");
        return false;
    }
    correcto = validarEmail(em1, em2);
    if (!correcto) {
        alert("Los emails introducidos no coinciden");
        return false;
    }

    return true;
}

