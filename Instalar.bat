cd "C:\Program Files\MySQL\MySQL Server 8.0\bin"
mysql -u root -pStudium2019; -e "CREATE DATABASE IF NOT EXISTS tiendecita;"
mysql -u root -pStudium2019; -e "use tiendecita;" -e "DROP TABLE IF EXISTS articulos;" -e "CREATE TABLE articulos (idArticulo int(11) NOT NULL AUTO_INCREMENT, descripcionArticulo varchar(45) NOT NULL, precioArticulo decimal(5,2) NOT NULL, cantidadArticulo int(3) NOT NULL,PRIMARY KEY (idArticulo));" -e "DROP TABLE IF EXISTS ticket;" -e "CREATE TABLE ticket (idTicket int(11) NOT NULL AUTO_INCREMENT, idArticuloFK int(11) NOT NULL, descripcionArticuloFK varchar(45) DEFAULT NULL, fechaTicket date DEFAULT NULL, totalTicket decimal(5,2) DEFAULT NULL, cantidadArtiTick int(11) DEFAULT NULL, PRIMARY KEY (idTicket,idArticuloFK), KEY idArticuloFK_idx (idArticuloFK), CONSTRAINT descripcionArticuloFK1 FOREIGN KEY (idArticuloFK) REFERENCES articulos (idArticulo) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT idArticuloFK2 FOREIGN KEY (idArticuloFK) REFERENCES articulos (idArticulo) ON DELETE CASCADE ON UPDATE CASCADE);"
cls
@echo off
echo --------------------------------------------------     
echo Acceso a MySQL realizado.
echo --------------------------------------------------      
echo Creando base de datos 'tiendecita'...
echo --------------------------------------------------     
echo Base de datos 'tiendecita' creada correctamente.
echo --------------------------------------------------     
echo Base de datos 'tiendecita' seleccionada.
echo --------------------------------------------------     
echo Tabla 'articulos' creada correctamente.
echo --------------------------------------------------     
echo Tabla 'tickets' creada correctamente.
echo --------------------------------------------------    	 
pause
