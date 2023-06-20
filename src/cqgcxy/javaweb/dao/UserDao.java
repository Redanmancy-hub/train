package cqgcxy.javaweb.dao;

import cqgcxy.javaweb.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * user表的增删改查
 * */
public class UserDao {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        boolean flag = userDao.deleteById(84);
        if (flag){
        System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
//        System.out.println("删除结果:"+flag);
//        User user = userDao.findById(71);
//        if (user!=null){
//            System.out.println("查询成功"+user.getUserId());
//        }else {
//            System.out.println("查询失败"+user.getUserId());
//        }
//          编译 编辑
//        User user = new User();
//
//        user.setUserId(74);
//        user.setUsername("陈浩然1");
//        user.setPasswd("2222");
//        user.setGender("男");
//        user.setFavor("java");
//        user.setDescription("123");
//        user.setRole("9");
//        boolean flag = userDao.update(user);
//        if(flag){
//            System.out.println("修改成功");
//        }else {
//            System.out.println("修改失败");
//        }

/*        查询框测试
        User user = new User();
        user.setUsername("胡虞强");
        user.setGender("男");
        List<User> list =  userDao.queryByCondition(user);
        System.out.println("**"+list.size());
        for(User user1:list){
            System.out.println(user.getUsername()+user1.getDescription());
        }*/

/*        Integer user =userDao.count();
        System.out.println(user);*/

/*        User user = new User();
        user.setUsername("陈浩然");
        user.setPasswd("6666");
        boolean flag = userDao.resetPassword(user);
        if(flag){
            System.out.println("重置密码成功");
        }else {
            System.out.println("重置密码失败");
        }*/
    }

    /*
     * 根据id删除用户
     * */
    public boolean deleteById(int userId) {
        //执行删除的jdbc
        // 1.注册驱动程序
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }

        try {
            // 2.链接数据库
            // jdbc_url格式：协议-ip-端口-数据库名称
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");

            // 3.写sql语句
            // 注册用户的sql语句
            String sql = "delete from user where user_id = ?";
            // 4.获得PreparedStatement对象（执行SQL容器）
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //4.1给SQL语句填充参数
            pstmt.setObject(1, userId);

            // 5.执行sql语句
            int rows = pstmt.executeUpdate(); // insert/update/delete指令用executeUpdate
            // 6.处理执行结果
            if (rows > 0) {
                System.out.println("<br>sql删除成功！");
                flag = true;
            } else {
                flag = false;
                System.out.println("<br>sql删除失败！");
            }
            // 7.关闭数据库连接
            conn.close();

        } catch (Exception e) {
            System.out.println("<br>链接数据库失败或者SQL语句执行失败！原因:" + e.getMessage());
            e.printStackTrace();
            //返回一条数据
            System.out.println("删除失败！原因为:" + e.getMessage());
        }
        return flag;
    }


    /**
     * 根据userid查询
     */
    public User findById(int userId) {
        User user = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM user WHERE user_id= " + userId;
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    int userId1 = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String passwd = rs.getString("password");
                    String gender = rs.getString("gender");
                    String favor = rs.getString("favor");
                    String description = rs.getString("description");
                    String role = rs.getString("role");
                    int state = rs.getInt("state");
                    user.setUserId(userId1);
                    user.setUsername(username);
                    user.setPasswd(passwd);
                    user.setGender(gender);
                    user.setFavor(favor);
                    user.setDescription(description);
                    user.setRole(role);
                    user.setState(state);
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL语句执行失败！" + e.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("连接失败！" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // 搜索框中的查询
    public List<User> queryByCondition(User condition) {
        List<User> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                Statement stmt = conn.createStatement();
                // 根据查询条件的多少，编写动态sql语句
                String baseSql = "select * from user where 1=1 ";
                if (condition.getUsername() != null && condition.getUsername() != "") {
                    baseSql += " and username like '%" + condition.getUsername() + "%' ";
                }
                if (condition.getGender() != null && condition.getGender() != "") {
                    baseSql += " and gender = '" + condition.getGender() + "'";
                }
                ResultSet rs = stmt.executeQuery(baseSql);
                //通过next方法循环移动行数据
                while (rs.next()) {
                    String username = rs.getString("username");//处理字符串类型，通过gerXxx方法获取某个字段的数据
                    int userId = rs.getInt("user_id");//处理int类型
                    String favor = rs.getString("favor");
                    String gender = rs.getString("gender");
                    String description = rs.getString("description");
                    String role = rs.getString("role");
                    int state = rs.getInt("state");
                    //转换为User对象
                    User user = new User();
                    user.setUserId(userId);
                    user.setUsername(username);//给Javabean赋值，使用set方法
                    user.setGender(gender);
                    user.setFavor(favor);
                    user.setDescription(description);
                    user.setRole(role);
                    user.setState(state);
                    list.add(user);
                }
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL语句执行失败！" + e.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("连接失败！" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }


//新增
    /**
     * 注册
     */
    public boolean register(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                String sql = "select count(*) from user where username = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUsername());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("count(*)");
                    if (count > 0) {
                        return false;
                    } else {
                        String sql1 = "INSERT INTO `user`(`username`, `password`, `gender` , `favor`,`description`) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                        pstmt1.setObject(1, user.getUsername());
                        pstmt1.setObject(2, user.getPasswd());
                        pstmt1.setObject(3, user.getGender());
                        pstmt1.setObject(4, user.getFavor());
                        pstmt1.setObject(5, user.getDescription());
                        int rows = pstmt1.executeUpdate();
                        if (rows > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("sql执行失败，原因是：" + e.getMessage());
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("数据库连接错误！" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // 更新
    /**
     * 修改 需要：全部属性（User） 修改后：判断是否修改成功
     */
    public boolean update(User user) {
        String sql = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                sql = "UPDATE user SET `username` = '" + user.getUsername()
                        + "', `password` = '" + user.getPasswd()
                        + "', `gender` = '" + user.getGender()
                        + "', `favor` = '" + user.getFavor()
                        + "', `description` = '" + user.getDescription()
                        + "',`role` = '" + user.getRole()
                        + "',`state` = '" + user.getState()
                        + "' WHERE `user_id` = "
                        + user.getUserId();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查询总数据条数
    public int count(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                String pageSql = "select count(*) from user";
                PreparedStatement pstmt = conn.prepareStatement(pageSql);
                ResultSet rs = pstmt.executeQuery(pageSql);
                int rows = 0;// 记录数，默认为0
                if (rs.next()) {
                    rows = rs.getInt(1);//用查询结果列的序号来取值
                }
                return rows;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL语句执行失败！" + e.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("连接失败！" + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    // 分页查询当页条数
    // 分页公式：（currPage-1）*pageSize
    public List<User> listPage(int currPage,int pageSize) {
        List<User> pagelist = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try{

                String currPageSql = "select * from user limit ?,?";
                PreparedStatement pstmt = conn.prepareStatement(currPageSql);
                pstmt.setObject(1,(currPage - 1) * pageSize);
                pstmt.setObject(2,pageSize);
//                DbUtil dbUtil = DbUtil.getInstance();
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    User user = new User();
                    int userId1 = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String passwd = rs.getString("password");
                    String gender = rs.getString("gender");
                    String favor = rs.getString("favor");
                    String description = rs.getString("description");
                    String role = rs.getString("role");
                    int state = rs.getInt("state");
                    user.setUserId(userId1);
                    user.setUsername(username);
                    user.setPasswd(passwd);
                    user.setGender(gender);
                    user.setFavor(favor);
                    user.setDescription(description);
                    user.setRole(role);
                    user.setState(state);
                    pagelist.add(user);
                }
            }catch (Exception e){
                System.out.println("sql语句失败");
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return pagelist;
    }

    // 修改密码
    public boolean resetPassword(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                String resetSql = "UPDATE user SET `password` = ? WHERE `username` = " + user.getUsername();
                PreparedStatement pstmt = conn.prepareStatement(resetSql);
                pstmt.setString(2, user.getPasswd());
                int rows = pstmt.executeUpdate(resetSql);
                return rows > 0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

