-- Insert of data in staff and persons
insert into persons(person_first_name, person_last_name, person_nickname) values('Fabian', 'Knopf', 'Fabian');
SELECT @Key := @@IDENTITY;
INSERT INTO staff VALUES (@Key);

insert into persons(person_first_name, person_last_name, person_nickname) values('Linus', 'Axelsson', 'Linus');
SELECT @Key := @@IDENTITY;
INSERT INTO staff VALUES (@Key);

insert into persons(person_first_name, person_last_name, person_nickname) values('Johanna', 'Selander', 'Johanna');
SELECT @Key := @@IDENTITY;
INSERT INTO staff VALUES (@Key);

insert into persons(person_first_name, person_last_name, person_nickname) values('Morten', 'Sandgrav', 'Morten');
SELECT @Key := @@IDENTITY;
INSERT INTO staff VALUES (@Key);

-- Insert of data in players and persons
insert into persons(person_first_name, person_last_name, person_nickname) values('Robin', 'Andersson', 'OneHandedOnly');
SELECT @Key := @@IDENTITY;
INSERT INTO players(person_person_id) VALUES (@Key);

insert into persons(person_first_name, person_last_name, person_nickname) values('Robert', 'Fridström', 'Lem0naZ');
SELECT @Key := @@IDENTITY;
INSERT INTO players(person_person_id) VALUES (@Key);

insert into persons(person_first_name, person_last_name, person_nickname) values('Daniel', 'Hedström', 'Drome');
SELECT @Key := @@IDENTITY;
INSERT INTO players(person_person_id) VALUES (@Key);

insert into persons(person_first_name, person_last_name, person_nickname) values('Andreas', 'Mellin', 'Andruzo');
SELECT @Key := @@IDENTITY;
INSERT INTO players(person_person_id) VALUES (@Key);