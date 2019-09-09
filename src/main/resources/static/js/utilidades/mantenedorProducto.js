$(document).ready(function () {
    //Eliminar con modal con datos de producto
    $('[name="btnEliminar"]').click(function () {
        var array = $(this).val().split(".");
        var id = array[0];
        var nombre = array[1];
        $('#lblNombre').text(nombre);
        $('#idProdu').val(id);
    });
    //Estilos para en input file con cambio de texto
    $("input[type=file]").change(function () {
        var fieldVal = $(this).val();

        // Change the node's value by removing the fake path (Chrome)
        fieldVal = fieldVal.replace("C:\\fakepath\\", "");

        if (fieldVal != undefined || fieldVal != "") {
            $(this).next(".custom-file-label").attr('data-content', fieldVal);
            $(this).next(".custom-file-label").text(fieldVal);
        }
    });
    //Validacion de nombre con REGEXP
    $('#txtNombre').keypress(function (e) {
        var regex = new RegExp("^[a-zA-ZÁ-ÿ \s]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        else
        {
            e.preventDefault();
            return false;
        }
    });
});

(function() {
    'use strict';
    window.addEventListener('load', function() {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
// Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();