package com.onlinestore.onlinestore.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Component
public class PriceFormatter  implements Formatter<BigDecimal> {


    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(BigDecimal object, Locale locale) {
        locale = new Locale("ro", "RO");
        return NumberFormat.getCurrencyInstance(locale).format(object);
    }
}
