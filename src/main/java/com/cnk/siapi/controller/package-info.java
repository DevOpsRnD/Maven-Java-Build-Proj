@XmlSchema(
    namespace="http://www.coxandkings.com/scota",
    elementFormDefault=XmlNsForm.QUALIFIED,
    xmlns={
        @XmlNs(prefix="ns",
            namespaceURI="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0")
    }
)
@XmlAccessorType(XmlAccessType.FIELD)
package com.cnk.siapi.controller;
 
import javax.xml.bind.annotation.*;