# 🎮 Pokédex Android App

<div align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-API_24+-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-2.9.0-48B983?style=for-the-badge&logo=square&logoColor=white)
![Coroutines](https://img.shields.io/badge/Coroutines-1.7.3-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-3-757575?style=for-the-badge&logo=material-design&logoColor=white)

![ViewModel](https://img.shields.io/badge/ViewModel-2.6.2-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Coil](https://img.shields.io/badge/Coil-2.4.0-00C4CC?style=for-the-badge&logo=kotlin&logoColor=white)
![PokeAPI](https://img.shields.io/badge/PokeAPI-v2-EF5350?style=for-the-badge&logo=pokemon&logoColor=white)
![RecyclerView](https://img.shields.io/badge/RecyclerView-1.3.2-4CAF50?style=for-the-badge&logo=android&logoColor=white)
![Lifecycle](https://img.shields.io/badge/Lifecycle-2.6.2-2196F3?style=for-the-badge&logo=android&logoColor=white)

![Gson](https://img.shields.io/badge/Gson-2.10.1-FF6F00?style=for-the-badge&logo=json&logoColor=white)
![ConstraintLayout](https://img.shields.io/badge/ConstraintLayout-2.1.4-9C27B0?style=for-the-badge&logo=android&logoColor=white)
![CardView](https://img.shields.io/badge/CardView-1.0.0-E91E63?style=for-the-badge&logo=android&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge&logo=opensourceinitiative&logoColor=white)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge&logo=statuspage&logoColor=white)

</div>

## 📖 Descripción

Aplicación móvil nativa de Android que implementa una Pokédex completa utilizando la [PokeAPI](https://pokeapi.co/). La aplicación permite explorar, buscar y visualizar información detallada de todos los Pokémon, con una interfaz moderna y fluida que sigue las guías de Material Design.

## ✨ Características

- 📱 **Interfaz Moderna**: Diseño inspirado en Material Design con animaciones fluidas
- 🔄 **Scroll Infinito**: Carga paginada de Pokémon para una experiencia óptima
- 🎨 **Diseño Temático**: Colores dinámicos según el tipo de Pokémon
- 📊 **Estadísticas Completas**: Visualización detallada de stats base con progress bars
- 🖼️ **Imágenes de Alta Calidad**: Artwork oficial de cada Pokémon
- ⚡ **Arquitectura MVVM**: Implementación robusta con ViewModel y StateFlow
- 🌐 **Manejo de Red**: Retrofit con Coroutines para peticiones asíncronas
- 💾 **Gestión de Estado**: StateFlow para actualización reactiva de UI

## 🛠️ Tecnologías Utilizadas

### Core
- **Kotlin**: Lenguaje de programación principal
- **Android SDK**: API Level 24+ (Android 7.0)
- **Coroutines**: Para manejo asíncrono y concurrencia
- **Flow/StateFlow**: Gestión de estado reactivo

### Arquitectura
- **MVVM (Model-View-ViewModel)**: Patrón arquitectónico
- **Repository Pattern**: Abstracción de fuentes de datos
- **Lifecycle Components**: ViewModel y LiveData

### Networking
- **Retrofit**: Cliente HTTP type-safe
- **Gson**: Serialización/deserialización JSON
- **OkHttp**: Cliente HTTP subyacente

### UI/UX
- **RecyclerView**: Lista eficiente de Pokémon
- **ConstraintLayout**: Layouts flexibles y responsivos
- **CardView**: Componentes de tarjeta material
- **Coil**: Carga y caché de imágenes
- **Material Components**: Componentes de Material Design

### API
- **PokeAPI v2**: API RESTful pública para datos de Pokémon

## 📋 Requisitos Previos

- Android Studio Hedgehog | 2023.1.1 o superior
- JDK 11 o superior
- Android SDK 24 o superior
- Conexión a Internet (para cargar datos de la API)

## 🚀 Instalación

1. **Clonar el repositorio**
```bash
git clone [https://github.com/DavidAlvar3z/DSM-Investigacion02]
cd pokedex-android
```

2. **Abrir en Android Studio**
   - Abre Android Studio
   - Selecciona "Open an Existing Project"
   - Navega a la carpeta del proyecto y selecciónala

3. **Sincronizar Gradle**
   - Android Studio sincronizará automáticamente las dependencias
   - Espera a que finalice el proceso

4. **Ejecutar la aplicación**
   - Conecta un dispositivo Android o inicia un emulador
   - Haz clic en el botón "Run" (▶️) o presiona `Shift + F10`

## 📦 Dependencias Principales

```gradle
dependencies {
    // Kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.9.0"
    
    // Android Core
    implementation "androidx.core:core-ktx:1.12.0"
    implementation "androidx.appcompat:appcompat:1.6.1"
    implementation "com.google.android.material:material:1.11.0"
    
    // Lifecycle & ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    
    // Networking
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    
    // Image Loading
    implementation "io.coil-kt:coil:2.4.0"
    
    // UI Components
    implementation "androidx.recyclerview:recyclerview:1.3.2"
    implementation "androidx.cardview:cardview:1.0.0"
    implementation "androidx.constraintlayout:constraintlayout:2.1.4"
}
```

## 🏗️ Estructura del Proyecto

```
com.example.investigacion02_dsm/
├── ui/
│   ├── MainActivity.kt              # Pantalla principal con lista
│   ├── PokemonDetailActivity.kt     # Pantalla de detalles
│   ├── SplashActivity.kt            # Pantalla de inicio
│   └── PokemonAdapter.kt            # Adaptador del RecyclerView
├── viewmodel/
│   └── PokemonViewModel.kt          # ViewModel con lógica de negocio
├── data/
│   ├── api/
│   │   ├── PokemonApiService.kt     # Definición de endpoints
│   │   └── RetrofitInstance.kt      # Configuración de Retrofit
│   └── model/
│       └── PokemonResponse.kt       # Modelos de datos
└── res/
    ├── layout/                       # Archivos XML de layouts
    ├── drawable/                     # Recursos gráficos
    └── values/                       # Colores, strings, estilos
```

## 🎨 Características de UI/UX

### Pantalla Principal
- Lista scrollable con scroll infinito
- Tarjetas con diseño Pokéball
- Imágenes de alta calidad con loading placeholder
- Indicador de carga al cargar más contenido

### Pantalla de Detalles
- Header con color dinámico según tipo
- Imagen de artwork oficial
- Información completa (peso, altura, habilidades)
- Stats visualizadas con progress bars coloridos
- Navegación intuitiva con botón de regreso

## 🔌 API Integration

La aplicación consume la [PokeAPI v2](https://pokeapi.co/):

**Base URL**: `https://pokeapi.co/api/v2/`

### Endpoints Utilizados

- `GET /pokemon?limit={limit}&offset={offset}` - Lista paginada
- `GET /pokemon/{id}` - Detalles de un Pokémon específico

## 👥 Autores

| Nombre | Carnet |
|--------|--------|
| David Alejandro Alvarez Moreira | AMM240104 |
| Ashley Gabriela Valdez González | VG240979 |

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

```
MIT License

Copyright (c) 2024 David Alvarez & Ashley Valdez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 🙏 Agradecimientos

- [PokeAPI](https://pokeapi.co/) por proporcionar la API pública
- [The Pokémon Company](https://www.pokemon.com/) por los recursos gráficos
- Comunidad de Android Developers

## 📞 Contacto

Para preguntas, sugerencias o reportar problemas, por favor abre un issue en el repositorio.

---

<div align="center">

**Hecho con ❤️ y ☕ en El Salvador**

⭐ Si te gustó este proyecto, no olvides darle una estrella!

</div>
```
