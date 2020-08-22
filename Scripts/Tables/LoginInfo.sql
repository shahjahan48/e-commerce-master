IF NOT EXISTS(SELECT * FROM sys.objects WHERE object_id=OBJECT_ID('LoginInfo'))
BEGIN
	CREATE TABLE [dbo].LoginInfo(
		Id BIGINT IDENTITY(1,1) NOT NULL,
		Username NVARCHAR(128) NOT NULL,
		Series VARCHAR(256) NOT NULL,
		Token VARCHAR(128) NOT NULL,
		LastUsed DATETIME NOT NULL
	)
END