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
const default_iframe = "https://www.youtube.com/embed/I5_T547tHf0"
$('#url').change(function () {
    let url = $('input[id=url]').val()
    if (url === "" || url === null || url === undefined) {
        $('#video-iframe').attr('src', default_iframe)
        return
    }
    let index = url.indexOf('.be/') + 4
    if(index == -1) {
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
});