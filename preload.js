const { contextBridge, ipcRenderer } = require('electron');

// No exponemos Node ni el sistema de archivos directamente a la página (eso
// sería un riesgo de seguridad); solo estas dos funciones concretas.
contextBridge.exposeInMainWorld('tasksAPI', {
  load: () => ipcRenderer.invoke('tasks:load'),
  save: (data) => ipcRenderer.invoke('tasks:save', data),
});
