insert into User values (1,'eunnning','1234','eunnning','eunnning@naver.com');
insert into User values (2,'kong','1234','kong','kong@naver.com');

insert into Chat_Room values (1,1,2);

insert into Message(Message_id,chat_room_id,Content,sender_id,receiver_id) values (1,1,'안녕',1,2);

insert into category(category_id, name) values (1,'생활/편의시설');
insert into category(category_id, name) values (2,'카페');
insert into category(category_id, name) values (3,'교통');
insert into category(category_id, name) values (4,'온라인 쇼핑');
insert into category(category_id, name) values (5,'음식점');

insert into Account values (1,1,60000,0,20240121,100000,1);
insert into Account values (2,1,0,45000,NOW(),55000,2);
insert into Account values (3,1,0,10000,NOW(),45000,4);
insert into Account values (4,1,0,20000,NOW(),25000,5);

INSERT INTO chat_bot_message (id,content, user_id, c_times)
VALUES
    (1,'이번 달 사용한 금액은 얼마인가요?', 1, NOW()),
    (2,'저번 달의 총 지출은 어떻게 되나요?', 1, NOW()),
    (3,'내 통장 잔액을 알려주세요.', 2, NOW()),
    (4,'어제의 소비 내역을 확인하고 싶어요.', 2, NOW());

