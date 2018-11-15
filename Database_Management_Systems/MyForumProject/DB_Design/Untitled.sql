drop database if exists myforum;
create database myforum;
use myforum;


SET FOREIGN_KEY_CHECKS=0;

drop table if exists admins;
create table admins (
    aid int(10) NOT NULL AUTO_INCREMENT,
    aname varchar(255) NOT NULL,
    apassword varchar(20) NOT NULL,
    aemail varchar(50) NOT NULL,
    primary key (aid),
    unique (aid, aemail)
    );

drop table if exists users;
create table IF NOT EXISTS users (
    uid int(10) NOT NULL AUTO_INCREMENT,
    uname varchar(255) NOT NULL,
    upassword varchar(20) NOT NULL,
    uemail varchar(50) NOT NULL,
    primary key (uid),
    unique (uid, uemail)
    );

drop table if exists post;
create table IF NOT EXISTS post (
    pid int(11) NOT NULL AUTO_INCREMENT, 
    uid int(10) NOT NULL,
    tid  int(11) NOT NULL,
    content varchar(255) NOT NULL,
    location varchar(255) NOT NULL,
    post_time datetime NOT NULL,
    flag int(1) NOT NULL,
    primary key (pid),
    foreign key (uid) references users(uid),
    foreign key (tid) references topic(tid)
    );

drop table if exists comments;
create table IF NOT EXISTS comments (
    cid int(14) NOT NULL AUTO_INCREMENT, 
    uid int(10) NOT NULL,
    pid int(11) NOT NULL,
    father_id int(11) NOT NULL,
    content varchar(255) NOT NULL,
    comment_time datetime NOT NULL,
    flag int(1) NOT NULL,
    primary key (cid),
    foreign key (uid) references users(uid),
    foreign key (pid) references post(pid) ON DELETE CASCADE,
    foreign key (father_id) references comments(cid) ON DELETE CASCADE
    );



drop table if exists banned;
create table IF NOT EXISTS banned (
    uid int(10) NOT NULL,
    banned int (1) NOT NULL,
    ban_time datetime NOT NULL,
    aid int(10) NOT NULL,
    content varchar(100) NOT NULL,
    primary key (uid, ban_time),
    foreign key (uid) references users(uid),
    foreign key (aid) references admins(aid)
    );

drop table if exists likes;
create table IF NOT EXISTS likes (
    pid int(11) NOT NULL,
    uid int(10) NOT NULL,
    like_time datetime NOT NULL,
    primary key (pid, like_time),
    foreign key (pid) references post(pid) ON DELETE CASCADE,
    foreign key (uid) references users(uid)
    );

drop table if exists report;
create table IF NOT EXISTS report (
    rid int(11) NOT NULL AUTO_INCREMENT,
    reporter int(10) NOT NULL,
    reported int (10) NOT NULL,
    pid int(11) ,
    cid int(14) , 
    report_time datetime NOT NULL,
    primary key (rid),
    foreign key (reporter) references users(uid),
    foreign key (reported) references users(uid),
    foreign key (pid) references post(pid) ON DELETE CASCADE,
    foreign key (cid) references comments(cid) ON DELETE CASCADE
    );


drop table if exists topic;
create table IF NOT EXISTS topic (
    tid int(11) NOT NULL AUTO_INCREMENT,
    topic varchar(255) NOT NULL,
    pid int(11) NOT NULL,
    primary key (tid),
    foreign key (pid) references post(pid)
    );