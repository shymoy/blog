package com.shymoy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shymoy.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ArticleMapper
 * Package:com.shymoy.mapper
 * Description:
 *
 * @Author: shymoy
 * @Create: 2025/10/24 -11:11
 * @Version: v1.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
