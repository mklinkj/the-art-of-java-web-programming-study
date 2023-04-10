package org.mklinkj.taojwp.board;

import static org.mklinkj.taojwp.common.constant.Constants.PAGE_NAVI_SIZE;
import static org.mklinkj.taojwp.common.constant.Constants.PAGE_SIZE;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.mapper.MySqlBoardMapper;

@Slf4j
@RequiredArgsConstructor
public class MySqlBoardDAO implements BoardDAO {

  private final MySqlBoardMapper boardMapper;

  public List<ArticleVO> selectAllArticles() {
    return boardMapper.selectAllArticles();
  }

  public int insertNewArticle(ArticleVO article) {

    int newArticleNo = boardMapper.selectMaxArticleNo() + 1;
    article.setArticleNo(newArticleNo);
    boardMapper.insertArticle(article);
    return newArticleNo;
  }

  public ArticleVO selectArticle(int articleNo) {
    return boardMapper.selectOne(articleNo);
  }

  public int updateArticle(ArticleVO articleVO) {
    return boardMapper.updateArticle(articleVO);
  }

  public int deleteArticle(int articleNo) {
    return boardMapper.deleteArticle(articleNo);
  }

  public List<Integer> selectArticleNumbersToDelete(int articleNo) {
    return boardMapper.selectArticleNumbersToDelete(articleNo);
  }

  public List<ArticleVO> selectPagedArticles(Map<String, Integer> pagingMap) {
    int section = pagingMap.get("section");
    int pageNum = pagingMap.get("pageNum");
    // LIMIT의 offset은 0부터 시작한다. 음수로 잘못 들어올 경우 0으로 처리해주는 보정 코드를 넣어주자.
    int offset =
        Math.max(
            ((section - 1) * (PAGE_SIZE * PAGE_NAVI_SIZE) + (pageNum - 1) * PAGE_SIZE + 1) - 1, 0);
    LOGGER.info("### offset : {}", offset);

    return boardMapper.selectPagedArticles(offset, PAGE_SIZE);
  }

  public int selectCountTotalArticles() {
    return boardMapper.selectCountTotalArticles();
  }
}
