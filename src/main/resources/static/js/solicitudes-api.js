/* La página HTML la entrega Thymeleaf; las filas se consultan al API REST en JSON. */
(() => {
    const formulario = document.getElementById("filtro-solicitudes");
    const cuerpo = document.getElementById("filas-solicitudes");
    if (!formulario || !cuerpo) return;

    async function cargarSolicitudes(evento) {
        if (evento) evento.preventDefault();
        const datos = new FormData(formulario);
        const consulta = new URLSearchParams();
        const texto = (datos.get("texto") || "").toString().trim();
        const estado = (datos.get("estado") || "").toString().trim();
        if (texto) consulta.set("texto", texto);
        if (estado) consulta.set("estado", estado);

        cuerpo.replaceChildren(filaMensaje("Consultando el API..."));
        try {
            const respuesta = await fetch(`${formulario.dataset.apiUrl}?${consulta.toString()}`, {
                headers: { Accept: "application/json" },
                credentials: "same-origin"
            });
            if (!respuesta.ok) throw new Error(respuesta.status === 401 || respuesta.status === 403
                ? "Tu sesión no permite consultar estas solicitudes. Vuelve a iniciar sesión."
                : "No se pudo consultar el API de solicitudes.");
            const solicitudes = await respuesta.json();
            cuerpo.replaceChildren();
            if (solicitudes.length === 0) {
                cuerpo.append(filaMensaje("No hay solicitudes que coincidan con estos filtros."));
                return;
            }
            solicitudes.forEach(solicitud => cuerpo.append(crearFila(solicitud)));
        } catch (error) {
            cuerpo.replaceChildren(filaMensaje(error.message, "text-danger"));
        }
    }

    function filaMensaje(mensaje, clase = "text-muted") {
        const fila = document.createElement("tr");
        const celda = document.createElement("td");
        celda.colSpan = 8;
        celda.className = `text-center py-5 ${clase}`;
        celda.textContent = mensaje;
        fila.append(celda);
        return fila;
    }

    function crearFila(solicitud) {
        const fila = document.createElement("tr");
        agregarCelda(fila, solicitud.codigo || "—", "fw-semibold");

        const cliente = document.createElement("td");
        const nombre = document.createElement("span");
        nombre.textContent = solicitud.cliente || "Cliente sin vincular";
        const dni = document.createElement("small");
        dni.className = "text-muted d-block";
        dni.textContent = solicitud.dni || "";
        cliente.append(nombre, dni);
        fila.append(cliente);

        agregarCelda(fila, solicitud.tipoCredito || "—");
        const monto = Number(solicitud.monto);
        agregarCelda(fila, Number.isFinite(monto) ? `S/ ${monto.toLocaleString("es-PE", { minimumFractionDigits: 2, maximumFractionDigits: 2 })}` : "—");
        agregarCelda(fila, solicitud.plazoMeses ? `${solicitud.plazoMeses} meses` : "—");
        agregarCelda(fila, solicitud.fecha || "—");

        const estadoCelda = document.createElement("td");
        const etiqueta = document.createElement("span");
        etiqueta.className = "badge text-bg-secondary";
        etiqueta.textContent = solicitud.estado || "Pendiente";
        estadoCelda.append(etiqueta);
        fila.append(estadoCelda);

        const acciones = document.createElement("td");
        const ver = document.createElement("a");
        ver.className = "btn btn-sm btn-outline-primary";
        ver.href = `/solicitudes/detalle?id=${encodeURIComponent(solicitud.id)}`;
        ver.textContent = "Ver";
        acciones.append(ver);
        fila.append(acciones);
        return fila;
    }

    function agregarCelda(fila, texto, clase = "") {
        const celda = document.createElement("td");
        celda.className = clase;
        celda.textContent = texto;
        fila.append(celda);
    }

    formulario.addEventListener("submit", cargarSolicitudes);
    cargarSolicitudes();
})();
