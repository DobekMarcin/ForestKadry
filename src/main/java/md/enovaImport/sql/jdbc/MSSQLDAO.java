package md.enovaImport.sql.jdbc;

import md.enovaImport.sql.models.Department;
import md.enovaImport.sql.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSSQLDAO {

    private final static String url = "jdbc:sqlserver://192.168.0.92\\PROD;encrypt=true;trustServerCertificate=true;database=eno_Forest";
    private final static String username = "sa";
    private final static String password = "F30_8r3-e05#0t";

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


    public Boolean testQuery() throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Boolean check = false;
        statement = connection.prepareStatement("Select count(*) as liczba from eno_Forest.dbo.Pracownicy");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) System.out.println(rs.getInt("liczba"));
        connection.close();
        return check;
    }

    public List<Person> getPersonsData() throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        List<Person> personList = new ArrayList<Person>();
        statement = connection.prepareStatement(" SELECT A.id as id,A.kod as kod,A.Imie+' '+A.nazwisko as dane,C.KontaktEMAIL as email" + " FROM eno_Forest.dbo.Pracownicy A " + " left join eno_Forest.dbo.PracHistorie B on A.ID=B.Pracownik " + " left join eno_Forest.dbo.PracHistorie2 C on C.id=B.ID " + " where GETDATE() BETWEEN B.AktualnoscFrom AND B.AktualnoscTo;");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Person person = new Person();
            person.setCode(rs.getInt("kod"));
            person.setEmail(rs.getString("email"));
            person.setSend(!person.getEmail().isEmpty());
            person.setName(rs.getString("dane"));
            personList.add(person);
        }
        connection.close();
        return personList;
    }

    public List<Department> getDepartment() throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        List<Department> departments = new ArrayList<>();
        statement = connection.prepareStatement(" SELECT id,kod,nazwa FROM eno_Forest.dbo.Wydzialy order by id");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setCode(rs.getString("kod"));
            department.setName(rs.getString("nazwa"));
            departments.add(department);
        }
        connection.close();
        return departments;
    }


}
