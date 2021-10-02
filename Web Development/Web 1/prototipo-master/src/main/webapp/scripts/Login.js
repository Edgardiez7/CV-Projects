function alerta() {
    alert("Usuario no encontrado");
}

function loginCliente(){
    var form = document.forms.usuario;
    var formData = new FormData(form);
    var dni = formData.get('id-usuario');
    var pass = formData.get('password-usuario');
    if(dni === null || pass === null || dni === "" || pass === ""){
        alert("Rellene los campos DNI y contrasena");
        return false;
    }
    return true;
    
}

function loginGestor(){
     var form = document.forms.empresa;
     var formData = new FormData(form);
     var cif = formData.get('id-gestor');
     var pass = formData.get('password-gestor');
     if(cif === null || pass === null || cif === "" || pass === ""){
        alert("Rellene los campos CIF y contrasena");
        return false;
    }
    return true;
}