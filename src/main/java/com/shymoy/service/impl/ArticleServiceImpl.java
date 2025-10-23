package com.shymoy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shymoy.dto.request.ArticleRequest;
import com.shymoy.dto.response.ArticleListVO;
import com.shymoy.dto.response.ArticleVO;
import com.shymoy.entity.Article;
import com.shymoy.exception.BusinessException;
import com.shymoy.mapper.ArticleMapper;
import com.shymoy.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 文章服务实现类
 * 
 * @Author: shymoy
 * @Create: 2025/10/24 -11:26
 * @Version: v1.0
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public Long createArticle(ArticleRequest request, Long userId) {
        Article article = new Article();
        BeanUtils.copyProperties(request, article);

        // 设置作者ID
        article.setUserId(userId);

        // 初始化统计字段
        article.setViewCount(0);
        article.setLikeCount(0);
        article.setCommentCount(0);

        articleMapper.insert(article);
        return article.getId();
    }

    @Override
    public void updateArticle(Long id, ArticleRequest request, Long userId) {
        // 查询文章
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }

        // 权限校验：只有作者才能修改
        if (!article.getUserId().equals(userId)) {
            throw new BusinessException("无权修改此文章");
        }

        // 更新文章内容
        BeanUtils.copyProperties(request, article);

        // 保持原有的作者ID和统计数据（BeanUtils 不会覆盖 null 值）
        articleMapper.updateById(article);
    }

    @Override
    public void deleteArticle(Long id, Long userId) {
        // 查询文章
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }

        // 权限校验：只有作者才能删除
        if (!article.getUserId().equals(userId)) {
            throw new BusinessException("无权删除此文章");
        }

        // 逻辑删除（MyBatis Plus 会自动处理）
        articleMapper.deleteById(id);
    }

    @Override
    public ArticleVO getArticleById(Long id, Long currentUserId) {
        // TODO: 实现查询文章详情逻辑
        // TODO: 自动增加浏览次数
        // TODO: 查询作者信息
        // TODO: 判断当前用户是否已点赞
        return null;
    }

    @Override
    public Page<ArticleListVO> getArticleList(Integer pageNum, Integer pageSize) {
        // TODO: 实现分页查询已发布文章列表
        return null;
    }

    @Override
    public Page<ArticleListVO> getArticlesByUserId(Long userId, Integer pageNum, Integer pageSize) {
        // TODO: 实现查询某用户的文章列表
        return null;
    }

    @Override
    public Page<ArticleListVO> getMyDrafts(Long userId, Integer pageNum, Integer pageSize) {
        // TODO: 实现查询我的草稿列表
        return null;
    }

    @Override
    public Long saveDraft(ArticleRequest request, Long userId) {
        Article article = new Article();
        BeanUtils.copyProperties(request, article);

        // 设置作者ID
        article.setUserId(userId);

        // 强制设置为草稿状态
        article.setStatus(0);

        // 初始化统计字段
        article.setViewCount(0);
        article.setLikeCount(0);
        article.setCommentCount(0);

        articleMapper.insert(article);
        return article.getId();
    }

    @Override
    public void publishArticle(Long id, Long userId) {
        // 查询文章
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }

        // 权限校验：只有作者才能发布
        if (!article.getUserId().equals(userId)) {
            throw new BusinessException("无权发布此文章");
        }

        // 修改状态为已发布
        article.setStatus(1);
        articleMapper.updateById(article);
    }

    @Override
    public void likeArticle(Long articleId, Long userId) {
        // TODO: 实现点赞/取消点赞逻辑
        // TODO: 使用 Redis Set 存储点赞关系
        // TODO: 更新数据库 like_count
    }

    @Override
    public boolean isLiked(Long articleId, Long userId) {
        // TODO: 实现查询是否已点赞逻辑
        // TODO: 从 Redis 查询
        return false;
    }

    @Override
    public void incrementCommentCount(Long articleId) {
        // TODO: 实现评论数+1逻辑
    }

    @Override
    public void decrementCommentCount(Long articleId) {
        // TODO: 实现评论数-1逻辑
    }
}
