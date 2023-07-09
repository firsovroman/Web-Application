use my_db;
create table comments (
    comment_id bigint not null auto_increment,
    author_id varchar(255),
    author_name varchar(255),
    comment_text varchar(255),
    date_time varchar(255),
    grade varchar(255),
    message_id bigint,
    primary key (comment_id)
) engine=MyISAM;


create table message (
    id bigint not null auto_increment,
    filename varchar(255),
    category_type varchar(255),
    tag varchar(255),
    text varchar(255),
    user_id bigint,
    primary key (id)
 ) engine=MyISAM;


create table user_role (
    user_id bigint not null,
    roles varchar(255)
) engine=MyISAM;


create table usr (
    id bigint not null auto_increment,
    activation_code varchar(255),
    active bit not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
 ) engine=MyISAM;


alter table comments add constraint message_comment_fk foreign key (message_id) references message (id);
alter table message add constraint message_user_fk foreign key (user_id) references usr (id);
alter table user_role add constraint user_role_fk foreign key (user_id) references usr (id);

