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

create table game_state(
	game_state_id int not null primary key auto_increment,
    country varchar(50) not null,
    player_possession int not null,
    army int not null
);

-- unsure if this table is necessary or if this is how we want to implement it
-- useful potentially for expansion of game beyond computer players
	create table players(
		players_id int not null primary key auto_increment,
        player_one varchar(36) not null,
        player_two varchar(36) not null,
		player_three varchar(36) not null,
        player_four varchar(36),
        player_five varchar(36),
        player_six varchar(36)
    ); 
    

create table game(
	game_id int not null primary key auto_increment,
    user_id varchar(36) not null,
    game_state_id int not null,
    players_id int not null,
    time_elapsed int not null,
    player_turn int not null,
    constraint fk_game_user_id
		foreign key (user_id)
        references game_user(user_id),
	constraint fk_game_game_state_id 
		foreign key (game_state_id)
        references game_state(game_state_id),
	constraint fk_game_players_id 
		foreign key (players_id)
        references players(players_id)
);



insert into game_role (`role`) values
	('ADMIN'),
    ('USER');
    

    


