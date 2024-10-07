import org.apache.commons.lang3.StringUtils;
import org.encyclopedia.semantica.quantities.common.ISerializable;
import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.instances.Dimensions;
import org.encyclopedia.semantica.quantities.instances.SIDerivedUnits;
import org.encyclopedia.semantica.quantities.io.RDFFormat;
import org.encyclopedia.semantica.quantities.io.serializer.RDFSerializer;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class CodeSerializer {
    public static void main(String[] args) throws IOException {
        String outputPath = "..\\src\\main\\resources\\units";

        List<Dimension> dimensions = Dimensions.All;
        List<Unit> derivedSIUnits = SIDerivedUnits.All;

        StringWriter writerSI = new StringWriter();

        RDFSerializer serializer = new RDFSerializer(RDFFormat.TURTLE);
        String fileDimensions = serialize(dimensions);
        serializer.serialize(derivedSIUnits, writerSI);
        String fileSIUnits = writerSI.toString();

        File file = new File(outputPath, "SIDerived.ttl");
        FileUtils.writeStringToFile(file, fileSIUnits, "UTF-8");
    }

    private static String serialize(List<? extends ISerializable> objects) {
        List<String> resources = new ArrayList<>();

        for (ISerializable object : objects) {
            resources.add(object.serialize());
        }

        String fileStr = StringUtils.join(resources, "\n");

        return fileStr;
    }
}
