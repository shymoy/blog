package com.shymoy.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 文章请求DTO
 *
 * @Author: shymoy
 */
@Data
public class ArticleRequest {

    @NotBlank(message = "文章标题不能为空")
    @Size(max = 200, message = "文章标题不能超过200字")
    private String title;

    @Size(max = 500, message = "文章摘要不能超过500字")
    private String summary;

    @NotBlank(message = "文章内容不能为空")
    private String content;

    private String coverImage;

    /**
     * 状态 0-草稿 1-已发布
     */
    private Integer status;
}
