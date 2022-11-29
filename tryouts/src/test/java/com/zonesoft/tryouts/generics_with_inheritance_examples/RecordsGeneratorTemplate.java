package com.zonesoft.tryouts.generics_with_inheritance_examples;

import static com.zonesoft.utils.data_generators.Generator.generateRandomInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class RecordsGeneratorTemplate<G extends IRecordGenerator<G, T>, T> {
	//Defaults
	private static final int MIN_RECORDS_DEFAULT = 2;
	private static final int MAX_RECORDS_DEFAULT = 10;
	private static final boolean AUTO_GENERATE_ID_DEFAULT = true;
	
	private int minimumNumberOfRecords = MIN_RECORDS_DEFAULT;
	private int maximumNumberOfRecords = MAX_RECORDS_DEFAULT;
	private boolean autoGenerateId = AUTO_GENERATE_ID_DEFAULT;
	private String[] providedIds = null;
	
	public RecordsGeneratorTemplate<G, T> minRecords(int minimumNumberOfRecords) {
		this.minimumNumberOfRecords = minimumNumberOfRecords;
		return this;
	}
	
	public RecordsGeneratorTemplate<G, T> maxRecords(int maximumNumberOfRecords) {
		this.maximumNumberOfRecords = maximumNumberOfRecords;
		return this;
	}
	
	public RecordsGeneratorTemplate<G, T> id(boolean autoGenerateId) {
		this.autoGenerateId = autoGenerateId;
		return this;
	}

	public RecordsGeneratorTemplate<G, T> id(String ...ids) {
		this.providedIds = ids;
		return this;
	}
	
	public List<T> generate(Supplier<G> supplier){
		List<T> records = null;
		if (Objects.isNull(providedIds)) {
			records = generateRecords(supplier);
		}else {
			records = generateRecordsFromProvidedIds(supplier);
		}		
		return records;
	}

	private List<T> generateRecordsFromProvidedIds(Supplier<G> supplier) {
		int listSize = (providedIds.length <= maximumNumberOfRecords)? providedIds.length : maximumNumberOfRecords;
		List<T> records = new ArrayList<>();		
		for(int j=0; j < listSize ; j++) {
			records.add(supplier.get().withDefaults().id(providedIds[j]).generate());
		}
		return records;
	}

	private List<T> generateRecords(Supplier<G> supplier) {
		int listSize = generateRandomInt(minimumNumberOfRecords, maximumNumberOfRecords);
		List<T> records = new ArrayList<>();		
		for(int j=0; j < listSize ; j++) {
			G generator = supplier.get();
			T record = generator.withDefaults().generate();
			if (!autoGenerateId) {
				generator.id(null);
			}
			records.add(record);
		}
		return records;
	}
}
