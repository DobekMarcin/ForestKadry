package md.enovaImport.sql.jdbc;

import md.enovaImport.sql.models.OrderWork;
import md.enovaImport.sql.models.PK;
import md.enovaImport.xml.models.Wyplata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<OrderWork> getOrderWork(Integer month, Integer year, List<Wyplata> wyplataList) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        List<OrderWork> orderWorksList = new ArrayList<>();


        String workers = "(";
        for (int i = 0; i < wyplataList.size(); i++) {
            if (i + 1 < wyplataList.size()) {
                workers = workers + wyplataList.get(i).getKodPracownika() + ",";
            } else {
                workers = workers + wyplataList.get(i).getKodPracownika() + ")";
            }
        }
        String query = ("Select C.rok,C.nr,A.id_zlecenia,g.rou2(sum(pracochlonnosc_h)) as suma,c.opis as opis from g.prod_karty_pracy A " + "left join g.prod_karty_pracy_pracownicy B " + "on B.rok=A.rok and B.mc=A.mc and B.nr=A.nr " + "LEFT JOIN G.MZK_ZLECENIA c ON c.id=A.id_zlecenia " + "where A.rok=? and A.mc=? " + "and B.id_pracownika in " + workers + " group by A.id_zlecenia,C.rok,C.opis,C.nr having sum(pracochlonnosc_h)>0 order by A.id_zlecenia");

        statement = connection.prepareStatement(query);

        statement.setInt(1, year);
        statement.setInt(2, month);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            OrderWork orderWork = new OrderWork();
            orderWork.setOrderId(rs.getInt("id_zlecenia"));
            orderWork.setOrderNumber(rs.getInt("nr"));
            orderWork.setOrderYear(rs.getInt("rok"));
            orderWork.setTime(rs.getDouble("suma"));
            orderWork.setOrderName(rs.getString("opis"));
            orderWorksList.add(orderWork);
        }
        connection.close();
        return orderWorksList;
    }
}
