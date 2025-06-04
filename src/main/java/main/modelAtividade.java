package main;

import java.time.LocalDate;

public class modelAtividade {
    private String nomeAtividade;
    private LocalDate inicioAtividade;
    private LocalDate fimAtividade;
    private String participantes;
    private String statusAtividade;

    //Constructor
    public modelAtividade(String nomeAtividade, LocalDate inicioAtividade, LocalDate fimAtividade, String participantes, String statusAtividade) {
        this.nomeAtividade = nomeAtividade;
        this.inicioAtividade = inicioAtividade;
        this.fimAtividade = fimAtividade;
        this.participantes = participantes;
        this.statusAtividade = statusAtividade;
    }

    // Getters
    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public LocalDate getInicioAtividade() {
        return inicioAtividade;
    }

    public LocalDate getFimAtividade() {
        return fimAtividade;
    }

    public String getParticipantes() {
        return participantes;
    }

    public String getStatusAtividade() {
        return statusAtividade;
    }

    // Setters
    public void setNomeAtividade(String nome) {
        this.nomeAtividade = nome;
    }

    public void setInicioAtividade(LocalDate inicio) {
        this.inicioAtividade = inicio;
    }

    public void setFimAtividade(LocalDate termino) {
        this.fimAtividade = termino;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public void setStatusAtividade(String status) {
        this.statusAtividade = status;
    }
}


