package com.proyecto.serviciocalculo.service;

import com.proyecto.serviciocalculo.dto.CalculationResponse;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class CalculoService {
    private final int N_THREADS = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

    public CalculationResponse multiplyMatrices(double[][] a, double[][] b) {
        long startTime = System.currentTimeMillis();

        if (a[0].length != b.length) {
            return new CalculationResponse(null, 0, 0, "Error: Las dimensiones de las matrices no son compatibles para la multiplicación.");
        }

        int rowsA = a.length;
        int colsB = b[0].length;
        double[][] result = new double[rowsA][colsB];
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < rowsA; i++) {
            final int row = i;
            // Creamos una tarea (Callable) para cada fila
            Callable<Void> task = () -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        result[row][j] += a[row][k] * b[k][j];
                    }
                }
                return null;
            };
            futures.add(executor.submit(task));
        }

        // Esperamos a que todos los hilos terminen
        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                return new CalculationResponse(null, 0, N_THREADS, "Error durante la ejecución paralela: " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        return new CalculationResponse(result, endTime - startTime, N_THREADS, "Multiplicación completada exitosamente.");
    }


    //Integración por trapecio
    public CalculationResponse integrate(String function, double start, double end, int intervals) {
        long startTime = System.currentTimeMillis();

        double deltaX = (end - start) / intervals;
        List<Future<Double>> futures = new ArrayList<>();
        int intervalsPerThread = intervals / N_THREADS;

        for (int i = 0; i < N_THREADS; i++) {
            final int threadIndex = i;
            // Creamos una tarea para cada bloque de intervalos
            Callable<Double> task = () -> {
                double localSum = 0;
                int startInterval = threadIndex * intervalsPerThread;
                int endInterval = (threadIndex == N_THREADS - 1) ? intervals : startInterval + intervalsPerThread;

                Expression expression = new ExpressionBuilder(function).variable("x").build();

                for (int j = startInterval; j < endInterval; j++) {
                    double x1 = start + j * deltaX;
                    double x2 = start + (j + 1) * deltaX;
                    double y1 = expression.setVariable("x", x1).evaluate();
                    double y2 = expression.setVariable("x", x2).evaluate();
                    localSum += (y1 + y2) / 2.0 * deltaX;
                }
                return localSum;
            };
            futures.add(executor.submit(task));
        }

        double totalArea = 0;
        // Esperamos y sumamos los resultados parciales de cada hilo
        for (Future<Double> future : futures) {
            try {
                totalArea += future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                return new CalculationResponse(null, 0, N_THREADS, "Error durante la ejecución paralela: " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        return new CalculationResponse(totalArea, endTime - startTime, N_THREADS, "Integración completada exitosamente.");
    }
}
