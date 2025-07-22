# Nacos Demo 微服务项目

这是一个基于 Spring Cloud Alibaba 和 Nacos 的微服务示例项目，展示了如何使用 Nacos 进行服务注册发现和配置管理，以及如何使用 OpenFeign 和 RestTemplate 进行服务间通信。

## 项目结构

项目采用多模块结构，包含以下服务：

- **order-service**: 订单服务，作为消费者调用其他服务
- **payment-service**: 支付服务，提供支付相关接口
- **user-service**: 用户服务，提供用户相关接口

## 技术栈

- **JDK**: 17
- **Spring Boot**: 3.2.4
- **Spring Cloud**: 2023.0.1
- **Spring Cloud Alibaba**: 2023.0.1.2
- **Nacos**: 服务注册发现和配置中心
- **OpenFeign**: 声明式服务调用
- **RestTemplate**: 服务调用
- **Lombok**: 简化代码
- **FastJSON2**: JSON处理

## 核心功能

### 服务注册与发现

所有服务都注册到 Nacos 注册中心，实现服务的自动发现。

### 配置管理

使用 Nacos 作为配置中心，支持动态配置更新。

### 服务间通信

提供两种服务调用方式：

1. **OpenFeign**: 声明式服务调用，更加简洁优雅
2. **RestTemplate**: 传统的服务调用方式，结合 @LoadBalanced 实现负载均衡

### 服务降级

使用 Feign Fallback 机制实现服务降级，提高系统可用性。

## 快速开始

### 前置条件

- JDK 17+
- Maven 3.6+
- Docker 和 Docker Compose (用于运行 Nacos)

### 启动 Nacos

使用项目提供的 docker-compose 文件启动 Nacos 服务：

```bash
cd nacos-microservice-demo
docker-compose up -d
```

Nacos 控制台访问地址：http://localhost:8088，默认用户名/密码：nacos/nacos

### 构建和运行服务

1. 构建项目

```bash
mvn clean package -DskipTests
```

2. 启动服务（按顺序）

```bash
# 启动支付服务
java -jar payment-service/target/payment-service-1.0-SNAPSHOT.jar

# 启动用户服务
java -jar user-service/target/user-service-1.0-SNAPSHOT.jar

# 启动订单服务
java -jar order-service/target/order-service-1.0-SNAPSHOT.jar
```

### 测试服务

#### 使用 Feign 调用

```bash
curl http://localhost:8081/order/info/feign
```

#### 使用 RestTemplate 调用

```bash
curl http://localhost:8081/order/info/rest
```

## 服务端口

- **order-service**: 8081
- **payment-service**: 8082
- **user-service**: 8083

## 项目特点

1. **模块化设计**: 每个服务都是独立的模块，可以单独部署
2. **统一配置管理**: 使用 Nacos 进行集中配置管理
3. **服务降级**: 实现了服务降级，提高系统可用性
4. **多种调用方式**: 支持 Feign 和 RestTemplate 两种调用方式
5. **代码规范**: 使用 Lombok 简化代码，注释完善

## 注意事项

1. 确保 Nacos 服务正常运行
2. 服务启动顺序建议：先启动被依赖的服务（payment-service 和 user-service），再启动依赖方（order-service）
3. 在 Spring Boot 应用中使用 Feign 客户端时，需要在主应用类上添加 `@EnableFeignClients` 注解

## 贡献指南

欢迎提交 Issue 和 Pull Request 来改进这个项目。

## 许可证

[MIT License](LICENSE)
# sca-microservice-demo
