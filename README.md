# Práctica de Hilos y Concurrencia en JavaFX 

Aplicación de escritorio desarrollada en **JavaFX** como parte de la Unidad III: Programación Concurrente para **NicaData Solutions S.A.**

## Objetivos de la Práctica
- Implementar hilos utilizando `Thread` y `Runnable`.
- Mantener la interfaz gráfica receptiva (`JavaFX Application Thread`) durante la ejecución de procesos pesados en segundo plano mediante `Platform.runLater()`.
- Gestionar el ciclo de vida y la detención controlada de los hilos utilizando `interrupt()` e `isInterrupted()`.
- Desarrollar un monitor de procesamiento concurrente con múltiples hilos y barras de progreso independientes (Reto Final).

## ¿Cómo funcionan los hilos en esta aplicación?

• Interfaz siempre fluida (Platform.runLater): Para evitar que la pantalla se congele o lance errores raros de concurrencia, dejamos que el hilo principal de JavaFX se encargue exclusivamente de la parte visual. Cada vez que la barra de progreso avanza o cambia un texto, lo mandamos a la cola visual de forma segura.
• Trabajo en paralelo (El Reto Final): El procesamiento pesado se divide en dos tareas que corren al mismo tiempo sin estorbarse:
	• Hilo de Clientes: Va procesando 100 registros uno a uno, con una pequeña pausa de 100 ms entre ellos (tarda unos 10 segundos en total).
	• Hilo de Productos: Procesa 50 registros, pero con una pausa de 150 ms por cada uno (tarda unos 7.5 segundos en total).
• Parada de emergencia limpia: Si el usuario decide pulsar el botón "Detener", no "matamos" el proceso a la fuerza. En su lugar, el sistema avisa a los hilos que deben parar (isInterrupted()). Ellos capturan el aviso, guardan el estado actual de forma segura y liberan los botones de la interfaz de inmediato.

<img width="509" height="382" alt="Screenshot 2026-10-12 172435" src="https://github.com/user-attachments/assets/77dbcceb-9d6d-4f55-9745-ce985159a886" />

<img width="410" height="356" alt="Screenshot 2026-10-12 172507" src="https://github.com/user-attachments/assets/265ad337-d3ae-454c-8cee-ad70626e3224" />

<img width="400" height="275" alt="Screenshot 2026-10-12 172522" src="https://github.com/user-attachments/assets/9336fdef-e042-47a6-953b-4fcc8daf6230" />


---

## Estructura del Proyecto
```text
PracticaHilosJavaFX/
│
├── src/main/java/ni/edu/uam/practicahilosjavafx/
│   ├── MainApp.java
│   └── controllers/
│       └── ProcesadorController.java
│
├── src/main/resources/ni/edu/uam/practicahilosjavafx/
│   └── procesador-view.fxml
│
└── pom.xml


