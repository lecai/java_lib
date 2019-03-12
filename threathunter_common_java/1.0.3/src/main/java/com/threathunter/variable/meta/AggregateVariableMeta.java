package com.threathunter.variable.meta;

import com.threathunter.model.Property;
import com.threathunter.model.PropertyReduction;
import com.threathunter.variable.condition.ConditionParser;
import com.threathunter.variable.reduction.ReductionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A new variable that is generated by doing aggregation on another existing variable.
 *
 * created by www.threathunter.cn
 */
public class AggregateVariableMeta extends BaseVariableMeta {
    public static final String TYPE = "aggregate";
    static {
        addSubClass(TYPE, AggregateVariableMeta.class);
    }

    public enum AggregateType {
        batchtime,
        realtime,
        length,
        batchlength
    }

    private AggregateType aggregateType = AggregateType.batchtime;

    protected AggregateVariableMeta() {}

    public AggregateType getAggregateType() {
        return aggregateType;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + "&&" + "AggregateVariableMeta{" +
                "period=" + getTtl() +
                ", aggregateType=" + aggregateType +
                ", reductions=" + reduction +
                ", condition=" + condition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AggregateVariableMeta that = (AggregateVariableMeta) o;

        if (getTtl() != that.getTtl()) return false;
        if (aggregateType != that.aggregateType) return false;
        if (!getGroupKeys().equals(that.getGroupKeys())) return false;
        if (!reduction.equals(that.reduction)) return false;
        return !(condition != null ? !condition.equals(that.condition) : that.condition != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = (int) (31 * result + this.ttl);
        result = 31 * result + aggregateType.hashCode();
        result = 31 * result + reduction.hashCode();
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    @Override
    public Object to_json_object() {
        Map<Object, Object> result = (Map<Object, Object>) super.to_json_object();
        result.put("filter", ConditionParser.toJsonObject(this.condition));
        result.put("function", ReductionParser.toJsonObject(this.reduction));
        return result;
    }

    @Override
    protected void initialFunction(Map<String, Object> functionJson) {
        this.aggregateType = AggregateType.valueOf((String) functionJson.getOrDefault("type", "realtime"));
        this.setReduction(PropertyReduction.from_json_object(ReductionParser.parseFrom(functionJson, this.srcVariableMetasID.get(0), this.id)));
    }

    @Override
    protected List<Property> parseProperties() {
        List<Property> propertyList = new ArrayList<>();
        if (this.groupKeys != null && groupKeys.size() > 0) {
            this.getGroupKeys().forEach(property -> propertyList.add(
                    new Property(this.getId(), property.getName(), property.getType(), property.getSubType())));
        }
        return propertyList;
    }

    public static AggregateVariableMeta from_json_object(final Object obj) {
        AggregateVariableMeta result = new AggregateVariableMeta();
        result = BaseVariableMeta.from_json_object(obj, result);

        return result;
    }
}
