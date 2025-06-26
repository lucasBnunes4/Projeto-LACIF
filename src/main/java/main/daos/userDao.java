package main.daos;

import main.models.modelUser;
import java.sql.*;
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
        String sql = "INSERT INTO usuarios (nome, cargo, data_nascimento, valor_bolsa, escala, horario, matricula) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCargo());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setDouble(4, usuario.getValorBolsa());
            stmt.setString(5, usuario.getEscala());
            stmt.setString(6, usuario.getHorario());
            stmt.setString(7, usuario.getMatricula());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            return false;
        }
    }

    public List<modelUser> buscarPorNome(String matricula) {
        List<modelUser> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE matricula LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + matricula + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelUser usuario = new modelUser(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cargo"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getDouble("valor_bolsa"),
                        rs.getString("escala"),
                        rs.getString("horario"),
                        rs.getString("matricula")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public modelUser consultarUsuarioPorMatricula(String matricula) throws SQLException {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new SQLException("Matrícula inválida");
        }

        String sql = "SELECT * FROM usuarios WHERE matricula = ?";
        try (Connection conn = conexaoDb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                modelUser usuario = new modelUser();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setMatricula(rs.getString("matricula"));
                // Outros campos conforme necessário
                return usuario;
            }
            return null; // Usuário não encontrado
        }
    }
}
