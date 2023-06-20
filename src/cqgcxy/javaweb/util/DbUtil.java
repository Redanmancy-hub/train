///:DdUtil.java
package cqgcxy.javaweb.util;


import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <h3>JDBC简易工具类。通过对数据库的增删改查4大基础功能，进行分类抽象，形成两大类工具方法：更新和查询。</h3>
 * <p>同时，每一类方法又提供了静态SQL方法和预编译SQL方法。通常在DAO层调用它进行具体的业务逻辑操作。</p>
 * <p style="color: red;">较上一版本的区别在于：本版本在处理insert指令时，会返回auto_increment（自动递增）主键的值。对于需要做关联表处理的场景非常有用。</p>
 * 非自增长主键则仍然返回受影响记录行数。
 * <p>示例1：预编译sql</p>
 * <code><pre>
 *      // 创建工具类对象
 *      DbUtil instance = DbUtil.getInstance();
 *      // 编写一个正确的预编译sql语句
 *      String sql = "select * from t_user where username = ? and passwd = ?";
 *      // 执行预编译sql
 *      ResultSet rs = instance.preparedQuery(sql, "02933", "admin");
 *      // 处理执行结果
 *      try {
 *          while (rs.next()) {
 *              System.out.println(rs.getString("real_name"));
 *          }
 *      } catch (SQLException e) {
 *          e.printStackTrace();
 *      } finally { // 查询功能别忘记在finally模块中关闭数据库连接
 *          instance.close();
 *      }
 * </pre></code>
 *
 * <p>示例2：静态sql</p>
 * <code><pre>
 *     // 创建工具类对象
 *     DbUtil instance = DbUtil.getInstance();
 *     // 拼接正确的静态sql
 *     String sql = "select * from t_user where username = '" + "02933" + "' and passwd = '" + "admin" + "'";
 *     // 执行静态sql
 *     ResultSet rs = instance.query(sql);
 *     // 处理执行结果
 *     try {
 *        while (rs.next()) {
 *             System.out.println(rs.getString("real_name"));
 *         }
 *     } catch (SQLException e) {
 *         e.printStackTrace();
 *     } finally { // 查询功能别忘记在finally模块中关闭数据库连接
 *         instance.close();
 *     }
 * </pre></code>
 *
 * <p>示例3：插入记录</p>
 * <code><pre>
 *     DbUtil db = DbUtil.getInstance();
 *     String sql = "insert into t_demo(id, name, create_time) values(?, ?, now())";
 *     int result = db.preparedUpdate(sql, 0, "test2"); // result可能是自增长主键的值，或者插入成功的行数。取决于表是否有自增长主键。
 *     System.out.println(result);
 * </pre></code>
 *
 * <p>示例4：删除记录</p>
 * <code><pre>
 *     DbUtil db = DbUtil.getInstance();
 *     int rows = db.preparedUpdate("delete from t_demo where name = ?", "test2"); // update和delete只会返回数据行数
 *     System.out.println(rows);
 * </pre></code>
 * @author icechen1219
 * @date 2020/04/06
 * @version 0.9.2
 */
public class DbUtil {
    // 当需要变更数据库参数的时候，需要修改了下面的变量，而java类发生变更需要重新编译发布才能生效
    // 改成配置文件形式，因为它不是java类，所以不需要重新发布。而且还可以放在工程外部。方便随时更改。
    private static String jdbcUrl;
    private static String username;
    private static String password;

    // 静态代码块，初始化静态变量
    static {
        // 资源文件放在classpath根目录
        InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            System.out.println(LocalDateTime.now() + "\t配置文件加载成功！");
            Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
            for (Map.Entry<Object, Object> objectEntry : entrySet) {
                System.out.println(objectEntry.getKey() + ": " + objectEntry.getValue());
            }
        } catch (Exception e) {
            System.err.println(LocalDateTime.now() + "\t配置文件加载失败，请检查后重试！");
            e.printStackTrace();
            throw new RuntimeException("配置文件加载失败，请检查后重试！", e);
        }
        try {
            // 1、检查驱动是否正确加载
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            System.err.println(LocalDateTime.now() + "\t驱动加载失败，请检查buildpath或者驱动类拼写是否正确");
            e.printStackTrace();
            throw new RuntimeException("驱动加载失败，请检查buildpath或者驱动类拼写是否正确", e);
        }
        jdbcUrl = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.user");
        password = properties.getProperty("jdbc.password");
    }

    /**
     * 数据库连接
     */
    private Connection connection;
    /**
     * 静态SQL执行对象
     */
    private Statement statement;
    /**
     * 查询结果集
     */
    private ResultSet resultSet;
    /**
     * 预编译SQL执行对象
     */
    private PreparedStatement preparedStatement;

    /**
     * 构造方法。私有化的目的是屏蔽直接new的方式创建对象，而是鼓励大家调用静态方法创建对象：DbUtil.getInstance()
     */
    private DbUtil() {
        getConnection();
    }

    /**
     * 创建一个DbUtil实例对象
     * @return
     */
    public static DbUtil getInstance() {
        return new DbUtil();
    }

    /**
     * 创建数据库连接，因为是内部使用，所以设为private不暴露给外界使用
     *
     * @return
     */
    public Connection getConnection() throws RuntimeException {
        // 2、创建数据库连接————类似于我们在mysql-front中创建新的连接一样，需要输入正确的ip/端口/数据库名称/用户名和密码。另外jdbc协议也要正确。
        try {
            // 先检查一下是否已经存在可用连接，避免重复创建浪费资源
            if (connection == null || connection.isClosed()) {
                long start = System.currentTimeMillis();
                connection = DriverManager.getConnection(jdbcUrl, username, password);
                long end = System.currentTimeMillis();
                System.out.println(LocalDateTime.now() + "\t创建数据库连接成功！耗时： " + (end - start) + "毫秒");
            }
        } catch (SQLException e) {
            System.err.println(LocalDateTime.now() + "\t连接数据库失败，请检查后重试！");
            e.printStackTrace();
            connection = null;
            throw new RuntimeException("连接数据库失败，请检查后重试！", e);
        }
        return connection;
    }

    /**
     * 执行增删改sql。静态SQL语句，要求传进来的SQL字符串参数是一个拼接完整的正确的SQL语句。
     *
     * @param sql
     * @return 返回自动生成的主键，或者受影响记录行数
     */
    public int update(String sql) throws RuntimeException {
        getConnection();
        int rows = 0;
        if (connection != null) {
            try {
                long start = System.currentTimeMillis();
                // 3、打开sql执行容器————相当与在mysql-front中打开了一个编写sql的窗口
                statement = connection.createStatement();
                // 这一步非常必要，帮助我们查看执行的sql到底是什么样！提高排错效率
                System.out.println(LocalDateTime.now() + "\tsql: " + sql);
                // 5、执行sql————相当于在mysql-front中点击了执行按钮
                rows = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                long end = System.currentTimeMillis();
                System.out.println(LocalDateTime.now() + "\tsql执行成功！耗时： " + (end - start) + "毫秒");

                if (rows > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        rows = generatedKeys.getInt(1);
                    }
                }
            } catch (SQLException e) {
                System.err.println("SQL执行失败，原因如下：");
                throw new RuntimeException("SQL执行失败", e);
            }
        }
        // 6. 处理sql执行结果
        System.out.println(LocalDateTime.now() + "\t更新成功：[" + rows + "] 行数据");
        System.out.println("----------------------------");
        return rows;
    }

    /**
     * 执行select语句。静态SQL语句，要求传进来的SQL字符串参数是一个拼接完整的正确的SQL语句。
     * <p style="color: red;">注意：该方法内没有释放数据库资源，需要在使用完ResultSet之后，主动调用close方法</p>
     *
     * @param sql
     * @return
     */
    public ResultSet query(String sql) throws RuntimeException {
        getConnection();
        if (connection != null) {
            try {
                long start = System.currentTimeMillis();
                // 3、打开sql执行容器————相当与在mysql-front中打开了一个编写sql的窗口
                statement = connection.createStatement();
                // 这一步非常必要，帮助我们查看执行的sql到底是什么样！提高排错效率
                System.out.println(LocalDateTime.now() + "\tsql: " + sql);
                // 5、执行sql————相当于在mysql-front中点击了执行按钮
                resultSet = statement.executeQuery(sql);
                long end = System.currentTimeMillis();
                System.out.println(LocalDateTime.now() + "\tsql执行成功！耗时： " + (end - start) + "毫秒");
            } catch (SQLException e) {
                System.err.println(LocalDateTime.now() + "\tSQL执行失败，原因如下：");
                throw new RuntimeException("SQL执行失败", e);
            }
        }
        System.out.println("----------------------------");
        // ResultSet需要提供给外部使用，所以不能在这里关闭
        return resultSet;
    }

    /**
     * 释放数据库资源。<span style="color: red;">通常只有查询功能才需要调用</span>
     */
    public void close() {
        // 7、释放数据库资源
        // 反序关闭
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用预编译方式执行增删改sql
     *
     * @param sql
     * @param params 预编译sql的参数，跟?的个数匹配。采用可变长参数方式，接受0-多个数据，以数组形式存储
     * @return 返回自动生成的主键，或者受影响记录行数
     */
    public int preparedUpdate(String sql, Object... params) throws RuntimeException {
        getConnection();
        int rows = 0;
        if (connection != null) {
            try {
                long start = System.currentTimeMillis();
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                // 循环设置参数
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                // 这一步非常必要，帮助我们查看执行的sql到底是什么样！提高排错效率
                guessPreparedSql(sql, params);
                rows = preparedStatement.executeUpdate();
                long end = System.currentTimeMillis();
                System.out.println(LocalDateTime.now() + "\tsql执行成功！耗时： " + (end - start) + "毫秒");

                if (rows > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        rows = generatedKeys.getInt(1);
                    }
                }
            } catch (SQLException e) {
                System.err.println("SQL执行失败，原因如下：");
                throw new RuntimeException("SQL执行失败", e);
            }
        }
        System.out.println(LocalDateTime.now() + "\t更新成功：[" + rows + "] 行数据");
        System.out.println("----------------------------");
        return rows;
    }

    /**
     * 使用预编译方式执行select语句
     * <p style="color: red;">注意：该方法内没有释放数据库资源，需要在使用完ResultSet之后，主动调用close方法</p>
     *
     * @param sql
     * @param params 预编译sql的参数，跟?的个数匹配
     * @return
     */
    public ResultSet preparedQuery(String sql, Object... params) throws RuntimeException {
        getConnection();
        if (connection != null) {
            try {
                long start = System.currentTimeMillis();
                // 3、打开sql执行容器————相当与在mysql-front中打开了一个编写sql的窗口
                preparedStatement = connection.prepareStatement(sql);
                // 循环设置参数
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                // 这一步非常必要，帮助我们查看执行的sql到底是什么样！提高排错效率
                guessPreparedSql(sql, params);
                // 5、执行sql————相当于在mysql-front中点击了执行按钮
                resultSet = preparedStatement.executeQuery();
                long end = System.currentTimeMillis();
                System.out.println(LocalDateTime.now() + "\tsql执行成功！耗时： " + (end - start) + "毫秒");
            } catch (SQLException e) {
                System.err.println("SQL执行失败，原因如下：");
                throw new RuntimeException("SQL执行失败", e);
            }
        }
        System.out.println("----------------------------");
        // ResultSet需要提供给外部使用，所以不能在这里关闭
        return resultSet;
    }

    /**
     * 还原预编译SQL语句，补全?所代表的参数，方便调试SQL语句的正确性
     *
     * @param sql
     * @param params
     * @return
     */
    private String guessPreparedSql(String sql, Object... params) {
        int paramNum = 0;

        // 如果参数集不为空，取得其长度
        if (null != params) {
            paramNum = params.length;
        }

        // 如果没有参数，说明不是动态SQL语句，直接返回原sql
        if (1 > paramNum) {
            System.out.println(LocalDateTime.now() + "\tprepared sql: " + sql);
            return sql;
        }

        // 如果有参数，则是动态SQL语句，需要构造并返回新sql
        StringBuffer returnSQL = new StringBuffer();
        String[] subSQL = sql.split("\\?");

        // 开始循环替换问号为参数值
        for (int i = 0; i < paramNum; i++) {
            Object value = params[i];
            if (value == null) {
                System.err.printf("第 %d 个参数的值为 null %n", i + 1);
                returnSQL.append(subSQL[i]).append(value);
                continue;
            }
            if (value instanceof Number) {
                // 数值不需要引号
                returnSQL.append(subSQL[i]).append(value);
            } else {
                // 非数值需要引号，遇到特殊字符将其转义输出
                String str = value.toString().replaceAll("(['\\\\])", "\\\\$1");
                returnSQL.append(subSQL[i]).append(" '").append(str).append("' ");
            }
        }

        // 如果问号不是原sql的最后一个字符，则将改问号后的部分添加到returnSQL中
        if (subSQL.length > params.length) {
            returnSQL.append(subSQL[subSQL.length - 1]);
        }

        String formatSql = returnSQL.toString();
        System.out.println(LocalDateTime.now() + "\tprepared sql: " + formatSql);

        return formatSql;
    }
}
///:DdUtil.java
