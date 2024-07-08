package org.encyclopedia.semantica.quantities.io.serializer;

import org.encyclopedia.semantica.quantities.Prefix;
import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.model.Unit;

import java.io.Writer;

public class XmlSerializer implements ISerializer {
    @Override
    public String serialize(Unit unit) {
        return null;
    }

    @Override
    public String serialize(Dimension dimension) {
        return null;
    }

    @Override
    public String serialize(Prefix prefix) {
        return null;
    }

    @Override
    public void serialize(Unit unit, Writer writer) {

    }

    @Override
    public void serialize(Dimension dimension, Writer writer) {

    }

    @Override
    public void serialize(Prefix prefix, Writer writer) {

    }
}
