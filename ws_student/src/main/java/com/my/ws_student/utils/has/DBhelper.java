package com.my.ws_student.utils.has;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DBhelper {
    public DBhelper() {
    }

    public static <T> List<T> findAll(BDDobject objectBdd, Connection connection) throws SQLException, InstantiationException, IllegalArgumentException, IllegalAccessException, Exception {
        DBAnnotation.Table tableAnnotation = (DBAnnotation.Table)objectBdd.getClass().getAnnotation(DBAnnotation.Table.class);
        String tableName = tableAnnotation.name();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        List<T> result = new ArrayList();

        while(resultSet.next()) {
            T instance = instanceResultSet(objectBdd, resultSet, connection);
            result.add(instance);
        }

        return result;
    }

    public static <T> List<T> findAllWhere(BDDobject objectBdd, Connection connection, String special) throws SQLException, InstantiationException, IllegalArgumentException, IllegalAccessException, Exception {
        DBAnnotation.Table tableAnnotation = (DBAnnotation.Table)objectBdd.getClass().getAnnotation(DBAnnotation.Table.class);
        String tableName = tableAnnotation.name();
        Statement statement = connection.createStatement();
        String req = "SELECT * FROM " + tableName + " " + special;
        System.out.println(req);
        ResultSet resultSet = statement.executeQuery(req);
        List<T> result = new ArrayList();

        while(resultSet.next()) {
            T instance = instanceResultSet(objectBdd, resultSet, connection);
            result.add(instance);
        }

        return result;
    }

    public static <T> List<T> findAllFromView(BDDobject objectBdd, Connection connection, String special) throws SQLException, InstantiationException, IllegalArgumentException, IllegalAccessException, Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(special);
        List<T> result = new ArrayList();

        while(resultSet.next()) {
            T instance = instanceResultSet(objectBdd, resultSet, connection);
            result.add(instance);
        }

        return result;
    }

    private static <T> T instanceResultSet(BDDobject objectBdd, ResultSet resultSet, Connection connection) throws Exception {
        T instance = (T) objectBdd.getClass().newInstance();
        Field[] var4 = getAllFields(objectBdd);
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            field.setAccessible(true);
            DBAnnotation.Column columnAnnotation = (DBAnnotation.Column)field.getAnnotation(DBAnnotation.Column.class);
            DBAnnotation.Id idAnnotation = (DBAnnotation.Id)field.getAnnotation(DBAnnotation.Id.class);
            DBAnnotation.FK fk = (DBAnnotation.FK)field.getAnnotation(DBAnnotation.FK.class);
            String columnName;
            Object value;
            if (columnAnnotation != null || idAnnotation != null) {
                columnName = field.getName();
                if (columnAnnotation != null && !columnAnnotation.name().equals("")) {
                    columnName = columnAnnotation.name();
                }

                if (idAnnotation != null && !idAnnotation.name().equals("")) {
                    columnName = idAnnotation.name();
                }

                if (!isColumn(resultSet, columnName)) {
                    continue;
                }

                value = resultSet.getObject(columnName);
                field.set(instance, value);
            }

            if (fk != null) {
                columnName = fk.id();
                value = field.getType().newInstance();
                Object idvalue = get((BDDobject)instance, columnName);
                BDDobject values = (BDDobject)findbyId((BDDobject)value, String.valueOf(idvalue), connection);
                field.set((BDDobject)instance, values);
            }
        }

        return instance;
    }

    public static <T> T findbyId(BDDobject objectBdd, String id, Connection connection) throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException, Exception {
        DBAnnotation.Table tableAnnotation = (DBAnnotation.Table)objectBdd.getClass().getAnnotation(DBAnnotation.Table.class);
        String tableName = tableAnnotation.name();
        Statement statement = connection.createStatement();
        String id_column = getIdColumn(objectBdd);
        String request = "SELECT * FROM " + tableName + " WHERE " + id_column + "=" + id;
        System.out.println("the id is: " + request);
        ResultSet resultSet = statement.executeQuery(request);
        T result = null;
        if (resultSet.next()) {
            result = instanceResultSet(objectBdd, resultSet, connection);
        }

        return result;
    }

    public static <T> T findbyId(BDDobject objectBdd, int id, Connection connection) throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException, Exception {
        return findbyId(objectBdd, String.valueOf(id), connection);
    }

    public static <T> T findOneRowWhere(BDDobject objectBdd, Connection connection, String r) throws Exception {
        DBAnnotation.Table tableAnnotation = (DBAnnotation.Table)objectBdd.getClass().getAnnotation(DBAnnotation.Table.class);
        String tableName = tableAnnotation.name();
        T result = null;
        Statement statement = connection.createStatement();
        String request = "SELECT * FROM " + tableName + " " + r;
        ResultSet resultSet = statement.executeQuery(request);
        if (resultSet.next()) {
            result = instanceResultSet(objectBdd, resultSet, connection);
        }

        return result;
    }

    public static <T> T findOneRowFromView(BDDobject objectBdd, Connection connection, String r) throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException, Exception {
        Statement statement = connection.createStatement();
        System.out.println("Voici la requete:" + r);
        ResultSet resultSet = statement.executeQuery(r);
        T result = null;
        if (resultSet.next()) {
            result = instanceResultSet(objectBdd, resultSet, connection);
        }

        return result;
    }

    private static String getIdColumn(BDDobject objectBdd) throws Exception {
        Field[] var1 = objectBdd.getClass().getDeclaredFields();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Field f = var1[var3];
            DBAnnotation.Id idAnnotation = (DBAnnotation.Id)f.getAnnotation(DBAnnotation.Id.class);
            if (idAnnotation != null) {
                if (!idAnnotation.name().equals("")) {
                    return idAnnotation.name();
                }

                return f.getName();
            }
        }

        throw new Exception("no Id Annotation found");
    }

    private static Field getFieldIdColumn(BDDobject objectBdd) throws Exception {
        Field[] var1 = objectBdd.getClass().getDeclaredFields();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Field f = var1[var3];
            DBAnnotation.Id idAnnotation = (DBAnnotation.Id)f.getAnnotation(DBAnnotation.Id.class);
            if (idAnnotation != null) {
                return f;
            }
        }

        return null;
    }

    public static int save(BDDobject objectBdd, Connection connection) throws SQLException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        DBAnnotation.Table tableAnnotation = (DBAnnotation.Table)objectBdd.getClass().getAnnotation(DBAnnotation.Table.class);
        String tableName = tableAnnotation.name();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Field[] var6 = getAllFields(objectBdd);
        int var7 = var6.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Field field = var6[var8];
            DBAnnotation.Default d = (DBAnnotation.Default)field.getAnnotation(DBAnnotation.Default.class);
            if (d == null) {
                DBAnnotation.Column columnAnnotation = (DBAnnotation.Column)field.getAnnotation(DBAnnotation.Column.class);
                DBAnnotation.Id idAnnotation = (DBAnnotation.Id)field.getAnnotation(DBAnnotation.Id.class);
                if (columnAnnotation != null && idAnnotation == null) {
                    String columnName = field.getName();
                    if (!columnAnnotation.name().equals("")) {
                        columnName = columnAnnotation.name();
                    }

                    columns.append(columnName).append(",");
                    Object value = get(objectBdd, field.getName());
                    if (!(value instanceof Integer) && !(value instanceof Double) && !(value instanceof Float)) {
                        values.append("'").append(value).append("'").append(",");
                    } else {
                        values.append(value).append(",");
                    }
                }
            }
        }

        columns.deleteCharAt(columns.length() - 1);
        values.deleteCharAt(values.length() - 1);
        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql, 1);
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return -1;
        }
    }

    public static Field[] getAllFields(Object obj) {
        List<Field> fieldsList = new ArrayList();

        for(Class<?> clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            fieldsList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }

        Field[] fieldsArray = new Field[fieldsList.size()];
        return (Field[])fieldsList.toArray(fieldsArray);
    }

    static Object get(BDDobject objectBdd, String column) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String getterName = "get" + column.substring(0, 1).toUpperCase() + column.substring(1);
        Method getter = objectBdd.getClass().getMethod(getterName);
        Object value = getter.invoke(objectBdd);
        return value;
    }

    static boolean isColumn(ResultSet res, String column) throws Exception {
        List<String> listes = getColumnListe(res);
        Iterator var3 = listes.iterator();

        String liste;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            liste = (String)var3.next();
        } while(!liste.equals(column));

        return true;
    }

    static List<String> getColumnListe(ResultSet res) throws Exception {
        ResultSetMetaData metaData = res.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columnNames = new ArrayList();

        for(int i = 1; i <= columnCount; ++i) {
            String columnName = metaData.getColumnName(i);
            columnNames.add(columnName);
        }

        return columnNames;
    }

    public static void updateRow(BDDobject o1, BDDobject o2, Connection conn) {
        try {
            DBAnnotation.Table tableAnnotation = (DBAnnotation.Table)o1.getClass().getAnnotation(DBAnnotation.Table.class);
            String tableName = tableAnnotation.name();
            String primaryKeyColumnName = getIdColumn(o1);
            String sql = "UPDATE " + tableName + " SET ";
            Object id = getFieldIdColumn(o1).get(o1);
            Field[] var8 = o1.getClass().getDeclaredFields();
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Field field = var8[var10];
                if (field.isAnnotationPresent(DBAnnotation.Column.class)) {
                    field.setAccessible(true);
                    Object value = field.get(o2);
                    if (value != null) {
                        System.out.println("JE NE SUIS PAS NULL");
                        String columnName = ((DBAnnotation.Column)field.getAnnotation(DBAnnotation.Column.class)).name();
                        if (columnName.equals("")) {
                            columnName = field.getName();
                        }

                        if (!(value instanceof Integer) && !(value instanceof Double) && !(value instanceof Float)) {
                            sql = sql + columnName + "='" + value + "',";
                        } else {
                            sql = sql + columnName + "=" + value + ",";
                        }
                    }
                }
            }

            sql = sql.substring(0, sql.length() - 1);
            sql = sql + " WHERE " + primaryKeyColumnName + "='" + id + "'";
            System.out.println(sql);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }
}
