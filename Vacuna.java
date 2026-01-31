/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.vacunas;

import java.util.Objects;

/**
 *
 * @author luPinheiro
 */
public class Vacuna extends VacunaAutorizacion{

    private String codigo;
    private String nombre;
    private String principioActivo;
    private String farmaceutica;
    private double precioRecomendado;

    public Vacuna() {
		super();
	}

	public Vacuna(String codigo, String nombre, String principioActivo, String farmaceutica, double precioRecomendado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.principioActivo = principioActivo;
        this.farmaceutica = farmaceutica;
        this.precioRecomendado = precioRecomendado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }

    public String getFarmaceutica() {
        return farmaceutica;
    }

    public void setFarmaceutica(String farmaceutica) {
        this.farmaceutica = farmaceutica;
    }

    public double getPrecioRecomendado() {
        return precioRecomendado;
    }

    public void setPrecioRecomendado(double precioRecomendado) {
        this.precioRecomendado = precioRecomendado;
    }


    /*
    • Dos métodos públicos, uno para acceder al atributo del precio y otro para
darle valor. Hacer lo mismo con el resto de los atributos, si procede.
• El código tendrá el siguiente formato:
o Comenzará por la letra V seguida de una vocal en mayúsculas.
o A continuación, tres o cuatro letras minúsculas.
o Finaliza, o con dos números del 4 al 7, o bien con el número 8.
     */
 /*
    Método toString() que mostrará los datos de una vacuna de la siguiente
manera (siempre que esté autorizada por la EMA).
     */
    @Override
    public String toString() {

        if (this.isVacunaAutorizada()) {
            return "Código: " + this.codigo + " Nombre: " + this.nombre + " Principio Activo: " + this.principioActivo
                    + " Farmaceutica: " + this.farmaceutica + " Precio:" + this.precioRecomendado;

        } else {
            return "Código: " + this.codigo + " Nombre: " + this.nombre + " Principio Activo: "
                    + this.principioActivo;

        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime * ((this.codigo == null)?0:this.codigo.hashCode()); //if
        return result;
    }

    @Override
    public boolean equals (Object obj){
        if (this == obj) return true;
        if (Objects.isNull(obj)) return false;
        if (this.getClass() != obj.getClass()) return false;
        Vacuna other = (Vacuna) obj;
        if (this.codigo != other.codigo) return false;
        return true;
    }

}


