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

