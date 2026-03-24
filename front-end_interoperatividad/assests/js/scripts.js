/* ════════════════════════════════════════════════════════════
   CONFIG
════════════════════════════════════════════════════════════ */
const API = "http://localhost:9090";

/* ════════════════════════════════════════════════════════════
   STATE
════════════════════════════════════════════════════════════ */
let jwtToken = sessionStorage.getItem("jwt") || null;
let userRole = sessionStorage.getItem("rol") || null;
let userName = sessionStorage.getItem("usr") || null;

/* ════════════════════════════════════════════════════════════
   NAVIGATION
════════════════════════════════════════════════════════════ */
function goTo(section) {
  document
    .querySelectorAll(".page-section")
    .forEach((s) => s.classList.remove("active"));
  const el = document.getElementById("sec" + capitalize(section));
  if (el) el.classList.add("active");
  renderNav();

  if (section === "productos") loadProductos();
  if (section === "usuarios") loadUsuarios();
}

function capitalize(str) {
  return str.charAt(0).toUpperCase() + str.slice(1);
}

/* ════════════════════════════════════════════════════════════
   RENDER NAV
════════════════════════════════════════════════════════════ */
function renderNav() {
  const nav = document.getElementById("navLinks");
  if (!jwtToken) {
    nav.innerHTML = `
      <button class="btn-nav-login" onclick="goTo('login')">
        <i class="bi bi-box-arrow-in-right me-1"></i>Iniciar sesión
      </button>`;
    return;
  }

  const isAdmin = userRole === "ROLE_ADMIN";
  const current = getActivePage();

  let links = `
    <button class="nav-link-custom ${current === "secProductos" ? "active" : ""}"
            onclick="goTo('productos')">
      <i class="bi bi-box-seam me-1"></i>Productos
    </button>`;

  if (isAdmin) {
    links += `
      <button class="nav-link-custom ${current === "secUsuarios" ? "active" : ""}"
              onclick="goTo('usuarios')">
        <i class="bi bi-people me-1"></i>Usuarios
      </button>`;
  }

  links += `
    <span style="color:var(--gray-400); font-size:.85rem; padding:0 .3rem;">
      <i class="bi bi-person-circle me-1"></i>${userName || "usuario"}
    </span>
    <button class="nav-link-custom logout" onclick="doLogout()">
      <i class="bi bi-box-arrow-right me-1"></i>Logout
    </button>`;

  nav.innerHTML = links;
}

function getActivePage() {
  const sec = document.querySelector(".page-section.active");
  return sec ? sec.id : "";
}

/* ════════════════════════════════════════════════════════════
   AUTH — LOGIN
════════════════════════════════════════════════════════════ */
async function doLogin() {
  const username = document.getElementById("inpUser").value.trim();
  const password = document.getElementById("inpPass").value;
  const errBox = document.getElementById("loginErr");
  const errMsg = document.getElementById("loginErrMsg");
  const spinner = document.getElementById("loginSpinner");
  const btnTxt = document.getElementById("loginBtnTxt");
  const btn = document.getElementById("btnLogin");

  errBox.style.display = "none";

  if (!username || !password) {
    errMsg.textContent = "Completa usuario y contraseña.";
    errBox.style.display = "flex";
    return;
  }

  // Loading state
  btnTxt.style.display = "none";
  spinner.style.display = "block";
  btn.disabled = true;

  try {
    const res = await fetch(`${API}/api/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    if (!res.ok) throw new Error("Credenciales incorrectas.");

    const data = await res.json();

    // Adaptá estas claves a tu respuesta real de Spring Boot
    const token = data.token || data.jwt || data.accessToken;
    const role = data.role?.[0] || data.roles?.[0] || data.rol || "ROLE_USER";
    const uname = data.username || data.nombre || username;

    if (!token) throw new Error("Token no recibido.");

    // Persist
    jwtToken = token;
    userRole = role;
    userName = uname;
    sessionStorage.setItem("jwt", token);
    sessionStorage.setItem("rol", role);
    sessionStorage.setItem("usr", uname);

    showToast(`¡Bienvenido, ${uname}!`, "success");
    goTo("productos");
  } catch (err) {
    errMsg.textContent = err.message || "Error al conectar con la API.";
    errBox.style.display = "flex";
  } finally {
    btnTxt.style.display = "inline";
    spinner.style.display = "none";
    btn.disabled = false;
  }
}

/* Enter key en login */
["inpUser", "inpPass"].forEach((id) => {
  document.getElementById(id).addEventListener("keydown", (e) => {
    if (e.key === "Enter") doLogin();
  });
});

/* ════════════════════════════════════════════════════════════
   AUTH — LOGOUT
════════════════════════════════════════════════════════════ */
function doLogout() {
  jwtToken = null;
  userRole = null;
  userName = null;
  sessionStorage.clear();
  showToast("Sesión cerrada.", "success");
  goTo("welcome");
}

/* ════════════════════════════════════════════════════════════
   HELPERS — FETCH CON JWT
   async function apiFetch(url, options = {}) {
    const headers = {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwtToken}`,
        ...(options.headers || {}),
    };
    const res = await fetch(`${API}${url}`, { ...options, headers });
    const token = sessionStorage.getItem("jwt");
    if (res.status === 401 || res.status === 403) {
        doLogout();
        throw new Error("Sesión expirada. Por favor ingresa de nuevo.");
    }
    return res;
}
════════════════════════════════════════════════════════════ */
async function apiFetch(url, options = {}) {
  const token = sessionStorage.getItem("jwt");
  const headers = {
    ...(options.headers || {}),
  };

  // Solo agregar Content-Type si hay body
  if (options.body) {
    headers["Content-Type"] = "application/json";
  }

  // Agregar Authorization solo si hay token
  if (token) {
    headers["Authorization"] = `Bearer ${token}`;
  }

  const res = await fetch(`${API}${url}`, {
    ...options,
    headers,
  });

  // Manejo de sesión
  if (res.status === 401 || res.status === 403) {
    doLogout();
    throw new Error("Sesión expirada. Por favor ingresa de nuevo.");
  }

  return res;
}

/* ════════════════════════════════════════════════════════════
   PRODUCTOS — CRUD
════════════════════════════════════════════════════════════ */
let productos = [];

async function loadProductos() {
  const tbody = document.getElementById("tbodyProductos");
  tbody.innerHTML = `<tr class="loading-row"><td colspan="5">
    <i class="bi bi-arrow-repeat" style="font-size:1.4rem;color:var(--blue-light);
       animation:spin .8s linear infinite;display:block;margin-bottom:.5rem;"></i>
    Cargando productos...</td></tr>`;
  try {
    const res = await apiFetch("/api/productos");
    productos = await res.json();
    renderProductos();
  } catch (err) {
    tbody.innerHTML = `<tr><td colspan="5" class="empty-state">
      <i class="bi bi-wifi-off"></i><p>${err.message}</p></td></tr>`;
  }
}

function renderProductos() {
  const tbody = document.getElementById("tbodyProductos");
  if (!productos.length) {
    tbody.innerHTML = `<tr><td colspan="5">
      <div class="empty-state">
        <i class="bi bi-box"></i>
        <p>No hay productos registrados.</p>
      </div></td></tr>`;
    return;
  }
  tbody.innerHTML = productos
    .map(
      (p, i) => `
    <tr>
      <td><span style="color:var(--gray-400);font-size:.82rem;font-weight:600;">${i + 1}</span></td>
      <td><strong>${esc(p.nombre)}</strong></td>
      <td><span style="color:var(--blue-mid);font-weight:700;">$${Number(p.precio).toFixed(2)}</span></td>
      <td>
        <span style="background:${p.stock > 0 ? "#f0fdf4" : "#fff0f0"};
              color:${p.stock > 0 ? "#16a34a" : "#e11d48"};
              padding:.2rem .65rem;border-radius:20px;font-size:.8rem;font-weight:700;">
          ${p.stock}
        </span>
      </td>
      <td>
        <button class="btn-action btn-edit" onclick="editProd(${p.id})">
          <i class="bi bi-pencil"></i> Editar
        </button>
        <button class="btn-action btn-del ms-1" onclick="confirmDelProd(${p.id}, '${esc(p.nombre)}')">
          <i class="bi bi-trash3"></i> Eliminar
        </button>
      </td>
    </tr>`,
    )
    .join("");
}

function openProdModal(prod = null) {
  document.getElementById("prodId").value = prod ? prod.id : "";
  document.getElementById("prodNombre").value = prod ? prod.nombre : "";
  document.getElementById("prodPrecio").value = prod ? prod.precio : "";
  document.getElementById("prodStock").value = prod ? prod.stock : "";
  document.getElementById("modalProdTitle").textContent = prod
    ? "Editar Producto"
    : "Nuevo Producto";
  openModal("modalProducto");
}

function editProd(id) {
  const prod = productos.find((p) => p.id === id);
  if (prod) openProdModal(prod);
}

async function saveProd() {
  const id = document.getElementById("prodId").value;
  const nombre = document.getElementById("prodNombre").value.trim();
  const precio = parseFloat(document.getElementById("prodPrecio").value);
  const stock = parseInt(document.getElementById("prodStock").value, 10);

  if (!nombre || isNaN(precio) || isNaN(stock)) {
    showToast("Completa todos los campos.", "error");
    return;
  }

  const body = { nombre, precio, stock };
  const method = id ? "PUT" : "POST";
  const url = id ? `/api/productos/${id}` : "/api/productos";

  try {
    const res = await apiFetch(url, {
      method,
      body: JSON.stringify(body),
    });
    if (!res.ok) throw new Error("Error al guardar.");
    closeModal("modalProducto");
    showToast(id ? "Producto actualizado." : "Producto creado.", "success");
    loadProductos();
  } catch (err) {
    showToast(err.message, "error");
  }
}

function confirmDelProd(id, nombre) {
  document.getElementById("delMsg").textContent =
    `¿Eliminar el producto "${nombre}"? Esta acción no se puede deshacer.`;
  document.getElementById("btnDelConfirm").onclick = () => deleteProd(id);
  openModal("modalDelete");
}

async function deleteProd(id) {
  try {
    const res = await apiFetch(`/api/productos/${id}`, {
      method: "DELETE",
    });
    if (!res.ok) throw new Error("Error al eliminar.");
    closeModal("modalDelete");
    showToast("Producto eliminado.", "success");
    loadProductos();
  } catch (err) {
    showToast(err.message, "error");
  }
}

/* ════════════════════════════════════════════════════════════
   USUARIOS — CRUD
════════════════════════════════════════════════════════════ */
let usuarios = [];

async function loadUsuarios() {
  const tbody = document.getElementById("tbodyUsuarios");
  tbody.innerHTML = `<tr class="loading-row"><td colspan="5">
    <i class="bi bi-arrow-repeat" style="font-size:1.4rem;color:var(--blue-light);
       animation:spin .8s linear infinite;display:block;margin-bottom:.5rem;"></i>
    Cargando usuarios...</td></tr>`;
  try {
    const res = await apiFetch("/api/usuarios");
    usuarios = await res.json();
    renderUsuarios();
  } catch (err) {
    tbody.innerHTML = `<tr><td colspan="5" class="empty-state">
      <i class="bi bi-wifi-off"></i><p>${err.message}</p></td></tr>`;
  }
}

function renderUsuarios() {
  const tbody = document.getElementById("tbodyUsuarios");
  if (!usuarios.length) {
    tbody.innerHTML = `<tr><td colspan="5">
      <div class="empty-state">
        <i class="bi bi-people"></i>
        <p>No hay usuarios registrados.</p>
      </div></td></tr>`;
    return;
  }
  console.log(usuarios);
  tbody.innerHTML = usuarios
    .map(
      (u, i) => `
    <tr>
      <td><span style="color:var(--gray-400);font-size:.82rem;font-weight:600;">${i + 1}</span></td>
      <td><strong>${esc(u.username)}</strong></td>
      <td style="color:var(--gray-600)">${esc(u.email)}</td>
      <td>
        <span class="badge-role ${u.role === "ADMIN" ? "badge-admin" : "badge-user"}">
          ${u.role === "ADMIN" ? "ADMIN" : "USER"}
        </span>
      </td>
      <td>
        <button class="btn-action btn-edit" onclick="editUser(${u.id})">
          <i class="bi bi-pencil"></i> Editar
        </button>
        <button class="btn-action btn-del ms-1" onclick="confirmDelUser(${u.id}, '${esc(u.username)}')">
          <i class="bi bi-trash3"></i> Eliminar
        </button>
      </td>
    </tr>`,
    )
    .join("");
}

function openUserModal(user = null) {
  document.getElementById("userId").value = user ? user.id : "";
  document.getElementById("userNombre").value = user ? user.username : "";
  document.getElementById("userEmail").value = user ? user.email : "";
  document.getElementById("userPass").value = "";
  document.getElementById("userRol").value = user ? user.role : "USER";
  document.getElementById("modalUserTitle").textContent = user
    ? "Editar Usuario"
    : "Nuevo Usuario";
  // Al editar, contraseña es opcional
  document.getElementById("passHint").style.display = user ? "block" : "none";
  openModal("modalUsuario");
}

function editUser(id) {
  const user = usuarios.find((u) => u.id === id);
  if (user) openUserModal(user);
}

async function saveUser() {
  const id = document.getElementById("userId").value;
  const username = document.getElementById("userNombre").value.trim();
  const email = document.getElementById("userEmail").value.trim();
  const pass = document.getElementById("userPass").value;
  const role = document.getElementById("userRol").value;

  if (!username || !email) {
    showToast("Nombre y email son obligatorios.", "error");
    return;
  }
  if (!id && !pass) {
    showToast("La contraseña es obligatoria para nuevos usuarios.", "error");
    return;
  }

  const body = { username, email, role };
  if (pass) body.password = pass;

  const method = id ? "PUT" : "POST";
  const url = id ? `/api/usuarios/${id}` : "/api/usuarios";

  try {
    const res = await apiFetch(url, {
      method,
      body: JSON.stringify(body),
    });
    if (!res.ok) throw new Error("Error al guardar.");
    closeModal("modalUsuario");
    showToast(id ? "Usuario actualizado." : "Usuario creado.", "success");
    loadUsuarios();
  } catch (err) {
    showToast(err.message, "error");
  }
}

function confirmDelUser(id, username) {
  document.getElementById("delMsg").textContent =
    `¿Eliminar al usuario "${username}"? Esta acción no se puede deshacer.`;
  document.getElementById("btnDelConfirm").onclick = () => deleteUser(id);
  openModal("modalDelete");
}

async function deleteUser(id) {
  try {
    const res = await apiFetch(`/api/usuarios/${id}`, {
      method: "DELETE",
    });
    if (!res.ok) throw new Error("Error al eliminar.");
    closeModal("modalDelete");
    showToast("Usuario eliminado.", "success");
    loadUsuarios();
  } catch (err) {
    showToast(err.message, "error");
  }
}

/* ════════════════════════════════════════════════════════════
   MODALES
════════════════════════════════════════════════════════════ */
function openModal(id) {
  document.getElementById(id).classList.add("open");
}
function closeModal(id) {
  document.getElementById(id).classList.remove("open");
}

// Cerrar al hacer click fuera
document.querySelectorAll(".modal-overlay").forEach((overlay) => {
  overlay.addEventListener("click", (e) => {
    if (e.target === overlay) overlay.classList.remove("open");
  });
});

/* ════════════════════════════════════════════════════════════
   TOAST
════════════════════════════════════════════════════════════ */
function showToast(msg, type = "success") {
  const container = document.getElementById("toastContainer");
  const icons = {
    success: "bi-check-circle-fill",
    error: "bi-x-circle-fill",
  };
  const toast = document.createElement("div");
  toast.className = `toast-msg ${type}`;
  toast.innerHTML = `<i class="bi ${icons[type] || "bi-info-circle"}"
    style="color:${type === "success" ? "#22c55e" : "#e11d48"}; font-size:1.1rem;"></i> ${msg}`;
  container.appendChild(toast);
  setTimeout(() => toast.remove(), 3500);
}

/* ════════════════════════════════════════════════════════════
   UTIL
════════════════════════════════════════════════════════════ */
function esc(str) {
  if (!str) return "";
  return String(str)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}

/* ════════════════════════════════════════════════════════════
   INIT — restaurar sesión si ya hay token
════════════════════════════════════════════════════════════ */
(function init() {
  renderNav();
  if (jwtToken) goTo("productos");
})();
