/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Teste.Services;

import Administrativo.Teste.Entity.Teste;
import Util.DataSource;
import Util.ServiceFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class TesteService {
        private static int QTD_PAGINACAO = 10;
        public List<Teste> getListagemTestes(String nome, Integer pagina) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teste> testes = null;
        try{
             ps = con.prepareStatement("Select codTeste, nome, descricao from Teste where nome like ? limit ?,?");

            ps.setString(1, "%" + nome + "%");
            if (pagina != null) {
                ps.setInt(2, (pagina - 1) * QTD_PAGINACAO);
                ps.setInt(3, QTD_PAGINACAO);
            } else {
                ps.setInt(2, 0);
                ps.setInt(3, QTD_PAGINACAO);
            }

            rs = ps.executeQuery();
            testes = new ArrayList<>();

            while (rs.next()) {
                Teste teste = new Teste();
                teste.setCodTeste(rs.getInt("codTeste"));
                teste.setNome(rs.getString("nome"));
                teste.setDescricao(rs.getString("descricao"));
                testes.add(teste);
            }
            rs.close();
        }finally {

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return testes;
    }
        
    public Teste getTeste(Integer id) throws Exception {

    Connection con = DataSource.getInstance().getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Teste teste = null;

    try {

        ps = con.prepareStatement("Select id, nome, descricao, chave from Teste where id = ?");

        ps.setInt(1, id);

        rs = ps.executeQuery();

        if (rs.next()) {
            teste = new Teste();
            teste.setId(id);
            teste.setNome(rs.getString("nome"));
            teste.setDescricao(rs.getString("descricao"));
            teste.setChave(rs.getString("chave"));
        }

        rs.close();
    } finally {

        if (ps != null) {
            ps.close();
        }

        if (rs != null) {
            rs.close();
        }

        con.close();
    }

    return teste;
}
    public Integer getQuantidadeTestes(String nome) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teste> testes = null;
        Integer qtd = null;

        try {
            ps = con.prepareStatement("Select count(id) as qtd from Teste where nome like ?");
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            testes = new ArrayList<>();
            rs.next();
            qtd = rs.getInt("qtd");
            rs.close();
        } finally {

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return ((qtd / QTD_PAGINACAO) + (qtd % QTD_PAGINACAO));
    }
    public boolean existeNome(Teste teste, boolean update) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer qtd = null;
        boolean existe = false;

        try {
            if (!update) {
                ps = con.prepareStatement("Select count(codTeste) as qtd from Teste where nome = ?");
                ps.setString(1, teste.getNome());
                rs = ps.executeQuery();
                rs.next();
                qtd = rs.getInt("qtd");
                rs.close();
                if (qtd > 0) {
                    existe = true;
                }
            } else {
                ps = con.prepareStatement("Select count(codTeste) as qtd from Teste where nome = ? and codTeste <> ?");
                ps.setString(1, teste.getNome());
                ps.setInt(2, teste.getId());
                rs = ps.executeQuery();
                rs.next();
                qtd = rs.getInt("qtd");
                rs.close();
                if (qtd > 0) {
                    existe = true;
                }
            }
        } finally {

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return existe;
    }
    public String gravarTeste(Teste teste) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        //String senha = null;

        PreparedStatement ps = null;

        try {
            
            if (teste.getCodTeste() != 0) {
                System.out.println("ccccccccccccccccccccccccccccccccccccccc");
                ps = con.prepareStatement("Update Teste set nome = ?, descricao = ?, chave = ? where codTeste = ?");
                //ps.setInt(1, teste.getId());
                ps.setString(1, teste.getNome());
                ps.setString(2, teste.getDescricao());
                ps.setString(3, teste.getChave());
               // ps.setInt(3, teste.getTipo().getValue());
                
                ps.executeUpdate();
            } else {
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                ps = con.prepareStatement("Insert into Teste (nome,descricao,chave) values (?,?,?)");
                //ps.setInt(1, teste.getCodTeste());
                ps.setString(1, teste.getNome());
                ps.setString(2, teste.getDescricao());
                ps.setString(3, teste.getChave());
                //ps.setInt(3, usuario.getTipo().getValue());
                
              //  senha = ServiceFactory.getLoginService().gerarSenha();
              //  String md5 = ServiceFactory.getLoginService().gerarMD5(senha);

              //  ps.setString(4, md5);
                ps.execute();
            }

        } finally {

            if (ps != null) {
                ps.close();
            }

            con.close();
        }

        //return senha;
        return "ai deu bao de mais da conta";
    }
    
    public String excluirTeste(Integer id) throws Exception {

        String nome = null;

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {

            ps1 = con.prepareStatement("Select nome from Teste where codTeste = ?");

            ps1.setInt(1, id);

            rs = ps1.executeQuery();

            if (rs.next()) {
                nome = rs.getString("nome");
            } else {
                throw new Exception("O código de Teste " + id + " é inválido");
            }

            ps = con.prepareStatement("Delete from Teste where codTeste = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            if (ps1 != null) {
                ps1.close();
            }

            con.close();
        }

        return nome;
    }
    private static int MAX = 20;

    public List<Teste> getListagemTestes(String nome) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teste> testes = null;
        try {

            ps = con.prepareStatement("Select id, nome, descricao, chave from Teste where nome like ? limit ?,?,?");

            ps.setString(1, "%" + nome + "%");
            ps.setInt(2, 0);
            ps.setInt(3, MAX);

            rs = ps.executeQuery();
            testes = new ArrayList<>();

            while (rs.next()) {
                Teste teste = new Teste();
                teste.setId(rs.getInt("id"));
                teste.setNome(rs.getString("nome"));
                teste.setDescricao(rs.getString("descricao"));
                teste.setChave(rs.getString("chave"));
                testes.add(teste);
            }

            rs.close();

        } finally {

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return testes;
    }
}

