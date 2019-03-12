package com.threathunter.variable.reduction;

import com.threathunter.common.Identifier;
import com.threathunter.common.NamedType;
import com.threathunter.model.Property;
import com.threathunter.model.PropertyReduction;

import java.util.HashMap;
import java.util.Map;

/**
 * Generate a new property from a {@code LongProperty}.
 *
 * created by www.threathunter.cn
 */
public abstract class LongPropertyReduction extends PropertyReduction {

    // for json
    protected LongPropertyReduction() {}

    public LongPropertyReduction(Property srcProperty, Property destProperty, String type, String method) {
        super(srcProperty, destProperty, type, method);
        if (srcProperty.getType() != NamedType.LONG)
            throw new IllegalArgumentException("the source type is not long");
    }

    @Override
    public Object to_json_object() {
        Map<Object, Object> result = new HashMap<>();
        result.put("type", getType());
        result.put("srcProperty", getSrcProperties().get(0).to_json_object());
        result.put("destProperty", getDestProperty().to_json_object());
        return result;
    }

    public static class LongMaxPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longmax";
        static {
            PropertyReduction.addSubClass(TYPE, LongMaxPropertyReduction.class);
        }

        // for json
        protected LongMaxPropertyReduction() {}

        public LongMaxPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "max");
        }

        public LongMaxPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "max");
        }

        public LongMaxPropertyReduction(Identifier srcID, String srcPropertyName,
                                        Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "max");
        }

        public static LongMaxPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongMaxPropertyReduction(src, dest);
        }

    }

    public static class LongMinPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longmin";
        static {
            PropertyReduction.addSubClass(TYPE, LongMinPropertyReduction.class);
        }

        // for json
        protected LongMinPropertyReduction() {}

        public LongMinPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "min");
        }

        public LongMinPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "min");
        }

        public LongMinPropertyReduction(Identifier srcID, String srcPropertyName,
                                        Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "min");
        }

        public static LongMinPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongMinPropertyReduction(src, dest);
        }
    }

    public static class LongSumPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longsum";
        static {
            PropertyReduction.addSubClass(TYPE, LongSumPropertyReduction.class);
        }

        // for json
        protected LongSumPropertyReduction() {}

        public LongSumPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "sum");
        }

        public LongSumPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "sum");
        }

        public LongSumPropertyReduction(Identifier srcID, String srcPropertyName,
                                        Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "sum");
        }

        public static LongSumPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongSumPropertyReduction(src, dest);
        }
    }

    public static class LongGroupSumPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longgroup_sum";
        private final Property groupProperty;
        static {
            PropertyReduction.addSubClass(TYPE, LongGroupSumPropertyReduction.class);
        }

        public Property getGroupProperty() {
            return this.groupProperty;
        }

        public int getLimit() {
            return 20;
        }

        // for json
        protected LongGroupSumPropertyReduction() {
            this.groupProperty = null;
        }

        public LongGroupSumPropertyReduction(Property srcProperty, Property destProperty, Property groupProperty) {
            super(srcProperty, destProperty, TYPE, "group_sum");
            this.groupProperty = groupProperty;
        }

        public LongGroupSumPropertyReduction(String srcPropertyName, String destPropertyName, String groupPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "group_sum");
            this.groupProperty = Property.buildStringProperty(groupPropertyName);
        }

        public LongGroupSumPropertyReduction(Identifier srcID, String srcPropertyName,
                                        Identifier destID, String destPropertyName, String groupPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "group_sum");
            this.groupProperty = Property.buildStringProperty(srcID, groupPropertyName);
        }

        public static LongGroupSumPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.MAP);
            dest.setSubType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            Property groupProperty = Property.buildStringProperty(src.getIdentifier(), (String) map.get("param"));
            return new LongGroupSumPropertyReduction(src, dest, groupProperty);
        }
    }

    public static class LongCountPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longcount";
        static {
            PropertyReduction.addSubClass(TYPE, LongCountPropertyReduction.class);
        }

        // for json
        protected LongCountPropertyReduction() {}

        public LongCountPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "count");
        }

        public LongCountPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "count");
        }

        public LongCountPropertyReduction(Identifier srcID, String srcPropertyName,
                                          Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "count");
        }

        public static LongCountPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongCountPropertyReduction(src, dest);
        }
    }

    public static class LongGroupCountPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longgroup_count";
        private final Property groupProperty;
        static {
            PropertyReduction.addSubClass(TYPE, LongGroupCountPropertyReduction.class);
        }

        public Property getGroupProperty() {
            return this.groupProperty;
        }

        public int getLimit() {
            return 20;
        }

        // for json
        protected LongGroupCountPropertyReduction() {
            this.groupProperty = null;
        }

        public LongGroupCountPropertyReduction(Property srcProperty, Property destProperty, Property groupProperty) {
            super(srcProperty, destProperty, TYPE, "group_count");
            this.groupProperty = groupProperty;
        }

        public LongGroupCountPropertyReduction(String srcPropertyName, String destPropertyName, String groupPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName),
                    TYPE, "group_count");
            this.groupProperty = Property.buildStringProperty(groupPropertyName);
        }

        public LongGroupCountPropertyReduction(Identifier srcID, String srcPropertyName,
                                             Identifier destID, String destPropertyName, String groupPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "group_count");
            this.groupProperty = Property.buildStringProperty(srcID, groupPropertyName);
        }

        public static LongGroupCountPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.MAP);
            dest.setSubType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            Property groupProperty = Property.buildStringProperty(src.getIdentifier(), (String) map.get("param"));
            return new LongGroupCountPropertyReduction(src, dest, groupProperty);
        }
    }

    public static class LongDistinctCountPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longdistinct_count";
        static {
            PropertyReduction.addSubClass(TYPE, LongDistinctCountPropertyReduction.class);
        }

        // for json
        protected LongDistinctCountPropertyReduction() {}

        public LongDistinctCountPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "distinct_count");
        }

        public LongDistinctCountPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName),
                    TYPE, "distinct_count");
        }

        public LongDistinctCountPropertyReduction(Identifier srcID, String srcPropertyName,
                                                  Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "distinct_count");
        }

        public static LongDistinctCountPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongDistinctCountPropertyReduction(src, dest);
        }
    }

    public static class LongAvgPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longavg";
        static {
            PropertyReduction.addSubClass(TYPE, LongAvgPropertyReduction.class);
        }

        // for json
        protected LongAvgPropertyReduction() {}

        public LongAvgPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "avg");
        }

        public LongAvgPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildDoubleProperty(destPropertyName), TYPE, "avg");
        }

        public LongAvgPropertyReduction(Identifier srcID, String srcPropertyName,
                                        Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildDoubleProperty(destID, destPropertyName),
                    TYPE, "avg");
        }

        public static LongAvgPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.DOUBLE);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongAvgPropertyReduction(src, dest);
        }
    }

    public static class LongFirstPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longfirst";
        static {
            PropertyReduction.addSubClass(TYPE, LongFirstPropertyReduction.class);
        }

        // for json
        protected LongFirstPropertyReduction() {}

        public LongFirstPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "first");
        }

        public LongFirstPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "first");
        }

        public LongFirstPropertyReduction(Identifier srcID, String srcPropertyName,
                                          Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName), TYPE,
                    "first");
        }

        public static LongFirstPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongFirstPropertyReduction(src, dest);
        }
    }

    public static class LongLastPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longlast";
        static {
            PropertyReduction.addSubClass(TYPE, LongLastPropertyReduction.class);
        }

        // for json
        protected LongLastPropertyReduction() {}

        public LongLastPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "last");
        }

        public LongLastPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "last");
        }

        public LongLastPropertyReduction(Identifier srcID, String srcPropertyName,
                                         Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "last");
        }

        public static LongLastPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongLastPropertyReduction(src, dest);
        }
    }

    public static class LongStddevPropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longstddev";
        static {
            PropertyReduction.addSubClass(TYPE, LongStddevPropertyReduction.class);
        }

        // for json
        protected LongStddevPropertyReduction() {}

        public LongStddevPropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "stddev");
        }

        public LongStddevPropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildDoubleProperty(destPropertyName), TYPE, "stddev");
        }

        public LongStddevPropertyReduction(Identifier srcID, String srcPropertyName,
                                           Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildDoubleProperty(destID, destPropertyName),
                    TYPE, "stddev");
        }

        public static LongStddevPropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.DOUBLE);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongStddevPropertyReduction(src, dest);
        }
    }

    public static class LongRangePropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longrange";
        static {
            PropertyReduction.addSubClass(TYPE, LongRangePropertyReduction.class);
        }

        // for json
        protected LongRangePropertyReduction() {}

        public LongRangePropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "range");
        }

        public LongRangePropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildLongProperty(destPropertyName), TYPE, "range");
        }

        public LongRangePropertyReduction(Identifier srcID, String srcPropertyName,
                                          Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildLongProperty(destID, destPropertyName),
                    TYPE, "range");
        }

        public static LongRangePropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongRangePropertyReduction(src, dest);
        }
    }

    public static class LongAmplitudePropertyReduction extends LongPropertyReduction {
        public static final String TYPE = "longamplitude";
        static {
            PropertyReduction.addSubClass(TYPE, LongAmplitudePropertyReduction.class);
        }

        // for json
        protected LongAmplitudePropertyReduction() {}

        public LongAmplitudePropertyReduction(Property srcProperty, Property destProperty) {
            super(srcProperty, destProperty, TYPE, "amplitude");
        }

        public LongAmplitudePropertyReduction(String srcPropertyName, String destPropertyName) {
            super(Property.buildLongProperty(srcPropertyName), Property.buildDoubleProperty(destPropertyName), TYPE, "amplitude");
        }

        public LongAmplitudePropertyReduction(Identifier srcID, String srcPropertyName,
                                              Identifier destID, String destPropertyName) {
            super(Property.buildLongProperty(srcID, srcPropertyName), Property.buildDoubleProperty(destID, destPropertyName),
                    TYPE, "amplitude");
        }

        public static LongAmplitudePropertyReduction from_json_object(Object obj) {
            Map<Object, Object> map = (Map<Object, Object>)obj;

            Property dest = Property.from_json_object(map.get("destProperty"));
            dest.setType(NamedType.LONG);
            Property src = Property.from_json_object(map.get("srcProperty"));
            return new LongAmplitudePropertyReduction(src, dest);
        }
    }
}
