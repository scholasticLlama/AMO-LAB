package com.labs.labwork3;

public class Error {
    private int n;
    private Double deltaN;
    private Double deltaExactN;
    private Double k;

    public Error(int n, Double deltaN, Double deltaExactN, Double k)
    {
        super();
        this.n = n;
        this.deltaN = deltaN;
        this.deltaExactN = deltaExactN;
        this.k = k;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Double getDeltaN() {
        return deltaN;
    }

    public void setDeltaN(Double deltaN) {
        this.deltaN = deltaN;
    }

    public Double getDeltaExactN() {
        return deltaExactN;
    }

    public void setDeltaExactN(Double deltaExactN) {
        this.deltaExactN = deltaExactN;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }
}
