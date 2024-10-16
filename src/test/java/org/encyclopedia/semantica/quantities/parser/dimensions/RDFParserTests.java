package org.encyclopedia.semantica.quantities.parser.dimensions;

import org.encyclopedia.semantica.quantities.dimension.BaseDimension;
import org.encyclopedia.semantica.quantities.dimension.DerivedDimension;
import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.dimension.Dimensionless;
import org.encyclopedia.semantica.quantities.instances.Dimensions;
import org.encyclopedia.semantica.quantities.io.parser.RDFParser;
import org.junit.jupiter.api.Test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RDFParserTests {
    ClassLoader loader = this.getClass().getClassLoader();

    @Test
    public void testSerialize_BaseDimension() throws IOException {
        // arrange
        File file = new File(loader.getResource("dimensions/length.ttl").getFile());
        String rdf = FileUtils.readFileToString(file, "UTF-8");
        BaseDimension expected = Dimensions.length;

        // act
        Dimension actual = RDFParser.parseDimensions(rdf).get(0).get();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSerialize_Dimensionless() throws IOException {
        // arrange
        File file = new File(loader.getResource("dimensions/angle.ttl").getFile());
        String rdf = FileUtils.readFileToString(file, "UTF-8");
        Dimensionless expected = Dimensions.angle;

        // act
        Dimension actual = RDFParser.parseDimensions(rdf).get(0).get();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSerialize_DerivedDimension() throws IOException {
        // arrange
        File file = new File(loader.getResource("dimensions/current_density.ttl").getFile());
        String rdf = FileUtils.readFileToString(file, "UTF-8");
        DerivedDimension expected = Dimensions.electricCurrentDensity;

        // act
        Dimension actual = RDFParser.parseDimensions(rdf).get(0).get();

        // assert
        assertEquals(expected, actual);
    }
}