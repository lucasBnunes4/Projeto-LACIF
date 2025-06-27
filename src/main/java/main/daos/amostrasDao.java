package main.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.models.*;
import main.daos.*;
import java.util.*;
import java.sql.Date;

public class amostrasDao {
    private Connection conexao;

    public amostrasDao() {
        try {
            this.conexao = conexaoDb.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registrarAmostra(modelAmostras  amostras) {
        String sql = "INSERT INTO amostras (tipo, peso_natural, peso_seco, data_corte, data_pesagem, parcela, tratamento, peso_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = main.daos.conexaoDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, amostras.getTipoAmostra());
            stmt.setDouble(2, amostras.getPesoNatural());
            stmt.setDouble(3, amostras.getPesoSeco());
            stmt.setDate(4, Date.valueOf(amostras.getDataCorte()));
            stmt.setDate(5, Date.valueOf(amostras.getDataPesagem()));
            stmt.setInt(6, amostras.getParcela());
            stmt.setString(7, amostras.getTratamento());
            stmt.setDouble(8, amostras.getPesoTotal());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Errp ao registrar amostra: " + e.getMessage());
            return false;
        }
    }

    public List<modelAmostras> buscarAmostra(String tipoAmostra) {
        List<modelAmostras> amostras = new ArrayList<>();
        String sql = "SELECT * FROM amostras WHERE tipo LIKE ?";
        try (Connection conn = main.daos.conexaoDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tipoAmostra + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelAmostras amostra = new modelAmostras(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getDouble("peso_natural"),
                        rs.getDouble("peso_seco"),
                        rs.getDate("data_corte").toLocalDate(),
                        rs.getDate("data_pesagem").toLocalDate(),
                        rs.getInt("parcela"),
                        rs.getString("tratamento"),
                        rs.getDouble("peso_total")
                );
                amostras.add(amostra);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar amostra: " + e.getMessage());
        }
        return amostras;
    }
}
