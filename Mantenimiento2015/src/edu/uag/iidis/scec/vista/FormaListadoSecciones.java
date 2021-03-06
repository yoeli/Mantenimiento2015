package edu.uag.iidis.scec.vista;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * Form bean para el registro de una nueva persona.
 *
 * @author Victor Ramos
 */
public final class FormaListadoSecciones extends ValidatorForm {

    private Collection roles;
    private int contador;
    private int idTest;

    public void setIdTest(int idTest){
        this.idTest = idTest;
    }

    public int getIdTest(){
        return this.idTest;
    }

    public void setSecciones(Collection roles) {
        this.roles = roles;
        if (roles != null) {
          this.contador = roles.size();
        } else
          this.contador = -1;
    }

    public Collection getSecciones() {
        return (this.roles);
    }
  
    public int getContador() {
        return (this.contador);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        contador=0;
        roles=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
