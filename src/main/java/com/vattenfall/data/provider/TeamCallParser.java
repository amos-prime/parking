package com.vattenfall.data.provider;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.vattenfall.data.DataParser;
import com.vattenfall.exceptions.DomainException;
import org.joda.time.DateTime;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static org.jsoup.Jsoup.connect;

/**
 * User: smotynga
 */
@Component
public class TeamCallParser implements DataParser {

    private static final String NAME_CLASS = "name";
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE_REGEX = "[\\s\\xA0]+";
    private static final String COMA = ",";
    private static final String SPACE = " ";

    @Value("#{'${data.absent.days.css.classes}'.split(',')}")
    private List<String> absentInOfficeClasses;
    @Value("${teamcall.url}")
    private String teamCallUrl;

    public List<String> collectAbsentHolders() {
        Element body = body(connect(teamCallUrl));

        Elements tableRows = parseTableRows(body);
        Elements filteredRows = new Elements(filter(tableRows, notInOfficePredicate()));

        return Lists.transform(filteredRows, extractNameFunction());
    }

    private Element body(Connection connect) {
        try {
            return connect.get().body();
        } catch (IOException e) {
            throw new DomainException(e);
        }

    }

    private Elements parseTableRows(Element body) {
        Elements names = body.getElementsByClass(NAME_CLASS);
        return collectElementsParents(names);
    }

    private Predicate<? super Element> notInOfficePredicate() {
        //table includes first column "name" it's + 1
        //and we need column from tomorrow. that's next + 1 :)
        final int columnIndex = new DateTime().getDayOfMonth() + 2;

        return new Predicate<Element>() {
            @Override
            public boolean apply(Element input) {
                Element tomorrowColumn = input.getElementsByTag("td").get(columnIndex);
                return hasAbsentInOfficeClasses(tomorrowColumn);
            }
        };
    }

    private boolean hasAbsentInOfficeClasses(Element column) {
        for (String className : absentInOfficeClasses) {
            if (column.hasClass(className)) {
                return true;
            }
        }
        return false;
    }

    private Function<Element, String> extractNameFunction() {
        return new Function<Element, String>() {
            @Override
            public String apply(Element input) {
                String name = input.getElementsByClass(NAME_CLASS).first().text();
                return name.replaceAll(WHITE_SPACE_REGEX, EMPTY_STRING).replace(COMA, SPACE);
            }
        };
    }

    private Elements collectElementsParents(Elements elements) {
        return new Elements(transform(elements, extractParentFunction()));
    }

    private Function<Element, Element> extractParentFunction() {
        return new Function<Element, Element>() {
            @Override
            public Element apply(Element input) {
                return input.parent();
            }
        };
    }
}
