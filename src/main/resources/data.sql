insert into User values (1,'eunnning','1234','eunnning','eunnning@naver.com');
insert into User values (2,'kong','1234','kong','kong@naver.com');

insert into Chat_Room values (1);

insert into category(category_id, name) values (1,'생활/편의시설');
insert into category(category_id, name) values (2,'카페');
insert into category(category_id, name) values (3,'교통');
insert into category(category_id, name) values (4,'온라인 쇼핑');
insert into category(category_id, name) values (5,'음식점');

insert into Account values (1,1,60000,0,20240121,100000,1);
insert into Account values (2,1,0,45000,NOW(),55000,2);
insert into Account values (3,1,0,10000,NOW(),45000,4);
insert into Account values (4,1,0,20000,NOW(),25000,5);

insert into financial_terms (term,definition) values ('디스인플레이션(Disinflation)', '물가 수준이 지속적으로 높아지고 있지만\n그에 반해 상승률이 줄어드는 현상');
insert into financial_terms (term,definition) values ('커버드 본드(Covered Bond)', '은행 등 금융회사가 주택담보대출, 국공채 등\n우량자산을 담보로 발행하는 채권');
insert into financial_terms (term,definition) values ('명목금리', '화폐 1단위를 일정 기간 동안\n빌리는 대가로 지불한 화폐액');
insert into financial_terms (term,definition) values ('실질 금리', '명목금리에서 물가 상승률을 차감해\n실제 구매력을 반영한 금리');
insert into financial_terms (term,definition) values ('기준금리', '한국은행의 환매조건부 채권 매매,\n대기성 여수신 등 금융기관 간 거래의\n기준이 되는 금리');
insert into financial_terms (term,definition) values ('국내총생산(GDP)', '일정 기간동안 한 나라에서 생산된\n재화와 용역의 시장 가치를 합한 것');
insert into financial_terms (term,definition) values ('기축통화', '언제,어디에서나 금융거래를 할 때\n사용할 수 있는 통화');
insert into financial_terms (term,definition) values ('윔블던효과', '국내 금융시장에서 외국계 자본이 침투해\n국내 금융기관을 밀어내는 현상');

