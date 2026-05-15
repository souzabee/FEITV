package controller;

import dao.Conexao;
import dao.CurtidaDAO;
import java.sql.Connection;
import model.Usuario;
import model.Video;

public class CurtidaController {

    public String curtir(Usuario usuarioLogado, Video video) {
        if (usuarioLogado == null || video == null) {
            return "Usuário ou vídeo inválido.";
        }

        try (Connection conn = new Conexao().getConnection()) {
            CurtidaDAO dao = new CurtidaDAO(conn);

            if (dao.jaCurtiu(usuarioLogado.getId(), video.getId())) {
                return "Você já curtiu este filme.";
            }

            dao.curtirVideo(usuarioLogado.getId(), video.getId());
            return "Filme curtido com sucesso.";

        } catch (Exception e) {
            return "Erro ao curtir filme: " + e.getMessage();
        }
    }

    public String descurtir(Usuario usuarioLogado, Video video) {
        if (usuarioLogado == null || video == null) {
            return "Usuário ou vídeo inválido.";
        }

        try (Connection conn = new Conexao().getConnection()) {
            CurtidaDAO dao = new CurtidaDAO(conn);

            if (!dao.jaCurtiu(usuarioLogado.getId(), video.getId())) {
                return "Você ainda não curtiu este filme.";
            }

            dao.descurtirVideo(usuarioLogado.getId(), video.getId());
            return "Curtida removida com sucesso.";

        } catch (Exception e) {
            return "Erro ao descurtir filme: " + e.getMessage();
        }
    }
}