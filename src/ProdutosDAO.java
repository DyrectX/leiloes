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
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ProdutosDTO> listaP = new ArrayList<>();

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

    public ArrayList<ProdutosDTO> listarProdutos(String listaProduto) {
        String sql = "SELECT * from produtos";

        try {
            conectaDAO cdao = new conectaDAO();
            conn = cdao.getConnection();

            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ArrayList<ProdutosDTO> listaP = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO pdto = new ProdutosDTO();

                pdto.setId(rs.getInt("id"));
                pdto.setNome(rs.getString("nome"));
                pdto.setStatus(rs.getString("status"));
                pdto.setValor(rs.getInt("valor"));

                listaP.add(pdto);
            }
            return listaP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos(String produtoVendido) {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            conectaDAO cdao = new conectaDAO();
            conn = cdao.getConnection();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setStatus(rs.getString("status"));
                produto.setValor(rs.getInt("valor"));
                listaProdutos.add(produto);
            }

            return (ArrayList<ProdutosDTO>) listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }

    public void venderProduto(int produtoId) {
        conn = conectaDAO.getConnection();

        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, produtoId);
            ps.executeUpdate();

            System.out.println("Produto vendido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao vender o produto: " + e.getMessage());
        }
    }
}
