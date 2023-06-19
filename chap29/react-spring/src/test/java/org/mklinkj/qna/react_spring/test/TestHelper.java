package org.mklinkj.qna.react_spring.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestHelper {
  @Autowired private EntityManager entityManager;

  /** 현재 ID가 자동생성 컬럼이 아니다, 간단하게 새로운 아이디 얻는 쿼리 실행 */
  public Integer createNewId() {
    TypedQuery<Integer> query =
        entityManager.createQuery(
            """
              SELECT max(a.articleNo) + 1
                FROM Article a
            """,
            Integer.class);
    return query.getSingleResult();
  }
}
