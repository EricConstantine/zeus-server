## Zeus使用手册
----------
Zeus代码生成器是一款自动代码生成工具，旨在快速生成基础的CRUD代码。

后端是基于`springboot`、`freemarker`、`mybatisplus`实现，前端采用`vue3`、`elementplus`、`electron`技术。

在此基础上也提供了一些高级功能，做到灵活配置，生成可扩展性强的代码。 客户端支持多开。同一个电脑可以打开多个客户端。可以分别登录管理员和普通用户的账号，管理员修改模板内容。普通用户生成代码。

-  **[ZEUS官网](https://engini.vip/zeus/docs)**
-  **[后端Gitee 仓库地址](https://gitee.com/rederic/zeus-server.git)** | [前端Gitee 仓库地址](https://gitee.com/rederic/zeus-view.git)
-  **[后端GitHub 仓库地址](https://github.com/EricConstantine/zeus-server.git)** | [前端GitHub 仓库地址](https://github.com/EricConstantine/zeus-view.git)

> [!TIP]
> **优势**
> 1. 项目结构可视化，文件树、文件图形化的模板编辑，操作模板更加直观、简洁。
> 2. 使用Freemarker作为模板引擎，模板中支持Freemarker所有语法，系统提供强大的内置字典。模板操作更加灵活。
> 3. Zeus可以作为作为mysql、oracle客户端，直接连接内网数据库。
> 4. 脚本管理。取代Jenkins，轻量化快速更新打包项目。网页查看项目日志等功能。
> 5. 方便团队开发项目，统一代码风格，规范化代码接口。

客户端支持多开。同一个电脑可以打开多个客户端。可以分别登录管理员和普通用户的账号，管理员修改模板内容。普通用户生成代码。
# ZEUS 代码生成器后台

## 1. 技术框架

zeus代码生成器后台采用springboot、mybatisplus、freemarker技术开发

zeus代码生成器前台采用vue3、typescript、elementplus技术开发

## 2. 如何上手

1. 新建数据库code

2. 导入code.sql

3. 克隆后台项目代码到本地

   ```
   git clone https://gitee.com/rederic/zeus-server.git
   ```

4. 启动springboot项目

5. 克隆前台代码到本地

   ```
   git clone https://gitee.com/rederic/zeus-view.git
   ```

6. 启动前端项目

   ```
   npm install
   npm run dev
   ```

## 3. 快速开发

可以使用代码生成器后端模板快速生成前后端代码

## 4. 项目打包部署

前台项目打包部署

```
npm run build:prod
```

找到dist_electron 文件夹把 zeus Setup 2.7.8.exe 安装文件

后台项目打包部署

```
mvn package -DskipTests
```

