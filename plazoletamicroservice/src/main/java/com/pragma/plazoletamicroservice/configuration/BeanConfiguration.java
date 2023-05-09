package com.pragma.plazoletamicroservice.configuration;

import com.pragma.plazoletamicroservice.adapters.driven.jpa.mysql.adapter.RestauranteMysqlAdapter;
import com.pragma.plazoletamicroservice.adapters.driven.jpa.mysql.mapper.IRestauranteEntityMapper;
import com.pragma.plazoletamicroservice.adapters.driven.jpa.mysql.repository.IRestauranteRepository;
import com.pragma.plazoletamicroservice.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservice.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservice.domain.usecase.RestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(restaurantePersistencePort());
    }

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestauranteMysqlAdapter(restauranteRepository,restauranteEntityMapper);
    }
}
