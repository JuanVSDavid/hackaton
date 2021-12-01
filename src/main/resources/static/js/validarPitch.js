(() => {
    'use strict'
    const forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms).forEach((form) => {
        form.addEventListener('submit', (event) => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
})()
function deletePitch(id) {
    console.log(id)
    Swal.fire({
        title: '¿Estas seguro?',
        text: "¡No podras revertir este cambio!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#261B26',
        cancelButtonColor: '#f93154',
        confirmButtonText: 'Confirmar'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/proyecto/delete'
            })
            Swal.fire(
                '¡Eliminado!',
                'Los datos de la venta se han eleminado.',
                'success'
            ).then(() => {
                location.href = "/"
            })
        }
    })
}
$('#limite').text('250 carácteres restantes')
$('#descripcion').keydown(function () {
    let max = 250
    let len = $(this).val().length
    if (len > max) {
        $('#limite').text('Has llegado al límite')
        $('#limite').addClass('text-danger')
        $('#descripcion').addClass('is-invalid')
    }
    else {
        let ch = max - len
        $('#limite').text(ch + ' carácteres restantes')
        $('#limite').removeClass('text-danger')
        $('#descripcion').removeClass('is-invalid')
    }
})
const iframe = "https://www.youtube.com/embed/"
const default_iframe = "https://www.youtube.com/embed/oixPPZf8UE8"
$('#url').change(function () {
    let url = $('input[id=url]').val()
    if (url === "" || url === null || url === undefined) {
        $('#video-iframe').attr('src', default_iframe)
        return
    }
    let index = url.indexOf('.be/') + 4
    if (index == -1) {
        let result = iframe + url
        $('#video-iframe').attr('src', result)
        return
    }
    let result = iframe + url.slice(index)
    $('#video-iframe').attr('src', result)
})
$(function () {
    $('#limpiar').on('click', function () {
        $('#titulo').val(null)
        $('#valor').val(null)
        $('#url').val(null)
        $('#descripcion').val(null)
        $('#file').val(null)
        $('#fecha_finally').val(null)
        $('#video-iframe').attr('src', default_iframe)
    })
})