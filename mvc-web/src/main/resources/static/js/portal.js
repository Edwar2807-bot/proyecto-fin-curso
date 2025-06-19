$(document).ready(function() {
    const calculoModal = new bootstrap.Modal(document.getElementById('calculoModal'));

    $('#btn-matriz').on('click', function() {
        $('#calculoModalLabel').text('Resultado: Multiplicación de Matrices');
        $('#resultado-calculo').text('Calculando...');
        calculoModal.show();

        const requestData = {
            "matrixA": [[1, 2, 3], [4, 5, 6]],
            "matrixB": [[7, 8], [9, 10], [11, 12]]
        };

        $.ajax({
            url: '/app/cientifico/matriz',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function(response) {
                $('#resultado-calculo').text(JSON.stringify(response, null, 2));
            },
            error: function() {
                $('#resultado-calculo').text('Error: No se pudo conectar con el servicio de cálculo.');
            }
        });
    });

    $('#btn-integral').on('click', function() {
        $('#calculoModalLabel').text('Resultado: Integración Numérica');
        $('#resultado-calculo').text('Calculando...');
        calculoModal.show();

        const requestData = {
            "function": "x^2", "start": 0, "end": 10, "intervals": 1000000
        };

        $.ajax({
            url: '/app/cientifico/integracion',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function(response) {
                $('#resultado-calculo').text(JSON.stringify(response, null, 2));
            },
            error: function() {
                $('#resultado-calculo').text('Error: No se pudo conectar con el servicio de cálculo.');
            }
        });
    });
});