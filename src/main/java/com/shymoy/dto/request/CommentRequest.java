package com.shymoy.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 评论请求DTO
 * 
 * @Author: shymoy
 */
@Data
public class CommentRequest {

    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 父评论ID（用于回复功能）
     */
    private Long parentId;
}

