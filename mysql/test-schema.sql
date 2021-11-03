 
drop database if exists risk_bg_test;
create database if not exists risk_bg_test;

use risk_bg_test;

create table game_role(
	role_id int primary key auto_increment,
	`role` varchar(20) not null
);

create table game_user(
	user_id varchar(36) not null primary key default(uuid()),
    user_name varchar(250) not null unique,
    password_hash varchar(2048) not null,
    disabled boolean not null default(0)
);

create table game_user_role(
	user_id varchar(36) not null,
    role_id int not null,
    constraint pk_game_user_role
		primary key(user_id, role_id),
	constraint fk_game_user_role_user_id
		foreign key(user_id)
        references game_user(user_id),
	constraint fk_game_user_role_role_id
		foreign key(role_id)
        references game_role(role_id)
);

create table user_profile(
	profile_id varchar(36) not null primary key default(uuid()),
    user_id varchar(36) not null,
    total_games int not null default(0),
    wins int not null default(0),
    game_time int not null default(0),
    points int not null default(0),
    constraint fk_user_profile_user_id
		foreign key (user_id)
        references game_user(user_id)
);

create table microtransaction(
	micro_id int not null primary key auto_increment,
    product varchar(250) not null,
    price int not null
);

create table user_profile_microtransaction(
	profile_id varchar(36) not null,
    micro_id int not null,
    equiped boolean default(0),
    constraint fk_user_profile_microtransaction_profile_id
		foreign key (profile_id)
        references user_profile(profile_id),
	constraint fk_game_user_microtransaction_micro_id
		foreign key (micro_id)
        references microtransaction(micro_id)
);

create table countries(
	country_id int not null primary key,
    country varchar(50) not null
);

-- unsure if this table is necessary or if this is how we want to implement it
-- useful potentially for expansion of game beyond computer players
-- create table player(
-- 		player_id int not null primary key auto_increment,
--         player_order varchar(15)
--     ); 
    
-- insert into player (player_order) values
-- 	('player one'),
--     ('player two'),
--     ('player three'),
--     ('player four'),
--     ('player five'),
--     ('player six');
    
--   select * from player;  
	
create table game(
	game_id int not null primary key auto_increment,
    time_elapsed int not null,
    player_turn int not null
);

create table game_player(
	game_id int not null,
	turn_order int not null,
    user_id varchar(36) null,
    constraint pk_game_user_player
		primary key(game_id, turn_order),
	constraint fk_game_user_player_game_id
		foreign key(game_id)
        references game(game_id),
	constraint fk_game_user_player_user_id
		foreign key(user_id)
        references game_user(user_id)
-- 	constraint fk_game_user_player_player_id
-- 		foreign key(player_id)
--         references player(player_id)
	);
    
create table game_state(
	game_id int not null,
    country_id int not null,
    player_possession int not null,
    army int not null,
    constraint pk_game_state
		primary key(game_id, country_id),
	constraint fk_game_state_game_id
		foreign key(game_id)
        references game(game_id),
	constraint fk_game_state_country_id
		foreign key(country_id)
        references countries(country_id)
);
insert into game_role (`role`) values
	('ADMIN'),
    ('USER');
    
insert into countries (country_id, country) values
	(0, 'ALASKA'),
    (1, 'NW_TERRITORY'),
    (2, 'ALBERTA'),
    (3, 'WestUS'),
    (4, 'EastUS'),
    (5, 'ONTARIO'),
    (6, 'QUEBEC'),
    (7, 'GREENLAND'),
    (8, 'CENTRAL_AMERICA'),
    (9, 'VENEZUELA'),
    (10, 'PERU'),
    (11, 'BRAZIL'),
    (12, 'ARGENTINA'),
    (13, 'N_AFRICA'),
    (14, 'EYGYPT'),
    (15, 'E_AFRICA'),
    (16, 'CONGO'),
    (17, 'S_AFRICA'),
    (18, 'MADAGASCAR'),
    (19, 'ICELAND'),
    (20, 'G_BRITAIN'),
    (21, 'SCANDINAVIA'),
    (22, 'W_EUROPE'),
    (23, 'S_EUROPE'),
    (24, 'N_EUROPE'),
    (25, 'UKRAINE'),
    (26, 'URAL'),
    (27, 'AFGHANISTAN'),
    (28, 'MID_EAST'),
    (29, 'SIBERIA'),
    (30, 'YAKURSK'),
    (31, 'KAMCHATKA'),
    (32, 'IRKUTSK'),
    (33, 'JAPAN'),
    (34, 'MONGOLIA'),
    (35, 'CHINA'),
    (36, 'INDIA'),
    (37, 'SIAM'),
    (38, 'INDONESIA'),
    (39, 'NEW_GUINEA'),
    (40, 'E_AUSTRALIA'),
    (41, 'W_AUSTRALIA');
    
insert into game_user (user_id, user_name, password_hash) values
	('4d980627-3b3c-11ec-8708-0242ac110002', 'user', '$2a$10$ZMZEHR/CeSDPh7o0dIUPJeSh6r2TGDMUuBSC.Vff6VGL2CGNUdB5q'),
    ('4d980a71-3b3c-11ec-8708-0242ac110002', 'admin', '$2a$10$L4c8Ky5D5O7WsVm89lTiPO3/pPtME0itveW5GuSI1.vVWSBS1bCJ2');

insert into game_user_role (user_id, role_id) values
	('4d980a71-3b3c-11ec-8708-0242ac110002', 1),
    ('4d980627-3b3c-11ec-8708-0242ac110002', 2);

insert into user_profile (profile_id, user_id, total_games, wins, game_time, points) values
	('69367d6d-3b3e-11ec-8708-0242ac110002', '4d980627-3b3c-11ec-8708-0242ac110002', 2, 1, 200, 1000),
    ('69367ff8-3b3e-11ec-8708-0242ac110002', '4d980a71-3b3c-11ec-8708-0242ac110002', 0, 0, 0, 100);
    
insert into microtransaction (product, price) values
	('Gummy Bear Avatar', 2),
    ('Black Bear Bow Tie Avatar', 1),
    ('Polar Bear Top Hat Avatar', 2),
    ('Ursa Major Avatar', 1);
    
insert into user_profile_microtransaction(profile_id, micro_id, equiped) values
	('69367ff8-3b3e-11ec-8708-0242ac110002', 1, 1),
    ('69367d6d-3b3e-11ec-8708-0242ac110002', 1, 0),
    ('69367d6d-3b3e-11ec-8708-0242ac110002', 2, 0),
    ('69367d6d-3b3e-11ec-8708-0242ac110002', 3, 1);
    
    insert into game(time_elapsed, player_turn) values
	(111, 1),
    (30, 1),
    (170, 1),
    (0, 1);
    
insert into game_player(game_id, turn_order, user_id) values
	(1, 1, '4d980627-3b3c-11ec-8708-0242ac110002'),
	(1, 2, null),
	(1, 3, null),
	(2, 1, '4d980627-3b3c-11ec-8708-0242ac110002'),
	(2, 2, null),
	(2, 3, null),
	(3, 1,'4d980627-3b3c-11ec-8708-0242ac110002'),
	(3, 2, null),
	(3, 3, null);
        
insert into game_state (game_id, country_id, player_possession, army) values
	(1, 0, 1, 3),
    (1, 1, 1, 3),
    (1, 2, 1, 3),
    (1, 3, 1, 3),
    (1, 4, 1, 3),
    (1, 5, 1, 3),
	(1, 6, 1, 3),
    (1, 7, 1, 3),
    (1, 8, 2, 3),
    (1, 9, 2, 3),
    (1, 10, 2, 3),
    (1, 11, 2, 3),
	(1, 12, 2, 3),
    (1, 13, 2, 3),
    (1, 14, 2, 3),
    (1, 15, 2, 3),
    (1, 16, 3, 3),
    (1, 17, 3, 3),
	(1, 18, 3, 3),
    (1, 19, 3, 3),
    (1, 20, 3, 3),
    (1, 21, 3, 3),
    (1, 22, 3, 3),
    (1, 23, 3, 3),
	(1, 24, 3, 3),
    (1, 25, 3, 3),
    (1, 26, 1, 3),
    (1, 27, 1, 3),
    (1, 28, 1, 3),
    (1, 29, 2, 3),
	(1, 30, 2, 3),
    (1, 31, 2, 3),
    (1, 32, 2, 3),
    (1, 33, 2, 3),
    (1, 34, 3, 3),
    (1, 35, 3, 3),
    (1, 36, 3, 3),
    (1, 37, 3, 3),
    (1, 38, 3, 3),
    (1, 39, 2, 3),
    (1, 40, 1, 3),
    (1, 41, 1, 3),
	(2, 0, 1, 3),
    (2, 1, 1, 3),
    (2, 2, 1, 3),
    (2, 3, 1, 3),
    (2, 4, 1, 3),
    (2, 5, 1, 3),
	(2, 6, 1, 3),
    (2, 7, 1, 3),
    (2, 8, 2, 3),
    (2, 9, 2, 3),
    (2, 10, 2, 3),
    (2, 11, 2, 3),
	(2, 12, 2, 3),
    (2, 13, 2, 3),
    (2, 14, 2, 3),
    (2, 15, 2, 3),
    (2, 16, 3, 3),
    (2, 17, 3, 3),
	(2, 18, 3, 3),
    (2, 19, 3, 3),
    (2, 20, 3, 3),
    (2, 21, 3, 3),
    (2, 22, 3, 3),
    (2, 23, 3, 3),
	(2, 24, 3, 3),
    (2, 25, 3, 3),
    (2, 26, 1, 3),
    (2, 27, 1, 3),
    (2, 28, 1, 3),
    (2, 29, 2, 3),
	(2, 30, 2, 3),
    (2, 31, 2, 3),
    (2, 32, 2, 3),
    (2, 33, 2, 3),
    (2, 34, 3, 3),
    (2, 35, 3, 3),
    (2, 36, 3, 3),
    (2, 37, 3, 3),
    (2, 38, 3, 3),
    (2, 39, 2, 3),
    (2, 40, 1, 3),
    (2, 41, 1, 3),
	(3, 0, 1, 3),
    (3, 1, 1, 3),
    (3, 2, 1, 3),
    (3, 3, 1, 3),
    (3, 4, 1, 3),
    (3, 5, 1, 3),
	(3, 6, 1, 3),
    (3, 7, 1, 3),
    (3, 8, 2, 3),
    (3, 9, 2, 3),
    (3, 10, 2, 3),
    (3, 11, 2, 3),
	(3, 12, 2, 3),
    (3, 13, 2, 3),
    (3, 14, 2, 3),
    (3, 15, 2, 3),
    (3, 16, 3, 3),
    (3, 17, 3, 3),
	(3, 18, 3, 3),
    (3, 19, 3, 3),
    (3, 20, 3, 3),
    (3, 21, 3, 3),
    (3, 22, 3, 3),
    (3, 23, 3, 3),
	(3, 24, 3, 3),
    (3, 25, 3, 3),
    (3, 26, 1, 3),
    (3, 27, 1, 3),
    (3, 28, 1, 3),
    (3, 29, 2, 3),
	(3, 30, 2, 3),
    (3, 31, 2, 3),
    (3, 32, 2, 3),
    (3, 33, 2, 3),
    (3, 34, 3, 3),
    (3, 35, 3, 3),
    (3, 36, 3, 3),
    (3, 37, 3, 3),
    (3, 38, 3, 3),
    (3, 39, 2, 3),
    (3, 40, 1, 3),
    (3, 41, 1, 3);
    

delimiter //
create procedure set_known_good_state()
begin

delete from user_profile_microtransaction;
alter table user_profile_microtransaction auto_increment = 1;
delete from microtransaction;
alter table microtransaction auto_increment = 1;
delete from user_profile;
delete from game_state;
delete from game_player;
delete from game;
alter table game auto_increment = 1;

insert into user_profile (profile_id, user_id, total_games, wins, game_time, points) values
	('69367d6d-3b3e-11ec-8708-0242ac110002', '4d980627-3b3c-11ec-8708-0242ac110002', 2, 1, 200, 1000),
    ('69367ff8-3b3e-11ec-8708-0242ac110002', '4d980a71-3b3c-11ec-8708-0242ac110002', 0, 0, 0, 100);

insert into microtransaction (product, price) values
	('Gummy Bear Avatar', 2),
    ('Black Bear Bow Tie Avatar', 1),
    ('Polar Bear Top Hat Avatar', 2),
    ('Ursa Major Avatar', 1);
    
insert into user_profile_microtransaction(profile_id, micro_id, equiped) values
	('69367ff8-3b3e-11ec-8708-0242ac110002', 1, 1),
    ('69367d6d-3b3e-11ec-8708-0242ac110002', 1, 0),
    ('69367d6d-3b3e-11ec-8708-0242ac110002', 2, 0),
    ('69367d6d-3b3e-11ec-8708-0242ac110002', 3, 1);

insert into game(time_elapsed, player_turn) values
	(111, 1),
    (30, 1),
    (170, 1),
    (0, 1);
    
insert into game_player(game_id, turn_order, user_id) values
	(1, 1, '4d980627-3b3c-11ec-8708-0242ac110002'),
	(1, 2, null),
	(1, 3, null),
	(2, 1, '4d980627-3b3c-11ec-8708-0242ac110002'),
	(2, 2, null),
	(2, 3, null),
	(3, 1,'4d980627-3b3c-11ec-8708-0242ac110002'),
	(3, 2, null),
	(3, 3, null);
        
insert into game_state (game_id, country_id, player_possession, army) values
	(1, 0, 1, 3),
    (1, 1, 1, 3),
    (1, 2, 1, 3),
    (1, 3, 1, 3),
    (1, 4, 1, 3),
    (1, 5, 1, 3),
	(1, 6, 1, 3),
    (1, 7, 1, 3),
    (1, 8, 2, 3),
    (1, 9, 2, 3),
    (1, 10, 2, 3),
    (1, 11, 2, 3),
	(1, 12, 2, 3),
    (1, 13, 2, 3),
    (1, 14, 2, 3),
    (1, 15, 2, 3),
    (1, 16, 3, 3),
    (1, 17, 3, 3),
	(1, 18, 3, 3),
    (1, 19, 3, 3),
    (1, 20, 3, 3),
    (1, 21, 3, 3),
    (1, 22, 3, 3),
    (1, 23, 3, 3),
	(1, 24, 3, 3),
    (1, 25, 3, 3),
    (1, 26, 1, 3),
    (1, 27, 1, 3),
    (1, 28, 1, 3),
    (1, 29, 2, 3),
	(1, 30, 2, 3),
    (1, 31, 2, 3),
    (1, 32, 2, 3),
    (1, 33, 2, 3),
    (1, 34, 3, 3),
    (1, 35, 3, 3),
    (1, 36, 3, 3),
    (1, 37, 3, 3),
    (1, 38, 3, 3),
    (1, 39, 2, 3),
    (1, 40, 1, 3),
    (1, 41, 1, 3),
	(2, 0, 1, 3),
    (2, 1, 1, 3),
    (2, 2, 1, 3),
    (2, 3, 1, 3),
    (2, 4, 1, 3),
    (2, 5, 1, 3),
	(2, 6, 1, 3),
    (2, 7, 1, 3),
    (2, 8, 2, 3),
    (2, 9, 2, 3),
    (2, 10, 2, 3),
    (2, 11, 2, 3),
	(2, 12, 2, 3),
    (2, 13, 2, 3),
    (2, 14, 2, 3),
    (2, 15, 2, 3),
    (2, 16, 3, 3),
    (2, 17, 3, 3),
	(2, 18, 3, 3),
    (2, 19, 3, 3),
    (2, 20, 3, 3),
    (2, 21, 3, 3),
    (2, 22, 3, 3),
    (2, 23, 3, 3),
	(2, 24, 3, 3),
    (2, 25, 3, 3),
    (2, 26, 1, 3),
    (2, 27, 1, 3),
    (2, 28, 1, 3),
    (2, 29, 2, 3),
	(2, 30, 2, 3),
    (2, 31, 2, 3),
    (2, 32, 2, 3),
    (2, 33, 2, 3),
    (2, 34, 3, 3),
    (2, 35, 3, 3),
    (2, 36, 3, 3),
    (2, 37, 3, 3),
    (2, 38, 3, 3),
    (2, 39, 2, 3),
    (2, 40, 1, 3),
    (2, 41, 1, 3),
	(3, 0, 1, 3),
    (3, 1, 1, 3),
    (3, 2, 1, 3),
    (3, 3, 1, 3),
    (3, 4, 1, 3),
    (3, 5, 1, 3),
	(3, 6, 1, 3),
    (3, 7, 1, 3),
    (3, 8, 2, 3),
    (3, 9, 2, 3),
    (3, 10, 2, 3),
    (3, 11, 2, 3),
	(3, 12, 2, 3),
    (3, 13, 2, 3),
    (3, 14, 2, 3),
    (3, 15, 2, 3),
    (3, 16, 3, 3),
    (3, 17, 3, 3),
	(3, 18, 3, 3),
    (3, 19, 3, 3),
    (3, 20, 3, 3),
    (3, 21, 3, 3),
    (3, 22, 3, 3),
    (3, 23, 3, 3),
	(3, 24, 3, 3),
    (3, 25, 3, 3),
    (3, 26, 1, 3),
    (3, 27, 1, 3),
    (3, 28, 1, 3),
    (3, 29, 2, 3),
	(3, 30, 2, 3),
    (3, 31, 2, 3),
    (3, 32, 2, 3),
    (3, 33, 2, 3),
    (3, 34, 3, 3),
    (3, 35, 3, 3),
    (3, 36, 3, 3),
    (3, 37, 3, 3),
    (3, 38, 3, 3),
    (3, 39, 2, 3),
    (3, 40, 1, 3),
    (3, 41, 1, 3);
    
    end //
    delimiter ;
    
    set sql_safe_updates = 0;
	call set_known_good_state();
	set sql_safe_updates = 1;
    
    select * from game_user;
	select * from game_role;
	select * from game_user_role;    
	select * from user_profile;
	select * from microtransaction;
	select * from game_player;
    select * from game_state;
    select * from game;

