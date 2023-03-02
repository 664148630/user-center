# 需要注意：在书写sql语句的时候结束符是; 而整个触发器结束时也需要用到分号;就会出现语法冲突，需要修改我们的结束符号。
# 临时修改sql语句的结束符号：只在当前窗口有效
# 结构： delimiter 新符号
# 案例： delimiter $$
# 触发器
delimiter $$  # 将mysql默认结束符;换为$$
DROP TRIGGER IF EXISTS default_datetime; $$
create trigger default_datetime before insert on user
    for each row # 在insert插入数据之前创建触发器（行级触发器）
    begin # 触发器代码
        if new.createTime is null then # mysql中if语句固定格式
            set new.createTime = now();
        end if;
    end; $$
delimiter ; # 结束触发器要把默认结束回来 不然后续操作容易混淆


DROP TABLE IF EXISTS user;

DROP TABLE user;

-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                        null comment '用户昵称',
    userAccount  varchar(256)                        null comment '账号',
    avatarUrl    varchar(1024)                       null comment '头像',
    gender       tinyint                             null comment '性别',
    userPassword varchar(512)                        not null comment '密码',
    phone        varchar(128)                        null comment '电话',
    email        varchar(512)                        null comment '邮箱',
    userStatus   int       default 0                 not null comment '状态（0-正常）',
    createTime   datetime                            null comment '创建时间',
    updateTime   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete     tinyint   default 0                 not null comment '是否删除',
    userRole     int       default 0                 not null comment '用户角色(0-普通用户 1-管理员)'
)
    comment '用户';