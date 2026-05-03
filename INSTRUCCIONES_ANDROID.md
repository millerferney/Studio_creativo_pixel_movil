# Guía y Prompt para Maquetación en Android Studio Nativo

Esta carpeta (`Android_Export`) contiene los recursos base para iniciar tu proyecto en Android Studio respetando el Design System "Neon Brutalist" de tu página web.

## 1. Dónde colocar los recursos generados

Si creas un nuevo proyecto en **Android Studio** (Empty Views Activity, Java), copia los archivos de esta carpeta hacia tu nuevo proyecto de la siguiente manera:

- `Android_Export/res/values/colors.xml` -> **Pégalo y reemplaza en:** `app/src/main/res/values/colors.xml`
- `Android_Export/res/values/themes.xml` -> **Pégalo y reemplaza en:** `app/src/main/res/values/themes.xml` (y haz lo mismo para `themes.xml (night)` si existe).

## 2. Cómo agregar las Tipografías

Tu proyecto web usa `Space Grotesk`, `Outfit` y `JetBrains Mono`. Para agregarlas a Android Studio:

1. Ve a [Google Fonts](https://fonts.google.com/) y descarga las fuentes:
   - Space Grotesk
   - Outfit
   - JetBrains Mono
2. Descomprime los archivos descargados. Busca los archivos `.ttf` (TrueType Font).
3. Renombra los archivos para que estén todo en minúsculas y sin espacios ni guiones, usando guiones bajos (ej: `space_grotesk.ttf`, `outfit.ttf`, `jetbrains_mono.ttf`).
4. En Android Studio, haz clic derecho sobre la carpeta `res` -> **New** -> **Android Resource Directory**. En "Resource type" selecciona **font** y haz clic en OK.
5. Pega los archivos `.ttf` dentro de la nueva carpeta `res/font/`.
6. ¡Listo! El archivo `themes.xml` que te generé ya está configurado para buscar y aplicar estas fuentes.

## 3. Cómo agregar Logos e Imágenes

1. Busca tus logos y recursos (por ejemplo, el favicon, los SVGs o PNGs que tienes en tu carpeta `Assets/` del proyecto web).
2. En Android Studio, ve a `res/drawable/`. Pega ahí tus imágenes (deben estar en minúsculas y sin espacios, ej: `logo_pixel.png`).
3. Para íconos vectoriales (.svg), haz clic derecho en `res/drawable` -> **New** -> **Vector Asset** -> Selecciona "Local file (SVG, PSD)" y elige tu archivo `.svg`. Android Studio lo convertirá a un `.xml` nativo que es mucho más eficiente.

---

## 4. Prompt para el Asistente AI / Copilot (Skills)

Usa este prompt en tu herramienta de IA favorita (como GitHub Copilot, Gemini, ChatGPT o el propio Android Studio Bot) para generar las pantallas de tu app basadas en el diseño web.

***Copia desde aquí:***

> **Rol y Objetivo:**
> Eres un desarrollador Senior de Android Nativo experto en UI/UX. Mi objetivo es maquetar una aplicación en Android Studio (Java y XML) que replique exactamente el estilo visual "Neon Brutalist" de mi página web e-commerce.
> 
> **Design System (Ya configurado en colors.xml y themes.xml):**
> - **Fondo Principal (Base):** #080B14
> - **Fondo de Tarjetas (Surface):** #0F1221
> - **Inputs (Elevated):** #161A2E
> - **Acento Primario:** #00E5FF (Cyan)
> - **Acento Secundario:** #7C3AED (Purple)
> - **Éxito (Neon Green):** #00FF00
> - **Texto Principal:** #FFFFFF
> - **Texto Secundario:** #D1D9E6
> 
> **Tipografías (Ya en res/font/):**
> - `space_grotesk` para Títulos (H1, H2, H3) y Botones.
> - `outfit` para Texto de cuerpo (Body) y Badges.
> - `jetbrains_mono` para código o etiquetas específicas.
> 
> **Reglas de Maquetación (XML):**
> 1. Usa `MaterialComponents` y `ConstraintLayout` para construir vistas responsivas.
> 2. Los botones primarios usan la clase `MaterialButton` con fondo #00E5FF, texto #080B14, `app:cornerRadius="10dp"` y elevación para simular el efecto glow.
> 3. Las tarjetas usan `MaterialCardView` con fondo #0F1221, un borde sutil (`app:strokeColor="#14FFFFFF"` y `app:strokeWidth="1dp"`) y un radio de 14dp (`app:cardCornerRadius="14dp"`).
> 4. Los campos de texto deben ser `TextInputLayout` con `TextInputEditText`, fondo #161A2E y un contorno que cambie a #00E5FF al tener el foco.
> 5. Para simular el efecto "Glassmorphism" o desenfoques en modales/drawers, usa fondos oscuros semi-transparentes como `#E0080B14`.
> 
> **Tarea actual:**
> Por favor, constrúyeme el layout XML para la pantalla de **[INSERTAR AQUÍ EL NOMBRE DE LA PANTALLA, EJEMPLO: "Catálogo de Productos" o "Inicio"]**. 
> La pantalla debe tener: [DESCRIBE BREVEMENTE LOS ELEMENTOS, EJEMPLO: un RecyclerView en cuadrícula, una barra superior (Toolbar) con logo a la izquierda y botón de carrito a la derecha, etc].
> 
> Entrégame únicamente el código XML bien estructurado utilizando los colores de mi `colors.xml` (`@color/base_background`, `@color/primary_cyan`, etc.) y las fuentes configuradas.

***Hasta aquí el prompt.***

---

## 5. Arquitectura Sugerida (Para cuando implementes Java)

Dado que es un E-commerce (Studio Creativo Pixel), te recomiendo usar **RecyclerView** con un **GridLayoutManager** para tu vista de `catalogo.html`. 

Para emular el "Cart Drawer" (carrito lateral) de la web web, en Android lo ideal es usar un `DrawerLayout` donde el lado derecho (`android:layout_gravity="end"`) sea el menú o resumen de tu carrito de compras.
