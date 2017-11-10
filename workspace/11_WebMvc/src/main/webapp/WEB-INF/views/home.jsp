<%@page import="com.atsistemas.webmvc.core.dto.PersonaDto"%>

${clave.edad} - ${clave.nombre}

<%=((PersonaDto)request.getAttribute("clave")).getEdad() %>
<%=((PersonaDto)request.getAttribute("clave")).getNombre() %>