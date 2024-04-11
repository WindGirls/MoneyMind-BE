drop table if exists account;
drop table if exists category;
drop table if exists message;
drop table if exists chat_room_participants;
drop table if exists chat_room;
drop table if exists chatroom;
drop table if exists financial_terms;
drop table if exists memo;
drop table if exists authority;
drop table if exists user;

CREATE TABLE user (
	user_id	bigint(20)  auto_increment primary key,
	account varchar(20) not null,
	password	varchar(255) not null,
	name	varchar(20) not null,
	email	varchar(50) not null
);

CREATE TABLE authority (
    authority_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    authority_name VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE chat_room (
    chat_room_id BIGINT PRIMARY KEY
);

CREATE TABLE chat_room_participants (
    chat_room_participants_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    chat_room_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (chat_room_id) REFERENCES chat_room(chat_room_id)
);



CREATE TABLE message (
                         message_id	BIGINT AUTO_INCREMENT PRIMARY KEY,
                         chat_room_id BIGINT,
                         Content	VARCHAR(255)   not null,
                         send_time	Date	NULL,
                         user_id BIGINT NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(user_id),
                         FOREIGN KEY (chat_room_id) REFERENCES chat_room(chat_room_id)
);

CREATE TABLE category (
                              CATEGORY_ID   bigint(20)   NOT NULL primary key,
                              NAME   VARCHAR(255)   NULL
);

CREATE TABLE account (
                         transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         deposit INT NOT NULL,
                         withdrawal INT NOT NULL,
                         times Date NOT NULL,
                         balance INT NOT NULL,
                         category_id   BIGINT(20)   NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(user_id),
                         FOREIGN KEY(category_id) REFERENCES category(category_id)
);

CREATE TABLE chat_bot_message (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  content VARCHAR(255) NOT NULL,
                                  user_id BIGINT NOT NULL,
                                  c_times TIMESTAMP DEFAULT NULL,
                                  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE financial_terms (
    term_id bigINT AUTO_INCREMENT PRIMARY KEY,
    term VARCHAR(255) NOT NULL,
    definition VARCHAR(255) NOT NULL
);

CREATE TABLE memo (
    memo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content VARCHAR(255) not null,
    created_at timestamp NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);