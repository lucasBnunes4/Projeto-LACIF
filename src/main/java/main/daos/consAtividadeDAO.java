package main.daos;

import main.models.modelAtividade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class consAtividadeDAO {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/farmhub";
        String user = "root";
        String password = "Lbn26827503";
        return DriverManager.getConnection(url, user, password);
    }

    public List<modelAtividade> buscarPorNome(String nome) {
        List<modelAtividade> atividades = new ArrayList<>();

        String sql = "SELECT * FROM atividades WHERE nome LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelAtividade atividade = new modelAtividade(
                        rs.getString("nome"),
                        rs.getDate("inicio").toLocalDate(),
                        rs.getDate("termino").toLocalDate(),
                        rs.getString("participantes"),
                        rs.getString("status")
                );
                atividades.add(atividade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar atividades: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Buscando por nome: " + nome);
        return atividades;
    }

    public boolean registrarAtividade(modelAtividade atividade) {
        String sql = "INSERT INTO atividades (nome, inicio, termino, participantes, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, atividade.getNomeAtividade());
            stmt.setDate(2, Date.valueOf(atividade.getInicioAtividade()));
            stmt.setDate(3, Date.valueOf(atividade.getFimAtividade()));
            stmt.setString(4, atividade.getParticipantes());
            stmt.setString(5, atividade.getStatusAtividade());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao registrar atividade: " + e.getMessage());
            return false;
        }
    }
}