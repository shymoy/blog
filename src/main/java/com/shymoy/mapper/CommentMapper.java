package com.shymoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shymoy.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: CommentMapper
 * Package:com.shymoy.mapper
 * Description:
 *
 * @Author: shymoy
 * @Create: 2025/10/24 -11:12
 * @Version: v1.0
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
