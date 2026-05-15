/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ArturGuimaraesdeSouz
 */
public class Curtida {
    private int id;
    private int idUsuario;
    private int idVideo;
    private boolean curtido;

    public Curtida() {
    }

    public Curtida(int id, int idUsuario, int idVideo, boolean curtido) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idVideo = idVideo;
        this.curtido = curtido;
    }

    public Curtida(int idUsuario, int idVideo, boolean curtido) {
        this.idUsuario = idUsuario;
        this.idVideo = idVideo;
        this.curtido = curtido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public boolean isCurtido() {
        return curtido;
    }

    public void setCurtido(boolean curtido) {
        this.curtido = curtido;
    }
}
