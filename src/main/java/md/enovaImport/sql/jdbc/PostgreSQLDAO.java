package md.enovaImport.sql.jdbc;

import md.enovaImport.sql.models.PK;

import java.sql.*;

public class PostgreSQLDAO {

    private final static String url = "jdbc:postgresql://192.168.0.6/forest";
    private final static String username = "postgres";
    private final static String password = "FoReSt2015!";

    public static void main(String[] args) throws SQLException {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, username, password);

            DatabaseMetaData dm = conn.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    private Connection getConnectcion() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    public Integer testQuery() throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Integer check = 0;
        statement = connection.prepareStatement("Select count(*) as liczba from g.gm_indeksy");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) System.out.println(rs.getInt("liczba"));
        connection.close();
        return check;
    }

    public void insertPK(PK pk) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO g.ks_pkpoz_tmp(" +
                "            id_uzytkownika, nrpary, nrpodpary, rownolegle, kontown, wartoscwn, " +
                "            kontoma, wartoscma, waluta, walutadata, walutarodzaj, walutatabela, " +
                "            waluta_jakikurs, opis, kontown2_makro, kontown3_makro, kontown4_makro, " +
                "            kontown5_makro, kontown6_makro, kontoma2_makro, kontoma3_makro, " +
                "            kontoma4_makro, kontoma5_makro, kontoma6_makro, zrodlo, wartoscwnwaluta, " +
                "            wartoscmawaluta, kurswaluta, id_cechy, zrodlofak, id_zrodla, " +
                "            kasakasa, kasarok, kasanr, kasalp, kasalp2, st_dok, st_inw, st_rok, " +
                "            st_mc, st_nr, id_zlecenia, pole1, pole2)" +
                "    VALUES (14, ?, ?, 0, ?, ?, " +
                "            ?, ?, '', 0, 0, '', " +
                "            0, ?, '', '', '', " +
                "            '', '', '', '', " +
                "            '', '', '', '', 0, " +
                "            0, 0, 0, 0, 0, " +
                "            0, 0, 0, 0, 0, 0, '', 0, " +
                "            0, 0, 0, 0, '');");
        statement.setInt(1,pk.getPair_number());
        statement.setInt(2,pk.getUnderPair_number());
        statement.setString(3,pk.getBlame_account());
        statement.setDouble(4,pk.getBlame_value());
        statement.setString(5,pk.getHac_account());
        statement.setDouble(6,pk.getHas_value());
        statement.setString(7,pk.getDescription());
        statement.executeUpdate();
        connection.close();
    }


}
