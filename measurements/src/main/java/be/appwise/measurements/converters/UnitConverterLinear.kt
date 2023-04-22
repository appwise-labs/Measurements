package be.appwise.measurements.converters


open class UnitConverterLinear(
    val coefficient: Double,
    val constant: Double = 0.0
) : UnitConverter {

    override fun baseUnitValue(value: Double): Double {
        return value * coefficient + constant
    }

    override fun value(baseUnitValue: Double): Double {
        return (baseUnitValue - constant) / coefficient
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnitConverterLinear

        if (coefficient != other.coefficient) return false
        if (constant != other.constant) return false

        return true
    }

    override fun hashCode(): Int {
        var result = coefficient.hashCode()
        result = 31 * result + constant.hashCode()
        return result
    }
}