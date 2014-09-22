/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author mauro di
 */
public interface Saldable {
    /*
    INTERFACE CALCULA AUTOMATICAMENTE EL SALDO ACTUAL, EN LOS INQUILINOS CON EL INTERES AL MOMENTO DE MOSTRAR
    EN LOS PROPIETARIOS EL SALDO ADDEUDADO
    PARA LAS PROPIEDADES IGUAL
    PARA LOS CONTRATOS
    
    */
    public Double calcularSaldoActual(Integer id);//DEVUELVE EL MONTO DEL SALDO ACTUAL
    public Boolean ajustarSaldo(Integer id,Double movimiento);//GENERA UN MOVIMIENTO DE AJUSTE
}
