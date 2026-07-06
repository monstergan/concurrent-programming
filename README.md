# Java 并发编程示例

一个用于学习和演示 Java 并发编程核心概念的示例项目。

## 项目简介

本项目包含了一系列 Java 并发编程的实践示例，涵盖线程基础、线程间通信、同步机制等核心主题。每个示例都是独立可运行的，便于理解和实验。

## 技术栈

- **Java**: 8+
- **构建工具**: Maven
- **日志框架**: SLF4J 2.0.17
- **测试框架**: JUnit 3.8.1

## 项目结构

```
src/main/java/com/monster/
├── base/                       # 线程基础示例
│   ├── OnlyMain.java          # 查看 JVM 中的所有线程
│   ├── NewThread.java         # 创建线程的多种方式
│   ├── ThreadJoinDemo.java    # join() 方法基础示例
│   ├── ThreadJoinDemo2.java   # join() 方法链式调用
│   ├── VolatileDemo.java      # volatile 关键字的可见性
│   ├── MonitorThread.java     # 优雅终止线程的示例
│   ├── WaitDemo.java          # wait() 和 notifyAll() 示例
│   └── ProducerConsumerModel.java  # 生产者-消费者模型
├── future/                     # Future 相关示例
│   └── FutureDemo.java        # Callable 和 FutureTask 的使用
└── utils/                      # 工具类
    └── SleepTools.java        # 线程休眠辅助工具
```

## 核心示例说明

### 线程基础

#### 1. OnlyMain - JVM 线程信息
展示如何使用 `ThreadMXBean` 查看 JVM 中运行的所有线程。

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.OnlyMain"
```

#### 2. NewThread - 创建线程的方式
演示了创建线程的多种方式：
- 继承 `Thread` 类
- 实现 `Runnable` 接口
- Lambda 表达式
- `FutureTask` + `Callable`

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.NewThread"
```

### 线程协调

#### 3. ThreadJoinDemo - 线程等待
演示 `join()` 方法的使用，主线程等待子线程完成后再继续执行。

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.ThreadJoinDemo"
```

#### 4. ThreadJoinDemo2 - 线程链式等待
演示多个线程之间的依赖关系（t3 等待 t2，t2 等待 t1）。

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.ThreadJoinDemo2"
```

### 线程间通信

#### 5. WaitDemo - wait 和 notify
演示 `wait()` 和 `notifyAll()` 的基础用法，实现线程间的等待和唤醒机制。

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.WaitDemo"
```

#### 6. ProducerConsumerModel - 生产者消费者模型
经典的生产者-消费者问题实现，使用 `wait()` 和 `notifyAll()` 进行线程协调。

**特点：**
- 使用 `LinkedList` 作为共享队列
- 队列容量限制为 10
- 生产者在队列满时等待
- 消费者在队列空时等待

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.ProducerConsumerModel"
```

### 可见性与同步

#### 7. VolatileDemo - volatile 可见性
演示 `volatile` 关键字如何保证变量在多线程间的可见性。

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.VolatileDemo"
```

#### 8. MonitorThread - 优雅终止线程
展示如何使用 `volatile` 标志和 `join()` 方法优雅地终止线程，并执行清理操作。

**关键点：**
- 使用 `volatile boolean` 作为终止标志
- 线程检查标志并自行退出
- 终止前执行资源清理

```bash
mvn exec:java -Dexec.mainClass="com.monster.base.MonitorThread"
```

### Future 机制

#### 9. FutureDemo - 异步任务结果获取
演示 `FutureTask` 和 `Callable` 的使用，展示如何从异步任务中获取返回值。

**对比：**
- `Runnable`：无返回值
- `Callable` + `FutureTask`：可以返回结果

```bash
mvn exec:java -Dexec.mainClass="com.monster.future.FutureDemo"
```

## 快速开始

### 环境要求

- JDK 8 或更高版本
- Maven 3.x

### 构建项目

```bash
# 克隆项目（如果需要）
git clone <repository-url>
cd concurrent-programming

# 编译项目
mvn clean compile

# 运行测试
mvn test

# 打包
mvn package
```

### 运行示例

每个示例类都包含 `main()` 方法，可以直接运行：

```bash
# 方式 1：使用 Maven
mvn exec:java -Dexec.mainClass="com.monster.base.ClassName"

# 方式 2：编译后直接运行
mvn clean compile
java -cp target/classes com.monster.base.ClassName
```

## 核心概念

### 1. 线程创建
- 继承 `Thread` 类
- 实现 `Runnable` 接口
- 使用 `Callable` + `FutureTask`

### 2. 线程同步
- `synchronized` 关键字
- `wait()` / `notify()` / `notifyAll()`
- `volatile` 关键字

### 3. 线程协调
- `join()` 方法
- `wait()` / `notify()` 机制
- 生产者-消费者模型

### 4. 线程终止
- 使用 `volatile` 标志
- 优雅关闭与资源清理

### 5. 异步结果
- `Callable` 接口
- `Future` / `FutureTask`

## 工具类

### SleepTools
提供便捷的线程休眠方法：
- `second(int seconds)` - 按秒休眠
- `ms(int milliseconds)` - 按毫秒休眠

## 学习路径建议

1. **基础入门**：`OnlyMain` → `NewThread`
2. **线程协调**：`ThreadJoinDemo` → `ThreadJoinDemo2`
3. **可见性**：`VolatileDemo`
4. **线程通信**：`WaitDemo` → `ProducerConsumerModel`
5. **线程终止**：`MonitorThread`
6. **异步任务**：`FutureDemo`

## 注意事项

- 所有示例使用 SLF4J 进行日志输出，确保正确配置日志级别
- 部分示例包含较长的 `Thread.sleep()`，运行时需要耐心等待
- 建议在 IDE 中运行示例以便查看详细的日志输出

## 贡献

欢迎提交 Issue 和 Pull Request 来改进示例代码。

## 许可证

本项目仅用于学习和教学目的。
