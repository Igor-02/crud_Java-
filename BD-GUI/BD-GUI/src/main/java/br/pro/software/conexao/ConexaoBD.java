package br.pro.software.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static String host = "localhost";
    private static String porta = "3306";
    private static String bd = "pessoas";
    private static String usuario = "alunos";
    private static String senha = "alunos";

    public static Connection obterConexao() throws SQLException {
//        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + porta + "/" + bd,
                    usuario,
                    senha
            );
            return c;
//        } catch ( e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}
