drop table if exists Message;
drop table if exists ChatRoom;
drop table if exists User;

CREATE TABLE User (
	UserId	bigint(20)   not null auto_increment primary key,
	Name	varchar(20) not null,
	Age	bigint(20)   not null,
	Job	varchar(50) not null,
	Token	bigint(128) null
);

CREATE TABLE ChatRoom (
    ChatRoomId BIGINT AUTO_INCREMENT PRIMARY KEY,
    User1Id BIGINT NOT NULL,
    User2Id BIGINT NOT NULL,
    FOREIGN KEY (User1Id) REFERENCES User(UserId),
    FOREIGN KEY (User2Id) REFERENCES User(UserId),
    CHECK (User1Id <> User2Id)
);

CREATE TABLE Message (
	MessageId	BIGINT AUTO_INCREMENT PRIMARY KEY,
	ChatRoomId	BIGINT not null,
	Content	VARCHAR(255)   not null,
	SendTime	timestamp	NULL,
	UserId	bigint(20)   not null,
	foreign key (ChatRoomId) references ChatRoom(ChatRoomId),
	foreign key (UserId) references user(UserId)
);