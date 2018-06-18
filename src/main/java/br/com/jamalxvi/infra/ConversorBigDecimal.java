package br.com.jamalxvi.infra;

import java.math.BigDecimal;

import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.BigDecimalConverter;

@SuppressWarnings("deprecation")
@Specializes
@Convert(BigDecimal.class)
public class ConversorBigDecimal extends BigDecimalConverter {
	@Override
    public BigDecimal convert(String value, Class<? extends BigDecimal> type) {
        // seu código aqui
		if (value.equals("")) {
			return new BigDecimal(0);
		}
		BigDecimal decimal = new BigDecimal(value);
        return decimal;
    }
}
