# El Taller de Santa

## ¿Qué es este proyecto?

Este es un programa que permite gestionar todo lo relacionado con la distribución de regalos.
- Los regalos disponibles en el inventario
- Los niños que recibirán regalos
- Qué regalo le corresponde a cada niño
- Reportes para ver el estado de todo

## Características principales

### Gestión de Usuarios
- Registro de nuevos usuarios
- Inicio de sesión seguro
- Control de acceso al sistema

### Gestión de Regalos
- Registrar nuevos regalos con código, nombre, descripción y marca
- Modificar información de regalos existentes
- Eliminar regalos (si no están asignados)
- Reabastecer el inventario cuando lleguen más regalos
- Consultar detalles de cualquier regalo

### Gestión de Niños
- Registrar niños con su información completa (identificación, nombre, edad, ciudad, dirección)
- Modificar datos de niños registrados
- Eliminar registros (si no tienen regalo asignado)
- Buscar niños por su identificación

### Asignación de Regalos
- Asignar un regalo específico a un niño
- Ver qué regalo tiene asignado cada niño
- El sistema verifica que haya disponibilidad antes de asignar

### Reportes
El sistema puede generar varios reportes útiles:
1. **Inventario actual**: Lista todos los regalos disponibles con sus cantidades
2. **Listado de niños**: Muestra todos los niños registrados con su información
3. **Asignaciones completas**: Detalle de qué regalo tiene cada niño
4. **Niños sin regalo**: Lista de niños que aún no tienen regalo asignado
5. **Regalos por marca**: Filtra y muestra regalos de una marca específica (se guarda en archivo)

## ¿Cómo usar el programa?

### Requisitos previos
- Java instalado en tu computadora (versión 17 o superior)
- Maven (para compilar el proyecto)

### Pasos para ejecutar

1. **Abrir el programa**
   - Ejecuta el archivo principal `ElTallerDeSanta.java`
   - Se ve un menú de bienvenida

2. **Registrarse o iniciar sesión**
   - Primero necesita crear una cuenta (opción 1)
   - O iniciar sesión si ya hay una cuenta (opción 2)

3. **Usar el sistema**
   - Una vez dentro, se puede acceder a todas las funciones de gestión
   - El menú te guia paso a paso

4. **Guardar información**
   - Todos los datos se guardan automáticamente en archivos JSON
   - No se pierde informacion ya que se guarda en JSON

## Estructura del proyecto

El proyecto está organizado de manera clara:

- **models/**: Contiene las "plantillas" de datos (Usuario, Regalo, Nino, Asignacion)
- **management/**: Los gestores que se encargan de las operaciones principales
- **utilities/**: Herramientas auxiliares (validaciones, manejo de archivos)
- **reports/**: Generador de reportes
- **data/**: Carpeta donde se guardan todos los datos del sistema

## Almacenamiento de datos

Todos los datos se guardan automáticamente en archivos JSON dentro de la carpeta `data/`:
- `usuarios.json`: Información de usuarios
- `regalos.json`: Inventario de regalos
- `ninos.json`: Registro de niños
- `asignaciones.json`: Asignaciones de regalos a niños

Se puede cerrar el programa y cuando se vuelva a abrir, toda la información estará ahí.

## Casos de uso

### Ejemplo 1: Registrar un nuevo regalo
1. Inicia sesión en el sistema
2. Ve a "Gestion del Sistema" → "Gestion de Regalos" → "Registrar Regalo"
3. Ingresa el código, nombre, descripción, marca y cantidad
4. El regalo queda registrado en el inventario

### Ejemplo 2: Asignar un regalo a un niño
1. Verificar de tener el niño y el regalo registrados
2. Ir a "Asignacion de Regalos" → "Asignar Regalo a Nino"
3. Ingresar la identificación del niño y el código del regalo
4. El sistema verifica que todo esté correcto y asigna el regalo

### Ejemplo 3: Ver qué niños no tienen regalo
1. Ir a "Reportes" → "Ninos Registrados Sin Regalo Asignado"
2. El sistema mostrará una lista de todos los niños que aún necesitan un regalo

## Seguridad

- Solo usuarios registrados pueden acceder a las funciones de gestión
- Cada usuario debe iniciar sesión para usar el sistema
- Los datos están protegidos y solo se pueden modificar desde el programa

## Notas importantes

- No se puede eliminar un regalo si ya está asignado a un niño
- No se puede eliminar un niño si ya tiene un regalo asignado
- La edad de los niños debe estar entre 0 y 18 años
- Todos los campos de texto son obligatorios


Este sistema está diseñado para hacer más fácil la tarea de organizar los regalos.

---

**Desarrollado por Josué Porfirio Salazar López**