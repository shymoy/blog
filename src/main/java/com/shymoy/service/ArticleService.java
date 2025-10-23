package com.shymoy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shymoy.dto.request.ArticleRequest;
import com.shymoy.dto.response.ArticleVO;
import com.shymoy.dto.response.ArticleListVO;

/**
 * 文章服务接口
 * 
 * @Author: shymoy
 * @Create: 2025/10/24 -11:25
 * @Version: v1.0
 */
public interface ArticleService {

    // ========== 基础 CRUD ==========

    /**
     * 创建文章
     * 
     * @param request 文章请求参数
     * @param userId  用户ID
     * @return 文章ID
     */
    Long createArticle(ArticleRequest request, Long userId);

    /**
     * 更新文章
     * 
     * @param id      文章ID
     * @param request 文章请求参数
     * @param userId  用户ID（用于权限校验）
     */
    void updateArticle(Long id, ArticleRequest request, Long userId);

    /**
     * 删除文章（逻辑删除）
     * 
     * @param id     文章ID
     * @param userId 用户ID（用于权限校验）
     */
    void deleteArticle(Long id, Long userId);

    /**
     * 根据ID查询文章详情
     * 自动增加浏览次数
     * 
     * @param id            文章ID
     * @param currentUserId 当前用户ID（用于判断是否已点赞，可为null）
     * @return 文章详情
     */
    ArticleVO getArticleById(Long id, Long currentUserId);

    // ========== 分页查询 ==========

    /**
     * 分页查询已发布的文章列表
     * 
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 文章列表分页结果
     */
    Page<ArticleListVO> getArticleList(Integer pageNum, Integer pageSize);

    /**
     * 查询某用户的文章列表
     * 
     * @param userId   用户ID
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 文章列表分页结果
     */
    Page<ArticleListVO> getArticlesByUserId(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 查询我的草稿列表
     * 
     * @param userId   用户ID
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 草稿列表分页结果
     */
    Page<ArticleListVO> getMyDrafts(Long userId, Integer pageNum, Integer pageSize);

    // ========== 草稿与发布 ==========

    /**
     * 保存草稿
     * 
     * @param request 文章请求参数
     * @param userId  用户ID
     * @return 文章ID
     */
    Long saveDraft(ArticleRequest request, Long userId);

    /**
     * 发布文章（将草稿变为已发布状态）
     * 
     * @param id     文章ID
     * @param userId 用户ID（用于权限校验）
     */
    void publishArticle(Long id, Long userId);

    // ========== 点赞功能 ==========

    /**
     * 点赞/取消点赞（Toggle）
     * 
     * @param articleId 文章ID
     * @param userId    用户ID
     */
    void likeArticle(Long articleId, Long userId);

    /**
     * 查询用户是否已点赞
     * 
     * @param articleId 文章ID
     * @param userId    用户ID
     * @return true-已点赞 false-未点赞
     */
    boolean isLiked(Long articleId, Long userId);

    // ========== 评论数更新（供 CommentService 调用）==========

    /**
     * 评论数+1
     * 
     * @param articleId 文章ID
     */
    void incrementCommentCount(Long articleId);

    /**
     * 评论数-1
     * 
     * @param articleId 文章ID
     */
    void decrementCommentCount(Long articleId);
}
