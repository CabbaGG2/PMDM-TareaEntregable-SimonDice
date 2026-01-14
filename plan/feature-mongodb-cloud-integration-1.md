---
goal: Integración de MongoDB Atlas en la Nube para Almacenamiento Remoto de Puntuaciones en DamianDice
version: 1.0
date_created: 2026-01-14
last_updated: 2026-01-14
owner: Dam (Desarrollador Principal)
status: 'Planned'
tags: ['feature', 'database', 'cloud', 'mongodb', 'improvement', 'backend']
---

# Introducción

![Status: Planned](https://img.shields.io/badge/status-Planned-blue)

Esta plan de implementación describe la integración de **MongoDB Atlas** (servicio de MongoDB en la nube) en la aplicación DamianDice. El objetivo es migrar del almacenamiento local (SQLite + SharedPreferences) a una base de datos remota en la nube para sincronizar las puntuaciones del usuario entre dispositivos, permitir un sistema de rankings globales y mejorar la persistencia de datos.

La aplicación actual utiliza SQLite (Room) para ciertos datos y SharedPreferences para las puntuaciones. Con esta mejora, se implementará una arquitectura híbrida que mantendrá caché local mientras sincroniza con MongoDB Atlas.

## 1. Requisitos & Restricciones

| Identificador | Descripción | Prioridad |
|---|---|---|
| **REQ-001** | Integrar SDK de MongoDB Realm para Kotlin/Android | Alta |
| **REQ-002** | Crear modelo de datos para sincronizar puntuaciones en MongoDB | Alta |
| **REQ-003** | Implementar autenticación con MongoDB Realm (usuario anónimo o email) | Alta |
| **REQ-004** | Sincronizar datos locales (SQLite) con MongoDB Atlas | Media |
| **REQ-005** | Implementar caché local para funcionamiento offline | Media |
| **REQ-006** | Crear repositorio remoto para gestionar operaciones CRUD con MongoDB | Media |
| **REQ-007** | Actualizar ViewModel para soportar operaciones asincrónicas | Media |
| **REQ-008** | Implementar manejo de errores y reconexión automática | Media |
| **REQ-009** | Crear tests unitarios para la capa de datos remota | Baja |
| **CON-001** | La aplicación debe mantener compatibilidad con minSdk 31 | Crítica |
| **CON-002** | No romper la funcionalidad actual de juego mientras se integra MongoDB | Crítica |
| **CON-003** | Requerirá credenciales de MongoDB Atlas (API Key, Connection String) | Restricción |
| **GUD-001** | Mantener arquitectura MVVM existente | Guía |
| **GUD-002** | Seguir el patrón de Repository para acceso a datos | Guía |
| **GUD-003** | Utilizar Coroutines para operaciones asincrónicas | Guía |
| **PAT-001** | Implementar patrón Repository para abstracción de datos | Patrón |
| **PAT-002** | Usar ViewModel con Coroutines para gestionar estado asincrónico | Patrón |

## 2. Pasos de Implementación

### Fase de Implementación 1: Configuración de MongoDB Atlas y Dependencias

**GOAL-001:** Configurar MongoDB Atlas en la nube, añadir dependencias necesarias y preparar la estructura del proyecto.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-001 | Crear cuenta/proyecto en MongoDB Atlas | | |
| TASK-002 | Crear cluster M0 (gratuito) en MongoDB Atlas | | |
| TASK-003 | Configurar App Services en MongoDB Atlas | | |
| TASK-004 | Obtener credentials (App ID y API Key) | | |
| TASK-005 | Añadir dependencias de MongoDB Realm a gradle.kts | | |
| TASK-006 | Añadir dependencias de Coroutines y Flow | | |
| TASK-007 | Actualizar AndroidManifest.xml con permisos de internet | | |
| TASK-008 | Crear archivo de configuración para MongoDB credentials | | |

### Fase de Implementación 2: Modelado de Datos y Entidades

**GOAL-002:** Crear modelos de datos que representen las puntuaciones en MongoDB Realm.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-009 | Crear clase `Puntuacion` como RealmObject | | |
| TASK-010 | Definir propiedades: id, usuarioId, puntuacion, fecha, dispositivo | | |
| TASK-011 | Crear clase `Usuario` como RealmObject | | |
| TASK-012 | Definir relación Usuario -> Puntuaciones (1:N) | | |
| TASK-013 | Implementar comparadores y métodos de utilidad en modelos | | |

**Especificación de Tarea TASK-009:**
- Archivo: `/app/src/main/java/jc/dam/damiandice/db/PuntuacionRealm.kt`
- Clase: `PuntuacionRealm`
- Propiedades:
  - `_id: ObjectId` (clave primaria)
  - `usuarioId: String`
  - `puntuacion: Int`
  - `fecha: LocalDateTime`
  - `dispositivo: String`
  - `sincronizado: Boolean`
- Anotaciones: `@RealmClass`, `@PrimaryKey`

### Fase de Implementación 3: Autenticación con MongoDB Realm

**GOAL-003:** Implementar sistema de autenticación con MongoDB Realm (soporte para usuario anónimo y email).

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-014 | Crear clase `AuthService` para gestionar autenticación | | |
| TASK-015 | Implementar autenticación anónima con Realm | | |
| TASK-016 | Implementar opción de autenticación con email/contraseña | | |
| TASK-017 | Crear método para persistir token de sesión localmente | | |
| TASK-018 | Implementar manejo de sesiones expiradas | | |
| TASK-019 | Crear tests unitarios para AuthService | | |

**Especificación de Tarea TASK-014:**
- Archivo: `/app/src/main/java/jc/dam/damiandice/db/AuthService.kt`
- Métodos:
  - `loginAnonymously(): User` (suspend function)
  - `loginWithEmail(email: String, password: String): User`
  - `logout(): Unit`
  - `getCurrentUser(): User?`
  - `isAuthenticated(): Boolean`

### Fase de Implementación 4: Repository Remoto para MongoDB

**GOAL-004:** Crear capa de abstracción Repository para operaciones CRUD con MongoDB Realm.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-020 | Crear interfaz `PuntuacionRepository` | | |
| TASK-021 | Implementar `MongoDBPuntuacionRepository` | | |
| TASK-022 | Implementar métodos: obtenerTodasLasPuntuaciones() | | |
| TASK-023 | Implementar métodos: obtenerPuntuacionesDelUsuario(usuarioId) | | |
| TASK-024 | Implementar métodos: guardarPuntuacion(puntuacion) | | |
| TASK-025 | Implementar métodos: obtenerMejorPuntuacion() | | |
| TASK-026 | Implementar métodos: obtenerRankingGlobal(limit) | | |
| TASK-027 | Implementar manejo de sincronización offline/online | | |

**Especificación de Tarea TASK-020:**
- Archivo: `/app/src/main/java/jc/dam/damiandice/db/PuntuacionRepository.kt`
- Métodos principales:
  ```kotlin
  suspend fun obtenerTodasLasPuntuaciones(): List<PuntuacionRealm>
  suspend fun obtenerPuntuacionesDelUsuario(usuarioId: String): List<PuntuacionRealm>
  suspend fun guardarPuntuacion(puntuacion: PuntuacionRealm): Boolean
  suspend fun obtenerMejorPuntuacion(): Int
  fun obtenerRankingGlobal(limit: Int): Flow<List<PuntuacionRealm>>
  fun sincronizar(): Flow<SincronizacionEstado>
  ```

### Fase de Implementación 5: Integración con ViewModel Existente

**GOAL-005:** Actualizar `MyViewModel` para utilizar el nuevo Repository remoto y mantener funcionalidad actual.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-028 | Inyectar `PuntuacionRepository` en MyViewModel | | |
| TASK-029 | Crear LiveData/StateFlow para puntuación remota | | |
| TASK-030 | Implementar método `guardarPuntuacionEnMongoDB()` | | |
| TASK-031 | Implementar método `cargarMejorPuntuacionRemota()` | | |
| TASK-032 | Implementar método `obtenerRankingGlobal()` | | |
| TASK-033 | Añadir manejo de errores en MyViewModel | | |
| TASK-034 | Mantener fallback a datos locales en caso de error | | |
| TASK-035 | Crear tests unitarios actualizados para MyViewModel | | |

### Fase de Implementación 6: UI y Experiencia de Usuario

**GOAL-006:** Actualizar la UI para mostrar datos remotos y rankings globales.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-036 | Crear composable `RankingGlobalScreen()` | | |
| TASK-037 | Crear composable `MiPuntuacionRemotaCard()` | | |
| TASK-038 | Implementar indicador de estado de sincronización | | |
| TASK-039 | Añadir botón para sincronizar manualmente | | |
| TASK-040 | Crear modal de login (si se implementa autenticación) | | |
| TASK-041 | Añadir notificaciones de sincronización | | |

### Fase de Implementación 7: Pruebas y Validación

**GOAL-007:** Implementar suite de tests completa para la nueva funcionalidad.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-042 | Tests unitarios para AuthService | | |
| TASK-043 | Tests unitarios para MongoDBPuntuacionRepository | | |
| TASK-044 | Tests unitarios para MyViewModel (actualizado) | | |
| TASK-045 | Tests de integración para sincronización | | |
| TASK-046 | Tests de UI para nuevos composables | | |
| TASK-047 | Pruebas manuales de offline/online | | |

### Fase de Implementación 8: Despliegue y Documentación

**GOAL-008:** Finalizar implementación, documentar cambios y preparar para release.

| Tarea | Descripción | Completado | Fecha |
|------|-------------|-----------|------|
| TASK-048 | Documentar cambios en README.md | | |
| TASK-049 | Crear guía de configuración de MongoDB Atlas | | |
| TASK-050 | Actualizar versión en build.gradle.kts (1.1.0) | | |
| TASK-051 | Crear rama release/1.1 desde develop | | |
| TASK-052 | Realizar testing final en dispositivos reales | | |
| TASK-053 | Crear pull request y merge a main | | |

## 3. Alternativas Consideradas

- **ALT-001**: Usar Firebase Realtime Database en lugar de MongoDB Atlas
  - **Razón de rechazo**: MongoDB ofrece más flexibilidad, mejor soporte para Kotlin y Realm, y es más apropiada para datos estructurados como puntuaciones.

- **ALT-002**: Implementar backend personalizado (Node.js/Express) con MongoDB
  - **Razón de rechazo**: MongoDB Realm proporciona sincronización automática y manejo de conflictos, reduciendo complejidad del backend.

- **ALT-003**: Mantener solo sincronización manual
  - **Razón de rechazo**: La sincronización automática proporciona mejor experiencia de usuario y consistencia de datos.

- **ALT-004**: Reemplazar completamente SQLite/SharedPreferences con MongoDB Realm
  - **Razón de rechazo**: Mantener caché local permite funcionalidad offline y mejor rendimiento.

## 4. Dependencias

| ID | Descripción | Estado |
|---|---|---|
| **DEP-001** | MongoDB Realm Kotlin SDK (v1.15.0+) | Requerida |
| **DEP-002** | Kotlin Coroutines (v1.7.0+) | Requerida |
| **DEP-003** | Kotlin Flow | Requerida |
| **DEP-004** | AndroidX Lifecycle (ViewModel, LiveData) | Ya presente |
| **DEP-005** | Jetpack Compose | Ya presente |
| **DEP-006** | Room Database | Ya presente |
| **DEP-007** | JUnit 4 y Mockito | Ya presente |
| **DEP-008** | Cuenta activa en MongoDB Atlas | Requerida |

## 5. Archivos Afectados

| ID | Ruta | Descripción | Tipo |
|---|---|---|---|
| **FILE-001** | `/app/build.gradle.kts` | Añadir dependencias de Realm y Coroutines | Modificar |
| **FILE-002** | `/app/src/main/AndroidManifest.xml` | Añadir permisos de internet | Modificar |
| **FILE-003** | `/app/src/main/java/jc/dam/damiandice/db/PuntuacionRealm.kt` | Crear modelo de datos remoto | Crear |
| **FILE-004** | `/app/src/main/java/jc/dam/damiandice/db/UsuarioRealm.kt` | Crear modelo de usuario | Crear |
| **FILE-005** | `/app/src/main/java/jc/dam/damiandice/db/AuthService.kt` | Crear servicio de autenticación | Crear |
| **FILE-006** | `/app/src/main/java/jc/dam/damiandice/db/PuntuacionRepository.kt` | Crear interfaz repository | Crear |
| **FILE-007** | `/app/src/main/java/jc/dam/damiandice/db/MongoDBPuntuacionRepository.kt` | Implementar repository | Crear |
| **FILE-008** | `/app/src/main/java/jc/dam/damiandice/MyViewModel.kt` | Integrar repository remoto | Modificar |
| **FILE-009** | `/app/src/main/java/jc/dam/damiandice/ui/UI.kt` | Añadir pantalla de ranking | Modificar |
| **FILE-010** | `/app/src/test/java/jc/dam/damiandice/db/AuthServiceTest.kt` | Tests para AuthService | Crear |
| **FILE-011** | `/app/src/test/java/jc/dam/damiandice/db/MongoDBRepositoryTest.kt` | Tests para Repository | Crear |
| **FILE-012** | `/app/src/test/java/jc/dam/damiandice/MyViewModelTest.kt` | Tests actualizados para ViewModel | Modificar |
| **FILE-013** | `/README.md` | Documentar nueva funcionalidad | Modificar |
| **FILE-014** | `/docs/MONGODB_SETUP.md` | Crear guía de configuración | Crear |
| **FILE-015** | `/gradle/libs.versions.toml` | Actualizar versiones de dependencias | Modificar |

## 6. Testing

| ID | Descripción | Tipo | Prioridad |
|---|---|---|---|
| **TEST-001** | Autenticación anónima con Realm | Unitario | Alta |
| **TEST-002** | Autenticación con email/contraseña | Unitario | Alta |
| **TEST-003** | Obtener todas las puntuaciones | Unitario | Alta |
| **TEST-004** | Guardar puntuación en MongoDB | Unitario | Alta |
| **TEST-005** | Obtener mejor puntuación global | Unitario | Media |
| **TEST-006** | Obtener ranking global | Unitario | Media |
| **TEST-007** | Sincronización offline/online | Integración | Media |
| **TEST-008** | Manejo de errores de conexión | Integración | Media |
| **TEST-009** | Fallback a datos locales | Integración | Media |
| **TEST-010** | Pantalla de ranking se carga correctamente | UI | Baja |
| **TEST-011** | Indicador de sincronización funciona | UI | Baja |
| **TEST-012** | Funcionalidad de juego no se rompe | Integración | Crítica |

## 7. Riesgos & Supuestos

| ID | Tipo | Descripción | Mitigación |
|---|---|---|---|
| **RISK-001** | Técnico | Conflictos de sincronización entre local y remoto | Implementar resolución automática de conflictos con timestamp, preferir versión más reciente |
| **RISK-002** | Técnico | Pérdida de conectividad durante sincronización | Implementar queue de operaciones pendientes, reintentos automáticos |
| **RISK-003** | Técnico | Incompatibilidad de versiones de Realm | Usar versión LTS de Realm, realizar testing exhaustivo |
| **RISK-004** | Negocio | Costos de MongoDB Atlas escalables | Monitorear límites de M0 gratuito, establecer alertas |
| **RISK-005** | Seguridad | Exposición de credenciales de MongoDB | Almacenar credenciales en archivo de configuración excluido de git, usar variables de entorno |
| **RISK-006** | Técnico | Aumento de latencia por operaciones remotas | Implementar caché local agresivo, operaciones asincrónicas con UI feedback |
| **ASSUMPTION-001** | Técnico | El usuario tiene conexión a internet para sincronización | Implementar modo offline completo con fallback a datos locales |
| **ASSUMPTION-002** | Técnico | MongoDB Atlas estará disponible 99.99% del tiempo | Implementar mecanismo de reconexión automática y logs de disponibilidad |
| **ASSUMPTION-003** | Técnico | Se mantendrá backward compatibility con datos SQLite actuales | Realizar migración de datos SQLite a Realm en primer inicio |

## 8. Referencias y Lecturas Relacionadas

- [MongoDB Atlas Documentation](https://docs.atlas.mongodb.com/)
- [MongoDB Realm Kotlin SDK](https://www.mongodb.com/docs/realm/sdk/kotlin/)
- [Realm Android Sync](https://www.mongodb.com/docs/realm/sdk/kotlin/sync/)
- [Kotlin Coroutines Documentation](https://kotlinlang.org/docs/coroutines-overview.html)
- [Android Repository Pattern](https://developer.android.com/topic/architecture/data-layer)
- [Jetpack Compose State Management](https://developer.android.com/jetpack/compose/state)
- [MongoDB Realm Authentication](https://www.mongodb.com/docs/realm/sdk/kotlin/authentication/)

---

**Versión del Plan:** 1.0  
**Última Actualización:** 2026-01-14  
**Siguiente Revisión:** Después de completar GOAL-001

