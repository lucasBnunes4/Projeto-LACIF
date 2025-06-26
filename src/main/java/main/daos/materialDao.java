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

public class materialDao {
    private Connection conexao;

    public materialDao() {
        try {
            this.conexao = conexaoDb.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adicionar novo material
    public void adicionarMaterial(modelMaterial material) throws SQLException {
        String sql = "INSERT INTO materiais (nome_material, tipo_material) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setString(2, material.getTipoMaterial());
            stmt.execute();
        }
    }

    // Consultar material por nome
    public modelMaterial consultarMaterial(String nomeMaterial) throws SQLException {
        String sql = "SELECT * FROM materiais WHERE nome_material = ?";
        modelMaterial material = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeMaterial);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                material = new modelMaterial();
                material.setId(rs.getInt("id"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setTipoMaterial(rs.getString("tipo_material"));
                material.setDisponivel(rs.getBoolean("disponivel"));
                material.setUltimaMovimentacaoId(rs.getInt("ultima_movimentacao_id"));
            }
        }
        return material;
    }

    // Verificar quem está com o material
    public modelUser consultarUsuarioComMaterial(int idMaterial) throws SQLException {
        String sql = "SELECT u.* FROM usuarios u " +
                "JOIN movimentacoes_materiais mm ON u.id_usuario = mm.id_usuario " +
                "WHERE mm.id_material = ? AND mm.tipo_movimentacao = 'Retirada' AND mm.status = 'Ativa'";

        modelUser usuario = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new modelUser();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setMatricula(rs.getString("matricula"));
                // Adicione outros campos conforme necessário
            }
        }
        return usuario;
    }

    // Retirar material
    public void retirarMaterial(int idMaterial, String matricula, LocalDate dataPrevistaDevolucao) throws SQLException {
        // Validação adicional
        if (idMaterial <= 0 || matricula == null || matricula.trim().isEmpty() || dataPrevistaDevolucao == null) {
            throw new SQLException("Parâmetros inválidos para retirada");
        }

        // Verifica se a data é futura
        if (dataPrevistaDevolucao.isBefore(LocalDate.now())) {
            throw new SQLException("Data de devolução deve ser futura");
        }

        Connection conn = null;
        try {
            conn = conexaoDb.getConnection();
            conn.setAutoCommit(false);

            // 1. Verifica usuário
            userDao usuarioDAO = new userDao();
            modelUser usuario = usuarioDAO.consultarUsuarioPorMatricula(matricula);
            if (usuario == null) {
                throw new SQLException("Usuário não encontrado");
            }

            // 2. Verifica material
            modelMaterial material = this.consultarMaterialPorId(idMaterial);
            if (material == null) {
                throw new SQLException("Material não encontrado");
            }
            if (!material.isDisponivel()) {
                throw new SQLException("Material já está em uso");
            }

            // 3. Registra movimentação
            String sqlMov = "INSERT INTO movimentacoes_materiais " +
                    "(id_material, id_usuario, matricula_usuario, tipo_movimentacao, " +
                    "data_movimentacao, status) VALUES (?, ?, ?, 'Retirada', ?, 'Ativa')";

            try (PreparedStatement stmtMov = conn.prepareStatement(sqlMov,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                stmtMov.setInt(1, idMaterial);
                stmtMov.setInt(2, usuario.getIdUsuario());
                stmtMov.setString(3, matricula);
                stmtMov.setDate(4, java.sql.Date.valueOf(dataPrevistaDevolucao));

                int affectedRows = stmtMov.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Falha ao registrar movimentação");
                }

                // 4. Atualiza status do material
                try (ResultSet generatedKeys = stmtMov.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        String sqlUpdate = "UPDATE materiais SET disponivel = FALSE, " +
                                "ultima_movimentacao_id = ? WHERE id = ?";

                        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                            stmtUpdate.setInt(1, generatedKeys.getInt(1));
                            stmtUpdate.setInt(2, idMaterial);
                            stmtUpdate.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    // Devolver material
    public void devolverMaterial(int idMaterial) throws SQLException {
        // Verifica se o material está emprestado
        modelMaterial material = this.consultarMaterialPorId(idMaterial);
        if (material == null) {
            throw new SQLException("Material não encontrado");
        }
        if (material.isDisponivel()) {
            throw new SQLException("Material já está disponível");
        }

        // Inicia transação
        conexao.setAutoCommit(false);

        try {
            // Atualiza a movimentação de retirada para finalizada
            String sqlMov = "UPDATE movimentacoes_materiais " +
                    "SET tipo_movimentacao = 'Devolução',  status = 'Finalizada' " +
                    "WHERE id_movimentacao = ?";

            try (PreparedStatement stmtMov = conexao.prepareStatement(sqlMov)) {
                stmtMov.setInt(1, material.getUltimaMovimentacaoId());
                stmtMov.executeUpdate();
            }

            // Atualiza o material para disponível
            String sqlMat = "UPDATE materiais SET disponivel = TRUE WHERE id = ?";
            try (PreparedStatement stmtMat = conexao.prepareStatement(sqlMat)) {
                stmtMat.setInt(1, idMaterial);
                stmtMat.executeUpdate();
            }

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(true);
        }
    }

    // Método auxiliar para consultar material por ID
    private modelMaterial consultarMaterialPorId(int id) throws SQLException {
        String sql = "SELECT * FROM materiais WHERE id = ?";
        modelMaterial material = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                material = new modelMaterial();
                material.setId(rs.getInt("id"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setTipoMaterial(rs.getString("tipo_material"));
                material.setDisponivel(rs.getBoolean("disponivel"));
                material.setUltimaMovimentacaoId(rs.getInt("ultima_movimentacao_id"));
            }
        }
        return material;
    }
}
