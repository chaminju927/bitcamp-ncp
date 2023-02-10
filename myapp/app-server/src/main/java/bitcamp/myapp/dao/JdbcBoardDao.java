package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import bitcamp.myapp.vo.Board;
import dao.DaoException;

public class JdbcBoardDao implements BoardDao {

  //의존객체 Connection을 생성자에서 받는다.
  Connection con;
  public JdbcBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board b) {
    try (
        Statement stmt =  con.createStatement()) {

      String sql = String.format("insert into app_board(title, content, pwd) values('%s', '%s', '%s')",
          b.getTitle(), b.getContent(), b.getPassword());
      stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Board[] findAll() {
    try (
        Statement stmt =  con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_id, title, content, created_date, view_cnt from app_board order by board_id desc")) {

      ArrayList<Board> list = new ArrayList<>();
      while (rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_id"));
        b.setTitle(rs.getString("title"));
        b.setContent(rs.getString("content"));
        b.setCreatedDate(rs.getString("created_date"));
        b.setViewCount(rs.getInt("view_cnt"));
        list.add(b);
      }
      return list.toArray(new Board[] {});  //아래 세줄 리팩토링 한 코드

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  //      Board[] boards = new Board[list.size()];
  //      list.toArray(boards);
  //      return boards;
  @Override
  public Board findByNo(int no) {
    try (
        Statement stmt =  con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_id, title, content, pwd, created_date, view_cnt from app_board where board_id=" + no)) {

      ArrayList<Board> list = new ArrayList<>();
      if (rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_id"));
        b.setTitle(rs.getString("title"));
        b.setContent(rs.getString("content"));
        b.setPassword(rs.getString("pwd"));
        b.setCreatedDate(rs.getString("created_date"));
        b.setViewCount(rs.getInt("view_cnt"));
        return b;
      }

      return null;

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Board[] findByKeyword(String keyword) {
    try (
        Statement stmt =  con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_id, title, content, pwd, created_date, view_cnt "
                + "from app_board"
                + "where title like ('%" + keyword + "%') "
                + " or content like('%"+ keyword + "%')"
                + " order by board_no desc " )) {

      ArrayList<Board> list = new ArrayList<>();
      if (rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_id"));
        b.setTitle(rs.getString("title"));
        b.setContent(rs.getString("content"));
        b.setPassword(rs.getString("pwd"));
        b.setCreatedDate(rs.getString("created_date"));
        b.setViewCount(rs.getInt("view_cnt"));
        list.add(b);
      }
      return list.toArray(new Board[] {});

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Board b) {
    try (
        Statement stmt =  con.createStatement()) {

      String sql = String.format("update app_board set title='%s', content ='%s'  where board_id=%d",
          b.getTitle(), b.getContent(), b.getNo());

      stmt.executeUpdate(sql);

    } catch (Exception e) {
      throw new DaoException(e);
    }
  }


  @Override
  public boolean delete(Board b) {
    try (
        Statement stmt =  con.createStatement()) {

      String sql = String.format("delete from app_board_id =%d", b.getNo());

      return stmt.executeUpdate(sql) == 1;


    } catch (Exception e) {
      throw new DaoException(e);
    }
  }


}























