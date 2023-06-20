package cqgcxy.javaweb.dao;

import cqgcxy.javaweb.news.News;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {
    public static void main(String[] args) {
        NewsDao newsDao = new NewsDao();
/*        查询测试
        News news = newsDao.findnewId(3);
        if(news!=null){
            System.out.println("查询成功"+news.getNewsId());
        }else {
            System.out.println("查询失败");
        }*/

/*        新增测试
        News news = new News();news.setTitle("蔡徐坤养鸡场");
        news.setAuthor("78");
        news.setState(1);
        news.setCategory("娱乐新闻");
        news.setCoverUrl("/upload/cover/选区_007.png");
        news.setContent("蔡徐坤在养鸡场跳鸡你太美");
        boolean flag = newsDao.Addnew(news);

        if(flag){
            System.out.println("新增成功");
        }else {
            System.out.println("新增失败");
        }*/

/*        删除测试
        boolean flag = newsDao.deleteNew(7);
        if (flag){
        System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        System.out.println("删除结果:"+flag);*/

/*        修改测试
        News news =new News();
        news.setNewsId(6);
        news.setTitle("蔡徐坤养鸡场1");
        news.setAuthor("caixukun");
        news.setState(1);
        news.setCategory("搞笑新闻");
        news.setCoverUrl("/upload/cover/选区_008.png");
        news.setContent("蔡徐坤在养鸡场跳鸡你太美，太酷啦");

        boolean flag = newsDao.updateNew(news);
        if(flag){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }*/
//        ArrayList<News> categoryList = newsDao.queryAllNews();
//        for(News news:categoryList){
//            System.out.println(news.getCategory());
//        }
/*        News news = newsDao.findnewId("《速度与激情10》中国内地票房最新突破6亿元");
        System.out.println(news.getTitle());*/

    /*    News news = newsDao.findnewId("19");
        System.out.println(news.getCoverUrl());*/


/*
        NewsDao newsDao1=new NewsDao();
        newsDao1.UpdateViewCount("习近平会见俄罗斯总理米舒斯京",2);*/

    }

    // 添加新闻
    public boolean Addnew(News news) {
        // 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        // 连接数据库
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                String sql = "select count(*) from news where title = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, news.getTitle());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("count(*)");
                    if (count > 0) {
                        return false;
                    } else {
                        String sql1 = "INSERT INTO `news`(`title`, `author`, `state` , `category`,`cover_url`,`content`) " +
                                "VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                        pstmt1.setObject(1, news.getTitle());
                        pstmt1.setObject(2, news.getAuthor());
                        pstmt1.setObject(3, news.getState());
                        pstmt1.setObject(4, news.getCategory());
                        pstmt1.setObject(5, news.getCoverUrl());
                        pstmt1.setObject(6, news.getContent());

                        int rows = pstmt1.executeUpdate();
                        if (rows > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("sql语句执行失败");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    return false;
    }

    // 删除
    public boolean deleteNew(int newsId){
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            // 3.写sql语句
            // 注册用户的sql语句
            String sql = "delete from news where news_id = ?";
            // 4.获得PreparedStatement对象（执行SQL容器）
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //4.1给SQL语句填充参数
            pstmt.setObject(1, newsId);

            // 5.执行sql语句
            int rows = pstmt.executeUpdate(); // insert/update/delete指令用executeUpdate
            // 6.处理执行结果
            if (rows > 0) {
                System.out.println("<br>sql删除成功！");
                flag = true;
            } else {
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

    // 查询
    public News findnewId(HttpSession session,String serach) {
        News news = new News();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            int view=0;
            try {
                String sql = "SELECT * FROM news WHERE news_id = ? OR title = ? ";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,serach);
                pstmt.setObject(2,serach);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int newsId1 = rs.getInt("news_id");
                    String title1 = rs.getString("title");
                    String author = rs.getString("author");
                    int state = rs.getInt("state");
                    String category = rs.getString("category");
                    String coverUrl = rs.getString("cover_url");
                    String content = rs.getString("content");
                    int viewcount = rs.getInt("view_count");
                    String createTime = rs.getString("create_time");
                    String updateTime = rs.getString("updateTime");
                    news.setNewsId(newsId1);
                    news.setTitle(title1);
                    news.setAuthor(author);
                    news.setState(state);
                    news.setCategory(category);
                    news.setContent(content);
                    news.setViewCount(viewcount);
                    news.setCreateTime(createTime);
                    news.setUpdateTime(updateTime);
                    news.setCoverUrl(coverUrl);
                }
                view=getViewCount(news.getTitle());
                session.setAttribute("view",view);
                NewsDao newsDao=new NewsDao();
                newsDao.UpdateViewCount(news.getTitle(),view);
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
        return news;
    }

    public int getViewCount(String title){
        int view=0;

        try{
            view=getViewCountDataBase(title);
            view+=1;
            UpdateViewCount(title,view);

        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    //获取浏览量
    public int getViewCountDataBase(String title){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                String sql = "select view_count as v1 from news where title = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setObject(1,title);

                ResultSet resultSet=pstmt.executeQuery();

                while (resultSet.next()){
                    return resultSet.getInt("v1");
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

        return 0;
    }

    // 更新浏览量
    public void UpdateViewCount(String title, int view){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                String sql = "update news set view_count= ? where title = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setObject(1,view);
                pstmt.setObject(2,title);

                int row= pstmt.executeUpdate();

/*                System.out.println(row>0?"执行成功":"执行失败");*/

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
    }

    // 搜索框中的查询
    public List<News> queryNewsCondition(String newscondition) {
        List<News> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                // 根据查询条件的多少，编写动态sql语句
                String QuerySql = "SELECT * FROM news WHERE title like ? OR author like ? OR category like ?";

                PreparedStatement pstmt = conn.prepareStatement(QuerySql);
                pstmt.setObject(1,"%"+newscondition+"%");
                pstmt.setObject(2,"%"+newscondition+"%");
                pstmt.setObject(3,"%"+newscondition+"%");
                ResultSet rs = pstmt.executeQuery();
                //通过next方法循环移动行数据
                while (rs.next()) {
                    int newsId1 = rs.getInt("news_id");
                    String title1 = rs.getString("title");
                    String author = rs.getString("author");
                    int state = rs.getInt("state");
                    String category = rs.getString("category");
                    String coverUrl = rs.getString("cover_url");
                    String content = rs.getString("content");
                    int viewcount = rs.getInt("view_count");
                    String createTime = rs.getString("create_time");
                    String updateTime = rs.getString("updateTime");
                    News news = new News();
                    news.setNewsId(newsId1);
                    news.setTitle(title1);
                    news.setAuthor(author);
                    news.setState(state);
                    news.setCategory(category);
                    news.setContent(content);
                    news.setViewCount(viewcount);
                    news.setCreateTime(createTime);
                    news.setUpdateTime(updateTime);
                    news.setCoverUrl(coverUrl);
                    list.add(news);
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
        return null;
    }

    // 修改
    public boolean updateNew(News news){
        String sql = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                sql = "UPDATE news SET `title` = '" + news.getTitle()
                        + "', `author` = '" + news.getAuthor()
                        + "', `state` = '" + news.getState()
                        + "', `category` = '" + news.getCategory()
                        + "', `cover_url` = '" + news.getCoverUrl()
                        + "',`content` = '" +news.getContent()
                        + "' WHERE `news_id` = "
                        + news.getNewsId();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                int rows = pstmt.executeUpdate();
                if (rows > 0){
                    return true;
                }else {
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                conn.close();
            }
        }catch (SQLException e){
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                String pageSql = "select count(*) from news";
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
    public List<News> newsPage(int currPage,int pageSize) {
        List<News> newspagelist = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try{

                String currPageSql = "select * from news limit ?,?";
                PreparedStatement pstmt = conn.prepareStatement(currPageSql);
                pstmt.setObject(1,(currPage - 1) * pageSize);
                pstmt.setObject(2,pageSize);

                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    int newsId1 = rs.getInt("news_id");
                    String title1 = rs.getString("title");
                    String author = rs.getString("author");
                    int state = rs.getInt("state");
                    String category = rs.getString("category");
                    String coverUrl = rs.getString("cover_url");
                    String content = rs.getString("content");
                    int viewcount = rs.getInt("view_count");
                    String createTime = rs.getString("create_time");
                    String updateTime = rs.getString("updateTime");
                    News news = new News();
                    news.setNewsId(newsId1);
                    news.setTitle(title1);
                    news.setAuthor(author);
                    news.setState(state);
                    news.setCategory(category);
                    news.setContent(content);
                    news.setViewCount(viewcount);
                    news.setCreateTime(createTime);
                    news.setUpdateTime(updateTime);
                    news.setCoverUrl(coverUrl);
                    newspagelist.add(news);
                }
            }catch (Exception e){
                System.out.println("sql语句失败");
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return newspagelist;
    }
    public ArrayList<News> queryAllNews() {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xscj", "root", "hu020808");
            try {
                String sql = "SELECT * FROM news ";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery(sql);
                while (rs.next()) {
                    News news = new News();
                    int newsId1 = rs.getInt("news_id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    int state = rs.getInt("state");
                    String category = rs.getString("category");
                    String coverUrl = rs.getString("cover_url");
                    String content = rs.getString("content");
                    String createTime = rs.getString("create_time");
                    String updateTime = rs.getString("updateTime");
                    news.setNewsId(newsId1);
                    news.setTitle(title);
                    news.setAuthor(author);
                    news.setState(state);
                    news.setCategory(category);
                    news.setCoverUrl(coverUrl);
                    news.setContent(content);
                    news.setCreateTime(createTime);
                    news.setUpdateTime(updateTime);
                    newsList.add(news);
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
        return newsList;
    }

}