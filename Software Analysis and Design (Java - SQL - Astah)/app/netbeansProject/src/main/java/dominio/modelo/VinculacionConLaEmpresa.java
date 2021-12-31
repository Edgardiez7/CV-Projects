/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.modelo;

import java.util.Date;


/**
 *
 * @author miris
 */
public class VinculacionConLaEmpresa {
        private Date inicio;
        private TipoDeVinculacion vinculacion;

     public VinculacionConLaEmpresa(Date inicio, TipoDeVinculacion vinculacion){
         this.inicio=inicio;
         this.vinculacion=vinculacion;
     }
     
     public TipoDeVinculacion getVinculacion(){
         return vinculacion;
     }
}
