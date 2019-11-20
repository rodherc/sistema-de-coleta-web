/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Imagem.Services;

import Administrativo.Imagem.Entity.Imagem;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ImagemService {
     private static int QTD_PAGINACAO = 10;
        public List<Imagem> getListagemImagens(String nome, Integer pagina) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
            System.out.println("vai se fudeeeeeeeeeeeeeeeeeeeeeeeee");
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Imagem> imagens = null;
        try{
             ps = con.prepareStatement("Select codImagem, nome, localizacao from Imagem where nome like ? limit ?,?");

            ps.setString(1, "%" + nome + "%");
            if (pagina != null) {
                ps.setInt(2, (pagina - 1) * QTD_PAGINACAO);
                ps.setInt(3, QTD_PAGINACAO);
            } else {
                ps.setInt(2, 0);
                ps.setInt(3, QTD_PAGINACAO);
            }

            rs = ps.executeQuery();
            imagens = new ArrayList<>();

            while (rs.next()) {
                Imagem imagem = new Imagem();
                imagem.setCodImagem(rs.getInt("codImagem"));
                imagem.setNome(rs.getString("nome"));
                imagem.setLocalizacao(rs.getString("localizacao"));
                imagens.add(imagem);
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
            System.out.println("aquiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        return imagens;
    }
        
    public Imagem getImagem(Integer codImagem) throws Exception {

    Connection con = DataSource.getInstance().getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Imagem imagem = null;

    try {

        ps = con.prepareStatement("Select codImagem, nome, localizacao from Teste where codImagem = ?");

        ps.setInt(1, codImagem);

        rs = ps.executeQuery();

        if (rs.next()) {
            imagem = new Imagem();
            imagem.setCodImagem(codImagem);
            imagem.setNome(rs.getString("nome"));
            imagem.setLocalizacao(rs.getString("localizacao"));
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

    return imagem;
}
    public Integer getQuantidadeImagens(String nome) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Imagem> imagens = null;
        Integer qtd = null;

        try {
            ps = con.prepareStatement("Select count(codImagem) as qtd from Imagem where nome like ?");
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            imagens = new ArrayList<>();
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
    public boolean existeNome(Imagem imagem, boolean update) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer qtd = null;
        boolean existe = false;

        try {
            if (!update) {
                ps = con.prepareStatement("Select count(codImagem) as qtd from Imagem where nome = ?");
                ps.setString(1, imagem.getNome());
                rs = ps.executeQuery();
                rs.next();
                qtd = rs.getInt("qtd");
                rs.close();
                if (qtd > 0) {
                    existe = true;
                }
            } else {
                ps = con.prepareStatement("Select count(codImagem) as qtd from Imagem where nome = ? and codImagem <> ?");
                ps.setString(1, imagem.getNome());
                ps.setInt(2, imagem.getCodImagem());
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
    public String gravarImagem(Imagem imagem) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        //String senha = null;

        PreparedStatement ps = null;

        try {
            
            if (imagem.getCodImagem() != 0) {
                
                ps = con.prepareStatement("Update Imagem set nome = ?, localizacao = ? where codImagem = ?");
                ps.setInt(1, imagem.getCodImagem());
                ps.setString(2, imagem.getNome());
                ps.setString(3, imagem.getLocalizacao());
               // ps.setInt(3, teste.getTipo().getValue());
                
                ps.executeUpdate();
            } else {
               
                ps = con.prepareStatement("Insert into Imagem (imagem,localizacao) values (?,?)");
                //ps.setInt(1, teste.getCodTeste());
                ps.setString(1, imagem.getNome());
                ps.setString(2, imagem.getLocalizacao());
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
    
    public String excluirImagem(Integer codImagem) throws Exception {

        String nome = null;

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {

            ps1 = con.prepareStatement("Select nome from Imagem where codImagem = ?");

            ps1.setInt(1, codImagem);

            rs = ps1.executeQuery();

            if (rs.next()) {
                nome = rs.getString("nome");
            } else {
                throw new Exception("O código da Imagem" + codImagem + " é inválido");
            }

            ps = con.prepareStatement("Delete from Imagem where codImagem = ?");
            ps.setInt(1, codImagem);
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

    public List<Imagem> getListagemImagens(String nome) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Imagem> imagens = null;
        try {

            ps = con.prepareStatement("Select codImagem, nome, localizacao from Imagem where nome like ? limit ?,?");

            ps.setString(1, "%" + nome + "%");
            ps.setInt(2, 0);
            ps.setInt(3, MAX);

            rs = ps.executeQuery();
            imagens = new ArrayList<>();

            while (rs.next()) {
                Imagem imagem = new Imagem();
                imagem.setCodImagem(rs.getInt("codImagem"));
                imagem.setNome(rs.getString("nome"));
                imagem.setLocalizacao(rs.getString("localizacao"));
                imagens.add(imagem);
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

        return imagens;
    }
}

