package br.pro.software.ui;

import br.pro.software.dao.UsuarioDAO;
import br.pro.software.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    JTextField loginTextField = new JTextField();
    JPasswordField senhaPasswordField = new JPasswordField();
    JButton loginButton = new JButton("login");
    JButton sairButton = new JButton("sair");
    
    public LoginForm(String titulo) {
        super(titulo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarTela());
    }

    private static void criarTela() {
        LoginForm loginForm = new LoginForm("Sistema ?");
        loginForm.setup();
    }

    private void setup() {
        definirBordas();
        criarComponentes();
        definirComportamentoBotoes();
        definirJanelaFinal();
    }

    private void definirJanelaFinal() {
        this.setLocationRelativeTo(null);
        this.setSize(400, 200);
        this.setMinimumSize(new Dimension(400, 200));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void definirComportamentoBotoes() {
        sairButton.addActionListener(e -> this.dispose());
        loginButton.addActionListener(e -> {
            Usuario tentativa = new Usuario(loginTextField.getText(), new String(senhaPasswordField.getPassword()));
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean acessoPermitido = false;
            try {
                acessoPermitido = usuarioDAO.existe(tentativa);
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null, "Problemas técnicos na conexão com o banco. Tente mais tarde.");
                exception.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,
                    acessoPermitido ? "Bem-vindo " + tentativa.getNome() : "Usuário inválido");
        });
    }

    private void criarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 1));
        container.add(loginTextField);
        container.add(senhaPasswordField);
        container.add(criarPainelBotoes());
    }

    private JPanel criarPainelBotoes() {
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 2));
        painelBotoes.add(loginButton);
        painelBotoes.add(sairButton);
        return painelBotoes;
    }

    private void definirBordas() {
        loginTextField.setBorder(BorderFactory.createTitledBorder("Digite seu login"));
        senhaPasswordField.setBorder(BorderFactory.createTitledBorder("Digite sua senha"));
    }

}
