package main.daos;

import main.models.modelUser;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/farmhub";
        String user = "root";
        String password = "Lbn26827503";
        return DriverManager.getConnection(url, user, password);
    }

    public boolean registrarUsuario(modelUser usuario) {
        String sql = "INSERT INTO usuarios (nome, cargo, data_nascimento, valor_bolsa, escala, horario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getCargo());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setDouble(4, usuario.getValorBolsa());
            stmt.setString(5, usuario.getEscala());
            stmt.setString(6, usuario.getHorarios());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            return false;
        }
    }

    public List<modelUser> buscarPorNome(String nome) {
        List<modelUser> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nome LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelUser usuario = new modelUser(
                        rs.getString("nome"),
                        rs.getString("cargo"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getDouble("valor_bolsa"),
                        rs.getString("escala"),
                        rs.getString("horarios")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return usuarios;
    }
}
