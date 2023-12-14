-- Insert of data in staff and persons
insert into persons(person_first_name, person_last_name, person_nickname) values('Fabian', 'Knopf', 'Fabian');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO staff(person_person_id) VALUES (@PersonKey);

insert into persons(person_first_name, person_last_name, person_nickname) values('Linus', 'Axelsson', 'Linus');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO staff(person_person_id) VALUES (@PersonKey);

insert into persons(person_first_name, person_last_name, person_nickname) values('Johanna', 'Selander', 'Johanna');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO staff(person_person_id) VALUES (@PersonKey);

insert into persons(person_first_name, person_last_name, person_nickname) values('Morten', 'Sandgrav', 'Morten');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO staff(person_person_id) VALUES (@PersonKey);

-- Insert of data in players and persons
insert into games(game_name) values ('Fotboll');
select @GameKey := @@IDENTITY;

insert into teams(team_name, game_id) values('Arsenal', @GameKey);
SELECT @TeamKey := @@IDENTITY;





insert into persons(person_first_name, person_last_name, person_nickname) values('Robin', 'Andersson', 'OneHandedOnly');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO players(person_person_id, team_id) VALUES (@PersonKey, @TeamKey);
SELECT @PlayerKey1 := @@IDENTITY;

insert into persons(person_first_name, person_last_name, person_nickname) values('Robert', 'Fridström', 'Lem0naZ');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO players(person_person_id, team_id) VALUES (@PersonKey, @TeamKey);

SELECT @PlayerKey2 := @@IDENTITY;
insert into teams(team_name, game_id) values('Manchester', @GameKey);

SELECT @TeamKey := @@IDENTITY;
insert into persons(person_first_name, person_last_name, person_nickname) values('Daniel', 'Hedström', 'Drome');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO players(person_person_id, team_id) VALUES (@PersonKey, @TeamKey);
SELECT @PlayerKey3 := @@IDENTITY;
insert into persons(person_first_name, person_last_name, person_nickname) values('Andreas', 'Mellin', 'Andruzo');
SELECT @PersonKey := @@IDENTITY;
INSERT INTO players(person_person_id, team_id) VALUES (@PersonKey, @TeamKey);
SELECT @PlayerKey4 := @@IDENTITY;
-- Insert of data in SoloMatches
insert into solo_matches(date, player1_id, player2_id) values(DATE(20000101),@PlayerKey1,@PlayerKey2);
insert into solo_matches(date, player1_id, player2_id) values(DATE(20231231),@PlayerKey3,@PlayerKey4);