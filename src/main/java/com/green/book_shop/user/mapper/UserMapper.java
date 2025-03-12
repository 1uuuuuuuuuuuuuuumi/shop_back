package com.green.book_shop.user.mapper;

import com.green.book_shop.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  //회원가입
  public void join(UserDTO userDTO);

  //id 중복 확인 (-> ServiceImpl의 회원가입 코너로 ㄱㄱ)
  public String checkUserId(String userId);

  //로그인
  public UserDTO login(UserDTO userDTO);

}
