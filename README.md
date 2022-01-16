# New Media

## Features

- 成员列表 user (CRUD+用户验证)
- 留言板 msgboard (CRUD)
- 作品集 artifact (CRUD)

## Technology Stack

- FrontEnd: Vue + Vue Router + ElementUI + Axios
- BackEnd: SpringBoot + Actuator/Prometheus/JMX/Druid + MySql/MyBatis

## FrontEnd

1. Build
   - vue create xxproject
   - vue add router
   - vue add element
   - npm install axios
2. Download & Use
   - npm install
   - npm run build
   - npm run serve

## BackEnd

- 前端 npm run build 之后/dist 下文件放入 src/main/resources/static 中
- 后端 mvn install 打包
- java -jar newmedia-spring-0.0.1-SNAPSHOT.jar

## Database

1. 数据库导入 sql
   - user
   - messageboard
   - artifact
