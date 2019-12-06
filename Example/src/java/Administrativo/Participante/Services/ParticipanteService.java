/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Participante.Services;

import Administrativo.Participante.Entity.Participante;
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
public class ParticipanteService {
        private static int QTD_PAGINACAO = 10;
        public List<Participante> getListagemParticipantes(int codParticipante, Integer pagina) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Participante> testes = null;
        try{
             ps = con.prepareStatement("Select codParticipante, telefone, idade, sexo, "
                     + "cep, cor, descricaoParticipante from Participante where codParticipante like ? limit ?,?");

            ps.setString(1, "%" + codParticipante + "%");
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
                Participante participante = new Participante();
                participante.setCodParticipante(rs.getInt("codParticipante"));
                participante.setEmailParticipante(rs.getString("emailParticipante"));
                participante.setTelefone(rs.getString("telefone"));
                participante.setIdade(rs.getInt("idade"));
                participante.setSexo(rs.getString("sexo"));
                participante.setCep(rs.getString("cep"));
                participante.setCor(rs.getString("cor"));
                participante.setDescricaoParticipante(rs.getString("descricaoParticipante"));

                testes.add(participante);
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
        
    /*public Teste getTeste(Integer id) throws Exception {

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
    }*/
    public String gravarParticipante(Participante participante) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        //String senha = null;

        PreparedStatement ps = null;

        try {
            
            if (participante.getCodParticipante() != 0) {
                System.out.println("-----------Atualizar Participante----------");
                ps = con.prepareStatement("Update Participante set (emailParticipante = ? ,telefone = ?,"
                        + "idade = ?,sexo = ?,cep = ?,cor = ?,descricaoParticipante = ? where codTeste = ?");
                
                ps.setString(1, participante.getEmailParticipante());
                ps.setString(2, participante.getTelefone());
                ps.setInt(3, participante.getIdade());
                ps.setString(4, participante.getSexo());
                ps.setString(5, participante.getCep());
                ps.setString(6, participante.getCor());
                ps.setString(7, participante.getDescricaoParticipante());
                
                ps.executeUpdate();
            } else {
                System.out.println("-----------Criar novo Participante-----------");
                ps = con.prepareStatement("Insert into Participante (emailParticipante,telefone,idade,"
                        + "sexo,cep,cor,descricaoParticipante) values (?,?,?,?,?,?,?)");
                ps.setString(1, participante.getEmailParticipante());
                ps.setString(2, participante.getTelefone());
                ps.setInt(3, participante.getIdade());
                ps.setString(4, participante.getSexo());
                ps.setString(5, participante.getCep());
                ps.setString(6, participante.getCor());
                ps.setString(7, participante.getDescricaoParticipante());

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
    /*
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
    }*/
}

