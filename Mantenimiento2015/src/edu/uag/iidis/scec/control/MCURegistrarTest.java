package edu.uag.iidis.scec.control;

import edu.uag.iidis.scec.vista.*;
import edu.uag.iidis.scec.modelo.*;
import edu.uag.iidis.scec.servicios.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;



public final class MCURegistrarTest 
        extends MappingDispatchAction {

    private Log log = LogFactory.getLog(MCURegistrarUsuario.class);


    public ActionForward solicitarRegistroTest(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistroTest");
        }

        return (mapping.findForward("exito"));
    }



    public ActionForward procesarRegistroTest(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        if (log.isDebugEnabled()) {
            log.debug(">procesarRegistroTest");
        }

        // Verifica si la acci�n fue cancelada por el usuario
        if (isCancelled(request)) {
            if (log.isDebugEnabled()) {
                log.debug("<La acci�n fue cancelada");
            }
            return (mapping.findForward("cancelar"));
        }

        
        // Se obtienen los datos para procesar el registro
        FormaNuevoTest forma = (FormaNuevoTest)form;

        Test rol = new Test(forma.getNombre(),
                          forma.getDescripcion());

        ManejadorTests mr = new ManejadorTests();
        int resultado = mr.crearTest(rol);

        ActionMessages errores = new ActionMessages();
        switch (resultado) {
            case 0:   
                return (mapping.findForward("exito"));

            case 1:
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.nombreTestYaExiste",
                                               forma.getNombre()));                
                saveErrors(request, errores);
                return (mapping.getInputForward());

            case 3:
                log.error("Ocurri� un error de infraestructura");
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.infraestructura"));                
                saveErrors(request, errores);
                return (mapping.getInputForward());

            default:
                log.warn("ManejadorUsuario.crearUsuario regres� reultado inesperado");
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.infraestructura"));                
                saveErrors(request, errores);
                return (mapping.getInputForward());
        }
    }

}

