package org.mklinkj.taojwp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.mklinkj.taojwp.sec03.brd01.ArticleVO;

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
