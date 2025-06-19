package com.proyecto.serviciocalculo.dto;

public class CalculationResponse {
    private Object result;
    private long executionTimeMillis;
    private int threadsUsed;
    private String message;

    public CalculationResponse() {
    }

    public CalculationResponse(Object result, long executionTimeMillis, int threadsUsed, String message) {
        this.result = result;
        this.executionTimeMillis = executionTimeMillis;
        this.threadsUsed = threadsUsed;
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public void setExecutionTimeMillis(long executionTimeMillis) {
        this.executionTimeMillis = executionTimeMillis;
    }

    public int getThreadsUsed() {
        return threadsUsed;
    }

    public void setThreadsUsed(int threadsUsed) {
        this.threadsUsed = threadsUsed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
