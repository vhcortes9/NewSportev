
function validarNumeros(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true; //Backspace
    if (tecla == 109)
        return true; //menos
    if (tecla == 110)
        return true; //punto
    if (tecla == 189)
        return true; //guion
    if (e.ctrlkey && tecla == 86) {
        return true
    }
    ; //ctrl V
    if (e.ctrlkey && tecla == 67) {
        return true
    }
    ; //ctrl c
    if (e.ctrlkey && tecla == 88) {
        return true
    }
    ; //ctrl x
    if (e.ctrlkey && tecla == 105) {
        return true;
    } //numpad

    patron = /[0-9]/; //patron
    te = String.fromCharCode(tecla);
    return patron.test(te); //prueba

}
function validarLetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true; //Backspace
    if (tecla == 32)
        return true; //space
    if (e.ctrlkey && tecla == 86) {
        return true;
    } //ctrl V
    if (e.ctrlkey && tecla == 67) {
        return true;
    } //ctrl c
    if (e.ctrlkey && tecla == 88) {
        return true;
    } //ctrl x
    patron = /[a-zA-Z]/;

    te = String.fromCharCode(tecla);
    return patron.test(te);
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