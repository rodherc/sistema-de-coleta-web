/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo.LoginChave.Services;

import Administrativo.LoginChave.Model.LoginChaveModel;
import Administrativo.Teste.Entity.Teste;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public class LoginChaveService {
    public Teste verificarLogin(LoginChaveModel lm) throws Exception {

        Connection con = DataSource.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teste teste = null;

        try {

            ps = con.prepareStatement("Select codTeste, chave from Teste where chave = ?");
            ps.setString(1, lm.getChave());

            rs = ps.executeQuery();

            if (rs.next()) {
                teste = new Teste();
                teste.setCodTeste(rs.getInt("codTeste"));
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
}
