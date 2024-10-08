package md.enovaImport.sql.jdbc;

import md.enovaImport.modelsFX.*;
import md.enovaImport.sql.models.*;
import md.enovaImport.xml.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportDAO {


    private final static String url = "jdbc:mysql://192.168.0.92:3306/import";
    private final static String username = "admin";
    private final static String password = "root";

    //TEST
    //  private static String url = "jdbc:mysql://127.0.0.1:3306/import";
    //   private static String username = "root";
    //   private static String password = "Tetragramaton123";

    public static void connectionTest() {


        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from import_list");
            while (rs.next()) System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }


    private Connection getConnectcion() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public String getDepartmentDistrubotorAccountPosition(String department, Integer departmentPosition) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        String account = "";
        statement = connection.prepareStatement("SELECT konto FROM import.korg A left join import.korg_konta B on A.id=B.korg_id where kod=? and lp=?;");
        statement.setString(1, department);
        statement.setInt(2, departmentPosition);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) account = rs.getString("konto");
        connection.close();
        return account;
    }



    public Double getpartSumByDepartment(Integer importId, Integer listId, String elementName, String department) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Double check = 0d;
        statement = connection.prepareStatement("SELECT sum(wartoscElementu) as suma FROM import.wyplata  A " + "left join element_wyplaty B on B.id_importu=A.id_importu and B.id_wyplata=A.id_wyplaty and B.id_listy=A.id_listy " + "where A.id_importu=? and A.id_listy=? and nazwaElementu=? and A.kodWydzialuKosztowego=?");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        statement.setString(3, elementName);
        statement.setString(4, department);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = Math.abs(rs.getDouble("suma"));
        connection.close();
        return check;
    }

    public PodatkiSkladki getTaxSUMListByIdAndByDepartment(Integer importId, Integer listId, String department) throws SQLException {
        PodatkiSkladki podatkiSkladki = null;
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("SELECT ROUND(sum(A.podatekZaliczkaUS),2) as podatekZaliczkaUS," + "ROUND(sum(A.emerytalnaPracownik),2) as emerytalnaPracownik, " + "ROUND(sum(A.rentowaPracownik),2) as rentowaPracownik," + "ROUND(sum(A.chorobowaPracownik),2) as chorobowaPracownik," + "ROUND(sum(A.wypadkowaPracownik),2) as wypadkowaPracownik," + "ROUND(sum(A.emerytalnaFirma),2) as emerytalnaFirma," + "ROUND(sum(A.rentowaFirma),2) as rentowaFirma," + "ROUND(sum(A.chorobowaFirma),2) as chorobowaFirma," + "ROUND(sum(A.wypadkowaFirma),2) as wypadkowaFirma," + "ROUND(sum(A.zdrowotkaPracownik),2) as zdrowotkaPracownik," + "ROUND(sum(A.FP),2) as FP," + "ROUND(sum(A.FGSP),2) as FGSP," + "ROUND(sum(A.FEP),2) as FEP," + "ROUND(sum(A.PPKPracownik),2) as PPKPracownik," + "ROUND(sum(A.PPKFirma),2) as PPKFirma " + "FROM import.podatkiskladki A " + "left join import.wyplata B on B.id_importu=A.id_importu and B.id_listy=A.id_listy and A.id_wyplata=B.id_wyplaty " + "where A.id_importu=? and A.id_listy=? and B.kodWydzialuKosztowego=?  ;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        statement.setString(3, department);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            podatkiSkladki = new PodatkiSkladki();

            podatkiSkladki.setPodatekZaliczkaUS(Math.abs(rs.getDouble("podatekZaliczkaUS")));
            podatkiSkladki.setEmerytalnaPracownik(Math.abs(rs.getDouble("emerytalnaPracownik")));
            podatkiSkladki.setRentowaPracownik(Math.abs(rs.getDouble("rentowaPracownik")));
            podatkiSkladki.setChorobowaPracownik(Math.abs(rs.getDouble("chorobowaPracownik")));
            podatkiSkladki.setWypadkowaPracownik(Math.abs(rs.getDouble("wypadkowaPracownik")));
            podatkiSkladki.setEmerytalnaFirma(Math.abs(rs.getDouble("emerytalnaFirma")));
            podatkiSkladki.setRentowaFirma(Math.abs(rs.getDouble("rentowaFirma")));
            podatkiSkladki.setChorobowaFirma(Math.abs(rs.getDouble("chorobowaFirma")));
            podatkiSkladki.setWypadkowaFirma(Math.abs(rs.getDouble("wypadkowaFirma")));
            podatkiSkladki.setZdrowotnaPracownik(Math.abs(rs.getDouble("zdrowotkaPracownik")));
            podatkiSkladki.setFP(Math.abs(rs.getDouble("FP")));
            podatkiSkladki.setFGSP(Math.abs(rs.getDouble("FGSP")));
            podatkiSkladki.setFEP(Math.abs(rs.getDouble("FEP")));
            podatkiSkladki.setPPKPracownik(Math.abs(rs.getDouble("PPKPracownik")));
            podatkiSkladki.setPPKFirma(Math.abs(rs.getDouble("PPKFirma")));
        }
        connection.close();
        return podatkiSkladki;
    }

    public Double getPaymentSumByDepartement(Integer importId, Integer listId, String department) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Double check = 0d;
        statement = connection.prepareStatement("SELECT sum(doWyplaty) as suma FROM import.wyplata where id_importu=? and id_listy=? and kodWydzialuKosztowego=?;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        statement.setString(3, department);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getDouble("suma");
        connection.close();
        return check;
    }

    public List<String> getDepartmentListByList(Integer importId, Integer listId) throws SQLException {
        List<String> departmentList = new ArrayList<String>();
        Connection connection = getConnectcion();
        PreparedStatement statement;
        statement = connection.prepareStatement("SELECT kodWydzialuKosztowego as wydzial FROM import.wyplata where id_importu=? and id_listy=? group by kodWydzialuKosztowego order by kodWydzialuKosztowego;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        ResultSet rs = statement.executeQuery();
        String departemnt = "";
        while (rs.next()) {
            departemnt = rs.getString("wydzial");
            departmentList.add(departemnt);
        }
        connection.close();
        return departmentList;
    }

    public void updateBookKeepingPatternsOnePositionsById(BookKeepingPatternsPosition bookKeepingPatternsPosition) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("UPDATE wzorce_ksiegowania_pozycje_slownik set nazwa=?,konto_wn=?,konto_ma=?,rozdzielnik=?,rozdzielnik_konto=?,przelew=?,pozycja_rozdzielnika=? " + "where id_wzorca=? and pozycja=?");
        statement.setString(1, bookKeepingPatternsPosition.getName());
        statement.setString(2, bookKeepingPatternsPosition.getAccountBlame());
        statement.setString(3, bookKeepingPatternsPosition.getAccountHas());
        statement.setBoolean(4, bookKeepingPatternsPosition.getDistributor());
        statement.setString(5, bookKeepingPatternsPosition.getAccountDisributor());
        statement.setBoolean(6, bookKeepingPatternsPosition.getPayment());
        statement.setInt(7, bookKeepingPatternsPosition.getDistributorPosition());
        statement.setInt(8, bookKeepingPatternsPosition.getPatternId());
        statement.setInt(9, bookKeepingPatternsPosition.getPositionId());
        statement.executeUpdate();
        connection.close();
    }

    public BookKeepingPatternsPosition getBookKeepingPatternsOnePositionsById(Integer id) throws SQLException {
        BookKeepingPatternsPosition bookKeepingPatternsPosition = null;
        Connection connection = getConnectcion();
        PreparedStatement statement;
        statement = connection.prepareStatement("Select id_wzorca,pozycja,nazwa,konto_wn,konto_ma,rozdzielnik,rozdzielnik_konto,przelew,pozycja_rozdzielnika from wzorce_ksiegowania_pozycje_slownik where id_wzorca=? order by pozycja;");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            bookKeepingPatternsPosition = new BookKeepingPatternsPosition();
            bookKeepingPatternsPosition.setPatternId(rs.getInt("id_wzorca"));
            bookKeepingPatternsPosition.setPositionId(rs.getInt("pozycja"));
            bookKeepingPatternsPosition.setAccountDisributor(rs.getString("rozdzielnik_konto"));
            bookKeepingPatternsPosition.setDistributor(rs.getBoolean("rozdzielnik"));
            bookKeepingPatternsPosition.setAccountHas(rs.getString("konto_ma"));
            bookKeepingPatternsPosition.setAccountBlame(rs.getString("konto_wn"));
            bookKeepingPatternsPosition.setName(rs.getString("nazwa"));
            bookKeepingPatternsPosition.setPayment(rs.getBoolean("przelew"));
            bookKeepingPatternsPosition.setDistributorPosition(rs.getInt("pozycja_rozdzielnika"));
        }
        connection.close();
        return bookKeepingPatternsPosition;
    }

    public PodatkiSkladki getTaxSUMListById(Integer importId, Integer listId) throws SQLException {
        PodatkiSkladki podatkiSkladki = null;
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("SELECT ROUND(sum(podatekZaliczkaUS),2) as podatekZaliczkaUS," + "ROUND(sum(emerytalnaPracownik),2) as emerytalnaPracownik, " + "ROUND(sum(rentowaPracownik),2) as rentowaPracownik," + "ROUND(sum(chorobowaPracownik),2) as chorobowaPracownik," + "ROUND(sum(wypadkowaPracownik),2) as wypadkowaPracownik," + "ROUND(sum(emerytalnaFirma),2) as emerytalnaFirma," + "ROUND(sum(rentowaFirma),2) as rentowaFirma," + "ROUND(sum(chorobowaFirma),2) as chorobowaFirma," + "ROUND(sum(wypadkowaFirma),2) as wypadkowaFirma," + "ROUND(sum(zdrowotkaPracownik),2) as zdrowotkaPracownik," + "ROUND(sum(FP),2) as FP," + "ROUND(sum(FGSP),2) as FGSP," + "ROUND(sum(FEP),2) as FEP," + "ROUND(sum(PPKPracownik),2) as PPKPracownik," + "ROUND(sum(PPKFirma),2) as PPKFirma " + "FROM import.podatkiskladki where id_importu=? and id_listy=?;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            podatkiSkladki = new PodatkiSkladki();

            podatkiSkladki.setPodatekZaliczkaUS(Math.abs(rs.getDouble("podatekZaliczkaUS")));
            podatkiSkladki.setEmerytalnaPracownik(Math.abs(rs.getDouble("emerytalnaPracownik")));
            podatkiSkladki.setRentowaPracownik(Math.abs(rs.getDouble("rentowaPracownik")));
            podatkiSkladki.setChorobowaPracownik(Math.abs(rs.getDouble("chorobowaPracownik")));
            podatkiSkladki.setWypadkowaPracownik(Math.abs(rs.getDouble("wypadkowaPracownik")));
            podatkiSkladki.setEmerytalnaFirma(Math.abs(rs.getDouble("emerytalnaFirma")));
            podatkiSkladki.setRentowaFirma(Math.abs(rs.getDouble("rentowaFirma")));
            podatkiSkladki.setChorobowaFirma(Math.abs(rs.getDouble("chorobowaFirma")));
            podatkiSkladki.setWypadkowaFirma(Math.abs(rs.getDouble("wypadkowaFirma")));
            podatkiSkladki.setZdrowotnaPracownik(Math.abs(rs.getDouble("zdrowotkaPracownik")));
            podatkiSkladki.setFP(Math.abs(rs.getDouble("FP")));
            podatkiSkladki.setFGSP(Math.abs(rs.getDouble("FGSP")));
            podatkiSkladki.setFEP(Math.abs(rs.getDouble("FEP")));
            podatkiSkladki.setPPKPracownik(Math.abs(rs.getDouble("PPKPracownik")));
            podatkiSkladki.setPPKFirma(Math.abs(rs.getDouble("PPKFirma")));
        }
        connection.close();
        return podatkiSkladki;
    }

    public Double getPaymentSum(Integer importId, Integer listId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Double check = 0d;
        statement = connection.prepareStatement("SELECT sum(doWyplaty) as suma FROM import.wyplata where id_importu=? and id_listy=?;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getDouble("suma");
        connection.close();
        return check;
    }

    public Double getpartSum(Integer importId, Integer listId, String elementName) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Double check = 0d;
        statement = connection.prepareStatement("SELECT round(sum(wartoscElementu),2) as suma FROM import.element_wyplaty where id_importu=? and id_listy=? and nazwaElementu=?;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        statement.setString(3, elementName);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = Math.abs(rs.getDouble("suma"));
        connection.close();
        return check;
    }

    public void deleteDepartmentPositionById(DepartmentDistributorPositionFX departmentDistributorPositionFX) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from korg_konta where korg_id=? and lp=?");
        statement.setInt(1, departmentDistributorPositionFX.getKorg_id());
        statement.setInt(2, departmentDistributorPositionFX.getId());
        statement.executeUpdate();
        connection.close();
    }

    public List<DepartmentDistributorPosition> getDepartmentPosition(Integer departmentId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        List<DepartmentDistributorPosition> departmentsPositions = new ArrayList<>();
        statement = connection.prepareStatement(" SELECT korg_id,lp,konto FROM korg_konta where korg_id=? order by lp");
        statement.setInt(1, departmentId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            DepartmentDistributorPosition departmentDistributorPosition = new DepartmentDistributorPosition();
            departmentDistributorPosition.setKorg_id(rs.getInt("korg_id"));
            departmentDistributorPosition.setId(rs.getInt("lp"));
            departmentDistributorPosition.setAccount(rs.getString("konto"));
            departmentsPositions.add(departmentDistributorPosition);
        }
        connection.close();
        return departmentsPositions;
    }

    public void addDepartmentDistributorPosition(DepartmentDistributorPosition departmentDistributorPosition) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO korg_konta (korg_id,lp,konto) VALUES (?,?,?);");
        statement.setInt(1, departmentDistributorPosition.getKorg_id());
        statement.setInt(2, departmentDistributorPosition.getId());
        statement.setString(3, departmentDistributorPosition.getAccount());
        statement.executeUpdate();
        connection.close();
    }

    public Integer checkDepartmentDistributorPositionById(DepartmentDistributorPosition departmentDistributorPosition) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Integer check = 0;
        statement = connection.prepareStatement("Select count(*) as ilosc from korg_konta where korg_id=? and lp=?;");
        statement.setInt(1, departmentDistributorPosition.getKorg_id());
        statement.setInt(2, departmentDistributorPosition.getId());
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getInt("ilosc");
        connection.close();
        return check;
    }

    public void addDepartment(Department department) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO korg (id,kod,nazwa) VALUES (?,?,?);");
        statement.setInt(1, department.getId());
        statement.setString(2, department.getCode());
        statement.setString(3, department.getName());
        statement.executeUpdate();
        connection.close();
    }


    public void updatesDepartmentsById(Department department) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("UPDATE korg set kod=?,nazwa=? where id=?");
        statement.setString(1, department.getCode());
        statement.setString(2, department.getName());
        statement.setInt(3, department.getId());
        statement.executeUpdate();
        connection.close();
    }

    public Integer checkdepartmentById(Integer id) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Integer check = 0;
        statement = connection.prepareStatement("Select count(*) as ilosc from korg where id=?;");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getInt("ilosc");
        connection.close();
        return check;
    }


    public List<Department> getDepartment() throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        List<Department> departments = new ArrayList<>();
        statement = connection.prepareStatement(" SELECT id,kod,nazwa FROM korg order by id");
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


    public Integer checkListPatternById(Integer patternId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Integer check = 0;
        statement = connection.prepareStatement("Select count(*) as ilosc from lista_plac_wzorce where id_wzorca=?;");
        statement.setInt(1, patternId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getInt("ilosc");
        connection.close();
        return check;
    }

    public void updatesPartsById(PartsFX partsFX, Integer patternId, Integer positionId) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("UPDATE skladniki set symbol=(select znak from (Select case when symbol='+' then '-' else '+' end as znak from skladniki where id_wzorca=? and pozycja=? and lp=?) X) where id_wzorca=? and pozycja=? and lp=?");
        statement.setInt(1, patternId);
        statement.setInt(2, positionId);
        statement.setInt(3, partsFX.getId());
        statement.setInt(4, patternId);
        statement.setInt(5, positionId);
        statement.setInt(6, partsFX.getId());
        statement.executeUpdate();
        connection.close();
    }

    public void deletePartsById(PartsFX partsFX, Integer patternId, Integer positionId) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from skladniki where id_wzorca=? and pozycja=? and lp=?");
        statement.setInt(1, patternId);
        statement.setInt(2, positionId);
        statement.setInt(3, partsFX.getId());
        statement.executeUpdate();
        connection.close();
    }

    public List<ElementSlownik> getDictionaryElementListOnlyNoParts(Integer patternId, Integer positionId) throws SQLException {
        List<ElementSlownik> elementSlownikList = new ArrayList<>();
        Connection connection = getConnectcion();
        PreparedStatement statement;
        statement = connection.prepareStatement("Select A.id,A.nazwa,A.alias,A.czy_drukowac,A.kolejnosc from elementy_slownik A where A.id not in (Select B.id_elementu from skladniki B where B.id_wzorca=? and pozycja=?)");
        statement.setInt(1, patternId);
        statement.setInt(2, positionId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            ElementSlownik elementSlownik = new ElementSlownik();
            elementSlownik.setId(rs.getInt("id"));
            elementSlownik.setNazwa(rs.getString("nazwa"));
            elementSlownik.setAlias(rs.getString("alias"));
            elementSlownik.setCzyDrukowac(rs.getBoolean("czy_drukowac"));
            elementSlownik.setKolejnosc(rs.getInt("kolejnosc"));

            elementSlownikList.add(elementSlownik);
        }
        connection.close();
        return elementSlownikList;
    }

    public List<Parts> getPartsById(Integer patternId, Integer positionid) throws SQLException {
        List<Parts> parts = new ArrayList<>();
        Connection connection = getConnectcion();
        PreparedStatement statement;
        statement = connection.prepareStatement("SELECT A.id_wzorca,A.pozycja,A.lp,A.id_elementu,B.nazwa,A.symbol  FROM import.skladniki A left join elementy_slownik B on B.id=A.id_elementu  where A.id_wzorca=? and A.pozycja=? order by lp;");
        statement.setInt(1, patternId);
        statement.setInt(2, positionid);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {

            Parts parts1 = new Parts();
            parts1.setPatternId(rs.getInt("id_wzorca"));
            parts1.setPositionId(rs.getInt("pozycja"));
            parts1.setId(rs.getInt("lp"));
            parts1.setPartsId(rs.getInt("id_elementu"));
            parts1.setPartsName(rs.getString("nazwa"));
            parts1.setSymbol(rs.getString("symbol"));
            parts.add(parts1);
        }
        connection.close();
        return parts;
    }

    public void addBookKeepingPart(Integer patternId, Integer position, ElementSlownikFX elementSlownikFX) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Insert into skladniki (id_wzorca,pozycja,lp,id_elementu,symbol) values (?,?,(Select idek from (Select coalesce(max(A.lp)+1,1) as idek from skladniki A where A.id_wzorca=? and A.pozycja=?) x ),?,?)");
        statement.setInt(1, patternId);
        statement.setInt(2, position);
        statement.setInt(3, patternId);
        statement.setInt(4, position);
        statement.setInt(5, elementSlownikFX.getId());
        statement.setString(6, "+");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteBookKeepingPatternPositionById(Integer patternId, Integer positionId) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from wzorce_ksiegowania_pozycje_slownik where id_wzorca=? and pozycja=?");
        statement.setInt(1, patternId);
        statement.setInt(2, positionId);
        statement.executeUpdate();
        connection.close();
    }

    public List<BookKeepingPatternsPosition> getBookKeepingPatternsPositionsById(Integer id) throws SQLException {
        List<BookKeepingPatternsPosition> bookKeepingPatternsPositionsList = new ArrayList<>();
        Connection connection = getConnectcion();
        PreparedStatement statement;
        statement = connection.prepareStatement("Select id_wzorca,pozycja,nazwa,konto_wn,konto_ma,rozdzielnik,rozdzielnik_konto,przelew,pozycja_rozdzielnika from wzorce_ksiegowania_pozycje_slownik where id_wzorca=? order by pozycja;");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            BookKeepingPatternsPosition bookKeepingPatternsPosition = new BookKeepingPatternsPosition();
            bookKeepingPatternsPosition.setPatternId(rs.getInt("id_wzorca"));
            bookKeepingPatternsPosition.setPositionId(rs.getInt("pozycja"));
            bookKeepingPatternsPosition.setAccountDisributor(rs.getString("rozdzielnik_konto"));
            bookKeepingPatternsPosition.setDistributor(rs.getBoolean("rozdzielnik"));
            bookKeepingPatternsPosition.setAccountHas(rs.getString("konto_ma"));
            bookKeepingPatternsPosition.setAccountBlame(rs.getString("konto_wn"));
            bookKeepingPatternsPosition.setName(rs.getString("nazwa"));
            bookKeepingPatternsPosition.setPayment(rs.getBoolean("przelew"));
            bookKeepingPatternsPosition.setDistributorPosition(rs.getInt("pozycja_rozdzielnika"));
            bookKeepingPatternsPositionsList.add(bookKeepingPatternsPosition);
        }
        connection.close();
        return bookKeepingPatternsPositionsList;
    }

    public Integer checkBookKeppingPositionId(Integer patternId, Integer positionId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Integer check = 0;
        statement = connection.prepareStatement("Select count(*) as ilosc from wzorce_ksiegowania_pozycje_slownik where id_wzorca=? and pozycja=?");
        statement.setInt(1, patternId);
        statement.setInt(2, positionId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getInt("ilosc");
        connection.close();
        return check;
    }

    public void addBookKeepingPatternPosition(BookKeepingPatternsPosition bookKeepingPatternsPosition) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Insert into wzorce_ksiegowania_pozycje_slownik (id_wzorca,pozycja,nazwa,konto_wn,konto_ma,rozdzielnik,rozdzielnik_konto,przelew,pozycja_rozdzielnika) values (?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, bookKeepingPatternsPosition.getPatternId());
        statement.setInt(2, bookKeepingPatternsPosition.getPositionId());
        statement.setString(3, bookKeepingPatternsPosition.getName());
        statement.setString(4, bookKeepingPatternsPosition.getAccountBlame());
        statement.setString(5, bookKeepingPatternsPosition.getAccountHas());
        statement.setBoolean(6, bookKeepingPatternsPosition.getDistributor());
        statement.setString(7, bookKeepingPatternsPosition.getAccountDisributor());
        statement.setBoolean(8, bookKeepingPatternsPosition.getPayment());
        statement.setInt(9, bookKeepingPatternsPosition.getDistributorPosition());
        statement.executeUpdate();
        connection.close();
    }

    public Boolean checkBookKeppingGenerate(Integer idImport) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Boolean check = false;
        statement = connection.prepareStatement("Select ks from import_list where id=?");
        statement.setInt(1, idImport);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getBoolean("ks");
        connection.close();
        return check;
    }

    public void updateImportListBookKeppingStatus(Integer importId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update import_list set ks=? where id=?");
        statement.setBoolean(1, true);
        statement.setInt(2, importId);
        statement.executeUpdate();
        connection.close();
    }

    public BookKeepingPatterns getBookKeepingPatternsById(Integer id) throws SQLException {
        BookKeepingPatterns bookKeepingPatterns = null;
        Connection connection = getConnectcion();
        PreparedStatement statement;
        statement = connection.prepareStatement("Select A.id,A.nazwa_wzorca,A.typ_wzorca,B.nazwa_typu,A.uwagi from wzorce_ksiegowania A left join typy_ksiegowania B on A.typ_wzorca=B.id where A.id=?;");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            bookKeepingPatterns = new BookKeepingPatterns();
            bookKeepingPatterns.setId(rs.getInt("id"));
            bookKeepingPatterns.setPatterName(rs.getString("nazwa_wzorca"));
            bookKeepingPatterns.setPatternType(rs.getInt("typ_wzorca"));
            bookKeepingPatterns.setPatternTypeName(rs.getString("nazwa_typu"));
            bookKeepingPatterns.setPatternComment(rs.getString("uwagi"));

        }
        connection.close();
        return bookKeepingPatterns;
    }

    public void updatePayListPattern(PayListPatternFX payListPatternFX) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update lista_plac_wzorce set id_wzorca=? where id_importu=? and id_listy=?");
        statement.setInt(1, payListPatternFX.getBookKeepingPatternType());
        statement.setInt(2, payListPatternFX.getImportId());
        statement.setInt(3, payListPatternFX.getIdList());
        statement.executeUpdate();
        connection.close();
    }

    public List<PaylistPattern> getBookKeepingPayListPattern(Integer importId) throws SQLException {
        List<PaylistPattern> paylistPatterns = new ArrayList<>();
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("SELECT A.id_importu,A.id_listy,B.numerListy,B.opisListy,B.dataListy,B.dataWyplaty,B.okresListyOd,B.okresListyDo,B.kodWydzialu,B.zatwierdzona,A.id_wzorca,C.nazwa_wzorca, A.zaksiegowana " + "FROM lista_plac_wzorce A left join lista_plac B on B.id_importu=A.id_importu and B.id_listy=A.id_listy left join wzorce_ksiegowania C on C.id=A.id_wzorca where A.id_importu=? order by A.id_listy");
        statement.setInt(1, importId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            PaylistPattern paylistPattern = new PaylistPattern();
            paylistPattern.setImportId(rs.getInt("id_importu"));
            paylistPattern.setIdList(rs.getInt("id_listy"));
            paylistPattern.setNumberList(rs.getString("numerListy"));
            paylistPattern.setDescriptionList(rs.getString("opisListy"));
            paylistPattern.setDateList(rs.getDate("dataListy"));
            paylistPattern.setDatePayment(rs.getDate("dataWyplaty"));
            paylistPattern.setDateFrom(rs.getDate("okresListyOd"));
            paylistPattern.setDateTo(rs.getDate("okresListyDo"));
            paylistPattern.setDepartmentCode(rs.getString("kodWydzialu"));
            paylistPattern.setAgreeList(rs.getBoolean("zatwierdzona"));
            paylistPattern.setBookKeepingPatternType(rs.getInt("id_wzorca"));
            paylistPattern.setBookKeepingPatterntTypeName(rs.getString("nazwa_wzorca"));
            paylistPattern.setBook(rs.getBoolean("zaksiegowana"));

            paylistPatterns.add(paylistPattern);
        }
        connection.close();
        return paylistPatterns;
    }

    public void generateBookKeepingPatternList(Integer idImport) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Insert into lista_plac_wzorce (id_importu,id_listy,id_wzorca,zaksiegowana) Select id_importu,id_listy,0,false from lista_plac where id_importu=? and id_listy not in (Select id_listy from lista_plac_wzorce where id_importu=?)");
        statement.setInt(1, idImport);
        statement.setInt(2, idImport);
        statement.executeUpdate();
        connection.close();
    }

    public void deleteBookKeepingPatternById(Integer id) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from wzorce_ksiegowania where id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }

    public void addNewBookKeepingPattern(BookKeepingPatterns bookKeepingPatterns) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO wzorce_ksiegowania(id,nazwa_wzorca,typ_wzorca,uwagi) VALUES ((Select B.idek from (Select coalesce(max(id+1),1) as idek from wzorce_ksiegowania) B),?,?,?);");
        statement.setString(1, bookKeepingPatterns.getPatterName());
        statement.setInt(2, bookKeepingPatterns.getPatternType());
        statement.setString(3, bookKeepingPatterns.getPatternComment());
        statement.executeUpdate();
        connection.close();
    }

    public List<BookKeepingPatterns> getBookKeepingPatterns() throws SQLException {
        List<BookKeepingPatterns> bookKeepingPatternsList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select A.id,A.nazwa_wzorca,A.typ_wzorca,B.nazwa_typu,A.uwagi from wzorce_ksiegowania A left join typy_ksiegowania B on A.typ_wzorca=B.id;");
        while (rs.next()) {
            BookKeepingPatterns bookKeepingPatterns = new BookKeepingPatterns();
            bookKeepingPatterns.setId(rs.getInt("id"));
            bookKeepingPatterns.setPatterName(rs.getString("nazwa_wzorca"));
            bookKeepingPatterns.setPatternType(rs.getInt("typ_wzorca"));
            bookKeepingPatterns.setPatternTypeName(rs.getString("nazwa_typu"));
            bookKeepingPatterns.setPatternComment(rs.getString("uwagi"));
            bookKeepingPatternsList.add(bookKeepingPatterns);
        }
        connection.close();
        return bookKeepingPatternsList;
    }

    public List<BookKeepingPatternType> getBookKeepingPatternsType() throws SQLException {
        List<BookKeepingPatternType> bookKeepingPatternTypes = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select id,nazwa_typu from typy_ksiegowania order by id;");
        while (rs.next()) {
            BookKeepingPatternType bookKeepingPatterns = new BookKeepingPatternType();
            bookKeepingPatterns.setId(rs.getInt("id"));
            bookKeepingPatterns.setName(rs.getString("nazwa_typu"));
            bookKeepingPatternTypes.add(bookKeepingPatterns);
        }
        connection.close();
        return bookKeepingPatternTypes;
    }

    public void deleteAllPersons() throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("delete from lista_pracownikow;");
        statement.executeUpdate();
        connection.close();
    }


    public Boolean checkImportName(String importName) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Boolean check = false;
        statement = connection.prepareStatement("Select count(*) as nameCount from import_list where opis=?");
        statement.setString(1, importName);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = (rs.getInt("nameCount") > 0);
        connection.close();
        return check;
    }

    public String getEmail(Integer code) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        String email = "";
        statement = connection.prepareStatement("Select email from lista_pracownikow where kod_pracownika=? and czy_wyslac=1");
        statement.setInt(1, code);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) email = rs.getString("email");
        connection.close();
        return email;
    }

    public void updateSendEmailStatus(SendMailFX sendMailFX) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update send_mail set czy_wyslano=? where id_importu=? and id_listy=? and id_wyplaty=? and kod=?");
        statement.setBoolean(1, sendMailFX.getIsSend());
        statement.setInt(2, sendMailFX.getImportId());
        statement.setInt(3, sendMailFX.getListId());
        statement.setInt(4, sendMailFX.getAmountId());
        statement.setInt(5, sendMailFX.getCode());
        statement.executeUpdate();
        connection.close();
    }

    public void updateSendEmailFile(SendMailFX sendMailFX) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update send_mail set sciezka_do_pliku=?,czy_jest_plik=? where id_importu=? and id_listy=? and id_wyplaty=? and kod=?");
        statement.setString(1, sendMailFX.getPathFile());
        statement.setInt(2, 1);
        statement.setInt(3, sendMailFX.getImportId());
        statement.setInt(4, sendMailFX.getListId());
        statement.setInt(5, sendMailFX.getAmountId());
        statement.setInt(6, sendMailFX.getCode());
        statement.executeUpdate();
        connection.close();
    }


    public List<PdfElement> getElementtoPdf(List<PdfElement> pdfElementList, SendMailFX sendMailFX) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        PdfElement pdfElement = null;
        statement = connection.prepareStatement("SELECT B.id as id,A.nazwaElementu as nazwa,sum(A.dniElementu) as czas,sum(A.dniElementu) as dni,sum(A.wartoscElementu) as wartosc,B.kolejnosc as kolejnosc,B.alias as alias FROM element_wyplaty A left join elementy_slownik B on A.nazwaElementu=B.nazwa where A.id_importu=? and A.id_listy=? and A.id_wyplata=? and B.czy_drukowac=1 group by id,nazwa,kolejnosc,alias");
        statement.setInt(1, sendMailFX.getImportId());
        statement.setInt(2, sendMailFX.getListId());
        statement.setInt(3, sendMailFX.getAmountId());
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            pdfElement = new PdfElement();
            pdfElement.setId(rs.getInt("id"));
            pdfElement.setElementName(rs.getString("nazwa"));
            pdfElement.setElementTime(rs.getString("czas"));
            pdfElement.setElementDays(rs.getInt("dni"));
            pdfElement.setElementVaule(rs.getDouble("wartosc"));
            pdfElement.setOrder(rs.getInt("kolejnosc"));
            pdfElement.setElementAlias(rs.getString("alias"));
            pdfElementList.add(pdfElement);
        }
        connection.close();
        return pdfElementList;
    }

    public List<ElementSlownik> getDictionaryTaxElementList() throws SQLException {
        List<ElementSlownik> elementSlownikList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select id,nazwa,alias,czy_drukowac,kolejnosc from elementy_slownik where id<16 order by id");
        while (rs.next()) {
            ElementSlownik elementSlownik = new ElementSlownik();
            elementSlownik.setId(rs.getInt("id"));
            elementSlownik.setNazwa(rs.getString("nazwa"));
            elementSlownik.setAlias(rs.getString("alias"));
            elementSlownik.setCzyDrukowac(rs.getBoolean("czy_drukowac"));
            elementSlownik.setKolejnosc(rs.getInt("kolejnosc"));

            elementSlownikList.add(elementSlownik);
        }
        connection.close();
        return elementSlownikList;
    }


    public PodatkiSkladki getTax(SendMailFX sendMailFX) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        PodatkiSkladki podatki = new PodatkiSkladki();
        statement = connection.prepareStatement("SELECT sum(podatekZaliczkaUS) as value1,sum(emerytalnaPracownik) as value2,sum(rentowaPracownik) as value3,sum(chorobowaPracownik) as value4,sum(wypadkowaPracownik) as value5,sum(emerytalnaFirma) as value6,sum(rentowaFirma) as value7,sum(chorobowaFirma) as value8,sum(wypadkowaFirma) as value9,sum(zdrowotkaPracownik) as value10,sum(FP) as value11,sum(FGSP) as value12,sum(FEP) as value13,sum(PPKPracownik) as value14,sum(PPKFirma) as value15 FROM podatkiskladki where id_importu=? and id_listy=? and id_wyplata=?");
        statement.setInt(1, sendMailFX.getImportId());
        statement.setInt(2, sendMailFX.getListId());
        statement.setInt(3, sendMailFX.getAmountId());
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            podatki.setPodatekZaliczkaUS(rs.getDouble("value1"));
            podatki.setEmerytalnaPracownik(rs.getDouble("value2"));
            podatki.setRentowaPracownik(rs.getDouble("value3"));
            podatki.setChorobowaPracownik(rs.getDouble("value4"));
            podatki.setWypadkowaPracownik(rs.getDouble("value5"));
            podatki.setEmerytalnaFirma(rs.getDouble("value6"));
            podatki.setRentowaFirma(rs.getDouble("value7"));
            podatki.setChorobowaFirma(rs.getDouble("value8"));
            podatki.setWypadkowaFirma(rs.getDouble("value9"));
            podatki.setZdrowotnaPracownik(rs.getDouble("value10"));
            podatki.setFP(rs.getDouble("value11"));
            podatki.setFGSP(rs.getDouble("value12"));
            podatki.setFEP(rs.getDouble("value13"));
            podatki.setPPKPracownik(rs.getDouble("value14"));
            podatki.setPPKFirma(rs.getDouble("value15"));
        }
        connection.close();
        return podatki;
    }

    public String getImportName(Integer idImport) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        String importName = "";
        statement = connection.prepareStatement("Select opis from import_list where id=?");
        statement.setInt(1, idImport);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) importName = rs.getString("opis");
        connection.close();
        return importName;
    }

    public List<SendMail> getSendMailList(Integer importId) throws SQLException {
        List<SendMail> sendMailList = new ArrayList<>();
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("SELECT id_importu,id_listy,nazwa_listy,kod,imie_nazwisko,sciezka_do_pliku,czy_jest_plik,czy_wyslano,id_wyplaty,przelew,dataWyplaty,dataListy,kwotaStawki FROM send_mail where id_importu=? order by imie_nazwisko");
        statement.setInt(1, importId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            SendMail sendMail = new SendMail();
            sendMail.setImportId(rs.getInt("id_importu"));
            sendMail.setListId(rs.getInt("id_listy"));
            sendMail.setListName(rs.getString("nazwa_listy"));
            sendMail.setCode(rs.getInt("kod"));
            sendMail.setName(rs.getString("imie_nazwisko"));
            sendMail.setPathFile(rs.getString("sciezka_do_pliku"));
            sendMail.setFile(rs.getBoolean("czy_jest_plik"));
            sendMail.setSend(rs.getBoolean("czy_wyslano"));
            sendMail.setAmountId(rs.getInt("id_wyplaty"));
            sendMail.setAmount(rs.getDouble("przelew"));
            sendMail.setPaymentDate(rs.getDate("dataWyplaty"));
            sendMail.setListdate(rs.getDate("dataListy"));
            sendMail.setAgreementAmount(rs.getDouble("kwotaStawki"));

            sendMailList.add(sendMail);
        }
        connection.close();
        return sendMailList;
    }

    public void updateImportListEmailStatus(Integer importId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update import_list set email=? where id=?");
        statement.setBoolean(1, true);
        statement.setInt(2, importId);
        statement.executeUpdate();
        connection.close();
    }

    public void generateEmailList(Integer idImport) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO send_mail (id_importu,id_listy,nazwa_listy,kod,imie_nazwisko,sciezka_do_pliku,czy_jest_plik,czy_wyslano,id_wyplaty,przelew,dataWyplaty,dataListy,kwotaStawki) " + "SELECT A.id_importu,A.id_listy,B.numerListy,A.kodPracownika,A.nazwiskoImie,'',false,false,A.id_wyplaty,A.doWyplaty,B.dataWyplaty,B.okresListyOd,A.kwotaStawki FROM wyplata A " + "left join lista_plac B on B.id_importu=A.id_importu and B.id_listy=A.id_listy " + "where A.kodPracownika in (Select kod_pracownika from lista_pracownikow where czy_wyslac=1) and A.id_importu=? " + "and A.kodPracownika not in (Select kod from send_mail where id_importu=A.id_importu and id_listy=A.id_listy) order by A.kodPracownika;");
        statement.setInt(1, idImport);
        statement.executeUpdate();
        connection.close();
    }

    public Boolean checkEmailGenerate(Integer idImport) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        Boolean check = false;
        statement = connection.prepareStatement("Select email from import_list where id=?");
        statement.setInt(1, idImport);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) check = rs.getBoolean("email");
        connection.close();
        return check;
    }

    public void addNewPersonDictionary(SimplePerson person) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO lista_pracownikow(kod_pracownika,imie_nazwisko,email,czy_wyslac) VALUES (?,?,?,?)");
        statement.setInt(1, person.getCode());
        statement.setString(2, person.getName());
        statement.setString(3, "");
        statement.setBoolean(4, false);
        statement.executeUpdate();
        connection.close();
    }

    public void addNewPersonFromEnova(Person person) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO lista_pracownikow(kod_pracownika,imie_nazwisko,email,czy_wyslac) VALUES (?,?,?,?)");
        statement.setInt(1, person.getCode());
        statement.setString(2, person.getName());
        statement.setString(3, person.getEmail());
        statement.setBoolean(4, person.getSend());
        statement.executeUpdate();
        connection.close();
    }

    public void updateDictionaryPersonEmail(Integer personCode, String email) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update lista_pracownikow set email=? where kod_pracownika=?");
        statement.setString(1, email);
        statement.setInt(2, personCode);
        statement.executeUpdate();
        connection.close();
    }

    public void updateDictionaryPersonSend(Integer personCode, Boolean send) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update lista_pracownikow set czy_wyslac=? where kod_pracownika=?");
        statement.setBoolean(1, send);
        statement.setInt(2, personCode);
        statement.executeUpdate();
        connection.close();
    }

    public List<Person> getPersonDictionaryList() throws SQLException {
        List<Person> personList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT kod_pracownika,imie_nazwisko,email,czy_wyslac FROM import.lista_pracownikow order by imie_nazwisko");
        while (rs.next()) {
            Person person = new Person();
            person.setCode(rs.getInt("kod_pracownika"));
            person.setName(rs.getString("imie_nazwisko"));
            person.setEmail(rs.getString("email"));
            person.setSend(rs.getBoolean("czy_wyslac"));
            personList.add(person);
        }
        connection.close();
        return personList;
    }

    public List<SimplePerson> getNewPersonDictionaryPosition() throws SQLException {
        List<SimplePerson> personList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select distinct kodPracownika,nazwiskoImie from wyplata where kodPracownika not in (Select kod_pracownika from lista_pracownikow) order by nazwiskoimie");
        while (rs.next()) {
            SimplePerson person = new SimplePerson();
            person.setCode(rs.getInt("kodPracownika"));
            person.setName(rs.getString("nazwiskoImie"));
            personList.add(person);
        }
        connection.close();
        return personList;
    }


    public Integer getCountNewPersonPersonList() throws SQLException {
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        Integer count = 0;
        ResultSet rs = statement.executeQuery("Select count(distinct kodPracownika) as ilosc from wyplata where kodPracownika not in (Select kod_pracownika from lista_pracownikow)");
        while (rs.next()) {
            count = rs.getInt("ilosc");
        }
        connection.close();
        return count;
    }

    public void updateDictionaryElementPrint(Integer elementId, Boolean print) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update elementy_slownik set czy_drukowac=? where id=?");
        statement.setBoolean(1, print);
        statement.setInt(2, elementId);
        statement.executeUpdate();
        connection.close();
    }

    public void updateDictionaryElementAlias(Integer elementId, String order) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update elementy_slownik set alias=? where id=?");
        statement.setString(1, order);
        statement.setInt(2, elementId);
        statement.executeUpdate();
        connection.close();
    }

    public void updateDictionaryElementOrder(Integer elementId, Integer order) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update elementy_slownik set kolejnosc=? where id=?");
        statement.setInt(1, order);
        statement.setInt(2, elementId);
        statement.executeUpdate();
        connection.close();
    }

    public Integer getNewElementDictionaryPositionCount() throws SQLException {
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        Integer count = 0;
        ResultSet rs = statement.executeQuery("SELECT  count(distinct nazwaElementu) as ilosc FROM import.element_wyplaty A where nazwaElementu not in (Select nazwa from import.elementy_slownik)");
        while (rs.next()) {
            count = rs.getInt("ilosc");
        }
        connection.close();
        return count;
    }

    public void addNewElementDictionary(Element element) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO elementy_slownik (id,nazwa,alias,czy_drukowac,kolejnosc) VALUES ((Select coalesce(max(a.id+1),1) from import.elementy_slownik a),?,'',false,0)");
        statement.setString(1, element.getElementName());
        statement.executeUpdate();
        connection.close();
    }

    public List<Element> getNewElementDictionaryPosition() throws SQLException {
        List<Element> elementList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT  distinct nazwaElementu as nazwa FROM import.element_wyplaty A where nazwaElementu not in (Select nazwa from import.elementy_slownik) order by nazwaElementu");
        while (rs.next()) {
            Element element = new Element();
            element.setElementName(rs.getString("nazwa"));
            elementList.add(element);
        }
        connection.close();
        return elementList;
    }

    public List<ElementSlownik> getDictionaryElementList() throws SQLException {
        List<ElementSlownik> elementSlownikList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select id,nazwa,alias,czy_drukowac,kolejnosc from elementy_slownik");
        while (rs.next()) {
            ElementSlownik elementSlownik = new ElementSlownik();
            elementSlownik.setId(rs.getInt("id"));
            elementSlownik.setNazwa(rs.getString("nazwa"));
            elementSlownik.setAlias(rs.getString("alias"));
            elementSlownik.setCzyDrukowac(rs.getBoolean("czy_drukowac"));
            elementSlownik.setKolejnosc(rs.getInt("kolejnosc"));

            elementSlownikList.add(elementSlownik);
        }
        connection.close();
        return elementSlownikList;
    }

    public List<ImportModel> getImportList() throws SQLException {
        List<ImportModel> importModelList = new ArrayList<>();
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select id,opis,data_importu,email,ks from import_list");
        while (rs.next()) {
            ImportModel importModel = new ImportModel();
            importModel.setId(rs.getInt("id"));
            importModel.setOpis(rs.getString("opis"));
            importModel.setDataImportu(rs.getDate("data_importu"));
            importModel.setEmail(rs.getBoolean("email"));
            importModel.setBookKeeping(rs.getBoolean("ks"));
            importModelList.add(importModel);
        }
        connection.close();
        return importModelList;
    }

    public List<Wyplata> getWyplataList(Integer importId, Integer listId) throws SQLException {
        List<Wyplata> wyplataList = new ArrayList<>();
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Select id_wyplaty,numerWyplaty,kodpracownika,kodWydzialuKosztowego,doWyplaty,nazwiskoImie,pesel,kwotaStawki from wyplata where id_importu=? and id_listy=?");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Wyplata wyplata = new Wyplata();
            wyplata.setId(rs.getInt("id_wyplaty"));
            wyplata.setNumerWyplaty(rs.getInt("numerWyplaty"));
            wyplata.setKodPracownika(rs.getInt("kodpracownika"));
            wyplata.setKodWydzialuKosztowego(rs.getString("kodWydzialuKosztowego"));
            wyplata.setDoWyplaty(rs.getDouble("doWyplaty"));
            wyplata.setNazwiskoImie(rs.getString("nazwiskoImie"));
            wyplata.setPesel(rs.getString("pesel"));
            wyplata.setKwotaStawki(rs.getDouble("kwotaStawki"));
            wyplataList.add(wyplata);
        }
        connection.close();
        return wyplataList;
    }

    public List<PodatkiSkladki> getTaxListById(Integer importId, Integer listId, Integer paymentId, Integer elementId) throws SQLException {
        List<PodatkiSkladki> podatkiSkladkiList = new ArrayList<>();
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("SELECT id_skladki, podatekZaliczkaUS, emerytalnaPracownik, rentowaPracownik, chorobowaPracownik, wypadkowaPracownik, emerytalnaFirma, rentowaFirma, " + "    chorobowaFirma, wypadkowaFirma, zdrowotkaPracownik, FP, FGSP, FEP, PPKPracownik, PPKFirma FROM podatkiskladki where id_importu=? and id_listy=? and id_wyplata=? and id_elementu=?;");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        statement.setInt(3, paymentId);
        statement.setInt(4, elementId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            PodatkiSkladki podatkiSkladki = new PodatkiSkladki();

            podatkiSkladki.setIdPodatku(rs.getInt("id_skladki"));
            podatkiSkladki.setPodatekZaliczkaUS(rs.getDouble("podatekZaliczkaUS"));
            podatkiSkladki.setEmerytalnaPracownik(rs.getDouble("emerytalnaPracownik"));
            podatkiSkladki.setRentowaPracownik(rs.getDouble("rentowaPracownik"));
            podatkiSkladki.setChorobowaPracownik(rs.getDouble("chorobowaPracownik"));
            podatkiSkladki.setWypadkowaPracownik(rs.getDouble("wypadkowaPracownik"));
            podatkiSkladki.setEmerytalnaFirma(rs.getDouble("emerytalnaFirma"));
            podatkiSkladki.setRentowaFirma(rs.getDouble("rentowaFirma"));
            podatkiSkladki.setChorobowaFirma(rs.getDouble("chorobowaFirma"));
            podatkiSkladki.setWypadkowaFirma(rs.getDouble("wypadkowaFirma"));
            podatkiSkladki.setZdrowotnaPracownik(rs.getDouble("zdrowotkaPracownik"));
            podatkiSkladki.setFP(rs.getDouble("FP"));
            podatkiSkladki.setFGSP(rs.getDouble("FGSP"));
            podatkiSkladki.setFEP(rs.getDouble("FEP"));
            podatkiSkladki.setPPKPracownik(rs.getDouble("PPKPracownik"));
            podatkiSkladki.setPPKFirma(rs.getDouble("PPKFirma"));

            podatkiSkladkiList.add(podatkiSkladki);
        }
        connection.close();
        return podatkiSkladkiList;
    }


    public List<ElementWyplaty> getElementListById(Integer importId, Integer listId, Integer paymentId) throws SQLException {
        List<ElementWyplaty> elementWyplatyList = new ArrayList<>();
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Select id_elementu,nazwaElementu,kodElementu,okresElementuOd,okresElementuDo,czasElementu,dniElementu,wartoscElementu from element_wyplaty where id_importu=? and id_listy=? and id_wyplata=?");
        statement.setInt(1, importId);
        statement.setInt(2, listId);
        statement.setInt(3, paymentId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            ElementWyplaty elementWyplaty = new ElementWyplaty();
            elementWyplaty.setIdElementu(rs.getInt("id_elementu"));
            elementWyplaty.setNazwaElementu(rs.getString("nazwaElementu"));
            elementWyplaty.setKodElementu(rs.getString("kodElementu"));
            elementWyplaty.setOkresElementuOd(rs.getDate("okresElementuOd"));
            elementWyplaty.setOkresElementuDo(rs.getDate("okresElementuDo"));
            elementWyplaty.setCzasElementu(rs.getString("czasElementu"));
            elementWyplaty.setDniElementu(rs.getInt("dniElementu"));
            elementWyplaty.setWartoscElementu(rs.getDouble("wartoscElementu"));
            elementWyplatyList.add(elementWyplaty);
        }
        connection.close();
        return elementWyplatyList;
    }

    public List<ListaPlac> getListaPlacListById(Integer importId) throws SQLException {
        List<ListaPlac> listaPlacList = new ArrayList<>();
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("SELECT id_listy,numerListy,opisListy,dataListy,dataWyplaty,okresListyOd,okresListyDo,kodWydzialu,zatwierdzona FROM lista_plac where id_importu=?");
        statement.setInt(1, importId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            ListaPlac listaPlac = new ListaPlac();
            listaPlac.setIdListy(rs.getInt("id_listy"));
            listaPlac.setNumerListy(rs.getString("numerListy"));
            listaPlac.setOpisListy(rs.getString("opisListy"));
            listaPlac.setDataListy(rs.getDate("dataListy"));
            listaPlac.setDataWyplaty(rs.getDate("dataWyplaty"));
            listaPlac.setOkresListyOd(rs.getDate("okresListyOd"));
            listaPlac.setOkresListyDo(rs.getDate("okresListyDo"));
            listaPlac.setKodWydzialu(rs.getString("kodWydzialu"));
            listaPlac.setZatwierdzona(rs.getBoolean("zatwierdzona"));
            listaPlacList.add(listaPlac);
        }
        connection.close();
        return listaPlacList;
    }

    public void deleteSendMail() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from send_mail");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteImportList() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from import_list");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteBookKeepingListPattern() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from lista_plac_wzorce");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteBookKeepingPattern() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from wzorce_ksiegowania");
        statement.executeUpdate();
        connection.close();
    }


    public void deleteListaPlac() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from lista_plac");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteWyplata() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from wyplata");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteElementWyplaty() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from element_wyplaty");
        statement.executeUpdate();
        connection.close();
    }

    public void deletePodatkiSkladki() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from podatkiskladki");
        statement.executeUpdate();
        connection.close();
    }

    public void deleteElementWyplatySlownik() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from elementy_slownik");
        statement.executeUpdate();
        connection.close();
    }

    public void deletePersonSlownik() throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from lista_pracownikow");
        statement.executeUpdate();
        connection.close();
    }

    public Integer getImportId() throws SQLException {
        Integer id = null;
        Connection connection = getConnectcion();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select coalesce(max(a.id+1),1) as id from import.import_list a");
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public void addNewImport(ImportModel importModel, Integer importId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO import.import_list(id,opis,data_importu,email,ks)VALUES ((Select coalesce(max(a.id+1),1) from import.import_list a),?,?,?,?);");
        statement.setString(1, importModel.getOpis());
        statement.setDate(2, new Date(importModel.getDataImportu().getTime()));
        statement.setBoolean(3, false);
        statement.setBoolean(4, false);
        statement.executeUpdate();
        connection.close();
    }

    public void addListaPlac(ListaPlac listaPlac, Integer idListy, Integer idImportu) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO import.lista_plac(id_importu,id_listy,numerListy,opisListy,dataListy,dataWyplaty,okresListyOd,okresListyDo," + "kodWydzialu,zatwierdzona) VALUES (?,?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, idImportu);
        statement.setInt(2, idListy);
        statement.setString(3, listaPlac.getNumerListy());
        statement.setString(4, listaPlac.getOpisListy());
        statement.setDate(5, new Date(listaPlac.getDataListy().getTime()));
        statement.setDate(6, new Date(listaPlac.getDataWyplaty().getTime()));
        statement.setDate(7, new Date(listaPlac.getOkresListyOd().getTime()));
        statement.setDate(8, new Date(listaPlac.getOkresListyDo().getTime()));
        statement.setString(9, listaPlac.getKodWydzialu());
        statement.setBoolean(10, listaPlac.getZatwierdzona());
        statement.executeUpdate();
        connection.close();
    }

    public void addWyplata(Wyplata wyplata, Integer idListy, Integer idWyplaty, Integer idImportu) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO import.wyplata(id_importu,id_listy,id_wyplaty,numerWyplaty,kodPracownika,kodWydzialuKosztowego,doWyplaty,nazwiskoImie,pesel,kwotaStawki) VALUES (?,?,?,?,?,?,?,?,?,?);");
        statement.setInt(1, idImportu);
        statement.setInt(2, idListy);
        statement.setInt(3, idWyplaty);
        statement.setInt(4, wyplata.getNumerWyplaty());
        statement.setInt(5, wyplata.getKodPracownika());
        statement.setString(6, wyplata.getKodWydzialuKosztowego());
        statement.setDouble(7, wyplata.getDoWyplaty());
        statement.setString(8, wyplata.getNazwiskoImie());
        statement.setString(9, wyplata.getPesel());
        statement.setDouble(10, wyplata.getKwotaStawki());

        statement.executeUpdate();
        connection.close();
    }

    public void addElementWyplaty(ElementWyplaty elementWyplaty, Integer idWyplaty, Integer idElementu, Integer idListy, Integer idImportu) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO import.element_wyplaty (id_importu,id_listy,id_wyplata,id_elementu,nazwaElementu,kodElementu,okresElementuOd,okresElementuDo,czasElementu,dniElementu,wartoscElementu)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, idImportu);
        statement.setInt(2, idListy);
        statement.setInt(3, idWyplaty);
        statement.setInt(4, idElementu);
        statement.setString(5, elementWyplaty.getNazwaElementu());
        statement.setString(6, elementWyplaty.getKodElementu());
        statement.setDate(7, new Date(elementWyplaty.getOkresElementuOd().getTime()));
        statement.setDate(8, new Date(elementWyplaty.getOkresElementuDo().getTime()));
        statement.setString(9, elementWyplaty.getCzasElementu());
        statement.setInt(10, elementWyplaty.getDniElementu());
        statement.setDouble(11, elementWyplaty.getWartoscElementu());
        statement.executeUpdate();
        connection.close();
    }

    public void addPodatkiSkladki(PodatkiSkladki podatkiSkladki, Integer id_listy, Integer id_wyplata, Integer id_elementu, Integer id_skladki, Integer idImportu) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("INSERT INTO import.podatkiskladki" + "(id_importu,id_listy,id_wyplata,id_elementu,id_skladki,podatekZaliczkaUS,emerytalnaPracownik,rentowaPracownik,chorobowaPracownik,wypadkowaPracownik," + "emerytalnaFirma,rentowaFirma,chorobowaFirma,wypadkowaFirma,zdrowotkaPracownik,FP,FGSP,FEP,PPKPracownik,PPKFirma)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        statement.setInt(1, idImportu);
        statement.setInt(2, id_listy);
        statement.setInt(3, id_wyplata);
        statement.setInt(4, id_elementu);
        statement.setInt(5, id_skladki);
        statement.setDouble(6, podatkiSkladki.getPodatekZaliczkaUS());
        statement.setDouble(7, podatkiSkladki.getEmerytalnaPracownik());
        statement.setDouble(8, podatkiSkladki.getRentowaPracownik());
        statement.setDouble(9, podatkiSkladki.getChorobowaPracownik());
        statement.setDouble(10, podatkiSkladki.getWypadkowaPracownik());
        statement.setDouble(11, podatkiSkladki.getEmerytalnaFirma());
        statement.setDouble(12, podatkiSkladki.getRentowaFirma());
        statement.setDouble(13, podatkiSkladki.getChorobowaFirma());
        statement.setDouble(14, podatkiSkladki.getWypadkowaFirma());
        statement.setDouble(15, podatkiSkladki.getZdrowotnaPracownik());
        statement.setDouble(16, podatkiSkladki.getFP());
        statement.setDouble(17, podatkiSkladki.getFGSP());
        statement.setDouble(18, podatkiSkladki.getFEP());
        statement.setDouble(19, podatkiSkladki.getPPKPracownik());
        statement.setDouble(20, podatkiSkladki.getPPKFirma());
        statement.executeUpdate();
        connection.close();
    }


    public void deleteImportListById(Integer id) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from import_list where id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }

    public void deleteListaPlacById(Integer id) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from lista_plac where id_importu=? ");
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }

    public void deleteWyplataById(Integer id) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from wyplata where id_importu=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }

    public void deleteElementWyplatyById(Integer id) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from element_wyplaty where id_importu=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }


    public void deletePodatkiSkladkiById(Integer id) throws SQLException {
        PreparedStatement statement;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Delete from podatkiskladki where id_importu=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.close();
    }

    public void updateSendMailDeletePDF(SendMailFX item) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = getConnectcion();
        statement = connection.prepareStatement("Update send_mail set czy_jest_plik=0, czy_wyslano=0, sciezka_do_pliku='' where id_importu=? and id_listy=? and id_wyplaty=? and kod=?");
        statement.setInt(1, item.getImportId());
        statement.setInt(2, item.getListId());
        statement.setInt(3, item.getAmountId());
        statement.setInt(4, item.getCode());
        statement.executeUpdate();
        connection.close();
    }
}
