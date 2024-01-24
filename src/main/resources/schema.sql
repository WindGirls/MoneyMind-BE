drop table if exists message;
drop table if exists chat_room;
drop table if exists chatroom;
drop table if exists account;
drop table if exists user;

CREATE TABLE user (
                      user_id	bigint(20)   not null auto_increment primary key,
                      Name	varchar(20) not null,
                      Age	bigint(20)   not null,
                      Job	varchar(50) not null,
                      Token	bigint(128) null
);

CREATE TABLE chatroom (
                          chatroom_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user1_id BIGINT NOT NULL,
                          user2_id BIGINT NOT NULL,
                          FOREIGN KEY (user1_id) REFERENCES user(user_id),
                          FOREIGN KEY (user2_id) REFERENCES user(user_id),
                          CHECK (user1_id <> user2_id)
);

CREATE TABLE message (
                         message_id	BIGINT AUTO_INCREMENT PRIMARY KEY,
                         chatroom_id	BIGINT not null,
                         Content	VARCHAR(255)   not null,
                         SendTime	timestamp	NULL,
                         user_id	bigint(20)   not null,
                         foreign key (chatroom_id) references chatroom(chatroom_id),
                         foreign key (user_id) references user(user_id)
);

CREATE TABLE account (
                         transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         deposit INT NOT NULL,
                         withdrawal INT NOT NULL,
                         times TIMESTAMP NOT NULL,
                         balance INT NOT NULL,
                         place VARCHAR(20) NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(user_id)
);
