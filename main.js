const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');
const fs = require('fs');

// app.getPath('userData') es el equivalente de Electron a lo que hacía tu
// versión Java con "user.home": una carpeta específica del sistema operativo
// para los datos de la app (en Windows: %APPDATA%\TasksTracker,
// en macOS: ~/Library/Application Support/TasksTracker,
// en Linux: ~/.config/TasksTracker).
const dataFilePath = path.join(app.getPath('userData'), 'tasktracker-data.json');

function readData() {
  try {
    const raw = fs.readFileSync(dataFilePath, 'utf-8');
    const parsed = JSON.parse(raw);
    if (parsed && Array.isArray(parsed.tasks)) return parsed;
    return { tasks: [] };
  } catch (err) {
    // El archivo no existe todavía (primer arranque) o está corrupto: en
    // ambos casos, empezamos con una lista vacía en vez de romper la app.
    return { tasks: [] };
  }
}

function writeData(data) {
  fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2), 'utf-8');
}

function createWindow() {
  const win = new BrowserWindow({
    width: 1100,
    height: 720,
    minWidth: 640,
    minHeight: 480,
    title: 'TasksTracker',
    backgroundColor: '#0B1F30', // evita el "flash" blanco al abrir
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      contextIsolation: true, // aísla el JS de la página del proceso de Node por seguridad
      nodeIntegration: false,
    },
  });

  win.loadFile('index.html');

  // Descomenta la siguiente línea si necesitas depurar la interfaz:
  // win.webContents.openDevTools();
}

// ===== IPC: puente entre la ventana (renderer) y el sistema de archivos =====
ipcMain.handle('tasks:load', () => readData());
ipcMain.handle('tasks:save', (_event, data) => {
  writeData(data);
  return true;
});

app.whenReady().then(() => {
  createWindow();

  app.on('activate', () => {
    // En macOS es normal volver a crear una ventana al hacer clic en el
    // icono del dock si no queda ninguna abierta.
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});
