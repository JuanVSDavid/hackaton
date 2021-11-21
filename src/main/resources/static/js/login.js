const sign_in_btn = document.querySelector('#sign-in-btn');
const sign_up_btn = document.querySelector('#sign-up-btn');
const container = document.querySelector('.container-custom');

sign_up_btn.addEventListener('click', () => {
    container.classList.add('sign-up-mode');
});

sign_in_btn.addEventListener('click', () => {
    container.classList.remove('sign-up-mode');
});

$(document).ready(() => {
    $('.color-primary-usuario').hide();
    $('.color-primary-pass').hide();
    $('.color-primary-pass-2').hide();

    $('#login').submit(function(e) {
        let user = $('#login_usuario').val().trim();
        let password = $('#login_password').val();
        if(user === '' || user === null) { 
            $('.color-primary-usuario').show()
            e.preventDefault();
            e.stopPropagation();
            return
        }
        if(password === '' || password === null) { 
            $('.color-primary-pass').show()
            e.preventDefault();
            e.stopPropagation();
            return
        }
    })
    $('#register').submit(function(e) {
        let pass_i = $('#pass_i').val()
        let pass_f = $('#pass_f').val()
        if(pass_i !== pass_f){
            $('.color-primary-pass-2').show()
            e.preventDefault();
            e.stopPropagation();
            return
        }
    })
})