package database;

import backend.Dish;
import backend.Order;

import java.sql.*;
import java.util.List;

public class Database {
    /**
     * �������ݿ�������Ϣ
     * ���ݱ�������޸�
     * <p>
     * ��ɿ����ڷ������ϴ���ݿ�
     */
    static final String URL = "jdbc:mysql://rm-bp10c5dfstb3h6q96mo.mysql.rds.aliyuncs.com/order_app";
    static final String USER = "user";
    static final String PASS = "User_2021";
    static Connection conn;
    static Statement stmt;

    public static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Connected to database.");
    }

    /**
     * �����û�����ѯǮ�����
     *
     * @param userName �û���
     * @return Ǯ�����
     * @throws SQLException ���ݿ��ѯ����
     */
    public static double getWallet(String userName) throws SQLException {
        return 0;
    }

    /**
     * ��ѯ�û������б�
     *
     * @param userName �û���
     * @return �����б�
     * @throws SQLException ���ݿ��ѯ����
     */
    public static List<Order> getUserOrderList(String userName) throws SQLException {
        return null;
    }

    /**
     * ��ӻ��޸Ķ���
     *
     * @param order ����
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void addOrder(Order order) throws SQLException {

    }

    /**
     * ɾ������
     *
     * @param order ����
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void deleteOrder(Order order) throws SQLException {

    }

    /**
     * ����Ǯ�����
     *
     * @param userName �û���
     * @param amount   ���
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void updateWallet(String userName, double amount) throws SQLException {

    }

    /**
     * �����̼�����
     *
     * @param userName  �û���
     * @param ownerName �̼���
     * @param rating    ����
     * @param comment   ����
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void updateOwnerRating(String userName, String ownerName, double rating, String comment) throws SQLException {

    }

    /**
     * �����̼�����
     *
     * @param ownerName �̼���
     * @return �̼�����
     * @throws SQLException ���ݿ��ѯ����
     */
    public static double getOwnerRating(String ownerName) throws SQLException {
        return 0;
    }

    /**
     * ��ȡ��Ʒ�б�
     *
     * @param ownerName �̼���
     * @return ��Ʒ�б�
     * @throws SQLException ���ݿ��ѯ����
     */
    public static List<Dish> getDishList(String ownerName) throws SQLException {
        return null;
    }

    /**
     * ��ȡ�����б�
     *
     * @param ownerName �̼���
     * @return �����б�
     * @throws SQLException ���ݿ��ѯ����
     */
    public static List<Order> getOwnerOrderList(String ownerName) throws SQLException {
        return null;
    }

    /**
     * �̼���Ӳ�Ʒ
     *
     * @param ownerName �̼���
     * @param dish      ��Ʒ
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void addDish(String ownerName, Dish dish) throws SQLException {

    }

    /**
     * �̼��޸Ĳ�Ʒ
     *
     * @param ownerName �̼���
     * @param dish      ��Ʒ
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void changeDish(String ownerName, Dish dish) throws SQLException {

    }

    /**
     * �̼�ɾ����Ʒ
     *
     * @param ownerName �̼���
     * @param dish      ��Ʒ
     * @throws SQLException ���ݿ��ѯ����
     */
    public static void deleteDish(String ownerName, Dish dish) throws SQLException {

    }

    public static void test_select() throws SQLException {
        stmt = conn.createStatement();
        String sql = "select id from test_table";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
    }
}
