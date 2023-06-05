package org.mklinkj.taojwp.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("accountVO")
@Getter
@Setter
@ToString
public class AccountVO {
  private String accountNo;
  private String custName;
  private int balance;
}
