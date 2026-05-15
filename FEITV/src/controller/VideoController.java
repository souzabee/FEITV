package controller;

import dao.Conexao;
import dao.VideoDAO;
import java.sql.Connection;
import model.Video;

public class VideoController {

    public Video buscarVideoPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return null;
        }

        try (Connection conn = new Conexao().getConnection()) {
            VideoDAO dao = new VideoDAO(conn);
            return dao.buscarPorNome(nome.trim());
        } catch (Exception e) {
            return null;
        }
    }
}