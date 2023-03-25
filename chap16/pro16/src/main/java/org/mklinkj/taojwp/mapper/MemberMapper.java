package org.mklinkj.taojwp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mklinkj.taojwp.sec02.ex01.MemberVO;

@Mapper
public interface MemberMapper {

  @Select(
      """
      SELECT DECODE(COUNT(*), 1, 'true', 'false') AS result
        FROM t_member
       WHERE id = #{id}
      """)
  boolean overlappedId(String id);

  @Insert(
      """
      INSERT INTO t_member (id, pwd, name, email, joinDate)
      VALUES (#{id}, #{pwd}, #{name}, #{email}, #{joinDate})
      """)
  int insertMember(MemberVO memberVO);
}
