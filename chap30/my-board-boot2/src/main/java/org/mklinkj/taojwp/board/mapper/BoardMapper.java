package org.mklinkj.taojwp.board.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mklinkj.taojwp.board.domain.ArticleVO;

@Mapper
public interface BoardMapper {

  List<ArticleVO> selectAllArticles();

  int selectMaxArticleNo();

  int insertArticle(ArticleVO article);

  ArticleVO selectOne(int articleNo);

  int updateArticle(ArticleVO articleVO);

  int deleteArticle(int articleNo);

  List<Integer> selectArticleNumbersToDelete(int articleNo);

  List<ArticleVO> selectPagedArticles(@Param("section") int section, @Param("pageNum") int pageNum);

  int selectCountTotalArticles();
}
