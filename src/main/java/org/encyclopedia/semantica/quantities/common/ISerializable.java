package org.encyclopedia.semantica.quantities.common;

import org.encyclopedia.semantica.quantities.io.serializer.ISerializer;

import java.io.Writer;

public interface ISerializable {
    String serialize();
    String serialize(ISerializer serializer);
    void serialize(ISerializer serialize, Writer writer);
}
