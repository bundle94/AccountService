IF OBJECT_ID('[dbo].[customer]', 'U') IS NOT NULL
DROP TABLE [dbo].[customer]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[customer]
(
    [id] INT NOT NULL IDENTITY(1,1) PRIMARY KEY, -- Primary Key column
    [name] VARCHAR(50) NOT NULL,
    [surname] VARCHAR(50) NOT NULL
    -- Specify more columns here
);
GO

-- Create a new table called '[Account]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[transactions]', 'U') IS NOT NULL
DROP TABLE [dbo].[transactions]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[transactions]
(
    [id] INT NOT NULL IDENTITY(1,1) PRIMARY KEY, -- Primary Key column
    [account_id] INT NOT NULL,
    [amount] DECIMAL(12,2) NULL,
    [transaction_date] DATETIME2 NOT NULL
    -- Specify more columns here
);
GO

-- Create a new table called '[Account]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[account]', 'U') IS NOT NULL
DROP TABLE [dbo].[account]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[account]
(
    [id] INT NOT NULL IDENTITY(1,1) PRIMARY KEY, -- Primary Key column
    [customer_id] INT NOT NULL,
    [balance] DECIMAL(12,2) NULL,
    [created_at] DATETIME2 NOT NULL
    -- Specify more columns here
);
GO


INSERT INTO [dbo].[customer]
( -- Columns to insert data into
 [name], [surname]
)
VALUES
( -- First row: values for the columns in the list above
 'Michael', 'Akobundu'
)
-- Add more rows here
GO