package main.models;

import java.time.LocalDate;

public class modelUser {
    private String nomeCompleto;
    private String cargo;
    private LocalDate dataNascimento;
    private double valorBolsa;
    private String escala;
    private String horarios;

    public modelUser(String nomeCompleto, String cargo, LocalDate dataNascimento, double valorBolsa, String escala, String horarios) {
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.valorBolsa = valorBolsa;
        this.escala = escala;
        this.horarios = horarios;
    }

    // Getters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public double getValorBolsa() {
        return valorBolsa;
    }

    public String getEscala() {
        return escala;
    }

    public String getHorarios() {
        return horarios;
    }

    // Setters
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setValorBolsa(double valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
}

