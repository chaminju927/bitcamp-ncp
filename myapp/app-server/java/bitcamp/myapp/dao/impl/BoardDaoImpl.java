package bitcamp.myapp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ConnectionFactory;
import dao.DaoException;

public class BoardDaoImpl implements BoardDao {

  ConnectionFactory conFactory;
  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Board b) {
    try (SqlSession sqlSession =  sqlSessionFactory.openSession()){
      sqlSession.insert("BoardMapper.insert", b);
      sqlSession.commit();
    }
  }

  @Override
  public List<Board> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      //자동 close하기 위해 try사용, runtime예외는 굳이 쓰지 않아도돼서 catch생략
      return sqlSession.selectList("BoardMapper.findAll");
    }

    @Override
    public Board findByNo(int no) {
      try (PreparedStatement stmt = conFactory.getConnection().prepareStatement(
          "select board_id, title, content, pwd, created_date, view_cnt from app_board where board_id=?")) {

        stmt.setInt(1, no);

        try (ResultSet rs = stmt.executeQuery()) {
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
        }
      } catch (Exception e) {
        throw new DaoException(e);
      }
    }

    @Override
    public void increaseViewCount(int no) {
      try (PreparedStatement stmt = conFactory.getConnection().prepareStatement(
          "update app_board set"
              + " view_cnt = view_cnt + 1"
              + " where board_id=?")) {

        stmt.setInt(1, no);
        stmt.executeUpdate();

      } catch (Exception e) {
        throw new DaoException(e);
      }
    }

    @Override
    public List<Board> findByKeyword(String keyword) {
      try (PreparedStatement stmt = conFactory.getConnection().prepareStatement(
          "select board_id, title, created_date, view_cnt"
              + " from app_board"
              + " where title like(?)"
              + " or content like(?)"
              + " order by board_id desc")) {

        stmt.setString(1, "%" + keyword + "%" );
        stmt.setString(2, "%" + keyword + "%" );

        try (ResultSet rs = stmt.executeQuery()) {
          ArrayList<Board> list = new ArrayList<>();
          while (rs.next()) {
            Board b = new Board();
            b.setNo(rs.getInt("board_id"));
            b.setTitle(rs.getString("title"));
            b.setCreatedDate(rs.getString("created_date"));
            b.setViewCount(rs.getInt("view_cnt"));

            list.add(b);
          }
          return list;

        } catch (Exception e) {
          throw new DaoException(e);
        }
      }

      @Override
      public int update(Board b) {
        try (PreparedStatement stmt = conFactory.getConnection().prepareStatement(
            "update app_board set title='%s', content='%s' where board_id=?")) {

          //미리 준비한 SQL에 값만 별도로 설정
          // 이때 문자열 안에 들어있는 작은 따옴표()는 일반 문자로 간주
          //
          stmt.setString(1,  b.getTitle());
          stmt.setString(2, b.getContent());
          stmt.setInt(3,  b.getNo());

          return stmt.executeUpdate();

        } catch (Exception e) {
          throw new DaoException(e);
        }
      }

      @Override
      public int delete(int no) {
        try (PreparedStatement stmt = conFactory.getConnection().prepareStatement(
            "delete from app_board where board_id=?")) {

          stmt.setInt(1, no);
          return stmt.executeUpdate();

        } catch (Exception e) {
          throw new DaoException(e);
        }
      }
    }























