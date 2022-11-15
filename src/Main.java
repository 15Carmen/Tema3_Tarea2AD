import java.sql.*;
import java.util.Scanner;

public class Main {

    private static String servidor = "jdbc:mysql://dns11036.phdns11.es";
    private static Connection connection;
    private static Statement st = null;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        crearConexion();

    }

    private static void crearConexion() {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(servidor + "/ad2223_cmartin", "ad2223_cmartin", "1234");

            mostrarTablas();
            //companiaContactoCiudad();
            //contactoDireccionCP();
            //nombreCategoriaPrecio();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarTablas() throws SQLException {
        String sql = "SHOW TABLES";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    public static void companiaContactoCiudad() throws SQLException{
        String sql = "Select NombreCompañia, NombreContacto, Ciudad from ad2223_neptuno.Clientes";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("NombreCompañia")+"\t"+
                                rs.getString("NombreContacto")+"\t"+
                                rs.getString("Ciudad"));
        }
    }

    public static void contactoDireccionCP() throws SQLException{
        String sql = "SELECT NombreContacto, Direccion, CodPostal FROM ad2223_neptuno.Clientes ORDER BY CodPostal desc, Direccion desc";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("NombreContacto")+"\t"+
                    rs.getString("Direccion")+"\t"+
                    rs.getString("CodPostal"));
        }
    }

    public static void nombreCategoriaPrecio() throws SQLException{
        String sql = "SELECT p.NombreProducto, c.NombreCategoria, p.PrecioUnidad FROM ad2223_neptuno.Productos p, Categorias c WHERE p.IdCategoria=c.IdCategoria ORDER BY c.NombreCategoria, p.NombreProducto";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("NombreProducto")+"\t"+
                    rs.getString("NombreCategoria")+"\t"+
                    rs.getString("PrecioUnidad"));
        }
    }



}