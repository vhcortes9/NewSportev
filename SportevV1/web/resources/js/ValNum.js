
function validarNumeros(e) {
    key=e.keyCode || e.which;
    teclado=String.fromCharCode(key);
    numero="0123456789-";
    especiales="8-9-37-38-46";
    teclado_especial=false;
    for (var i in especiales) {
        if (key==especiales[i]) {
            teclado_especial=true;
           
        }
    }
    if (numero.indexOf(teclado)==-1 && !teclado_especial) {
        return false;
    }

}


function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúñabcdefghijklmnñopqrstuvwxyzñx";
    especiales = [8, 37, 39, 46, 241];

    tecla_especial = false
    for(var i in especiales) {
        if(key == especiales[i]) {
            tecla_especial = true;
           
        }
    }

    if(letras.indexOf(tecla) == -1 && !tecla_especial)
        return false;
}


function limpia() {
    var val = document.getElementById("miInput").value;
    var tam = val.length;
    for(i = 0; i < tam; i++) {
        if(!isNaN(val[i]))
            document.getElementById("miInput").value = '';
    }
}
function validarEmail(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 109)
        return true; //menos
    if (tecla == 110)
        return true; //punto
    if (tecla == 189)
        return true; //guion
    if (tecla == 8)
        return true; //Backspace
    return !(e.keyCode==86 && e.ctrlKey) //ctrl V
    return !(e.keyCode==67 && e.ctrlKey) //ctrl c
    return !(e.keyCode==88 && e.ctrlKey) //ctrl x
    
    patron = /[a-zA-Z]-[0-9]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}
