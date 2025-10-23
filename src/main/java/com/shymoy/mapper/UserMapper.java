package com.shymoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shymoy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package:com.shymoy.mapper
 * Description:
 *
 * @Author: shymoy
 * @Create: 2025/10/24 -11:13
 * @Version: v1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
