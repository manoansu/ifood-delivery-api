package pt.amane.ifooddeliveryapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.amane.ifooddeliveryapi.api.model.modeldto.inputData.UsuarioInputData;
import pt.amane.ifooddeliveryapi.domain.entities.Restaurante;
import pt.amane.ifooddeliveryapi.domain.entities.Usuario;

@Component
public class UsuarioInputDataDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    /**
     * Retorna uma nova instancia do tipo usuario recebido da propriedade UsuarioInputData.
     * @param usuarioInputData
     * @return usuario
     */
    public Usuario toDomainObject(UsuarioInputData usuarioInputData) {
       return modelMapper.map(usuarioInputData, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInputData usuarioInputData, Usuario usuario) {
        // Para evitar exception quando queremos atualizar o id da Restaurante existente na classe Usuario para outro id.
        // vamos te que instancia restaurante dentro do set restaurante.
        usuario.setRestaurante(new Restaurante());
        modelMapper.map(usuarioInputData, usuario);
    }

}
