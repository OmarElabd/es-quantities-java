package org.encyclopedia.semantica.quantities.io.parser;

import org.encyclopedia.semantica.quantities.dimension.BaseDimension;
import org.encyclopedia.semantica.quantities.dimension.DerivedDimension;
import org.encyclopedia.semantica.quantities.dimension.Dimension;
import org.encyclopedia.semantica.quantities.dimension.Dimensionless;
import org.encyclopedia.semantica.quantities.instances.Dimensions;
import org.encyclopedia.semantica.quantities.io.RDFModel;
import org.encyclopedia.semantica.quantities.model.Unit;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class RDFParser {
    private RDFParser() {}

    /**
     *
     */
    public static List<Unit> parseUnits(String rdf) {
        List<Unit> units = new ArrayList<>();

        InputStream stream = new ByteArrayInputStream(rdf.getBytes());

        Model model = ModelFactory.createDefaultModel();
        model.read(stream, null, "TTL");

        for (ResIterator iterator = model.listResourcesWithProperty(RDF.type); iterator.hasNext(); ) {
            Resource resource = iterator.next();

            String name = resource.getProperty(RDFS.label).getObject().asLiteral().getString();
            String symbol = resource.getProperty(RDFModel.symbol).getObject().asLiteral().getString();
            String unicodeSymbol = resource.getProperty(RDFModel.symbolUnicode).getObject().asLiteral().getString();
            String typeURI = resource.getProperty(RDF.type).getObject().asResource().getLocalName();

        }

        return units;
    }

    public static List<Optional<Dimension>> parseDimensions(String rdf) {
        List<Optional<Dimension>> dimensions = new ArrayList<>();

        InputStream stream = new ByteArrayInputStream(rdf.getBytes());

        Model model = ModelFactory.createDefaultModel();
        model.read(stream, "", "TTL");

        for (ResIterator iterator = model.listResourcesWithProperty(RDF.type); iterator.hasNext(); ) {
            Resource resource = iterator.next();

            Optional<Dimension> parseResult = parseDimension(resource);

            dimensions.add(parseResult);
        }

        return dimensions;
    }

    /**
     * Attempts to parse a dimension from a Jena Resource object.
     * Will first check if the resource name is present in the existing models,
     * If so it will return that instance, if not it will create a new dimension object.
     */
    private static Optional<Dimension> parseDimension(Resource resource) {
        // Parse values from RDF Jena Model
        String resourceName = resource.getLocalName();
        String name = resource.getProperty(RDFS.label).getObject().asLiteral().getString();
        String symbol = resource.getProperty(RDFModel.symbol).getObject().asLiteral().getString();
        String unicodeSymbol = resource.getProperty(RDFModel.symbolUnicode).getObject().asLiteral().getString();
        String typeURI = resource.getProperty(RDF.type).getObject().asResource().getLocalName();

        // Check if dimension already exists
        Optional<Dimension> result = Dimensions.getByResourceName(resourceName);

        if (result.isPresent()) {
            return result;
        }

        // If dimension doesn't already exist then create it
        if (typeURI.equals("Dimensionless")) {
            Dimensionless dimension = new Dimensionless(name);
            return Optional.of(dimension);
        } else if (typeURI.equals("DerivedDimension")) {
            Map<BaseDimension, Integer> bases = DimensionParser.parseSymbol(symbol);
            DerivedDimension dimension = new DerivedDimension(name, bases);
            return Optional.of(dimension);
        } else {
            return Optional.empty();
        }
    }
}
