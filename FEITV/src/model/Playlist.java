package model;

public class Playlist {
    private int id;
    private int usuarioId;
    private int videoId;

    public Playlist() {
    }

    public Playlist(int usuarioId, int videoId) {
        this.usuarioId = usuarioId;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}