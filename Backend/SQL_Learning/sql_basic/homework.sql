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


-- 准备测试数据
INSERT INTO emp(id, username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time)
VALUES (1,'shinaian','123456','施耐庵',1,'13309090001',4,15000,'1.jpg','2000-01-01','2023-10-27 16:35:33','2023-10-27 16:35:35'),
       (2,'songjiang','123456','宋江',1,'13309090002',2,8600,'2.jpg','2015-01-01','2023-10-27 16:35:33','2023-10-27 16:35:37'),
       (3,'lujunyi','123456','卢俊义',1,'13309090003',2,8900,'3.jpg','2008-05-01','2023-10-27 16:35:33','2023-10-27 16:35:39'),
       (4,'wuyong','123456','吴用',1,'13309090004',2,9200,'4.jpg','2007-01-01','2023-10-27 16:35:33','2023-10-27 16:35:41'),
       (5,'gongsunsheng','123456','公孙胜',1,'13309090005',2,9500,'5.jpg','2012-12-05','2023-10-27 16:35:33','2023-10-27 16:35:43'),
       (6,'huosanniang','123456','扈三娘',2,'13309090006',3,6500,'6.jpg','2013-09-05','2023-10-27 16:35:33','2023-10-27 16:35:45'),
       (7,'chaijin','123456','柴进',1,'13309090007',1,4700,'7.jpg','2005-08-01','2023-10-27 16:35:33','2023-10-27 16:35:47'),
       (8,'likui','123456','李逵',1,'13309090008',1,4800,'8.jpg','2014-11-09','2023-10-27 16:35:33','2023-10-27 16:35:49'),
       (9,'wusong','123456','武松',1,'13309090009',1,4900,'9.jpg','2011-03-11','2023-10-27 16:35:33','2023-10-27 16:35:51'),
       (10,'lichong','123456','林冲',1,'13309090010',1,5000,'10.jpg','2013-09-05','2023-10-27 16:35:33','2023-10-27 16:35:53'),
       (11,'huyanzhuo','123456','呼延灼',1,'13309090011',2,9700,'11.jpg','2007-02-01','2023-10-27 16:35:33','2023-10-27 16:35:55'),
       (12,'xiaoliguang','123456','小李广',1,'13309090012',2,10000,'12.jpg','2008-08-18','2023-10-27 16:35:33','2023-10-27 16:35:57'),
       (13,'yangzhi','123456','杨志',1,'13309090013',1,5300,'13.jpg','2012-11-01','2023-10-27 16:35:33','2023-10-27 16:35:59'),
       (14,'shijin','123456','史进',1,'13309090014',2,10600,'14.jpg','2002-08-01','2023-10-27 16:35:33','2023-10-27 16:36:01'),
       (15,'sunerniang','123456','孙二娘',2,'13309090015',2,10900,'15.jpg','2011-05-01','2023-10-27 16:35:33','2023-10-27 16:36:03'),
       (16,'luzhishen','123456','鲁智深',1,'13309090016',2,9600,'16.jpg','2010-01-01','2023-10-27 16:35:33','2023-10-27 16:36:05'),
       (17,'liying','12345678','李应',1,'13309090017',1,5800,'17.jpg','2015-03-21','2023-10-27 16:35:33','2023-10-27 16:36:07'),
       (18,'shiqian','123456','时迁',1,'13309090018',2,10200,'18.jpg','2015-01-01','2023-10-27 16:35:33','2023-10-27 16:36:09'),
       (19,'gudasao','123456','顾大嫂',2,'13309090019',2,10500,'19.jpg','2008-01-01','2023-10-27 16:35:33','2023-10-27 16:36:11'),
       (20,'ruanxiaoer','123456','阮小二',1,'13309090020',2,10800,'20.jpg','2018-01-01','2023-10-27 16:35:33','2023-10-27 16:36:13'),
       (21,'ruanxiaowu','123456','阮小五',1,'13309090021',5,5200,'21.jpg','2015-01-01','2023-10-27 16:35:33','2023-10-27 16:36:15'),
       (22,'ruanxiaoqi','123456','阮小七',1,'13309090022',5,5500,'22.jpg','2016-01-01','2023-10-27 16:35:33','2023-10-27 16:36:17'),
       (23,'ruanji','123456','阮籍',1,'13309090023',5,5800,'23.jpg','2012-01-01','2023-10-27 16:35:33','2023-10-27 16:36:19'),
       (24,'tongwei','123456','童威',1,'13309090024',5,5000,'24.jpg','2006-01-01','2023-10-27 16:35:33','2023-10-27 16:36:21'),
       (25,'tongmeng','123456','童猛',1,'13309090025',5,4800,'25.jpg','2002-01-01','2023-10-27 16:35:33','2023-10-27 16:36:23'),
       (26,'yanshun','123456','燕顺',1,'13309090026',5,5400,'26.jpg','2011-01-01','2023-10-27 16:35:33','2023-10-27 16:36:25'),
       (27,'lijun','123456','李俊',1,'13309090027',5,6600,'27.jpg','2004-01-01','2023-10-27 16:35:33','2023-10-27 16:36:27'),
       (28,'lizhong','123456','李忠',1,'13309090028',5,5000,'28.jpg','2007-01-01','2023-10-27 16:35:33','2023-10-27 16:36:29'),
       (29,'songqing','123456','宋清',1,'13309090029',5,5100,'29.jpg','2020-01-01','2023-10-27 16:35:33','2023-10-27 16:36:31'),
       (30,'liyun','123456','李云',1,'13309090030',NULL,NULL,'30.jpg','2020-03-01','2023-10-27 16:35:33','2023-10-27 16:36:31');

-- 1. 往emp表中插入一条测试数据， 全部的字段都需要插入值 。
insert into emp(id, username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time)
values (null,'kongming','12345678','孔明',1,'13309090031',3,9000,'31.jpg','2026-5-11',now(),now());

-- 2. 批量往emp表中插入两条测试数据， 全部的字段都需要插入值 。
insert into emp(id, username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time)
values (null,'xueyong','12345678','薛永',1,'13309090032',3,9100,'32.jpg','2026-5-11',now(),now()),
       (null,'dengfei','12345678','邓飞',1,'13309090033',3,9110,'33.jpg','2026-5-11',now(),now());

-- 3. 为id为2,4,6,8,9这个几个员工，涨100块钱工资 （在当前薪资的基础上 + 100）。
update emp set salary=salary+100 where id in (2,4,6,8,9);

-- 4. 删除id为30,31的员工信息
delete from emp where id=30 or id=31;

-- 5. 将id为6，7，9，12这个几个员工的 入职日期修改为 '2018-09-10' , 密码修改为 '1234567'
update emp set entry_date='2018-09-10',password='1234567' where id in (6,7,9,12);




-- 1. 查询指定字段 name,entry_date 并设置别名为 姓名、入职日期 并返回
select name as 姓名,entry_date as 入职日期 from emp;

-- 2. 查询返回所有字段 （两种写法哦）
select * from emp;
select id, username, password, name, gender, phone, job, salary, image, entry_date, create_time, update_time from emp;

-- 3. 查询性别为女 且 有职位的员工，要求查询出 name,entry_date 并起别名 (姓名、入职日期)
select name as 姓名,entry_date as 入职日期 from emp where gender=2 and job is not null ;

-- 4. 查询已有的员工关联了 哪几种 职位(不要重复)
select  job from emp group by job;

-- 5. 查询 姓名 为 '童威' 或 入职时间在 '2000-01-01' 到 '2010-01-01' 的员工
select * from emp where name='童威' or (entry_date between '2000-01-01' and '2010-01-01');

-- 6. 查询在 薪资小于等于5000 的员工信息
select * from emp where salary<=5000;

-- 7. 查询 没有分配职位 的员工信息
select * from emp where job is null;

-- 8. 查询 有职位 的员工信息
select * from emp where job is not null;


-- 9. 查询 密码不等于 '123456' 或 没有职位的员工信息
select * from emp where password!='123456' or job is null;

-- 10. 查询入职日期 在 '2000-01-01' (包含) 到 '2015-01-01'(包含) 之间的员工信息
select * from emp where entry_date>='2000-01-01' and entry_date<='2015-01-01';

-- 11. 查询 入职时间 在 '2000-01-01' (包含) 到 '2015-01-01'(包含) 之间 且 性别为女 的员工信息
select * from emp where entry_date>='2000-01-01' and entry_date<='2015-01-01 ' and  gender=2;


-- 12. 查询 职位是 2 (讲师) 或 3 (学工主管) 或 4 (教研主管) 的员工信息（两种写法实现）
select * from emp where job in (2,3,4);
select * from emp where job=2 or job=3 or job=4;
-- 13. 查询姓名为两个字 且 性别为男 且 薪资高于5000 的员工信息
select * from emp where name like '__' and gender=1 and salary>5000;

-- 14. 查询姓名中包含 '小' 的员工信息  且  入职时间在 '2008-01-01' 之后入职的员工
select * from emp where name like '%小%' and entry_date>'2008-01-01';

-- 15. 查询出 性别为 男 , 或 入职时间在 '2010-01-01' 之后入职的员工
select * from emp where gender=1 and entry_date>'2010-01-01';

-- 16. 查询有职位的， 且 姓名为两个字 的员工信息
select * from emp where job is not null and name like '__';

-- 17. 查询所有的性别为男(gender 为 1)的 讲师 (job 为 2) 的员工信息
select * from emp where gender=1 and job=2;

-- 18. 查询薪资小于 8000 或 入职时间在 2010-10-09 之后入职的员工信息
select * from emp where salary<8000 or entry_date>'2010-10-09';

-- 19. 根据性别分组 , 统计男性和女性员工的数量
select emp.gender,count(*) from emp group by gender;

-- 22. 先查询入职时间在 '2015-01-01' (包含) 以前的员工 , 并对结果根据职位分组 , 获取员工数量大于等于2的职位
select job,count(*) from emp where entry_date<'2015-01-01' group by job having count(*)>=2;


# ------------------------------------------------------------------------------------------------
# 作业3: 表结构设计
# 需求: 根据 资料/页面原型/tlias智能学习辅助系统 , 详细阅读页面原型及需求，完成 部门管理、班级管理  的表结构设计  。
# 涉及到的表有两张 , 部门表 dept 、班级表 clazz 。
# 要求/提示:
# - 设置班级表 clazz 时，不用考虑班主任这个字段（忽略）
# - 提交时，提交建表的SQL语句

create table clazz(
                      id int unsigned primary key auto_increment comment '学生id',
                      class_name varchar(30) not null unique comment '班级名称',
                      classroom varchar(20) comment '班级教室',
                      class_starttime date not null comment '开课时间',
                      class_finishtime date not null comment '结课时间',
                      subject varchar(255) not null comment '学科',
                      class_state tinyint unsigned not null comment '状态,1:未开班,2:在读',
                      createtable_time datetime comment '创建表时间',
                      updatetable_time datetime comment '更改表时间'
)comment '班级表';

create table dept(
                     id int unsigned primary key auto_increment comment '序号',
                     dept_name varchar(10) not null unique comment '部门名称',
                     create_time datetime comment '创建表时间',
                     update_time datetime not null comment '最后操作时间'
)comment '部门表';

