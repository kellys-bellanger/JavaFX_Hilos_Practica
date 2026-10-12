package ni.edu.uam.practicahilosjavafx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProcesadorController {

    // Controles de Clientes
    @FXML private Label lblEstadoClientes;
    @FXML private Label lblPorcentajeClientes;
    @FXML private ProgressBar barraClientes;
    @FXML private Button btnIniciarClientes;
    @FXML private Button btnDetenerClientes;

    // Controles de Productos
    @FXML private Label lblEstadoProductos;
    @FXML private Label lblPorcentajeProductos;
    @FXML private ProgressBar barraProductos;
    @FXML private Button btnIniciarProductos;
    @FXML private Button btnDetenerProductos;

    private Thread hiloClientes;
    private Thread hiloProductos;

    @FXML
    private void iniciarClientes() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            return;
        }

        barraClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Procesando clientes...");

        btnIniciarClientes.setDisable(true);
        btnDetenerClientes.setDisable(false);

        Runnable tareaClientes = () -> {
            try {
                for (int i = 1; i <= 100; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(100); // 100 ms por registro (~10 segundos)
                    final int avance = i;

                    Platform.runLater(() -> {
                        barraClientes.setProgress(avance / 100.0);
                        lblPorcentajeClientes.setText(avance + "%");
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoClientes.setText("Clientes finalizados");
                    btnIniciarClientes.setDisable(false);
                    btnDetenerClientes.setDisable(true);
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstadoClientes.setText("Clientes detenidos");
                    btnIniciarClientes.setDisable(false);
                    btnDetenerClientes.setDisable(true);
                });
            }
        };

        hiloClientes = new Thread(tareaClientes);
        hiloClientes.setName("Hilo-Clientes");
        hiloClientes.setDaemon(true);
        hiloClientes.start();
    }

    @FXML
    private void detenerClientes() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            btnDetenerClientes.setDisable(true);
            lblEstadoClientes.setText("Solicitando detención...");
            hiloClientes.interrupt();
        }
    }

    @FXML
    private void iniciarProductos() {
        if (hiloProductos != null && hiloProductos.isAlive()) {
            return;
        }

        barraProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Procesando productos...");

        btnIniciarProductos.setDisable(true);
        btnDetenerProductos.setDisable(false);

        Runnable tareaProductos = () -> {
            try {
                for (int i = 1; i <= 50; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(150); // 150 ms por registro (~7.5 segundos) - tiempo diferente
                    final int avance = i;

                    Platform.runLater(() -> {
                        barraProductos.setProgress(avance / 50.0);
                        lblPorcentajeProductos.setText(avance + "%");
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoProductos.setText("Productos finalizados");
                    btnIniciarProductos.setDisable(false);
                    btnDetenerProductos.setDisable(true);
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                Platform.runLater(() -> {
                    lblEstadoProductos.setText("Productos detenidos");
                    btnIniciarProductos.setDisable(false);
                    btnDetenerProductos.setDisable(true);
                });
            }
        };

        hiloProductos = new Thread(tareaProductos);
        hiloProductos.setName("Hilo-Productos");
        hiloProductos.setDaemon(true);
        hiloProductos.start();
    }

    @FXML
    private void detenerProductos() {
        if (hiloProductos != null && hiloProductos.isAlive()) {
            btnDetenerProductos.setDisable(true);
            lblEstadoProductos.setText("Solicitando detención...");
            hiloProductos.interrupt();
        }
    }

    public void detenerAlCerrar() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            hiloClientes.interrupt();
        }
        if (hiloProductos != null && hiloProductos.isAlive()) {
            hiloProductos.interrupt();
        }
    }
}