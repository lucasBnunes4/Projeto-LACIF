package main.models;

public class modelMaterial {
    private int id;
    private String nomeMaterial;
    private String tipoMaterial;
    private boolean disponivel;
    private int ultimaMovimentacaoId;

    // contrutor
    public modelMaterial() {
    }

    public modelMaterial(String nomeMaterial, String tipoMaterial) {
        this.nomeMaterial = nomeMaterial;
        this.tipoMaterial = tipoMaterial;
        this.disponivel = true;
    }

    // get e set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getUltimaMovimentacaoId() {
        return ultimaMovimentacaoId;
    }

    public void setUltimaMovimentacaoId(int ultimaMovimentacaoId) {
        this.ultimaMovimentacaoId = ultimaMovimentacaoId;
    }
}