package org.mklinkj.taojwp.account;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountDAO accountDAO;

  @Transactional(propagation = Propagation.REQUIRED)
  public void sendMoney() {
    accountDAO.uploadBalance1();
    accountDAO.uploadBalance2();
  }

  public List<AccountVO> selectAll() {
    return accountDAO.selectAll();
  }

  public void resetAllBalance() {
    accountDAO.resetAllBalance();
  }
}
