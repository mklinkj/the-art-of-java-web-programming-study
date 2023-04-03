package org.mklinkj.taojwp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.mklinkj.taojwp.sec03.brd01.ArticleVO;

@Mapper
public interface BoardMapper {

  List<ArticleVO> selectAllArticles();

  int selectMaxArticleNo();

  int insertArticle(ArticleVO article);
}
