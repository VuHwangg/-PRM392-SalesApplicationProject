USE [PRM392_DB]
GO

INSERT INTO [dbo].[tbl_product]
           ([category]
           ,[color]
           ,[description]
           ,[discount]
           ,[image]
           ,[name]
           ,[price]
           ,[supplier])
     VALUES
           ('true'
           ,'Pink'
           ,'Dù đã được ra mắt từ lâu song điện thoại Apple iPhone 6S Plus 128GB vẫn được nhiều người dùng ưa chuộng nhờ vẻ ngoài quyến rũ, hiệu năng mạnh mẽ và camera chụp ảnh ấn tượng.'
           ,10
           ,'https://images.unsplash.com/photo-1527455102718-437c64ea37ad?auto=format&fit=crop&q=80&w=987&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
           ,'IPhone 6'
           ,4590000
           ,'Apple Inc')
GO


