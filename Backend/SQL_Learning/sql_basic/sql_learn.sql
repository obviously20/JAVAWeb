-- - 数据库：英文为 DataBase，简称DB，它是存储和管理数据的仓库。


-- - 数据库管理系统（DataBase Management System，简称DBMS），是操作和管理数据库的大型软件。


-- - SQL（Structured Query Language，简称SQL）：结构化查询语言，它是操作关系型数据库的编程语言，定义了一套操作关系型数据库的统一标准。
# 结论：程序员给数据库管理系统(DBMS)发送SQL语句，再由数据库管理系统操作数据库当中的数据。
# 结论：只要我们学会了SQL语句，就可以通过SQL语句来操作Mysql，也可以通过SQL语句来操作Oracle或SQL Server


-- cmd终端连接数据库的命令：mysql [-h数据库服务器的IP地址 -P端口号] -u用户名 -p密码
# - -h  参数不加，默认连接的是本地 127.0.0.1 的MySQL服务器
# - -P  参数不加，默认连接的端口号是 3306


-- 数据库->企业使用方式(了解):MySQL服务器我们是安装在本地的，这个仅仅是在我们学习阶段，在真实的企业开发中，MySQL数据库服务器是不会在我们本地安装的，
# 是在公司的服务器上安装的，而服务器还需要放置在专门的IDC机房中的，
# IDC机房呢，就需要保证恒温、恒湿、恒压，而且还要保证网络、电源的可靠性(备用电源及网络)。
# 那我们要想使用服务器上的这台MySQL服务器，就需要在我们的电脑上去远程连接这台MySQL 。
# 而服务器上安装的MySQL数据库呢，并不是你一个人在访问，我们项目组的其他开发人员也是需要访问这台MySQL的。
# 接下来，就来演示一下，通过MySQL的客户端命令行，如何来连接服务器上部署的MySQL ：
# mysql [-h数据库服务器的IP地址 -P端口号] -u用户名 -p密码


-- 数据模型
# 关系型数据库（RDBMS）：建立在关系模型基础上，由多张相互连接的二维表组成的数据库。而所谓二维表，指的是由行和列组成的表
# 我们之前提到的MySQL、Oracle、DB2、SQLServer这些都是属于关系型数据库，里面都是基于二维表存储数据的。
# 结论：基于二维表存储数据的数据库就成为关系型数据库，不是基于二维表存储数据的数据库，就是非关系型数据库（比如大家后面要学习的Redis，就属于非关系型数据库）。
-- -- 存储数据？
# 在Mysql数据库服务器当中存储数据，你需要：
# 1. 先去创建数据库（可以创建多个数据库，之间是相互独立的）
# 2. 在数据库下再去创建数据表（一个数据库下可以创建多张表）
# 3. 再将数据存放在数据表中（一张表可以存储多行数据）

# -----------------------------------------------------------------------------------------------------------------------------------

-- SQL：结构化查询语言。一门操作关系型数据库的编程语言，定义操作所有关系型数据库的统一标准。SQL语句根据其功能被分为四大类：DDL、DML、DQL、DCL

-- DDL语句：Data Definition Language(数据定义语言)，用来定义数据库对象(数据库、表)。
# DDL中数据库的常见操作：查询、创建、使用、删除。

# - 查询所有数据库
show databases;

# - 查询当前数据库
select database();

# 创建数据库
# - 语法： create database [ if not exists ] 数据库名  [default charset utf8mb4];
create database javaweb_SQL_Learn;
create database javaweb_SQL_Learn2;

# 使用/切换数据库
use javaweb_SQL_Learn;

# 删除数据库
# drop database [ if exists ] 数据库名 ;
drop database javaweb_SQL_Learn2;

# 上述语法中的database，也可以替换成 schema
# - 如：create schema db01;
# - 如：show schemas;


# DDL语句当中关于表结构的操作（创建表、查询表、修改表、删除表）
# create table  表名(
#         字段1  字段1类型 [约束]  [comment  字段1注释 ],
#         字段2  字段2类型 [约束]  [comment  字段2注释 ],
#         ......
#         字段n  字段n类型 [约束]  [comment  字段n注释 ]
# ) [ comment  表注释 ] ;

create table user(
                     id int primary key auto_increment comment '用户唯一标识id',-- primary key 主键 auto_increment 自增
                     username varchar(50) unique not null comment '用户名',-- unique 唯一（不重复） not null 非空
                     name varchar(50) not null comment '用户昵称',
                     age int comment '年龄',
                     gender char(1) default '男' comment '性别'-- default xx 默认值为xx
)comment '用户数据表';

# 创建员工表
# 步骤：
# 1. 阅读产品原型及需求文档，看看里面涉及到哪些字段。
# 2. 查看需求文档说明，确认各个字段的类型以及字段存储数据的长度限制。
# 3. 在页面原型中描述的基础字段的基础上，再增加额外的基础字段(主键，建表时间，改表时间)。
create table emp(
                    id int unsigned primary key auto_increment comment '员工id',
                    password varchar(32) default '123456' comment '员工的账号初始密码',
                    username varchar(20) not null unique comment '用户名',
                    name varchar(10) not null comment '姓名',
                    gender tinyint unsigned not null  comment '性别,1:男 2:女',
                    phone char(11) not null unique comment '手机号',
                    job tinyint unsigned comment '职位,1:班主任 2:讲师 3:学工主管 4:教研主管 5:咨询师',
                    salary int comment '薪资',
                    imag varchar(255) comment '头像(路径)',
                    jointime date comment '入职日期',
                    createtime datetime comment '创表时间',
                    updatetime datetime comment '修改时间'
)comment '员工表';
# 设计表流程：
# 1. 阅读页面原型及需求文档
# 2. 基于页面原则和需求文档，确定原型字段(类型、长度限制、约束)
# 3. 再增加表设计所需要的业务基础字段(id、create_time、update_time)
#   - create_time：记录的是当前这条数据插入的时间。
#   - update_time：记录当前这条数据最后更新的时间。

-- 查询当前数据库的所有表
show tables;

-- 查看指定的表结构
desc emp ;   -- 可以查看指定表的字段、字段的类型、是否可以为NULL、是否存在默认值等信息

-- 查询指定表的建表语句
show create table emp ;

-- 修改
-- 添加字段
# alter table 表名 add  字段名  类型(长度)  [comment 注释]  [约束];
alter table emp add qq varchar(13) comment 'qq号码';

-- 修改字段类型
# alter table 表名 modify  字段名  新数据类型(长度);
alter table emp modify qq varchar(15) comment 'qq';

-- 修改字段名，字段类型
# alter table 表名 change  旧字段名  新字段名  类型(长度)  [comment 注释]  [约束];
alter table emp change qq qq_number varchar(15) comment 'qq_number';

-- 删除字段
# alter table 表名 drop 字段名;
alter table emp drop qq_number;

-- 修改表名
# rename table 表名 to  新表名;
alter table emp rename to employee;

-- 删除表
# drop  table [ if exists ]  表名;
# drop table employee;

-- 下面是对数据进行操作DML:增删改

# 增加(insert)
# insert into 表名 (字段名1, 字段名2) values (值1, 值2);
# insert into 表名 values (值1, 值2, ...);
# insert into 表名 (字段名1, 字段名2) values (值1, 值2), (值1, 值2);
# insert into 表名 values (值1, 值2, ...), (值1, 值2, ...);

# 修改(update)
# update 表名 set 字段名1 = 值1 , 字段名2 = 值2 , .... [where 条件] ;

# 删除(delete)
# delete from 表名  [where  条件] ;


-- DML : 数据操作语言

-- DML : 插入数据 - insert
-- 1. 为 emp 表的 username, password, name, gender, phone 字段插入值
insert into employee (username, password, name, gender, phone) values ('songjiang','12345678','宋江',1,'13413333456');

-- 2. 为 emp 表的 所有字段插入值
insert into employee (id, password, username, name, gender, phone, job, salary, imag, jointime, createtime, updatetime)
values (null,'12345678','likui','李逵',1,'13414633456',3,12000,'https://hsoashopsh.jpg','2026-5-10',now(),now());


insert into employee  values (null,'12345678','wuyong','吴用',1,'13414633457',1,15000,'https://hsoas33hopsh.jpg','2026-5-11',now(),now());
-- 3. 批量为 emp 表的 username, password, name, gender, phone  字段插入数据
insert into employee (username, password, name, gender, phone)
values ('ruanxiaoer','123456677','阮小二',1,'14456744569'),
       ('ruanxiaowu','123452677','阮小伍',1,'14456744869');


-- DML : 更新数据 - update
-- 1. 将 emp 表的ID为1员工 用户名更新为 'zhangsan', 姓名name字段更新为 '张三'
update employee set username='zhangsan',name='张三' where id=1;

-- 2. 将 emp 表的所有员工的入职日期更新为 '2010-01-01'
update employee set jointime='2026-5-10';



-- DML : 删除数据 - delete
-- 1. 删除 emp 表中 ID为1的员工
delete from employee where id=1;

-- 2. 删除 emp 表中的所有员工
# delete from employee;

# 注意事项:
# - DELETE 语句的条件可以有，也可以没有，如果没有条件，则会删除整张表的所有数据。
# - DELETE 语句不能删除某一个字段的值(可以使用UPDATE，将该字段值置为NULL即可)。
# - 当进行删除全部数据操作时，会提示询问是否确认删除所有数据，直接点击Execute即可。



-- ---------------------------------------------------------------------------------------------------------------------

# DQL查询语句，语法结构如下：
# SELECT
#         字段列表
# FROM
#         表名列表
# WHERE
#         条件列表
# GROUP  BY
#         分组字段列表
# HAVING
#         分组后条件列表
# ORDER BY
#         排序字段列表
# LIMIT
#         分页参数
# 我们今天会将上面的完整语法拆分为以下几个部分学习：
# - 基本查询（不带任何条件）
# - 条件查询（where）
# - 分组查询（group by）
# - 排序查询（order by）
# - 分页查询（limit）

create table emp(
                    id int unsigned primary key auto_increment comment 'ID,主键',
                    username varchar(20) not null unique comment '用户名',
                    password varchar(32) not null comment '密码',
                    name varchar(10) not null comment '姓名',
                    gender tinyint unsigned not null comment '性别, 1:男, 2:女',
                    phone char(11) not null unique comment '手机号',
                    job tinyint unsigned comment '职位, 1:班主任,2:讲师,3:学工主管,4:教研主管,5:咨询师',
                    salary int unsigned comment '薪资',
                    image varchar(300) comment '头像',
                    entry_date date comment '入职日期',
                    create_time datetime comment '创建时间',
                    update_time datetime comment '修改时间'
) comment '员工表';

# TRUNCATE TABLE emp;

-- 准备测试数据
INSERT INTO emp(id, username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time)
VALUES (1,'shinaian','123456','施耐庵',1,'13309090001',4,15000,'1.jpg','2000-01-01','2024-04-11 16:35:33','2024-04-11 16:35:35'),
       (2,'songjiang','123456','宋江',1,'13309090002',2,8600,'2.jpg','2015-01-01','2024-04-11 16:35:33','2024-04-11 16:35:37'),
       (3,'lujunyi','123456','卢俊义',1,'13309090003',2,8900,'3.jpg','2008-05-01','2024-04-11 16:35:33','2024-04-11 16:35:39'),
       (4,'wuyong','123456','吴用',1,'13309090004',2,9200,'4.jpg','2007-01-01','2024-04-11 16:35:33','2024-04-11 16:35:41'),
       (5,'gongsunsheng','123456','公孙胜',1,'13309090005',2,9500,'5.jpg','2012-12-05','2024-04-11 16:35:33','2024-04-11 16:35:43'),
       (6,'huosanniang','123456','扈三娘',2,'13309090006',3,6500,'6.jpg','2013-09-05','2024-04-11 16:35:33','2024-04-11 16:35:45'),
       (7,'chaijin','123456','柴进',1,'13309090007',1,4700,'7.jpg','2005-08-01','2024-04-11 16:35:33','2024-04-11 16:35:47'),
       (8,'likui','123456','李逵',1,'13309090008',1,4800,'8.jpg','2014-11-09','2024-04-11 16:35:33','2024-04-11 16:35:49'),
       (9,'wusong','123456','武松',1,'13309090009',1,4900,'9.jpg','2011-03-11','2024-04-11 16:35:33','2024-04-11 16:35:51'),
       (10,'lichong','123456','林冲',1,'13309090010',1,5000,'10.jpg','2013-09-05','2024-04-11 16:35:33','2024-04-11 16:35:53'),
       (11,'huyanzhuo','123456','呼延灼',1,'13309090011',2,9700,'11.jpg','2007-02-01','2024-04-11 16:35:33','2024-04-11 16:35:55'),
       (12,'xiaoliguang','123456','小李广',1,'13309090012',2,10000,'12.jpg','2008-08-18','2024-04-11 16:35:33','2024-04-11 16:35:57'),
       (13,'yangzhi','123456','杨志',1,'13309090013',1,5300,'13.jpg','2012-11-01','2024-04-11 16:35:33','2024-04-11 16:35:59'),
       (14,'shijin','123456','史进',1,'13309090014',2,10600,'14.jpg','2002-08-01','2024-04-11 16:35:33','2024-04-11 16:36:01'),
       (15,'sunerniang','123456','孙二娘',2,'13309090015',2,10900,'15.jpg','2011-05-01','2024-04-11 16:35:33','2024-04-11 16:36:03'),
       (16,'luzhishen','123456','鲁智深',1,'13309090016',2,9600,'16.jpg','2010-01-01','2024-04-11 16:35:33','2024-04-11 16:36:05'),
       (17,'liying','12345678','李应',1,'13309090017',1,5800,'17.jpg','2015-03-21','2024-04-11 16:35:33','2024-04-11 16:36:07'),
       (18,'shiqian','123456','时迁',1,'13309090018',2,10200,'18.jpg','2015-01-01','2024-04-11 16:35:33','2024-04-11 16:36:09'),
       (19,'gudasao','123456','顾大嫂',2,'13309090019',2,10500,'19.jpg','2008-01-01','2024-04-11 16:35:33','2024-04-11 16:36:11'),
       (20,'ruanxiaoer','123456','阮小二',1,'13309090020',2,10800,'20.jpg','2018-01-01','2024-04-11 16:35:33','2024-04-11 16:36:13'),
       (21,'ruanxiaowu','123456','阮小五',1,'13309090021',5,5200,'21.jpg','2015-01-01','2024-04-11 16:35:33','2024-04-11 16:36:15'),
       (22,'ruanxiaoqi','123456','阮小七',1,'13309090022',5,5500,'22.jpg','2016-01-01','2024-04-11 16:35:33','2024-04-11 16:36:17'),
       (23,'ruanji','123456','阮籍',1,'13309090023',5,5800,'23.jpg','2012-01-01','2024-04-11 16:35:33','2024-04-11 16:36:19'),
       (24,'tongwei','123456','童威',1,'13309090024',5,5000,'24.jpg','2006-01-01','2024-04-11 16:35:33','2024-04-11 16:36:21'),
       (25,'tongmeng','123456','童猛',1,'13309090025',5,4800,'25.jpg','2002-01-01','2024-04-11 16:35:33','2024-04-11 16:36:23'),
       (26,'yanshun','123456','燕顺',1,'13309090026',5,5400,'26.jpg','2011-01-01','2024-04-11 16:35:33','2024-04-11 16:36:25'),
       (27,'lijun','123456','李俊',1,'13309090027',5,6600,'27.jpg','2004-01-01','2024-04-11 16:35:33','2024-04-11 16:36:27'),
       (28,'lizhong','123456','李忠',1,'13309090028',5,5000,'28.jpg','2007-01-01','2024-04-11 16:35:33','2024-04-11 16:36:29'),
       (29,'songqing','123456','宋清',1,'13309090029',5,5100,'29.jpg','2020-01-01','2024-04-11 16:35:33','2024-04-11 16:36:31'),
       (30,'liyun','123456','李云',1,'13309090030',NULL,NULL,'30.jpg','2020-03-01','2024-04-11 16:35:33','2024-04-11 16:36:31');



# - 查询多个字段
# select 字段1, 字段2, 字段3 from  表名;
# - 查询所有字段（通配符）
# select *  from  表名;
# - 设置别名
# select 字段1 [ as 别名1 ] , 字段2 [ as 别名2 ]  from  表名;
# - 去除重复记录
# select distinct 字段列表 from  表名;

--  =================== DQL: 基本查询 ======================
-- 1. 查询指定字段 name,entry_date 并返回
# select name,entry_date from emp;

-- 2. 查询返回所有字段
# select id, username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time from emp;
# select * from emp;

-- 3. 查询所有员工的 name,entry_date, 并起别名(姓名、入职日期)
# select name as 姓名,entry_date as 入职日期 from emp;

-- 4. 查询已有的员工关联了哪几种职位(不要重复)
# select distinct job from emp;


# select  字段列表  from   表名   where   条件列表 ; -- 条件列表：意味着可以有多个条件
--  =================== DQL: 条件查询 ======================
-- 1. 查询 姓名 为 柴进 的员工
select * from emp where name='柴进';

-- 2. 查询 薪资小于等于5000 的员工信息
select * from emp where salary<=5000;

-- 3. 查询 没有分配职位 的员工信息
select * from emp where job is null;

-- 4. 查询 有职位 的员工信息
select * from emp where job is not null;

-- 5. 查询 密码不等于 '123456' 的员工信息
select * from emp where password !='123456';

-- 6. 查询 入职日期 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间的员工信息
# select * from emp where entry_date between '2000-01-01' and '2010-01-01';

-- 7. 查询 入职时间 在 '2000-01-01' (包含) 到 '2010-01-01'(包含) 之间 且 性别为女 的员工信息
# select * from emp where (entry_date between '2000-01-01' and '2010-01-01') and gender=2;


-- 8. 查询 职位是 2 (讲师), 3 (学工主管), 4 (教研主管) 的员工信息
select * from emp where job in (2,3,4);

select * from emp where job=2 or job=3 or job=4;

# like 模糊匹配(_匹配单个字符, %匹配任意个字符)
-- 9. 查询 姓名 为两个字的员工信息
select * from emp where name like '__';

-- 10. 查询 姓 '李' 的员工信息
select * from emp where name like '李%';

-- 11. 查询 姓名中包含 '二' 的员工信息
select * from emp where name like '%二%';


--  =================== DQL: 分组查询 ======================
# -- 聚合函数
# - count ：按照列去统计有多少行数据。
#   - 在根据指定的列统计的时候，如果这一列中有null的行，该行不会被统计在其中。
# - sum ：计算指定列的数值和，如果不是数值类型，那么计算结果为0
# - max ：计算指定列的最大值
# - min ：计算指定列的最小值
# - avg ：计算指定列的平均值

# 聚合函数对null过滤:聚合函数会忽略空值，对NULL值不作为统计
-- 1. 统计该企业员工数量
# 推荐*
select count(*) from emp;
# 其次是count常量
select count(1) from emp;
# 效率最低count字段
select count(job) from emp;
-- 2. 统计该企业员工的平均薪资
select avg(emp.salary) from emp;

-- 3. 统计该企业员工的最低薪资
select min(salary) from emp;

-- 4. 统计该企业员工的最高薪资
select max(emp.salary) from emp;

-- 5. 统计该企业每月要给员工发放的薪资总额(薪资之和)
select sum(emp.salary) from emp;




-- 分组
# 分组之后selet后面就只能写分组字段+聚合语句
-- 1. 根据性别分组 , 统计男性和女性员工的数量
select gender,count(*) from emp group by gender;

-- 2. 先查询入职时间在 '2015-01-01' (包含) 以前的员工 , 并对结果根据职位分组 , 获取员工数量大于等于2的职位
# select job,count(*) from emp where entry_date <='2015-01-01' group by job having count(*)>=2;

# DQL语句中where与having的区别?
# 执行时机不同(where ->group by->having)：先会过滤掉不符合where的信息然后再将根据经过where筛选后的信息按照分组条件进行分组，最后再根据需要对聚合函数的字段用having再筛选一遍
# 判断条件不同(having后可以用聚合函数，where不可以)

--  =================== 排序查询 ======================
-- 1. 根据入职时间, 对员工进行升序排序:ase（默认）
# select * from emp order by entry_date asc ;

-- 2. 根据入职时间, 对员工进行降序排序:desc
# select * from emp order by entry_date desc ;

-- 3. 根据 入职时间 对公司的员工进行 升序排序 ， 入职时间相同 , 再按照 更新时间 进行降序排序
# select * from emp order by entry_date asc,update_time desc ;


--  =================== 分页查询 ======================
-- 1. 从起始索引0开始查询员工数据, 每页展示5条记录
select * from emp limit 0,5;
select * from emp limit 5;
-- 2. 查询 第1页 员工数据, 每页展示5条记录
select * from emp limit 0,5;

-- 3. 查询 第2页 员工数据, 每页展示5条记录
select * from emp limit 5,5;

-- 4. 查询 第3页 员工数据, 每页展示5条记录
select * from emp limit 10,5;

# 公式：
# 分页查询开始索引（从0开始）=（页数-1）*每页展示信息数量

