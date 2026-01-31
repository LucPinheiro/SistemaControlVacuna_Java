# SistemaControlVacuna_Java
# Práctica Vacunas 💉

Proyecto académico desarrollado en **Java** para la gestión de vacunas contra el COVID-19 encargado por la **Agencia Europea del Medicamento (EMA)**.  
El sistema permite registrar vacunas, gestionar las fases de investigación y decidir su **autorización o rechazo** siguiendo unas reglas de negocio bien definidas.

---

## 📌 Funcionalidades

El programa se ejecuta por consola y ofrece el siguiente menú de opciones:

1. Listar todas las vacunas y mostrar sus datos  
2. Buscar una vacuna  
3. Agregar una vacuna  
4. Eliminar una vacuna  
5. Introducir el resultado de las fases de investigación  
6. Autorizar o rechazar una vacuna  
7. Ver vacunas autorizadas  
8. Ver vacunas rechazadas  
9. Ver vacunas pendientes de autorización/rechazo  
10. Ver la última fase investigada de cada vacuna  

---

## 🗂️ Estructura del proyecto

Paquete único:


### Clases e interfaz

- **IAutorizable**  
  Interfaz que define el comportamiento de autorización:
  - `boolean autorizar()`
  - `boolean rechazar()`

- **VacunaAutorizacion (abstracta)**  
  Implementa `IAutorizable` y gestiona:
  - Fases de investigación (`fase1Superada`, `fase2Superada`, `fase3Superada`)
  - Número de fases completadas (`byte fasesCompletadas`)
  - Estado de la vacuna (`autorizada`, `rechazada`)
  - Fecha del resultado (`LocalDate fechaResultado`)

- **Vacuna (POJO)**  
  Representa una vacuna con los siguientes atributos:
  - código
  - nombre
  - principio activo
  - farmacéutica
  - precio recomendado  

- **VacAlmacen**  
  Gestiona una colección de vacunas y contiene la lógica necesaria para ejecutar las opciones del menú.

- **Aplicación**  
  Clase con el método `main()` que muestra el menú e interactúa con el usuario.

---

## ⚖️ Reglas de negocio

- Una vacuna **solo puede autorizarse** si:
  - Las **3 fases** han sido completadas (`fasesCompletadas == 3`)
  - Todas las fases han sido **superadas**
- La EMA puede **rechazar una vacuna** incluso si ha superado todas las fases.
- Una vacuna **no puede cambiar de estado**:
  - Si está rechazada, no puede autorizarse después.
  - Si está autorizada, no puede rechazarse después.
- La `fechaResultado`:
  - Solo se asigna al autorizar o rechazar
  - Se corresponde con la fecha actual del sistema
- Si `autorizada == false` y `rechazada == false`, la vacuna está **pendiente**.

---

## 🔢 Formato del código de vacuna

El código debe cumplir el siguiente patrón:

- Empieza por `V`
- Sigue una **vocal mayúscula**
- Continúa con **3 o 4 letras minúsculas**
- Finaliza con:
  - **dos números entre 4 y 7**, o
  - **un único número 8**

### Ejemplos válidos
- `VAedf45`
- `VEabc8`

### Ejemplos inválidos
- `vaedf45` (v minúscula)
- `VAbc123` (números incorrectos)
- `VXabc45` (X no es vocal)

---

## 🖨️ Ejemplos de salida

### Vacuna autorizada

### Datos de la vacuna

## 🛠️ Tecnologías utilizadas

- **Java**
- **Programación orientada a objetos (POO)**
- **Colecciones**
- **LocalDate** (API de fechas)

---

## ✍️ Autor

- **Nombre del alumno/a:**
Luciana Pinheiro**
- **Ciclo / Módulo:**
Ciclo Superior de Programación y Desarrollo Multiplataforma**

## 📄 Licencia

Proyecto desarrollado con fines **educativos**.


- **Código:** VAedf45  
- **Nombre:** COVID-19 vacuna Astrazeneca  
- **P. activo:** Adenovirus de chimpancé  
- **Farmacéutica:** AstraZeneca  
- **Precio:** 2.9 €
