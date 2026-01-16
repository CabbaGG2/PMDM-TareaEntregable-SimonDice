# 🎲 Damian Dice

> Un juego de memoria adictivo para Android con sincronización en la nube usando MongoDB Atlas

[![Language: Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat-square&logo=kotlin)](https://kotlinlang.org/)
[![Platform: Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android)](https://www.android.com/)
[![Architecture: MVVM](https://img.shields.io/badge/Architecture-MVVM-4285F4?style=flat-square)](https://developer.android.com/topic/architecture)
[![Database: MongoDB](https://img.shields.io/badge/Database-MongoDB-13AA52?style=flat-square&logo=mongodb)](https://www.mongodb.com/)
[![Status: In Development](https://img.shields.io/badge/Status-In_Development-yellow?style=flat-square)](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice)

Damian Dice es un juego de memoria clásico inspirado en "Simon Says" que desafía tu capacidad de recordar secuencias de colores cada vez más largas. **Rama de desarrollo: MongoDB Cloud Integration v1.1.0**

## 🎮 Cómo Jugar

1. **Inicia el juego** y observa atentamente la secuencia de colores que se iluminan
2. **Repite la secuencia** tocando los colores en el mismo orden
3. Si aciertas, la secuencia se hará más larga
4. Si te equivocas, el juego termina
5. **Compite globalmente** contra otros jugadores en el ranking mundial

## ✨ Características

### Fase Actual (mongoDB_con_asistenciaIA)

Esta rama implementa la integración con **MongoDB Cloud** para sincronizar puntuaciones en tiempo real:

- 🌐 **Sincronización en la nube** con MongoDB Atlas
- 📊 **Ranking global** para ver tu posición contra otros jugadores
- 🔐 **Autenticación flexible** (usuario anónimo o por email)
- 📱 **Funcionamiento offline** con sincronización automática
- 🔄 **Caché local inteligente** para experiencia fluida
- 📈 **Historial de puntuaciones** sincronizado entre dispositivos

### Características Existentes

- Juego de memoria clásico con dificultad progresiva
- Interfaz moderna con Jetpack Compose
- Seguimiento de puntuaciones y mejor record
- Persistencia local con SQLite y SharedPreferences

## 🛠️ Stack Técnico

| Componente | Tecnología |
|-----------|-----------|
| **Lenguaje** | Kotlin |
| **UI** | Jetpack Compose |
| **Arquitectura** | MVVM (Model-View-ViewModel) |
| **Base de datos local** | SQLite (Room) + SharedPreferences |
| **Base de datos remota** | MongoDB Atlas + Realm |
| **Asincronía** | Coroutines + Flow |
| **Testing** | JUnit, Mockito |
| **Autenticación** | MongoDB Realm Authentication |
| **SDK Mínimo** | API 31 (Android 12) |

## 📋 Estructura del Proyecto

```
DamianDice/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/jc/dam/damiandice/
│   │   │   │   ├── MyViewModel.kt          # Lógica de juego
│   │   │   │   ├── db/                      # Capa de datos
│   │   │   │   │   ├── AuthService.kt       # [NEW] Autenticación
│   │   │   │   │   ├── PuntuacionRealm.kt   # [NEW] Modelo MongoDB
│   │   │   │   │   └── MongoDBRepository.kt # [NEW] Operaciones CRUD
│   │   │   │   └── ui/UI.kt                # Componentes Compose
│   │   │   └── res/                         # Recursos
│   │   ├── test/java/                       # Tests unitarios
│   │   └── androidTest/java/                # Tests de integración
│   └── build.gradle.kts
├── plan/
│   └── feature-mongodb-cloud-integration-1.md  # Plan de implementación
├── docs/
│   ├── GITHUB_ISSUES_CREATED.md             # Issues generados
│   └── MONGODB_SETUP.md                     # [PRÓXIMO] Guía de configuración
├── .github/
│   └── prompts/
│       └── create-github-issues-feature-from-implementation-plan.prompt.md
└── README.md                                 # Este archivo
```

## 🚀 Plan de Implementación (8 Fases)

La rama **mongoDB_con_asistenciaIA** implementa una integración completa de MongoDB siguiendo este plan de 8 fases:

### 📌 FASE 1: Configuración de MongoDB Atlas
- Crear infraestructura en MongoDB Atlas
- Integrar SDK de Realm para Kotlin
- Configurar permisos y credenciales

**GitHub Issue:** [#20](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/20)

### 📌 FASE 2: Modelado de Datos
- Crear entidad `PuntuacionRealm` para MongoDB
- Establecer relaciones Usuario ↔ Puntuaciones
- Definir esquema de sincronización

**GitHub Issue:** [#21](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/21)

### 📌 FASE 3: Autenticación
- Implementar `AuthService` con Realm
- Soportar login anónimo y por email
- Gestionar sesiones y recuperación de errores

**GitHub Issue:** [#22](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/22)

### 📌 FASE 4: Repository Remoto
- Crear interfaz `PuntuacionRepository`
- Implementar `MongoDBPuntuacionRepository`
- Operaciones CRUD completas con sincronización

**GitHub Issue:** [#23](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/23)

### 📌 FASE 5: Integración ViewModel
- Actualizar `MyViewModel` con operaciones remotas
- Mantener funcionalidad actual sin cambios
- Implementar fallback a datos locales

**GitHub Issue:** [#24](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/24)

### 📌 FASE 6: Interfaz de Usuario
- Crear pantalla de rankings globales
- Mostrar sincronización en tiempo real
- Mejorar experiencia del usuario

**GitHub Issue:** [#25](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/25)

### 📌 FASE 7: Testing Completo
- Tests unitarios para todas las capas
- Tests de integración para sincronización
- Validación crítica de funcionalidad de juego

**GitHub Issue:** [#26](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/26)

### 📌 FASE 8: Deployment y Release
- Documentación completa
- Versión 1.1.0 (SemVer)
- Rama release/1.1 lista para producción

**GitHub Issue:** [#27](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues/27)

## 🔍 Acerca del Prompt: `create-github-issues-feature-from-implementation-plan.prompt.md`

Este prompt automatiza la creación de GitHub Issues basándose en fases del plan de implementación:

### 📝 Propósito
Convertir automáticamente un plan de implementación en GitHub Issues bien estructurados, facilitando el seguimiento del proyecto y asignación de tareas.

### 🎯 Funcionalidad Principal
```
Entrada:  Plan de implementación (archivo markdown)
        ↓
    [Analizar fases del plan]
        ↓
    [Verificar issues existentes]
        ↓
    [Crear/actualizar issues]
        ↓
Salida:  GitHub Issues con estructura completa
```

### ⚙️ Proceso Automatizado

1. **Análisis**: Lee el plan e identifica todas las fases
2. **Validación**: Busca issues existentes para evitar duplicados
3. **Creación**: Genera nuevos issues con:
   - Título descriptivo de la fase
   - Descripción con contexto y requisitos
   - Tareas checklist
   - Labels apropiados (feature/chore)
   - Referencias a requisitos del plan
4. **Actualización**: Modifica issues existentes si es necesario

### 📊 Estadísticas de Generación

Para este proyecto se generaron:
- **8 Issues** (uno por fase)
- **47 Subtareas** mapeadas
- **9 Archivos** a crear
- **5 Archivos** a modificar
- **Version target:** 1.1.0

### 🏷️ Templates Utilizados

El prompt utiliza templates de GitHub:
- `feature_request.yml` para nuevas funcionalidades
- `chore_request.yml` para tareas de mantenimiento
- Labels automáticos: `feature`, `database`, `mongodb`, `testing`, etc.

### 📍 Resultado

Los 8 issues están vinculados en la rama `mongoDB_con_asistenciaIA`:
- [Issue #20](#fase-1-configuración-de-mongodb-atlas) - FASE 1
- [Issue #21](#fase-2-modelado-de-datos) - FASE 2
- [Issue #22](#fase-3-autenticación) - FASE 3
- [Issue #23](#fase-4-repository-remoto) - FASE 4
- [Issue #24](#fase-5-integración-viewmodel) - FASE 5
- [Issue #25](#fase-6-interfaz-de-usuario) - FASE 6
- [Issue #26](#fase-7-testing-completo) - FASE 7
- [Issue #27](#fase-8-deployment-y-release) - FASE 8

Ver [docs/GITHUB_ISSUES_CREATED.md](docs/GITHUB_ISSUES_CREATED.md) para detalles completos.

## 🚦 Requisitos Previos

Para ejecutar el proyecto necesitas:

- **Android Studio** 2024.1 o superior
- **JDK 11** o superior
- **API Level:** 31+ (Android 12+)
- **MongoDB Atlas Account** (gratuito en [mongodb.com](https://mongodb.com))
  - Crear cluster M0
  - Configurar App Services
  - Obtener credenciales

## 📦 Compilación y Ejecución

### Clonar el Repositorio

```bash
git clone https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice.git
cd PMDM-TareaEntregable-SimonDice
git checkout mongoDB_con_asistenciaIA
```

### Configurar MongoDB (IMPORTANTE)

1. Crea una cuenta en [MongoDB Atlas](https://mongodb.com/cloud/atlas)
2. Crea un cluster M0 (gratuito)
3. Configura App Services
4. Obtén el App ID y API Key
5. Crea `app/src/main/res/values/mongodb_config.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="mongodb_app_id">TU_APP_ID</string>
    <string name="mongodb_api_key">TU_API_KEY</string>
</resources>
```

### Compilar y Ejecutar

```bash
# Abrir en Android Studio
open -a "Android Studio" .

# O compilar desde línea de comandos
./gradlew build

# Ejecutar en emulador/dispositivo
./gradlew installDebug
```

## 🧪 Tests

```bash
# Ejecutar todos los tests
./gradlew test

# Tests de UI
./gradlew connectedAndroidTest

# Coverage de tests
./gradlew testDebugUnitTest --info
```

## 📊 Control de Versiones

Este proyecto sigue **GitFlow** y **SemVer**:

```
main (v1.0.0) ─┐
               ├─→ release/1.1
               └─ develop ─ mongoDB_con_asistenciaIA
                          ├─ feature/auth
                          ├─ feature/repository
                          └─ feature/ui
```

- **main**: Versiones estables (release tags)
- **release/1.1**: Preparación de versión 1.1.0
- **develop**: Rama de integración
- **mongoDB_con_asistenciaIA**: Rama de feature para MongoDB

## 🤝 Contribuir

Para contribuir a este proyecto:

1. Fork el repositorio
2. Crea una rama `feature/tu-feature`
3. Realiza tus cambios
4. Asegúrate de que los tests pasen
5. Crea un Pull Request

## 🐛 Reportar Bugs

Usa la sección de [GitHub Issues](https://github.com/CabbaGG2/PMDM-TareaEntregable-SimonDice/issues) para reportar bugs.

## 📄 Licencia

Este proyecto es parte de una tarea académica (PMDM - Programación Multimedia para Dispositivos Móviles).

## 👤 Autoría

- **Desarrollador:** José Cámara
- **Rama MongoDB:** Dam (con asistencia de GitHub Copilot)
- **Institución:** Ciclo Superior de Desarrollo de Aplicaciones Multiplataforma

## 🔗 Enlaces Útiles

- [Plan de Implementación](plan/feature-mongodb-cloud-integration-1.md)
- [GitHub Issues Creados](docs/GITHUB_ISSUES_CREATED.md)
- [MongoDB Realm Documentation](https://www.mongodb.com/docs/realm/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

