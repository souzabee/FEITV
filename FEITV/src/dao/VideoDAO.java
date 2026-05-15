package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Video;
import model.Filme;

public class VideoDAO {
    private Connection conn;

    public VideoDAO(Connection conn) {
        this.conn = conn;
    }

    public Video buscarPorNome(String nome) throws Exception {
        String texto = nome.replace("\n", "").replace("\r", "").trim();

        String sql = "SELECT * FROM tbvideos WHERE LOWER(TRIM(nome)) LIKE LOWER(TRIM(?)) LIMIT 1";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + texto + "%");

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Video video = new Filme();
        video.setId(rs.getInt("id"));
        video.setTitulo(rs.getString("nome"));
        video.setDescricao(rs.getString("descricao"));
        video.setGenero(rs.getString("genero"));
        video.setAno(rs.getInt("ano"));

        return video;
    }
}