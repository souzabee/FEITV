/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ArturGuimaraesdeSouz
 */
public class PlaylistVideo {
    private int idPlaylist;
    private int idVideo;

    public PlaylistVideo() {
    }

    public PlaylistVideo(int idPlaylist, int idVideo) {
        this.idPlaylist = idPlaylist;
        this.idVideo = idVideo;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }
}
