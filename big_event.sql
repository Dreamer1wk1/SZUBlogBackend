create table address_book
(
    id            bigint auto_increment comment '主键'
        primary key,
    user_id       bigint                       not null comment '用户id',
    consignee     varchar(50)                  null comment '收货人',
    sex           varchar(2)                   null comment '性别',
    phone         varchar(11)                  not null comment '手机号',
    province_code varchar(12) charset utf8mb4  null comment '省级区划编号',
    province_name varchar(32) charset utf8mb4  null comment '省级名称',
    city_code     varchar(12) charset utf8mb4  null comment '市级区划编号',
    city_name     varchar(32) charset utf8mb4  null comment '市级名称',
    district_code varchar(12) charset utf8mb4  null comment '区级区划编号',
    district_name varchar(32) charset utf8mb4  null comment '区级名称',
    detail        varchar(200) charset utf8mb4 null comment '详细地址',
    label         varchar(100) charset utf8mb4 null comment '标签',
    is_default    tinyint(1) default 0         not null comment '默认 0 否 1是'
)
    comment '地址簿' collate = utf8mb3_bin;

create table admin
(
    id          bigint auto_increment comment '主键'
        primary key,
    power       int default 1 not null comment '管理员权限：权限越高，值越大,default=1',
    username    varchar(32)   not null comment '用户名',
    phone       varchar(64)   not null comment '电话',
    password    varchar(64)   not null comment '密码',
    status      int default 1 not null comment '状态:default=1,1启用，0禁用',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '最后修改时间'
)
    comment '管理员表' charset = utf8mb3;

create table advertisement
(
    id      bigint auto_increment
        primary key,
    type    int           not null comment '类型：1.开屏广告 2.推广广告 3.分享卡片插图',
    title   varchar(64)   null comment '主题',
    picture varchar(128)  null comment '图片路径',
    detail  int           null comment '详情',
    status  int default 1 null comment '是否启用：0禁用 1启用'
)
    comment '广告表' charset = utf8mb3;

create table user
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    username    varchar(20)             not null comment '用户名',
    password    varchar(32)             null comment '密码',
    nickname    varchar(10)  default '' null comment '昵称',
    email       varchar(128) default '' null comment '邮箱',
    user_pic    varchar(128) default '' null comment '头像',
    create_time datetime                not null comment '创建时间',
    update_time datetime                not null comment '修改时间',
    constraint username
        unique (username)
)
    comment '用户表';

create table article
(
    id          int unsigned auto_increment comment 'ID'
        primary key,
    title       varchar(30)               not null comment '文章标题',
    content     varchar(10000)            not null comment '文章内容',
    cover_img   varchar(128)              not null comment '文章封面',
    state       varchar(3) default '草稿' null comment '文章状态: 只能是[已发布] 或者 [草稿]',
    category_id int unsigned              null comment '文章分类ID',
    create_user int unsigned              not null comment '创建人ID',
    create_time datetime                  not null comment '创建时间',
    update_time datetime                  not null comment '修改时间',
    constraint fk_article_category
        foreign key (category_id) references category (id),
    constraint fk_article_user
        foreign key (create_user) references user (id)
);

