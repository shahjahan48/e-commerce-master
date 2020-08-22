IF NOT EXISTS(SELECT * FROM sys.objects WHERE object_id=OBJECT_ID('UserRoles'))
BEGIN
	CREATE TABLE [dbo].UserRoles(
		Id BIGINT IDENTITY(1,1) NOT NULL,
		UserId BIGINT NOT NULL,
		RoleId BIGINT NOT NULL,
		CreatedDate DATETIME NOT NULL,

		CONSTRAINT [PK_UserRoles] PRIMARY KEY(Id),
		CONSTRAINT [FK_UserRoles_Users] FOREIGN KEY (UserId) REFERENCES Users(Id),
		CONSTRAINT [FK_UserRoles_Roles] FOREIGN KEY (RoleId) REFERENCES Roles(Id)
	)
END