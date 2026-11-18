/* Permite agregar documentos en varias selecciones sin reemplazar los anteriores. */
document.querySelectorAll("[data-file-queue]").forEach((input) => {
    const form = input.closest("form");
    const list = form?.querySelector("[data-file-list]");
    if (!list) return;
    const error = form.querySelector("[data-file-error]");
    const maximoPorArchivo = 5 * 1024 * 1024;
    const maximoTotal = 20 * 1024 * 1024;

    let archivosSeleccionados = [];

    input.addEventListener("change", () => {
        const nuevos = Array.from(input.files || []);
        const firma = (archivo) => `${archivo.name}|${archivo.size}|${archivo.lastModified}`;
        const existentes = new Set(archivosSeleccionados.map(firma));
        const agregados = nuevos.filter((archivo) => !existentes.has(firma(archivo)));
        const archivoGrande = agregados.find((archivo) => archivo.size > maximoPorArchivo);
        const total = archivosSeleccionados.reduce((suma, archivo) => suma + archivo.size, 0)
            + agregados.reduce((suma, archivo) => suma + archivo.size, 0);
        if (archivoGrande || total > maximoTotal) {
            if (error) error.textContent = archivoGrande
                ? `${archivoGrande.name} supera el límite de 5 MB.`
                : "El tamaño total de los documentos no puede superar 20 MB.";
            const restaurar = new DataTransfer();
            archivosSeleccionados.forEach((archivo) => restaurar.items.add(archivo));
            input.files = restaurar.files;
            return;
        }
        if (error) error.textContent = "";
        archivosSeleccionados.push(...agregados);

        const transferencia = new DataTransfer();
        archivosSeleccionados.forEach((archivo) => transferencia.items.add(archivo));
        input.files = transferencia.files;
        dibujarLista();
    });

    function dibujarLista() {
        list.replaceChildren();
        if (archivosSeleccionados.length === 0) {
            const nota = document.createElement("li");
            nota.className = "list-group-item small text-muted px-0";
            nota.textContent = "Aún no has agregado documentos.";
            list.append(nota);
            return;
        }

        archivosSeleccionados.forEach((archivo, indice) => {
            const fila = document.createElement("li");
            fila.className = "list-group-item d-flex justify-content-between align-items-center gap-3 px-0";
            const texto = document.createElement("span");
            texto.className = "small text-break";
            texto.textContent = `${archivo.name} · ${(archivo.size / 1024 / 1024).toFixed(2)} MB`;
            const quitar = document.createElement("button");
            quitar.type = "button";
            quitar.className = "btn btn-sm btn-outline-secondary flex-shrink-0";
            quitar.textContent = "Quitar";
            quitar.setAttribute("aria-label", `Quitar ${archivo.name}`);
            quitar.addEventListener("click", () => {
                archivosSeleccionados.splice(indice, 1);
                const transferencia = new DataTransfer();
                archivosSeleccionados.forEach((archivoRestante) => transferencia.items.add(archivoRestante));
                input.files = transferencia.files;
                dibujarLista();
            });
            fila.append(texto, quitar);
            list.append(fila);
        });
    }

    dibujarLista();
});
