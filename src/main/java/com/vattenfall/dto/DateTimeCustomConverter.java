package com.vattenfall.dto;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;

/**
 * Created by amoss on 27.03.14.
 */
public class DateTimeCustomConverter extends DozerConverter<DateTime, DateTime> {
    public DateTimeCustomConverter() {
        super(DateTime.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(final DateTime source, final DateTime destination) {
        return source;
    }

    @Override
    public DateTime convertFrom(final DateTime source, final DateTime destination) {
        return source;
    }

}
