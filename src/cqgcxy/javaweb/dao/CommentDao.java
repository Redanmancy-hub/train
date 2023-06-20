package cqgcxy.javaweb.dao;

import cqgcxy.javaweb.news.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    public static void main(String[] args) {
        CommentDao commentDao = new CommentDao();
       /* // 查询测试
        Comment comment = commentDao.findcommentId(2);
        if(comment!=null){
            System.out.println("查询评论成功");
            System.out.println(comment.getCommentText()+" "+comment.getPublishTime());
        }else{
            System.out.println("查询评论失败");
        }*/

        /*// 增加评论
        Comment comment1 = new Comment();
        comment1.setCommentText("测试2");
        comment1.setUserId(71);
        comment1.setNewsId(16);
        boolean flag = commentDao.AddComment(comment1);
        if(flag){
            System.out.println("success!");
        }else {
            System.out.println("error!");
        }*/

 /*       // 删除测试
        boolean flag = commentDao.DeleteComment(2);
        System.out.println(flag?"删除成功":"删除失败");
*/
    }

    // 添加评论
    public boolean AddComment(Comment comment){
        // 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        // 连接数据库
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                String sql1 = "INSERT INTO `comment`(`comment_text`,`user_id`,`news_id`) VALUES (?,?,?); ";
                PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                pstmt1.setString(1,comment.getCommentText());
                pstmt1.setInt(2,comment.getUserId());
                pstmt1.setInt(3,comment.getNewsId());
                int rows = pstmt1.executeUpdate();
                if (rows > 0) {
                    return true;
                } else {
                    return false;
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

    // 管理员删除评论
    public boolean DeleteComment(int commentId){
        // 1.注册驱动程序
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            String sql = "delete from comment where comment_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,commentId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("sql删除成功！");
                flag = true;
            } else {
                System.out.println("sql删除失败！");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("链接数据库失败或者SQL语句执行失败！原因:" + e.getMessage());
            e.printStackTrace();
            //返回一条数据
            System.out.println("删除失败！原因为:" + e.getMessage());
        }
        return flag;
    }

    // 搜索框查询
    public List<Comment> queryCommentCondition(String comcondition) {
        List<Comment> CommentList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                // 根据查询条件的多少，编写动态sql语句
                String QueryComSql = "SELECT * FROM comment WHERE user_id like ? OR news_id like ? OR comment_text like ?";
                PreparedStatement pstmt = conn.prepareStatement(QueryComSql);
                pstmt.setObject(1,"%"+comcondition+"%");
                pstmt.setObject(2,"%"+comcondition+"%");
                pstmt.setObject(3,"%"+comcondition+"%");
                ResultSet rs = pstmt.executeQuery();
                //通过next方法循环移动行数据
                while (rs.next()) {
                    int commentId = rs.getInt("comment_id");
                    int userId = rs.getInt("user_id");
                    int newsId = rs.getInt("news_id");
                    String commentText = rs.getString("comment_text");
                    String publishTime = rs.getString("publish_time");
                    Comment comment = new Comment();
                    comment.setCommentId(commentId);
                    comment.setUserId(userId);
                    comment.setNewsId(newsId);
                    comment.setCommentText(commentText);
                    comment.setPublishTime(publishTime);
                    CommentList.add(comment);
                }
                return CommentList;
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
                String pageSql = "select count(*) from comment";
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
    public List<Comment> commentPage(int currPage, int pageSize) {
        List<Comment> commentpage = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try{

                String currPageSql = "select * from comment limit ?,?";
                PreparedStatement pstmt = conn.prepareStatement(currPageSql);
                pstmt.setObject(1,(currPage - 1) * pageSize);
                pstmt.setObject(2,pageSize);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    Comment comment = new Comment();
                    comment.setCommentId(rs.getInt("comment_id"));
                    comment.setUserId(rs.getInt("user_id"));
                    comment.setNewsId(rs.getInt("news_id"));
                    comment.setCommentText(rs.getString("comment_text"));
                    comment.setPublishTime(rs.getString("publish_time"));
                    commentpage.add(comment);
                }
            }catch (Exception e){
                System.out.println("sql语句失败");
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return commentpage;
    }

    /**
     * 根据newsid查询评论
     * @return
     */
    public ArrayList<Comment> quryNewsComment(int newsId){
        ArrayList<Comment> commentList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 利用反射技术实现类的加载
        } catch (Exception e) {
            System.out.println("注册驱动失败！原因:" + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "hu020808");
            try {
                String sql = "select * from `comment`,`user`,`news` where `comment`.news_id =  `news`.news_id and `comment`.user_id = `user`.user_id and `news`.news_id = ?;";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, newsId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setCommentId(rs.getInt("comment_id"));
                    comment.setUserId(rs.getInt("user_id"));
                    comment.setUserName(rs.getString("username"));
                    comment.setCommentText(rs.getString("comment_text"));
                    comment.setPublishTime(rs.getString("publish_time"));
                    commentList.add(comment);
                }
                return commentList;
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
        return commentList;
    }

}
