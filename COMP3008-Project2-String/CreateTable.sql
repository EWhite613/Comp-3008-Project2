Create Table UserAccounts
(
Username Text Not NULL,
Domain Text Not NULL,
Password Text,
Primary Key( Username, Domain)
)

CREATE TABLE Logs
(
Username Text Not NULL,
Mode Text Not NULL,
Event Text Not NULL,
Timestamp Text Not NULL
);