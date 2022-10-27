# 项目启动步骤

## 框架选取

`Vue.js` + `Navie UI` + `Spring Boot` + `Spring Data MongoDB`

## 前端配置

:grinning: 需要 `Node.js` 运行环境以及 `npm` 包管理器

打开 命令行，进入`vue` 文件夹，`npm install` 安装依赖

执行`npm run dev` 启动前端

## 后端配置

:smile:推荐使用 `IntelliJ IDEA` 来启动

### 一、数据库配置

MySQL 建立数据库， 名为 `lucene`，执行 目录下的 `lucene.sql`  ，生成 `chapter` 表，表内容应为红楼梦的所有章节





### 二、Spring Boot 配置

用 `IntelliJ IDEA` 打开`boot`文件夹，Maven会自动下载依赖，下载完成之后项目的目录如下

<img src="README.assets/image-20221021140724974.png" alt="image-20221021140724974" style="zoom:67%;" />

在 `application.yml` 中，将用户名和密码修改为本机 `MySQL `的用户名和密码

<img src="README.assets/image-20221021135610301.png" alt="image-20221021135610301" style="zoom:67%;" />

运行后端，此时点击管理页面，出现数据即前后端成功通信，**项目启动完成。**

<img src="README.assets/image-20221021143006541.png" alt="image-20221021143006541"  />

> 搜索功能：

![image-20221021154230537](README.assets/image-20221021154230537.png)



> 管理功能：

在管理页面进行**章节**<chapter>的增删改之后，**索引库会自动更新相应的索引**

![image-20221021154602531](README.assets/image-20221021154602531.png)



【补充】：`index` 文件为项目的索引库存放的位置，如果删除，可以向后端发送 `post` 请求： [http://localhost:8080/search](http://localhost:8080/search)，**后端会自动根据数据库文件重新生成索引**

![image-20221021143208098](README.assets/image-20221021143208098.png)

