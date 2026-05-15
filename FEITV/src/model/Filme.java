/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ArturGuimaraesdeSouz
 */
public class Filme extends Video {

    public Filme() {
        super();
    }

    public Filme(int id, String titulo, String genero, String descricao, int ano) {
        super(id, titulo, genero, descricao, ano);
    }

    @Override
    public String exibirDetalhes() {
        return "Filme: " + getTitulo() +
               "\nGênero: " + getGenero() +
               "\nDescrição: " + getDescricao() +
               "\nAno: " + getAno();
    }
}