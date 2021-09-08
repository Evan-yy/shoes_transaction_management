package com.hyy.mapper;

import com.hyy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String username);

}
