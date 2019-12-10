/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Pergunta.Services;

import Administrativo.Pergunta.Entity.Pergunta;
import Administrativo.Pergunta.Entity.Tipo;
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
public class PerguntaService {
        private static int QTD_PAGINACAO = 10;
        public List<Pergunta> getListagemPerguntas(int codTeste ,Integer pagina) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pergunta> perguntas = null;
        try{
             ps = con.prepareStatement("Select codPergunta, tipo, descricaoPergunta, codTeste, existeDescricao from Pergunta where codTeste like ? limit ?,?");

            ps.setString(1,"%"+codTeste+"%");
            
            if (pagina != null) {
                ps.setInt(2, (pagina - 1) * QTD_PAGINACAO);
                ps.setInt(3, QTD_PAGINACAO);
            } else {
                ps.setInt(2, 0);
                ps.setInt(3, QTD_PAGINACAO);
            }

            rs = ps.executeQuery();
            perguntas = new ArrayList<>();

            while (rs.next()) {
                Pergunta pergunta = new Pergunta();
                pergunta.setCodPergunta(rs.getInt("codPergunta"));
                pergunta.setTipo(Tipo.getTipo(rs.getInt("tipo")));
                pergunta.setDescricaoPergunta(rs.getString("descricaoPergunta"));
                pergunta.setCodTeste(rs.getInt("codTeste"));
                pergunta.setExisteDescricao(rs.getBoolean("existeDescricao"));
                
                perguntas.add(pergunta);
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

        return perguntas;
    }
    
    public Integer getQuantidadePerguntas(int codTeste) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pergunta> perguntas = null;
        Integer qtd = null;

        try {
            ps = con.prepareStatement("Select count(codPergunta) as qtd from Pergunta where codTeste like ?");
            ps.setString(1,"%"+codTeste+"%");
            rs = ps.executeQuery();
            perguntas = new ArrayList<>();
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
    
    public String gravarPergunta(Pergunta pergunta) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        //String senha = null;

        PreparedStatement ps = null;

        try {
            
            if (pergunta.getCodPergunta() != 0) {
                ps = con.prepareStatement("Update Pergunta set descricaoPergunta = ? where codPergunta = ?");
                ps.setString(1, pergunta.getDescricaoPergunta());
                ps.setString(2,"%" +pergunta.getCodPergunta() + "%");
                ps.executeUpdate();
            } else {
                ps = con.prepareStatement("Insert into Pergunta (descricaoPergunta,codTeste,existeDescricao,tipo) values (?,?,?,?)");
                
                ps.setString(1, pergunta.getDescricaoPergunta());
                ps.setInt(2,pergunta.getCodTeste());
                ps.setBoolean(3,pergunta.getExisteDescricao());
                ps.setInt(4, pergunta.getTipo().getValue());
              
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
    
    public Integer excluirPergunta(Integer codPergunta) throws Exception {

 

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {

            
            ps = con.prepareStatement("Delete from Pergunta where codPergunta = ?");
            ps.setInt(1, codPergunta);
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
        
        //return nome;
        return codPergunta;
    }
    private static int MAX = 20;
}

