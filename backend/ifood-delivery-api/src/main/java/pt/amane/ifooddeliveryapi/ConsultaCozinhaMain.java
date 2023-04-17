package pt.amane.ifooddeliveryapi;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = (ApplicationContext) new SpringApplicationBuilder(IfoodDeliveryApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

//        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
//
//        List<Cozinha> cozinhas = cadastroCozinha.lista();
//
//        for (Cozinha cozinha: cozinhas) {
//            System.out.println(cozinha.getNome());
//        }
    }
}
