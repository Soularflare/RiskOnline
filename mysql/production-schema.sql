drop database if exists risk_bg;
create database if not exists risk_bg;

use risk_bg;

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
    constraint fk_user_profile_user_id
		foreign key (user_id)
        references game_user(user_id)
);

create table microtransaction(
	micro_id int not null primary key auto_increment,
    product varchar(250) not null,
    price int not null
);

create table game_user_microtransaction(
	user_id varchar(36) not null,
    micro_id int not null,
    equiped boolean default(0),
    constraint fk_game_user_microtransaction_user_id
		foreign key (user_id)
        references game_user(user_id),
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
	create table player(
		player_id int not null primary key auto_increment,
        player_order varchar(15)
    ); 
    
    
create table game(
	game_id int not null primary key auto_increment,
    time_elapsed int not null,
    player_turn int not null
);

create table game_user_player(
	game_id int not null,
    user_id varchar(36) not null,
    player_id int not null,
    constraint pk_game_user_player
		primary key(game_id, user_id, player_id),
	constraint fk_game_user_player_game_id
		foreign key(game_id)
        references game(game_id),
	constraint fk_game_user_player_user_id
		foreign key(user_id)
        references game_user(user_id),
	constraint fk_game_user_player_player_id
		foreign key(player_id)
        references player(player_id)
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

    
insert into player (player_order) values
	('player one'),
    ('player two'),
    ('player three'),
    ('player four'),
    ('player five'),
    ('player six');
    
    
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

    

    


