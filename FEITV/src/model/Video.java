package model;

public abstract class Video {
    private int id;
    private String titulo;
    private String genero;
    private String descricao;
    private int ano;

    public Video() {
    }

    public Video(int id, String titulo, String genero, String descricao, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.descricao = descricao;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public abstract String exibirDetalhes();
}