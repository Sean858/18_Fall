use forum;
create table IF NOT EXISTS Administrator (
	admin_ID int(10), 
    admin_name varchar(1000),
    admin_password varchar(20),
    admin_email varchar(50),
    primary key (admin_ID, admin_email)
    );

create table IF NOT EXISTS Users (
	user_ID int(10), 
    user_name varchar(1000),
    user_password varchar(20),
    user_email varchar(50),
    primary key (user_ID, user_email)
    );

create table IF NOT EXISTS Thread (
	thread_ID int(11), 
    user_ID varchar(1000),
    thread_title varchar(1000),
    thread_content varchar(5000),
    thread_location varchar(1000),
    thread_time datetime,
    thread_flagDelete int(1),
    primary key (thread_ID),
    foreign key (user_ID) reference Users(user_ID)
    );

create table IF NOT EXISTS Comments (
	comment_ID int(14), 
    user_ID int(10),
    thread_ID int(11),
    comment_content varchar(1000),
    comment_time datetime,
    comment_flagDelete int(1),
    primary key (comment_ID),
    foreign key (user_ID) reference Users(user_ID),
    foreign key (thread_ID) reference Thread(thread_ID)
    );

create table IF NOT EXISTS childComments (
	child_ID int(14), 
    parent_ID int(14),
    user_ID int(10),
    thread_ID varchar(11),
    comment_content varchar(1000),
    comment_time datetime,
    comment_flagDelete int(1),
    primary key (comment_ID),
    foreign key (user_ID) reference Users(user_ID),
    foreign key (thread_ID) reference Thread(thread_ID),
    foreign key (parent_ID) reference Comments(comment_ID)
    );

create table IF NOT EXISTS Banned (
	user_ID int(10),
    banned int (1),
    ban_time datetime,
    admin_ID int(10),
    ban_content varchar(100),
    primary key (user_ID, ban_time),
    foreign key (user_ID) reference Users(user_ID),
	foreign key (admin_ID) reference Administrator(admin_ID)
    );

create table IF NOT EXISTS Likes (
    thread_ID varchar(11),
	user_ID int(10),
	like_time datetime,
    primary key (thead_ID, like_time),
    foreign key (thread_ID) reference Thread(thread_ID),
    foreign key (user_ID) reference Users(user_ID)
    );

create table IF NOT EXISTS Report (
	reporter int(10),
    reported int (10),
    thread_ID varchar(11),
	comment_ID int(14), 
	report_time datetime,
    primary key (reporter, report_time),
    foreign key (reporter) reference Users(user_ID),
    foreign key (reported) reference Users(user_ID),
    foreign key (thread_ID) reference Thread(thread_ID),
	foreign key (comment_ID) reference Comments(comment_ID)
    );

create table IF NOT EXISTS Topic (
	topic varchar(100),
    thread_ID varchar(11),
    primary key (topic),
	foreign key (thread_ID) reference Thread(thread_ID)
    );
