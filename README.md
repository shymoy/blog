# 个人博客系统

## 项目简介

这是一个前后端分离的个人博客系统，使用Spring Boot + Vue 3构建。

**技术栈**：
- 后端：Spring Boot 3.1.5 + MyBatis Plus + MySQL + Redis + JWT
- 前端：Vue 3 + Vite + Element Plus（待开发）

## 项目结构

```
blog/
├── src/main/
│   ├── java/com/shymoy/
│   │   ├── BlogApplication.java     ✅ 启动类
│   │   ├── common/                  ✅ 通用类
│   │   │   ├── Result.java          - 统一返回结果
│   │   │   └── BaseEntity.java      - 实体基类
│   │   ├── exception/               ✅ 异常处理
│   │   │   ├── BusinessException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── config/                  ✅ 配置类
│   │   │   ├── MyBatisPlusConfig.java
│   │   │   ├── MetaObjectHandlerConfig.java
│   │   │   ├── CorsConfig.java
│   │   │   └── SecurityConfig.java
│   │   ├── util/                    ✅ 工具类
│   │   │   └── JwtUtil.java
│   │   ├── entity/                  ⏳ 待开发（你来写）
│   │   ├── mapper/                  ⏳ 待开发（你来写）
│   │   ├── service/                 ⏳ 待开发（你来写）
│   │   └── controller/              ⏳ 待开发（你来写）
│   └── resources/
│       ├── application.yml          ✅ 配置文件
│       └── sql/
│           └── init.sql             ✅ 数据库初始化脚本
├── pom.xml                          ✅ Maven依赖
└── README.md                        ✅ 项目说明
```

## 快速开始

### 1. 环境要求

- JDK 17
- MySQL 8.0+
- Redis（可选，暂时不用也能跑）
- Maven 3.6+

### 2. 数据库初始化

在MySQL中执行以下命令：

```bash
# 方式1：直接导入SQL文件
mysql -u root -p < src/main/resources/sql/init.sql

# 方式2：在MySQL客户端中执行
source src/main/resources/sql/init.sql
```

这会创建：
- `blog` 数据库
- `user` 用户表
- `article` 文章表
- `comment` 评论表
- 默认管理员账号：`admin` / `123456`

### 3. 修改配置

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/blog?...
    username: root
    password: root  # 改成你的MySQL密码
```

### 4. 启动项目

```bash
# 方式1：IDEA中直接运行BlogApplication的main方法

# 方式2：命令行
mvn spring-boot:run

# 方式3：打包后运行
mvn clean package
java -jar target/blog-1.0-SNAPSHOT.jar
```

启动成功后，访问：`http://localhost:8080/api`

## 接口设计

### 用户模块
```
POST   /api/auth/register      # 注册
POST   /api/auth/login         # 登录
GET    /api/user/info          # 获取用户信息
```

### 文章模块
```
GET    /api/articles           # 文章列表（分页）
GET    /api/articles/{id}      # 文章详情
POST   /api/articles           # 发布文章（需登录）
PUT    /api/articles/{id}      # 编辑文章（需登录）
DELETE /api/articles/{id}      # 删除文章（需登录）
GET    /api/articles/search    # 搜索文章
```

### 评论模块
```
GET    /api/comments           # 获取评论（按文章ID）
POST   /api/comments           # 发表评论（需登录）
DELETE /api/comments/{id}      # 删除评论（需登录）
```

## 下一步（你来完成）

### 步骤1：创建实体类

在 `src/main/java/com/shymoy/entity/` 下创建：
- `User.java`
- `Article.java`
- `Comment.java`

提示：继承 `BaseEntity`，使用 `@TableName` 注解

### 步骤2：创建Mapper接口

在 `src/main/java/com/shymoy/mapper/` 下创建：
- `UserMapper.java`
- `ArticleMapper.java`
- `CommentMapper.java`

提示：继承 `BaseMapper<T>`，加 `@Mapper` 注解

### 步骤3：创建Service层

在 `src/main/java/com/shymoy/service/` 下创建：
- 接口：`UserService.java`, `ArticleService.java`, `CommentService.java`
- 实现类：`impl/UserServiceImpl.java`, `impl/ArticleServiceImpl.java`, `impl/CommentServiceImpl.java`

### 步骤4：创建Controller层

在 `src/main/java/com/shymoy/controller/` 下创建：
- `AuthController.java` - 注册、登录
- `UserController.java` - 用户信息
- `ArticleController.java` - 文章管理
- `CommentController.java` - 评论管理

### 步骤5：创建DTO类

在 `src/main/java/com/shymoy/dto/` 下创建：
- `LoginRequest.java`
- `RegisterRequest.java`
- `ArticleRequest.java`
- `CommentRequest.java`
- `LoginResponse.java`

## 学习建议

1. **先实现用户模块**：注册、登录、JWT认证
2. **再实现文章模块**：CRUD、分页查询、搜索
3. **最后实现评论模块**：发表评论、查询评论、删除评论
4. **测试接口**：使用Postman或Apifox测试

## 遇到问题？

- 启动报错：检查MySQL是否启动，数据库是否创建
- 依赖下载失败：检查Maven配置，换镜像源
- 接口404：检查Controller的`@RequestMapping`路径
- 参数校验失败：检查DTO的`@Valid`注解和校验规则

## 作者

@shymoy - 2025

## 许可

MIT License

