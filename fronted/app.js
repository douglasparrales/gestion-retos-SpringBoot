const BASE_URL = "http://localhost:8080/system/api/v1/challenge";
const USER_URL = "http://localhost:8080/system/api/v1/user";

let selectedChallengeId = null;

// --- 1. LISTAR RETOS ---
async function getChallenges() {
    try {
        const res = await fetch(BASE_URL);
        const challenges = await res.json();
        const container = document.getElementById("challenges-list");
        
        container.innerHTML = challenges.map(c => `
            <div class="challenge-card" onclick="selectChallenge(${c.challengeId}, '${c.title}', ${c.points})" id="card-${c.challengeId}">
                <div style="display:flex; justify-content:space-between; align-items:flex-start;">
                    <h4 style="margin:0 0 10px 0">${c.title}</h4>
                    <span class="id-badge" style="font-size:0.6rem">#${c.challengeId}</span>
                </div>
                <p style="font-size: 0.85rem; color: #64748b; margin-bottom: 15px;">${c.description}</p>
                <div style="font-size: 0.85rem; font-weight: 700; color: var(--primary);">⭐ ${c.points} pts</div>
            </div>
        `).join('');
    } catch (e) { showToast("Error al conectar con el servidor", "error"); }
}

// --- 2. SELECCIONAR RETO Y VER PARTICIPANTES ---
function selectChallenge(id, title, points) {
    selectedChallengeId = id;
    document.querySelectorAll('.challenge-card').forEach(c => c.classList.remove('selected'));
    document.getElementById(`card-${id}`).classList.add('selected');

    document.getElementById("empty-state").style.display = "none";
    document.getElementById("challenge-detail-content").style.display = "block";
    document.getElementById("selected-title").innerText = title;
    document.getElementById("selected-points").innerText = `${points} pts`;

    getParticipants(id);
}

async function getParticipants(challengeId) {
    const listContainer = document.getElementById("participants-list");
    listContainer.innerHTML = "<p>Cargando participantes...</p>";
    try {
        const res = await fetch(`${BASE_URL}/${challengeId}/user`);
        const users = await res.json();
        if(users.length === 0) {
            listContainer.innerHTML = "<p style='color:gray; font-size:0.85rem;'>Sin inscritos.</p>";
            return;
        }
        listContainer.innerHTML = users.map(u => `
            <div class="user-item">
                <div style="display:flex; align-items:center; gap:10px;">
                    <div style="width:30px; height:30px; background:var(--primary); color:white; border-radius:50%; display:flex; align-items:center; justify-content:center; font-size:10px; font-weight:bold;">
                        ${u.username.substring(0,2).toUpperCase()}
                    </div>
                    <div>
                        <div style="font-weight:600; font-size:0.85rem;">${u.username}</div>
                        <div style="font-size:0.7rem; color:gray;">ID: ${u.userId}</div>
                    </div>
                </div>
                <button class="btn-ghost" style="padding:4px 8px; font-size:0.7rem" onclick="marcarCompletado(${u.userId})">Completar</button>
            </div>
        `).join('');
    } catch (e) { listContainer.innerHTML = "Error al cargar."; }
}

// --- 3. DIRECTORIO GLOBAL ---
async function openUserDirectory() {
    toggleModal('directory-modal');
    const listContainer = document.getElementById("global-users-list");
    listContainer.innerHTML = "<tr><td colspan='5' style='text-align:center;'>Cargando...</td></tr>";
    try {
        const res = await fetch(USER_URL);
        const users = await res.json();
        listContainer.innerHTML = users.map(u => `
            <tr>
                <td><span class="id-badge">#${u.userId}</span></td>
                <td><strong>${u.username}</strong></td>
                <td>${u.email}</td>
                <td><span class="points-badge">${u.totalPoints} pts</span></td>
                <td><button class="btn-ghost" style="padding:4px 8px; font-size:0.75rem;" onclick="viewUserDetails(${u.userId})">Info</button></td>
            </tr>
        `).join('');
    } catch (e) { listContainer.innerHTML = "Error."; }
}

async function viewUserDetails(id) {
    try {
        const res = await fetch(`${USER_URL}/${id}`);
        const u = await res.json();
        alert(`USUARIO: ${u.username}\nID: ${u.userId}\nEMAIL: ${u.email}\nPUNTOS: ${u.totalPoints}\nREGISTRO: ${u.registrationDate}`);
    } catch (e) { showToast("Error al obtener detalle", "error"); }
}

// --- 4. ACCIONES (POST / PUT) ---
async function inscribirseRapido() {
    const userId = document.getElementById("quick-user-id").value;
    if(!userId || !selectedChallengeId) return showToast("Falta ID o Reto", "error");
    try {
        const res = await fetch(`${BASE_URL}/${selectedChallengeId}/inscription/${userId}`, { method: "POST" });
        if(res.ok || res.status === 201) { showToast("¡Inscrito! 🎯"); getParticipants(selectedChallengeId); }
        else { showToast("Error o ya inscrito", "error"); }
    } catch (e) { showToast("Error de conexión", "error"); }
}

async function marcarCompletado(userId) {
    try {
        const res = await fetch(`${BASE_URL}/${selectedChallengeId}/complete/${userId}`, { method: "PUT" });
        if(res.ok) { showToast("¡Reto completado! 🏆"); getParticipants(selectedChallengeId); }
    } catch (e) { showToast("Error", "error"); }
}

async function createUser() {
    const data = {
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };
    try {
        const res = await fetch(USER_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(data)
        });
        if(res.ok) { showToast("Usuario creado"); toggleModal('user-modal'); }
    } catch (e) { showToast("Error", "error"); }
}

async function createChallenge() {
    const data = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        points: parseInt(document.getElementById("points").value),
        startDate: document.getElementById("startDate").value,
        endDate: document.getElementById("endDate").value,
        creatorId: parseInt(document.getElementById("creatorId").value)
    };
    try {
        const res = await fetch(BASE_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(data)
        });
        if(res.status === 201) { showToast("Reto creado"); toggleModal('challenge-modal'); getChallenges(); }
    } catch (e) { showToast("Error al crear reto", "error"); }
}

// --- UTILIDADES ---
function toggleModal(id) {
    const m = document.getElementById(id);
    m.style.display = (m.style.display === 'flex') ? 'none' : 'flex';
}

function showToast(msg, type = "success") {
    const t = document.getElementById("toast");
    t.innerText = msg;
    t.style.background = type === "error" ? "#ef4444" : "#1e293b";
    t.style.display = "block";
    setTimeout(() => t.style.display = "none", 3000);
}

window.onload = () => { getChallenges(); lucide.createIcons(); };