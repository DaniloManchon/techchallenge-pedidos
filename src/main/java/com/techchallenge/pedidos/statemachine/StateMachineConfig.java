package com.techchallenge.pedidos.statemachine;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Log4j2
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<EstadoPedido, EventoPedido> {

    //Configurar o estado inicial e carregar os outros estados
    @Override
    public void configure(StateMachineStateConfigurer<EstadoPedido, EventoPedido> states) throws Exception {
        states.withStates()
                .initial(EstadoPedido.A_PAGAR)
                .states(EnumSet.allOf(EstadoPedido.class))
                .end(EstadoPedido.FINALIZADO);
    }

    //Definir as transicoes
    //de .source para .target apos .event
    @Override
    public void configure(StateMachineTransitionConfigurer<EstadoPedido, EventoPedido> transitions) throws Exception {
        transitions.withExternal()
                .source(EstadoPedido.A_PAGAR).target(EstadoPedido.A_PAGAR).event(EventoPedido.A_PAGAR)
                .and().withExternal()
                .source(EstadoPedido.A_PAGAR).target(EstadoPedido.PAGO).event(EventoPedido.PAGAR)
                .and().withExternal()
                .source(EstadoPedido.PAGO).target(EstadoPedido.RECEBIDO).event(EventoPedido.RECEBER)
                .and().withExternal()
                .source(EstadoPedido.RECEBIDO).target(EstadoPedido.EM_PREPARACAO).event(EventoPedido.PREPARAR)
                .and().withExternal()
                .source(EstadoPedido.EM_PREPARACAO).target(EstadoPedido.PRONTO).event(EventoPedido.APRONTAR)
                .and().withExternal()
                .source(EstadoPedido.PRONTO).target(EstadoPedido.FINALIZADO).event(EventoPedido.ENTREGAR);
    }

    //Startup automatico + incluir listener
    @Override
    public void configure(StateMachineConfigurationConfigurer<EstadoPedido, EventoPedido> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(listener());
    }

    //implementar o listener
    @Bean
    public StateMachineListener<EstadoPedido, EventoPedido> listener() {
        return new StateMachineListenerAdapter<EstadoPedido, EventoPedido>() {
            @Override
            public void stateChanged(State<EstadoPedido, EventoPedido> from, State<EstadoPedido, EventoPedido> to) {
                log.info("Estado do pedido mudou de " + from.getId() + " para " + to.getId());
            }
        };
    }
    //https://www.youtube.com/watch?v=A-dVgRV5-Bw
    //https://medium.com/nstech/spring-state-machine-como-op%C3%A7%C3%A3o-97144586bf48
}

