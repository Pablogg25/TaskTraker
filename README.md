# TasksTracker (escritorio)

Aplicación de escritorio con Electron. Un único proyecto, sin backend ni base
de datos aparte: los datos se guardan en un archivo JSON en la carpeta de
datos de tu usuario (equivalente a lo que hacía la versión Java con
`user.home`, pero gestionado automáticamente por Electron).

## Requisitos

- Node.js 20 o superior instalado.

## Ejecutar en modo desarrollo

```bash
cd TasksTracker-Desktop
npm install
npm start
```

Se abre una ventana nativa con la aplicación. Los cambios se guardan solos.

## Dónde se guardan los datos

- **Windows**: `%APPDATA%\TasksTracker\tasktracker-data.json`
- **macOS**: `~/Library/Application Support/TasksTracker/tasktracker-data.json`
- **Linux**: `~/.config/TasksTracker/tasktracker-data.json`

Es un JSON legible, así que puedes abrirlo con un editor de texto para
inspeccionarlo o hacer una copia de seguridad manual.

## Generar el instalador (.exe / .dmg / .AppImage)

```bash
npm run dist
```

Esto crea el instalador para tu sistema operativo actual dentro de la
carpeta `dist/`. Para generar el instalador de **otro** sistema operativo
(por ejemplo, un `.exe` de Windows compilado desde macOS o Linux) hace falta
configuración adicional específica de `electron-builder` — puedes generar
cada instalador directamente desde el sistema operativo correspondiente sin
tocar nada más, que es el camino más sencillo si no necesitas automatizarlo.

## Notas

- No hay ventana de terminal ni proceso de servidor visible: `npm start`
  abre directamente la ventana de la aplicación.
- El icono de la app es el que trae Electron por defecto. Para poner uno
  propio, añade un `icon.ico` (Windows), `icon.icns` (macOS) e `icon.png`
  (Linux) y referencia sus rutas en la sección `build` de `package.json`.
