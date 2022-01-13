/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Oscar Rodenas
 * Created: 11-ene-2022
 */

CREATE TABLE TAREA (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NOMBRE VARCHAR(50) NOT NULL,
    CONTADOR INTEGER,
    PRIMARY KEY (ID)
);