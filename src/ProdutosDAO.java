/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.math.BigInteger;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public int cadastrarProduto(ProdutosDTO produto) {
        int status;

        try {
            Connection conn = conectaDAO.getConnection();
            

            ps = conn.prepareStatement("INSERT INTO produtos VALUES(?, ?, ?, ?)");
            ps.setInt(1, produto.getId());
            ps.setString(2, produto.getNome());
            ps.setInt(3, produto.getValor());
            ps.setString(4, produto.getStatus());

            status = ps.executeUpdate();
            return status;
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao cadastrar dados." + ex.getMessage());

            return ex.getErrorCode();
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
