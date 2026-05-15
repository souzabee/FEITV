package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Usuario;
import view.Cadastro;
import view.Login;
import view.TelaPrincipal;

public class UsuarioController {

    public void cadastrar(Cadastro tela) {
        String nome = tela.getNome_input().getText().trim();
        String usuario = tela.getUsuario_input().getText().trim();
        String senha = tela.getSenha_input().getText().trim();

        if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos.");
            return;
        }

        Usuario novo = new Usuario(nome, usuario, senha);

        try (Connection conn = new Conexao().getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(conn);
            dao.inserir(novo);

            JOptionPane.showMessageDialog(tela, "Usuário cadastrado com sucesso.");
            tela.dispose();

            Login login = new Login();
            login.setLocationRelativeTo(null);
            login.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(tela, "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void login(Login tela) {
        String usuario = tela.getUsuario_input().getText().trim();
        String senha = tela.getSenha_input().getText().trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha usuário e senha.");
            return;
        }

        try (Connection conn = new Conexao().getConnection()) {
            UsuarioDAO dao = new UsuarioDAO(conn);

            Usuario user = new Usuario();
            user.setUsuario(usuario);
            user.setSenha(senha);

            ResultSet rs = dao.consultar(user);

            if (rs.next()) {
                Usuario usuarioLogado = new Usuario();
                usuarioLogado.setId(rs.getInt("id"));
                usuarioLogado.setNome(rs.getString("nome"));
                usuarioLogado.setUsuario(rs.getString("usuario"));
                usuarioLogado.setSenha(rs.getString("senha"));

                JOptionPane.showMessageDialog(tela, "Login realizado com sucesso.");

                TelaPrincipal principal = new TelaPrincipal(usuarioLogado);
                principal.setLocationRelativeTo(null);
                principal.setVisible(true);

                tela.dispose();
            } else {
                JOptionPane.showMessageDialog(tela, "Usuário ou senha inválidos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tela, "Erro no login: " + e.getMessage());
        }
    }
}