package org.mklinkj.taojwp.common.util;

import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/** 트랜젝션으로 묶어야하는 작업이 있을 경우 묶을 때, 테스트 해보려고 만듬. 팩토리를 하나만 쓰도록 하자. */
public class TransactionFactoryHelper {
  private static final TransactionFactory FACTORY = MyBatisTransactionFactoryHolder.FACTORY;

  public static Transaction newTransaction() {
    return FACTORY.newTransaction(DBUtils.getDataSourceFromJNDI(), null, false);
  }

  public static TransactionFactory transactionFactory() {
    return FACTORY;
  }

  private static class MyBatisTransactionFactoryHolder {
    private static final TransactionFactory FACTORY;

    static {
      FACTORY = new JdbcTransactionFactory();
    }
  }
}
