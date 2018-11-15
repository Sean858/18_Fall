create table IF NOT EXISTS administrator (
	admin_id int(10), 
    admin_name varchar(255),
    admin_password varchar(20),
    admin_email varchar(50),
    primary key (admin_id),
    unique (admin_id, admin_email)
    );

create table IF NOT EXISTS users (
	user_id int(10), 
    user_name varchar(255),
    user_password varchar(20),
    user_email varchar(50),
    primary key (user_id),
    unique (user_id, user_email)
    );

create table IF NOT EXISTS thread (
	thread_id int(11), 
    user_id int(10),
    thread_title varchar(255),
    thread_content varchar(255),
    thread_location varchar(255),
    thread_time datetime,
    thread_flagdelete int(1),
    primary key (thread_id),
    foreign key (user_id) references users(user_id)
    );

create table IF NOT EXISTS comments (
	comment_id int(14),
    parent_id int(14),
    user_id int(10),
    thread_id int(11),
    comment_content varchar(255),
    comment_time datetime,
    comment_flagdelete int(1),
    primary key (comment_id),
    foreign key (user_id) references users(user_id),
    foreign key (thread_id) references thread(thread_id),
    foreign key (parent_id) references comments(comment_id)
    );


create table IF NOT EXISTS banned (
	user_id int(10),
    banned int (1),
    ban_time datetime,
    admin_id int(10),
    ban_content varchar(100),
    primary key (user_id, ban_time),
    foreign key (user_id) references users(user_id),
	foreign key (admin_id) references administrator(admin_id)
    );

create table IF NOT EXISTS likes (
    thread_id int(11),
	user_id int(10),
	like_time datetime,
    primary key (thread_id, like_time),
    foreign key (thread_id) references thread(thread_id),
    foreign key (user_id) references users(user_id)
    );

create table IF NOT EXISTS report (
	reporter int(10),
    reported int (10),
    thread_id int(11),
	comment_id int(14), 
	report_time datetime,
    primary key (reporter, report_time),
    foreign key (reporter) references users(user_id),
    foreign key (reported) references users(user_id),
    foreign key (thread_id) references thread(thread_id),
	foreign key (comment_id) references comments(comment_id)
    );

create table IF NOT EXISTS topic (
	topic varchar(255),
    thread_id int(11),
    primary key (topic),
	foreign key (thread_id) references thread(thread_id)
    );
    
    

 