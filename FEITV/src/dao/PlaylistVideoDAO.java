/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ArturGuimaraesdeSouz
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistVideoDAO {
    private Connection conn;

    public PlaylistVideoDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarVideo(int idPlaylist, int idVideo) throws SQLException {
        String sql = "insert into tbplaylist_video (id_playlist, id_video) values (?, ?) on conflict do nothing";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idVideo);
        stmt.executeUpdate();
        stmt.close();
    }

    public void removerVideo(int idPlaylist, int idVideo) throws SQLException {
        String sql = "delete from tbplaylist_video where id_playlist = ? and id_video = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        stmt.setInt(2, idVideo);
        stmt.executeUpdate();
        stmt.close();
    }

    public ResultSet listarVideosDaPlaylist(int idPlaylist) throws SQLException {
        String sql = """
            select v.*
            from tbplaylist_video pv
            join tbvideos v on v.id = pv.id_video
            where pv.id_playlist = ?
            order by v.titulo
        """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPlaylist);
        return stmt.executeQuery();
    }
}
