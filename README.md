# Quadra Admin Modules

一个基于 DDD 分层的后台管理示例工程，演示聚合与领域服务、应用层编排、事件发布、JPA 持久化适配与模块化拆分。

## 模块结构
- `quadra-admin-domain`：领域层（聚合、值对象、领域事件、领域服务、端口）
- `quadra-admin-common`：通用工具（如 `JwtUtil`、`Result`）
- `quadra-admin-application`：应用层（用例接口与服务编排、事件发布）
- `quadra-admin-adapter`：适配器层
  - `quadra-admin-adapter-in-web`：Web 入口（预留）
  - `quadra-admin-adapter-out-persistence`：JPA 持久化适配
- `quadra-admin-bootstrap`：启动层（Spring Boot 应用与配置）

## 架构设计
- 领域层不依赖 Spring；通过应用层配置进行装配
- 依赖方向：Application → Domain（依赖端口与服务），Adapter ↔ Application（实现端口），Bootstrap 装配与运行

## 领域模型与服务
- 聚合根 `Admin`
  - 启用：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/Admin.java:31`
  - 禁用：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/Admin.java:39`
  - 软删：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/Admin.java:47`
  - 改密并发布事件：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/Admin.java:72`
- 端口 `AdminRepository`：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/port/AdminRepository.java:8`
- 领域事件 `AdminPasswordChangedEvent`：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/event/AdminPasswordChangedEvent.java:1`
- 领域服务 `AdminDomainService`
  - 创建（带/不带头像）：`quadra-admin-domain/src/main/java/com/wuyunbin/quadra/admin/domain/service/AdminDomainService.java:35,64`
  - 用户名唯一性：`.../AdminDomainService.java:72`
  - 密码策略校验：`.../AdminDomainService.java:87`
  - 头像规范化：`.../AdminDomainService.java:105`
  - 统一更新入口：`.../AdminDomainService.java:112`

## 应用层用例与服务
- 用例接口在 `quadra-admin-application/src/main/java/.../usecase/`：创建、更新、列表、登录、禁用、软删等
- 服务
  - 创建：`CreateAdminService` 调用领域服务、持久化保存
    - `quadra-admin-application/src/main/java/com/wuyunbin/quadra/admin/application/service/CreateAdminService.java:25`
  - 更新与批量操作：`AdminApplicationService`
    - 统一更新：`quadra-admin-application/src/main/java/com/wuyunbin/quadra/admin/application/service/AdminApplicationService.java:47–64`
    - 批量禁用：`.../AdminApplicationService.java:67–82`
    - 批量软删：`.../AdminApplicationService.java:85–100`
  - 事件发布统一方法：`publishEvents`
    - `quadra-admin-application/src/main/java/com/wuyunbin/quadra/admin/application/service/AdminApplicationService.java:88–95`
- 认证：`AuthService` 使用 `JwtUtil` 生成令牌
  - `quadra-admin-application/src/main/java/com/wuyunbin/quadra/admin/application/service/AuthService.java:18–25`

## 适配器层（持久化）
- JPA 仓储 `AdminJpaRepository`：`quadra-admin-adapter/quadra-admin-adapter-out-persistence/src/main/java/com/wuyunbin/quadra/admin/adapter/out/persistence/AdminJpaRepository.java:12`
- 实体映射 `AdminEntity`：`.../entity/AdminEntity.java:6`
- 端口适配器 `AdminRepositoryAdapter`
  - 聚合 ↔ 实体映射：`.../AdminRepositoryAdapter.java:71`
  - 分页封装：`.../AdminRepositoryAdapter.java:59`

## 启动与配置
- 启动类：`QuadraAdminBootstrapApplication`
  - `quadra-admin-bootstrap/src/main/java/com/wuyunbin/quadra/admin/bootstrap/QuadraAdminBootstrapApplication.java:6`
- JPA 配置与仓储开启：
  - `JpaBootstrapConfig`：`quadra-admin-bootstrap/src/main/java/com/wuyunbin/quadra/admin/bootstrap/config/JpaBootstrapConfig.java:12`
  - `PersistenceConfig`：`quadra-admin-bootstrap/src/main/java/com/wuyunbin/quadra/admin/bootstrap/config/PersistenceConfig.java:6`
- 数据脚本：`quadra-admin-bootstrap/src/main/resources/schema.sql`、`data.sql`

## 构建与测试
- 构建：
  - `mvn -DskipTests clean install`
- 测试：
  - 全仓库：`mvn -DskipTests=false test`
  - 指定模块：`mvn -DskipTests=false -pl quadra-admin-domain,quadra-admin-application test`

## 运行
- 启动：在 `quadra-admin-bootstrap` 运行 `QuadraAdminBootstrapApplication`
- 配置：编辑 `quadra-admin-bootstrap/src/main/resources/application.properties` 配置数据库与端口

## 示例流程
- 创建管理员
  - `CreateAdminService.create("alice", "Alice@123")` → 领域服务构建聚合 → 仓储保存
- 更新管理员
  - `AdminApplicationService.update(id, "newName", avatarUrl, "New@1234")` → 统一更新（唯一性、头像规范化、密码策略） → 保存 → 发布事件
- 批量禁用/软删
  - 加载聚合 → `AdminDomainService.disableAll/softDeleteAll` → 保存 → 发布事件
- 登录
  - `AuthService.login(username, password)` → `JwtUtil.generateToken(...)`

## 事件机制
- 事件由聚合在状态变更时产生（例如 `Admin.changePassword`）；应用层统一收集并通过 `ApplicationEventPublisher` 发布
- 统一发布方法：`publishEvents`

## 约定与扩展
- 领域层不引入 Spring；应用层通过 `DomainBeansConfig` 注入 `AdminDomainService`
- 可扩展：密码策略（历史/黑名单）、用户名策略（格式/保留字）、统一事件派发器组件
