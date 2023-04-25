package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.UsuarioDTO;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Copia os dados de UsuarioDTO para Usuario..
     * @param usuario
     * @return usuarioDTO
     */
    public UsuarioDTO toModel(Usuario usuario) {
       return modelMapper.map(usuario, UsuarioDTO.class);
    }

    /**
     * Transforma o lista de Usuario para usuarioDTO.
     * @param usuarios
     * @return usuarioDTOs
     */
    public List<UsuarioDTO> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }
}
