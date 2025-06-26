package main.models;

import java.time.LocalDate;

public class modelUser {
    private int idUsuario;
    private String nome;
    private String cargo;
    private LocalDate dataNascimento;
    private double valorBolsa;
    private String escala;
    private String horario;
    private String matricula;

    public modelUser(int idUsuario, String nome, String cargo, LocalDate dataNascimento, double valorBolsa, String escala, String horario, String matricula) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
        this.valorBolsa = valorBolsa;
        this.escala = escala;
        this.horario = horario;
        this.matricula = matricula;
    }

    public modelUser() {

    }

    // Getters
    public int getIdUsuario() {return idUsuario;}

    public String getNome() {
        return nome;
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

    public String getHorario() {
        return horario;
    }

    public String getMatricula() { return matricula; }

    // Setters

    public void setIdUsuario(int id_usuario) {this.idUsuario = id_usuario;}

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setMatricula(String matricula) { this.matricula = matricula; }
}

