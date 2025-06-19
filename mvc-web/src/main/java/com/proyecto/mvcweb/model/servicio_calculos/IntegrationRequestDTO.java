    package com.proyecto.mvcweb.model.servicio_calculos;


    public class IntegrationRequestDTO {
        private String function;
        private double start;
        private double end;
        private int intervals;

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public double getStart() {
            return start;
        }

        public void setStart(double start) {
            this.start = start;
        }

        public double getEnd() {
            return end;
        }

        public void setEnd(double end) {
            this.end = end;
        }

        public int getIntervals() {
            return intervals;
        }

        public void setIntervals(int intervals) {
            this.intervals = intervals;
        }

        // Getters y setters
    }

