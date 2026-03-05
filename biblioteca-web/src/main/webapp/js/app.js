/**
 * Biblioteca Digital — JavaScript
 * Solo utilidades mínimas: auto-dismiss de alertas y confirmaciones.
 */

document.addEventListener('DOMContentLoaded', () => {

    // Auto-cerrar alertas flash después de 4 segundos
    document.querySelectorAll('.alert').forEach(alert => {
        setTimeout(() => {
            alert.style.transition = 'opacity 0.5s';
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 500);
        }, 4000);
    });

    // Resaltar la fila de tabla al hacer hover (accesibilidad visual)
    document.querySelectorAll('.table tbody tr').forEach(row => {
        row.style.cursor = 'default';
    });

    // Validación simple de formularios antes del submit
    document.querySelectorAll('form.form-grid').forEach(form => {
        form.addEventListener('submit', e => {
            let valido = true;
            form.querySelectorAll('[required]').forEach(campo => {
                if (!campo.value.trim()) {
                    campo.style.borderColor = '#d9534f';
                    valido = false;
                } else {
                    campo.style.borderColor = '';
                }
            });
            if (!valido) {
                e.preventDefault();
                alert('Por favor, complete todos los campos obligatorios (*).');
            }
        });
    });

});
