package com.shymoy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shymoy.common.Result;
import com.shymoy.dto.request.ArticleRequest;
import com.shymoy.dto.response.ArticleListVO;
import com.shymoy.dto.response.ArticleVO;
import com.shymoy.service.ArticleService;
import com.shymoy.util.UserContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 文章控制器
 * 
 * @Author: shymoy
 */
@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserContext userContext;

    // ========== 创建文章 ==========

    /**
     * 创建文章（可以直接发布或保存为草稿）
     * 
     * @param request 文章请求参数（status: 0-草稿, 1-已发布）
     * @return 文章ID
     */
    @PostMapping
    public Result<Long> createArticle(@RequestBody @Valid ArticleRequest request) {
        Long userId = userContext.getCurrentUserId();
        Long articleId = articleService.createArticle(request, userId);
        return Result.success(articleId);
    }

    /**
     * 保存草稿
     * 
     * @param request 文章请求参数
     * @return 文章ID
     */
    @PostMapping("/draft")
    public Result<Long> saveDraft(@RequestBody @Valid ArticleRequest request) {
        Long userId = userContext.getCurrentUserId();
        Long articleId = articleService.saveDraft(request, userId);
        return Result.success(articleId);
    }

    // ========== 更新文章 ==========

    /**
     * 更新文章
     * 
     * @param id      文章ID
     * @param request 文章请求参数
     * @return 成功响应
     */
    @PutMapping("/{id}")
    public Result<Void> updateArticle(@PathVariable Long id,
            @RequestBody @Valid ArticleRequest request) {
        Long userId = userContext.getCurrentUserId();
        articleService.updateArticle(id, request, userId);
        return Result.success();
    }

    /**
     * 发布文章（将草稿改为已发布状态）
     * 
     * @param id 文章ID
     * @return 成功响应
     */
    @PutMapping("/{id}/publish")
    public Result<Void> publishArticle(@PathVariable Long id) {
        Long userId = userContext.getCurrentUserId();
        articleService.publishArticle(id, userId);
        return Result.success();
    }

    // ========== 删除文章 ==========

    /**
     * 删除文章
     * 
     * @param id 文章ID
     * @return 成功响应
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable Long id) {
        Long userId = userContext.getCurrentUserId();
        articleService.deleteArticle(id, userId);
        return Result.success();
    }

    // ========== 查询文章 ==========

    /**
     * 查询文章详情
     * 自动增加浏览次数
     * 
     * @param id 文章ID
     * @return 文章详情
     */
    @GetMapping("/{id}")
    public Result<ArticleVO> getArticleById(@PathVariable Long id) {
        // 获取当前用户ID（可能为null，未登录用户也能查看）
        Long currentUserId = userContext.getCurrentUserIdOrNull();
        ArticleVO article = articleService.getArticleById(id, currentUserId);
        return Result.success(article);
    }

    /**
     * 分页查询已发布的文章列表
     * 
     * @param pageNum  页码，默认第1页
     * @param pageSize 每页数量，默认10条
     * @return 文章列表分页结果
     */
    @GetMapping("/list")
    public Result<Page<ArticleListVO>> getArticleList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ArticleListVO> page = articleService.getArticleList(pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询某用户的文章列表
     * 
     * @param userId   用户ID
     * @param pageNum  页码，默认第1页
     * @param pageSize 每页数量，默认10条
     * @return 文章列表分页结果
     */
    @GetMapping("/user/{userId}")
    public Result<Page<ArticleListVO>> getArticlesByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ArticleListVO> page = articleService.getArticlesByUserId(userId, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询我的草稿列表
     * 
     * @param pageNum  页码，默认第1页
     * @param pageSize 每页数量，默认10条
     * @return 草稿列表分页结果
     */
    @GetMapping("/my/drafts")
    public Result<Page<ArticleListVO>> getMyDrafts(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Long userId = userContext.getCurrentUserId();
        Page<ArticleListVO> page = articleService.getMyDrafts(userId, pageNum, pageSize);
        return Result.success(page);
    }

    // ========== 点赞功能 ==========

    /**
     * 点赞/取消点赞（Toggle）
     * 
     * @param id 文章ID
     * @return 成功响应
     */
    @PostMapping("/{id}/like")
    public Result<Void> likeArticle(@PathVariable Long id) {
        Long userId = userContext.getCurrentUserId();
        articleService.likeArticle(id, userId);
        return Result.success();
    }

    /**
     * 查询是否已点赞
     * 
     * @param id 文章ID
     * @return true-已点赞 false-未点赞
     */
    @GetMapping("/{id}/liked")
    public Result<Boolean> isLiked(@PathVariable Long id) {
        Long userId = userContext.getCurrentUserId();
        boolean liked = articleService.isLiked(id, userId);
        return Result.success(liked);
    }
}
