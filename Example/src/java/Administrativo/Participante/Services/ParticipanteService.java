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
        
    public String gravarParticipante(Participante participante) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        //String senha = null;

        PreparedStatement ps = null;

        try {
            
            if (participante.getCodParticipante() != 0) {
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
}

