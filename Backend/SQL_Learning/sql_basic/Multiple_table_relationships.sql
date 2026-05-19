CREATE TABLE dept (
                      id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
                      name varchar(10) NOT NULL UNIQUE COMMENT '部门名称',
                      create_time datetime DEFAULT NULL COMMENT '创建时间',
                      update_time datetime DEFAULT NULL COMMENT '修改时间'
) COMMENT '部门表';

INSERT INTO dept VALUES (1,'学工部','2023-09-25 09:47:40','2023-09-25 09:47:40'),
                        (2,'教研部','2023-09-25 09:47:40','2023-10-09 15:17:04'),
                        (3,'咨询部2','2023-09-25 09:47:40','2023-11-30 21:26:24'),
                        (4,'就业部','2023-09-25 09:47:40','2023-09-25 09:47:40'),
                        (5,'人事部','2023-09-25 09:47:40','2023-09-25 09:47:40'),
                        (15,'行政部','2023-11-30 20:56:37','2023-11-30 20:56:37');




create table emp(
                    id int unsigned primary key auto_increment comment 'ID,主键',
                    username varchar(20) not null unique comment '用户名',
                    password varchar(32) default '123456' comment '密码',
                    name varchar(10) not null comment '姓名',
                    gender tinyint unsigned not null comment '性别, 1:男, 2:女',
                    phone char(11) not null unique comment '手机号',
                    job tinyint unsigned comment '职位, 1 班主任, 2 讲师 , 3 学工主管, 4 教研主管, 5 咨询师',
                    salary int unsigned comment '薪资',
                    image varchar(255) comment '头像',
                    entry_date date comment '入职日期',
                    dept_id int unsigned comment '部门ID',
                    create_time datetime comment '创建时间',
                    update_time datetime comment '修改时间'
) comment '员工表';


INSERT INTO emp VALUES
                    (1,'shinaian','123456','施耐庵',1,'13309090001',4,15000,'5.png','2000-01-01',2,'2023-10-20 16:35:33','2023-11-16 16:11:26'),
                    (2,'songjiang','123456','宋江',1,'13309090002',2,8600,'01.png','2015-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:37'),
                    (3,'lujunyi','123456','卢俊义',1,'13309090003',2,8900,'01.png','2008-05-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:39'),
                    (4,'wuyong','123456','吴用',1,'13309090004',2,9200,'01.png','2007-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:41'),
                    (5,'gongsunsheng','123456','公孙胜',1,'13309090005',2,9500,'01.png','2012-12-05',2,'2023-10-20 16:35:33','2023-10-20 16:35:43'),
                    (6,'huosanniang','123456','扈三娘',2,'13309090006',3,6500,'01.png','2013-09-05',1,'2023-10-20 16:35:33','2023-10-20 16:35:45'),
                    (7,'chaijin','123456','柴进',1,'13309090007',1,4700,'01.png','2005-08-01',1,'2023-10-20 16:35:33','2023-10-20 16:35:47'),
                    (8,'likui','123456','李逵',1,'13309090008',1,4800,'01.png','2014-11-09',1,'2023-10-20 16:35:33','2023-10-20 16:35:49'),
                    (9,'wusong','123456','武松',1,'13309090009',1,4900,'01.png','2011-03-11',1,'2023-10-20 16:35:33','2023-10-20 16:35:51'),
                    (10,'linchong','123456','林冲',1,'13309090010',1,5000,'01.png','2013-09-05',1,'2023-10-20 16:35:33','2023-10-20 16:35:53'),
                    (11,'huyanzhuo','123456','呼延灼',1,'13309090011',2,9700,'01.png','2007-02-01',2,'2023-10-20 16:35:33','2023-10-20 16:35:55'),
                    (12,'xiaoliguang','123456','小李广',1,'13309090012',2,10000,'01.png','2008-08-18',2,'2023-10-20 16:35:33','2023-10-20 16:35:57'),
                    (13,'yangzhi','123456','杨志',1,'13309090013',1,5300,'01.png','2012-11-01',1,'2023-10-20 16:35:33','2023-10-20 16:35:59'),
                    (14,'shijin','123456','史进',1,'13309090014',2,10600,'01.png','2002-08-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:01'),
                    (15,'sunerniang','123456','孙二娘',2,'13309090015',2,10900,'01.png','2011-05-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:03'),
                    (16,'luzhishen','123456','鲁智深',1,'13309090016',2,9600,'01.png','2010-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:05'),
                    (17,'liying','12345678','李应',1,'13309090017',1,5800,'01.png','2015-03-21',1,'2023-10-20 16:35:33','2023-10-20 16:36:07'),
                    (18,'shiqian','123456','时迁',1,'13309090018',2,10200,'01.png','2015-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:09'),
                    (19,'gudasao','123456','顾大嫂',2,'13309090019',2,10500,'01.png','2008-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:11'),
                    (20,'ruanxiaoer','123456','阮小二',1,'13309090020',2,10800,'01.png','2018-01-01',2,'2023-10-20 16:35:33','2023-10-20 16:36:13'),
                    (21,'ruanxiaowu','123456','阮小五',1,'13309090021',5,5200,'01.png','2015-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:15'),
                    (22,'ruanxiaoqi','123456','阮小七',1,'13309090022',5,5500,'01.png','2016-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:17'),
                    (23,'ruanji','123456','阮籍',1,'13309090023',5,5800,'01.png','2012-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:19'),
                    (24,'tongwei','123456','童威',1,'13309090024',5,5000,'01.png','2006-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:21'),
                    (25,'tongmeng','123456','童猛',1,'13309090025',5,4800,'01.png','2002-01-01',3,'2023-10-20 16:35:33','2023-10-20 16:36:23'),
                    (26,'yanshun','123456','燕顺',1,'13309090026',5,5400,'01.png','2011-01-01',3,'2023-10-20 16:35:33','2023-11-08 22:12:46'),
                    (27,'lijun','123456','李俊',1,'13309090027',2,6600,'8.png','2004-01-01',2,'2023-10-20 16:35:33','2023-11-16 17:56:59'),
                    (28,'lizhong','123456','李忠',1,'13309090028',5,5000,'6.png','2007-01-01',3,'2023-10-20 16:35:33','2023-11-17 16:34:22'),
                    (30,'liyun','123456','李云',1,'13309090030',NULL,NULL,'01.png','2020-03-01',NULL,'2023-10-20 16:35:33','2023-10-20 16:36:31'),
                    (36,'linghuchong','123456','令狐冲',1,'18809091212',2,6800,'1.png','2023-10-19',2,'2023-10-20 20:44:54','2023-11-09 09:41:04');


-- 多表关系

# 1对多(1->n)
-- 场景：部门与员工的关系（一个部门下有多个员工）dept->emp
-- 问题：一对多的表关系，在数据库层面该如何实现 ？
-- 在数据库表中多的一方，添加字段，来关联一的一方的主键 。
-- 外键约束：让两张表的数据建立连接，保证数据的一致性和完整性。
-- 对应的关键字：foreign key

-- 创建表时指定
-- create table 表名(
--         字段名    数据类型,
--         ...
--         [constraint]   [外键名称]  foreign  key (外键字段名)   references   主表 (主表列名)
-- );


-- 建完表后，添加外键
-- alter table  表名  add constraint  外键名称  foreign key(外键字段名) references 主表(主表列名);

-- alter table emp add constraint fk_emp_id foreign key (dept_id) references dept(id);--物理外键
#     - 物理外键
#   - 概念：使用foreign key定义外键关联另外一张表。
#   - 缺点：
#     - 影响增、删、改的效率（需要检查外键关系）。
#     - 仅用于单节点数据库，不适用于分布式、集群场景。
#     - 容易引发数据库的死锁问题，消耗性能。
#
# - 逻辑外键
#   - 概念：在业务层逻辑中，解决外键关联。
#   - 通过逻辑外键，就可以很方便的解决上述问题
-- 但是这种物理外键已经被禁用了；在现在的企业开发中，很少会使用物理外键，都是使用逻辑外键。 甚至在一些数据库开发规范中，会明确指出禁止使用物理外键 foreign key

-- -----------------------------------------------------------------------------------------------------------------------------

# 一对一的应用场景： 用户表(基本信息+身份信息)
-- 用户基本信息表
create table tb_user(
                        id int unsigned  primary key auto_increment comment 'ID',
                        name varchar(10) not null comment '姓名',
                        gender tinyint unsigned not null comment '性别, 1 男  2 女',
                        phone char(11) comment '手机号',
                        degree varchar(10) comment '学历'
) comment '用户基本信息表';
-- 测试数据
insert into tb_user values (1,'白眉鹰王',1,'18812340001','初中'),
                           (2,'青翼蝠王',1,'18812340002','大专'),
                           (3,'金毛狮王',1,'18812340003','初中'),
                           (4,'紫衫龙王',2,'18812340004','硕士');

-- 用户身份信息表
create table tb_user_card(
                             id int unsigned  primary key auto_increment comment 'ID',
                             nationality varchar(10) not null comment '民族',
                             birthday date not null comment '生日',
                             idcard char(18) not null comment '身份证号',
                             issued varchar(20) not null comment '签发机关',
                             expire_begin date not null comment '有效期限-开始',
                             expire_end date comment '有效期限-结束',
                             user_id int unsigned not null unique comment '用户ID',
                             constraint fk_user_id foreign key (user_id) references tb_user(id)
) comment '用户身份信息表';
-- 测试数据
insert into tb_user_card values (1,'汉','1960-11-06','100000100000100001','朝阳区公安局','2000-06-10',null,1),
                                (2,'汉','1971-11-06','100000100000100002','静安区公安局','2005-06-10','2025-06-10',2),
                                (3,'汉','1963-11-06','100000100000100003','昌平区公安局','2006-06-10',null,3),
                                (4,'回','1980-11-06','100000100000100004','海淀区公安局','2008-06-10','2028-06-10',4);

# 一对一 ：在任意一方加入外键，关联另外一方的主键，并且设置外键为唯一的(UNIQUE)
#     user_id int unsigned not null unique comment '用户ID',
#     constraint fk_user_id foreign key (user_id) references tb_user(id)

-- --------------------------------------------------------------------------------------------------------------------

# 多对多
# 案例：学生与课程的关系
# - 关系：一个学生可以选修多门课程，一门课程也可以供多个学生选择
# - 实现关系：建立第三张中间表，中间表至少包含两个外键，分别关联两方主键

-- 学生表
create table tb_student(
                           id int auto_increment primary key comment '主键ID',
                           name varchar(10) comment '姓名',
                           no varchar(10) comment '学号'
) comment '学生表';
-- 学生表测试数据
insert into tb_student(name, no) values ('黛绮丝', '2000100101'),
                                        ('谢逊', '2000100102'),
                                        ('殷天正', '2000100103'),
                                        ('韦一笑', '2000100104');

-- 课程表
create table tb_course(
                          id int auto_increment primary key comment '主键ID',
                          name varchar(10) comment '课程名称'
) comment '课程表';
-- 课程表测试数据
insert into tb_course (name) values ('Java'), ('PHP'), ('MySQL') , ('Hadoop');

-- 学生课程表（中间表）
create table tb_student_course(
                                  id int auto_increment comment '主键' primary key,
                                  student_id int not null comment '学生ID',
                                  course_id  int not null comment '课程ID',
                                  constraint fk_courseid foreign key (course_id) references tb_course (id),
                                  constraint fk_studentid foreign key (student_id) references tb_student (id)
)comment '学生课程中间表';

-- 学生课程表测试数据
insert into tb_student_course(student_id, course_id) values (1,1),(1,2),(1,3),(2,2),(2,3),(3,4);

# 多对多 ：需要建立一张中间表，中间表中有两个外键字段，分别关联两方的主键。如：学生课程表（中间表）

-- -------------------------------------------------------------------------------------------------------------------

# 案例：
-- 部门表
create table dept (
                      id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
                      name varchar(10) NOT NULL UNIQUE COMMENT '部门名称',
                      create_time datetime COMMENT '创建时间',
                      update_time datetime COMMENT '修改时间'
) COMMENT '部门表';

-- 员工表
create table emp(
                    id int unsigned primary key auto_increment comment 'ID,主键',
                    username varchar(20) not null unique comment '用户名',
                    password varchar(50) default '123456' comment '密码',
                    name varchar(10) not null comment '姓名',
                    gender tinyint unsigned not null comment '性别, 1:男, 2:女',
                    phone char(11) not null unique comment '手机号',
                    job tinyint unsigned comment '职位, 1 班主任, 2 讲师 , 3 学工主管, 4 教研主管, 5 咨询师',
                    salary int unsigned comment '薪资',
                    image varchar(300) comment '头像',
                    entry_date date comment '入职日期',
                    dept_id int unsigned comment '部门ID',  -- 关联的是dept部门表的ID
                    create_time datetime comment '创建时间',
                    update_time datetime comment '修改时间'
) comment '员工表';

-- 员工工作经历表
create table emp_expr(
                         id int unsigned primary key auto_increment comment 'ID, 主键',
                         emp_id  int unsigned null comment '员工ID', -- 关联的是emp员工表的ID
                         begin  date null comment '开始时间',
                         end date null comment '结束时间',
                         company varchar(50) null comment '公司名称',
                         job varchar(50) null comment '职位'
) comment '工作经历';


-- -------------------------------------------------------------------------------------------------------------------

# 多表查询
-- 部门管理
create table dept(
                     id int unsigned primary key auto_increment comment 'ID, 主键',
                     name varchar(10) not null unique comment '部门名称',
                     create_time datetime comment '创建时间',
                     update_time datetime comment '修改时间'
) comment '部门表' ;

insert into dept (id, name, create_time, update_time) values
                                                          (1,'学工部',now(),now()),
                                                          (2,'教研部',now(),now()),
                                                          (3,'咨询部',now(),now()),
                                                          (4,'就业部',now(),now()),
                                                          (5,'人事部',now(),now());


-- 员工管理
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
                    dept_id int unsigned COMMENT '关联的部门ID',
                    create_time datetime comment '创建时间',
                    update_time datetime comment '修改时间'
) comment '员工表';


-- 准备测试数据
INSERT INTO `emp` VALUES (1,'shinaian','123456','施耐庵',1,'13309090001',4,15000,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2000-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:35:35'),
                         (2,'songjiang','123456','宋江',1,'13309090002',2,8600,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2015-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:35:37'),
                         (3,'lujunyi','123456','卢俊义',1,'13309090003',2,8900,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2008-05-01',2,'2023-10-27 16:35:33','2023-10-27 16:35:39'),
                         (4,'wuyong','123456','吴用',1,'13309090004',2,9200,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2007-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:35:41'),
                         (5,'gongsunsheng','123456','公孙胜',1,'13309090005',2,9500,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2012-12-05',2,'2023-10-27 16:35:33','2023-10-27 16:35:43'),
                         (6,'huosanniang','123456','扈三娘',2,'13309090006',3,6500,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2013-09-05',1,'2023-10-27 16:35:33','2023-10-27 16:35:45'),
                         (7,'chaijin','123456','柴进',1,'13309090007',1,4700,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2005-08-01',1,'2023-10-27 16:35:33','2023-10-27 16:35:47'),
                         (8,'likui','123456','李逵',1,'13309090008',1,4800,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2014-11-09',1,'2023-10-27 16:35:33','2023-10-27 16:35:49'),
                         (9,'wusong','123456','武松',1,'13309090009',1,4900,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2011-03-11',1,'2023-10-27 16:35:33','2023-10-27 16:35:51'),
                         (10,'lichong','123456','林冲',1,'13309090010',1,5000,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2013-09-05',1,'2023-10-27 16:35:33','2023-10-27 16:35:53'),
                         (11,'huyanzhuo','123456','呼延灼',1,'13309090011',2,9700,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2007-02-01',2,'2023-10-27 16:35:33','2023-10-27 16:35:55'),
                         (12,'xiaoliguang','123456','小李广',1,'13309090012',2,10000,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2008-08-18',2,'2023-10-27 16:35:33','2023-10-27 16:35:57'),
                         (13,'yangzhi','123456','杨志',1,'13309090013',1,5300,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2012-11-01',1,'2023-10-27 16:35:33','2023-10-27 16:35:59'),
                         (14,'shijin','123456','史进',1,'13309090014',2,10600,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2002-08-01',2,'2023-10-27 16:35:33','2023-10-27 16:36:01'),
                         (15,'sunerniang','123456','孙二娘',2,'13309090015',2,10900,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2011-05-01',2,'2023-10-27 16:35:33','2023-10-27 16:36:03'),
                         (16,'luzhishen','123456','鲁智深',1,'13309090016',2,9600,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2010-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:36:05'),
                         (17,'liying','12345678','李应',1,'13309090017',1,5800,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2015-03-21',1,'2023-10-27 16:35:33','2023-10-27 16:36:07'),
                         (18,'shiqian','123456','时迁',1,'13309090018',2,10200,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2015-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:36:09'),
                         (19,'gudasao','123456','顾大嫂',2,'13309090019',2,10500,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2008-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:36:11'),
                         (20,'ruanxiaoer','123456','阮小二',1,'13309090020',2,10800,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2018-01-01',2,'2023-10-27 16:35:33','2023-10-27 16:36:13'),
                         (21,'ruanxiaowu','123456','阮小五',1,'13309090021',5,5200,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2015-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:15'),
                         (22,'ruanxiaoqi','123456','阮小七',1,'13309090022',5,5500,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2016-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:17'),
                         (23,'ruanji','123456','阮籍',1,'13309090023',5,5800,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2012-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:19'),
                         (24,'tongwei','123456','童威',1,'13309090024',5,5000,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2006-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:21'),
                         (25,'tongmeng','123456','童猛',1,'13309090025',5,4800,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2002-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:23'),
                         (26,'yanshun','123456','燕顺',1,'13309090026',5,5400,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2011-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:25'),
                         (27,'lijun','123456','李俊',1,'13309090027',5,6600,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2004-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:27'),
                         (28,'lizhong','123456','李忠',1,'13309090028',5,5000,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2007-01-01',3,'2023-10-27 16:35:33','2023-10-27 16:36:29'),
                         (29,'songqing','123456','宋清',1,'13309090029',NULL,5100,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2020-01-01',NULL,'2023-10-27 16:35:33','2023-10-27 16:36:31'),
                         (30,'liyun','123456','李云',1,'13309090030',NULL,NULL,'https://dawn-itcast.oss-cn-hangzhou.aliyuncs.com/01.png','2020-03-01',NULL,'2023-10-27 16:35:33','2023-10-27 16:36:31');

# 多表查询：查询时从多张表中获取所需数据
# 单表查询的SQL语句：select  字段列表  from  表名;
# 那么要执行多表查询，只需要使用逗号分隔多张表即可，如： select   字段列表  from  表1, 表2;
select * from  emp , dept;
# 此时，我们看到查询结果中包含了大量的结果集，总共180条记录，而这其实就是员工表所有的记录(30行)与部门表所有记录(6行)的所有组合情况，这种现象称之为笛卡尔积。
# 笛卡尔积：笛卡尔乘积是指在数学中，两个集合(A集合和B集合)的所有组合情况。
# 在SQL语句中，如何去除无效的笛卡尔积呢？只需要给多表查询加上连接查询的条件即可。
select * from emp , dept where emp.dept_id = dept.id ;

# 多表查询可以分为：
# 1. 连接查询
#   - 内连接：相当于查询A、B交集部分数据
#   - 外连接
#     - 左外连接：查询左表所有数据(包括两张表交集部分数据)
#     - 右外连接：查询右表所有数据(包括两张表交集部分数据)
# 2. 子查询

# 内连接查询：查询两表或多表中交集部分数据。
# 内连接从语法上可以分为：
# - 隐式内连接
# - 显式内连接
#
# 隐式内连接语法（常见）：
# select  字段列表   from   表1 , 表2   where  条件 ... ;
# 显式内连接语法：
# select  字段列表   from   表1  [ inner ]  join 表2  on  连接条件 ... ;

-- ============================= 内连接 ==========================
-- A. 查询所有员工的ID, 姓名 , 及所属的部门名称 (隐式、显式内连接实现)
select emp.id,emp.name,dept.name from dept,emp where dept.id=emp.dept_id;

select emp.id,emp.name,dept.name from dept inner join emp on dept.id=emp.dept_id;

select emp.id,emp.name,dept.name from dept  join emp on dept.id=emp.dept_id;


-- B. 查询 性别为男, 且工资 高于8000 的员工的ID, 姓名, 及所属的部门名称 (隐式、显式内连接实现)
select emp.id,emp.name,dept.name from dept,emp where dept.id=emp.dept_id and gender=1 and emp.salary>8000;

select emp.id,emp.name,dept.name from dept  join emp on dept.id=emp.dept_id where salary>8000 and gender=1;

# 给表起别名简化书写：
# select  字段列表 from 表1 as 别名1 , 表2 as  别名2  where  条件 ... ;
#
# select  字段列表 from 表1 别名1 , 表2  别名2  where  条件 ... ;  -- as 可以省略
# 使用了别名的多表查询：
# select e.id, e.name, d.name from emp as e , dept as d where e.dept_id = d.id and e.gender = 1 and e.salary > 8000;
# 注意事项: 一旦为表起了别名，就不能再使用表名来指定对应的字段了，此时只能够使用别名来指定字段。
-- ------------------------------------------------------------------------------------------------------------------
# 外连接分为两种：左外连接 和 右外连接。
# 左外连接语法：
# select  字段列表   from   表1  left  [ outer ]  join 表2  on  连接条件 ... ;
# 左外连接相当于查询表1(左表)的所有数据，当然也包含表1和表2交集部分的数据。
#
# 右外连接语法：
# select  字段列表   from   表1  right  [ outer ]  join 表2  on  连接条件 ... ;
# 右外连接相当于查询表2(右表)的所有数据，当然也包含表1和表2交集部分的数据。

-- =============================== 外连接 ============================
-- A. 查询员工表 所有 员工的姓名, 和对应的部门名称 (左外连接)
select e.name,d.name from emp e left join dept d on e.dept_id = d.id ;

-- B. 查询部门表 所有 部门的名称, 和对应的员工名称 (右外连接)
select d.name,e.name from emp e right join dept d on e.dept_id = d.id ;

-- C. 查询工资 高于8000 的 所有员工的姓名, 和对应的部门名称 (左外连接)
select e.name,d.name from emp e left join dept d on e.dept_id = d.id  where e.salary>8000 ;

# 注意事项：
# 左外连接和右外连接是可以相互替换的，只需要调整连接查询时SQL语句中表的先后顺序就可以了。而我们在日常开发使用时，更偏向于左外连接。
-- ---------------------------------------------------------------------------------------------------------------

# SQL语句中嵌套select语句，称为嵌套查询，又称子查询。
# SELECT  *  FROM   t1   WHERE  column1 =  ( SELECT  column1  FROM  t2 ... );
# 子查询外部的语句可以是insert / update / delete / select 的任何一个，最常见的是 select。
#
# 根据子查询结果的不同分为：
# 1. 标量子查询（子查询结果为单个值 [一行一列]）
# 2. 列子查询（子查询结果为一列，但可以是多行）
# 3. 行子查询（子查询结果为一行，但可以是多列）
# 4. 表子查询（子查询结果为多行多列[相当于子查询结果是一张表]）
#
# 子查询可以书写的位置：
# 1. where之后
# 2. from之后
# 3. select之后
# 子查询的要点是，先对需求做拆分，明确具体的步骤，然后再逐条编写SQL语句。 最终将多条SQL语句合并为一条。

-- ========================= 子查询 ================================
-- 标量子查询
-- A. 查询 最早入职 的员工信息
-- 最早入职的时间
select min(entry_date) from emp;
-- 对应该时间的员工信息
select * from emp where entry_date='2000-01-01';
-- 结合两个语句
select * from emp where entry_date=(select min(entry_date) from emp);

-- B. 查询在 "阮小五" 入职之后入职的员工信息
select emp.entry_date from emp where name='阮小五';
select * from emp where entry_date>'2015-01-01';
select * from emp where entry_date>(select emp.entry_date from emp where name='阮小五');

-- 列子查询
-- A. 查询 "教研部" 和 "咨询部" 的所有员工信息
-- 查询"教研部" 和 "咨询部"对应的id
select id from dept where name='教研部' or name='咨询部';
-- 查询对应id的员工信息
select * from emp where dept_id in (3,2);
-- 组合
select * from emp where dept_id in (select id from dept where name='教研部' or name='咨询部');


-- 行子查询
-- A. 查询与 "李忠" 的薪资 及 职位都相同的员工信息 ;
-- "李忠" 的薪资 及 职位
select salary,job from emp where name='李忠';
-- 相同的员工信息
select * from emp where job=(select job from emp where name='李忠') and salary=(select salary from emp where name='李忠');
-- 但是效率慢
-- 优化
select * from emp where (job,salary) = (5,5000);
select * from emp where (salary,job) = (select salary,job from emp where name='李忠');

-- 表子查询
-- A. 获取每个部门中薪资最高的员工信息
-- 查询每个部门的最高工资
select dept_id,max(salary) from emp group by dept_id;
-- 对应的部门和工资相同的员工信息(联合上面表子查询的临时表来)
select * from emp e , (select dept_id,max(salary) max_sal from emp group by dept_id) as a where salary=a.max_sal and e.dept_id=a.dept_id;

-- 需求:
-- 1. 查询 "教研部" 性别为 男，且在 "2011-05-01" 之后入职的员工信息 。
select id from dept where name='教研部';
select * from emp where dept_id=(select id from dept where name='教研部') and gender=1 and entry_date>'2011-05-01';
# select e.* from emp as e , dept as d where e.dept_id = d.id and d.name = '教研部' and e.gender = 1 and e.entry_date > '2011-05-01';

-- 2. 查询工资 低于公司平均工资的 且 性别为男 的员工信息 。
select avg(emp.salary) from emp;
select * from emp where salary<(select avg(emp.salary) from emp) and gender=1;

-- 3. 查询部门人数超过 10 人的部门名称 。
select dept_id,count(*) from emp group by dept_id;
select l.dept_id,d.name,l.peo_num from dept d,(select dept_id,count(*) peo_num from emp group by dept_id) l where d.id=l.dept_id and l.peo_num>10;
# select d.name , count(*) from emp as e , dept as d where e.dept_id = d.id group by d.name having count(*) > 10;

-- 4. 查询在 "2010-05-01" 后入职，且薪资高于 10000 的 "教研部" 员工信息，并根据薪资倒序排序。
select id from dept where name='教研部';
select * from emp where salary>10000 and entry_date>'2010-05-01' and dept_id=(select id from dept where name='教研部') order by salary desc ;
# select * from emp e , dept d where e.dept_id = d.id and e.entry_date > '2010-05-01' and e.salary > 10000 and d.name = '教研部' order by e.salary desc;

-- 5. 查询工资 低于本部门平均工资的员工信息 。
select dept_id,avg(emp.salary) from emp group by dept_id;
select * from emp e,(select dept_id,avg(emp.salary) dept_avg from emp group by dept_id) l where e.dept_id=l.dept_id and e.salary<l.dept_avg;
# select e.* from emp e , (select dept_id, avg(salary) avg_sal from emp group by dept_id) as a
#           where e.dept_id = a.dept_id and e.salary < a.avg_sal;



