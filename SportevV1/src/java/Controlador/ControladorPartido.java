package Controlador;

import Modelo.Bean.BeanCampeonato;
import Modelo.Bean.BeanDatosPersona;
import Modelo.Bean.BeanEquipo;
import Modelo.Bean.BeanPartido;
import Modelo.Bean.BeanUsuariosLogin;
import Modelo.Dao.DaoCampeonato;
import Modelo.Dao.DaoEquipo;
import Modelo.Dao.DaoPartido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Capera
 */
@ManagedBean
@SessionScoped
public class ControladorPartido {

    /**
     * Creates a new instance of ControladorPartido
     */
    private BeanPartido BPar = new BeanPartido();
    private List<BeanPartido> consPar;
    private List<BeanPartido> consEqui;
    private List<BeanDatosPersona> consultarCorreos;
    private BeanPartido partidoselecionado;
    BeanUsuariosLogin usuario;
    private boolean deshabilitarvistapartido;
    public HttpSession idpartido = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public void listarPorPart(String Equi1, String Equi2, int id) {
        idpartido.setAttribute("idPartido", id);
        DaoPartido DPart = new DaoPartido();
        consEqui = DPart.consultarEquiPorPartido(Equi1, Equi2);
    }

    public void cerrarPartido() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("idPartido");
    }

    // Eliminar partido

    public void eliminar(int id) {
        DaoPartido dapartido = new DaoPartido();
        if (dapartido.Eliminar(id)) {
        } else {
            DaoPartido Dpa = new DaoPartido();
            consPar = Dpa.listarpartido();
        }

    }
// registrar partido

    public String registrarPart(int id, int id1) {
        String respuesta = "";
        DaoPartido DPar = new DaoPartido();
        if (BPar.getEquipo1().equals(BPar.getEquipo2())) {
            FacesMessage mensaje = new FacesMessage("Los Equipos deben ser distintos");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        } else {
            if (DPar.registrarPartido(this.BPar)) {
                DaoPartido Dpa = new DaoPartido();
                consPar = Dpa.listarpartido();
                consultarCorreos(id, id1);

            } else {
                FacesMessage mensaje = new FacesMessage("No Se pudo Registrar");
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            }
        }

        return respuesta;
    }

    public void consultarCorreos(int id, int id1) {

        DaoPartido dpart = new DaoPartido();
        consultarCorreos = dpart.consultarCorreos(id, id1);
        String[] correos = new String[consultarCorreos.size()];
        if (consultarCorreos.isEmpty()) {
            System.out.println("Error de lista");
        } else {
            int count = 0;
            for (BeanDatosPersona datos : consultarCorreos) {
                correos[count] = datos.getEmail();
                count++;
            }
            mandarCorreo(correos);
        }
    }

    public void mandarCorreo(String[] correos) {
        // El correo gmail de envío
        String correoEnvia = "sportev2017@gmail.com";
        String claveCorreo = "sportev2017";

        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEnvia, claveCorreo);
            }
        };

        // Obtener la sesion
        Session session = Session.getInstance(properties, auth);

        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);

            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Dato Java"));

            InternetAddress[] recipientAddress = new InternetAddress[correos.length];
            int counter = 0;
            for (String lista : correos) {
                recipientAddress[counter] = new InternetAddress(lista);
                counter++;
            }

            // Agregar los destinatarios al mensaje
            mimeMessage.addRecipients(Message.RecipientType.TO,
                    recipientAddress);

            // Agregar el asunto al correo
            mimeMessage.setSubject("Sportev Enviando Correo.");
           // mimeMessage.setText("Siguiendo el Tutorial de datojava.blogspot.com envío el correo.");

            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(" Usted Tiene Partido Revice la pagina http://localhost:8080/SportevV1/ ");

            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            // Agregar el multipart al cuerpo del mensaje
            mimeMessage.setContent(multipart);

            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Correo enviado");
    }

    // modifico El Partido

    public String modificar() {
        String respuesta = "";
        DaoPartido DPar = new DaoPartido();
        if (DPar.modificar(this.BPar)) {
            DaoPartido Dpa = new DaoPartido();
            consPar = Dpa.listarpartido();
        } else {
            FacesMessage mensaje = new FacesMessage("Accion no realizada");
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            respuesta = "/RegistrarPartido.xhtml";
        }
        return respuesta;
    }

    public List<SelectItem> consultarPartido() {
        DaoEquipo Dpar = new DaoEquipo();
        List<BeanEquipo> listar;
        List<SelectItem> listarpa = new ArrayList();
        listar = Dpar.listarEquipo();
        listar.stream().forEach((s) -> {
            listarpa.add(new SelectItem(s.getIdEquipo(), s.getNombreEquipo()));
        });
        return listarpa;
    }

    public List<SelectItem> consultarCamponatos() throws NamingException, SQLException {
        DaoCampeonato dec = new DaoCampeonato();
        List<BeanCampeonato> listar;
        List<SelectItem> listarcap = new ArrayList();
        listar = dec.listacampe();
        listar.stream().forEach((s) -> {
            listarcap.add(new SelectItem(s.getIdCampeonato(), s.getNombre()));
        });
        return listarcap;
    }

    // Edito los datos de la tabla partido para llenar el formulario par hacer la modificacion 

    public void asignarEditar(BeanPartido usu) {
        deshabilitarvistapartido = true;
        System.out.println("Asignar Editar");
        this.BPar = usu;
    }

    public void consPart(int id) {
        DaoPartido DPar = new DaoPartido();
        List<BeanPartido> listar;
        listar = DPar.consultar(id);
        for (BeanPartido Bpar : listar) {
            BPar = Bpar;
        }
    }

    public void consPartidosSegCam(int id) {
        deshabilitarvistapartido = false;
        partidoselecionado = new BeanPartido();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (BeanUsuariosLogin) session.getAttribute("user");
        try {
            deshabilitarvistapartido = false;
            DaoPartido Dpar = new DaoPartido();
            consPar = Dpar.listarpartidoSegCamp(id);

        } catch (Exception e) {
        }
    }

    public ControladorPartido() {
//        deshabilitarvistapartido = false;
//        partidoselecionado = new BeanPartido();
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        usuario = (BeanUsuariosLogin) session.getAttribute("user");
//        try {
//             deshabilitarvistapartido = false;
//            DaoPartido Dpar = new DaoPartido();
//            consPar = Dpar.listarpartido();
//
//        } catch (Exception e) {
//        }

    }

    public List<BeanPartido> getConsEqui() {
        return consEqui;
    }

    public void setConsEqui(List<BeanPartido> consEqui) {
        this.consEqui = consEqui;
    }

    public BeanPartido getPartidoselecionado() {
        return partidoselecionado;
    }

    public void setPartidoselecionado(BeanPartido partidoselecionado) {
        this.partidoselecionado = partidoselecionado;
    }

    public BeanUsuariosLogin getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuariosLogin usuario) {
        this.usuario = usuario;
    }

    public BeanPartido getBpar() {
        return BPar;
    }

    public void setBpar(BeanPartido Bpar) {
        this.BPar = Bpar;
    }

    public List<BeanPartido> getConsPar() {
        return consPar;
    }

    public void setConsPar(List<BeanPartido> consPar) {
        this.consPar = consPar;
    }

    public boolean isDeshabilitarvistapartido() {
        return deshabilitarvistapartido;
    }

    public void setDeshabilitarvistapartido(boolean deshabilitarvistapartido) {
        this.deshabilitarvistapartido = deshabilitarvistapartido;
    }

}
