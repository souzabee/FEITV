package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Video;
import model.Filme;
        
public class PlaylistDAO {
    private Connection conn;

    public PlaylistDAO(Connection conn) {
        this.conn = conn;
    }

    public void criarPlaylist(String nome, int idUsuario) throws Exception {
        String sql = "INSERT INTO tbplaylists (nome, id_usuario) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
        }
    }

    public List<String> listarPlaylistsDoUsuario(int idUsuario) throws Exception {
        List<String> playlists = new ArrayList<>();
        String sql = "SELECT nome FROM tbplaylists WHERE id_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    playlists.add(rs.getString("nome"));
                }
            }
        }

        return playlists;
    }

    public int buscarIdPlaylist(String nomePlaylist, int idUsuario) throws Exception {
        String sql = "SELECT id FROM tbplaylists WHERE nome = ? AND id_usuario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomePlaylist);
            stmt.setInt(2, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }

        throw new Exception("Playlist não encontrada.");
    }

    public void adicionarVideoNaPlaylist(int idPlaylist, int idVideo) throws Exception {
        String sqlVerifica = "SELECT 1 FROM tbplaylist_videos WHERE id_playlist = ? AND id_video = ?";
        String sqlInsert = "INSERT INTO tbplaylist_videos (id_playlist, id_video) VALUES (?, ?)";

        try (PreparedStatement stmtVerifica = conn.prepareStatement(sqlVerifica)) {
            stmtVerifica.setInt(1, idPlaylist);
            stmtVerifica.setInt(2, idVideo);

            try (ResultSet rs = stmtVerifica.executeQuery()) {
                if (rs.next()) {
                    return;
                }
            }
        }

        try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
            stmtInsert.setInt(1, idPlaylist);
            stmtInsert.setInt(2, idVideo);
            stmtInsert.executeUpdate();
        }
    }

    public List<Video> listarVideosDaPlaylist(String nomePlaylist, int idUsuario) throws Exception {
        List<Video> videos = new ArrayList<>();

        String sql = """
            SELECT v.id, v.nome, v.genero, v.descricao, v.ano
            FROM tbplaylists p
            INNER JOIN tbplaylist_videos pv ON p.id = pv.id_playlist
            INNER JOIN tbvideos v ON v.id = pv.id_video
            WHERE p.nome = ? AND p.id_usuario = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomePlaylist);
            stmt.setInt(2, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Video video = new Filme();
                    video.setId(rs.getInt("id"));
                    video.setTitulo(rs.getString("nome"));
                    video.setGenero(rs.getString("genero"));
                    video.setDescricao(rs.getString("descricao"));
                    video.setAno(rs.getInt("ano"));
                    videos.add(video);
                }
            }
        }

        return videos;
    }

    public void removerVideoDaPlaylist(int idPlaylist, int idVideo) throws Exception {
        String sql = "DELETE FROM tbplaylist_videos WHERE id_playlist = ? AND id_video = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlaylist);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        }
    }
}