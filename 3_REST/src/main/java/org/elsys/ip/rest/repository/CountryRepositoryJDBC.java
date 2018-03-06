package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Country;
import org.elsys.ip.rest.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CountryRepositoryJDBC {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/country"; 

    //Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";

    public static List getCountryList() {
        List countries = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        countries = session.createQuery("from Country").list();

//        Connection conn = null;
//
//        try {
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            Statement stmt = null;
//            ResultSet rs = null;
//
//            try {
//                stmt = conn.createStatement();
//                String sql = "SELECT id, name FROM country";
//                rs = stmt.executeQuery(sql);
//
//                while (rs.next()) {
//                    Country country = new Country();
//                    country.setId(rs.getInt("id"));
//                    country.setName(rs.getString("name"));
//                    countries.add(country);
//                }
//            } finally {
//                if (stmt != null && !stmt.isClosed()) {
//                    stmt.close();
//                }
//
//                if (rs != null && !rs.isClosed()) {
//                    rs.close();
//                }
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        return countries;
    }

    public static Country saveCountry(Country country) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(country);

        session.getTransaction().commit();

        return country;
    }
}
