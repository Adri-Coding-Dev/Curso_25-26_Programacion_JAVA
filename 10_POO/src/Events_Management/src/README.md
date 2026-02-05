# 📅 Proyecto – Gestor de Eventos con Login (Java)

Aplicación de consola desarrollada en Java que permite **gestionar eventos** (presenciales y online) mediante **Programación Orientada a Objetos**, protegida por un **sistema de login con control de usuarios**.

Este proyecto corresponde a una **práctica evaluable** del módulo de **Programación** del ciclo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

---

## 🎯 Objetivo del proyecto

Crear una aplicación que combine:

- POO (clases, herencia, abstracción)
- Interfaces
- Estructuras de datos (ArrayList y HashMap)
- Gestión de usuarios con login
- Validación de contraseñas mediante Regex
- Manejo de excepciones personalizadas
- Fechas con `LocalDateTime`

Todo ello integrado en una aplicación funcional de consola.

---

## 🔐 Sistema de login

El programa no permite acceder al menú sin un login válido.

Los usuarios se almacenan en:

```java
HashMap<String, String> usuarios
Donde:

Clave → nombre de usuario

Valor → contraseña

Usuarios iniciales
```

El sistema arranca con al menos:

Usuario Rol
admin Administrador
alumno Usuario estándar

Solo el usuario admin puede crear nuevos usuarios.

Se permiten máximo 3 intentos de login fallidos.
Si se superan, el programa se cierra.

🧑‍💼 Creación de usuarios (solo admin)
Cuando el administrador crea un nuevo usuario, la contraseña debe cumplir:

Longitud entre 8 y 12 caracteres

Contener solo letras

Contener al menos un símbolo de: ! @ # $ % & \*

No puede contener números

Se valida mediante Regex.

Si la contraseña no cumple:

Se lanza una excepción personalizada:

```java
PasswordDebilException extends Exception
```

Y el sistema muestra un mensaje claro sin cerrarse.

📋 Menú principal
Tras un login correcto, el usuario accede a este menú:

```markdown
1. Listar todos los eventos
2. Listar solo eventos futuros
3. Añadir evento presencial
4. Añadir evento online
5. Borrar evento por nombre
6. (Solo admin) Crear nuevo usuario
7. Salir
```

Si un usuario normal intenta usar la opción 6:

"Acceso denegado: solo admin puede crear usuarios."

🗂️ Gestión de eventos
Los eventos se almacenan en:

```java
ArrayList<Evento> eventos
```

Cada evento tiene:

Nombre

Fecha y hora (LocalDateTime)

Tipo (presencial u online)

El sistema permite:

Añadir eventos

Listarlos todos

Listar solo los futuros

Borrarlos por nombre

🧱 Diseño POO
📌 Interfaz

```java
public interface Mostrable {
    void mostrarInfo();
}
```

📌 Clase abstracta

```java
abstract class Evento
```

Atributos:

String nombre

LocalDateTime fechaHora

Métodos:

Constructor completo

Getters

boolean esFuturo()

Implementación o uso forzado de mostrarInfo()

📌 Clases hijas
EventoPresencial

```java
class EventoPresencial extends Evento
```

Atributo:

String aula

EventoOnline

```java
class EventoOnline extends Evento
```

Atributo:

String plataforma

Ambas sobrescriben el método mostrarInfo() para mostrar los datos específicos.

⏰ Control de fechas
Cada evento compara su fecha con:

```java
LocalDateTime.now()
```

El método esFuturo() determina si el evento ya ocurrió o está por realizarse.
Esta información se muestra al listar los eventos.

🛠️ Tecnologías y conceptos usados
Java

Programación Orientada a Objetos

Herencia

Interfaces

Excepciones personalizadas

Regex

LocalDateTime

ArrayList

HashMap

Aplicación de consola

📌 Estado del proyecto
✔️ Completado
📘 Proyecto académico evaluable

✍️ Autor
Alumno de DAM – Módulo de Programación
Curso 2024 – 2026

📎 Notas
Este proyecto no solo busca que el programa funcione, sino que el código esté:

Bien estructurado

Modularizado

Comentado

Orientado a buenas prácticas

Refleja un uso real de POO aplicada a un problema concreto.
