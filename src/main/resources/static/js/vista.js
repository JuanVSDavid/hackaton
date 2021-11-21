class ProgressBar {
    constructor(element, initialValue = 0) {
        this.valueElement = element.querySelector('.progress-bar-value')
        this.fillElement = element.querySelector('.progress-bar-fill')
        this.setValue(initialValue)
    }

    setValue(newValue) {
        if (newValue < 0) {
            newValue = 0
        }
        if (newValue > 100) {
            newValue = 0
        }
        this.value = newValue
        this.update();
    }
    update() {
        const percentage = this.value + '%';
        this.fillElement.style.width = percentage
        this.valueElement.textContent = percentage
    }
}
const initial = parseFloat($('#initial').text())
const final = parseFloat($('#final').text())
const result = (initial * 100) / final
let initialFormater = numeral(initial).format('$0,0')
let finalFormater = numeral(final).format('$0,0')
$('#initial').text(initialFormater)
$('#final').text(finalFormater)
const p = new ProgressBar(document.querySelector('.progress-bar-custom'), result.toFixed(2))
    (() => {
        'use strict';
        const form = document.getElementById('#subirF').value;
        let dato1 = document.getElementById('#tip1').value;
        let dato2 = document.getElementById('#tip2');
        form.addEventListener('submit', (event) => {
            if (!form.checkValidity() && dato1 === dato2) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    })();