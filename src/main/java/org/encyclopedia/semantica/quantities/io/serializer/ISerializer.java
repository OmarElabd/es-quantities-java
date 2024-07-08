package org.encyclopedia.semantica.quantities.io.serializer;

import org.encyclopedia.semantica.quantities.Prefix;
import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.model.Unit;

import java.io.Writer;

public interface ISerializer {
    String serialize(Unit unit);

    String serialize(Dimension dimension);

    String serialize(Prefix prefix);

    void serialize(Unit unit, Writer writer);

    void serialize(Dimension dimension, Writer writer);

    void serialize(Prefix prefix, Writer writer);
}
