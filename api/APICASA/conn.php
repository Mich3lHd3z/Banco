<?php
//!Apartado para la conexión con la base de datos
class conexion extends PDO
{
	private $hostBd='localhost';
	private $NombreBd='datos';
	private $usuarioBd='root';
	private $passwordBd='';
	
	
public function __construct(){
	try{
		parent::__construct('mysql:host=' . $this->hostBd . ';dbname=' . $this->NombreBd . ';charset=utf8', $this->usuarioBd, $this->passwordBd, array (PDO:: ATTR_ERRMODE =>PDO:: ERRMODE_EXCEPTION));
		
	}catch(PDOException $e){
		echo'Error: '.$e->getMessage();
		exit;
    }
    }	
}	
?>