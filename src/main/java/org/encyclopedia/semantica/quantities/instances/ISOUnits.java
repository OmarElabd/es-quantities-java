package org.encyclopedia.semantica.quantities.instances;

import org.encyclopedia.semantica.quantities.model.PrefixableUnit;
import org.encyclopedia.semantica.quantities.model.Unit;

import java.util.Arrays;
import java.util.List;

public final class ISOUnits {
    private ISOUnits() {}

    public static void initialize() {}

    public static final PrefixableUnit bit = new PrefixableUnit("bit", "bits", "b", Dimensions.informationEntropy,
                                                                Prefixes.Binary);

    public static final PrefixableUnit bytes = new PrefixableUnit("byte", "bytes", "B", Dimensions.informationEntropy,
                                                                  Prefixes.Binary);

    // public static final DivisionDerivedUnit bitsPerSecond = new DivisionDerivedUnit(bit, second, Dimensions.informationTransfer);
    // public static final DivisionDerivedUnit bytesPerSecond = new DivisionDerivedUnit(bytes, second, Dimensions.informationTransfer);

    public static final List<Unit> All = Arrays.asList(bit, bytes);
}
