package br.com.poc.processingservice.shared.config;


import br.com.poc.processingservice.application.domain.Transaction;
import br.com.poc.processingservice.application.domain.TransactionProcess;
import br.com.poc.processingservice.infrastructure.entity.TransactionEntity;
import br.com.poc.processingservice.infrastructure.entity.TransactionProcessEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		// Configuração global
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true);

		/*// Mapeamento personalizado para TransactionEntity -> Transaction
		mapper.createTypeMap(TransactionEntity.class, Transaction.class)
				.setPostConverter(context -> {
					TransactionEntity source = context.getSource();
					Transaction destination = context.getDestination();
					return destination;
				});

		// Mapeamento personalizado para Transaction -> TransactionEntity
		mapper.createTypeMap(Transaction.class, TransactionEntity.class)
				.setPostConverter(context -> {
					Transaction source = context.getSource();
					TransactionEntity destination = context.getDestination();
					return destination;
				});

		// Mapeamento personalizado para TransactionProcessEntity -> TransactionProcess
		mapper.createTypeMap(TransactionProcessEntity.class, TransactionProcess.class)
				.setPostConverter(context -> {
					TransactionProcessEntity source = context.getSource();
					TransactionProcess destination = context.getDestination();
					return destination;
				});

		// Mapeamento personalizado para TransactionProcess -> TransactionProcessEntity
		mapper.createTypeMap(TransactionProcess.class, TransactionProcessEntity.class)
				.setPostConverter(context -> {
					TransactionProcess source = context.getSource();
					TransactionProcessEntity destination = context.getDestination();
					return destination;
				});*/

		return mapper;
	}
}