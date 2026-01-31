/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.vacunas;

import java.util.HashMap;

/**
 *
 * @author luPinheiro
 */
public class VacAlmacen {
                          //clave, valor
	private HashMap < String, Vacuna> coleccion = new HashMap <String, Vacuna>();

        
	public VacAlmacen() {
		super();
	}

	public void listarVacunas() {
		if (coleccion.isEmpty()) {
			System.out.println("No esisten vacunas para mostrar");
		} else {
			System.out.println("------Listado de Vacunas:----");                          //conyunto de claves
			for (String clave : coleccion.keySet()) {
				//devuelve una vacuna del listado
				System.out.println(coleccion.get(clave).toString());
                                
			}
			System.out.println("------Fin Listado de Vacunas:----");
		}
                
        }

	public Vacuna buscarVacuna(String codigo) {
            if (this.coleccion.isEmpty()) {
                return null;
            }
            else {
               return this.coleccion.get(codigo);
               
            }
           
	}

	public void agregar(Vacuna v) {
                this.coleccion.put(v.getCodigo(), v);                               
	}

	public void eliminar(String codigo) {
            if (!(this.coleccion.isEmpty())) {             
                this.coleccion.remove(codigo);
            }
	}
        
        public void verVacunasAutorizadas() {
            if (coleccion.isEmpty()) {
			System.out.println("No esisten vacunas para mostrar");
		} else {
			System.out.println("++++++++++++Vacunas autorizadas:");                    //conyunto de claves
			for (String clave : coleccion.keySet()) {
				//devuelve una vacuna del listado
                                if (coleccion.get(clave).isVacunaAutorizada()) {
                                	System.out.println(coleccion.get(clave).toString());
                                }
                                
			}
			System.out.println("+++++++++++++++++++++++++++++++++");  
		}
        }

        public void verVacunasRechazadas() {
            if (coleccion.isEmpty()) {
			System.out.println("No esisten vacunas para mostrar");
		} else {
			System.out.println("++++++++++++Vacunas Rechazadas:");                                            //conyunto de claves
			for (String clave : coleccion.keySet()) {
				//devuelve una vacuna del listado
                                if (coleccion.get(clave).isVacunaRechazada()) {
                                	System.out.println(coleccion.get(clave).toString());
                                }
                                
			}
			System.out.println("+++++++++++++++++++++++++++++++++");  
		}
        }
        
        public void verVacunasPendientes() {
            if (coleccion.isEmpty()) {
			System.out.println("No esisten vacunas para mostrar");
		} else {
			System.out.println("++++++++++++Vacunas Pendientes:");                                            //conyunto de claves
			for (String clave : coleccion.keySet()) {
				//devuelve una vacuna del listado
                                if (coleccion.get(clave).isVacunaPendiente()) {
                                	System.out.println(coleccion.get(clave).toString());
                                }                               
			}
			System.out.println("+++++++++++++++++++++++++++++++++"); 
		}
        }
        
        public void verUltimaFaseInvestigada() {
            if (coleccion.isEmpty()) {
			System.out.println("No esisten vacunas para mostrar");
		} else {
                                                       //conyunto de claves
			for (String clave : coleccion.keySet()) {
				System.out.println("**********************************");  
                System.out.println("Codigo Vacuna:"+coleccion.get(clave).getCodigo());
                System.out.println("Nombre Vacuna:"+coleccion.get(clave).getNombre());
                
                if (coleccion.get(clave).getFasesCompletadas()==0) {
                	System.out.println("Ultima fase investigada:"+coleccion.get(clave).getFasesCompletadas());
                } else {
                	System.out.println("Ultima fase investigada:"+coleccion.get(clave).getFasesCompletadas()+" resultado:"+coleccion.get(clave).getResultadoUltimaFase());
                }
                System.out.println("**********************************");                                
			}
		}
        }
}
