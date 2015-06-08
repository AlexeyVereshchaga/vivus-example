package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Parser;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class ParserRemote extends BaseModel {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Parser parser;
    private String text;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
