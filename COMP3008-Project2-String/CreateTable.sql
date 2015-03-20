Create Table UserAccounts
(
Username Text Not NULL,
Domain Text Not NULL,
Password Text,
Primary Key( Username, Domain)
)


