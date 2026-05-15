package controller;

import dao.Conexao;
import dao.PlaylistDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import model.Video;

public class PlaylistController {

    public String criarPlaylist(String nomePlaylist, Usuario usuarioLogado) {
        if (usuarioLogado == null) {
            return "Usuário inválido.";
        }

        if (nomePlaylist == null || nomePlaylist.trim().isEmpty()) {
            return "Digite o nome da playlist.";
        }

        try (Connection conn = new Conexao().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            dao.criarPlaylist(nomePlaylist.trim(), usuarioLogado.getId());
            return "Playlist criada com sucesso.";
        } catch (Exception e) {
            return "Erro ao criar playlist: " + e.getMessage();
        }
    }

    public List<String> listarPlaylists(Usuario usuarioLogado) {
        if (usuarioLogado == null) {
            return new ArrayList<>();
        }

        try (Connection conn = new Conexao().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            return dao.listarPlaylistsDoUsuario(usuarioLogado.getId());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public String adicionarVideoNaPlaylist(String nomePlaylist, Usuario usuarioLogado, Video video) {
        if (usuarioLogado == null) {
            return "Usuário inválido.";
        }

        if (video == null) {
            return "Vídeo inválido.";
        }

        if (nomePlaylist == null || nomePlaylist.trim().isEmpty()) {
            return "Selecione uma playlist.";
        }

        try (Connection conn = new Conexao().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            int idPlaylist = dao.buscarIdPlaylist(nomePlaylist, usuarioLogado.getId());
            dao.adicionarVideoNaPlaylist(idPlaylist, video.getId());
            return "Filme adicionado à playlist.";
        } catch (Exception e) {
            return "Erro ao adicionar filme à playlist: " + e.getMessage();
        }
    }

    public List<Video> listarVideosDaPlaylist(String nomePlaylist, Usuario usuarioLogado) {
        if (usuarioLogado == null || nomePlaylist == null || nomePlaylist.trim().isEmpty()) {
            return new ArrayList<>();
        }

        try (Connection conn = new Conexao().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            return dao.listarVideosDaPlaylist(nomePlaylist, usuarioLogado.getId());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public String removerVideoDaPlaylist(String nomePlaylist, Usuario usuarioLogado, int idVideo) {
        if (usuarioLogado == null) {
            return "Usuário inválido.";
        }

        if (idVideo <= 0) {
            return "Vídeo inválido.";
        }

        if (nomePlaylist == null || nomePlaylist.trim().isEmpty()) {
            return "Playlist inválida.";
        }

        try (Connection conn = new Conexao().getConnection()) {
            PlaylistDAO dao = new PlaylistDAO(conn);
            int idPlaylist = dao.buscarIdPlaylist(nomePlaylist, usuarioLogado.getId());
            dao.removerVideoDaPlaylist(idPlaylist, idVideo);
            return "Vídeo removido da playlist.";
        } catch (Exception e) {
            return "Erro ao remover vídeo: " + e.getMessage();
        }
    }
}