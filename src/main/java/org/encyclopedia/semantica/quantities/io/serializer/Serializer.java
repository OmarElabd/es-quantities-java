package org.encyclopedia.semantica.quantities.io.serializer;

import org.encyclopedia.semantica.quantities.io.ReflectionUtil;
import org.encyclopedia.semantica.quantities.io.annotations.Element;
import org.encyclopedia.semantica.quantities.io.annotations.Attribute;
import org.apache.jena.rdf.model.*;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;

public class Serializer {
    public static String serialize(Object objectToSerialize) {
        if (objectToSerialize.getClass().isAnnotationPresent(Element.class)) {
            Element entityAnnotation = objectToSerialize.getClass().getAnnotation(Element.class);

            Model model = ModelFactory.createDefaultModel();
            Resource resource = model.createResource(entityAnnotation.value());

            List<Field> fields = ReflectionUtil.getAllFields(objectToSerialize.getClass());

            for (Field field : fields) {
                if (field.isAnnotationPresent(Attribute.class)) {
                    Attribute propertyAnnotation = field.getAnnotation(Attribute.class);

                    if (field.getType().isPrimitive()) {
                        try {
                            Property property = ResourceFactory.createProperty("http://test/", propertyAnnotation.value());
                            resource.addProperty(property, field.get(objectToSerialize).toString());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }
            }

            StringWriter out = new StringWriter();
            model.write(out, "RDF/XML");

            return out.toString();
        } else {
            throw new IllegalArgumentException("Cannot serialize object with no @RDFEntity attribute");
        }
    }
}
