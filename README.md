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

