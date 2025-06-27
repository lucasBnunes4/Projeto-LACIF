package main.models;

import java.time.LocalDate;

public class modelAmostras {
    private int idAmostra;
    private String tipoAmostra;
    private Double pesoNatural;
    private Double pesoSeco;
    private LocalDate dataCorte;
    private LocalDate dataPesagem;
    private int parcela;
    private String tratamento;
    private Double pesoTotal;

    public modelAmostras(int idAmostra, String tipoAmostra, Double pesoNatural, Double pesoSeco, LocalDate dataCorte, LocalDate dataPesagem, int parcela, String tratamento, Double pesoTotal) {
        this.idAmostra = idAmostra;
        this.tipoAmostra = tipoAmostra;
        this.pesoNatural = pesoNatural;
        this.pesoSeco = pesoSeco;
        this.dataCorte = dataCorte;
        this.dataPesagem = dataPesagem;
        this.parcela = parcela;
        this.tratamento = tratamento;
        this.pesoTotal = pesoTotal;
    }

    public modelAmostras() {

    }

    // Getters

    public int getIdAmostra() {
        return idAmostra;
    }

    public String getTipoAmostra() {
        return tipoAmostra;
    }

    public Double getPesoNatural() {
        return pesoNatural;
    }

    public Double getPesoSeco() {
        return pesoSeco;
    }

    public LocalDate getDataCorte() {
        return dataCorte;
    }

    public LocalDate getDataPesagem() {
        return dataPesagem;
    }

    public int getParcela() {
        return parcela;
    }

    public String getTratamento() {
        return tratamento;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }
    // Setters

    public void setIdAmostra(int idAmostra) {
        this.idAmostra = idAmostra;
    }

    public void setTipoAmostra(String tipoAmostra) {
        this.tipoAmostra = tipoAmostra;
    }

    public void setPesoNatural(Double pesoNatural) {
        this.pesoNatural = pesoNatural;
    }

    public void setPesoSeco(Double pesoSeco) {
        this.pesoSeco = pesoSeco;
    }

    public void setDataCorte(LocalDate dataCorte) {
        this.dataCorte = dataCorte;
    }

    public void setDataPesagem(LocalDate dataPesagem) {
        this.dataPesagem = dataPesagem;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }
}
