package edu.javacourse.first.domain;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType (XmlAccessType.FIELD)
public class PersonChild extends Person implements Serializable {

    @XmlElement (name = "number", required = true)
    private String birthDocument;

    public String getBirthDocument() {
        return birthDocument;
    }

    public void setBirthDocument(String birthDocument) {
        this.birthDocument = birthDocument;
    }
}
