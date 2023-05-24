package org.mklinkj.taojwp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mklinkj.taojwp.board.ArticleVO;

@Mapper
public interface MySqlBoardMapper {

  List<ArticleVO> selectAllArticles();

  int selectMaxArticleNo();

  int insertArticle(ArticleVO article);

  ArticleVO selectOne(int articleNo);

  int updateArticle(ArticleVO articleVO);

  int deleteArticle(int articleNo);

  List<Integer> selectArticleNumbersToDelete(int articleNo);

  List<ArticleVO> selectPagedArticles(@Param("offset") int offset, @Param("rowCount") int rowCount);

  int selectCountTotalArticles();
}
