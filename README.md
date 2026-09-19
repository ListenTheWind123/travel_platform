# 重庆南山景区旅游系统

一个基于 Spring Boot 的景区旅游管理平台，提供景区攻略、游玩路线、酒店与景点的在线浏览、收藏关注和预订功能，并配套完整的后台管理系统。

## 项目简介

本系统是一个前后端分离的传统 CRUD 项目，面向游客提供重庆南山景区的游玩攻略、路线推荐、酒店与景点在线预订等服务；面向管理员提供用户、酒店、景点、攻略、路线等信息的统一管理。

## 技术栈

- **后端**：Spring Boot、Spring Data JPA、Thymeleaf
- **前端**：Bootstrap、jQuery、Layui
- **数据库**：MySQL
- **构建工具**：Maven

## 功能模块

### 前台（游客端）

- 用户注册、登录、个人信息与密码修改
- 游玩路线查询、详情查看与关注
- 游玩攻略查询、详情查看、收藏与发布
- 酒店、景点查询与在线预订
- 我的关注、收藏、预订列表管理

### 后台（管理员端）

- 用户管理
- 酒店管理
- 景点管理
- 攻略管理
- 路线管理

## 快速开始

### 环境要求

- JDK 8
- Maven 3.x
- MySQL 5.7+

### 运行步骤

1. 创建数据库并导入初始化脚本：

   ```sql
   source sql/travel.sql;
   ```

2. 修改数据库连接配置（`src/main/resources/application.yml`）：

   ```yaml
   spring:
     datasource:
       username: root
       password: '123456'
       url: jdbc:mysql://localhost:3306/travel?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
   ```

3. 启动项目：

   ```bash
   mvn spring-boot:run
   ```

4. 访问系统：

   - 前台首页：`http://localhost:8080/travel`
   - 后台管理：`http://localhost:8080/travel/system/login`

   默认后台管理员账号：`admin`，密码：`admin`。

## 目录结构

```
travel_platform
├── sql/                 # 数据库初始化脚本
├── src/main/java/       # Java 源码
│   └── com/power/travel
│       ├── controller/  # 控制层
│       ├── service/     # 业务层
│       ├── repository/  # 数据访问层
│       ├── model/       # 实体模型
│       ├── core/        # 通用返回结果与异常
│       ├── enums/       # 枚举定义
│       └── util/        # 工具类
└── src/main/resources/  # 配置、模板与静态资源
```

## 许可证

本项目仅用于学习交流，感兴趣的同学可自行下载学习。
