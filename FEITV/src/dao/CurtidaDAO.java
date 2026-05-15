package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CurtidaDAO {
    private Connection conn;

    public CurtidaDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean jaCurtiu(int idUsuario, int idVideo) throws Exception {
        String sql = "SELECT 1 FROM tbcurtidas WHERE id_usuario = ? AND id_video = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idVideo);

        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public void curtirVideo(int idUsuario, int idVideo) throws Exception {
        String sql = "INSERT INTO tbcurtidas (id_usuario, id_video) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idVideo);
        stmt.executeUpdate();
    }

    public void descurtirVideo(int idUsuario, int idVideo) throws Exception {
        String sql = "DELETE FROM tbcurtidas WHERE id_usuario = ? AND id_video = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idVideo);
        stmt.executeUpdate();
    }
}