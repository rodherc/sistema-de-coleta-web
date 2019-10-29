/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.Pesquisador.Services;

import Administrativo.Pesquisador.Entity.Tipo;
import Administrativo.Pesquisador.Entity.Pesquisador;
import Util.DataSource;
import Util.ServiceFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author raphaelwb
 */
public class PesquisadorService {

    private static int QTD_PAGINACAO = 2;

    public List<Pesquisador> getListagemPesquisadores(String nome, Integer pagina) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pesquisador> pesquisadores = null;
        try {

            ps = con.prepareStatement("Select id, nome, email, tipo from pesquisador where nome like ? limit ?,?");

            ps.setString(1, "%" + nome + "%");
            if (pagina != null) {
                ps.setInt(2, (pagina - 1) * QTD_PAGINACAO);
                ps.setInt(3, QTD_PAGINACAO);
            } else {
                ps.setInt(2, 0);
                ps.setInt(3, QTD_PAGINACAO);
            }

            rs = ps.executeQuery();
            pesquisadores = new ArrayList<>();

            while (rs.next()) {
                Pesquisador pesquisador = new Pesquisador();
                pesquisador.setCodPesquisador(rs.getInt("id"));
                pesquisador.setNome(rs.getString("nome"));
                pesquisador.setEmail(rs.getString("email"));
                pesquisador.setTipo(Tipo.getTipo(rs.getInt("tipo")));
                pesquisadores.add(pesquisador);
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

        return pesquisadores;
    }

    public Pesquisador getPesquisador(Integer id) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pesquisador pesquisador = null;

        try {

            ps = con.prepareStatement("Select id, nome, email, tipo from pesquisador where id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                pesquisador = new Pesquisador();
                pesquisador.setCodPesquisador(id);
                pesquisador.setNome(rs.getString("nome"));
                pesquisador.setEmail(rs.getString("email"));
                pesquisador.setTipo(Tipo.getTipo(rs.getInt("tipo")));
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

        return pesquisador;
    }

    public Integer getQuantidadePaginasPesquisadores(String nome) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pesquisador> pesquisadores = null;
        Integer qtd = null;

        try {
            ps = con.prepareStatement("Select count(id) as qtd from pesquisador where nome like ?");
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            pesquisadores = new ArrayList<>();
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

    public boolean existeEmail(Pesquisador pesquisador, boolean update) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer qtd = null;
        boolean existe = false;

        try {
            if (!update) {
                ps = con.prepareStatement("Select count(id) as qtd from pesquisador where email = ?");
                ps.setString(1, pesquisador.getEmail());
                rs = ps.executeQuery();
                rs.next();
                qtd = rs.getInt("qtd");
                rs.close();
                if (qtd > 0) {
                    existe = true;
                }
            } else {
                ps = con.prepareStatement("Select count(id) as qtd from pesquisador where email = ? and id <> ?");
                ps.setString(1, pesquisador.getEmail());
                ps.setInt(2, pesquisador.getCodPesquisador());
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

    public String gravarPesquisador(Pesquisador pesquisador) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        String senha = null;

        PreparedStatement ps = null;

        try {
            
            /*
            AINDA FALTA IMPLEMENTAR O CONTROLE POR VERSIONAMENTO
            */

            if (pesquisador.getCodPesquisador() != 0) {
                ps = con.prepareStatement("Update pesquisador set nome = ?, email = ?, tipo = ? where id = ?");
                ps.setString(1, pesquisador.getNome());
                ps.setString(2, pesquisador.getEmail());
                ps.setInt(3, pesquisador.getTipo().getValue());
                ps.setInt(4, pesquisador.getCodPesquisador());
                ps.executeUpdate();
            } else {
                ps = con.prepareStatement("Insert into pesquisador (nome,email,tipo, senha) values (?,?,?,?)");
                ps.setString(1, pesquisador.getNome());
                ps.setString(2, pesquisador.getEmail());
                ps.setInt(3, pesquisador.getTipo().getValue());
                
                senha = ServiceFactory.getLoginService().gerarSenha();
                String md5 = ServiceFactory.getLoginService().gerarMD5(senha);

                ps.setString(4, md5);
                ps.execute();
            }

        } finally {

            if (ps != null) {
                ps.close();
            }

            con.close();
        }

        return senha;
    }

    public String excluirPesquisador(Integer id) throws Exception {

        String nome = null;

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;

        try {

            ps1 = con.prepareStatement("Select nome from pesquisador where id = ?");

            ps1.setInt(1, id);

            rs = ps1.executeQuery();

            if (rs.next()) {
                nome = rs.getString("nome");
            } else {
                throw new Exception("O código de usuário " + id + " é inválido");
            }

            ps = con.prepareStatement("Delete from Pesquisador where id = ?");
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

    public List<Pesquisador> getListagemPesquisadores(String nome) throws Exception {

        Connection con = DataSource.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pesquisador> pesquisadores = null;
        try {

            ps = con.prepareStatement("Select id, nome, email, tipo from pesquisador where nome like ? limit ?,?");

            ps.setString(1, "%" + nome + "%");
            ps.setInt(2, 0);
            ps.setInt(3, MAX);

            rs = ps.executeQuery();
            pesquisadores = new ArrayList<>();

            while (rs.next()) {
                Pesquisador pesquisador = new Pesquisador();
                pesquisador.setCodPesquisador(rs.getInt("id"));
                pesquisador.setNome(rs.getString("nome"));
                pesquisador.setEmail(rs.getString("email"));
                pesquisador.setTipo(Tipo.getTipo(rs.getInt("tipo")));
                pesquisadores.add(pesquisador);
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

        return pesquisadores;
    }
}
