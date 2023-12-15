-- Skapa personer
insert into persons(person_first_name, person_last_name, person_nickname) values('Fabian', 'Knopf', 'Fabian');
SELECT @PersonKey1 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Linus', 'Axelsson', 'Linus');
SELECT @PersonKey2 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Johanna', 'Selander', 'Johanna');
SELECT @PersonKey3 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Morten', 'Sandgrav', 'Morten');
SELECT @PersonKey4 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Robin', 'Andersson', 'OneHandedOnly'); -- player1
SELECT @PersonKey5 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Robert', 'Fridström', 'Lem0naZ'); -- player2
SELECT @PersonKey6 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Daniel', 'Hedström', 'Drome'); -- player 3
SELECT @PersonKey7 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Andreas', 'Mellin', 'Andruzo'); -- player 4
SELECT @PersonKey8 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Elsa', 'Lindberg', 'elslin90');
SELECT @PersonKey9 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Axel', 'Persson', 'axeper49');
SELECT @PersonKey10 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Freja', 'Bergström', 'freber58');
SELECT @PersonKey11 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Hugo', 'Larsson', 'huglar61');
SELECT @PersonKey12 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Linn', 'Svensson', 'linsve41');
SELECT @PersonKey13 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Emil', 'Nilsson', 'eminil14');
SELECT @PersonKey14 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Maja', 'Gustafsson', 'majgus93');
SELECT @PersonKey15 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Erik', 'Ekström', 'erieks87');
SELECT @PersonKey16 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Klara', 'Andreasson', 'klaand36');
SELECT @PersonKey17 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Viktor', 'Johansson', 'vikjoh65');
SELECT @PersonKey18 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Alice', 'Ström', 'alistr28');
SELECT @PersonKey19 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Noah', 'Blom', 'noablo12');
SELECT @PersonKey20 := @@IDENTITY;


-- Skapa Spel
insert into games(game_name) values('League of Legends');
SELECT @GameKey1 := @@IDENTITY;

insert into games(game_name) values('Fortnite');
SELECT @GameKey2 := @@IDENTITY;

insert into games(game_name) values('StarCraft II');
SELECT @GameKey3 := @@IDENTITY;


-- Skapa Team
insert into teams(team_name, game_id) values('Cyber Phoenixes', @GameKey1);
SELECT @TeamKey1 := @@IDENTITY;

insert into teams(team_name, game_id) values('Neon Dragons', @GameKey1);
SELECT @TeamKey2 := @@IDENTITY;

insert into teams(team_name, game_id) values('Quantum Vipers', @GameKey2);
SELECT @TeamKey3 := @@IDENTITY;

insert into teams(team_name, game_id) values('Galactic Gladiators', @GameKey2);
SELECT @TeamKey4 := @@IDENTITY;


-- Insert persons into players
INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey5, @TeamKey1, @GameKey1);
SELECT @PlayerKey1 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey6, @TeamKey1, @GameKey1);
SELECT @PlayerKey2 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey7, @TeamKey1, @GameKey1);
SELECT @PlayerKey3 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey8, @TeamKey2, @GameKey1);
SELECT @PlayerKey4 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey9, @TeamKey2, @GameKey1);
SELECT @PlayerKey5 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey10, @TeamKey2, @GameKey1);
SELECT @PlayerKey6 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey11, @TeamKey3, @GameKey2);
SELECT @PlayerKey7 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey12, @TeamKey3, @GameKey2);
SELECT @PlayerKey8 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey13, @TeamKey3, @GameKey2);
SELECT @PlayerKey9 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey14, @TeamKey4, @GameKey2);
SELECT @PlayerKey10 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey15, @TeamKey4, @GameKey2);
SELECT @PlayerKey11 := @@IDENTITY;

INSERT INTO players(person_person_id, team_id, game_id) VALUES (@PersonKey16, @TeamKey4, @GameKey2);
SELECT @PlayerKey12 := @@IDENTITY;

INSERT INTO players(person_person_id, game_id) VALUES (@PersonKey17, @GameKey3);
SELECT @PlayerKey13 := @@IDENTITY;

INSERT INTO players(person_person_id, game_id) VALUES (@PersonKey18, @GameKey3);
SELECT @PlayerKey14 := @@IDENTITY;

INSERT INTO players(person_person_id, game_id) VALUES (@PersonKey19, @GameKey3);
SELECT @PlayerKey15 := @@IDENTITY;

INSERT INTO players(person_person_id, game_id) VALUES (@PersonKey20, @GameKey3);
SELECT @PlayerKey16 := @@IDENTITY;


-- Insert persons into staff
insert into staff(person_person_id) values (@PersonKey1);
select @StaffKey1 := @@IDENTITY;

insert into staff(person_person_id) values (@PersonKey2);
select @StaffKey2 := @@IDENTITY;

insert into staff(person_person_id) values (@PersonKey3);
select @StaffKey3 := @@IDENTITY;

insert into staff(person_person_id) values (@PersonKey4);
select @StaffKey4 := @@IDENTITY;


-- Insert of data in SoloMatches
insert into solo_matches(date, player1_id, player2_id) values(DATE(20000101), @PersonKey17, @PersonKey18);
insert into solo_matches(date, player1_id, player2_id) values(DATE(20231231), @PersonKey19, @PersonKey20);

insert into solo_matches(date, player1_id, player2_id) values(DATE(20000101), @PersonKey17, @PersonKey19);
insert into solo_matches(date, player1_id, player2_id) values(DATE(20231231), @PersonKey18, @PersonKey20);